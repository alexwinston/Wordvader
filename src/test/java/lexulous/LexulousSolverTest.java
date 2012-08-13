package lexulous;

import java.util.List;

import javax.imageio.ImageIO;

import junit.framework.TestCase;
import wordvader.Solution;
import wordvader.Solvable;
import friends.Anagram;
import friends.Move;

import static wordvader.Asserts.assertArraysEqual;

public class LexulousSolverTest extends TestCase {
	public void testRack() throws Exception {
		LexulousSolver solver = new LexulousSolver();
		
		// iPhone 3G
		assertEquals("miirouaa", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0605.PNG")));
		assertEquals("iouaagne", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0606.PNG")));
		assertEquals("iouanzed", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0607.PNG")));
		assertEquals("iounedoa", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0608.PNG")));
		assertEquals("oneanort", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0609.PNG")));
		assertEquals("onaneeoi", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0610.PNG")));
		assertEquals("aelaaprw", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0611.PNG")));
		assertEquals("aeafehlq", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0612.PNG")));
		assertEquals("aeahqvle", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0613.PNG")));
		assertEquals("aeqlgnav", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0614.PNG")));
		assertEquals("qnvucei", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0615.PNG")));
		assertEquals("nvceoijy", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0616.PNG")));
		assertEquals("jynxstea", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0617.PNG")));
		assertEquals("ynxeyyo", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0618.PNG")));
		assertEquals("yeyykws", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0619.PNG")));
		assertEquals("ywrctbt", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0620.PNG")));
		assertEquals("wrtbtptd", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0621.PNG")));
		assertEquals("wrtbtdir", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0622.PNG")));
		assertEquals("rtbtdfdb", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0623.PNG")));
		assertEquals("rtbtfbsi", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0624.PNG")));
		assertEquals("rbbsmaeu", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0625.PNG")));
		assertEquals("bsehieo", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0626.PNG")));
		assertEquals("ehieo", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0627.PNG")));
		assertEquals("eio", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0628.PNG")));
		assertEquals("i", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0629.PNG")));
		assertEquals("", solver.rack(this.getClass().getResourceAsStream("/lexulous/IMG_0630.PNG")));
		
		// iPhone 4
		assertEquals("qcggnrao", solver.rack(this.getClass().getResourceAsStream("/lexulous/photo1.PNG")));
		assertEquals("qcv", solver.rack(this.getClass().getResourceAsStream("/lexulous/photo2.PNG")));
		assertEquals("uzlfrci", solver.rack(this.getClass().getResourceAsStream("/lexulous/photo3.PNG")));
		assertEquals("czvrssnh", solver.rack(this.getClass().getResourceAsStream("/lexulous/photo4.PNG")));
		assertEquals("glijeyou", solver.rack(this.getClass().getResourceAsStream("/lexulous/photo5.PNG")));
		assertEquals("zoqaainj", solver.rack(this.getClass().getResourceAsStream("/lexulous/photo6.PNG")));
		assertEquals("ddpzdlkv", solver.rack(this.getClass().getResourceAsStream("/lexulous/photo7.PNG")));
		assertEquals("djlpwtxw", solver.rack(this.getClass().getResourceAsStream("/lexulous/photo8.PNG")));
		assertEquals("wmjbdxbl", solver.rack(this.getClass().getResourceAsStream("/lexulous/photo9.PNG")));
		assertEquals("jdvxvmmh", solver.rack(this.getClass().getResourceAsStream("/lexulous/photo10.PNG")));
	}
	
	public void testBoard() throws Exception {
		LexulousSolver solver = new LexulousSolver();
		
		char[][] board605 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board605,
				solver.board(this.getClass().getResourceAsStream("/lexulous/IMG_0605.PNG")));
		
		char[][] board630 = new char[][] {
				{'_','_','_','_','w','_','i','_','_','_','_','_','_','_','_'},
				{'_','_','_','f','r','i','t','t','_','q','u','i','l','l','_'},
				{'_','_','_','_','i','_','_','h','e','_','_','_','_','e','_'},
				{'_','_','p','s','t','_','_','e','_','p','r','e','l','a','w'},
				{'s','_','_','y','_','_','_','_','_','_','_','n','_','f','_'},
				{'e','x','o','n','_','z','a','g','_','_','_','r','_','_','_'},
				{'j','_','_','c','_','_','_','a','u','d','i','o','_','_','_'},
				{'a','_','_','_','_','r','i','m','_','o','_','o','_','_','_'},
				{'n','o','v','i','c','e','_','e','_','_','_','t','i','_','_'},
				{'t','_','_','_','_','_','_','_','_','_','_','_','o','d','_'},
				{'_','_','b','_','_','_','_','_','g','a','l','e','n','a','_'},
				{'_','r','u','m','b','a','_','_','_','_','_','_','o','d','_'},
				{'_','_','s','_','_','s','k','y','e','y','_','_','n','_','_'},
				{'_','_','_','_','_','_','_','_','h','e','a','v','e','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board630,
				solver.board(this.getClass().getResourceAsStream("/lexulous/IMG_0630.PNG")));
		
		char[][] board1 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','z','i','t','_','_'},
				{'_','h','i','m','s','_','_','_','_','_','_','_','a','_','_'},
				{'_','_','_','a','_','_','_','_','_','_','_','_','t','_','_'},
				{'_','_','_','y','_','_','_','_','_','v','i','l','e','_','_'},
				{'_','_','j','a','w','_','_','f','_','_','_','a','_','_','_'},
				{'_','_','a','_','i','_','_','e','_','w','o','r','m','_','_'},
				{'_','_','y','_','n','i','n','e','_','r','_','_','o','_','_'},
				{'_','_','_','_','_','_','_','d','a','y','_','_','l','_','_'},
				{'h','i','d','_','_','_','_','_','x','_','_','_','e','l','f'},
				{'e','_','_','_','_','s','o','l','e','_','_','_','_','_','i'},
				{'n','i','b','_','_','u','_','_','_','_','_','n','_','_','t'},
				{'_','_','o','_','_','c','u','p','_','_','_','e','_','_','_'},
				{'_','_','n','o','o','k','_','e','_','_','_','a','_','_','_'},
				{'_','_','e','r','_','_','_','t','u','b','e','r','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','d','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board1,
				solver.board(this.getClass().getResourceAsStream("/lexulous/photo1.PNG")));
		
		char[][] board2 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','z','i','t','_','_'},
				{'_','h','i','m','s','_','_','_','_','_','_','_','a','g','_'},
				{'_','_','_','a','_','_','_','_','_','_','_','_','t','o','p'},
				{'_','_','_','y','_','_','_','_','_','v','i','l','e','_','a'},
				{'_','_','j','a','w','_','_','f','_','_','_','a','_','_','n'},
				{'s','e','a','_','i','_','_','e','_','w','o','r','m','_','t'},
				{'_','_','y','_','n','i','n','e','_','r','_','_','o','_','_'},
				{'_','_','_','_','_','_','_','d','a','y','_','_','l','_','_'},
				{'h','i','d','_','_','_','_','_','x','_','_','_','e','l','f'},
				{'e','_','_','_','_','s','o','l','e','_','_','_','_','_','i'},
				{'n','i','b','_','_','u','_','_','_','_','_','n','_','_','t'},
				{'_','_','o','_','_','c','u','p','_','_','_','e','_','_','_'},
				{'_','_','n','o','o','k','_','e','_','_','r','a','g','_','_'},
				{'_','_','e','r','_','_','_','t','u','b','e','r','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','d','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board2,
				solver.board(this.getClass().getResourceAsStream("/lexulous/photo2.PNG")));
		
		char[][] board3 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','b','a','d','e','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','a','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','p','a','v','e','d','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','i','_','_','_'},
				{'_','_','_','_','_','_','_','_','f','o','n','t','_','_','_'},
				{'_','_','_','_','_','_','_','_','l','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','m','o','v','e','d','_','_','_'},
				{'_','_','_','_','_','_','j','_','p','_','_','i','_','_','_'},
				{'_','c','_','_','m','o','a','t','s','_','_','e','_','_','_'},
				{'_','o','_','_','u','_','w','_','_','_','_','d','i','n','_'},
				{'_','o','_','_','s','_','s','a','x','_','_','_','_','i','_'},
				{'_','n','e','w','t','_','_','_','_','g','r','u','n','t','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board3,
				solver.board(this.getClass().getResourceAsStream("/lexulous/photo3.PNG")));
		
		char[][] board4 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','b','u','y','_','_','_','_','_'},
				{'_','_','_','_','m','o','v','e','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','x','_','n','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','d','_','_','p','_','_','r','_'},
				{'_','_','_','_','_','w','y','e','_','_','e','_','_','a','_'},
				{'_','_','_','_','_','i','_','r','i','d','e','_','_','t','o'},
				{'_','q','_','f','o','g','_','_','_','_','l','i','t','e','_'},
				{'_','u','_','o','_','_','b','l','i','s','s','_','i','_','_'},
				{'f','a','n','e','_','_','_','_','_','_','_','_','p','a','r'},
				{'_','_','e','_','_','_','_','_','_','_','_','_','_','_','i'},
				{'_','_','a','_','_','h','_','_','_','_','_','_','_','_','m'},
				{'_','_','r','a','k','e','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','y','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board4,
				solver.board(this.getClass().getResourceAsStream("/lexulous/photo4.PNG")));
		
		char[][] board5 = new char[][] {
				{'_','_','_','_','_','_','t','a','n','s','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','o','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','t','r','i','p','_','t','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','r','_','o','n'},
				{'_','_','_','_','_','_','_','g','_','_','h','o','o','k','_'},
				{'_','_','_','_','_','_','_','e','_','_','e','_','_','e','_'},
				{'_','_','_','_','_','q','u','a','_','_','e','_','_','_','_'},
				{'_','_','_','_','_','_','_','r','e','a','l','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','y','_','_','_','_','_','_'},
				{'_','_','_','_','_','w','i','v','e','s','_','_','_','_','_'},
				{'_','_','_','_','_','i','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','s','h','a','w','_','_','_','_','_','_'},
				{'_','_','b','a','r','e','_','_','i','_','_','_','_','_','_'},
				{'_','_','o','_','_','_','_','_','f','l','y','_','_','_','_'},
				{'_','_','x','_','_','_','_','_','e','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board5,
				solver.board(this.getClass().getResourceAsStream("/lexulous/photo5.PNG")));
		
		char[][] board6 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','d','o','a','t','_','_','_','_','_','_','_','_'},
				{'_','_','_','u','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','g','i','p','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','r','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','w','a','f','t','s','_','_','_','c','u','t'},
				{'_','_','_','_','_','y','_','a','_','_','_','_','o','_','_'},
				{'_','_','_','_','_','_','_','r','i','o','t','_','p','o','d'},
				{'_','_','_','_','_','_','_','_','n','_','a','x','e','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','l','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','l','o','v','e','_'},
				{'_','_','_','_','_','_','_','_','m','a','y','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','i','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','c','a','v','e','_','_','_'},
				{'_','_','_','_','_','_','_','_','e','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board6,
				solver.board(this.getClass().getResourceAsStream("/lexulous/photo6.PNG")));
	}
	
	public void testMoves() {
		LexulousSolver solver = new LexulousSolver();
		
		char[][] board = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','g','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','a','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','r','i','m','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','e','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};

		String rack = "iouanzed";
		String[] anagrams = new Anagram().anagram(rack); 
		
		List<Move> solutions = solver.solve(board, rack, anagrams);
		//System.out.println(solutions);
		assertEquals(549, solutions.size());
		assertTrue(new Move(4, 9, "anodize", 71).equalsWithPoints(solutions.get(0)));
		assertTrue(new Move(6, 5, "agonized", 65).equalsWithPoints(solutions.get(1)));
		assertTrue(new Move(5, 2, "zander", 43).equalsWithPoints(solutions.get(2)));
		assertTrue(new Move(5, 2, "zanier", 42).equalsWithPoints(solutions.get(3)));
		assertTrue(new Move(6, 3, "diazine", 40).equalsWithPoints(solutions.get(4)));
		assertTrue(new Move(6, 9, "adze", 39).equalsWithPoints(solutions.get(5)));
		assertTrue(new Move(6, 5, "azido", 39).equalsWithPoints(solutions.get(6)));
		assertTrue(new Move(6, 5, "azide", 39).equalsWithPoints(solutions.get(7)));
	}
	
	public void testSolves() throws Exception {
		Solvable solver = new LexulousSolvable();
		
		// Old
		assertFalse(solver.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/lexulous/old1.PNG"))));
		
		// iPhone 3G
		assertTrue(solver.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/lexulous/IMG_0605.PNG"))));
		
		// iPhone 4
		assertTrue(solver.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/lexulous/photo1.PNG"))));
	}
	
	public void testSolve() throws Exception {
		LexulousSolver solver = new LexulousSolver();
		
		Solution s607 = solver.solve(
				ImageIO.read(this.getClass().getResourceAsStream("/lexulous/IMG_0607.PNG")));
		assertEquals(1, s607.hintGroupSegments.size());
		assertEquals("Points", s607.hintGroupSegments.get(0));
		assertTrue("71 Point Words", s607.hintGroupKeys.get(0).contains("71 Point Words"));
		assertTrue("65 Point Words", s607.hintGroupKeys.get(0).contains("65 Point Words"));
		assertTrue("43 Point Words", s607.hintGroupKeys.get(0).contains("43 Point Words"));
		assertTrue("42 Point Words", s607.hintGroupKeys.get(0).contains("42 Point Words"));
		assertTrue("40 Point Words", s607.hintGroupKeys.get(0).contains("40 Point Words"));
		assertTrue("39 Point Words", s607.hintGroupKeys.get(0).contains("39 Point Words"));
		assertEquals(1, s607.hintGroups.get(0).get("71 Point Words").size());
		assertEquals("anodize", s607.hintGroups.get(0).get("71 Point Words").get(0));
		assertEquals(1, s607.hintGroups.get(0).get("65 Point Words").size());
		assertEquals("agonized", s607.hintGroups.get(0).get("65 Point Words").get(0));
		assertEquals(1, s607.hintGroups.get(0).get("43 Point Words").size());
		assertEquals("zander", s607.hintGroups.get(0).get("43 Point Words").get(0));
		assertEquals(1, s607.hintGroups.get(0).get("42 Point Words").size());
		assertEquals("zanier", s607.hintGroups.get(0).get("42 Point Words").get(0));
		assertEquals(1, s607.hintGroups.get(0).get("40 Point Words").size());
		assertEquals("diazine", s607.hintGroups.get(0).get("40 Point Words").get(0));
		assertEquals(3, s607.hintGroups.get(0).get("39 Point Words").size());
	}
}
