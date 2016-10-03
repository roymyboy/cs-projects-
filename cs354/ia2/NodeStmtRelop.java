/**
 * defines all the operation of comparison. includes symbol like
 * greater than, greater than equal to, less than, less than equal to
 * check for boolexpr, and not equal to
 */
	public class NodeStmtRelop extends Node{

	private String relop;
	/**
 	*
 	*/ 
	public NodeStmtRelop(int pos, String relop){
		this.pos = pos;
		this.relop = relop;
	}
	
	/**
 	* implementation of operators 
 	* @param o2 value in relop function
 	* @param o1 value in relop function
 	* @return result of function 
 	* @throws EvalException
 	*/ 

	public double op( double o1, double o2) throws EvalException{
		if(relop.equals("<")){
			return (o1 < o2) ? 1.0 : 0.0;
		} else if(relop.equals("<=")){
			return (o1 <= o2) ? 1.0 : 0.0;
		} else if(relop.equals(">")){
			return (o1>o2) ?  1.0 : 0.0;
		} else if(relop.equals(">=")){
			return (o1>=o2) ? 1.0 : 0.0;
		} else if(relop.equals("<>")){
			return (o1!=o2) ?  1.0 : 0.0;
		} else if(relop.equals("==")){
			return (o1==o2) ? 1.0 : 0.0;
		}
		throw new EvalException(pos, "mulop: " + relop);
	}
} 
