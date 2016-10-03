/**
 * this class defines id for node factor
 */
public class NodeFactId extends NodeFact {

    private String id;

    /**
     * @param pos position of id
     * @param id of the factor 
     */
    public NodeFactId(int pos, String id) {
	this.pos=pos;
	this.id=id;
    }
	
	public String getId(){
	return id;
	}
    /* (non-Javadoc)
     * @see Node#eval(Environment)
     */
    public double eval(Environment env) throws EvalException {
	return env.get(pos,id);
    }

}
