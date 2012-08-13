package friends;

import java.util.HashSet;
import java.util.Set;

public class Anagram {
	public Anagram() {
	}
	
	public Set<String> strings(String[] rack, int wordLength) {
		// TODO Eliminate creation of strings larger than wordLength
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rack.length; i++)
			sb.append(rack[i].charAt(0));
		String[] anagrams = this.anagram(sb.toString());
		
		Set<String> words = new HashSet<String>();
		for (int i = 0; i < anagrams.length; i++) {
			for (int j = 0; j < anagrams[i].length(); j++) {
				String word = anagrams[i].substring(0, j + 1);
				if (word.length() == wordLength)
					words.add(word);
			}
		}
		
		return words;
	}
	
    public String[] anagram(String word) {

        // Terminating condition for the recursion, a string of length 1
        if(word.length() == 1) {
            return new String[] {word};

        // For strings longer than 1 remove first character, and recursively
        // call anagram on the remainder of the string.
        } else {

            // remove first character
            char first = word.charAt(0);
            int ndx = 0;

            // recursively call anagram with remainder of string to create a sublist
            String list[] = anagram(word.substring(1));

            // Allocate space for the new list that we'll create from the
            // returned sub list and the first character.
            String ret[] = new String[factorial(word.length())];

            // Add removed character back into each string in sublist at
            // each character position in strings
            for(int i=0; i<list.length; i++) {

                // Convert one string from sublist into an array of characters
                char exp[] = list[i].toCharArray();

	        // a temporary string used to build string one character at a time
                String tmp;

	        // This loop cycles through each character of exp and determines
	        // where in string to place removed character.
                for(int j=0; j<exp.length; j++) {
                    tmp = "";

	            // This loop cycles through each character of exp and builds
	            // the new string one character at a time.
                    for(int k=0; k<exp.length; k++) {

                        // Build new string on character at a time, when the two
                        // loop variables are equal add in the removed character.
                        if(j==k) {
                            tmp = tmp + (new Character(first)).toString()
                                + (new Character(exp[k])).toString();
                        } else {
                          tmp = tmp + (new Character(exp[k])).toString();
                        }
                    } //end-for

	            // Add the completed string to the return list
                    ret[ndx++] = tmp;
                } //end-for

	        // Make one final addition to the list: the string with the
	        // removed character appended, since this combination is not
	        // created in the loops above.
                ret[ndx++] = list[i] + (new Character(first)).toString();
            } //end-for

            return ret;
        } //end-else
    }


    /* Method:		factorial
     *
     * Description:	This method returns the factorial of a n.
     */
    private int factorial(int n) {
        if(n <= 1) {
            return 1;
        } else {
            return n * factorial(n-1);
        }
    }
}