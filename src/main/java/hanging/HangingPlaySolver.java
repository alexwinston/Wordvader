package hanging;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.core.NeuralNetwork;

import wordvader.Images;
import wordvader.Solution;
import wordvader.Solver;
import wordvader.Trie;

public class HangingPlaySolver implements Solver {
	// Neuroph plugin to generate the accuracies of letter recognition
	private ImageRecognitionPlugin imageRecognition;
	
	// ENABLE word dictionary
	private Trie dictionary = new Trie();
	
	// Tile letter points
	Map<Character, Integer> points = new HashMap<Character, Integer>();
	
	public HangingPlaySolver() {
		this(HangingPlaySolver.class.getResourceAsStream("/neuroph/hanging.nnet"));
	}
	
	public HangingPlaySolver(InputStream nnetStream) {
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
			String letters = this.letters(image);
			Set<Play> words = this.permutate(letters);
			
			List<Play> plays = new ArrayList<Play>(words);
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
			
			// Sort plays by length
			Collections.sort(plays, new Comparator<Play>() {
				public int compare(Play p1, Play p2) {
					return p2.getWord().length() - p1.getWord().length();
				}
			});
			
			// Rank words by length
			solution.hintGroupSegments.add("Length");
			
			HashMap<String, List<String>> lengthRankings = new HashMap<String, List<String>>();
			List<String> lengthRankingKeys = new ArrayList<String>();
			for (Play play : plays) {
				String points = play.getWord().length() + " Letter Words";
				if (!lengthRankings.containsKey(points)) {
					lengthRankings.put(points, new ArrayList<String>());
					lengthRankingKeys.add(points);
				}
				
				lengthRankings.get(points).add(play.getWord());
			}
			
			solution.hintGroupKeys.add(lengthRankingKeys);
			solution.hintGroups.add(lengthRankings);
		} catch (Exception e) {
			e.printStackTrace(); // Ignore
		}
		
		return solution;
	}

	public String letters(BufferedImage image) throws Exception {
		StringBuilder letters = new StringBuilder();
		
        int width = 25;
        int height = 25;
        
		BufferedImage scaledImage = new Images().scale(image, 320, 480);
        for (int row = 0; row < 2; row++) {
	        for (int column = 0; column < 6; column++) {
	        	int xOffset = (column * (width + 17)) + 10;
	        	int yOffset = (row * (height + 18)) + 404;
	        	BufferedImage subImage = scaledImage.getSubimage(xOffset, yOffset, width, height);
//	        	ImageIO.write(subImage, "png", new File("/Users/alexwinston/Desktop/test/orig_" + row + "_" + column + ".png"));
	        	
	        	String letter = "";
			    Double accuracy = 0d;
			    
		        HashMap<String, Double> output = this.imageRecognition.recognizeImage(subImage);
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
        }
        
        return letters.toString();
	}
	
	public Set<Play> permutate(String s) {
		return this.permutate("", s, new HashSet<Play>());
	}

	private Set<Play> permutate(String prefix, String s, Set<Play> words) {
		if (prefix.length() >= 4 && !this.dictionary.stringInTrie(prefix))
			return words;
		
		if (prefix.length() >= 4 && this.dictionary.wordInTrie(prefix)) {
			words.add(new Play(prefix, this.points));
		}
		
		int index = s.length();
		if (index == 0) {
			// Ignore
		} else {
			for(int i = 0; i < index; i++) {
	        	permutate(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, index), words);
	        }
	    }
		
		return words;
	}
}
