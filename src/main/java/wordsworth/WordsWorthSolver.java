package wordsworth;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorConvertOp;
import java.awt.image.LookupOp;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.core.NeuralNetwork;

import wordvader.Images;
import wordvader.Index;
import wordvader.Solution;
import wordvader.Solver;
import wordvader.Trie;
import wordvader.WordvaderGridStrategy;

public class WordsWorthSolver implements Solver {
	// Neuroph plugin to generate the accuracies of letter recognition
	private ImageRecognitionPlugin imageRecognition;
	
	// Binary Search Tree implementation for optimum performance
	private Trie dictionary = new Trie();
	
	// The max number of letters that are traversed for possible words
	private int maxTraversal = 8;
	
	// Hash that contains the mappings between letters and points
	private Map<String, Integer> points = new HashMap<String, Integer>();

    public WordsWorthSolver() {
        this(WordsWorthSolver.class.getResourceAsStream("/neuroph/wordsworth3.nnet"));
    }

	public WordsWorthSolver(InputStream nnetStream) {
		this.imageRecognition =
	    	(ImageRecognitionPlugin) NeuralNetwork.load(nnetStream).getPlugin(
	    			ImageRecognitionPlugin.IMG_REC_PLUGIN_NAME);
		
		this.dictionary.setupTrie(
				this.getClass().getResourceAsStream("/wordsworth.dic"));
		
		this.points.put("a", 0);
		this.points.put("b", 1);
		this.points.put("c", 1);
		this.points.put("d", 1);
		this.points.put("e", 0);
		this.points.put("f", 2);
		this.points.put("g", 1);
		this.points.put("h", 2);
		this.points.put("i", 0);
		this.points.put("j", 3);
		this.points.put("k", 2);
		this.points.put("l", 1);
		this.points.put("m", 1);
		this.points.put("n", 1);
		this.points.put("o", 0);
		this.points.put("p", 1);
		this.points.put("q", 2);
		this.points.put("r", 1);
		this.points.put("s", 0);
		this.points.put("t", 1);
		this.points.put("u", 1);
		this.points.put("v", 2);
		this.points.put("w", 2);
		this.points.put("x", 3);
		this.points.put("y", 2);
		this.points.put("z", 3);
	}
	
	public WordsWorthSolver(InputStream nnetStream, int maxTraversal) {
		this(nnetStream);
		this.maxTraversal = maxTraversal;
	}
	
	public Solution group(Set<String> words) {
		Solution solution = new Solution();

		// Rank words by dot totals
		solution.hintGroupSegments.add("Dots");
		
		HashMap<String, List<String>> dotRankings = new HashMap<String, List<String>>();
		for (String word : words) {
			String dots = this.dots(word);
			if (!dotRankings.containsKey(dots))
				dotRankings.put(dots, new ArrayList<String>());
			
			dotRankings.get(dots).add(this.dottify(word));
		}
		
		List<String> dotRankingKeys = new ArrayList<String>(new TreeSet<String>(dotRankings.keySet()));
		Collections.reverse(dotRankingKeys);
		solution.hintGroupKeys.add(dotRankingKeys);
		solution.hintGroups.add(dotRankings);

		// Rank words by letter totals
		solution.hintGroupSegments.add("Length");
		
		HashMap<String, List<String>> letterRankings = new HashMap<String, List<String>>();
		for (String word : words) {
			String letters = word.length() + " Letter Words";
			if (!letterRankings.containsKey(letters))
				letterRankings.put(letters, new ArrayList<String>());
			
			letterRankings.get(letters).add(this.dottify(word));
		}
		
		List<String> letterRankingKeys = new ArrayList<String>(new TreeSet<String>(letterRankings.keySet()));
		Collections.reverse(letterRankingKeys);
		solution.hintGroupKeys.add(letterRankingKeys);
		solution.hintGroups.add(letterRankings);

		return solution;
	}
	
	private String dots(String word) {
		int total = 0;
		for (int i = 0; i < word.length(); i++)
			total += this.points.get(String.valueOf(word.charAt(i)));
				
		return total + " Dot Words";
	}
	
	private String dottify(String word) {
		StringBuilder dottified = new StringBuilder();
		
		for (int i = 0; i < word.length(); i++) {
			dottified.append(word.charAt(i));
			
			for (int j = 0; j < this.points.get(String.valueOf(word.charAt(i))); j++)
				dottified.append(".");
			
			dottified.append(" ");
		}
		
		return dottified.toString();
	}
	
	public Solution solve(BufferedImage bufferedImage) {
		Set<String> solution = new HashSet<String>();
		
		try {
			BufferedImage image = new Images().scale(bufferedImage, 320, 480);
	
			// Determine the grid strategy, 5x5 ,6x6 or 7x7 from the image
			WordvaderGridStrategy grid = new WordvaderGridStrategy().create(image);
			
			// Decode the letters from the supplied image
			String[][] letters = this.letters(image, grid);
			
			/*
			for (int i = 0; i < grid.rowCount; i++) {
				for (int j = 0; j < grid.columnCount; j++) {
					System.out.print(letters[i][j] + " ");
				}
				System.out.println();
			}
			*/
			
			// Solve the puzzle after traversing the letters		
			for (int i = 0; i < grid.rowCount; i++) {
				for (int j = 0; j < grid.columnCount; j++) {
					Set<String> words = this.solve(letters, this.traverse(letters, grid, new Index(i,j)));
					for (String word : words)
						solution.add(word);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Ignore
 		}
		
		return this.group(solution);
	}
	
	public Set<String> solve(String[][] letters, List<List<Index>> traversals) {
		Set<String> words = new HashSet<String>();
		
		for (List<Index> traversal : traversals) {
			StringBuilder builder = new StringBuilder();
			
			for (Index letter : traversal) {
				builder.append(letters[letter.x][letter.y]);
				
				String word = builder.toString();
				if (word.length() > 3 && this.dictionary.wordInTrie(word))
					words.add(word);
			}
		}
		
		return words;
	}
	
	public List<BufferedImage> letterImages(BufferedImage image, WordvaderGridStrategy grid) throws Exception {
		ArrayList<BufferedImage> blackAndWhiteImages = new ArrayList<BufferedImage>();
			
		byte[] invert = new byte[256];
		for (int i = 0; i < 256; i++)
			invert[i] = (byte)(255 - i);
		
        int width = grid.letterWidth;
        int height = grid.letterHeight;
        
        for (int i = 0; i < grid.rowCount; i++) {
	        for (int j = 0; j < grid.columnCount; j++) {
	        	int xOffset = grid.xOffset(i, j);
	        	int yOffset = grid.yOffset(i, j);
	        	BufferedImage subImage = new Images().scale(image.getSubimage(xOffset, yOffset, width, height), 25, 20);
//	        	ImageIO.write(subImage, "png", new File("/Users/alexwinston/Desktop/test/orig_" + j + "_" + i + ".png"));
	        	
	        	ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
	        	BufferedImage grayImage = op.filter(subImage, null);
//	        	ImageIO.write(grayImage, "png", new File("/Users/alexwinston/Desktop/test/gray_" + i + "_" + j + ".png"));
	        	
	        	BufferedImage invertedImage = grayImage;
	        	if (grid.invert(subImage)) {
	        		BufferedImageOp invertOp = new LookupOp(new ByteLookupTable(0, invert), null);
	        		invertedImage = invertOp.filter(grayImage, null);
	        	}
	        	
	        	BufferedImage blackAndWhiteImage = new BufferedImage(invertedImage.getWidth(null), invertedImage.getHeight(null), BufferedImage.TYPE_BYTE_BINARY);
	        	Graphics2D g = (Graphics2D) blackAndWhiteImage.getGraphics();
	        	g.drawImage(invertedImage, 0, 0, null);
	        	g.dispose();
	        	
	        	blackAndWhiteImages.add(blackAndWhiteImage);
//	        	ImageIO.write(blackAndWhiteImage, "png", new File("/Users/alexwinston/Desktop/test/bw_" + i + "_" + j + ".png"));
	        }
        }
        
        return blackAndWhiteImages;
	}
	
	public String[][] letters(InputStream imageStream) throws Exception {
		BufferedImage image = new Images().scale(ImageIO.read(imageStream), 320, 480);
		
		// Create the grid strategy before identifying the letters
		return this.letters(image, new WordvaderGridStrategy().create(image));
	}
	
	public String[][] letters(BufferedImage image, WordvaderGridStrategy grid) throws Exception {
		return this.letters(this.letterImages(image, grid), grid);
	}
	
	public String[][] letters(List<BufferedImage> letterImages, WordvaderGridStrategy grid) throws Exception {
		String letters[][] = new String[grid.rowCount][grid.columnCount];
		
		for (int i = 0; i < letterImages.size(); i++) {
			BufferedImage letterImage = letterImages.get(i);
			String letter = "";
		    Double accuracy = 0d;
		    
	        HashMap<String, Double> output = this.imageRecognition.recognizeImage(letterImage);
	        for (String key : output.keySet()) {
	        	if (output.get(key) > accuracy) {
	        		letter = key;
	        		accuracy = output.get(key);
	        		
	        		// Handle wild uniquely because "w" is more accurate
	        		if (letter.equals("0") && accuracy > .95d)
	        			break;
	        	}
	        }
	        
	        letters[i % grid.rowCount][i / grid.columnCount] = letter;
//	        ImageIO.write(letterImage, "png", new File("/Users/alexwinston/Desktop/test/" + letter + "_" + (i / grid.columnCount) + "_" + (i % grid.rowCount) + ".png"));
		}
		
		return letters;
	}
	
	public List<List<Index>> traverse(String[][]letters, WordvaderGridStrategy grid, Index start) {
		return this.traverse(letters, grid, start, new ArrayList<Index>(), new ArrayList<List<Index>>());
	}
	
	public List<List<Index>> traverse(String[][] letters, WordvaderGridStrategy grid, Index start, ArrayList<Index> traversed, ArrayList<List<Index>> results) {
		ArrayList<Index> traversing = (ArrayList<Index>) traversed.clone();
		traversing.add(start);
		
		if (traversing.size() == this.maxTraversal) {
			results.add(traversing);
			return results;
		}
		
		Index up = new Index(start.x - 1, start.y);
		if (up.x >= 0 && !traversed.contains(up)) {
			//System.out.print("up ");
			this.traverse(letters, grid, up, traversing, results);
		}
		
		// Can go up diagonally on even columns
		if (start.y % 2 == 0) {
			Index upLeft = new Index(start.x - 1, start.y - 1);
			if (upLeft.x >= 0 && upLeft.y >= 0 && !traversed.contains(upLeft)) {
				//System.out.print("up-left ");
				this.traverse(letters, grid, upLeft, traversing, results);
			}
			
			Index upRight = new Index(start.x - 1, start.y + 1);
			if (upRight.x >= 0 && upRight.y < grid.columnCount && !traversed.contains(upRight)) {
				//System.out.print("up-right ");
				this.traverse(letters, grid, upRight, traversing, results);
			}
		}
		
		// Can go down diagonally on odd columns
		if (start.y % 2 == 1) {
			Index downLeft = new Index(start.x + 1, start.y - 1);
			if (downLeft.x < grid.rowCount && downLeft.y >= 0 && !traversed.contains(downLeft)) {
				//System.out.print("down-right ");
				this.traverse(letters, grid, downLeft, traversing, results);
			}
			
			Index downRight = new Index(start.x + 1, start.y + 1);
			if (downRight.x < grid.rowCount && downRight.y < grid.columnCount && !traversed.contains(downRight)) {
				//System.out.print("down-right ");
				this.traverse(letters, grid, downRight, traversing, results);
			}
		}
		
		Index down = new Index(start.x + 1, start.y);
		if (down.x < grid.rowCount && !traversed.contains(down)) {
			//System.out.print("down ");
			this.traverse(letters, grid, down, traversing, results);
		}
		
		Index left = new Index(start.x, start.y - 1);
		if (left.y >= 0 && !traversed.contains(left)) {
			//System.out.print("left ");
			this.traverse(letters, grid, left, traversing, results);
		}
		
		Index right = new Index(start.x, start.y + 1);
		if (right.y < grid.rowCount && !traversed.contains(right)) {
			//System.out.print("right ");
			this.traverse(letters, grid, right, traversing, results);
		}
		
		return results;
	}
}
