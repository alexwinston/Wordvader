package scrabble;

import java.awt.image.BufferedImage;

import wordvader.Color;
import wordvader.Images;
import wordvader.Solvable;
import wordvader.Solver;

public class ScrabbleSolvable implements Solvable {
	
	public boolean solves(BufferedImage bufferedImage) {
		try {
			BufferedImage image = new Images().scale(bufferedImage, 320, 480);
			
			// Look for an empty tile with the correct size in any of the corners
			return ((new Color(image.getRGB(3,53)).threshold(new Color(255, 101, 16), 5) &&
							new Color(image.getRGB(3,71)).threshold(new Color(255, 101, 16), 5)) ||
					(new Color(image.getRGB(316,53)).threshold(new Color(255, 101, 16), 5) &&
							new Color(image.getRGB(316,71)).threshold(new Color(255, 101, 16), 5)) ||
					(new Color(image.getRGB(3,347)).threshold(new Color(255, 101, 16), 5) &&
							new Color(image.getRGB(3,366)).threshold(new Color(255, 101, 16), 5)) ||
					(new Color(image.getRGB(316,347)).threshold(new Color(255, 101, 16), 5) &&
							new Color(image.getRGB(316,366)).threshold(new Color(255, 101, 16), 5)));
		} catch (Exception e) {
			e.printStackTrace(); // Ignore
		}
		
		return false;
	}
	
	public Solver solver() {
		return new ScrabbleSolver();
	}
}
