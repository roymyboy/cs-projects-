// This is the main class/method for the interpreter.
// Each command-line argument is a complete program,
// which is scanned, parsed, and evaluated.
// All evaluations share the same environment,
// so they can share variables.

/**
 * Driver class. This class can read in multiple argument 
 * and compute the values and print it by calling different classes
 */ 
public class Interpreter {

    public static void main(String[] args) {
	Parser parser=new Parser();
	Environment env=new Environment();
	for (String stmt: args){
		if(stmt.charAt(0) == '#')
			continue;
	    try {
		System.out.println(parser.parse(stmt).eval(env));
	    } catch (SyntaxException e) {
		System.err.println(e);
	    } catch (EvalException e) {
		System.err.println(e);
	    }
    }

    }
}