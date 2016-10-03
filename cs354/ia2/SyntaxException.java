/**
 * this class defines syntax error, when token does not match the expected value 
 * exception is throws with the information like position, expected value and 
 * found value at the position of exception occured  
 */
public class SyntaxException extends Exception {

    private int pos;
    private Token expected;
    private Token found;

    /**
     * if the token value does not match with the token at the position
     * exception will occur
     * @param pos position where the exception happened 
     * @param expected what was program expecting 
     * @param found what was found at exception position 
     */
    public SyntaxException(int pos, Token expected, Token found) {
	this.pos=pos;
	this.expected=expected;
	this.found=found;
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#toString()
     */
    public String toString() {
	return "syntax error"
	    +", pos="+pos
	    +", expected="+expected
	    +", found="+found;
    }

}
