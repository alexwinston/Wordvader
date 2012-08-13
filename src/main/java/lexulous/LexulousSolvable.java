package lexulous;

import java.awt.image.BufferedImage;

import wordvader.Color;
import wordvader.Images;
import wordvader.Solvable;
import wordvader.Solver;

public class LexulousSolvable implements Solvable {
	
	public boolean solves(BufferedImage bufferedImage) {
		try {
			BufferedImage image = new Images().scale(bufferedImage, 320, 480);
			
			// Look for an empty tile with the correct size in any of the corners
			return ((new Color(image.getRGB(10,55)).threshold(new Color(230, 15, 15), 10) &&
							new Color(image.getRGB(10,73)).threshold(new Color(220, 15, 15), 10)) ||
					(new Color(image.getRGB(290,55)).threshold(new Color(230, 20, 20), 10) &&
							new Color(image.getRGB(290,73)).threshold(new Color(220, 20, 20), 10)) ||
					(new Color(image.getRGB(10,335)).threshold(new Color(230, 15, 15), 10) &&
							new Color(image.getRGB(10,353)).threshold(new Color(220, 15, 15), 10)) ||
					(new Color(image.getRGB(290,335)).threshold(new Color(230, 15, 15), 10) &&
							new Color(image.getRGB(290,353)).threshold(new Color(220, 20, 20), 10)));
		} catch (Exception e) {
			e.printStackTrace(); // Ignore
		}
		
		return false;
	}
	
	public Solver solver() {
		return new LexulousSolver();
	}
}
