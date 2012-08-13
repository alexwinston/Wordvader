package lexulous;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.core.NeuralNetwork;

import wordvader.Color;
import wordvader.WordvaderCrosswordSolver;

public class LexulousSolver extends WordvaderCrosswordSolver {
	// Board letter and word multipliers
	char[][] multipliers = new char[][] {
			{'#','_','_','2','_','_','_','#','_','_','_','2','_','_','#'},
			{'_','_','@','_','_','_','2','_','2','_','_','_','@','_','_'},
			{'_','@','_','_','_','3','_','_','_','3','_','_','_','@','_'},
			{'2','_','_','_','@','_','_','_','_','_','@','_','_','_','2'},
			{'_','_','_','@','_','_','_','_','_','_','_','@','_','_','_'},
			{'_','_','3','_','_','_','2','_','2','_','_','_','3','_','_'},
			{'_','2','_','_','_','2','_','_','_','2','_','_','_','2','_'},
			{'#','_','_','_','_','_','_','_','_','_','_','_','_','_','#'},
			{'_','2','_','_','_','2','_','_','_','2','_','_','_','2','_'},
			{'_','_','3','_','_','_','2','_','2','_','_','_','3','_','_'},
			{'_','_','_','@','_','_','_','_','_','_','_','@','_','_','_'},
			{'2','_','_','_','@','_','_','_','_','_','@','_','_','_','2'},
			{'_','@','_','_','_','3','_','_','_','3','_','_','_','@','_'},
			{'_','_','@','_','_','_','2','_','2','_','_','_','@','_','_'},
			{'#','_','_','2','_','_','_','#','_','_','_','2','_','_','3'},
	};
	
	// Tile letter points
	Map<Character, Integer> points = new HashMap<Character, Integer>();
	
	public LexulousSolver(String dictionaryName) {
		InputStream nnetRackStream =
			this.getClass().getResourceAsStream("/neuroph/lexulous-rack.nnet");
		
		super.rackRecognition =
	    	(ImageRecognitionPlugin) NeuralNetwork.load(nnetRackStream).getPlugin(
	    			ImageRecognitionPlugin.IMG_REC_PLUGIN_NAME);
		
		InputStream nnetBoardStream =
			this.getClass().getResourceAsStream("/neuroph/lexulous-board.nnet");
		
		super.boardRecognition =
	    	(ImageRecognitionPlugin) NeuralNetwork.load(nnetBoardStream).getPlugin(
	    			ImageRecognitionPlugin.IMG_REC_PLUGIN_NAME);
		
		super.dictionary.setupTrie(
				this.getClass().getResourceAsStream(dictionaryName));
		
		this.points.put('a', 1);
		this.points.put('b', 4);
		this.points.put('c', 4);
		this.points.put('d', 2);
		this.points.put('e', 1);
		this.points.put('f', 5);
		this.points.put('g', 2);
		this.points.put('h', 5);
		this.points.put('i', 1);
		this.points.put('j', 8);
		this.points.put('k', 6);
		this.points.put('l', 1);
		this.points.put('m', 4);
		this.points.put('n', 1);
		this.points.put('o', 1);
		this.points.put('p', 4);
		this.points.put('q', 12);
		this.points.put('r', 1);
		this.points.put('s', 1);
		this.points.put('t', 2);
		this.points.put('u', 1);
		this.points.put('v', 5);
		this.points.put('w', 5);
		this.points.put('x', 8);
		this.points.put('y', 5);
		this.points.put('z', 12);
	}
	
	public LexulousSolver() {
		this("/twl06.dic");
	}
	
	public char[][] getMultipliers() {
		return this.multipliers;
	}
	
	public int getPoints(char letter) {
		return this.points.get(letter);
	}
	
	public int getBonus(int tileCount) {
		return tileCount == 7 ? 40 : tileCount == 8 ? 50 : 0;
	}
	
	public int getRackSize() {
		return 8;
	}
	
	public int getRackTileWidth() {
		return 16;
	}
	
	public int getRackTileHeight() {
		return 14;
	}
	
	public int getRackTileXOffset(int rackTileNumber) {
		return (28 * rackTileNumber) + 52;
	}
	
	public int getRackTileYOffset(int rackTileNumber) {
		return 367;
	}
	
	public int getBoardTileWidth() {
		return 12;
	}
	
	public int getBoardTileHeight() {
		return 10;
	}
	
	public double getBoardTileXOffset(int rowNumber) {
		return (20 * rowNumber) + 13;
	}
	
	public double getBoardTileYOffset(int columnNumber) {
		return (20 * columnNumber) + 59;
	}
	
	public boolean isRackTile(BufferedImage rackTileImage) {
		for (int x = 2; x <= 14; x++) {
			for (int y = 7; y <= 8; y++) {
				if (new Color(rackTileImage.getRGB(x,y)).threshold(new Color(25, 25, 25), 25))
					return true;
			}
		}
		
		return false;
	}
	
	public BufferedImage convertRackTileImage(BufferedImage rackTileImage) {
		return rackTileImage;
	}
	
	public boolean isBoardTile(BufferedImage image) {
		return (new Color(image.getRGB(0,0)).threshold(new Color(255, 219, 97), 5) &&
				new Color(image.getRGB(0,0)).threshold(new Color(255, 219, 97), 5));
	}
	
	public BufferedImage convertBoardTileImage(BufferedImage boardTileImage) {
		BufferedImage convertedBoardTileImage = boardTileImage;
		
		ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        convertedBoardTileImage = op.filter(convertedBoardTileImage, null);

        return convertedBoardTileImage;
	}
}
