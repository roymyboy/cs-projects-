/**
 * this class represents an assignment of node,
 */ 

	public class NodeStmtBlock extends Node{
		private NodeStmt stmt;
		private NodeStmtBlock block;

		/**
 		* constructor 
 		* @param stmt
 		* @param block
 		*/
		public NodeStmtBlock(NodeStmt stmt, NodeStmtBlock block){
			this.stmt = stmt;
			this.block = block;
		}


		/**
 		* (non-Javadoc)
 		* @see Node#eval(Environment)
 		*
 		*/ 
		public double eval(Environment env) throws EvalException {
			if(block == null){
				return stmt.eval(env);
			} else {
				stmt.eval(env);
				return block.eval(env);
			}
		}
		
	}
