/**
 * when the expression is input by the user this class assigns it to id.
 * 
 */
public class NodeAssn extends NodeStmt {

    private String id;
    private NodeExpr expr;

    /**
     * @param id is associated with expression
     * @param expr expression associated with id
     */
    public NodeAssn(String id, NodeExpr expr) {
	this.id=id;
	this.expr=expr;
    }

    /**
     * this method will store the returned values in the hash map  
     */
    public double eval(Environment env) throws EvalException {
	return env.put(id,expr.eval(env));
    }

}
