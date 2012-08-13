package wordsworth;

import junit.framework.Assert;
import org.junit.Test;
import wordvader.Images;
import wordvader.WordvaderGridStrategy;

import static wordvader.Asserts.assertArraysEqual;

public class WordsWorth7x7GridV3Test {
    @Test
    public void testHandles() throws Exception {
        WordvaderGridStrategy strategy = new WordsWorth7x7GridV3();

        Assert.assertTrue(strategy.handles(
                new Images().getBufferedImage("/wordsworthV3/7_1.png")));
        Assert.assertTrue(strategy.handles(
                new Images().getBufferedImage("/wordsworthV3/7_2.png")));

        Assert.assertTrue(strategy.handles(
                new Images().getBufferedImage(("/wordsworthV3/x2_7_1.png"))));
        Assert.assertTrue(strategy.handles(
                new Images().getBufferedImage(("/wordsworthV3/x2_7_2.png"))));
        Assert.assertTrue(strategy.handles(
                new Images().getBufferedImage(("/wordsworthV3/x2_7_3.png"))));
    }
    
    @Test
    public void testLetters() throws Exception {
        WordsWorthSolver solver = new WordsWorthSolver();

        String[][] letters1 = {
                { "x", "c", "s", "k", "z", "g", "m" },
                { "f", "l", "p", "r", "c", "g", "m" },
                { "s", "b", "p", "z", "g", "c", "l" },
                { "n", "v", "b", "y", "z", "k", "qu" },
                { "y", "x", "f", "d", "v", "x", "o" },
                { "qu", "e", "j", "h", "j", "o", "r" },
                { "a", "b", "w", "j", "e", "g", "h" }};
        assertArraysEqual(letters1, solver.letters(
                ClassLoader.class.getResourceAsStream("/wordsworthV3/7_1.png")));

        String[][] letters2 = {
                { "e", "a", "y", "g", "l", "a", "t" },
                { "g", "a", "i", "g", "t", "p", "v" },
                { "g", "n", "l", "e", "t", "a", "f" },
                { "s", "w", "y", "t", "c", "e", "y" },
                { "h", "x", "k", "m", "c", "s", "h" },
                { "x", "e", "z", "v", "x", "a", "m" },
                { "e", "m", "y", "qu", "j", "d", "v" }};
        assertArraysEqual(letters2, solver.letters(
                ClassLoader.class.getResourceAsStream("/wordsworthV3/x2_7_1.png")));

        String[][] letters3 = {
                { "i", "l", "s", "u", "s", "g", "t" },
                { "t", "x", "f", "u", "w", "a", "qu" }, // f=s
                { "y", "x", "i", "k", "w", "k", "t" },
                { "t", "g", "j", "k", "w", "r", "a" },
                { "g", "o", "a", "w", "f", "r", "a" },
                { "z", "a", "g", "x", "i", "o", "m" },
                { "e", "r", "y", "m", "c", "c", "g" }};
        assertArraysEqual(letters3, solver.letters(
                ClassLoader.class.getResourceAsStream("/wordsworthV3/x2_7_2.png")));

        String[][] letters4 = {
                { "p", "e", "k", "b", "s", "f", "e" },
                { "e", "y", "o", "d", "e", "r", "qu" }, // e=s
                { "k", "g", "r", "i", "j", "qu", "c" },
                { "m", "e", "s", "c", "t", "d", "h" },
                { "l", "d", "h", "m", "m", "i", "u" },
                { "t", "d", "c", "m", "r", "h", "j" }, // r=k
                { "s", "l", "w", "y", "y", "p", "y" }};
        assertArraysEqual(letters4, solver.letters(
                ClassLoader.class.getResourceAsStream("/wordsworthV3/x2_7_3.png")));

        String[][] letters5 = {
                { "p", "c", "a", "g", "n", "l", "a" },
                { "e", "c", "t", "s", "e", "i", "u" },
                { "e", "i", "i", "o", "o", "o", "k" },
                { "b", "i", "i", "qu", "g", "o", "i" }, // i=0
                { "h", "w", "i", "x", "u", "j", "i" },
                { "v", "s", "v", "o", "u", "w", "v" },
                { "o", "m", "m", "g", "x", "k", "a" }};
        assertArraysEqual(letters5, solver.letters(
                ClassLoader.class.getResourceAsStream("/wordsworthV3/7_2.png")));
    }
}
