package wordvader;

public class Color {
	public int r;
	public int g;
	public int b;
	
	public Color(int rgb) {
	   	this((rgb & 0x00ff0000) >> 16, (rgb & 0x0000ff00) >> 8, rgb & 0x000000ff);
	}
	
	public Color(int r, int g, int b) {
		//System.out.println(r + " " + g + " " + b);
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public boolean threshold(Color target, int threshold) {
		return this.threshold(target, threshold, threshold, threshold, false);
	}
	
	public boolean threshold(Color target, int threshold, boolean debug) {
		return this.threshold(target, threshold, threshold, threshold, debug);
	}
	
	public boolean threshold(Color target, int tR, int tG, int tB) {
		return this.threshold(target, tR, tG, tB, false);
	}
	
	public boolean threshold(Color target, int tR, int tG, int tB, boolean debug) {
		if (debug)
			System.out.println(r + " " + g + " " + b + ", " + target.r + " " + target.g + " " + target.b);
		
		return ((Math.abs(this.r - target.r) <= tR) &&
				(Math.abs(this.g - target.g) <= tG) &&
				(Math.abs(this.b - target.b) <= tB));
	}
}
