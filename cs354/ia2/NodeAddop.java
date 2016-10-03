/**
 * class to perform addition or subtraction, according to the asssiged operation 
 */ 
public class NodeAddop extends Node {

    private String addop;

	/**
 	* @param pos position of the value stored in the scanner 
 	* @param addop add operation, either + or - according to the assignment
 	*/ 
    public NodeAddop(int pos, String addop) {
	this.pos=pos;
	this.addop=addop;
    }

	/**
	* has functionality to use double  
    * @param o1 value in add operation
    * @param o2 value in add operation
    * @throws EvalException Evaluation Exception
    * @return the calculated value according to add operation assignment
	*/ 
    public double op(double o1, double o2) throws EvalException {
	if (addop.equals("+"))
	    return o1+o2;
	if (addop.equals("-"))
	    return o1-o2;
	throw new EvalException(pos,"bogus addop: "+addop);
    }

}
