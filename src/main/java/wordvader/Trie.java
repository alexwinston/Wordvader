package wordvader;
/** ***********************************trie.java*****************************************
	@author Chris Urso
	@program Program #2: WordPop With Text
	@class CS 340, Spring 2005
	@date Tuesday, 8th of March
	@system java 1.4.2, linux machines 2260 SEO

	The words in the dictionary are stored in a data type trie.  In this type of tree,
	each node can have a maximum of 26 children, one for each letter of the alphabet.
	Each node is structured like this.
	class node {
		char letter;
		boolean endsWord;
		node down;
		node right;
	}

	Each node stores a char letter, a boolean variable (indicates if the letter stored
	in the node ends a word or not), a pointer to the child of the node, and a pointer
	to the right sibling of the node.  Each node can have a maximum of 26 children, one
	for each letter of the alphabet.  All children are arranged in alphabetical order
	for easy searching.  This trie stores each word in the foreward rather than
	backword because the searching must be done from the start of the word.

	This document is responsible for reading the file whose name is sent from
	wordpop.java.  Each word in order is inserted into the trie one at a time.  This
	class is also responsible for searching the trie to see if a word (or phrase) is
	in the trie.  Finally, this class keeps track of the amount of each letter stored
	in the trie which is saved in amountList[].
/** ***********************************trie.java****************************************/

import java.io.*;

public class Trie {
	public class node {
		char letter;		        // stores the letter for each node
		boolean endsWord;	    	// true if the letter ends a word
		node down;		      	// points to the left most child of the node
		node right;		      	// points to the right sibling of the node

		// constructor for node class
		public node(char c) {
			letter = c;
			endsWord = false;
			down = null;
			right = null;
		}
		// overloaded constructor needed for some declarations
		public node(char c, node r) {
			letter = c;
			endsWord = false;
			down = null;
			right = r;
		}
	}

	node root;				// keep track of the root
	node position;				// keep track of the position
	public int[] amountList = new int[26];	// keep track of the amount of each letter

    public Trie() {
        root = position = null;
    }

    /*
	receives: a String str
	returns: a new string able to be stored in the tree
	gets rid of all spaces, punctuation, and numbers in str,
	converts it to lower case, and stores only words longer 
	than three
	*/
	public String fixString(String str) {
		int index = 0;  //starting index is 0

		// convert the string to lower case
		str=str.toLowerCase();

		// convert the String to an array of chars to easily
		// manipulate each char
		char[] myChars = str.toCharArray();  // holds the old String
		char[] newChars = new char[str.length()];  // will make up the new String

		// loop until every char in myChars is tested
		for( int x=0 ; x<myChars.length ; x++ ) {
			// accept all alphabetic characters only
			if(myChars[x]>='a' && myChars[x]<='z') {
				newChars[index++]=myChars[x];
			}
		}

		//return a String consisting of the characters in newChars
		return (new String(newChars));
	}


	/*
	receives: a String str
	returns: nothing - inserts
	insert the String str into the tree starting at the end of the word.
	each letter is inserted, and based on the other elements in the list
	a new child is added to the list, or an existing child is used.
	*/
	public void insert(String str) {
		// call fixString to prepare it for insertion
		str = fixString(str);

		// return if str in null
		if(str==null) return;

		// keep track of each index in the String
		int index=0;

		// if root equals null, create an empty node (this will only happen the first time)
		if(root==null) {
			root = new node(' ');
		}

		// declare pointers needed
		node temp,tempBack,pred;
		temp = tempBack = pred = root;

		// loop until every index in str is inserted
		while(index<str.length()) {
			// must make a new branch in the tree because there
			// is not yet a branch starting with the desired letter
			if(temp.down == null) {
				temp.down = new node(str.charAt(index));
				temp.down.right = new node(' ');
				temp.down.right.down = temp;
				temp = temp.down;
			}
			else {
				// update pred and temp
				pred = temp;
				temp = temp.down;
				// if the letter goes at the start of the list,
				// make an new path at the start of the list
				if(str.charAt(index) < temp.letter) {
					temp = new node(str.charAt(index),temp);
					pred.down = temp;
				}
				// if the letter is goes somewhere else in the list,
				// find the correct spot
				else if(str.charAt(index) > temp.letter) {
					//find the correct spot to insert the letter based on
					//alphabetical order
					while(temp.letter != ' ' && str.charAt(index) > temp.letter) {
						tempBack = temp;
						temp = temp.right;
					}
					//if the letter is not present, make a new path
					//otherwise, do nothing, simply follow the correct path
					if(str.charAt(index) != temp.letter) {
						temp = new node(str.charAt(index), temp);
						tempBack.right = temp;
					}
				}
			}
			//keep track of each letter, to easily calculate probabilities later
			if(str.charAt(index)>=97) amountList[str.charAt(index)-97]++;
			index++;
		}
		//at this point it will always be at the start of a word
		temp.endsWord = true;
	}

	/*
	receives: a String str (the name of a file)
	returns: nothing - builds the tree
	function builds a tree by reading the file named fileName one
	string at a time, and calls insert() to add that word to the tree.
	*/
	public void setupTrie(InputStream wordsStream) {
		this.setupTrie(wordsStream, false);
	}
	public void setupTrie(InputStream wordsStream, boolean reverse) {
		//stores the current word from the file
		String word = new String();

		try {
			InputStreamReader read = new InputStreamReader(wordsStream);
			//create a FileReader of the file name fileName
			//FileReader read = new FileReader(fileName);
			//create a BufferedReader using FileReader read
			//of size 50
			BufferedReader in = new BufferedReader(read,50);

			//while there are String left to read, read them
			//and insert them one by one
			while(in.ready()) {
				word=in.readLine();
				insert(reverse ? new StringBuffer(word).reverse().toString() : word);
				}
		}
		catch (Exception e) {
			//catch exception cause by reading the file
			e.printStackTrace();
			return;
		}
	}

 	//recursively print each node, and under that print each child of the node
 	//if the node starts a word, a '^' is printed after it.  this is used just
	//for testing.
	public void print() {
		node t=root;
		System.out.println(root.letter + " *");
		print(t.down);
	}

	void print(node t) {
		if(t==null) return;
		//System.out.println(t.letter + " children");
		node temp=t;
		while(t!=null) {
			System.out.print(t.letter + " ");
			if(t.endsWord)System.out.print("^");
			t=t.right;
		}
		System.out.println("*");
		t=temp;
		while(t!=null) {
			if(t.down!=null && t.right!=null) {
				System.out.println(t.letter + " children");
				print(t.down);

			}
			t=t.right;
		}
	}

	//reset the positition by pointing it to root
	public void resetPosition() {
		position = root;
	}

	/*
	receives: a String str
	returns: true if str is found, otherwise false
	follows the tree until the start until the prefix is found.
	it doesn't matter if str is a full word or not.
	*/
	public boolean stringInTrie(String str) {
		//node to traverse the tree
		node t = root;

		//loop until ever index in str is searched
		for(int index=0;index<str.length();index++) {
			//move t down each time
			if(t.down!=null) {
				t = t.down;
			}
			//if t can go down no more, but str hasn't been found
			//then it is not in the dictionary
			else {
				return false;
			}

			//search the children lists for the correct path to follow
			while(str.charAt(index) != t.letter) {
				//if the correct path hasn't been found,
				//then str is not in the dictionary
				if(t.right==null) {
					return false;
				}
				//move t to the right
				t = t.right;
			};
		}
		position = t;
		return true;
	}

	/*
	receives: a String str
	returns: true if str is found, otherwise false
	calls stringInTrie to see if the string is there, and returns true
	if the last node in the string ends a word
	*/
	public boolean wordInTrie(String str) {
		//only return true if position starts a word, otherwise it's not in the dictionary
		if(stringInTrie(str) && position.endsWord) {
			return true;
		}
		else {
			return false;
		}
	}
}
