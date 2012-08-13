package friends;

import junit.framework.TestCase;

public class FriendsTileTest extends TestCase {
	public void testDummy() {
	}
	
	public void tstCreateTileImages() throws Exception {
		FriendsSolver solver = new FriendsSolver();
		
		solver.board(this.getClass().getResourceAsStream("/friends/IMG_0579.PNG"));
	}
}
