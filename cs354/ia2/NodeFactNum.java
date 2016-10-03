/**
 * this class defines the number of the node factor
 *
 */
public class NodeFactNum extends NodeFact {

    private String num;

    /**
     * @param num numer of the node factor
     */
    public NodeFactNum(String num) {
	this.num=num;
    }

    /* (non-Javadoc)
     * @see Node#eval(Environment)
     */
    public double eval(Environment env) throws EvalException {
	return Double.parseDouble(num);
    }

}
