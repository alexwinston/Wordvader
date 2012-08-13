package wordsworth;
import java.awt.image.BufferedImage;

import wordvader.Color;
import wordvader.WordvaderGridStrategy;


public class WordsWorth6x6Grid extends WordvaderGridStrategy {
	public WordsWorth6x6Grid() {
		super.rowCount = 6;
		super.columnCount = 6;
		
		super.letterWidth = 25;
		super.letterHeight = 20;
	}
	
	public boolean handles(BufferedImage image) {
		return (super.threshold(new Color(image.getRGB(250, 124)), new Color(57,32,16), 10) &&
				super.threshold(new Color(image.getRGB(264, 97)), new Color(66,40,24), 10) &&
				super.threshold(new Color(image.getRGB(295, 97)), new Color(57,36,24), 10) &&
				super.threshold(new Color(image.getRGB(310, 125)), new Color(49,32,16), 10) &&
				super.threshold(new Color(image.getRGB(297, 150)), new Color(49,32,16), 10) &&
				super.threshold(new Color(image.getRGB(264, 149)), new Color(67,41,26), 10));
	}
	
	public int xOffset(int row, int column) {
		return (46 * row) + 39;
	}
	
	public int yOffset(int row, int column) {
		return (52 * column) + (row % 2 == 0 ? 87 : 113); // Rows are staggered
	}
	
	public boolean invert(BufferedImage image) {
    	int c = image.getRGB(0, image.getHeight() - 1);
    	int  red = (c & 0x00ff0000) >> 16;
    	int  green = (c & 0x0000ff00) >> 8;
    	int  blue = c & 0x000000ff;
//    	System.out.println(red + " " + green + " " + blue);
    	
		return !(threshold(red, 247, 10) && threshold(green, 186, 10) && threshold(blue, 90, 10));
	}
}
