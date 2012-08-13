package bookworm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import junit.framework.TestCase;

public class BookwormDictionaryTest extends TestCase {
	public void testDummy() {	
	}
	
	public void tstDictionary() throws Exception {
		BufferedReader reader = new BufferedReader(
				  new FileReader("/Users/alexwinston/Desktop/oed_encoded.txt"));
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("/Users/alexwinston/Desktop/bookworm.dic"));
				        
		/*Encoding e1 = new Encoding().encode("AARDVARK", "8S");
		System.out.println(e1.word);
		Encoding e2 = new Encoding(e1.index).encode(e1.word, "4WOLF");
		System.out.println(e2.word);
		Encoding e3 = new Encoding(e2.index).encode(e2.word, "7VES");
		System.out.println(e3.word);
		Encoding e4 = new Encoding(e3.index).encode(e3.word, "1BACA");
		System.out.println(e4.word);
		Encoding e5 = new Encoding(e4.index).encode(e4.word, "5S");
		System.out.println(e5.word);
		Encoding e6 = new Encoding(e5.index).encode(e5.word, "4K");
		System.out.println(e6.word);
		Encoding e7 = new Encoding(e6.index).encode(e6.word, "US");
		System.out.println(e7.word);
		Encoding e8 = new Encoding(e7.index).encode(e7.word, "6ES");
		System.out.println(e8.word);
		Encoding e9 = new Encoding(e8.index).encode(e8.word, "3FT");
		System.out.println(e9.word);
		Encoding e10 = new Encoding(e9.index).encode(e9.word, "LONE");
		System.out.println(e10.word);
		Encoding e11 = new Encoding(e10.index).encode(e10.word, "0BAA");
		System.out.println(e11.word);*/
		
		try {
			Encoding e = new Encoding().encode(reader.readLine(), reader.readLine());
			
			String line;
			while ((line = reader.readLine()) != null) {
				e = new Encoding(e.index).encode(e.word, line);
				if (e.word.length() >= 4 && e.word.length() <= 8) {
					writer.write(e.word.toLowerCase());
					writer.newLine();
				}
		    }
			
			writer.flush();
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private class Encoding {
		int index;
		String word;
		
		public Encoding() {
			this(0);
		}
		
		public Encoding(int index) {
			this.index = index;
		}
		
		public Encoding encode(String s1) {
			return this.encode("", s1);
		}
		
		public Encoding encode(String s1, String s2) {
			int suffixIndex = 0;
			for (int i = 0; i < s2.length(); i++) {
				try {
					suffixIndex = Integer.parseInt(s2.substring(0, i));
				} catch (NumberFormatException e) {
					// Ignore
				}
			}
			
			String suffix = s2.substring(s2.contains(String.valueOf(suffixIndex)) ? String.valueOf(suffixIndex).length() : 0);
			
			this.index = s2.contains(String.valueOf(suffixIndex)) ? suffixIndex : this.index;
			this.word = s1.substring(0, this.index).concat(suffix);
			
			return this;
		}
	}
}
