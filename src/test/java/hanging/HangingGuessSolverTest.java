package hanging;

import com.google.gson.GsonBuilder;
import org.junit.Test;
import wordvader.Solution;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HangingGuessSolverTest {
	public void testWords() throws Exception {
		HangingGuessSolver solver = new HangingGuessSolver();
		solver.solve(ImageIO.read(
				new FileInputStream("/Users/alexwinston/Desktop/Wordvader/guess2.PNG")));
	}

    @Test
	public void testLetters() throws Exception {
		HangingGuessSolver solver = new HangingGuessSolver();

		// 320x480
		assertEquals("000000e0", solver.letters(
                ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/guess1.png"))));
		assertEquals("h00000e0", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/guess2.png"))));
		assertEquals("hunts0en", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/guess3.png"))));
		assertEquals("0e00", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/guess4.png"))));
		assertEquals("pet0", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/guess5.png"))));
		assertEquals("a0000a00", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/guess6.png"))));
		assertEquals("adrena0s", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/guess7.png"))));
		assertEquals("0000ing", solver.letters(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/guess8.png"))));
	}

    @Test
	public void testGuess() throws Exception {
		Guess g1 = new Guess("h00000e0");
		assertEquals("h", g1.getWord(""));
		assertEquals("ha", g1.getWord("a"));
		assertEquals("hai", g1.getWord("ai"));
		assertEquals("hair", g1.getWord("air"));
		assertEquals("hairi", g1.getWord("airi"));
		assertEquals("hairrie", g1.getWord("airri"));
		assertEquals("hairries", g1.getWord("airris"));

		Guess g2 = new Guess("t0s0i0g");
		assertEquals("t", g2.getWord(""));
		assertEquals("tes", g2.getWord("e"));
		assertEquals("testi", g2.getWord("et"));
		assertEquals("testing", g2.getWord("etn"));

		Guess g3 = new Guess("0000ing");
		assertEquals("t", g3.getWord("t"));
		assertEquals("te", g3.getWord("te"));
		assertEquals("tes", g3.getWord("tes"));
		assertEquals("testing", g3.getWord("test"));

		Guess g4 = new Guess("0000ing0");
		assertEquals("testing", g4.getWord("test"));

		Guess g5 = new Guess("0es0");
		assertEquals("tes", g5.getWord("t"));
		assertEquals("test", g5.getWord("tt"));

		try {
			Guess g6 = new Guess("t000");
			g6.getWord("esting");
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e) {
			// Passed
		}

		Guess g7 = new Guess("0000");
		assertEquals("t", g7.getWord("t"));
		assertEquals("test", g7.getWord("test"));
	}

    @Test
	public void testSolve() throws Exception {
		HangingGuessSolver solver = new HangingGuessSolver();

		assertEquals(
				new HashSet<Play>(Arrays.asList(
						solver.getPlay("pets")
						)),
				solver.plays(new Guess("pet0"), "stu"));

		assertEquals(
				new HashSet<Play>(Arrays.asList(
						solver.getPlay("boom")
						)),
				solver.plays(new Guess("b00m"), "o"));

		assertEquals(
				new HashSet<Play>(Arrays.asList(
						solver.getPlay("rest"),
						solver.getPlay("test")
						)),
				solver.plays(new Guess("0est"), "rstx"));

		assertEquals(
				new HashSet<Play>(Arrays.asList(
						solver.getPlay("wrap"),
						solver.getPlay("para"),
						solver.getPlay("warp"),
						solver.getPlay("papa"),
						solver.getPlay("parr")
						)),
				solver.plays(new Guess("0000"), "pwra"));

		assertEquals(
				new HashSet<Play>(Arrays.asList(
						solver.getPlay("cesti"),
						solver.getPlay("zesty"),
						solver.getPlay("pesto"),
						solver.getPlay("pesty")
						)),
				solver.plays(new Guess("0est0"), "bcdfghijklmnopqruvwxyz"));
		// Suggested Next Letter = p
		// Probability = 50.00%
		// c (25.00%)	i (25.00%)	o (25.00%)	p (50.00%)	y (50.00%)	z (25.00%)

		assertEquals(525, solver.plays(new Guess("0000ing"), "bcdefhjklmopqrstuvwxyz").size());
		BufferedImage guess8 = ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/guess8.png"));
		assertEquals(525, solver.plays(new Guess(solver.letters(guess8)), solver.remainingLetters(guess8)).size());
	}

    @Test
	public void testSolution() throws Exception {
		HangingGuessSolver solver = new HangingGuessSolver();
		
		Solution s1 = solver.solve(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/guess7.png")));
		System.out.println(new GsonBuilder().create().toJson(s1));
		Solution s2 = solver.solve(
				ImageIO.read(ClassLoader.class.getResourceAsStream("/hanging/guess8.png")));
		System.out.println(new GsonBuilder().create().toJson(s2));

		// TODO Ignore letters that have already been played, ie "b"
		Solution s3 = solver.solve(new Guess("b00b"), "o");
		System.out.println(new GsonBuilder().create().toJson(s3));
	}
}
