package wordvader;

public class Index {
	public int x;
	public int y;
	
	public Index(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Index clone() {
		return new Index(this.x, this.y);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Index other = (Index) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	public String toString() {
		return x + "," + y;
	}
}
