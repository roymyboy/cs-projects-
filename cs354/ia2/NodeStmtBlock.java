/**
 * this class represents an assignment of node,
 */ 

	public class NodeStmtBlock extends NodeStmt{
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
		
		public NodeStmtBlock(NodeStmt stmt){
			this.stmt = stmt;
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
