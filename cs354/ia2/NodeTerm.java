/**
 * This class defines the term which can be the result of 
 * addop operation, mulop operation a fact(id, Expr or a num)
 */
public class NodeTerm extends Node {

    private NodeFact fact;
    private NodeMulop mulop;
    private NodeTerm term;

    /**
     * @param fact
     * @param mulop
     * @param term
     */
    public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term) {
	this.fact=fact;
	this.mulop=mulop;
	this.term=term;
    }

    /**
     * @param term
     */
    public void append(NodeTerm term) {
	if (this.term==null) {
	    this.mulop=term.mulop;
	    this.term=term;
	    term.mulop=null;
	} else
	    this.term.append(term);
    }

    /* (non-Javadoc)
     * @see Node#eval(Environment)
     */
    public double eval(Environment env) throws EvalException {
	return term==null
	    ? fact.eval(env)
	    : mulop.op(term.eval(env),fact.eval(env));
    }

}
