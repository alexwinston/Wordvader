package scrabble;

import java.util.List;

import javax.imageio.ImageIO;

import junit.framework.TestCase;
import wordvader.Solution;
import wordvader.Solvable;
import friends.Anagram;
import friends.Move;

import static wordvader.Asserts.assertArraysEqual;

public class ScrabbleSolverTest extends TestCase {
	public void testRack() throws Exception {
		ScrabbleSolver solver = new ScrabbleSolver();
		
		// iPhone 3G
		assertEquals("ecyaoo", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0631.PNG")));
		assertEquals("aanekia", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0632.PNG")));
		assertEquals("avapoe", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0633.PNG")));
		assertEquals("aiaairt", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0634.PNG")));
		assertEquals("oesrwdh", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0635.PNG")));
		assertEquals("iaieoti", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0636.PNG")));
		assertEquals("odbexfi", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0637.PNG")));
		assertEquals("iiilbvr", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0638.PNG")));
		assertEquals("bfiyila", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0639.PNG")));
		assertEquals("iilvmse", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0640.PNG")));
		assertEquals("bfiipih", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0641.PNG")));
		assertEquals("ilvtqet", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0642.PNG")));
		assertEquals("bfiiios", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0643.PNG")));
		assertEquals("iquanfr", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0644.PNG")));
		assertEquals("fiiiidm", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0645.PNG")));
		assertEquals("fdwnclr", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0646.PNG")));
		assertEquals("fdclud", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0647.PNG")));
		assertEquals("iigegzo", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0648.PNG")));
		assertEquals("fddgteu", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0649.PNG")));
		assertEquals("iigeoeo", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0650.PNG")));
		assertEquals("fdgtunl", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0651.PNG")));
		assertEquals("ieaeiut", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0652.PNG")));
		assertEquals("fgnlson", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0653.PNG")));
		assertEquals("ieaeurn", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0654.PNG")));
		assertEquals("fgnlj", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0655.PNG")));
		assertEquals("ieaer", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0656.PNG")));
		assertEquals("fnj", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0657.PNG")));
		assertEquals("i", solver.rack(this.getClass().getResourceAsStream("/scrabble/IMG_0658.PNG")));
	
		// iPhone 4
		assertEquals("ikarxeg", solver.rack(this.getClass().getResourceAsStream("/scrabble/photo1.PNG")));
		assertEquals("ieaaiei", solver.rack(this.getClass().getResourceAsStream("/scrabble/photo2.PNG")));
		assertEquals("qyefnlr", solver.rack(this.getClass().getResourceAsStream("/scrabble/photo3.PNG")));
		assertEquals("ocjeeee", solver.rack(this.getClass().getResourceAsStream("/scrabble/photo4.PNG")));
		assertEquals("plteitl", solver.rack(this.getClass().getResourceAsStream("/scrabble/photo5.PNG")));
		assertEquals("iiuoesd", solver.rack(this.getClass().getResourceAsStream("/scrabble/photo6.PNG")));
		assertEquals("bdprmjt", solver.rack(this.getClass().getResourceAsStream("/scrabble/photo7.PNG")));
		assertEquals("mwrkdmr", solver.rack(this.getClass().getResourceAsStream("/scrabble/photo8.PNG")));
		assertEquals("zbvhdtx", solver.rack(this.getClass().getResourceAsStream("/scrabble/photo9.PNG")));
		assertEquals("mdpbwvp", solver.rack(this.getClass().getResourceAsStream("/scrabble/photo10.PNG")));
	}
	
	public void testBoard() throws Exception {
		ScrabbleSolver solver = new ScrabbleSolver();
		
		char[][] board1 = new char[][] {
				{'a','_','_','_','q','_','_','w','_','_','_','_','_','_','i'},
				{'d','r','o','g','u','e','_','e','_','v','_','o','p','e','n'},
				{'d','_','_','_','i','_','_','n','a','i','f','_','a','_','_'},
				{'_','_','_','_','l','i','n','t','_','a','a','_','r','o','t'},
				{'_','_','h','o','t','_','_','_','_','_','v','_','d','_','_'},
				{'_','_','a','_','_','_','_','_','_','c','a','d','i','_','_'},
				{'_','_','g','_','_','_','_','_','j','_','_','r','e','n','t'},
				{'_','_','_','_','_','_','_','w','e','e','n','y','_','_','u'},
				{'_','_','_','_','_','_','_','_','e','_','_','_','_','_','m'},
				{'_','_','_','_','_','_','_','o','r','b','_','_','_','_','u'},
				{'_','_','_','_','_','_','_','_','_','u','_','b','e','l','l'},
				{'_','_','_','_','_','_','_','_','_','r','_','u','_','_','i'},
				{'_','_','_','_','_','_','_','z','a','p','_','y','e','n','_'},
				{'_','_','_','_','_','_','_','o','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','c','o','s','t','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board1,
				solver.board(this.getClass().getResourceAsStream("/scrabble/photo1.PNG")));
		
		char[][] board2 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','w','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','e','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','a','_','_','_','_'},
				{'_','_','_','_','_','_','q','u','e','e','n','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board2,
				solver.board(this.getClass().getResourceAsStream("/scrabble/photo2.PNG")));
		
		char[][] board3 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','n','_','d','_'},
				{'_','_','_','_','_','_','_','_','v','_','o','o','z','e','_'},
				{'_','_','_','_','_','_','_','r','o','a','r','_','_','m','_'},
				{'_','_','_','_','_','_','_','_','u','_','_','_','_','i','_'},
				{'_','_','_','_','_','_','n','i','c','e','r','_','_','t','_'},
				{'_','_','_','_','b','_','a','_','h','_','i','_','_','_','_'},
				{'_','_','g','r','a','v','y','_','_','_','p','l','o','w','_'},
				{'_','_','a','_','t','_','_','_','_','_','_','_','h','i','_'},
				{'_','_','l','_','_','_','_','_','_','_','_','_','_','n','_'},
				{'p','e','e','n','_','_','_','_','_','_','s','a','i','d','_'},
		};
		//solver.print(
		assertArraysEqual(board3,
				solver.board(this.getClass().getResourceAsStream("/scrabble/photo3.PNG")));
		
		char[][] board4 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','a','u','d','i','l','e','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','m','_','s','_','_'},
				{'_','_','_','_','_','_','_','m','e','d','i','a','l','_','_'},
				{'_','_','_','_','o','i','l','y','_','_','g','_','o','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','r','_','s','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','a','_','h','o','p'},
				{'_','_','_','_','_','_','_','p','a','w','n','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','f','_','t','_','_','_','_'},
				{'_','_','_','_','_','q','u','a','g','_','_','_','_','v','_'},
				{'_','_','_','_','_','_','_','u','h','_','t','o','n','i','c'},
				{'_','_','_','_','_','_','_','k','a','b','a','r','_','t','_'},
				{'_','_','_','_','_','_','_','_','n','_','_','_','_','a','_'},
				{'_','_','_','_','_','_','_','_','s','t','i','r','r','e','d'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board4,
				solver.board(this.getClass().getResourceAsStream("/scrabble/photo4.PNG")));
		
		char[][] board5 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','c','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','s','c','o','u','t','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','v','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','e','t','_','_'},
				{'_','_','_','_','_','_','_','_','_','a','i','r','y','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','p','_','_'},
				{'_','_','_','_','_','_','_','_','_','b','u','t','e','_','_'},
				{'_','_','_','_','_','_','_','w','a','y','s','i','d','e','_'},
				{'_','_','_','_','_','_','_','_','x','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','i','_','_','_','_','r','_'},
				{'_','_','_','_','_','_','_','h','a','u','l','_','_','o','_'},
				{'_','_','_','_','_','_','_','a','l','_','o','o','z','e','_'},
				{'_','_','_','_','_','_','_','i','_','_','g','_','o','_','_'},
				{'_','_','_','_','_','j','o','k','e','s','_','_','n','_','_'},
				{'_','_','_','_','_','_','_','a','_','_','_','_','e','_','_'},
		};
		//solver.print(
		assertArraysEqual(board5,
				solver.board(this.getClass().getResourceAsStream("/scrabble/photo5.PNG")));
		
		char[][] board6 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','f','a','x','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','g','_','_','_'},
				{'_','_','r','i','v','e','n','_','_','_','p','e','_','_','_'},
				{'_','_','_','_','i','_','o','_','_','g','e','n','e','v','a'},
				{'_','_','_','_','l','_','d','i','p','_','a','d','_','_','n'},
				{'_','_','_','_','e','_','_','t','a','n','k','a','s','_','i'},
				{'_','_','_','_','_','_','_','_','w','a','y','_','i','_','s'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','t','_','e'},
				{'_','_','_','_','_','_','_','_','_','_','z','_','u','_','e'},
				{'_','_','_','_','_','_','_','_','_','_','e','_','a','_','d'},
				{'_','_','_','_','_','_','_','_','_','_','r','_','t','_','_'},
				{'_','_','_','_','_','_','_','_','_','s','o','l','e','m','n'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board6,
				solver.board(this.getClass().getResourceAsStream("/scrabble/photo6.PNG")));
		
		char[][] board632 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','c','o','o','e','y','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board632,
				solver.board(this.getClass().getResourceAsStream("/scrabble/IMG_0632.PNG")));
		
		char[][] board633 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','c','o','o','e','y','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','a','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','k','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','e','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','n','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board633,
				solver.board(this.getClass().getResourceAsStream("/scrabble/IMG_0633.PNG")));
		
		char[][] board634 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','c','o','o','e','y','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','a','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','k','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','e','_','_','_','_','_'},
				{'_','_','_','_','_','p','a','v','a','n','e','s','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board634,
				solver.board(this.getClass().getResourceAsStream("/scrabble/IMG_0634.PNG")));
		
		char[][] board635 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','a','_','_','_','_'},
				{'_','_','_','_','_','_','_','c','o','o','e','y','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','a','r','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','k','a','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','e','t','_','_','_','_'},
				{'_','_','_','_','_','p','a','v','a','n','e','s','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board635,
				solver.board(this.getClass().getResourceAsStream("/scrabble/IMG_0635.PNG")));
		
		char[][] board636 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','a','_','_','_','_'},
				{'_','_','_','_','_','_','_','c','o','o','e','y','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','a','r','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','k','a','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','e','t','_','_','_','_'},
				{'_','_','_','_','_','p','a','v','a','n','e','s','_','_','_'},
				{'_','_','s','h','r','e','w','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board636,
				solver.board(this.getClass().getResourceAsStream("/scrabble/IMG_0636.PNG")));
		
		char[][] board658 = new char[][] {
				{'_','_','_','_','c','a','u','l','d','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','i','_','_','_','_','_','_'},
				{'_','_','_','_','h','_','_','_','m','_','r','_','_','_','s'},
				{'_','_','_','v','i','t','t','l','e','_','i','d','_','g','o'},
				{'_','_','_','_','p','_','_','_','_','_','b','e','_','o','n'},
				{'_','_','_','_','s','m','i','l','e','_','_','o','_','o','_'},
				{'_','_','_','_','_','_','_','a','_','_','a','x','e','d','_'},
				{'_','_','_','z','_','f','_','c','o','o','e','y','_','i','_'},
				{'_','q','u','i','n','a','r','y','_','a','r','_','l','e','g'},
				{'f','_','_','g','_','_','_','_','_','k','a','_','_','_','e'},
				{'i','_','_','_','_','_','j','_','w','e','t','_','_','_','a'},
				{'t','o','e','a','_','p','a','v','a','n','e','s','_','_','r'},
				{'_','_','s','h','r','e','w','_','r','_','_','o','_','_','_'},
				{'_','_','_','_','u','_','_','_','n','e','_','b','i','t','_'},
				{'_','_','_','_','n','_','_','_','_','d','u','s','t','_','_'},
		};
		//solver.print(
		assertArraysEqual(board658,
				solver.board(this.getClass().getResourceAsStream("/scrabble/IMG_0658.PNG")));
	}
	
	public void testMoves() throws Exception {
		ScrabbleSolver solver = new ScrabbleSolver();
		
		char[][] board = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','c','o','o','e','y','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','a','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','k','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','e','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','n','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};

		String rack = "avapoe";
		String[] anagrams = new Anagram().anagram(rack); 
		
		List<Move> solutions = solver.solve(board, rack, anagrams);
		//System.out.println(solutions);
		assertEquals(174, solutions.size());
		assertTrue(new Move(11, 3, "peavy", 26).equalsWithPoints(solutions.get(0)));
		assertTrue(new Move(10, 6, "pave", 22).equalsWithPoints(solutions.get(1)));
		assertTrue(new Move(8, 10, "pave", 19).equalsWithPoints(solutions.get(2)));
		assertTrue(new Move(7, 11, "apnoea", 18).equalsWithPoints(solutions.get(3)));
		assertTrue(new Move(7, 10, "veep", 18).equalsWithPoints(solutions.get(4)));
		assertTrue(new Move(8, 10, "veep", 18).equalsWithPoints(solutions.get(5)));
		assertTrue(new Move(10, 4, "pave", 18).equalsWithPoints(solutions.get(6)));
		assertTrue(new Move(7, 11, "apnea", 16).equalsWithPoints(solutions.get(7)));
		assertTrue(new Move(9, 11, "novae", 16).equalsWithPoints(solutions.get(8)));
	}
	
	public void testSolves() throws Exception {
		Solvable solver = new ScrabbleSolvable();
		
		// Old
		assertFalse(solver.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/scrabble/old1.PNG"))));
		
		// iPhone 3G
		assertTrue(solver.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/scrabble/IMG_0658.PNG"))));
		
		// iPhone 4
		assertTrue(solver.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/scrabble/photo1.PNG"))));
		assertTrue(solver.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/scrabble/photo2.PNG"))));
	}
	
	public void testSolve() throws Exception {
		ScrabbleSolver solver = new ScrabbleSolver();
		
		Solution s633 = solver.solve(
				ImageIO.read(this.getClass().getResourceAsStream("/scrabble/IMG_0633.PNG")));
		assertEquals(1, s633.hintGroupSegments.size());
		assertEquals("Points", s633.hintGroupSegments.get(0));
		assertTrue("26 Point Words", s633.hintGroupKeys.get(0).contains("26 Point Words"));
		assertTrue("22 Point Words", s633.hintGroupKeys.get(0).contains("22 Point Words"));
		assertTrue("19 Point Words", s633.hintGroupKeys.get(0).contains("19 Point Words"));
		assertTrue("18 Point Words", s633.hintGroupKeys.get(0).contains("18 Point Words"));
		assertTrue("16 Point Words", s633.hintGroupKeys.get(0).contains("16 Point Words"));
		assertEquals(1, s633.hintGroups.get(0).get("26 Point Words").size());
		assertEquals("peavy", s633.hintGroups.get(0).get("26 Point Words").get(0));
		assertEquals(1, s633.hintGroups.get(0).get("22 Point Words").size());
		assertEquals("pave", s633.hintGroups.get(0).get("22 Point Words").get(0));
		assertEquals(1, s633.hintGroups.get(0).get("19 Point Words").size());
		assertEquals("pave", s633.hintGroups.get(0).get("19 Point Words").get(0));
		assertEquals(4, s633.hintGroups.get(0).get("18 Point Words").size());
		assertEquals("apnoea", s633.hintGroups.get(0).get("18 Point Words").get(0));
		assertEquals("veep", s633.hintGroups.get(0).get("18 Point Words").get(1));
		assertEquals("veep", s633.hintGroups.get(0).get("18 Point Words").get(2));
		assertEquals("pave", s633.hintGroups.get(0).get("18 Point Words").get(3));
		assertEquals(2, s633.hintGroups.get(0).get("16 Point Words").size());
		assertEquals("apnea", s633.hintGroups.get(0).get("16 Point Words").get(0));
		assertEquals("novae", s633.hintGroups.get(0).get("16 Point Words").get(1));
	}
}
