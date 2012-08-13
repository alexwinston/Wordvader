package hanging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.core.NeuralNetwork;

import wordvader.AlphanumComparator;
import wordvader.Color;
import wordvader.Images;
import wordvader.Solution;
import wordvader.Solver;
import wordvader.Trie;

public class HangingGuessSolver implements Solver {
	// Neuroph plugin to generate the accuracies of letter recognition
	private ImageRecognitionPlugin imageRecognition;
	
	// ENABLE word dictionary
	private Trie dictionary = new Trie();
	
	// Tile letter points
	Map<Character, Integer> points = new HashMap<Character, Integer>();
	
	private static int[] xOffsets = { 6, 46, 85, 124, 166, 204, 245, 284 };
	
	public HangingGuessSolver() {
		this(HangingPlaySolver.class.getResourceAsStream("/neuroph/hanging.nnet"));
	}
	
	public HangingGuessSolver(InputStream nnetStream) {
		this.imageRecognition =
	    	(ImageRecognitionPlugin) NeuralNetwork.load(nnetStream).getPlugin(
	    			ImageRecognitionPlugin.IMG_REC_PLUGIN_NAME);
		
		this.dictionary.setupTrie(
				this.getClass().getResourceAsStream("/enable1.dic"));
		
		this.points.put('a', 1);
		this.points.put('b', 4);
		this.points.put('c', 4);
		this.points.put('d', 2);
		this.points.put('e', 1);
		this.points.put('f', 4);
		this.points.put('g', 3);
		this.points.put('h', 3);
		this.points.put('i', 1);
		this.points.put('j', 10);
		this.points.put('k', 5);
		this.points.put('l', 2);
		this.points.put('m', 4);
		this.points.put('n', 2);
		this.points.put('o', 1);
		this.points.put('p', 4);
		this.points.put('q', 10);
		this.points.put('r', 1);
		this.points.put('s', 1);
		this.points.put('t', 1);
		this.points.put('u', 2);
		this.points.put('v', 5);
		this.points.put('w', 4);
		this.points.put('x', 8);
		this.points.put('y', 3);
		this.points.put('z', 10);
	}
	
	public Play getPlay(String word) {
		return new Play(word, this.points);
	}
	
	public Solution solve(BufferedImage image) {
		Solution solution = new Solution();
		
		try {
			solution = this.solve(new Guess(this.letters(image)), this.remainingLetters(image));
		} catch(Exception e) {
			e.printStackTrace(); // Ignore
		}
		
		return solution;
	}
	
	public Solution solve(Guess guess, String remainingLetters) {
		Solution solution = new Solution();
		
		try {
			List<Play> plays = new ArrayList<Play>(this.plays(guess, remainingLetters));

			// Rank next letter by probability
			solution.hintGroupSegments.add("Next Letter");
			
			Map<Character, Double> letterCounts = new HashMap<Character, Double>();
			for (Play play : plays) {
				Set<Character> lettersPlayed = guess.getLetters(play);
				for (char letter : remainingLetters.toCharArray()) {
					if (lettersPlayed.contains(letter)) {
						if (!letterCounts.containsKey(letter))
							letterCounts.put(letter, 0.0);
	
						letterCounts.put(letter, letterCounts.get(letter) + 1);
					}
				}
			}
			
			Map<String, List<String>> probabilityRankings = new HashMap<String, List<String>>();
			
			for (char letter : letterCounts.keySet()) {
				String probability =
					String.format("%1.2f", (letterCounts.get(letter) / plays.size()) * 100) + "% Probability";
				
				if (!probabilityRankings.containsKey(probability))
					probabilityRankings.put(probability, new ArrayList<String>());
				
				probabilityRankings.get(probability).add(String.valueOf(letter));
			}
			
			List<String> probabilityRankingKeys = new ArrayList<String>(probabilityRankings.keySet());
			Collections.sort(probabilityRankingKeys, new AlphanumComparator());
			Collections.reverse(probabilityRankingKeys);
			
			solution.hintGroupKeys.add(probabilityRankingKeys);
			solution.hintGroups.add(probabilityRankings);
			
			// Sort plays by points
			Collections.sort(plays, new Comparator<Play>() {
				public int compare(Play p1, Play p2) {
					return p2.getPoints() - p1.getPoints();
				}
			});
			
			// Rank words by point totals
			solution.hintGroupSegments.add("Points");
			
			HashMap<String, List<String>> pointRankings = new HashMap<String, List<String>>();
			List<String> pointRankingKeys = new ArrayList<String>();
			for (Play play : plays) {
				String points = play.getPoints() + " Point Words";
				if (!pointRankings.containsKey(points)) {
					pointRankings.put(points, new ArrayList<String>());
					pointRankingKeys.add(points);
				}
				
				pointRankings.get(points).add(play.getWord());
			}
			
			solution.hintGroupKeys.add(pointRankingKeys);
			solution.hintGroups.add(pointRankings);
		} catch (Exception e) {
			e.printStackTrace(); // Ignore
		}
		
		return solution;
	}
	
	public Set<Play> plays(Guess guess, String letters) {
		return this.permutate(guess, letters);
	}

	public String letters(BufferedImage image) throws Exception {
		StringBuilder letters = new StringBuilder();
		
        int width = 25;
        int height = 25;
        
        for (int i = 0; i < 8; i++) {        	
        	int xOffset = xOffsets[i]; // (i * (width + 15)) + 6;
        	int yOffset = 266;
        	BufferedImage subImage = image.getSubimage(xOffset, yOffset, width, height);
        	
        	// Check that there is a tile in the slot
        	if (!new Color(subImage.getRGB(0, 0)).threshold(new Color(232, 177, 57), 5))
        		break;
//        	ImageIO.write(subImage, "png", new File("/Users/alexwinston/Desktop/test/orig_" + i + ".png"));
        	
        	String letter = "";
		    Double accuracy = 0d;
		    
	        HashMap<String, Double> output = this.imageRecognition.recognizeImage(new Images().scale(subImage, 25, 25));
	        for (String key : output.keySet()) {
	        	if (output.get(key) > accuracy) {
	        		letter = key;
	        		accuracy = output.get(key);
	        		
	        		if (accuracy > .95d)
	        			break;
	        	}
	        }
	        letters.append(letter);
        }
        
        return letters.toString();
	}
	
	public String remainingLetters(BufferedImage image) throws Exception {
		StringBuilder remainingLetters = new StringBuilder();
		
        int width = 25;
        int height = 24;
        
        for (int row = 0, letter = 0; row < 4; row++) {
	        for (int column = 0; column < 7; column++) {
	        	if (letter++ == 26)
	        		break; // There are only 26 letters in the alphabet
	        	
	        	int xOffset = (column * (width + 17)) + 20;
	        	int yOffset = (row * (height + 17)) + 322;
	        	BufferedImage subImage = image.getSubimage(xOffset, yOffset, width, height);
//	        	ImageIO.write(subImage, "png", new File("/Users/alexwinston/Desktop/test/orig_" + row + "_" + column + ".png"));

	        	if (new Color(subImage.getRGB(0, 0)).threshold(new Color(241, 172, 68), 5))
	        		remainingLetters.append((char) ('\u0060' + letter));
	        }
        }
        
        return remainingLetters.toString();
	}
	
	public Set<Play> permutate(Guess guess, String letters) {
		return this.permutate("", guess, letters, 0, guess.getWord().length(), new HashSet<Play>());
	}
	
	public Set<Play> permutate(String prefix, Guess guess, String letters, int index, int length, HashSet<Play> plays) {
		String word = guess.getWord(prefix);
		if (!this.dictionary.stringInTrie(word))
			return plays;
		
		if (word.length() >= 4 && word.length() == guess.getWord().length() && this.dictionary.wordInTrie(word)) {
			plays.add(new Play(word, this.points));
		}
		
		for (int i = 0; i < letters.length(); i++) {			
			if (prefix.length() < guess.getBlanks())
				this.permutate(prefix + letters.charAt(i), guess, letters, index + 1, length, plays);
		}
		
		return plays;
	}
}
