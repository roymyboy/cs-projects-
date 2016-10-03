/**
 * this class reads in the entire statement passed in by the user including 
 * Grammar followed by the semi-colon
 */ 
public class NodeStmt extends Node {

   private NodeAssn assn;

    /**
 	* @param assn assignment value of program  
 	*/
    public NodeStmt(NodeAssn assn) {
		this.assn=assn;
    }
	
	public NodeStmt(){

	}	
//ublic class getId(){
//return assn.getId();
//	}

	/**
	* (non-Javadoc) 
 	* @see Node#eval (Environment)
 	*/ 
    public double eval(Environment env) throws EvalException {
		return assn.eval(env);
    }

}
