package wordsworth;
import java.awt.image.BufferedImage;

import wordvader.Color;
import wordvader.WordvaderGridStrategy;


public class WordsWorth5x5Grid extends WordvaderGridStrategy {
	public WordsWorth5x5Grid() {
		super.rowCount = 5;
		super.columnCount = 5;
		
		super.letterWidth = 30;
		super.letterHeight = 24;
	}
	
	public boolean handles(BufferedImage image) {
		return (super.threshold(new Color(image.getRGB(231, 108)), new Color(57,32,24), 10) &&
				super.threshold(new Color(image.getRGB(249, 80)), new Color(66,36,24), 10) &&
				super.threshold(new Color(image.getRGB(280, 81)), new Color(62,36,24), 10) &&
				super.threshold(new Color(image.getRGB(295, 108)), new Color(57,36,24), 10) &&
				super.threshold(new Color(image.getRGB(281, 138)), new Color(49,28,8), 10) &&
				super.threshold(new Color(image.getRGB(249, 138)), new Color(49,32,16), 10));
	}
	
	public int xOffset(int row, int column) {
		return (51 * row) + 46;
	}
	
	public int yOffset(int row, int column) {
		return (58 * column) + (row % 2 == 0 ? 94 : 123); // Rows are staggered
	}
	
	public boolean invert(BufferedImage image) {
		int c = image.getRGB(0, 0);
    	int  red = (c & 0x00ff0000) >> 16;
    	int  green = (c & 0x0000ff00) >> 8;
    	int  blue = c & 0x000000ff;
		//System.out.println(red + " " + green + " " + blue + " ");
    	
		return !(threshold(red, 255, 5) && threshold(green, 210, 5) && threshold(blue, 130, 5));
	}
}
