import java.util.Scanner;
/**
 *
 */

public class NodeStmtRd extends NodeStmt{
	private NodeFactId id;
	private static Scanner scan;
	/**
 	* @param id
 	* @param num
	*/
	public NodeStmtRd(NodeFactId id){
		this.id = id;
	}

	private double read(){
		double num = 0.0;
		scan = new Scanner(System.in);
		try{
			num = Double.parseDouble(scan.next());
		} catch(Exception e){
			System.err.println(e);
			return read();
		}
		return num;
	}
	
	/**
 	* (non-Javadoc)
 	* @see Node#eval(Environment)
 	*/ 
	public double eval(Environment env) throws EvalException{
		return env.put(id.getId(), read());
	}
		

}
