package wordvader;

import junit.framework.TestCase;

public class WordvaderTest extends TestCase {
	public void testSolvers() throws Exception {
		Wordvader solver = new Wordvader();
		
		Solution s1 = solver.solve(this.getClass().getResourceAsStream("/bookworm/IMG_0034.PNG"));
		assertTrue(s1.hintGroups.get(0).size() > 0);
		Solution s11 = solver.solve(this.getClass().getResourceAsStream("/bookworm/IMG_0054.PNG"));
		assertEquals(0, s11.hintGroups.size());
		
		Solution s2 = solver.solve(this.getClass().getResourceAsStream("/friends/IMG_0577.PNG"));
		assertTrue(s2.hintGroups.get(0).size() > 0);
		Solution s22 = solver.solve(this.getClass().getResourceAsStream("/friends/IMG_0586.PNG"));
		assertEquals(0, s22.hintGroups.size());
		
		Solution s3 = solver.solve(this.getClass().getResourceAsStream("/lexulous/IMG_0605.PNG"));
		assertTrue(s3.hintGroups.get(0).size() > 0);
		Solution s33 = solver.solve(this.getClass().getResourceAsStream("/lexulous/old1.PNG"));
		assertEquals(0, s33.hintGroups.size());
		
		Solution s4 = solver.solve(this.getClass().getResourceAsStream("/scrabble/IMG_0631.PNG"));
		assertTrue(s4.hintGroups.get(0).size() > 0);
		Solution s44 = solver.solve(this.getClass().getResourceAsStream("/scrabble/old1.PNG"));
		assertEquals(0, s44.hintGroups.size());
		
		Solution s5 = solver.solve(this.getClass().getResourceAsStream("/wordsworth/IMG_0009.PNG"));
		assertTrue(s5.hintGroups.get(0).size() > 0);
		Solution s55 = solver.solve(this.getClass().getResourceAsStream("/wordsworth/IMG_0002.PNG"));
		assertEquals(0, s55.hintGroups.size());
		
		Solution s6 = solver.solve(this.getClass().getResourceAsStream("/hanging/play1.PNG"));
		assertTrue(s6.hintGroups.get(0).size() > 0);

        Solution s7 = solver.solve(this.getClass().getResourceAsStream("/wordsworthV3/7_1.png"));
        assertEquals(0, s7.hintGroups.get(0).size());
        Solution s8 = solver.solve(this.getClass().getResourceAsStream("/wordsworthV3/7_2.png"));
        assertTrue(s8.hintGroups.get(0).size() > 0);
        Solution s9 = solver.solve(this.getClass().getResourceAsStream("/wordsworthV3/x2_7_1.png"));
        assertTrue(s9.hintGroups.get(0).size() > 0);
        Solution s10 = solver.solve(this.getClass().getResourceAsStream("/wordsworthV3/x2_7_2.png"));
        assertTrue(s10.hintGroups.get(0).size() > 0);
	}
}
