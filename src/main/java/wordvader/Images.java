package wordvader;

import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class Images {
    public BufferedImage getBufferedImage(String name) {
        try {
            return ImageIO.read(ClassLoader.class.getResourceAsStream(name));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
	public BufferedImage scale(BufferedImage image, int width, int height) {
		// Scale the image if it isn't 320x480
		if (image.getWidth() != width && image.getHeight() != height) {
			GraphicsConfiguration gc = image.createGraphics().getDeviceConfiguration();
			BufferedImage out = gc.createCompatibleImage(width, height, Transparency.BITMASK);
			
			Graphics2D g2d = out.createGraphics();
			g2d.setComposite(AlphaComposite.Src);
			g2d.drawImage(image, 0, 0, width, height, null);
			g2d.dispose();
			
			image = out;
		}
		
		// Throw an invalid argument exception if they size of the input image is not 320x480
		if (image.getWidth() != width && image.getHeight() != height)
			throw new IllegalArgumentException("Image must have a width and height of 320x480");
		
		return image;
	}
	
	public boolean threshold(Color value, Color target, int threshold) {
		return (threshold(value.r, target.r, threshold) &&
				threshold(value.g, target.g, threshold) &&
				threshold(value.b, target.b, threshold));
	}
	
	public boolean threshold(int value, int target, int threshold) {
//		System.out.println(target + " - " + value + " <= " + threshold);
		return (Math.abs(value - target) <= threshold);
	}
}
