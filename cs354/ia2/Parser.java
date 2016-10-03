// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

/**
 * This class iterates through the input by the user and provides the information
 * like current position value of current.
 */

import java.util.*;

public class Parser {

	private Scanner scanner;

	/**
	 * compares s value to the current value stored in the scanner object 
	 * @param s value to match current value stored of the token in the scanner object
	 * @throws SyntaxException
	 */
	private void match(String s) throws SyntaxException {
		scanner.match(new Token(s));
	}

	/**
	 * @return the current value for the token stored in the scanner object
	 * @throws SyntaxException
	 */
	private Token curr() throws SyntaxException {
		return scanner.curr();
	}

	/**
	 * @return current position value stored in the scanner object
	 */
	private int pos() {
		return scanner.pos();
	}

	/**
 	*
 	*/
	private NodeStmtRelop parseRelop() throws SyntaxException {
		if(curr().equals(new Token("<"))){
			match("<");
			return new NodeStmtRelop(pos(), "<");
		}else if(curr().equals(new Token("<="))){
			match("<=");
			return new NodeStmtRelop(pos(), "<=");
		}else if(curr().equals(new Token(">"))){
			match(">");
			return new NodeStmtRelop(pos(), ">");
		}else if(curr().equals(new Token(">="))){
			match(">=");
			return new NodeStmtRelop(pos(), ">=");
		}else if(curr().equals(new Token("<>"))){
			match("<>");
			return new NodeStmtRelop(pos(), "<>");
		}else if(curr().equals(new Token("=="))){
			match("==");
			return new NodeStmtRelop(pos(), "==");
		}
		return null;
	}

	/**
	 * this method finds the operator sign(* or /) and increases the position
	 * as well as token as in match function. 
	 * @return new value according to the operator i.e * or / 
	 * @throws SyntaxException
	 */
	private NodeMulop parseMulop() throws SyntaxException {
		if (curr().equals(new Token("*"))) {
			match("*");
			return new NodeMulop(pos(),"*");
		}
		if (curr().equals(new Token("/"))) {
			match("/");
			return new NodeMulop(pos(),"/");
		}
		return null;
	}

	/**
 	*
 	*/ 
	private NodeBoolExpr parseBool() throws SyntaxException{
			NodeExpr expr = parseExpr();
			NodeStmtRelop relop =  parseRelop();
			NodeExpr expr1 = parseExpr();
			return new NodeBoolExpr(expr, relop, expr1);
	}

	/**
	 * this method finds operator sign(- or +) and increases the position 
	 * as well as token as in math function
	 * @return new value according to the operator i.e - or +
	 * @throws SyntaxException
	 */
	private NodeAddop parseAddop() throws SyntaxException {
		if (curr().equals(new Token("+"))) {
			match("+");
			return new NodeAddop(pos(),"+");
		}
		if (curr().equals(new Token("-"))) {
			match("-");
			return new NodeAddop(pos(),"-");
		}
		return null;
	}

	/**
	 * this method parses the Node factor, it has unary parenthesis, id and operator.
	 * @return node factor of parenthesis, id or operator 
	 * @throws SyntaxException
	 */
	private NodeFact parseFact() throws SyntaxException {
		if (curr().equals(new Token("("))) {
			match("(");
			NodeExpr expr=parseExpr();
			match(")");
			return new NodeFactExpr(expr);
		}
		if (curr().equals(new Token("id"))) {
			Token id=curr();
			match("id");
			return new NodeFactId(pos(),id.lex());
		}
		if (curr().equals(new Token("-"))){
			match("-");
			NodeFact fact = parseFact();
			return new NodeFactUnary(fact);
		}
		Token num=curr();
		match("num");
		return new NodeFactNum(num.lex());
	}

	/**
	 * this method parses the node term 
	 * @return node term
	 * @throws SyntaxException
	 */
	private NodeTerm parseTerm() throws SyntaxException {
		NodeFact fact=parseFact();
		NodeMulop mulop=parseMulop();
		if (mulop==null)
			return new NodeTerm(fact,null,null);
		NodeTerm term=parseTerm();
		term.append(new NodeTerm(fact,mulop,null));
		return term;
	}

	/**
	 * this method parses the expressions 
	 * @return node expression
	 * @throws SyntaxException
	 */
	private NodeExpr parseExpr() throws SyntaxException {
		NodeTerm term=parseTerm();
		NodeAddop addop=parseAddop();
		if (addop==null)
			return new NodeExpr(term,null,null);
		NodeExpr expr=parseExpr();
		expr.append(new NodeExpr(term,addop,null));
		return expr;
	}

	/**
	 * this method assigns the current value for the token stored in the scanner 
	 * @return assn assigned value 
	 * @throws SyntaxException
	 */
	private NodeAssn parseAssn() throws SyntaxException {
		Token id=curr();
		match("id");
		match("=");
		NodeExpr expr=parseExpr();
		NodeAssn assn=new NodeAssn(id.lex(),expr);
		return assn;
	}
	
	private NodeStmtWr parseWr() throws SyntaxException{
		match("wr");
		NodeExpr expr = parseExpr();
		return new NodeStmtWr(expr);
	}
	
	private NodeStmtRd parseRd() throws SyntaxException{
	//	Token id = curr();
	//	match("id");
	//	Token id = curr();
		match("rd");
		NodeFact id = parseFact();
		if(id instanceof NodeFactId){
			return new NodeStmtRd((NodeFactId)id);}
			return new NodeStmtRd((NodeFactId)id);
	}
	/**
	 * this method calls match function to update position, current value and token
	 * @return stmt node statement 
	 * @throws SyntaxException
	 */
	private NodeStmt parseStmt() throws SyntaxException {
		if(curr().equals(new Token("rd"))){
			return parseRd();
		} else if(curr().equals(new Token("wr"))){
			return parseWr();
		} else if(curr().equals(new Token("if"))){
			return parseIf();
		} else if(curr().equals(new Token("while"))){
			return parseWhile();
		} else if(curr().equals(new Token("begin"))){
			return parseBegin();
		} else {
			return parseAssn();
		}
	}

	private NodeStmtWhile parseWhile() throws SyntaxException {
		match("while");
		NodeBoolExpr bool = parseBool();
		match("do");
		NodeStmt stmt = parseStmt();
		return new NodeStmtWhile(bool, stmt);
	}

	private NodeStmtIf parseIf() throws SyntaxException{
		match("if");
		NodeBoolExpr bool = parseBool();
		match("then");
		NodeStmt stmt = parseStmt();
		if(curr().equals(new Token("else"))){
			match("else");
			NodeStmt stmt1 = parseStmt();
			return new NodeStmtIf(bool, stmt, stmt1);
		} else {
			return new NodeStmtIf(bool, stmt);	
		}
	}
	
	private NodeStmtBlock parseBlock() throws SyntaxException{
		NodeStmt stmt = parseStmt();
		if(curr().equals(new Token(";"))){
			match(";");
			NodeStmtBlock block = parseBlock();
			return new NodeStmtBlock(stmt, block);
		} else {
			return new NodeStmtBlock(stmt);
			}
	}
	
	private NodeStmtBlock parseBegin() throws SyntaxException{
		match("begin");	
		NodeStmtBlock block = parseBlock();
		match ("end");
		return new NodeStmtBlock( block);
	}

	/**
	 * this method reads the input value and interprets it 
	 * @param program the input given by the user 
	 * @return node 
	 * @throws SyntaxException
	 */
	public Node parse(String program) throws SyntaxException {
		scanner=new Scanner(program);
		scanner.next();
		return parseStmt();
	}

}
