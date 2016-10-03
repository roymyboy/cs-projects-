public class NodeStmtWhile extends NodeStmt{
		private NodeBoolExpr bool;
		private NodeStmt stmt;

	public NodeStmtWhile(NodeBoolExpr bool, NodeStmt stmt){
		this.bool = bool;
		this.stmt = stmt;
	}

	public double eval(Environment env) throws EvalException{
		double result = stmt.eval(env);
		while(bool.eval(env) == 1.0){
			result += stmt.eval(env);
		}
		return result;
	}
}
