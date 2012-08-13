package wordsworth;

import wordvader.Color;
import wordvader.Images;
import wordvader.WordvaderGridStrategy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class WordsWorth7x7GridV3 extends WordvaderGridStrategy {
    public WordsWorth7x7GridV3() {
        super.rowCount = 7;
        super.columnCount = 7;

        super.letterWidth = 23;
        super.letterHeight = 18;
    }

    public boolean handles(BufferedImage image) {
        BufferedImage scaled = new Images().scale(image, 320, 480);

        return (super.threshold(new Color(scaled.getRGB(250, 97+16)), new Color(70,0,0), 10) &&
                super.threshold(new Color(scaled.getRGB(261, 77+15)), new Color(74,4,0), 10) &&
                super.threshold(new Color(scaled.getRGB(287, 77+15)), new Color(90,8,0), 10) &&
                super.threshold(new Color(scaled.getRGB(299, 98+16)), new Color(74,0,0), 10) &&
                super.threshold(new Color(scaled.getRGB(287, 120+15)), new Color(74,5,0), 10) &&
                super.threshold(new Color(scaled.getRGB(261, 120+15)), new Color(74,5,0), 10));
    }

    public int xOffset(int row, int column) {
        return (38 * row) + 36;
    }

    public int yOffset(int row, int column) {
        return (44 * column) + (row % 2 == 0 ? 89+14 : 111+14); // Rows are staggered
    }

    public boolean invert(BufferedImage image) {
        Color upperRight = new Color(image.getRGB(image.getWidth() - 1, 0));
        Color lowerRight = new Color(image.getRGB(image.getWidth() - 1, image.getHeight() - 1));

        return !(threshold(upperRight.r, 240, 10)
                    && threshold(upperRight.g, 200, 10)
                    && threshold(upperRight.b, 145, 10))
                && !(threshold(lowerRight.r, 231, 10)
                    && threshold(lowerRight.g, 175, 10)
                    && threshold(lowerRight.b, 90, 10));
    }
}
