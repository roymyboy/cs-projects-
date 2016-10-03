// This class models a token, which has two parts:
// 1) the token itself (e.g., "id" or "+")
// 2) the token's lexeme (e.g., "foo")
/**
 * This class defines token object and lexeme values
 */
public class Token {

    private String token;
    private String lexeme;

    /**
     * @param token
     * @param lexeme
     */
    public Token(String token, String lexeme) {
	this.token=token;
	this.lexeme=lexeme;
    }

    /**
     * @param token
     */
    public Token(String token) {
	this(token,token);
    }

    /**
     * @return token
     */
    public String tok() { return token; } 
    
   /**
    * @return lexeme
    */
    public String lex() { return lexeme; }

    /**
     * @param t token value passed in for comparison 
     * @return boolean value, is token is equal to the token being compared against
     */
    public boolean equals(Token t) {
	return token.equals(t.token);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
	return "<"+tok()+","+lex()+">";
    }

}
