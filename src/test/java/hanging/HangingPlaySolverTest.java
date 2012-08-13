package hanging;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashSet;

import javax.imageio.ImageIO;

import junit.framework.TestCase;
import wordvader.Solution;

import com.google.gson.GsonBuilder;

public class HangingPlaySolverTest extends TestCase {
	
	public void tstSolve() throws Exception {
		HangingPlaySolver solver = new HangingPlaySolver();
		Solution s1 = solver.solve(ImageIO.read(
				new FileInputStream("/Users/alexwinston/Desktop/Wordvader/play5.PNG")));
		System.out.println(new GsonBuilder().create().toJson(s1));
	}
	
	public void testLetters() throws Exception {
		HangingPlaySolver solver = new HangingPlaySolver();
		
		// 320x480
		assertEquals("umtnechesfun", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play1.png"))));
		assertEquals("izsttetutsep", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play2.png"))));
		assertEquals("yanleaarmdsr", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play3.png"))));
		assertEquals("bfseotqetedj", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play4.png"))));
		assertEquals("fuuqtevtgicy", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play5.png"))));
		assertEquals("gmitdyhuvawa", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play6.png"))));
		assertEquals("aiixqndozmyl", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play7.png"))));
		assertEquals("ivncelkyldiu", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play8.png"))));
		
		// 640x960
		assertEquals("stdeemquhhiv", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play9.png"))));
	}
	
	public void testWords() throws Exception {
		HangingPlaySolver solver = new HangingPlaySolver();
		
		assertEquals(205, solver.permutate("umtnechesfun").size());
		assertEquals(127, solver.permutate("izsttetutsep").size());
		assertEquals(506, solver.permutate("yanleaarmdsr").size()); // 502
		assertEquals(98, solver.permutate("bfseotqetedj").size());
		assertEquals(36, solver.permutate("fuuqtevtgicy").size());
		assertEquals(95, solver.permutate("gmitdyhuvawa").size());
		assertEquals(158, solver.permutate("aiixqndozmyl").size());
		assertEquals(220, solver.permutate("ivncelkyldiu").size()); // 219
		assertEquals(227, solver.permutate("stdeemquhhiv").size());
		
		assertEquals(
				new HashSet<Play>(Arrays.asList(
						solver.getPlay("brat")
						)),
				solver.permutate("bart"));
		assertEquals(
				new HashSet<Play>(Arrays.asList(
						solver.getPlay("abbacy"),
						solver.getPlay("bacca"),
						solver.getPlay("cabby"),
						solver.getPlay("abba"),
						solver.getPlay("baba"),
						solver.getPlay("baby"),
						solver.getPlay("caca")
						)),
				solver.permutate("yyyaabbcc"));
		assertEquals(
				new HashSet<Play>(Arrays.asList(
						solver.getPlay("testy"),
						solver.getPlay("yetts"),
						solver.getPlay("sett"),
						solver.getPlay("stet"),
						solver.getPlay("stey"),
						solver.getPlay("stye"),
						solver.getPlay("test"),
						solver.getPlay("tets"),
						solver.getPlay("tyes"),
						solver.getPlay("yett")
						)),
				solver.permutate("testy"));
	}
	
	public void testSolution() throws Exception {
		HangingPlaySolver solver = new HangingPlaySolver();
		
		Solution s1 = solver.solve(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play5.png")));
		assertEquals(2, s1.hintGroupSegments.size());
		assertEquals("Points", s1.hintGroupSegments.get(0));
		assertEquals("Length", s1.hintGroupSegments.get(1));
		
		assertEquals(2, s1.hintGroupKeys.size());
		assertEquals(12, s1.hintGroupKeys.get(0).size());
		assertEquals("18 Point Words", s1.hintGroupKeys.get(0).get(0));
		assertEquals("16 Point Words", s1.hintGroupKeys.get(0).get(1));
		assertEquals("15 Point Words", s1.hintGroupKeys.get(0).get(2));
		assertEquals("14 Point Words", s1.hintGroupKeys.get(0).get(3));
		assertEquals("12 Point Words", s1.hintGroupKeys.get(0).get(4));
		assertEquals("11 Point Words", s1.hintGroupKeys.get(0).get(5));
		assertEquals("10 Point Words", s1.hintGroupKeys.get(0).get(6));
		assertEquals("9 Point Words", s1.hintGroupKeys.get(0).get(7));
		assertEquals("8 Point Words", s1.hintGroupKeys.get(0).get(8));
		assertEquals("7 Point Words", s1.hintGroupKeys.get(0).get(9));
		assertEquals("6 Point Words", s1.hintGroupKeys.get(0).get(10));
		assertEquals("5 Point Words", s1.hintGroupKeys.get(0).get(11));
		
		assertEquals(2, s1.hintGroups.get(0).get("18 Point Words").size());
		assertTrue(s1.hintGroups.get(0).get("18 Point Words").contains("fique"));
		assertTrue(s1.hintGroups.get(0).get("18 Point Words").contains("equity"));
		assertEquals(2, s1.hintGroups.get(0).get("16 Point Words").size());
		assertTrue(s1.hintGroups.get(0).get("16 Point Words").contains("quey"));
		assertTrue(s1.hintGroups.get(0).get("16 Point Words").contains("tuque"));
		assertEquals(3, s1.hintGroups.get(0).get("6 Point Words").size());
		assertTrue(s1.hintGroups.get(0).get("6 Point Words").contains("yett"));
		assertTrue(s1.hintGroups.get(0).get("6 Point Words").contains("yeti"));
		assertTrue(s1.hintGroups.get(0).get("6 Point Words").contains("tutu"));
		assertEquals(1, s1.hintGroups.get(0).get("5 Point Words").size());
		assertTrue(s1.hintGroups.get(0).get("5 Point Words").contains("etui"));
		
		Solution s2 = solver.solve(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play9.png")));
		assertEquals(1, s2.hintGroups.get(0).get("21 Point Words").size());
		assertTrue(s2.hintGroups.get(0).get("21 Point Words").contains("mesquite"));
		assertEquals(1, s2.hintGroups.get(1).get("8 Letter Words").size());
		assertTrue(s2.hintGroups.get(1).get("8 Letter Words").contains("mesquite"));
	}
	
	public void testSolves() throws Exception {
		HangingPlaySolvable solvable = new HangingPlaySolvable();
		
		assertTrue(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play1.png"))));
		assertTrue(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play2.png"))));
		assertTrue(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play3.png"))));
		assertTrue(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play4.png"))));
		assertTrue(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play5.png"))));
		assertTrue(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play6.png"))));
		assertTrue(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play7.png"))));
		assertTrue(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play8.png"))));
		assertTrue(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/play9.png"))));
		
		assertFalse(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/bookworm/IMG_0034.png"))));
		assertFalse(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/friends/IMG_0577.png"))));
		assertFalse(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/lexulous/IMG_0605.png"))));
		assertFalse(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/bookworm/IMG_0034.png"))));
		assertFalse(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/scrabble/IMG_0631.png"))));
		assertFalse(solvable.solves(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/wordsworth/IMG_0009.png"))));
		
	}
}
