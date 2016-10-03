public class NodeStmtIf extends NodeStmt{
	private NodeBoolExpr boolExpr;
	private NodeStmt stmt;
	private NodeStmt stmt1;

	public NodeStmtIf(NodeBoolExpr boolExpr, NodeStmt stmt){
		this.boolExpr = boolExpr;
		this.stmt = stmt;
	}

	public NodeStmtIf(NodeBoolExpr boolExpr, NodeStmt stmt,NodeStmt stmt1){
		this.boolExpr = boolExpr;
		this.stmt = stmt;
		this.stmt1 = stmt1;
	}

	public double eval(Environment env) throws EvalException{
		if(boolExpr.eval(env) == 1.0){
			return stmt.eval(env);
		} else {
			if(stmt1 != null){
				return stmt1.eval(env);
			} else {
				return 0.0;
			}
		}
	}	

}
