package hanging;

import java.awt.image.BufferedImage;

import wordvader.Color;
import wordvader.Images;
import wordvader.Solvable;
import wordvader.Solver;

public class HangingPlaySolvable implements Solvable {
	public boolean solves(BufferedImage bufferedImage) {
		try {
			BufferedImage image = new Images().scale(bufferedImage, 320, 480);
			
			// Look for an empty tile with the correct size in any of the corners
			return (new Color(image.getRGB(2,394)).threshold(new Color(87, 13, 15), 10) &&
					new Color(image.getRGB(318,394)).threshold(new Color(87, 13, 16), 10) &&
					new Color(image.getRGB(318,478)).threshold(new Color(88, 14, 16), 10) &&
					new Color(image.getRGB(2,478)).threshold(new Color(105, 17, 20), 10));
		} catch (Exception e) {
			e.printStackTrace(); // Ignore
		}
		
		return false;
	}
	
	public Solver solver() {
		return new HangingPlaySolver();
	}
}
