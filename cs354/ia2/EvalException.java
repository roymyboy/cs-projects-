/**
 * Evaluation Exception class, throws exception error with position and message
 */ 
public class EvalException extends Exception {

    private int pos;
    private String msg;

	/**
 	* @param pos position where exception occurred while running the program
 	* @param msg message of the error
 	*/ 
    public EvalException(int pos, String msg) {
	this.pos=pos;
	this.msg=msg;
    }

	//format to print error message
    public String toString() {
	return "eval error"
	    +", pos="+pos
	    +", "+msg;
    }

}
