package wordsworth;

import java.awt.image.BufferedImage;

import wordvader.Color;
import wordvader.Images;
import wordvader.Solvable;
import wordvader.Solver;

public class WordsWorthSolvable implements Solvable {
	
	public boolean solves(BufferedImage bufferedImage) {
		Images images = new Images();
		
		try {
			BufferedImage image = images.scale(bufferedImage, 320, 480);
			
			return (images.threshold(new Color(image.getRGB(0, 68)), new Color(230,121,32), 10) &&
					images.threshold(new Color(image.getRGB(0, 409)), new Color(25,18,12), 10) &&
					images.threshold(new Color(image.getRGB(319, 72)), new Color(46,26,20), 10) &&
					images.threshold(new Color(image.getRGB(319, 409)), new Color(27,19,12), 10));
		} catch (Exception e) {
			e.printStackTrace(); // Ignore
		}
		
		return false;
	}
	
	public Solver solver() {
		return new WordsWorthSolver(getClass().getResourceAsStream("/neuroph/wordsworth3.nnet"), 8);
	}
}
