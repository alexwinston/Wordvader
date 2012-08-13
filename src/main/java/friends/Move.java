package friends;

public class Move {
	public int x;
	public int y;
	public String letters;
	public int points;
	
	public Move(int x, int y, String letters) {
		this.x = x;
		this.y = y;
		this.letters = letters;
	}
	
	public Move(int x, int y, String letters, int points) {
		this(x, y, letters);
		this.points = points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((letters == null) ? 0 : letters.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (letters == null) {
			if (other.letters != null)
				return false;
		} else if (!letters.equals(other.letters))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	public boolean equalsWithPoints(Move m) {
		return (this.x == m.x && this.y == m.y && this.letters.equals(m.letters) && this.points == m.points); 
	}

	@Override
	public String toString() {
		return x + ":" + y + " " + letters + " " + points;
	}
}
