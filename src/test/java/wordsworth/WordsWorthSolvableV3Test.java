package wordsworth;

import junit.framework.Assert;
import org.junit.Test;
import wordvader.Images;
import wordvader.Solvable;

import javax.imageio.ImageIO;

public class WordsWorthSolvableV3Test {
    @Test
    public void testSolves() throws Exception {
        Solvable solver = new WordsWorthSolvableV3();

        Assert.assertFalse(solver.solves(
                new Images().getBufferedImage("/wordsworth/IMG_0018.PNG")));
        Assert.assertFalse(solver.solves(
                new Images().getBufferedImage("/wordsworth/Strategy5_1.png")));
        Assert.assertFalse(solver.solves(
                new Images().getBufferedImage("/wordsworth/Strategy5.png")));
        Assert.assertFalse(solver.solves(
                new Images().getBufferedImage("/wordsworth/Strategy320_1.png")));
        Assert.assertFalse(solver.solves(
                new Images().getBufferedImage("/wordsworth/Strategy320_7_1.png")));

        Assert.assertTrue(solver.solves(
                new Images().getBufferedImage("/wordsworthV3/7_1.png")));
        Assert.assertTrue(solver.solves(
                new Images().getBufferedImage("/wordsworthV3/7_2.png")));

        Assert.assertTrue(solver.solves(
                new Images().getBufferedImage("/wordsworthV3/x2_7_1.png")));
        Assert.assertTrue(solver.solves(
                new Images().getBufferedImage("/wordsworthV3/x2_7_2.png")));
        Assert.assertTrue(solver.solves(
                new Images().getBufferedImage("/wordsworthV3/x2_7_3.png")));
    }
}
