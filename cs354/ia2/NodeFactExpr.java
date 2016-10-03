public class NodeFactExpr extends NodeFact {

    private NodeExpr expr;

    public NodeFactExpr(NodeExpr expr) {
	this.expr=expr;
    }

    /* (non-Javadoc)
     * @see Node#eval(Environment)
     */
    public double eval(Environment env) throws EvalException {
	return expr.eval(env);
    }

}
