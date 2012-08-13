package friends;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import junit.framework.TestCase;
import wordvader.Solution;
import wordvader.Solvable;

import static wordvader.Asserts.assertArraysEqual;

public class FriendsSolverTest extends TestCase {
	public void testRackTiles() throws Exception {
		FriendsSolver solver = new FriendsSolver();
		
		assertEquals("urettir", solver.rack(this.getClass().getResourceAsStream("/friends/IMG_0577.PNG")));
		assertEquals("eadeee", solver.rack(this.getClass().getResourceAsStream("/friends/IMG_0578.PNG")));
		assertEquals("", solver.rack(this.getClass().getResourceAsStream("/friends/IMG_0579.PNG")));
		assertEquals("u", solver.rack(this.getClass().getResourceAsStream("/friends/IMG_0580.PNG")));
		assertEquals("iee", solver.rack(this.getClass().getResourceAsStream("/friends/IMG_0581.PNG")));
		assertEquals("nbhgle", solver.rack(this.getClass().getResourceAsStream("/friends/IMG_0582.PNG")));
		assertEquals("oxcsaoo", solver.rack(this.getClass().getResourceAsStream("/friends/IMG_0583.PNG")));
		assertEquals("turtier", solver.rack(this.getClass().getResourceAsStream("/friends/IMG_0584.PNG")));
		assertEquals("daribe", solver.rack(this.getClass().getResourceAsStream("/friends/IMG_0587a.PNG")));
		assertEquals("ettemda", solver.rack(this.getClass().getResourceAsStream("/friends/IMG_0588.PNG")));
		assertEquals("etmdiwr", solver.rack(this.getClass().getResourceAsStream("/friends/IMG_0589.PNG")));
		assertEquals("rqrhee", solver.rack(this.getClass().getResourceAsStream("/friends/photo2.PNG")));
		assertEquals("tt", solver.rack(this.getClass().getResourceAsStream("/friends/photo3.PNG")));
		assertEquals("nicr", solver.rack(this.getClass().getResourceAsStream("/friends/photo4.PNG")));
		assertEquals("uin", solver.rack(this.getClass().getResourceAsStream("/friends/photo5.PNG")));
		assertEquals("", solver.rack(this.getClass().getResourceAsStream("/friends/photo6.PNG")));
	}
	
	public void testBoardTiles() throws Exception {
		FriendsSolver solver = new FriendsSolver();
		
		BufferedImage[][] boardTileImages = solver.boardTileImages(
				ImageIO.read(this.getClass().getResourceAsStream("/friends/IMG_0581.PNG")));
		assertFalse(solver.isBoardTile(boardTileImages[0][0])); // No tile
		assertTrue(solver.isBoardTile(boardTileImages[0][1]));
		assertTrue(solver.isBoardTile(boardTileImages[0][2]));
		assertTrue(solver.isBoardTile(boardTileImages[0][3]));
		assertTrue(solver.isBoardTile(boardTileImages[0][4]));
		assertTrue(solver.isBoardTile(boardTileImages[0][5]));
		assertTrue(solver.isBoardTile(boardTileImages[0][6]));
		assertFalse(solver.isBoardTile(boardTileImages[0][7])); // No Tile
		assertFalse(solver.isBoardTile(boardTileImages[0][8])); // TL Tile
		assertFalse(solver.isBoardTile(boardTileImages[0][11])); // TW Tile
		assertFalse(solver.isBoardTile(boardTileImages[1][2])); // DL Tile
		assertTrue(solver.isBoardTile(boardTileImages[1][5]));
		assertTrue(solver.isBoardTile(boardTileImages[1][9]));
		assertTrue(solver.isBoardTile(boardTileImages[2][5]));
		assertTrue(solver.isBoardTile(boardTileImages[2][9]));
		assertTrue(solver.isBoardTile(boardTileImages[2][10]));
		assertFalse(solver.isBoardTile(boardTileImages[3][0])); // TW Tile
		assertTrue(solver.isBoardTile(boardTileImages[3][3]));
		assertTrue(solver.isBoardTile(boardTileImages[3][4]));
		assertTrue(solver.isBoardTile(boardTileImages[3][5]));
		assertTrue(solver.isBoardTile(boardTileImages[3][6]));
		assertTrue(solver.isBoardTile(boardTileImages[3][7]));
		assertTrue(solver.isBoardTile(boardTileImages[3][10]));
		assertTrue(solver.isBoardTile(boardTileImages[3][14]));
		assertFalse(solver.isBoardTile(boardTileImages[5][1])); // DW tile
		assertFalse(solver.isBoardTile(boardTileImages[9][1])); // DW tile
		assertFalse(solver.isBoardTile(boardTileImages[11][0])); // TW tile
		assertTrue(solver.isBoardTile(boardTileImages[11][2]));
		assertTrue(solver.isBoardTile(boardTileImages[13][8]));
		assertTrue(solver.isBoardTile(boardTileImages[13][9]));
		assertTrue(solver.isBoardTile(boardTileImages[13][10]));
		assertTrue(solver.isBoardTile(boardTileImages[13][12]));
		assertTrue(solver.isBoardTile(boardTileImages[13][13]));
		assertTrue(solver.isBoardTile(boardTileImages[13][14]));
		assertTrue(solver.isBoardTile(boardTileImages[14][2]));
		assertTrue(solver.isBoardTile(boardTileImages[14][3]));
		assertTrue(solver.isBoardTile(boardTileImages[14][4]));
		assertTrue(solver.isBoardTile(boardTileImages[14][6]));
		assertTrue(solver.isBoardTile(boardTileImages[14][7]));
		assertTrue(solver.isBoardTile(boardTileImages[14][8]));
		assertTrue(solver.isBoardTile(boardTileImages[14][11]));
		assertTrue(solver.isBoardTile(boardTileImages[14][12]));
		assertTrue(solver.isBoardTile(boardTileImages[14][13]));
	}
	
	public void testBoard() throws Exception {
		FriendsSolver solver = new FriendsSolver();
		
		char[][] board577 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','v','o','t','e','r','_','_','_','_','_','_','_'},
				{'_','_','_','_','p','a','_','a','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','t','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','t','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','l','_','_','_','_','_','_','_'},
				{'_','_','_','_','f','a','n','e','_','_','_','_','_','_','_'},
				{'_','_','_','j','a','g','e','r','_','_','_','_','_','_','_'},
				{'_','_','_','o','x','_','_','s','o','a','r','e','d','_','_'},
		};
		//solver.print(
		assertArraysEqual(board577,
                solver.board(this.getClass().getResourceAsStream("/friends/IMG_0577.PNG")));
		
		char[][] board578 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','b','_','_','_','_','_','_','_'},
				{'_','_','_','h','_','_','_','e','_','_','_','_','_','_','_'},
				{'_','_','b','i','_','_','_','s','_','_','_','_','_','_','_'},
				{'_','q','i','s','_','j','_','t','_','z','_','_','_','_','_'},
				{'x','u','_','_','_','i','_','o','_','e','_','_','_','_','_'},
				{'_','e','_','_','_','l','_','w','i','d','t','h','_','_','_'},
				{'_','l','o','c','a','l','e','s','_','s','a','e','_','_','_'},
				{'a','l','_','u','_','_','_','_','_','_','_','_','_','_','_'},
				{'m','_','_','r','_','_','_','_','_','_','_','_','_','_','_'},
				{'p','_','_','v','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','d','e','w','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','d','o','n','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board578,
                solver.board(this.getClass().getResourceAsStream("/friends/IMG_0578.PNG")));
		
		char[][] board579 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','a','v','e','_','_'},
				{'_','_','_','_','_','p','a','l','a','c','e','_','_','_','_'},
				{'_','e','_','_','h','e','_','_','_','_','_','_','_','_','_'},
				{'a','x','_','q','i','s','_','t','_','_','_','_','_','_','_'},
				{'l','_','h','i','m','_','_','a','_','_','_','_','_','_','_'},
				{'o','r','e','_','_','w','_','u','_','_','_','_','_','_','_'},
				{'f','a','r','_','w','h','i','p','_','_','_','_','_','_','j'},
				{'t','i','_','m','o','o','s','e','_','_','_','_','_','_','u'},
				{'_','n','e','o','n','s','_','_','d','_','_','_','_','_','d'},
				{'a','s','_','u','_','e','_','b','i','z','_','_','_','l','o'},
				{'g','_','_','n','_','_','h','e','r','_','_','_','_','a','_'},
				{'e','f','_','d','_','_','_','a','t','_','_','k','i','n','g'},
				{'d','i','g','s','_','_','_','u','_','_','_','i','_','c','_'},
				{'_','n','o','_','b','o','l','t','e','r','_','t','_','e','_'},
				{'_','d','o','r','y','_','a','y','_','e','v','e','r','t','_'},
		};
		//solver.print(
		assertArraysEqual(board579,
                solver.board(this.getClass().getResourceAsStream("/friends/IMG_0579.PNG")));
		
		char[][] board580 = new char[][] {
				{'_','_','_','_','_','_','u','t','_','_','s','u','n','g','_'},
				{'_','_','_','_','_','_','p','i','v','o','t','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','y','e','a','h','_'},
				{'_','_','_','_','_','_','_','q','_','v','e','x','_','a','b'},
				{'_','_','_','_','_','_','r','i','s','e','_','c','_','_','a'},
				{'_','_','_','_','_','h','e','_','_','g','l','i','n','t','s'},
				{'_','_','_','_','_','_','f','e','h','_','_','t','_','i','t'},
				{'_','_','_','_','_','_','_','l','o','o','t','e','r','_','_'},
				{'_','_','_','_','c','r','u','d','_','w','a','d','e','r','_'},
				{'_','_','_','_','_','_','_','_','z','e','d','_','_','a','y'},
				{'_','_','_','_','_','a','_','m','o','d','_','_','o','w','e'},
				{'_','_','_','j','_','b','r','a','n','_','_','l','i','s','p'},
				{'_','_','m','o','_','_','e','m','e','_','d','i','n','_','_'}, // !!! m == n
				{'_','_','_','i','_','f','e','a','s','t','_','_','k','i','r'},
				{'_','_','a','n','g','e','l','s','_','o','h','o','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board580,
                solver.board(this.getClass().getResourceAsStream("/friends/IMG_0580.PNG")));
		
		char[][] board581 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'f','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'r','_','_','_','t','_','_','_','_','_','_','d','_','_','w'},
				{'i','_','_','t','o','_','_','_','_','_','_','e','t','u','i'},
				{'s','_','_','a','l','p','_','_','_','_','f','a','_','_','z'},
				{'k','e','t','t','l','e','_','_','_','n','i','c','e','r','_'},
				{'y','_','_','a','s','p','_','_','_','a','n','o','l','e','s'},
				{'_','_','_','r','_','l','_','m','a','v','e','n','s','_','e'},
				{'_','_','_','_','j','u','r','a','_','v','_','_','_','a','g'},
				{'_','h','a','_','o','m','_','c','o','y','_','_','q','i','_'},
				{'_','_','g','i','b','_','h','e','n','_','_','_','i','d','_'},
				{'_','_','_','_','_','r','i','d','_','_','_','_','_','_','w'},
				{'_','_','_','_','_','o','d','_','_','_','_','_','t','h','o'},
				{'_','_','_','_','_','b','e','a','r','d','s','_','h','o','g'},
				{'_','_','_','q','u','e','s','t','_','_','o','x','e','n','_'},
		};
	
		//solver.print(
		assertArraysEqual(board581,
                solver.board(this.getClass().getResourceAsStream("/friends/IMG_0581.PNG")));
		
		char[][] board582 = new char[][] {
				{'_','_','_','_','_','_','_','_','p','e','a','t','y','_','_'},
				{'_','_','_','_','_','_','_','_','_','l','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','w','_','u','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','e','_','a','_','_','_','_','_'},
				{'_','_','_','_','_','_','h','e','n','t','_','_','_','_','_'},
				{'_','_','_','_','a','p','e','d','_','e','_','_','_','_','_'},
				{'_','_','_','_','_','e','_','e','r','s','_','_','_','_','_'},
				{'_','_','_','w','a','k','e','d','_','_','_','_','_','_','_'},
				{'_','_','m','e','m','e','_','_','_','_','_','_','_','_','_'},
				{'_','f','i','b','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','g','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'v','i','s','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board582,
                solver.board(this.getClass().getResourceAsStream("/friends/IMG_0582.PNG")));
		
		char[][] board4 = new char[][] {
				{'_','_','_','c','h','u','b','_','u','r','e','a','_','_','_'},
				{'_','_','_','a','_','m','o','r','s','e','l','_','_','_','_'},
				{'_','_','_','t','_','_','z','a','_','_','l','a','_','_','_'},
				{'d','o','p','e','_','_','o','d','_','u','_','x','_','_','_'},
				{'_','_','l','_','_','_','_','i','n','n','_','_','q','_','_'},
				{'_','_','y','_','_','_','j','o','e','s','_','_','i','t','_'},
				{'_','_','_','a','e','_','a','_','t','_','_','g','_','w','_'},
				{'_','_','_','g','r','i','m','e','_','h','a','u','t','e','_'},
				{'_','_','l','o','s','t','_','h','a','i','r','y','_','e','f'},
				{'s','p','a','n','_','_','_','_','_','n','e','_','_','d','i'},
				{'o','_','b','e','g','_','_','_','_','_','_','u','h','_','r'},
				{'w','e','_','_','o','_','_','_','_','_','_','k','i','d','s'},
				{'_','v','_','d','o','_','_','_','_','_','v','e','t','_','t'},
				{'_','e','_','i','f','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','d','_','_','_','_','_','_','_','_','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board4,
                solver.board(this.getClass().getResourceAsStream("/friends/photo4.PNG")));
		
		char[][] board5 = new char[][] {
				{'_','_','_','_','_','_','_','_','s','t','e','a','l','e','h'}, // !!! h == r
				{'_','_','_','_','_','_','_','_','_','_','_','l','o','t','_'},
				{'_','_','_','_','_','_','_','_','_','_','q','i','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','f','o','r','d'},
				{'_','_','_','_','_','_','_','_','n','_','_','_','x','u','_'},
				{'_','_','_','_','_','j','o','k','e','_','_','t','i','n','_'},
				{'v','_','_','_','_','o','_','_','a','g','_','i','d','_','_'},
				{'i','_','_','_','_','e','_','b','r','u','i','n','s','_','c'},
				{'a','s','_','_','_','_','w','o','_','m','_','_','_','_','o'},
				{'_','t','_','d','_','p','a','w','_','b','r','a','v','e','r'},
				{'_','e','_','o','_','e','y','e','_','o','_','_','_','_','a'},
				{'f','a','_','z','i','t','_','d','_','s','u','c','h','_','l'},
				{'e','l','_','i','_','_','_','_','_','_','h','e','a','d','s'},
				{'_','t','o','n','g','s','_','r','a','h','_','p','y','e','_'},
				{'_','h','_','g','_','_','_','_','m','e','t','e','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board5,
                solver.board(this.getClass().getResourceAsStream("/friends/photo5.PNG")));
		
		char[][] board6 = new char[][] {
				{'_','_','_','h','_','a','i','_','_','_','_','o','_','_','_'},
				{'r','a','k','i','n','g','_','_','_','t','a','x','i','_','_'},
				{'_','_','_','n','_','_','c','_','_','_','_','e','_','_','_'},
				{'w','_','_','t','h','r','u','s','t','e','r','s','_','l','_'},
				{'i','_','_','_','_','_','b','_','_','_','_','_','b','i','_'},
				{'g','r','i','m','_','p','e','_','y','o','_','h','a','t','_'},
				{'_','_','_','y','e','h','_','_','a','_','d','o','t','_','_'},
				{'_','_','_','_','d','o','v','e','n','_','o','w','_','_','_'},
				{'_','_','_','_','_','n','_','_','g','_','c','l','u','e','_'},
				{'_','_','_','_','_','e','_','i','s','m','s','_','_','f','a'},
				{'_','_','_','r','a','s','h','_','_','_','_','_','_','_','q'},
				{'_','_','_','_','_','_','e','t','_','_','_','_','_','_','u'},
				{'_','j','u','t','e','_','l','o','_','_','_','_','_','_','a'},
				{'_','_','_','_','r','e','p','e','l','_','_','o','v','a','_'},
				{'_','f','i','n','e','d','_','d','o','z','e','d','_','_','_'},
		};
		//solver.print(
		assertArraysEqual(board6,
                solver.board(this.getClass().getResourceAsStream("/friends/photo6.PNG")));
		
		char[][] board3 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','j','o','g'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','k','a'},
				{'_','_','_','_','_','_','_','q','u','a','_','_','l','a','m'},
				{'_','_','_','_','_','_','o','i','_','t','r','u','e','_','_'},
				{'_','_','_','_','r','_','_','_','_','h','a','_','x','i','s'},
				{'r','_','h','a','h','s','_','_','_','e','h','_','_','_','l'},
				{'e','n','_','y','o','u','n','g','_','i','_','f','i','l','e'},
				{'v','o','t','e','_','p','a','i','l','s','_','i','_','_','w'},
				{'e','r','_','_','_','e','y','e','_','t','_','f','o','n','s'},
				{'s','_','_','w','a','r','_','_','z','_','_','e','m','u','_'},
				{'t','_','t','a','_','_','_','c','e','d','e','d','_','b','o'},
				{'_','_','_','v','o','i','d','_','n','o','t','_','_','_','r'},
				{'_','_','b','e','n','d','_','_','_','g','a','p','_','_','_'},
				{'_','_','_','d','_','_','_','_','_','_','_','i','c','e','d'},
		};
		//solver.print(
		assertArraysEqual(board3,
                solver.board(this.getClass().getResourceAsStream("/friends/photo3.PNG")));
	}
	
	public void testSolves() throws Exception {
		Solvable solvable = new FriendsSolvable();
		
		assertTrue(solvable.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/friends/IMG_0577.PNG"))));
		assertTrue(solvable.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/friends/IMG_0578.PNG"))));
		assertFalse(solvable.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/friends/IMG_0584.PNG"))));
		assertFalse(solvable.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/friends/IMG_0585.PNG"))));
		assertFalse(solvable.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/friends/IMG_0586.PNG"))));
		assertTrue(solvable.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/friends/IMG_0596.PNG"))));
		assertTrue(solvable.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/friends/IMG_0601.PNG"))));
		assertTrue(solvable.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/friends/photo2.PNG"))));
		assertTrue(solvable.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/friends/photo3.PNG"))));
		assertTrue(solvable.solves(
				ImageIO.read(this.getClass().getResourceAsStream("/friends/photo5.PNG"))));

		FriendsSolver solver = new FriendsSolver("/sowpods.dic");
		
		Solution s1 = solver.solve(
				ImageIO.read(this.getClass().getResourceAsStream("/friends/IMG_0582.PNG")));
		assertEquals(1, s1.hintGroupSegments.size());
		assertEquals("Points", s1.hintGroupSegments.get(0));
		assertTrue("27 Point Words", s1.hintGroupKeys.get(0).contains("27 Point Words"));
		assertTrue("25 Point Words", s1.hintGroupKeys.get(0).contains("25 Point Words"));
		assertTrue("22 Point Words", s1.hintGroupKeys.get(0).contains("22 Point Words"));
		assertEquals(1, s1.hintGroups.get(0).get("27 Point Words").size());
		assertEquals("beg", s1.hintGroups.get(0).get("27 Point Words").get(0));
		assertEquals(1, s1.hintGroups.get(0).get("25 Point Words").size());
		assertEquals("ben", s1.hintGroups.get(0).get("25 Point Words").get(0));
		assertEquals(3, s1.hintGroups.get(0).get("22 Point Words").size());
		assertEquals("glen", s1.hintGroups.get(0).get("22 Point Words").get(0));
	}
	
	public void testFirstMove() throws Exception {
		FriendsSolver solver = new FriendsSolver("/sowpods.dic");
		
		char[][] board = new char[][] {
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
		String rack = "ienerlg";
		String[] anagrams = new Anagram().anagram(rack); 

		assertTrue(solver.isFirstMove(board));
		List<Move> moves = solver.solve(board, rack, anagrams);
		//System.out.println(moves);
		assertEquals(99, moves.size());
		assertEquals(new Move(1, 7, "reeling", 57), moves.get(0));
		assertEquals(new Move(1, 7, "leering", 57), moves.get(1));
		assertEquals(new Move(2, 7, "lering", 20), moves.get(2));
		assertEquals(new Move(2, 7, "girnel", 20), moves.get(3));
		assertEquals(new Move(2, 7, "linger", 20), moves.get(4));
		assertEquals(new Move(2, 7, "leeing", 20), moves.get(5));
		assertEquals(new Move(2, 7, "lenger", 20), moves.get(6));
		assertEquals(new Move(3, 7, "ingle", 18), moves.get(7));
		
		assertTrue(solver.isFirstMove(
				solver.board(this.getClass().getResourceAsStream("/friends/IMG_0583.PNG"))));
		assertFalse(solver.isFirstMove(
				solver.board(this.getClass().getResourceAsStream("/friends/IMG_0582.PNG"))));
	}
	
	public void testBingo() {
		FriendsSolver solver = new FriendsSolver();
		
		char[][] board = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','s','_','_','_'},
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
		
		String rack = "notepad";
		String[] anagrams = { "notepad" };
		//String[] anagrams = new Anagram().anagram(rack); 
		//System.out.println(Arrays.asList(anagrams));
		
		Solution solution = solver.rank(solver.solve(board, rack, anagrams));
		assertEquals(11, solution.hintGroupKeys.get(0).size());
		//System.out.println(solution.hintGroupKeys);
		assertEquals(1, solution.hintGroups.get(0).get("53 Point Words").size());
		assertEquals("notepads", solution.hintGroups.get(0).get("53 Point Words").get(0));
		assertEquals(1, solution.hintGroups.get(0).get("24 Point Words").size());
		assertEquals("notes", solution.hintGroups.get(0).get("24 Point Words").get(0));
		assertEquals(3, solution.hintGroups.get(0).get("7 Point Words").size());
		assertEquals("notes", solution.hintGroups.get(0).get("7 Point Words").get(0));
		assertEquals("snot", solution.hintGroups.get(0).get("7 Point Words").get(1));
		assertEquals("no", solution.hintGroups.get(0).get("7 Point Words").get(2));
	}
	
	public void testSolve() throws Exception {
		FriendsSolver solver = new FriendsSolver("/sowpods.dic");
		
		char[][] board = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','v','o','t','e','r','_','_','_','_','_','_','_'},
				{'_','_','_','_','p','o','_','a','_','_','_','_','_','_','_'},
				{'t','u','r','r','e','t','_','t','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','t','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','l','_','_','_','_','_','_','_'},
				{'_','_','_','_','f','a','n','e','_','_','_','_','_','_','_'},
				{'_','_','_','j','a','g','e','r','_','_','_','_','_','_','_'},
				{'_','_','_','o','x','_','_','s','o','a','r','e','d','_','_'},
		};

		String rack = "abcdefg";
		String[] anagrams = new Anagram().anagram(rack); 
		
		List<Move> solutions = solver.solve(board, rack, anagrams);
		//System.out.println(solutions);
		assertEquals(480, solutions.size());
		assertTrue(new Move(0, 7, "acted", 51).equalsWithPoints(solutions.get(0)));
		assertTrue(new Move(9, 13, "debag", 41).equalsWithPoints(solutions.get(1)));
		assertTrue(new Move(9, 13, "fade", 37).equalsWithPoints(solutions.get(2)));
		assertTrue(new Move(9, 13, "bade", 37).equalsWithPoints(solutions.get(3)));
		assertTrue(new Move(9, 13, "fab", 35).equalsWithPoints(solutions.get(4)));
		assertTrue(new Move(0, 7, "bated", 33).equalsWithPoints(solutions.get(5)));
		assertTrue(new Move(0, 7, "fated", 33).equalsWithPoints(solutions.get(6)));
		assertTrue(new Move(0, 7, "gated", 30).equalsWithPoints(solutions.get(7)));
		
		char[][] board2 = new char[][] {
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','b','_','_','_','_','_','_','_'},
				{'_','_','_','h','_','_','_','e','_','_','_','_','_','_','_'},
				{'_','_','b','i','_','_','_','s','_','_','_','_','_','_','_'},
				{'_','q','i','s','_','j','_','t','_','z','_','_','_','_','_'},
				{'x','u','_','_','_','i','_','o','_','e','_','_','_','_','_'},
				{'_','e','_','_','_','l','_','w','i','d','t','h','_','_','_'},
				{'_','l','o','c','a','l','e','s','_','s','a','e','_','_','_'},
				{'a','l','_','u','_','_','_','_','_','_','_','_','_','_','_'},
				{'m','_','_','r','_','_','_','_','_','_','_','_','_','_','_'},
				{'p','_','_','v','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','d','e','w','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','d','o','n','_','_','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
		};

		String rack2 = "eadesee";
		String[] anagrams2 = new Anagram().anagram(rack2); 
		
		List<Move> solutions2 = solver.solve(board2, rack2, anagrams2);
		assertEquals(383, solutions2.size());
		assertTrue(new Move(3, 9, "uds", 28).equalsWithPoints(solutions2.get(0)));
		assertTrue(new Move(6, 3, "daes", 27).equalsWithPoints(solutions2.get(1)));
		assertTrue(new Move(6, 3, "dees", 27).equalsWithPoints(solutions2.get(2)));
		assertTrue(new Move(3, 0, "dashis", 27).equalsWithPoints(solutions2.get(3)));
		assertTrue(new Move(6, 3, "dee", 23).equalsWithPoints(solutions2.get(4)));
		assertTrue(new Move(6, 3, "dae", 23).equalsWithPoints(solutions2.get(5)));
		assertTrue(new Move(4, 14, "eased", 21).equalsWithPoints(solutions2.get(6)));
		assertTrue(new Move(12, 4, "dees", 19).equalsWithPoints(solutions2.get(7)));
		
		List<Move> solutions3 =
			solver.solve("nbhgle", this.getClass().getResourceAsStream("/friends/IMG_0582.PNG"));
		assertEquals(94, solutions3.size()); // !!! 98
		assertTrue(new Move(3, 3, "beg", 27).equalsWithPoints(solutions3.get(0)));
		assertTrue(new Move(3, 3, "ben", 25).equalsWithPoints(solutions3.get(1)));
		assertTrue(new Move(3, 2, "glen", 22).equalsWithPoints(solutions3.get(2)));
		assertTrue(new Move(3, 3, "gen", 22).equalsWithPoints(solutions3.get(3)));
		assertTrue(new Move(3, 3, "hen", 22).equalsWithPoints(solutions3.get(4)));
		assertTrue(new Move(3, 2, "leng", 21).equalsWithPoints(solutions3.get(5)));
		assertTrue(new Move(3, 3, "leg", 21).equalsWithPoints(solutions3.get(6)));
		assertTrue(new Move(3, 3, "neg", 21).equalsWithPoints(solutions3.get(7)));
		assertTrue(new Move(3, 3, "eng", 19).equalsWithPoints(solutions3.get(8)));
	}
	
	public void testMoves() {
		FriendsSolver solver = new FriendsSolver();
		
		char[][] board = new char[][] {
				{'_','_','e','x','t','r','a','v','a','g','a','n','_','_','y'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','o','e'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','a'},
				{'_','_','_','_','_','_','_','o','p','_','_','_','f','a','r'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'_','_','e','x','t','r','a','v','a','g','a','n','_','l','y'},
				{'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'},
				{'b','a','_','v','o','t','e','r','_','_','_','_','_','_','_'},
				{'_','_','_','_','p','o','_','a','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','t','_','_','_','_','_','i','_'},
				{'_','_','_','_','_','_','_','t','_','_','_','_','_','_','_'},
				{'_','_','_','_','_','_','_','l','_','_','o','e','_','_','_'},
				{'a','_','e','_','f','a','n','e','_','_','_','_','_','_','_'},
				{'b','_','_','j','a','g','e','r','_','_','_','_','_','_','_'},
				{'_','_','_','o','x','_','_','s','o','a','r','e','d','_','_'},
		};
		solver.print(solver.rotate(board));
		System.out.println();
		
		String rack = "urettir";
		String[] anagrams = { "ttoedef" };
		//String[] anagrams = new Anagram().anagram(rack); 
		//System.out.println(Arrays.asList(anagrams));
		
		Set<Move> moves = solver.moves(board, rack, anagrams);
		Set<Move> movesRotated = solver.moves(solver.rotate(board), rack, anagrams);
		
		System.out.println(moves);
		assertEquals(14, moves.size());
		System.out.println(movesRotated);
		assertEquals(40, movesRotated.size());
		
		Set<Move> solutions = solver.validate(moves, board);
		Set<Move> solutionsRotated = solver.validate(movesRotated, solver.rotate(board));
		
		System.out.println(solutions);
		assertEquals(13, solutions.size());
		System.out.println(solutionsRotated);
		assertEquals(15, solutionsRotated.size());
	}
}