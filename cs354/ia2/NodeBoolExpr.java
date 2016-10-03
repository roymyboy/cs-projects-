/**
 * BoolExpr is defined for comparison 
 */ 

public class NodeBoolExpr extends Node {

	private NodeStmtRelop relop;
	private NodeExpr expr;
	private NodeExpr expr1;

	/**
	* @param relop
	* @param expr
	* @param expr1
	*/ 
	public NodeBoolExpr(NodeExpr expr, NodeStmtRelop relop ,NodeExpr expr1){
			this.relop = relop;
			this.expr = expr;
			this.expr1 = expr1;
	
	}

	/**
    * (non-Javadoc)
    * @see Node#eval(Environment)
    * checks the expression
    */
	public double eval(Environment env) throws EvalException {
			return expr == null ? expr1.eval(env) : relop.op(expr.eval(env), expr1.eval(env));
	}


}
