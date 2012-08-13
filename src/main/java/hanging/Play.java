package hanging;

import java.util.Map;

public class Play {
	private String word;
	private int points;
	
	public Play(String word, Map<Character, Integer> pointsMap) {
		this.word = word;
		for (int i = 0; i < word.length(); i++) {
			this.points += pointsMap.get(word.charAt(i));
		}
	}
	
	public String getWord() {
		return this.word;
	}
	
	public int getPoints() {
		return this.points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
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
		Play other = (Play) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
	public String toString() {
		return this.word;
	}
}
