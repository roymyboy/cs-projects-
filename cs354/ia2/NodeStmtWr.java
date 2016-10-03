/**
 * 
 */
public class NodeStmtWr extends NodeStmt{
	private NodeExpr expr;

	public NodeStmtWr(NodeExpr expr){
		this.expr = expr; 
	} 

	public double eval(Environment env) throws EvalException{
		//System.out.println(expr.eval(env));
		return expr.eval(env);
	}
} 
