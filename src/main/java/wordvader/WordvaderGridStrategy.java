package wordvader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import wordsworth.WordsWorth5x5Grid;
import wordsworth.WordsWorth6x6Grid;
import wordsworth.WordsWorth7x7Grid;
import wordsworth.WordsWorth7x7GridV3;

public class WordvaderGridStrategy {
	public int rowCount;
	public int columnCount;
	
	public int letterWidth;
	public int letterHeight;
	
	public WordvaderGridStrategy create(BufferedImage image) throws Exception {
		List<WordvaderGridStrategy> strategies = Arrays.asList(
				new WordsWorth5x5Grid(),
                new WordsWorth6x6Grid(),
                new WordsWorth7x7Grid(),
                new WordsWorth7x7GridV3());
		
		for (WordvaderGridStrategy strategy : strategies) {
			if (strategy.handles(image))
				return strategy;
		}
		
		ImageIO.write(image, "png", new File("/tmp/Strategy" + Math.random() + ".png"));
		throw new RuntimeException("WordsWorthGridStrategy not found for BufferedImage");
	}
	
	public boolean handles(BufferedImage image) {
		throw new RuntimeException("MethodNotSupportedException: handles(BufferedImage)");
	}
	
	public int xOffset(int row, int column) {
		throw new RuntimeException("MethodNotSupportedException: xOffset(int, int)");
	}
	
	public int yOffset(int row, int column) {
		throw new RuntimeException("MethodNotSupportedException: xOffset(int, int)");
	}
	
	public boolean invert(BufferedImage image) {
		throw new RuntimeException("MethodNotSupportedException: invert(BufferedImage)");
	}
	
	public boolean threshold(Color value, Color target, int threshold) {
		return (threshold(value.r, target.r, threshold) &&
				threshold(value.g, target.g, threshold) &&
				threshold(value.b, target.b, threshold));
	}
	
	public boolean threshold(int value, int target, int threshold) {
//		System.out.println(target + "," + value + " " + Math.abs(value-target) + " <= " + threshold);
		return (Math.abs(value - target) <= threshold);
	}
}
