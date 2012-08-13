package scrabble;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.core.NeuralNetwork;

import wordvader.Color;
import wordvader.WordvaderCrosswordSolver;

public class ScrabbleSolver extends WordvaderCrosswordSolver {
	// Board letter and word multipliers
	char[][] multipliers = new char[][] {
			{'#','_','_','2','_','_','_','#','_','_','_','2','_','_','#'},
			{'_','@','_','_','_','3','_','_','_','3','_','_','_','@','_'},
			{'_','_','@','_','_','_','2','_','2','_','_','_','@','_','_'},
			{'2','_','_','@','_','_','_','2','_','_','_','@','_','_','2'},
			{'_','_','_','_','@','_','_','_','_','_','@','_','_','_','_'},
			{'_','3','_','_','_','3','_','_','_','3','_','_','_','3','_'},
			{'_','_','2','_','_','_','2','_','2','_','_','_','2','_','_'},
			{'#','_','_','2','_','_','_','_','_','_','_','2','_','_','#'},
			{'_','_','2','_','_','_','2','_','2','_','_','_','2','_','_'},
			{'_','3','_','_','_','3','_','_','_','3','_','_','_','3','_'},
			{'_','_','_','_','@','_','_','_','_','_','@','_','_','_','_'},
			{'2','_','_','@','_','_','_','2','_','_','_','@','_','_','2'},
			{'_','_','@','_','_','_','2','_','2','_','_','_','@','_','_'},
			{'_','@','_','_','_','3','_','_','_','3','_','_','_','@','_'},
			{'#','_','_','2','_','_','_','#','_','_','_','2','_','_','#'},
	};
	
	// Tile letter points
	Map<Character, Integer> points = new HashMap<Character, Integer>();
	
	public ScrabbleSolver(String dictionaryName) {
		InputStream nnetRackStream =
			this.getClass().getResourceAsStream("/neuroph/scrabble-rack.nnet");
		
		super.rackRecognition =
	    	(ImageRecognitionPlugin) NeuralNetwork.load(nnetRackStream).getPlugin(
	    			ImageRecognitionPlugin.IMG_REC_PLUGIN_NAME);
		
		InputStream nnetBoardStream =
			this.getClass().getResourceAsStream("/neuroph/scrabble-board.nnet");
		
		super.boardRecognition =
	    	(ImageRecognitionPlugin) NeuralNetwork.load(nnetBoardStream).getPlugin(
	    			ImageRecognitionPlugin.IMG_REC_PLUGIN_NAME);
		
		super.dictionary.setupTrie(
				this.getClass().getResourceAsStream(dictionaryName));
		
		this.points.put('a', 1);
		this.points.put('b', 3);
		this.points.put('c', 3);
		this.points.put('d', 2);
		this.points.put('e', 1);
		this.points.put('f', 4);
		this.points.put('g', 2);
		this.points.put('h', 4);
		this.points.put('i', 1);
		this.points.put('j', 8);
		this.points.put('k', 5);
		this.points.put('l', 1);
		this.points.put('m', 3);
		this.points.put('n', 1);
		this.points.put('o', 1);
		this.points.put('p', 3);
		this.points.put('q', 10);
		this.points.put('r', 1);
		this.points.put('s', 1);
		this.points.put('t', 1);
		this.points.put('u', 1);
		this.points.put('v', 4);
		this.points.put('w', 4);
		this.points.put('x', 8);
		this.points.put('y', 4);
		this.points.put('z', 10);
	}
	
	public ScrabbleSolver() {
		this("/twl06.dic");
	}
	
	public char[][] getMultipliers() {
		return this.multipliers;
	}
	
	public int getPoints(char letter) {
		return this.points.get(letter);
	}
	
	public int getBonus(int tileCount) {
		return tileCount == 7 ? 50 : 0;
	}
	
	public int getRackSize() {
		return 7;
	}
	
	public int getRackTileWidth() {
		return 28;
	}
	
	public int getRackTileHeight() {
		return 29;
	}
	
	public int getRackTileXOffset(int rackTileNumber) {
		return (43 * rackTileNumber) + 13;
	}
	
	public int getRackTileYOffset(int rackTileNumber) {
		return 374;
	}
	
	public int getBoardTileWidth() {
		return 18;
	}
	
	public int getBoardTileHeight() {
		return 16;
	}
	
	public double getBoardTileXOffset(int rowNumber) {
		return (21 * rowNumber) + 3;
	}
	
	public double getBoardTileYOffset(int columnNumber) {
		return (21 * columnNumber) + 54;
	}
	
	public boolean isRackTile(BufferedImage rackTileImage) {
		for (int x = 2; x <= 26; x++) {
			for (int y = 13; y <= 14; y++) {
				if (new Color(rackTileImage.getRGB(x,y)).threshold(new Color(51, 51, 51), 5))
					return true;
			}
		}
		
		return false;
	}
	
	public BufferedImage convertRackTileImage(BufferedImage rackTileImage) {
		BufferedImage grayRackTileImage = rackTileImage;
		
		ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		grayRackTileImage = op.filter(grayRackTileImage, null);
        
        BufferedImage blackAndWhiteImage = new BufferedImage(grayRackTileImage.getWidth(null), grayRackTileImage.getHeight(null), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g = (Graphics2D) blackAndWhiteImage.getGraphics();
        g.drawImage(grayRackTileImage, 0, 0, null);
        g.dispose();

        return blackAndWhiteImage;
	}
	
	public boolean isBoardTile(BufferedImage image) {
		for (int i = 10; i < 14; i++)
			if (new Color(image.getRGB(0,i)).threshold(new Color(238, 204, 153), 5))
				return true;
		
		for (int i = 6; i < 10; i++)
			if (new Color(image.getRGB(0,i)).threshold(new Color(255, 255, 160), 10))
				return true;
		
		return false;
	}
	
	public BufferedImage convertBoardTileImage(BufferedImage boardTileImage) {
		BufferedImage grayBoardTileImage = boardTileImage;
		
		ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		grayBoardTileImage = op.filter(grayBoardTileImage, null);
        
        BufferedImage blackAndWhiteImage = new BufferedImage(boardTileImage.getWidth(null), boardTileImage.getHeight(null), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g = (Graphics2D) blackAndWhiteImage.getGraphics();
        g.drawImage(boardTileImage, 0, 0, null);
        g.dispose();

        return blackAndWhiteImage;
	}
}
