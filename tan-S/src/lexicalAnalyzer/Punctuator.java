package lexicalAnalyzer;

import inputHandler.TextLocation;
import tokens.LextantToken;
import tokens.Token;


public enum Punctuator implements Lextant {
	ADD("+"), 
	SUBTRACT("-"),
	MULTIPLY("*"),
	DIVIDE("/"),
	NOT("!"),
	GREATER(">"),
	GREATEREQUAL(">="),
	LESS("<"),
	LESSEREQUAL("<="),
	EQUAL("=="),
	NOTEQUAL("!="),
	ASSIGN(":="),
	CAST(""),
	CAST_END(">"),
	PRINT_SEPARATOR("\\"),
	PRINT_SPACE("\\s"),
	PRINT_NEWLINE("\\n"),
	PRINT_TAB("\\t"),
	TERMINATOR(";"), 
	OPEN_BRACE("{"),
	CLOSE_BRACE("}"),
	OPEN_SQUARE_BRACE("["),
	CLOSE_SQUARE_BRACE("]"),
	COMMA(","),
	AND("&&"),
	OR("||"),
	INDEXING(":"),
	OPEN_ROUND_BRACE("("),
	CLOSE_ROUND_BRACE(")"),
	NULL_PUNCTUATOR("");

	private String lexeme;
	private Token prototype;
	
	private Punctuator(String lexeme) {
		this.lexeme = lexeme;
		this.prototype = LextantToken.make(TextLocation.nullInstance(), lexeme, this);
	}
	public String getLexeme() {
		return lexeme;
	}
	public Token prototype() {
		return prototype;
	}
	
	
	public static Punctuator forLexeme(String lexeme) {
		for(Punctuator punctuator: values()) {
			if(punctuator.lexeme.equals(lexeme)) {
				return punctuator;
			}
		}
		return NULL_PUNCTUATOR;
	}
	
/*
	//   the following hashtable lookup can replace the implementation of forLexeme above. It is faster but less clear. 
	private static LexemeMap<Punctuator> lexemeToPunctuator = new LexemeMap<Punctuator>(values(), NULL_PUNCTUATOR);
	public static Punctuator forLexeme(String lexeme) {
		   return lexemeToPunctuator.forLexeme(lexeme);
	}
*/
	
}


