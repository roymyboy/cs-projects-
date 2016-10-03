// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.
import java.util.HashMap;

public class Environment {
	private HashMap<String, Double>values = new HashMap<String, Double>();
   
     /**
	 * @param var id which  is associated with value stored in hash map
	 * @param val value to the associated id stored in hash map
	 * @return value given by us to store in hash map
	 */ 
	 public double put(String var, double val) {
		values.put(var, val);
		return val;
	 }
	
	/**
 	* @param pos position for exception handling 
 	* @param var id associated with value we are trying to access in storage 
 	* @throws EvalException
 	* @return values associated with the id from  storage
 	*/ 
	 public double get(int pos, String var) throws EvalException { 
		if(!values.containsKey(var)){
			throw new EvalException(pos,"Invalid Variable name: " + var);
		}
		return values.get(var);
	 }

}
