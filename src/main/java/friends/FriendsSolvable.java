package friends;

import java.awt.image.BufferedImage;

import wordvader.Color;
import wordvader.Images;
import wordvader.Solvable;
import wordvader.Solver;

public class FriendsSolvable implements Solvable {
	
	public boolean solves(BufferedImage bufferedImage) {
		try {
			BufferedImage image = new Images().scale(bufferedImage, 320, 480);
			
			// Look for an empty tile with the correct size in any of the corners
			return ((new Color(image.getRGB(10,68)).threshold(new Color(195, 199, 205), 5) &&
						new Color(image.getRGB(10,82)).threshold(new Color(229, 229, 230), 5)) ||
					(new Color(image.getRGB(308,68)).threshold(new Color(195, 199, 205), 5) &&
						new Color(image.getRGB(308,82)).threshold(new Color(229, 229, 230), 5)) ||
					(new Color(image.getRGB(10,366)).threshold(new Color(195, 199, 205), 5) &&
						new Color(image.getRGB(10,380)).threshold(new Color(229, 229, 230), 5)) ||
					(new Color(image.getRGB(308,366)).threshold(new Color(195, 199, 205), 5) &&
						new Color(image.getRGB(308,380)).threshold(new Color(229, 229, 230), 5)));
		} catch (Exception e) {
			e.printStackTrace(); // Ignore
		}
		
		return false;
	}
	
	public Solver solver() {
		return new FriendsSolver();
	}
}
