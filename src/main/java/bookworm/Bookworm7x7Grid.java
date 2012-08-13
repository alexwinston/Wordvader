package bookworm;

import java.awt.image.BufferedImage;

import wordvader.WordvaderGridStrategy;


public class Bookworm7x7Grid extends WordvaderGridStrategy {
	public Bookworm7x7Grid() {
		super.rowCount = 7;
		super.columnCount = 7;
		
		super.letterWidth = 27;
		super.letterHeight = 19;
	}
	
	public int xOffset(int row, int column) {
		return (45 * row) + 11;
	}
	
	public int yOffset(int row, int column) {
		return (45 * column) + (row % 2 == 0 ? 100 : 122); // Rows are staggered
	}
	
	public boolean invert(BufferedImage image) {
		return false;
	}
}
