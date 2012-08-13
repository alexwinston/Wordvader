package wordvader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;

import friends.Anagram;
import friends.Move;

public abstract class WordvaderCrosswordSolver implements Solver {
	// Neuroph plugin to generate the accuracies of letter recognition
	protected ImageRecognitionPlugin rackRecognition;
	protected ImageRecognitionPlugin boardRecognition;
	
	// Binary Search Tree implementation for optimum performance
	protected Trie dictionary = new Trie();
	
	// Abstract methods implemented by crossword solvers
	public abstract char[][] getMultipliers();
	public abstract int getPoints(char letter);
	public abstract int getBonus(int tileCount);
	public abstract int getRackSize();
	public abstract int getRackTileWidth();
	public abstract int getRackTileHeight();
	public abstract int getRackTileXOffset(int rackTileNumber);
	public abstract int getRackTileYOffset(int rackTileNumber);
	public abstract int getBoardTileWidth();
	public abstract int getBoardTileHeight();
	public abstract double getBoardTileXOffset(int boardTileNumber);
	public abstract double getBoardTileYOffset(int boardTileNumber);
	public abstract boolean isRackTile(BufferedImage rackTileImage);
	public abstract BufferedImage convertRackTileImage(BufferedImage rackTileImage);
	public abstract boolean isBoardTile(BufferedImage image);
	public abstract BufferedImage convertBoardTileImage(BufferedImage boardTileImage);
	
	public String rack(InputStream imageStream) throws Exception {
		return this.rack(new Images().scale(ImageIO.read(imageStream), 320, 480));
	}
	
	public String rack(BufferedImage image) throws Exception {
		int width = this.getRackTileWidth(); // 31
        int height = this.getRackTileHeight(); // 28
        
        StringBuilder rack = new StringBuilder();
        
        for (int i = 0; i < this.getRackSize(); i++) {
	        	double xOffset = this.getRackTileXOffset(i); //(45 * i) + 6;
	        	double yOffset = this.getRackTileYOffset(i); //398;
	        	BufferedImage rackTileImage = image.getSubimage((int)xOffset, (int)yOffset, width, height);
	        	//ImageIO.write(rackTileImage, "png", new File("/Users/alexwinston/Desktop/test/rack_" + i + ".png"));
	        	
	        	if (this.isRackTile(rackTileImage)) {
	        		rackTileImage = this.convertRackTileImage(rackTileImage);
	        		//ImageIO.write(rackTileImage, "png", new File("/Users/alexwinston/Desktop/test/rack_conv_" + i + ".png"));
	        		
		        	rack.append(this.rackTile(rackTileImage));
	        	}
        }
        
        return rack.toString();
	}
	
	public String rackTile(BufferedImage rackTileImage) throws Exception {
		String tile = "";
	    Double accuracy = 0d;
	    
        HashMap<String, Double> output = this.rackRecognition.recognizeImage(rackTileImage);
        for (String key : output.keySet()) {
        	if (output.get(key) > accuracy) {
        		tile = key;
        		accuracy = output.get(key);
        	}
        }
        
        //ImageIO.write(rackTileImage, "png", new File("/Users/alexwinston/Desktop/test/" + tile + "_" + Math.random() + ".png"));
		
		return tile;
	}
	
	public BufferedImage[][] boardTileImages(BufferedImage image) throws Exception {
		BufferedImage[][] boardTileImages = new BufferedImage[15][15];
        
        int width = this.getBoardTileWidth(); // 16
        int height = this.getBoardTileHeight(); // 14
        
        for (int i = 0; i < 15; i++) {
	        for (int j = 0; j < 15; j++) {
	        	double xOffset = this.getBoardTileXOffset(i); // (21.25 * i) + 3
	        	double yOffset = this.getBoardTileYOffset(j); // (21.25 * j) + 69
	        	BufferedImage boardTileImage = image.getSubimage((int)xOffset, (int)yOffset, width, height);
	        	//ImageIO.write(boardTileImage, "png", new File("/Users/alexwinston/Desktop/test/board_" + i + "_" + j + ".png"));
	        	
	        	boardTileImages[i][j] = boardTileImage;
	        }
        }
        
        return boardTileImages;
	}
	
	public char boardTile(BufferedImage boardTileImage, int x, int y) throws Exception {
		char letter = '_';
	    Double accuracy = 0d;
	    
        HashMap<String, Double> output = this.boardRecognition.recognizeImage(boardTileImage);
        for (String key : output.keySet()) {
        	if (output.get(key) > accuracy) {
        		letter = key.charAt(0);
        		accuracy = output.get(key);
        	}
        }
        
        //ImageIO.write(boardTileImage, "png", new File("/Users/alexwinston/Desktop/test/" + letter + "_" + x + "_" + y + ".png"));
		
		return letter;
	}
	
	public char[][] board(InputStream imageStream) throws Exception {
		return this.board(new Images().scale(ImageIO.read(imageStream), 320, 480));
	}
	
	public char[][] board(BufferedImage image) throws Exception {
		char[][] board = new char[15][15];
		
		BufferedImage[][] boardTileImages = this.boardTileImages(image);
		
		for (int y = 0; y < 15; y++) {
	        for (int x = 0; x < 15; x++) {
	        	board[x][y] = '_';
	        	
	        	BufferedImage boardTileImage = boardTileImages[y][x];
	        	//ImageIO.write(boardTileImage, "png", new File("/Users/alexwinston/Desktop/test/board_" + x + "_" + y + ".png"));
	        	
	        	if (this.isBoardTile(boardTileImage)) {
	        		boardTileImage = this.convertBoardTileImage(boardTileImage);
		        	//ImageIO.write(boardTileImage, "png", new File("/Users/alexwinston/Desktop/test/board_" + x + "_" + y + "_conv.png"));
        		
	        		board[x][y] = this.boardTile(boardTileImage, x, y);
	        	}	        	
	        }
		}
		
		return board;
	}
	
	public Set<Move> rotateMoves(char[][] board, Set<Move> moves) {
		for (Move move: moves) {
			int tmpX = move.x;
			move.x = move.y;
			move.y = tmpX;
		}
		
		return moves;
	}
	public List<Move> solve(char[][] board, String rack) {
		return this.solve(board, rack, new Anagram().anagram(rack));
	}
	
	public List<Move> solve(char[][] board, String rack, String[] anagrams) {
		boolean isFirstMove = this.isFirstMove(board);
		
		Set<Move> moves = this.moves(board, rack, anagrams);
		if (isFirstMove)
			moves = this.firstMoves(board, rack, anagrams);
		
		char[][] boardRotated = this.rotate(board);
		Set<Move> movesRotated = this.moves(boardRotated, rack, anagrams);
		
		List<Move> solutions = new ArrayList<Move>();
		solutions.addAll(this.validate(moves, board, isFirstMove));
		solutions.addAll(this.rotateMoves(board, this.validate(movesRotated, boardRotated, isFirstMove)));
		
		// Sort solutions by points
		Collections.sort(solutions, new Comparator<Move>() {
			public int compare(Move m1, Move m2) {
				return m2.points - m1.points;
			}
		});

		return solutions;
	}
	
	public List<Move> solve(String rack, InputStream imageStream) throws Exception {
		return this.solve(this.board(imageStream), rack, new Anagram().anagram(rack));
	}

	public boolean isFirstMove(char[][] board) {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (board[y][x] != '_')
					return false;
			}
		}
		
		return true;
	}
	
	public Set<Move> firstMoves(char[][] board, String rack, String[] anagrams) {
		Set<String> words = new HashSet<String>();
		
		for (int i = 0; i < anagrams.length; i++) {
			String anagram = anagrams[i];
			for (int j = 0; j < anagram.length(); j++) {
				String word = anagram.substring(j);
				if (this.dictionary.wordInTrie(word))
					words.add(word);
			}
		}

		Set<Move> moves = new HashSet<Move>();
		for (String word : words) {
			moves.add(new Move(8 - word.length(), 7, word));
		}
		return moves;
	}
	
	public Set<Move> moves(char[][] board, String rack, String[] anagrams) {
		// Debugging
		char[][] boardSlotsPlayable = new char[15][15];
		
		Set<Move> solutions = new HashSet<Move>();
		
		long iterations = 0; // 1932408
		
		// Iterate board Y indexes
		for (int y = 0; y < board.length; y++) {
			// Iterate board X indexes
			for (int x = 0; x < board[y].length; x++) {
				// Skip slots that already contain letters unless it is the first letter, there is a letter to the left, or it is the last slot
				if ((board[y][x] != '_' && (x - 1 >= 0 && board[y][x - 1] != '_')) ||
						(board[y][x] == '_' && (x - 1 >= 0 && board[y][x - 1] != '_')) || x == board[y].length - 1) {
					boardSlotsPlayable[y][x]= board[y][x];
					continue;
				}
				
				// The number of slots available without going off the board
				int slotsAvailable = (board[y].length - x);
				
				// Determine if the current slot is playable
				boolean isPlayable = false;
				if (x - 1 > 0 && board[y][x - 1] != '_')
					isPlayable = true;
				for (int i = 0; i < slotsAvailable; i++) {
					iterations++;
					// Look at the current slot
					if (board[y][x + i] != '_') {
						isPlayable = true;
						break;
					}
					// Look at the slot above
					if (y - 1 > 0 && board[y - 1][x + i] != '_') {
						isPlayable = true;
						break;
					}
					// Look at the slot below
					if (y + 1 < board[y].length && board[y + 1][x + i] != '_') {
						isPlayable = true;
						break;
					}
				}
				
				// Continue if the current slot isn't playable
				if (!isPlayable) {
					boardSlotsPlayable[y][x] = board[y][x];
					continue;
				}
				boardSlotsPlayable[y][x] = Character.isLetter(board[y][x]) ? Character.toUpperCase(board[y][x]) : '*';
				
				//System.out.println(x + " " + y);
				
				// Count the number of slots playable from the current index
				int slotsPlayable = 0;
				for (int i = 0, j = 0; i < slotsAvailable && j < rack.length(); i++) {
					iterations++;
					slotsPlayable++;
					if (board[y][x + i] == '_')
						j++;
				}
				// Count any subsequent slots already played
				for (; slotsPlayable < slotsAvailable && board[y][x + slotsPlayable] != '_'; slotsPlayable++) {
					iterations++;
				}
				//System.out.println(slotsPlayable);
				
				// Determine where to split the anagrams across letters already played
				List<StringBuilder> lettersPlayedSplits = new ArrayList<StringBuilder>();
				List<Integer> anagramSplitIndexes = new ArrayList<Integer>();
				
				boolean isAnagramSplitting = false;
				for (int i = 0, j = 0, k = 0; i < slotsAvailable && j < slotsPlayable; i++) {
					iterations++;
					if (board[y][x + i] == '_') {
						j++;
						isAnagramSplitting = false;
					} else {
						if (!isAnagramSplitting) {
							k++;
							lettersPlayedSplits.add(new StringBuilder());
							
							// Restrict the split indexes to the length of the rack
							anagramSplitIndexes.add(j <= rack.length() ? j : rack.length());
						}
						lettersPlayedSplits.get(k - 1).append(board[y][x + i]);
						
						isAnagramSplitting = true;
					}
				}
				//System.out.println(lettersPlayedSplits);
				//System.out.println(anagramSplitIndexes);
				
				// Iterate anagrams
				for (int i = 0; i < anagrams.length; i++) {
					String anagram = anagrams[i];
					
					// Split anagram across indexes
					String[] anagramSplits = new String[anagramSplitIndexes.size() + 1];
					for (int j = 0, beginIndex = 0; j < anagramSplits.length - 1; j++) {
						iterations++;
						int endIndex = anagramSplitIndexes.get(j);
						anagramSplits[j] = anagram.substring(beginIndex, endIndex);
						beginIndex = endIndex;
					}
					anagramSplits[anagramSplits.length - 1] =
						anagram.substring(anagramSplits.length == 1 ? 0 : anagramSplitIndexes.get(anagramSplitIndexes.size() - 1), anagram.length());
					//System.out.println(Arrays.asList(anagramSplits));
					
					// Iterate the anagram splits appending the letters played splits
					StringBuilder lettersPlaying = new StringBuilder();
					for (int j = 0; j < anagramSplits.length; j++) {
						String anagramSplit = anagramSplits[j];
						if (anagramSplit.length() == 0 && dictionary.wordInTrie(lettersPlaying.toString())) {
							solutions.add(new Move(x, y, lettersPlaying.toString()));
							//System.out.println(lettersPlaying.toString());
						}
						
						for (int k = 0; k < anagramSplit.length(); k++) {
							iterations++;
							
							// Break if the length of the play would go off the board
							String lettersPlayingString = lettersPlaying.toString();
							if (lettersPlayingString.length() > slotsPlayable)
								break;
							
							// Ignore words already on the board
							if (!(j == 1 && k == 0 && anagramSplits[0].length() == 0)) { // lettersPlayedSplits.get(0).equals(lettersPlayingString)
								if (dictionary.wordInTrie(lettersPlayingString)) {
									solutions.add(new Move(x, y, lettersPlayingString));
								}
							}
							
							// Break if the letters being played are not the start of a word
							if (!dictionary.stringInTrie(lettersPlayingString))
								break;
							
							lettersPlaying.append(anagramSplit.charAt(k));
						}

						if (j < lettersPlayedSplits.size())
							lettersPlaying.append(lettersPlayedSplits.get(j));
					}
				}
			}
		}
		
		//this.print(boardSlotsPlayable);
		//System.out.println();
		//System.out.println(iterations);
		
		return solutions;
	}
	
	public char[][] rotate(char[][] board)
	{
	    int w = board.length;
	    int h = board[0].length;   
	    char[][] ret = new char[h][w];
	    for (int i = 0; i < h; ++i) {
	        for (int j = 0; j < w; ++j) {
	            ret[h - i - 1][j] = board[j][h - i - 1];
	        }
	    }
	    return ret;
	}
	
	public Set<Move> validate(Set<Move> moves, char[][] board) {
		return this.validate(moves, board, false);
	}
	
	public Set<Move> validate(Set<Move> moves, char[][] board, boolean isFirstMove) {
		Set<Move> invalid = new HashSet<Move>();
		
		for (Move move : moves) {
			if (!this.validate(move, board, this.dictionary, isFirstMove)) {
				invalid.add(move);
			}
		}
		moves.removeAll(invalid);
		
		return moves;
	}
	
	public boolean validate(Move move, char[][] board, Trie dictionary, boolean isFirstMove) {
		// Validate the move is connected and connected letters are valid
		boolean isConnected = false;
		
		// Track whether this move is a bingo 
		int bingoCount = 0;
		
		for (int i = 0; i < move.letters.length(); i++) {
			if (board[move.y][move.x + i] == '_')
				bingoCount++;
			else
				isConnected = true;
			         
			StringBuilder connectedLetters = new StringBuilder();
			int connectedPoints = 0;
			
			connectedLetters.append(move.letters.charAt(i));
			
			// Build vertically connected word at the current index and add points
			for (int j = move.y - 1; j >= 0 && board[j][move.x + i] != '_'; j--) {
				char connectedLetter = board[j][move.x + i];
				connectedLetters.insert(0, connectedLetter);
				connectedPoints += this.getPoints(connectedLetter);
			}
			for (int j = move.y + 1; j < board.length && board[j][move.x + i] != '_'; j++) {
				char connectedLetter = board[j][move.x + i];
				connectedLetters.append(connectedLetter);
				connectedPoints += this.getPoints(connectedLetter);
			}
			
			if (connectedLetters.length() > 1) {
				isConnected = true;
				if (!dictionary.wordInTrie(connectedLetters.toString()))
					return false;
				
				// Add points for the connected letters if the current slot is playable
				char currentLetter = board[move.y][move.x + i];
				if (currentLetter == '_') {
					char multiplier = this.getMultipliers()[move.y][move.x + i];
					switch (multiplier) {
						case '2':
							connectedPoints += this.getPoints(move.letters.charAt(i)) * 2;
							break;
						case '3':
							connectedPoints += this.getPoints(move.letters.charAt(i)) * 3;
							break;
						case '@':
							connectedPoints += this.getPoints(move.letters.charAt(i));
							connectedPoints *= 2;
							break;
						case '#':
							connectedPoints += this.getPoints(move.letters.charAt(i));
							connectedPoints *= 3;
							break;
						default:
							connectedPoints += this.getPoints(move.letters.charAt(i));
							break;
					}
					
					move.points += connectedPoints;
				}				
			}
		}
		
		// TODO Refactor into code above
		if (isConnected || isFirstMove) {
			// Add points for the current move letters
			int connectedPoints = 0;
			List<Integer> connectedMultpliers = new ArrayList<Integer>();
			
			for (int i = 0; i < move.letters.length(); i++) {
				char currentLetter = move.letters.charAt(i);
				if (board[move.y][move.x + i] != '_')
					connectedPoints += this.getPoints(currentLetter);
				else {
					char multiplier = this.getMultipliers()[move.y][move.x + i];
					switch (multiplier) {
						case '2':
							connectedPoints += this.getPoints(currentLetter) * 2;
							break;
						case '3':
							connectedPoints += this.getPoints(currentLetter) * 3;
							break;
						case '@':
							connectedPoints += this.getPoints(currentLetter);
							connectedMultpliers.add(2);
							break;
						case '#':
							connectedPoints += this.getPoints(currentLetter);
							connectedMultpliers.add(3);
							break;
						default:
							connectedPoints += this.getPoints(currentLetter);
							break;
					}
				}
			}
			for (int i : connectedMultpliers)
				connectedPoints *= i;
			move.points += connectedPoints;
			
			// Add the bingo bonus
			move.points += this.getBonus(bingoCount); // 35
			
			return true;
		}
		return false;
	}
	
	public void print(char[][] board) {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				System.out.print((x == 0 ? "{" : ",") + "'" + board[y][x] + "'");
				if (x == board[y].length - 1)
					System.out.println("},");
			}
		}
	}

	public Solution solve(BufferedImage bufferedImage) {
		List<Move> moves = new ArrayList<Move>();
		
		try {
			BufferedImage image = new Images().scale(bufferedImage, 320, 480);
			
			// Get the moves for the rack and board
			moves = this.solve(this.board(image), this.rack(image));
			//System.out.println(moves);
			//System.out.println(moves.size());
		} catch (Exception e) {
			e.printStackTrace(); // Ignore
		}
		
		return this.rank(moves);
	}
	
	public Solution rank(List<Move> moves) {
		Solution solution = new Solution();

		// Rank words by letter totals
		solution.hintGroupSegments.add("Points");
		
		HashMap<String, List<String>> letterRankings = new HashMap<String, List<String>>();
		for (Move move : moves) {
			String points = move.points + " Point Words";
			if (!letterRankings.containsKey(points))
				letterRankings.put(points, new ArrayList<String>());
			
			letterRankings.get(points).add(move.letters);
		}
		
		List<String> letterRankingKeys = new ArrayList<String>(new TreeSet<String>(letterRankings.keySet()));
		Collections.sort(letterRankingKeys, new AlphanumComparator());
		Collections.reverse(letterRankingKeys);
		
		solution.hintGroupKeys.add(letterRankingKeys);
		solution.hintGroups.add(letterRankings);

		return solution;
	}
}
