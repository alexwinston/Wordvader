package bookworm;

import java.awt.image.BufferedImage;

import wordvader.Color;
import wordvader.Images;
import wordvader.Solvable;
import wordvader.Solver;

public class BookwormSolvable implements Solvable {
	
	public boolean solves(BufferedImage bufferedImage) {
		Images images = new Images();
		
		try {
			BufferedImage image = images.scale(bufferedImage, 320, 480);
			
			return (images.threshold(new Color(image.getRGB(50, 50)), new Color(47,255,31), 5) && // 4th column brown header
					images.threshold(new Color(image.getRGB(0, 0)), new Color(70,0,0), 10) &&
					images.threshold(new Color(image.getRGB(0, image.getHeight() - 1)), new Color(155,125,85), 10) &&
					images.threshold(new Color(image.getRGB(image.getWidth() - 1, 0)), new Color(80,0,0), 10) &&
					images.threshold(new Color(image.getRGB(image.getWidth() - 1, image.getHeight() - 1)), new Color(148,125,82), 5));
		} catch (Exception e) {
			e.printStackTrace(); // Ignore
		}
		
		return false;
	}
	
	public Solver solver() {
		return new BookwormSolver(getClass().getResourceAsStream("/neuroph/bookworm.nnet"), 8);
	}
}
