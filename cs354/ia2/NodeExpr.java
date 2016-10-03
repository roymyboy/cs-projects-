/**
 * append values, evaluation of given expression 
 *
 */
public class NodeExpr extends Node {

    private NodeTerm term;
    private NodeAddop addop;
    private NodeExpr expr;

    /**
     * @param term result of addop or mulop 
     * @param addop operation of addition or subtraction 
     * @param expr result of addop or mulop
     */
    public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
	this.term=term;
	this.addop=addop;
	this.expr=expr;
    }

    /**
     * this method appends value for the expression
     * @param expr term or result of addop
     */
    public void append(NodeExpr expr) {
	if (this.expr==null) {
	    this.addop=expr.addop;
	    this.expr=expr;
	    expr.addop=null;
	} else
	    this.expr.append(expr);
    }

    /* (non-Javadoc)
     * @see Node#eval(Environment)
     * checks the expression 
     */
    public double eval(Environment env) throws EvalException {
	return expr==null
	    ? term.eval(env)
	    : addop.op(expr.eval(env),term.eval(env));
    }

}
