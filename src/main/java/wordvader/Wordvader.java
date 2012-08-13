package wordvader;

import hanging.HangingPlaySolvable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import lexulous.LexulousSolvable;
import scrabble.ScrabbleSolvable;
import wordsworth.WordsWorthSolvable;
import bookworm.BookwormSolvable;
import friends.FriendsSolvable;
import wordsworth.WordsWorthSolvableV3;


public class Wordvader {
	List<Solvable> solvers = new ArrayList<Solvable>();
	
	public Wordvader() {
		// Lexulous Solvable
		this.solvers.add(new LexulousSolvable());
		// Scrabble Solvable
		this.solvers.add(new ScrabbleSolvable());
		// Words with Friends Solvable
		this.solvers.add(new FriendsSolvable());
		// WordsWorth Solvables
		this.solvers.add(new WordsWorthSolvable());
        this.solvers.add(new WordsWorthSolvableV3());
		// Bookworm Solvable
		this.solvers.add(new BookwormSolvable());
		// Hinging with Friends Solvables
		this.solvers.add(new HangingPlaySolvable());
        // TODO this.solvers.add(new HangingGuessSolvable());
	}
	
	public Solution solve(InputStream imageStream) throws Exception {
		return this.solve(ImageIO.read(imageStream));
	}
	
	// Delay the initialization of the Solver until it is determined the image can be solved.
	public Solution solve(BufferedImage image) throws Exception {
		for (Solvable solver : this.solvers) {
			if (solver.solves(image))
				return solver.solver().solve(image);
		}
		
		System.out.println(new Date().toString() + " No Solvable found for InputStream");
		ImageIO.write(image, "png", new File("/tmp/Solvable" + Math.random() + ".png"));
		
		return new Solution();
	}
}
