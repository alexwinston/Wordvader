package bookworm;

import junit.framework.TestCase;
import wordvader.Images;
import wordvader.Solvable;

import static wordvader.Asserts.assertArraysEqual;

public class BookwormSolverTest extends TestCase {
	public void testConsutructor() {
		BookwormSolver solver = new BookwormSolver(
				this.getClass().getClassLoader().getResourceAsStream("neuroph/bookworm.nnet"));
	}
	
	public void testLetters() throws Exception {
		BookwormSolver solver = new BookwormSolver(
				this.getClass().getClassLoader().getResourceAsStream("neuroph/bookworm.nnet"));

		String[][] letters34 = {
				{ "n", "f", "y", "e", "n", "t", "t" },
				{ "t", "e", "a", "r", "t", "e", "a" },
				{ "c", "t", "u", "c", "g", "g", "e" },
				{ "e", "u", "i", "o", "i", "p", "t" },
				{ "d", "e", "r", "h", "n", "c", "y" },
				{ "z", "a", "s", "e", "r", "w", "p" },
				{ "s", "e", "d", "w", "w", "a", "j" }};
		assertArraysEqual(letters34, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0034.PNG")));
		
		String[][] letters35 = {
				{ "n", "f", "r", "o", "n", "t", "t" },
				{ "t", "e", "e", "t", "t", "e", "a" },
				{ "c", "t", "u", "c", "g", "g", "e" },
				{ "e", "u", "i", "o", "i", "p", "t" },
				{ "d", "e", "r", "h", "n", "c", "y" },
				{ "z", "a", "s", "e", "r", "w", "p" },
				{ "s", "e", "d", "w", "w", "a", "j" }};
		assertArraysEqual(letters35, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0035.PNG")));
		
		String[][] letters36 = {
				{ "n", "p", "a", "t", "g", "u", "t" },
				{ "t", "w", "r", "f", "e", "t", "a" },
				{ "c", "t", "u", "o", "i", "g", "e" },
				{ "e", "u", "i", "i", "e", "p", "t" },
				{ "d", "e", "r", "h", "g", "c", "y" },
				{ "z", "a", "s", "e", "r", "w", "p" },
				{ "s", "e", "d", "w", "w", "a", "j" }};
		assertArraysEqual(letters36, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0036.PNG")));
		
		String[][] letters39 = {
				{ "a", "f", "i", "d", "r", "u", "o" },
				{ "e", "r", "g", "e", "o", "b", "n" },
				{ "o", "t", "e", "r", "e", "e", "f" },
				{ "f", "w", "g", "f", "i", "a", "e" },
				{ "a", "u", "n", "l", "e", "o", "n" },
				{ "u", "n", "o", "t", "i", "e", "d" },
				{ "a", "m", "n", "g", "f", "a", "r" }};
		assertArraysEqual(letters39, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0039.PNG")));
		
		String[][] letters40 = {
				{ "t", "f", "g", "d", "u", "a", "i" },
				{ "e", "e", "r", "z", "r", "l", "o" },
				{ "f", "o", "i", "t", "k", "m", "n" },
				{ "c", "r", "n", "m", "t", "e", "g" },
				{ "n", "j", "z", "a", "n", "u", "e" },
				{ "a", "u", "e", "w", "m", "h", "t" },
				{ "s", "o", "f", "i", "h", "e", "e" }};
		assertArraysEqual(letters40, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0040.PNG")));
		
		String[][] letters41 = {
				{ "t", "r", "e", "b", "h", "a", "i" },
				{ "e", "f", "g", "c", "u", "l", "o" },
				{ "f", "o", "r", "d", "r", "m", "n" },
				{ "c", "r", "n", "m", "t", "e", "g" },
				{ "n", "j", "z", "a", "n", "u", "e" },
				{ "a", "u", "e", "w", "m", "h", "t" },
				{ "s", "o", "f", "i", "h", "e", "e" }};
		assertArraysEqual(letters41, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0041.PNG")));
		
		String[][] letters42 = {
				{ "e", "m", "e", "i", "u", "o", "n" },
				{ "f", "n", "n", "r", "e", "c", "o" },
				{ "a", "r", "b", "e", "i", "i", "l" },
				{ "n", "f", "u", "t", "e", "a", "i" },
				{ "o", "s", "i", "d", "r", "d", "r" },
				{ "e", "e", "l", "e", "b", "m", "s" },
				{ "l", "y", "e", "p", "a", "p", "u" }};
		assertArraysEqual(letters42, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0042.PNG")));
		
		String[][] letters43 = {
				{ "e", "m", "e", "i", "u", "o", "n" },
				{ "f", "n", "n", "r", "e", "c", "o" },
				{ "a", "r", "b", "e", "i", "i", "l" },
				{ "n", "f", "u", "t", "e", "a", "i" },
				{ "o", "s", "i", "d", "r", "d", "r" },
				{ "e", "e", "l", "e", "b", "m", "s" },
				{ "l", "y", "e", "p", "a", "p", "u" }};
		assertArraysEqual(letters43, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0043.PNG")));
		
		String[][] letters44 = {
				{ "e", "l", "e", "c", "t", "y", "n" },
				{ "f", "n", "n", "t", "n", "v", "o" },
				{ "a", "p", "b", "o", "u", "o", "l" },
				{ "n", "m", "u", "e", "r", "c", "i" },
				{ "o", "n", "i", "e", "t", "i", "r" },
				{ "e", "r", "l", "r", "w", "a", "s" },
				{ "l", "f", "e", "i", "u", "m", "u" }};
		assertArraysEqual(letters44, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0044.PNG")));
		
		String[][] letters45 = {
				{ "e", "l", "e", "c", "t", "y", "n" },
				{ "f", "n", "n", "t", "n", "v", "o" },
				{ "a", "p", "b", "o", "u", "o", "l" },
				{ "n", "m", "u", "e", "r", "c", "i" },
				{ "o", "n", "i", "e", "t", "i", "r" },
				{ "e", "r", "l", "r", "w", "a", "s" },
				{ "l", "f", "e", "i", "u", "m", "u" }};
		assertArraysEqual(letters45, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0045.PNG")));
		
		String[][] letters46 = {
				{ "e", "i", "a", "i", "t", "y", "n" },
				{ "f", "l", "e", "c", "n", "v", "o" },
				{ "a", "n", "n", "t", "u", "o", "l" },
				{ "n", "p", "b", "o", "r", "c", "i" },
				{ "o", "m", "u", "e", "t", "i", "r" },
				{ "e", "n", "i", "e", "w", "a", "s" },
				{ "l", "r", "l", "i", "u", "m", "u" }};
		assertArraysEqual(letters46, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0046.PNG")));
		
		String[][] letters47 = {
				{ "b", "qu", "g", "t", "c", "n", "a" },
				{ "i", "g", "i", "u", "n", "r", "n" },
				{ "r", "e", "j", "l", "u", "o", "s" },
				{ "i", "a", "o", "n", "r", "u", "g" },
				{ "l", "e", "p", "b", "k", "r", "a" },
				{ "t", "g", "e", "e", "i", "o", "l" },
				{ "o", "a", "l", "n", "u", "t", "e" }};
		assertArraysEqual(letters47, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0047.PNG")));
		
		String[][] letters50 = {
				{ "a", "h", "k", "i", "e", "j", "i" },
				{ "h", "d", "n", "a", "g", "f", "o" },
				{ "e", "u", "k", "i", "y", "i", "u" },
				{ "a", "i", "i", "p", "a", "i", "a" },
				{ "r", "j", "j", "n", "k", "o", "i" },
				{ "i", "g", "o", "e", "i", "o", "u" },
				{ "o", "a", "l", "n", "u", "t", "n" }};
		assertArraysEqual(letters50, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0050.PNG")));
		
		String[][] letters51 = {
				{ "n", "g", "f", "n", "w", "e", "r" },
				{ "t", "u", "y", "c", "d", "w", "f" },
				{ "a", "w", "e", "l", "i", "s", "g" },
				{ "g", "t", "r", "a", "i", "m", "n" },
				{ "t", "s", "y", "u", "d", "f", "c" },
				{ "k", "e", "o", "a", "w", "a", "e" },
				{ "u", "h", "i", "g", "o", "m", "s" }};
		assertArraysEqual(letters51, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0051.PNG")));
		
		String[][] letters53 = {
				{ "e", "e", "n", "n", "x", "d", "n" },
				{ "r", "o", "o", "d", "e", "a", "w" },
				{ "u", "e", "r", "i", "l", "p", "u" },
				{ "a", "n", "w", "e", "r", "r", "i" },
				{ "o", "h", "u", "d", "g", "e", "a" },
				{ "a", "n", "e", "n", "n", "r", "n" },
				{ "r", "t", "l", "r", "a", "e", "m" }};
		assertArraysEqual(letters53, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/IMG_0053.PNG")));
		
		// 640x960
		String[][] retina1 = {
				{ "u", "d", "u", "l", "o", "h", "g" },
				{ "g", "a", "r", "w", "c", "i", "e" },
				{ "r", "e", "o", "d", "b", "u", "i" },
				{ "x", "y", "e", "e", "a", "o", "f" },
				{ "u", "h", "t", "v", "g", "v", "u" },
				{ "n", "z", "u", "y", "u", "l", "n" },
				{ "l", "i", "d", "u", "v", "b", "o" }};
		assertArraysEqual(retina1, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/retina1.PNG")));
		
		String[][] retina2 = {
				{ "r", "e", "r", "a", "i", "u", "e" },
				{ "l", "c", "o", "n", "l", "i", "m" },
				{ "a", "i", "g", "a", "a", "v", "i" },
				{ "x", "y", "e", "e", "a", "o", "f" },
				{ "u", "h", "t", "v", "g", "v", "u" },
				{ "n", "z", "u", "y", "u", "l", "n" },
				{ "l", "i", "d", "u", "v", "b", "o" }};
		assertArraysEqual(retina2, solver.letters(
				ClassLoader.class.getResourceAsStream("/bookworm/retina2.PNG")));
	}
	
	public void testSolves() throws Exception {
		Solvable solver = new BookwormSolvable();
		
		// 320x480
		assertFalse(solver.solves(
                new Images().getBufferedImage("/wordsworth/IMG_0009.PNG")));
		assertFalse(solver.solves(
                new Images().getBufferedImage("/bookworm/IMG_0054.PNG")));
		
		assertTrue(solver.solves(
                new Images().getBufferedImage("/bookworm/IMG_0034.PNG")));
		assertTrue(solver.solves(
                new Images().getBufferedImage("/bookworm/IMG_0035.PNG")));
		
		// 640x960
		assertTrue(solver.solves(
                new Images().getBufferedImage("/bookworm/retina1.PNG")));
		assertTrue(solver.solves(
                new Images().getBufferedImage("/bookworm/retina2.PNG")));
		assertTrue(solver.solves(
                new Images().getBufferedImage("/bookworm/retina3.PNG")));
		assertTrue(solver.solves(
                new Images().getBufferedImage("/bookworm/retina4.PNG")));
	}
}
