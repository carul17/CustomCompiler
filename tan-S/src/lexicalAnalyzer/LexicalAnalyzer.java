package lexicalAnalyzer;


import logging.TanLogger;

import inputHandler.InputHandler;
import inputHandler.LocatedChar;
import inputHandler.LocatedCharStream;
import inputHandler.PushbackCharStream;
import tokens.IdentifierToken;
import tokens.LextantToken;
import tokens.NullToken;
import tokens.NumberToken;
import tokens.Token;
import tokens.FloatingLiteralToken;
import tokens.CharacterToken;
import tokens.StringToken;

import static lexicalAnalyzer.PunctuatorScanningAids.*;

public class LexicalAnalyzer extends ScannerImp implements Scanner {
	public static LexicalAnalyzer make(String filename) {
		InputHandler handler = InputHandler.fromFilename(filename);
		PushbackCharStream charStream = PushbackCharStream.make(handler);
		return new LexicalAnalyzer(charStream);
	}

	public LexicalAnalyzer(PushbackCharStream input) {
		super(input);
	}
	
	private LocatedChar chPrev = null;
	private LocatedChar ch = null;

	
	//////////////////////////////////////////////////////////////////////////////
	// Token-finding main dispatch	

	@Override
	protected Token findNextToken() {
		LocatedChar ch = nextNonWhitespaceChar();
		if ((ch.getCharacter() == '-' || ch.getCharacter() == '+') && (this.chPrev != null && (this.chPrev.getCharacter() == ':') || (this.chPrev.getCharacter() == '+')|| (this.chPrev.getCharacter() == '-')|| (this.chPrev.getCharacter() == '/')|| (this.chPrev.getCharacter() == '*')|| (this.chPrev.getCharacter() == '>')|| (this.chPrev.getCharacter() == '<')|| (this.chPrev.getCharacter() == '=') || (this.chPrev.getCharacter() == '!') || (this.chPrev.getCharacter() == '&') || (this.chPrev.getCharacter() == '|'))) {
			return unaryScan(ch);
		}
		else if(ch.isDigit()) { 
			return scanNumber(ch);
		}
		else if(ch.isChar('\'') || ch.isChar('%')) { 
			return scanChar(ch);
		}
		else if(ch.isChar('"')) { 
			return scanString(ch);
		}
		else if(ch.isChar('#')) { 
			ignoreComment(ch);
			return findNextToken();
		}
		else if(ch.isLowerCase() || ch.isChar('@') || ch.isChar('_')) {
			return scanIdentifier(ch);
		}
		else if(isPunctuatorStart(ch)) {
			return PunctuatorScanner.scan(ch, input);
		}
		else if(isEndOfInput(ch)) {
			return NullToken.make(ch);
		}
		else {
			lexicalError(ch);
			return findNextToken();
		}
	}


	private LocatedChar nextNonWhitespaceChar() {
		if(this.ch != null) {
			this.chPrev = ch;
		}
		this.ch = input.next();
		while(this.ch.isWhitespace()) {
			this.ch = input.next();
		}
		return ch;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	// Comment lexical analysis	
		
	private void ignoreComment(LocatedChar firstChar) {
		LocatedChar c = input.next();
		while(!(c.isChar('#') || c.isChar('\n'))) {
			c = input.next();
		}
	}
	//////////////////////////////////////////////////////////////////////////////
	// Char lexical analysis	
	
	private Token scanChar(LocatedChar firstChar) {
		StringBuffer buffer = new StringBuffer();
		
		if(firstChar.getCharacter() == '%') {
			
			appendSubsequentDigits(buffer);

			//convert octal ascii to char
			String bufferString = buffer.toString();
			if(bufferString.length() < 1 || bufferString.length() > 3) {
				lexicalError("Invalid octal form", firstChar);
				return findNextToken();
			}
			
			int decimalValue = 0;
			try {
				decimalValue = Integer.parseInt(bufferString, 8);
			} catch(NumberFormatException e) {
				TanLogger log = TanLogger.getLogger("compiler.CharToken");
				log.severe("Input char value is too large ");
			}
			
			if(decimalValue < 0 || decimalValue > 127) {
				TanLogger log = TanLogger.getLogger("compiler.CharToken");
				log.severe("Input char value is too large ");
			}
			
			char converted = (char)decimalValue;
			LocatedChar newChar = new LocatedChar(converted, firstChar.getLocation());
			
			return CharacterToken.make(newChar, Character.toString(converted));
			
		}
		else {
			LocatedChar c = input.next();
			char ch = c.getCharacter();
			if(!(ch >= 32 && ch <= 126)) {
				lexicalError("Invalid character", c);
				return findNextToken();
			}
		
			buffer.append(ch);
			
			LocatedChar end = input.next();
			if(end.getCharacter() != '\'') {
				lexicalError("Invalid character", end);
				return findNextToken();
			}
			
			LocatedChar sc = input.next();
			input.pushback(sc);
			return CharacterToken.make(c, buffer.toString());

			
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////
	// String lexical analysis	
	
	private Token scanString(LocatedChar firstChar) {
		
		StringBuffer buffer = new StringBuffer();
		
		LocatedChar chars = input.next();
		
		while(!chars.isChar('"') && !isEndOfInput(chars) && !chars.isChar('\n')) {
			buffer.append(chars.getCharacter());
			chars = input.next();
		}
		
		if(isEndOfInput(chars) || chars.isChar('\n')) {
			lexicalError("No terminator for string", firstChar);
			return findNextToken();
		}
		
		if(chars.isChar('"')){
			chars = input.next();
			input.pushback(chars);
			
			return StringToken.make(firstChar, buffer.toString());
		}
		else {
			lexicalError("Incorrect string format", chars);
			return findNextToken();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////
	// Integer lexical analysis	
	

	private Token scanNumber(LocatedChar firstChar) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(firstChar.getCharacter());
		appendSubsequentDigits(buffer);
		
		//check if next character is a decimal point
		return scanDecimal(firstChar, buffer);
		
		
	}
	
	
	
	private Token unaryScan(LocatedChar firstChar) {
		StringBuffer buffer = new StringBuffer();
		
		
//		if(firstChar.getCharacter() == '&') {
//			if(input.peek().getCharacter() == '&') {
//				buffer.append(firstChar.getCharacter());
//				buffer.append(input.next().getCharacter());
//			}
//		}
		
		if(firstChar.getCharacter() == '-' || firstChar.getCharacter() == '+' || firstChar.getCharacter() == '!') {
			buffer.append(firstChar.getCharacter());
			
			LocatedChar decimal = input.next();
			
			if(!decimal.isDigit()) {	
				lexicalError("incorrect unary operator", firstChar);
				return findNextToken();
			} else {
				buffer.append(decimal.getCharacter());
			}
			
		} else {
			buffer.append(firstChar.getCharacter());
			appendSubsequentDigits(buffer);
		}
		
		
		//check if next character is a decimal point
		
		return scanDecimal(firstChar, buffer);
		
	}
	
	
	private Token scanDecimal(LocatedChar firstChar, StringBuffer buffer) {
		if(input.peek().getCharacter() == '.') {
			LocatedChar decimal = input.next();
			//input.next();
			if(!decimal.isDigit() && decimal.getCharacter() != '.') {	
				lexicalError("Malformed floating-point literal", decimal);
				return findNextToken();
			}
			buffer.append(decimal.getCharacter());
			appendSubsequentDigits(buffer);
			
			LocatedChar next = input.peek(); //changed to .next it was .peek
			if(next.getCharacter() == 'e' || next.getCharacter() == 'E') {
				buffer.append(next.getCharacter());
				appendSubsequentDigits(buffer);
				input.next();
				
				LocatedChar num = input.peek();
				//check if a number after e exist
				if(num.getCharacter() =='-' || num.getCharacter() == '+') {
					buffer.append(num.getCharacter());
					appendSubsequentDigits(buffer);
					
					input.next();
					
					//check if a number after e exist
					appendSubsequentDigits(buffer);
					
				} else if(num.isDigit()) {
					appendSubsequentDigits(buffer);
					
				} else {
					lexicalError("Malformed floating-point literal", num);
					return findNextToken();
				}
				
				
			}
			
			return FloatingLiteralToken.make(firstChar, buffer.toString());
			
		} else {
			return NumberToken.make(firstChar, buffer.toString());
		}

	}
	
	
	private void appendSubsequentDigits(StringBuffer buffer) {
		LocatedChar c = input.next();
		while(c.isDigit()) {
			buffer.append(c.getCharacter());
			c = input.next();
		}
		input.pushback(c);
	}

	
	
	//////////////////////////////////////////////////////////////////////////////
	// Identifier and keyword lexical analysis	

	private Token scanIdentifier(LocatedChar firstChar) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(firstChar.getCharacter());
		appendSubsequentCharacters(buffer);

		String lexeme = buffer.toString();
		if(Keyword.isAKeyword(lexeme)) {
			return LextantToken.make(firstChar, lexeme, Keyword.forLexeme(lexeme));
		}
		else {
			return IdentifierToken.make(firstChar, lexeme);
		}
	}
	private void appendSubsequentLowercase(StringBuffer buffer) {
		LocatedChar c = input.next();
		while(c.isLowerCase()) {
			buffer.append(c.getCharacter());
			c = input.next();
		}
		input.pushback(c);
	}
	
	private void appendSubsequentCharacters(StringBuffer buffer) {
		LocatedChar c = input.next();
		
		while(Character.isLetterOrDigit(c.getCharacter()) || c.isChar('@') || c.isChar('_')) {
			buffer.append(c.getCharacter());
			c = input.next();
		}
		input.pushback(c);
	}
	
	
	//////////////////////////////////////////////////////////////////////////////
	// Punctuator lexical analysis	
	// old method left in to show a simple scanning method.
	// current method is the algorithm object PunctuatorScanner.java

	@SuppressWarnings("unused")
	private Token oldScanPunctuator(LocatedChar ch) {
		
		switch(ch.getCharacter()) {
		case '*':
			return LextantToken.make(ch, "*", Punctuator.MULTIPLY);
		case '+':
			return LextantToken.make(ch, "+", Punctuator.ADD);
		case '>':
			return LextantToken.make(ch, ">", Punctuator.GREATER);
		case ':':
			if(ch.getCharacter()=='=') {
				return LextantToken.make(ch, ":=", Punctuator.ASSIGN);
			}
			else {
				lexicalError(ch);
				return(NullToken.make(ch));
			}
		case ',':
			return LextantToken.make(ch, ",", Punctuator.PRINT_SEPARATOR);
		case ';':
			return LextantToken.make(ch, ";", Punctuator.TERMINATOR);
		default:
			lexicalError(ch);
			return(NullToken.make(ch));
		}
	}

	

	//////////////////////////////////////////////////////////////////////////////
	// Character-classification routines specific to tan scanning.	

	private boolean isPunctuatorStart(LocatedChar lc) {
		char c = lc.getCharacter();
		return isPunctuatorStartingCharacter(c);
	}

	private boolean isEndOfInput(LocatedChar lc) {
		return lc == LocatedCharStream.FLAG_END_OF_INPUT;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////
	// Error-reporting	

	private void lexicalError(LocatedChar ch) {
		TanLogger log = TanLogger.getLogger("compiler.lexicalAnalyzer");
		log.severe("Lexical error: invalid character " + ch);
	}
	
	private void lexicalError(String string, LocatedChar decimal) {
		TanLogger log = TanLogger.getLogger("compiler.lexicalAnalyzer");
		log.severe("Lexical error: " + string + " " + decimal);
		
	}

	
}
