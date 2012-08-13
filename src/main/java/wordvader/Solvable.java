package wordvader;

import java.awt.image.BufferedImage;

/**
 * This interface is needed because building the Solver should happen after it is
 * determined that the image can be solved by this implementation. Otherwise
 * there is a considerable performance penalty for initializing all the Solver(s)
 * simply to determine if they solve an image in wordvader.Wordvader
 * 
 * @author alexwinston
 *
 */
public interface Solvable {
	public boolean solves(BufferedImage image);
	public Solver solver();
}
