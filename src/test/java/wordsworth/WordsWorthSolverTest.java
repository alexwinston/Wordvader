package wordsworth;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import junit.framework.Assert;
import junit.framework.TestCase;
import wordvader.Images;
import wordvader.Index;
import wordvader.Solution;
import wordvader.Solvable;
import wordvader.WordvaderGridStrategy;

import static wordvader.Asserts.assertArraysEqual;


public class WordsWorthSolverTest extends TestCase {
	
	public void tstStrategy() throws Exception {
		WordvaderGridStrategy grid6x6 = new WordsWorth6x6Grid();
		
		Assert.assertTrue(grid6x6.handles(
				new Images().scale(ImageIO.read(
						new FileInputStream("/Users/alexwinston/Desktop/Wordvader/strategy5_4.PNG")),
						320, 480)));
	}
	
	public void tstClassic() throws Exception {
		WordsWorthSolver solver = new WordsWorthSolver(
				this.getClass().getClassLoader().getResourceAsStream("neuroph/wordsworth3.nnet"));
		
		String[][] letters5_1 = {
				{ "o", "u", "o", "e", "d", "u" },
				{ "a", "p", "s", "b", "a", "c" },
				{ "p", "y", "e", "n", "i", "l" },
				{ "c", "f", "a", "c", "v", "i" },
				{ "c", "y", "a", "c", "f", "e" },
				{ "m", "k", "a", "n", "w", "o" }};
		assertArraysEqual(letters5_1, solver.letters(
				new FileInputStream("/Users/alexwinston/Desktop/Wordvader/Strategy5_1_C.PNG")));
		
		String[][] letters5_7 = {
				{ "v", "p", "g", "m", "e", "w" },
				{ "l", "w", "s", "y", "x", "e" },
				{ "u", "c", "s", "j", "c", "e" },
				{ "e", "u", "c", "y", "f", "e" },
				{ "i", "t", "g", "o", "e", "y" },
				{ "a", "p", "v", "i", "z", "v" }};
		assertArraysEqual(letters5_7, solver.letters(
				new FileInputStream("/Users/alexwinston/Desktop/Wordvader/Strategy5_7_W.PNG")));
		
		String[][] letters5_6 = {
				{ "o", "o", "a", "c", "i", "x", "k" },
				{ "qu", "y", "a", "o", "c", "e", "c" },
				{ "u", "w", "i", "g", "c", "e", "i" },
				{ "u", "f", "m", "x", "i", "u", "o" },
				{ "z", "n", "o", "x", "g", "o", "v" },
				{ "o", "b", "v", "w", "k", "b", "u" },
				{ "v", "d", "f", "u", "v", "p", "e" }};
		assertArraysEqual(letters5_6, solver.letters(
				new FileInputStream("/Users/alexwinston/Desktop/Wordvader/Strategy5_6_QuW.PNG")));
	}
	
	public void tstWildcard() throws Exception {
		WordsWorthSolver solver = new WordsWorthSolver(
				this.getClass().getClassLoader().getResourceAsStream("neuroph/wordsworth3.nnet"));
		
		for (char letter = 'a'; letter <= 'z'; letter++) {
			Set<String> solution = new HashSet<String>();
			try {
				String[][] letters = {
						{ "h", "s", "v", "g", "a" },
						{ "h", String.valueOf(letter), "v", "qu", "v" },
						{ "x", "p", "g", "n", "d" },
						{ "k", "s", "m", "g", "f" },
						{ "g", "j", "f", "z", "r" }};
	
				WordsWorth5x5Grid grid = new WordsWorth5x5Grid();
				
				for (int i = 0; i < grid.rowCount; i++) {
					for (int j = 0; j < grid.columnCount; j++) {
						Set<String> words = solver.solve(letters, solver.traverse(letters, grid, new Index(i,j)));
						for (String word : words)
							solution.add(word);
					}
				}
			} catch (Exception e) {
				e.printStackTrace(); // Ignore
	 		}
			
			System.out.println(solver.group(solution).hintGroups);
		}
	}
	
	public void tstDistribution() throws Exception {
		WordsWorthSolver solver = new WordsWorthSolver(
				this.getClass().getClassLoader().getResourceAsStream("neuroph/wordsworth3.nnet"));
		
		Map<String, Integer> distributions = new HashMap<String, Integer>();
		
		File folder = new File("/Users/alexwinston/Desktop/wwpix6");
		File[] listOfFiles = folder.listFiles();  
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String[][] letters = solver.letters(
						new FileInputStream(folder.getAbsolutePath() + "/" + listOfFiles[i].getName()));
				
				for (int j = 0; j < letters.length; j++) {
					for (int k = 0; k < letters[j].length; k++) {
						if (!distributions.containsKey(letters[j][k]))
							distributions.put(letters[j][k], 0);
						distributions.put(letters[j][k], distributions.get(letters[j][k]) + 1);
					}
				}
				//System.out.println(folder.getAbsolutePath() + "/" + listOfFiles[i].getName());
			}
		}
		
		System.out.println(distributions);
	}
	
	public void testConstructor() {
		WordsWorthSolver solver = new WordsWorthSolver(
				this.getClass().getClassLoader().getResourceAsStream("neuroph/wordsworth3.nnet"));
	}
	
	public void testGridStrategy() throws Exception {
		// 5x5 grid strategy
		WordvaderGridStrategy grid5x5 = new WordsWorth5x5Grid();
		Assert.assertTrue(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0029.PNG"))));
		Assert.assertTrue(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0032.PNG"))));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0009.PNG"))));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0010.PNG"))));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0033.PNG"))));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0034.PNG"))));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy1.png"))));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy3.png"))));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy12.png"))));
		Assert.assertTrue(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy10.png"))));
		Assert.assertTrue(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5.png"))));
		Assert.assertTrue(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy7.png"))));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy4.png"))));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/photo5.PNG"))));
		Assert.assertFalse(grid5x5.handles(
				new Images().scale(
						ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_1.PNG")),
						320, 480)));
		Assert.assertFalse(grid5x5.handles(
				new Images().scale(
						ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_4.PNG")),
						320, 480)));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_2.PNG"))));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_3.PNG"))));
		Assert.assertFalse(grid5x5.handles(
				new Images().scale(
						ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_5.PNG")),
						320, 480)));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5_6.png"))));
		Assert.assertTrue(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_1.png"))));
		Assert.assertTrue(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_2.png"))));
		Assert.assertTrue(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_3.png"))));
		Assert.assertFalse(grid5x5.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_7_1.png"))));
		
		// 6x6 grid strategy
		WordvaderGridStrategy grid6x6 = new WordsWorth6x6Grid();
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0029.PNG"))));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0032.PNG"))));
		Assert.assertTrue(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0035.PNG"))));
		Assert.assertTrue(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0010.PNG"))));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0033.PNG"))));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0034.PNG"))));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy1.png"))));
		Assert.assertTrue(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy3.png"))));
		Assert.assertTrue(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy12.png"))));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy10.png"))));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5.png"))));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy7.png"))));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy4.png"))));
		Assert.assertTrue(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/photo5.PNG"))));
		Assert.assertTrue(grid6x6.handles(
				new Images().scale(
						ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_1.PNG")),
						320, 480)));
		Assert.assertTrue(grid6x6.handles(
				new Images().scale(
						ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_4.PNG")),
						320, 480)));
		Assert.assertTrue(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_2.PNG"))));
		Assert.assertTrue(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_3.PNG"))));
		Assert.assertFalse(grid6x6.handles(
				new Images().scale(
						ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_5.PNG")),
						320, 480)));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5_6.png"))));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_1.png"))));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_2.png"))));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_3.png"))));
		Assert.assertFalse(grid6x6.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_7_1.png"))));
		
		// 7x7 grid strategy
		WordvaderGridStrategy grid7x7 = new WordsWorth7x7Grid();
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0029.PNG"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0032.PNG"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0035.PNG"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0010.PNG"))));
		Assert.assertTrue(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0033.PNG"))));
		Assert.assertTrue(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0036.PNG"))));
		Assert.assertTrue(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/photo2.PNG"))));
		Assert.assertTrue(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy1.png"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy3.png"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy12.png"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy10.png"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5.png"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy7.png"))));
		Assert.assertTrue(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy4.png"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/photo5.PNG"))));
		Assert.assertFalse(grid7x7.handles(
				new Images().scale(
						ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_1.PNG")),
						320, 480)));
		Assert.assertFalse(grid7x7.handles(
				new Images().scale(
						ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_4.PNG")),
						320, 480)));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_2.PNG"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_3.PNG"))));
		Assert.assertTrue(grid7x7.handles(
				new Images().scale(
						ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/strategy5_5.PNG")),
						320, 480)));
		Assert.assertTrue(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5_6.png"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_1.png"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_2.png"))));
		Assert.assertFalse(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_3.png"))));
		Assert.assertTrue(grid7x7.handles(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_7_1.png"))));
	}
	
	public void testLetters() throws Exception {
		WordsWorthSolver solver = new WordsWorthSolver(
				this.getClass().getClassLoader().getResourceAsStream("neuroph/wordsworth3.nnet"));
		
		String[][] strategy320_1 = {
				{ "h", "m", "v", "o", "qu" },
				{ "o", "m", "i", "p", "p" },
				{ "r", "h", "a", "f", "d" },
				{ "p", "j", "d", "d", "v" },
				{ "e", "c", "u", "b", "a" }};
		assertArraysEqual(strategy320_1, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/strategy320_1.png")));
		
		String[][] strategy320_3 = {
				{ "y", "s", "s", "g", "a" },
				{ "0", "v", "r", "k", "g" },
				{ "k", "c", "i", "v", "u" },
				{ "m", "c", "r", "f", "s" },
				{ "j", "t", "v", "p", "b" }};
		assertArraysEqual(strategy320_3, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/strategy320_3.png")));
		
		String[][] letters5_1 = {
				{ "o", "u", "o", "e", "d", "u" },
				{ "a", "p", "s", "b", "a", "c" },
				{ "p", "y", "e", "n", "i", "l" },
				{ "c", "f", "a", "c", "v", "i" },
				{ "c", "y", "a", "c", "f", "e" },
				{ "m", "k", "a", "n", "w", "o" }};
		assertArraysEqual(letters5_1, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5_1.PNG")));
		
		String[][] letters5_6 = {
				{ "o", "o", "a", "c", "i", "x", "k" },
				{ "qu", "y", "a", "o", "c", "e", "c" },
				{ "u", "w", "i", "g", "c", "e", "i" },
				{ "u", "f", "m", "x", "i", "u", "o" },
				{ "z", "n", "o", "x", "g", "o", "v" },
				{ "o", "b", "v", "w", "k", "b", "u" },
				{ "v", "d", "f", "u", "v", "p", "e" }};
		assertArraysEqual(letters5_6, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5_6.PNG")));
		
		String[][] letters5_7 = {
				{ "v", "p", "g", "m", "e", "w" },
				{ "l", "w", "s", "y", "x", "e" },
				{ "u", "c", "s", "j", "c", "e" },
				{ "e", "u", "c", "y", "f", "e" },
				{ "i", "t", "g", "o", "e", "y" },
				{ "a", "p", "v", "i", "z", "v" }};
		assertArraysEqual(letters5_7, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5_7.PNG")));
		
		String[][] letters1 = {
				{ "x", "a", "k", "l", "a", "w", "i" },
				{ "k", "a", "o", "v", "i", "o", "m" },
				{ "v", "m", "n", "n", "b", "e", "y" },
				{ "p", "l", "w", "r", "n", "h", "y" },
				{ "l", "c", "w", "i", "d", "m", "t" },
				{ "p", "c", "d", "d", "i", "p", "c" },
				{ "p", "e", "u", "f", "o", "u", "f" }};
		assertArraysEqual(letters1, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/photo1.PNG")));
		
		String[][] letters3 = {
				{ "u", "e", "z", "u", "i", "h", "d" },
				{ "w", "u", "m", "j", "s", "h", "e" },
				{ "m", "o", "a", "a", "i", "v", "d" },
				{ "a", "k", "h", "p", "x", "n", "f" },
				{ "i", "o", "t", "f", "x", "a", "o" },
				{ "w", "c", "f", "z", "w", "m", "r" },
				{ "y", "e", "t", "d", "t", "e", "l" }};
		assertArraysEqual(letters3, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/photo3.PNG")));
		
		String[][] letters4 = {
				{ "l", "y", "m", "y", "r" },
				{ "j", "a", "m", "w", "e" },
				{ "y", "z", "i", "g", "w" },
				{ "u", "k", "z", "e", "u" },
				{ "o", "p", "h", "j", "o" }};
		assertArraysEqual(letters4, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/photo4.PNG")));
		
		String[][] letters5 = {
				{ "f", "y", "o", "o", "i", "t" },
				{ "m", "0", "r", "o", "v", "i" },
				{ "u", "a", "o", "z", "i", "a" },
				{ "u", "c", "w", "g", "h", "i" },
				{ "c", "m", "u", "x", "b", "e" },
				{ "v", "j", "u", "g", "h", "c" }};
		assertArraysEqual(letters5, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/photo5.PNG")));
		
		String[][] letters6 = {
				{ "h", "n", "y", "o", "l", "a", "c" },
				{ "o", "v", "n", "qu", "e", "y", "d" },
				{ "p", "l", "p", "qu", "r", "y", "p" },
				{ "a", "j", "l", "h", "r", "0", "z" },
				{ "u", "g", "i", "z", "d", "x", "m" },
				{ "o", "f", "v", "p", "x", "s", "r" },
				{ "w", "u", "v", "j", "a", "d", "f" }};
		assertArraysEqual(letters6, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/photo6.PNG")));
		
		String[][] letters09 = {
				{ "h", "t", "d", "s", "g", "m" },
				{ "g", "a", "z", "x", "e", "x" },
				{ "k", "e", "g", "h", "h", "k" },
				{ "d", "e", "u", "j", "z", "l" },
				{ "e", "j", "b", "i", "v", "w" },
				{ "o", "u", "v", "a", "b", "v" }};
		assertArraysEqual(letters09, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0009.PNG")));
		
		String[][] letters10 = {
				{ "o", "k", "u", "s", "0", "m" },
				{ "h", "o", "r", "b", "g", "x" },
				{ "g", "p", "s", "t", "e", "k" },
				{ "k", "t", "s", "f", "h", "l" },
				{ "e", "j", "g", "x", "z", "w" },
				{ "o", "u", "u", "h", "v", "v" }};
		assertArraysEqual(letters10, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0010.PNG")));
		
		String[][] letters11 = {
				{ "o", "m", "p", "t", "h", "d" },
				{ "a", "m", "a", "a", "u", "w" },
				{ "s", "m", "e", "e", "i", "p" },
				{ "e", "n", "a", "l", "i", "r" },
				{ "s", "d", "v", "u", "e", "c" },
				{ "o", "p", "y", "a", "b", "v" }};
		assertArraysEqual(letters11, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0011.PNG")));
		
		String[][] letters12 = {
				{ "o", "0", "b", "m", "e", "b" },
				{ "c", "o", "w", "r", "b", "k" },
				{ "y", "e", "g", "w", "j", "i" },
				{ "g", "u", "u", "t", "e", "c" },
				{ "x", "e", "w", "j", "v", "z" },
				{ "y", "a", "k", "qu", "e", "p" }};
		assertArraysEqual(letters12, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0012.PNG")));
		
		String[][] letters13 = {
				{ "n", "s", "s", "p", "f", "a" },
				{ "o", "v", "h", "t", "h", "d" },
				{ "a", "m", "e", "a", "u", "w" },
				{ "s", "m", "x", "e", "i", "p" },
				{ "s", "d", "g", "u", "i", "c" },
				{ "o", "p", "e", "a", "b", "v" }};
		assertArraysEqual(letters13, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0013.PNG")));
		
		String[][] letters14 = {
				{ "a", "a", "g", "b", "l", "o" },
				{ "d", "a", "y", "a", "l", "o" },
				{ "t", "m", "i", "l", "e", "u" },
				{ "t", "x", "e", "g", "c", "w" },
				{ "s", "g", "i", "i", "f", "u" },
				{ "e", "d", "v", "o", "w", "i" }};
		assertArraysEqual(letters14, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0014.PNG")));
		
		String[][] letters15 = {
				{ "l", "l", "e", "j", "y", "v" },
				{ "l", "v", "u", "r", "t", "l" },
				{ "g", "s", "u", "a", "m", "t" },
				{ "f", "k", "u", "a", "j", "d" },
				{ "k", "x", "0", "e", "i", "qu" },
				{ "e", "n", "c", "qu", "a", "p" }};
		assertArraysEqual(letters15, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0015.PNG")));
		
		String[][] letters16 = {
				{ "b", "y", "t", "s", "u", "t" },
				{ "p", "y", "e", "i", "i", "g" },
				{ "e", "i", "b", "s", "c", "0" },
				{ "u", "g", "c", "z", "a", "g" },
				{ "u", "s", "v", "m", "y", "j" },
				{ "i", "b", "v", "z", "s", "c" }};
		assertArraysEqual(letters16, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0016.PNG")));
		
		String[][] letters17 = {
				{ "g", "t", "p", "k", "n", "k" },
				{ "a", "t", "f", "o", "n", "s" },
				{ "o", "r", "v", "p", "a", "p" },
				{ "d", "c", "s", "c", "y", "f" },
				{ "d", "n", "e", "n", "w", "o" },
				{ "a", "t", "e", "c", "v", "a" }};
		assertArraysEqual(letters17, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0017.PNG")));
		
		String[][] letters18 = {
				{ "r", "f", "b", "t", "l", "z" },
				{ "b", "w", "r", "v", "l", "y" },
				{ "v", "j", "s", "t", "h", "qu" },
				{ "i", "u", "w", "j", "s", "g" },
				{ "y", "qu", "s", "l", "v", "x" },
				{ "s", "t", "k", "z", "s", "t" }};
		assertArraysEqual(letters18, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0018.PNG")));
		
		String[][] letters29 = {
				{ "i", "t", "i", "g", "r" },
				{ "r", "a", "b", "o", "n" },
				{ "e", "p", "i", "b", "e" },
				{ "g", "k", "o", "m", "f" },
				{ "w", "e", "p", "v", "a" }};
		assertArraysEqual(letters29, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0029.PNG")));
		
		String[][] letters30 = {
				{ "s", "c", "o", "l", "z", "h", "i" },
				{ "w", "a", "g", "e", "s", "u", "m" },
				{ "t", "r", "o", "p", "i", "h", "w" },
				{ "e", "e", "l", "t", "n", "y", "x" },
				{ "m", "s", "e", "m", "a", "i", "l" },
				{ "g", "t", "i", "n", "t", "b", "o" },
				{ "n", "o", "y", "p", "e", "h", "x" }};
		assertArraysEqual(letters30, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0030.PNG")));
		
		String[][] letters31 = {
				{ "u", "f", "u", "f", "a", "c", "e" },
				{ "i", "b", "a", "p", "m", "d", "e" },
				{ "c", "o", "m", "a", "l", "e", "c" },
				{ "i", "c", "s", "b", "a", "u", "o" },
				{ "y", "i", "p", "o", "b", "o", "l" },
				{ "v", "s", "d", "e", "f", "s", "t" },
				{ "h", "o", "b", "h", "s", "g", "t" }};
		assertArraysEqual(letters31, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0031.PNG")));
		
		String[][] letters32 = {
				{ "w", "a", "g", "e", "s" },
				{ "m", "i", "l", "s", "t" },
				{ "c", "o", "l", "e", "n" },
				{ "a", "r", "d", "t", "o" },
				{ "t", "e", "n", "l", "o" }};
		assertArraysEqual(letters32, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0032.PNG")));
		
		String[][] letters33 = {
				{ "s", "c", "qu", "b", "h", "s", "n" },
				{ "e", "g", "i", "t", "u", "s", "p" },
				{ "e", "m", "s", "u", "a", "i", "t" },
				{ "c", "i", "g", "o", "p", "a", "n" },
				{ "b", "w", "f", "a", "l", "v", "o" },
				{ "e", "n", "h", "s", "l", "v", "e" },
				{ "w", "o", "d", "v", "e", "m", "h" }};
		assertArraysEqual(letters33, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0033.PNG")));
		
		String[][] letters34 = {
				{ "t", "h", "a", "l", "h", "e", "b" },
				{ "c", "r", "d", "o", "p", "h", "o" },
				{ "s", "m", "a", "n", "a", "m", "i" },
				{ "g", "s", "n", "i", "t", "i", "n" },
				{ "m", "w", "s", "p", "s", "m", "i" },
				{ "u", "v", "s", "a", "b", "y", "i" },
				{ "e", "p", "v", "a", "n", "v", "a" }};
		assertArraysEqual(letters34, solver.letters(
				ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0034.PNG")));
	}
	
	@SuppressWarnings("unchecked")
	public void testTraverse() {
		WordsWorthSolver solver = new WordsWorthSolver(
				ClassLoader.class.getResourceAsStream("/neuroph/wordsworth3.nnet"), 2);
		WordvaderGridStrategy grid = new WordsWorth6x6Grid();
		
		String[][] letters09 = {
				{ "h", "t", "d", "s", "g", "m" },
				{ "g", "a", "z", "x", "e", "x" },
				{ "k", "e", "g", "h", "h", "k" },
				{ "d", "e", "u", "j", "z", "l" },
				{ "e", "j", "b", "i", "v", "w" },
				{ "o", "u", "v", "a", "b", "v" }};
		
		List<List<Index>> traversals1 = solver.traverse(letters09, grid, new Index(0,0));
		Assert.assertEquals(traversals1, Arrays.asList(
				Arrays.asList(new Index(0,0), new Index(1,0)),
				Arrays.asList(new Index(0,0), new Index(0,1))));
		
		List<List<Index>> traversals2 = solver.traverse(letters09, grid, new Index(1,0));
		Assert.assertEquals(traversals2, Arrays.asList(
				Arrays.asList(new Index(1,0), new Index(0,0)),
				Arrays.asList(new Index(1,0), new Index(0,1)),
				Arrays.asList(new Index(1,0), new Index(2,0)),
				Arrays.asList(new Index(1,0), new Index(1,1))));
		
		List<List<Index>> traversals3 = solver.traverse(letters09, grid, new Index(5,0));
		Assert.assertEquals(traversals3, Arrays.asList(
				Arrays.asList(new Index(5,0), new Index(4,0)),
				Arrays.asList(new Index(5,0), new Index(4,1)),
				Arrays.asList(new Index(5,0), new Index(5,1))));
		
		List<List<Index>> traversals4 = solver.traverse(letters09, grid, new Index(0,1));
		Assert.assertEquals(traversals4, Arrays.asList(
				Arrays.asList(new Index(0,1), new Index(1,0)),
				Arrays.asList(new Index(0,1), new Index(1,2)),
				Arrays.asList(new Index(0,1), new Index(1,1)),
				Arrays.asList(new Index(0,1), new Index(0,0)),
				Arrays.asList(new Index(0,1), new Index(0,2))));
		
		List<List<Index>> traversals5 = solver.traverse(letters09, grid, new Index(1,1));
		Assert.assertEquals(traversals5, Arrays.asList(
				Arrays.asList(new Index(1,1), new Index(0,1)),
				Arrays.asList(new Index(1,1), new Index(2,0)),
				Arrays.asList(new Index(1,1), new Index(2,2)),
				Arrays.asList(new Index(1,1), new Index(2,1)),
				Arrays.asList(new Index(1,1), new Index(1,0)),
				Arrays.asList(new Index(1,1), new Index(1,2))));
		
		List<List<Index>> traversals6 = solver.traverse(letters09, grid, new Index(5,1));
		Assert.assertEquals(traversals6, Arrays.asList(
				Arrays.asList(new Index(5,1), new Index(4,1)),
				Arrays.asList(new Index(5,1), new Index(5,0)),
				Arrays.asList(new Index(5,1), new Index(5,2))));
		
		List<List<Index>> traversals7 = solver.traverse(letters09, grid, new Index(5,5));
		Assert.assertEquals(traversals7, Arrays.asList(
				Arrays.asList(new Index(5,5), new Index(4,5)),
				Arrays.asList(new Index(5,5), new Index(5,4))));
	}
	
	public void testSolves() throws Exception {
		Solvable solver = new WordsWorthSolvable();

		Assert.assertTrue(solver.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0018.PNG"))));
		Assert.assertTrue(solver.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5_1.png"))));
		Assert.assertTrue(solver.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5_2.png"))));
		Assert.assertTrue(solver.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5_3.png"))));
		Assert.assertTrue(solver.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy1.png"))));
		Assert.assertTrue(solver.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy5.png"))));
		Assert.assertTrue(solver.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_1.png"))));
		Assert.assertTrue(solver.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/Strategy320_7_1.png"))));
		
		Assert.assertFalse(solver.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0002.PNG"))));
	}
	
	public void testSolve() throws Exception {
		WordsWorthSolver solver = new WordsWorthSolver(
				ClassLoader.class.getResourceAsStream("/neuroph/wordsworth3.nnet"));
		WordvaderGridStrategy grid = new WordsWorth6x6Grid();
		
		String[][] letters1 = {
				{ "0", "t", "e", "s", "t", "0" },
				{ "b", "0", "a", "0", "0", "0" },
				{ "a", "0", "m", "s", "0", "0" },
				{ "k", "0", "0", "0", "0", "0" },
				{ "e", "0", "y", "t", "0", "s" },
				{ "0", "0", "0", "e", "f", "a" }};
		
		Set<String> words1 = solver.solve(letters1, solver.traverse(letters1, grid, new Index(0,0)));
		Assert.assertEquals(0, words1.size());
		
		Set<String> words2 = solver.solve(letters1, solver.traverse(letters1, grid, new Index(1,0)));
		Assert.assertEquals(1, words2.size());
		Assert.assertTrue(words2.containsAll(Arrays.asList("bake")));
		
		Set<String> words3 = solver.solve(letters1, solver.traverse(letters1, grid, new Index(0,1)));
		Assert.assertEquals(5, words3.size());
		Assert.assertTrue(words3.containsAll(Arrays.asList("teams", "test", "teas", "tams", "team")));
		
		Set<String> words4 = solver.solve(letters1, solver.traverse(letters1, grid, new Index(4,5)));
		Assert.assertEquals(2, words4.size());
		Assert.assertTrue(words4.containsAll(Arrays.asList("safe", "safety")));
		
		Solution s1 = solver.solve(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0002.PNG")));
		Assert.assertEquals(2, s1.hintGroupSegments.size());
		Assert.assertEquals("Dots", s1.hintGroupSegments.get(0));
		Assert.assertEquals("Length", s1.hintGroupSegments.get(1));
		Assert.assertEquals(2, s1.hintGroupKeys.size());
		Assert.assertEquals(0, s1.hintGroups.get(0).size());
		Assert.assertEquals(0, s1.hintGroups.get(1).size());
		
		Solution s2 = solver.solve(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0018.PNG")));
		Assert.assertEquals(2, s2.hintGroupKeys.size());
		Assert.assertEquals(0, s2.hintGroups.get(0).size());
		Assert.assertEquals(0, s2.hintGroups.get(1).size());
	}
}
