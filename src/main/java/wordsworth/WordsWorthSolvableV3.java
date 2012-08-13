package wordsworth;

import wordvader.Color;
import wordvader.Images;
import wordvader.Solvable;
import wordvader.Solver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class WordsWorthSolvableV3 implements Solvable {
    public boolean solves(BufferedImage bufferedImage) {
        Images images = new Images();

        try {
            BufferedImage image = images.scale(bufferedImage, 320, 480);

            return (images.threshold(new Color(image.getRGB(0, 0)), new Color(162,0,18), 10) &&
                    images.threshold(new Color(image.getRGB(318, 1)), new Color(210,0,22), 15) &&
                    images.threshold(new Color(image.getRGB(318, 478)), new Color(140,0,8), 10) &&
                    images.threshold(new Color(image.getRGB(1, 478)), new Color(145,2,8), 10));
        } catch (Exception e) {
            e.printStackTrace(); // Ignore
        }

        return false;
    }

    public Solver solver() {
        return new WordsWorthSolver(getClass().getResourceAsStream("/neuroph/wordsworth3.nnet"), 8);
    }
}
