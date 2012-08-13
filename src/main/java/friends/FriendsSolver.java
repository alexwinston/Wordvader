package friends;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ByteLookupTable;
import java.awt.image.ColorConvertOp;
import java.awt.image.LookupOp;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.core.NeuralNetwork;

import wordvader.Color;
import wordvader.WordvaderCrosswordSolver;

public class FriendsSolver extends WordvaderCrosswordSolver {
	// Board letter and word multipliers
	char[][] multipliers = new char[][] {
			{'_','_','_','#','_','_','3','_','3','_','_','#','_','_','_'},
			{'_','_','2','_','_','@','_','_','_','@','_','_','2','_','_'},
			{'_','2','_','_','2','_','_','_','_','_','2','_','_','2','_'},
			{'#','_','_','3','_','_','_','@','_','_','_','3','_','_','#'},
			{'_','_','2','_','_','_','2','_','2','_','_','_','2','_','_'},
			{'_','@','_','_','_','3','_','_','_','3','_','_','_','@','_'},
			{'3','_','_','_','2','_','_','_','_','_','2','_','_','_','3'},
			{'_','_','_','@','_','_','_','_','_','_','_','@','_','_','_'},
			{'3','_','_','_','2','_','_','_','_','_','2','_','_','_','3'},
			{'_','@','_','_','_','3','_','_','_','3','_','_','_','@','_'},
			{'_','_','2','_','_','_','2','_','2','_','_','_','2','_','_'},
			{'#','_','_','3','_','_','_','@','_','_','_','3','_','_','#'},
			{'_','2','_','_','2','_','_','_','_','_','2','_','_','2','_'},
			{'_','_','2','_','_','@','_','_','_','@','_','_','2','_','_'},
			{'_','_','_','#','_','_','3','_','3','_','_','#','_','_','_'},
	};
	
	// Tile letter points
	Map<Character, Integer> points = new HashMap<Character, Integer>();
	
	public FriendsSolver(String dictionaryName) {
		InputStream nnetRackStream =
			this.getClass().getResourceAsStream("/neuroph/friends-rack.nnet");
		
		super.rackRecognition =
	    	(ImageRecognitionPlugin) NeuralNetwork.load(nnetRackStream).getPlugin(
	    			ImageRecognitionPlugin.IMG_REC_PLUGIN_NAME);
		
		InputStream nnetBoardStream =
			this.getClass().getResourceAsStream("/neuroph/friends-board.nnet");
		
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
		this.points.put('f', 4);
		this.points.put('g', 3);
		this.points.put('h', 3);
		this.points.put('i', 1);
		this.points.put('j', 10);
		this.points.put('k', 5);
		this.points.put('l', 2);
		this.points.put('m', 4);
		this.points.put('n', 2);
		this.points.put('o', 1);
		this.points.put('p', 4);
		this.points.put('q', 10);
		this.points.put('r', 1);
		this.points.put('s', 1);
		this.points.put('t', 1);
		this.points.put('u', 2);
		this.points.put('v', 5);
		this.points.put('w', 4);
		this.points.put('x', 8);
		this.points.put('y', 3);
		this.points.put('z', 10);
	}
	
	public FriendsSolver() {
		this("/enable1.dic");
	}
	
	public char[][] getMultipliers() {
		return this.multipliers;
	}
	
	public int getPoints(char letter) {
		return this.points.get(letter);
	}
	
	public int getBonus(int tileCount) {
		return tileCount == 7 ? 35 : 0;
	}
	
	public int getRackSize() {
		return 7;
	}
	
	public int getRackTileWidth() {
		return 31;
	}
	
	public int getRackTileHeight() {
		return 28;
	}
	
	public int getRackTileXOffset(int rackTileNumber) {
		return (45 * rackTileNumber) + 6;
	}
	
	public int getRackTileYOffset(int rackTileNumber) {
		return 398;
	}
	
	public int getBoardTileWidth() {
		return 16;
	}
	
	public int getBoardTileHeight() {
		return 14;
	}
	
	public double getBoardTileXOffset(int rowNumber) {
		return (21.25 * rowNumber) + 3;
	}
	
	public double getBoardTileYOffset(int columnNumber) {
		return (21.25 * columnNumber) + 69;
	}
	
	public boolean isRackTile(BufferedImage rackTileImage) {
		for (int x = 2; x <= 30; x++) {
			for (int y = 14; y <= 15; y++) {
				if (new Color(rackTileImage.getRGB(x,y)).threshold(new Color(78, 28, 0), 5))
					return true;
			}
		}
		
		return false;
	}
	
	public BufferedImage convertRackTileImage(BufferedImage rackTileImage) {
		ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
    	BufferedImage grayImage = op.filter(rackTileImage, null);
    	
    	BufferedImage blackAndWhiteImage = new BufferedImage(grayImage.getWidth(null), grayImage.getHeight(null), BufferedImage.TYPE_BYTE_BINARY);
    	Graphics2D g = (Graphics2D) blackAndWhiteImage.getGraphics();
    	g.drawImage(grayImage, 0, 0, null);
    	g.dispose();
    	
    	return blackAndWhiteImage;
	}
	
	public boolean isBoardTile(BufferedImage image) {
		// Ignore TW tiles
		for (int x = 2; x <= 14; x++) {
			for (int y = 11; y <= 12; y++) {
				if (new Color(image.getRGB(x,y)).threshold(new Color(78, 28, 0), 5) ||
						new Color(image.getRGB(x,y)).threshold(new Color(250, 250, 250), 5))
					return true;
			}
		}
		
		return false;
	}
	
	public BufferedImage convertBoardTileImage(BufferedImage boardTileImage) {
		BufferedImage grayImage = boardTileImage;
		
		ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        grayImage = op.filter(boardTileImage, null);
    	
        byte[] invert = new byte[256];
		for (int i = 0; i < 256; i++)
			invert[i] = (byte)(255 - i);
		
    	BufferedImage invertedImage = grayImage;
    	if (this.invertBoardTileImage(boardTileImage)) {
    		BufferedImageOp invertOp = new LookupOp(new ByteLookupTable(0, invert), null);
    		invertedImage = invertOp.filter(grayImage, null);
    	}
    	
    	BufferedImage blackAndWhiteImage = invertedImage;
    	blackAndWhiteImage = new BufferedImage(invertedImage.getWidth(null), invertedImage.getHeight(null), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g = (Graphics2D) blackAndWhiteImage.getGraphics();
        g.drawImage(invertedImage, 0, 0, null);
        g.dispose();
        
        return blackAndWhiteImage;
	}
	
	private boolean invertBoardTileImage(BufferedImage boardTileImage) {
		for (int x = 2; x <= 14; x++) {
			for (int y = 5; y <= 6; y++) {
				if (new Color(boardTileImage.getRGB(x,y)).threshold(new Color(250, 250, 250), 5))
					return true;
			}
		}
		
		return false;
	}
}
