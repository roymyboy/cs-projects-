// This class is a scanner for the program
// and programming language being interpreted.

import java.util.*;

/**
 * Scanner class, it scans the argument passed into the command line
 * it is capable of reading the white spaces comparators.
 * It has useful functions like to define token and lexeme values 
 */
public class Scanner {

	private String program;	// source program being interpreted
	private int pos;		// index of next char in program
	private Token token;	// last/current scanned token

	// sets of various characters and lexemes
	private Set<String> whitespace=new HashSet<String>();
	private Set<String> digits=new HashSet<String>();
	private Set<String> letters=new HashSet<String>();
	private Set<String> legits=new HashSet<String>();
	private Set<String> keywords=new HashSet<String>();
	private Set<String> operators=new HashSet<String>();
	private Set<String> comment = new HashSet<String>();

	// initializers for previous sets
	/**
	 * @param s is the hash set, where we append values
	 * @param lo lower boundary range of the values
	 * @param hi upper boundary range of the values
	 */
	private void fill(Set<String> s, char lo, char hi) {
		for (char c=lo; c<=hi; c++)
			s.add(c+"");
	}    
	
	/**
	 * This function will define @ as the comment 
	 * @param s is the hash set, where we append values
	 */
	private void initComment(Set<String> s){
		s.add("#");
	}

	/**
	 * whitespace will be append to the hash set values
	 * @param s
	 */
	private void initWhitespace(Set<String> s) {
		s.add(" ");
		s.add("\n");
		s.add("\t");
	}

	/**
	 * digits will be append to the hash set values
	 * @param s
	 */
	private void initDigits(Set<String> s) {
		s.add(".");
		fill(s,'0','9');
	}

	/**
	 * letters will be append to the hash set values
	 * @param s
	 */
	private void initLetters(Set<String> s) {
		fill(s,'A','Z');
		fill(s,'a','z');
	}

	/**
	 * legits will be append to the hash set values
	 * @param s
	 */
	private void initLegits(Set<String> s) {
		s.addAll(letters);
		s.addAll(digits);
	}

	/**
	 * operators will be append to the hash set values  
	 * @param s
	 */
	private void initOperators(Set<String> s) {
		s.add("=");
		s.add("+");
		s.add("-");
		s.add("*");
		s.add("/");
		s.add("(");
		s.add(")");
		s.add(";");
		s.add("<");
		s.add("<=");
		s.add(">");
		s.add(">=");
		s.add("<>");
		s.add("==");
	}

	/**
	 * this function does nothing
	 * @param s
	 */
	private void initKeywords(Set<String> s) {
		s.add("rd");
		s.add("wr");
		s.add("if");
		s.add("then");
		s.add("else");
		s.add("while");
		s.add("do");
		s.add("begin");
		s.add("end");
	}

	// constructor:
	//   - squirrel-away source program
	//   - initialize sets
	/**
	 * the input by user to be scanned and processed 
	 * @param program
	 */
	public Scanner(String program) {
		this.program=program;
		pos=0;
		token=null;
		initComment(comment);
		initWhitespace(whitespace);
		initDigits(digits);
		initLetters(letters);
		initLegits(legits);
		initKeywords(keywords);
		initOperators(operators);
	}

	// handy string-processing methods
	/**
	 * @return boolean value of the current position in the scanner  
	 */
	public boolean done() {
		return pos>=program.length();
	}
	
	

	/**
	 * this method increments the position to step through the input value
	 * until the program has no more character in the has set passed in 
	 * @param s the hash to which we compare the current iteration 
	 */
	private void many(Set<String> s) {
		while (!done() && s.contains(program.charAt(pos)+""))
			pos++;
	}

	/**
	 * this program will iterate through until we reach the character
	 * and once found the position will increase  
	 * @param c character value, which we will be iterating through
	 */
	private void past(char c) {
		while (!done() && c!=program.charAt(pos)){
			pos++;
		//	System.out.println(program.charAt(pos));
		//	System.out.println(done());
		}
		if (!done() && c==program.charAt(pos))
			pos++;
	}
	
	// scan various kinds of lexeme
	/**
	 * this function will find the comment passed in by the user
	 * in the command line argument iterate through it and jump 
	 * to the nest program ignoring the comment as long as the 
	 * comment is started with the #  
	 * 
	 * Rules to comment : #nednkcw.ncekle or #sdnckjdcnkjdnc#
	 */
	private void nextComment(){
		pos++;
		past('#');
	    next();
}

	/**
	 * iterates through the end of the program, assigns the value of token 
	 * with num
	 */
	private void nextNumber() {
		int old=pos;
		many(digits);
		token=new Token("num",program.substring(old,pos));
	}

	/**
	 * iterates through the end of the program, assigns value to the token 
	 * with keyword
	 */
	private void nextKwId() {
		int old=pos;
		many(letters);
		many(legits);
		String lexeme=program.substring(old,pos);
		token=new Token((keywords.contains(lexeme) ? lexeme : "id"),lexeme);
	}

	/**
	 * this function looks for all the valid operator and assigns value to the token 
	 */
	private void nextOp() {
		int old=pos;
		pos=old+2;
		if (!done()) {
			String lexeme=program.substring(old,pos);
			if (operators.contains(lexeme)) {
				token=new Token(lexeme); // two-char operator
				return;
			}
		}
		pos=old+1;
		String lexeme=program.substring(old,pos);
		token=new Token(lexeme); // one-char operator
	}

	// This method determines the kind of the next token (e.g., "id"),
	// and calls a method to scan that token's lexeme (e.g., "foo").
	/**
	 *  this class reads in all the input until boolean done is 
	 *  true updating the position and token to next valid option
	 * @return
	 */
	public boolean next() {
		if (done())
			return false;
		many(whitespace);
		String c=program.charAt(pos)+"";
		if (digits.contains(c))
			nextNumber();
		else if (letters.contains(c))
			nextKwId();
		else if (operators.contains(c))
			nextOp();
		else if(comment.contains(c)){
			nextComment();
			return false;
		}
		else {
			System.err.println("illegal character at position "+pos);
			pos++;
			return next();
		}
		return true;
	}

	// This method scans the next lexeme,
	// if the current token is the expected token.
	/**
	 * @param t token value to which compare the current token 
	 * 		  value stored in the scanner object 
	 * @throws SyntaxException
	 */
	public void match(Token t) throws SyntaxException {
		if (!t.equals(curr()))
			throw new SyntaxException(pos,t,curr());
		next();
	}

	/**
	 * @return current token value stored in the scanner object
	 * @throws SyntaxException
	 */
	public Token curr() throws SyntaxException {
		if (token==null)
			throw new SyntaxException(pos,new Token("ANY"),new Token("EMPTY"));
		return token;
	}

	/**
	 * @return current value of position
	 */
	public int pos() { return pos; }

	// for unit testing
	public static void main(String[] args) {
		try {
			Scanner scanner=new Scanner(args[0]);
			while (scanner.next())
				System.out.println(scanner.curr());
		} catch (SyntaxException e) {
			System.err.println(e);
		}
	}

}
