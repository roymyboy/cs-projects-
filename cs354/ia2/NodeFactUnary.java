
/**
 * this class defines the unary operator as denoted by the Grammar 
 * @author utroy
 */
public class NodeFactUnary extends NodeFact{
	private NodeFact fact;
	
	/**
	 * @param fact
	 */
	public NodeFactUnary(NodeFact fact){
		this.fact = fact;
	}
	
	/* (non-Javadoc)
	 * @see Node#eval(Environment)
	 */
	public double eval(Environment env) throws EvalException{
		return (fact.eval(env)) * -1;
	}
	
}
