/**
 * This class defines the multiplication operation Grammar. It also has the functionality to carry out 
 * multiplication and division. 
 *
 */
public class NodeMulop extends Node {

    private String mulop;

    /**
     * @param pos position of the current value passed in through scanner
     * @param mulop variable to store calculated value
     */
    public NodeMulop(int pos, String mulop) {
	this.pos=pos;
	this.mulop=mulop;
    }

    /**
     * this function carries out multiplication operation 
     * @param o1 double variable
     * @param o2 double variable 
     * @return the calculated value of multiplication or division
     * @throws EvalException
     */
    public double op(double o1, double o2) throws EvalException {
	if (mulop.equals("*"))
	    return o1*o2;
	if (mulop.equals("/"))
	    return o1/o2;
	throw new EvalException(pos,"bogus mulop: "+mulop);
    }

}
