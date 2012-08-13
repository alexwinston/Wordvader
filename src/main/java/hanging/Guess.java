package hanging;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Guess {
	private String word;
	// Unknown letter indexes
	private List<Integer> indexes = new ArrayList<Integer>();
	private int blanks;
	
	public Guess(String word) {
		this.word = word;
		// Create the list of unknown letter indexes and set the number of blank "?" tiles 
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == '0')
				indexes.add(i);
		}
		
		this.blanks = indexes.size();
	}
	
	public Set<Character> getLetters(Play play) {
		Set<Character> letters = new HashSet<Character>();
		
		// Iterate through the list of unknown letter indexes and get the letters played 
		for (int i : this.indexes) {
			letters.add(play.getWord().charAt(i));
		}

		return letters;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public String getWord(String letters) {
		if (letters != null && letters.length() > this.blanks)
			throw new IllegalArgumentException("Guessed letters cannot be greater than the number of blanks in the word");
		
		StringBuilder sb = new StringBuilder();
		
		int index = 0;
		for (int skipped = 0; index < this.word.length() && (index - skipped) < letters.length(); index++) {
			if (this.word.charAt(index) != '0') {
				sb.append(this.word.charAt(index));
				skipped++;
			} else {
				sb.append(letters.charAt(index - skipped));
			}
		}
		index--;
		
		// Append any letters that might appear after all the guesses have been placed
		while (index + 1 < this.word.length() &&  this.word.charAt(index + 1) != '0')
			sb.append(this.word.charAt(index++ + 1));
		
		return sb.toString();
	}
	
	public int getBlanks() {
		return this.blanks;
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
		Guess other = (Guess) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
}
