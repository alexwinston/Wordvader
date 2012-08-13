package wordsworth;
import java.awt.image.BufferedImage;

import wordvader.Color;
import wordvader.WordvaderGridStrategy;


public class WordsWorth7x7Grid extends WordvaderGridStrategy {
	public WordsWorth7x7Grid() {
		super.rowCount = 7;
		super.columnCount = 7;
		
		super.letterWidth = 23;
		super.letterHeight = 18;
	}
	
	public boolean handles(BufferedImage image) {
		return (super.threshold(new Color(image.getRGB(250, 97)), new Color(66,36,24), 10) &&
				super.threshold(new Color(image.getRGB(261, 77)), new Color(55,34,15), 10) &&
				super.threshold(new Color(image.getRGB(287, 77)), new Color(60,34,15), 10) &&
				super.threshold(new Color(image.getRGB(299, 98)), new Color(57,32,16), 10) &&
				super.threshold(new Color(image.getRGB(287, 120)), new Color(57,36,24), 10) &&
				super.threshold(new Color(image.getRGB(263, 120)), new Color(62,40,24), 15));
	}
	
	public int xOffset(int row, int column) {
		return (38 * row) + 36;
	}
	
	public int yOffset(int row, int column) {
		return (44 * column) + (row % 2 == 0 ? 89 : 111); // Rows are staggered
	}
	
	public boolean invert(BufferedImage image) {
		Color upperRight = new Color(image.getRGB(image.getWidth() - 1, 0));
		Color lowerRight = new Color(image.getRGB(image.getWidth() - 1, image.getHeight() - 1));
		//System.out.println(red + " " + green + " " + blue + " ");
    	
		return !(threshold(upperRight.r, 255, 5) && threshold(upperRight.g, 207, 5) && threshold(upperRight.b, 132, 10)) &&
				!(threshold(lowerRight.r, 247, 5) && threshold(lowerRight.g, 189, 5) && threshold(lowerRight.b, 97, 10));
	}
}
