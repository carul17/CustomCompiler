package parser;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import logging.TanLogger;
import parseTree.*;
import parseTree.nodeTypes.*;
import semanticAnalyzer.types.PrimitiveType;
import symbolTable.Binding;
import tokens.*;
import lexicalAnalyzer.Keyword;
import lexicalAnalyzer.Lextant;
import lexicalAnalyzer.Punctuator;
import lexicalAnalyzer.Scanner;


public class Parser {
	private Scanner scanner;
	private Token nowReading;
	private Token previouslyRead;
	
	public static ParseNode parse(Scanner scanner) {
		Parser parser = new Parser(scanner);
		return parser.parse();
	}
	public Parser(Scanner scanner) {
		super();
		this.scanner = scanner;
	}
	
	public ParseNode parse() {
		readToken();
		return parseProgram();
	}

	////////////////////////////////////////////////////////////
	// "program" is the start symbol S
	// S -> MAIN mainBlock
	
	private ParseNode parseProgram() { //here
		if(!startsProgram(nowReading)) {
			return syntaxErrorNode("program");
		}
		ParseNode program = new ProgramNode(nowReading);
		
		while(startsFunctionsDefinition(nowReading)) {
			ParseNode globalDefinition = parseFucntionDefinition();
			program.appendChild(globalDefinition);
		}
		
		expect(Keyword.MAIN);
		ParseNode mainBlock = parseMainBlock();
		program.appendChild(mainBlock);
		
		if(!(nowReading instanceof NullToken) && nowReading.getLexeme() != ":=") {
			return syntaxErrorNode("end of program"); //crashing here!
		}
		
		return program;
	}
	
	//defining functions
	private ParseNode parseFucntionDefinition() {
		assert startsFunctionsDefinition(nowReading);
		Token token = nowReading;
		expect(Keyword.SUBR);
		ParseNode type = pasrseTypeFunctions();
		ParseNode identifier = parseIdentifier();
		expect(Punctuator.OPEN_ROUND_BRACE);
		ParseNode parameterList = parseParamterList();
		expect(Punctuator.CLOSE_ROUND_BRACE);
		ParseNode blockStatement = parseBlockStatement();
		return FunctionDefinitionNode.make(token, type, identifier, parameterList, blockStatement);
	}
	
	private ParseNode parseParamterList() {
		ParseNode parameterList = new ParameterListNode(nowReading);
		while(startsParameter(nowReading)) {
			ParseNode parameter = parseParameter();
			parameterList.appendChild(parameter);
		}
		return parameterList;
	}
	private ParseNode parseParameter() {
		Token token = nowReading;
		ParseNode type = pasrseTypeFunctions();
		ParseNode identifier = parseIdentifier();
		return ParameterNode.make(token, type, identifier);
	}
	private boolean startsParameter(Token token) {
		return startNotVoidType(token);
	}
	private boolean startNotVoidType(Token token) {
		return token.isLextant(Keyword.BOOL, Keyword.CHAR, Keyword.INT, Keyword.FLOAT);
	}
	private boolean startsFunctionsDefinition(Token token) {
		return token.isLextant(Keyword.SUBR);
	}
	
	private boolean startsProgram(Token token) {
		return startsFunctionsDefinition(token) || token.isLextant(Keyword.MAIN);
	}
	
	private ParseNode pasrseTypeFunctions() {
		if(!startsType(nowReading)) {
			return syntaxErrorNode("type");
		}
		ParseNode node = new FunctionTypeNode(nowReading);
		node.setType(PrimitiveType.VOID); //need to fix this
		readToken();
		return node;
	}
	
	
	///////////////////////////////////////////////////////////
	// mainBlock
	
	// mainBlock -> { statement* }
	private ParseNode parseMainBlock() {
		if(!startsMainBlock(nowReading)) {
			return syntaxErrorNode("mainBlock");
		}
		ParseNode mainBlock = new MainBlockNode(nowReading);
		expect(Punctuator.OPEN_BRACE);
		while(startsStatement(nowReading)) {
			ParseNode statement = parseStatement();
			mainBlock.appendChild(statement);
		};
		expect(Punctuator.CLOSE_BRACE); //crashing here
		return mainBlock;
	}
	private boolean startsMainBlock(Token token) {
		return token.isLextant(Punctuator.OPEN_BRACE);
	}
	
	private ParseNode parseBlockStatement() {
		if(!startsBlockStatement(nowReading)) {
			return syntaxErrorNode("BlockStatement");
		}
		ParseNode BlockStatement = new BlockStatementNode(nowReading);
		expect(Punctuator.OPEN_BRACE);
		while(startsStatement(nowReading)) {
			ParseNode statement = parseStatement();
			BlockStatement.appendChild(statement);
		};
		expect(Punctuator.CLOSE_BRACE); //crashing here
		return BlockStatement;
	}
	private boolean startsBlockStatement(Token token) {
		return token.isLextant(Punctuator.OPEN_BRACE);
	}
	
	
	///////////////////////////////////////////////////////////
	// statements
	
	// statement-> declaration | printStmt
	private ParseNode parseStatement() {
		
		
		if(!startsStatement(nowReading)) {
			return syntaxErrorNode("statement");
		}
		
		if(startsBreak(nowReading)) {
			System.out.println("hello");
			return parseBreak();
		}
			
		if(startsDeclaration(nowReading)) {
			return parseDeclaration();
		}
		
		if(startsAssignment(nowReading)) {
			return parseAssignment();
		}
		
		
		if(startsPrintStatement(nowReading)) {
			return parsePrintStatement();
		}
		
		if(startsCallStatement(nowReading)) {
			return parseCallStatement();
		}
		
		if(startsBlockStatement(nowReading)) {
			return parseBlockStatement();
		}
		if(startsIfStatement(nowReading)) {
			return parseIfStatement();
		}
		if(startsWhile(nowReading)) {
			return parseWhile();
		}
		
		return syntaxErrorNode("statement");
	}
	
	private ParseNode parseCallStatement() {
		assert startsCallStatement(nowReading);
		Token token = nowReading;
		expect(Keyword.CALL);
		ParseNode identifier = parseIdentifier();
		expect(Punctuator.OPEN_ROUND_BRACE);
		ParseNode expresstionList = parseExpressionList();
		expect(Punctuator.CLOSE_ROUND_BRACE);
		expect(Punctuator.TERMINATOR);
		return CallStatementNode.make(token, identifier, expresstionList);
	}
	
	private ParseNode parseExpressionList() {
		ParseNode expressionList = new ExpressionListNode(nowReading);
		while(startsExpression(nowReading)) {
			ParseNode expression = parseExpression();
			expressionList.appendChild(expression);
			if(nowReading.isLextant(Punctuator.COMMA)) {
				readToken();
			}
		}
		return expressionList;
	}
	private boolean startsStatement(Token token) {
		return startsPrintStatement(token) ||
			   startsDeclaration(token) ||
			   startsAssignment(token) ||
			   startsBlockStatement(token) ||
			   startsIfStatement(token) ||
			   startsWhile(token) ||
			   startsBreak(token)||
			   startsCallStatement(token);
	}
	
	private boolean startsCallStatement(Token token) {
		return token.isLextant(Keyword.CALL);
	}
	// printStmt -> PRINT printExpressionList TERMINATOR
	private ParseNode parsePrintStatement() {
		if(!startsPrintStatement(nowReading)) {
			return syntaxErrorNode("print statement");
		}
		ParseNode result = new PrintStatementNode(nowReading);
		
		readToken();
		result = parsePrintExpressionList(result);
		
		expect(Punctuator.TERMINATOR);
		return result;
	}
	private boolean startsPrintStatement(Token token) {
		return token.isLextant(Keyword.PRINT);
	}	

	// This adds the printExpressions it parses to the children of the given parent
	// printExpressionList -> printSeparator* (expression printSeparator+)* expression? (note that this is nullable)

	private ParseNode parsePrintExpressionList(ParseNode parent) {
		if(!startsPrintExpressionList(nowReading)) {
			return syntaxErrorNode("printExpressionList");
		}
		
		while(startsPrintSeparator(nowReading)) {
			parsePrintSeparator(parent);
		}
		while(startsExpression(nowReading)) {
			parent.appendChild(parseExpression());
			if(nowReading.isLextant(Punctuator.TERMINATOR)) {
				return parent;
			}
			do {
				parsePrintSeparator(parent);
			} while(startsPrintSeparator(nowReading));
		}
		return parent;
	}	
	private boolean startsPrintExpressionList(Token token) {
		return startsExpression(token) || startsPrintSeparator(token) || token.isLextant(Punctuator.TERMINATOR);
	}

	
	// This adds the printSeparator it parses to the children of the given parent
	// printSeparator -> PRINT_SEPARATOR | PRINT_SPACE | PRINT_NEWLINE | PRINT_TAB
	
	private void parsePrintSeparator(ParseNode parent) {
		if(!startsPrintSeparator(nowReading)) {
			ParseNode child = syntaxErrorNode("print separator");
			parent.appendChild(child);
			return;
		}
		
		if(nowReading.isLextant(Punctuator.PRINT_NEWLINE)) {
			readToken();
			ParseNode child = new NewlineNode(previouslyRead);
			parent.appendChild(child);
		}		
		else if(nowReading.isLextant(Punctuator.PRINT_SPACE)) {
			readToken();
			ParseNode child = new SpaceNode(previouslyRead);
			parent.appendChild(child);
		}
		else if(nowReading.isLextant(Punctuator.PRINT_TAB)) {
			readToken();
			ParseNode child = new TabNode(previouslyRead);
			parent.appendChild(child);
		}
		else if(nowReading.isLextant(Punctuator.PRINT_SEPARATOR)) {
			readToken();
		} 
	}
	private boolean startsPrintSeparator(Token token) {
		return token.isLextant(Punctuator.PRINT_SEPARATOR, Punctuator.PRINT_SPACE, Punctuator.PRINT_NEWLINE, Punctuator.PRINT_TAB);
	}
	
	
	// declaration -> CONST identifier := expression TERMINATOR
	private ParseNode parseDeclaration() {
		if(!startsDeclaration(nowReading)) {
			return syntaxErrorNode("declaration");
		}
		Token declarationToken = nowReading;
		readToken();
		
		ParseNode identifier = parseIdentifier();
		
		expect(Punctuator.ASSIGN);
		ParseNode initializer = parseExpression();
		expect(Punctuator.TERMINATOR);
		
		return DeclarationNode.withChildren(declarationToken, identifier, initializer);
	}
	
	private boolean startsDeclaration(Token token) {
		return token.isLextant(Keyword.CONST) || token.isLextant(Keyword.VAR);
	}
	
	//assignment
	private ParseNode parseAssignment() {
		if(!startsAssignment(nowReading)) {
			return syntaxErrorNode("assignment");
		}
		Token identifierToken = nowReading;
		
		ParseNode identifier = parseIdentifier();
		expect(Punctuator.ASSIGN);
		ParseNode expression = parseExpression();
		expect(Punctuator.TERMINATOR);
		
		return AssignmentStatementNode.withChildren(identifierToken, identifier, expression);
	}
	
	private ParseNode parseBreak() {
		if(!startsBreak(nowReading)) {
			return syntaxErrorNode("break");
		}
		
		
		Token breakToken = nowReading;
		readToken();
		expect(Punctuator.TERMINATOR);
		return BreakNode.withChildren(breakToken, new BreakNode(breakToken));
	}
	
	private boolean startsAssignment(Token token) {
		return startsIdentifier(token); //could be start of assignment
	}
	
	private boolean startsBreak(Token token) {
		return token.isLextant(Keyword.BREAK);
	}
	//if statement
	private ParseNode parseIfStatement() {
		if(!startsIfStatement(nowReading)) {
			return syntaxErrorNode("if statement");
		}
		Token ifToken = nowReading;
		readToken();
		
		ParseNode condition = parseBracketExpression();
		ParseNode block = parseBlockStatement();
		if(nowReading.isLextant(Keyword.ELSE)) {
			readToken();
			ParseNode elseParseNode = parseBlockStatement();
			return IfStatementNode.withChildren(ifToken, condition, block, elseParseNode);
		}
		
		return IfStatementNode.withChildren(ifToken, condition, block);
		
	}
	private boolean startsIfStatement(Token token) {
		return token.isLextant(Keyword.IF);
	}
	
	private ParseNode parseWhile() {
		if(!startsWhile(nowReading)) {
			return syntaxErrorNode("while statement");
		}
		Token whileToken = nowReading;
		readToken();
		
		ParseNode condition = parseBracketExpression();
		ParseNode block = parseBlockStatement();
		
		return WhileNode.withChildren(whileToken, condition, block);
		
	}
	private boolean startsWhile(Token token) {
		return token.isLextant(Keyword.WHILE);
	}



	
	///////////////////////////////////////////////////////////
	// expressions
	// expr                     -> comparisonExpression
	// comparisonExpression     -> additiveExpression [> additiveExpression]?
	// additiveExpression       -> multiplicativeExpression [+ multiplicativeExpression]*  (left-assoc)
	// multiplicativeExpression -> atomicExpression [MULT atomicExpression]*  (left-assoc)
	// atomicExpression         -> unaryExpression | literal
	// unaryExpression			-> UNARYOP atomicExpression
	// literal                  -> intNumber | identifier | booleanConstant

	// expr  -> comparisonExpression
	private ParseNode parseExpression() {		
		if(!startsExpression(nowReading)) {
			return syntaxErrorNode("expression");
		}
		return parseAndOrExpression();
	}
	private boolean startsExpression(Token token) {
		return startsAndOrExpression(token);
	}
	
	private ParseNode parseAndOrExpression() {
		if(!startsAndOrExpression(nowReading)) {
			return syntaxErrorNode("and, or expression");
		}
		ParseNode left = parseComparisonExpression();
		if(nowReading.isLextant(Punctuator.AND) || nowReading.isLextant(Punctuator.OR)) {
			Token compareToken = nowReading;
			readToken();
			ParseNode right = parseComparisonExpression();
			return OperatorNode.withChildren(compareToken, left, right);
		}
		return left;
	}
	
	private boolean startsAndOrExpression(Token token) {
		return startsComparisonExpression(token);
	}
	

	// comparisonExpression -> additiveExpression [> additiveExpression]?
	private ParseNode parseComparisonExpression() {
		if(!startsComparisonExpression(nowReading)) {
			return syntaxErrorNode("comparison expression");
		}
		
		ParseNode left = parseAdditiveExpression();
		if(nowReading.isLextant(Punctuator.GREATER) 
				|| nowReading.isLextant(Punctuator.LESS)
				|| nowReading.isLextant(Punctuator.LESSEREQUAL)
				|| nowReading.isLextant(Punctuator.GREATEREQUAL)
				|| nowReading.isLextant(Punctuator.EQUAL)
				|| nowReading.isLextant(Punctuator.NOTEQUAL)
				) {
			Token compareToken = nowReading;
			readToken();
			ParseNode right = parseAdditiveExpression();
			
			return OperatorNode.withChildren(compareToken, left, right);
		}
		return left;

	}
	private boolean startsComparisonExpression(Token token) {
		return startsAdditiveExpression(token);
	}

	// additiveExpression -> multiplicativeExpression [+ multiplicativeExpression]*  (left-assoc)
	private ParseNode parseAdditiveExpression() {
		if(!startsAdditiveExpression(nowReading)) {
			return syntaxErrorNode("additiveExpression");
		}
		
		ParseNode left = parseMultiplicativeExpression();
		while(nowReading.isLextant(Punctuator.ADD)) {
			Token additiveToken = nowReading;
			readToken();
			ParseNode right = parseMultiplicativeExpression();
			
			left = OperatorNode.withChildren(additiveToken, left, right);
		}
		
		while(nowReading.isLextant(Punctuator.SUBTRACT)) {
			Token subtractionToken = nowReading;
			readToken();
			ParseNode right = parseMultiplicativeExpression();
			
			left = OperatorNode.withChildren(subtractionToken, left, right);
		}
		
		
		
		
		return left;
	}
	private boolean startsAdditiveExpression(Token token) {
		return startsMultiplicativeExpression(token);
	}	

	// multiplicativeExpression -> atomicExpression [MULT atomicExpression]*  (left-assoc)
	private ParseNode parseMultiplicativeExpression() {
		if(!startsMultiplicativeExpression(nowReading)) {
			return syntaxErrorNode("multiplicativeExpression");
		}
		
		ParseNode left = parseAtomicExpression();
		while(nowReading.isLextant(Punctuator.MULTIPLY)) {
			Token multiplicativeToken = nowReading;
			readToken();
			ParseNode right = parseAtomicExpression();
			
			left = OperatorNode.withChildren(multiplicativeToken, left, right);
		}
		
		while(nowReading.isLextant(Punctuator.DIVIDE)) {
			Token subtractionToken = nowReading;
			readToken();
			ParseNode right = parseMultiplicativeExpression();
			
			left = OperatorNode.withChildren(subtractionToken, left, right);
		}
		
		
		return left;
	}
	private boolean startsMultiplicativeExpression(Token token) {
		return startsAtomicExpression(token);
	}
	
	// atomicExpression         -> unaryExpression | literal
	private ParseNode parseAtomicExpression() {
		if(!startsAtomicExpression(nowReading)) {
			return syntaxErrorNode("atomic expression");
		}
		
		
		if(startsUnaryExpression(nowReading)) {
			return parseUnaryExpression();
		}
		if(startsBracketExpression(nowReading)) {
			return parseBracketExpression();
		}
		if(startsCast(nowReading)) {
			return parseCast();
		}
		if(startsArray(nowReading)) {
			return parseArray();
		}
		
		return parseLiteral();
	}
	private boolean startsAtomicExpression(Token token) {
		return startsLiteral(token) 
				|| startsUnaryExpression(token)
				|| startsBracketExpression(token)
				|| startsCast(token)
				|| startsArray(token);
	}
	
	
	
	private ParseNode parseArray() {
		
		if(!startsArray(nowReading)) {
			return syntaxErrorNode("array expression");
		}
		Token arrayToken = nowReading;
		if(arrayToken.isLextant(Keyword.NEW)) {
			readToken();
			expect(Punctuator.OPEN_SQUARE_BRACE);
			ParseNode type = parseType();
			expect(Punctuator.CLOSE_SQUARE_BRACE);
			expect(Punctuator.OPEN_ROUND_BRACE);
			ParseNode size = parseExpression();
			expect(Punctuator.CLOSE_ROUND_BRACE);
			return OperatorNode.withChildren(arrayToken, type, size);
		}
		else {
			expect(Punctuator.OPEN_SQUARE_BRACE);
			List<ParseNode> elements = new ArrayList<>();
			while(!(nowReading instanceof NullToken) || !(nowReading.isLextant(Punctuator.CLOSE_SQUARE_BRACE))){
				ParseNode expr = parseExpression();
				elements.add(expr);
				
				if(nowReading.isLextant(Punctuator.CLOSE_SQUARE_BRACE)) {
					break;
				}
				if(nowReading.isLextant(Punctuator.INDEXING)) {
					Token indexingToken = LextantToken.make(arrayToken, ":", Punctuator.INDEXING);
					readToken();
					ParseNode index = parseExpression();
					expect(Punctuator.CLOSE_SQUARE_BRACE);
					return OperatorNode.withChildren(indexingToken, expr, index);
				}
				expect(Punctuator.COMMA);
			}
			expect(Punctuator.CLOSE_SQUARE_BRACE);
			return ArrayNode.withChildren(arrayToken, elements);
		}
	}
	
	private boolean startsArray(Token token) {
		return token.isLextant(Punctuator.OPEN_SQUARE_BRACE) || token.isLextant(Keyword.NEW);
	}
	
	private ParseNode parseCast() {
		if(!startsCast(nowReading)) {
			return syntaxErrorNode("cast expression");
		}
		Token castToken = LextantToken.make(nowReading,"<>()", Punctuator.CAST);
		readToken();
		ParseNode typeNode = parseType();
		readToken();
		ParseNode expr = parseBracketExpression();
		
		return OperatorNode.withChildren(castToken, typeNode, expr);
	}
	
	private boolean startsCast(Token token) {
		return token.isLextant(Punctuator.LESS);
	}
	
	private ParseNode parseType() {
		if(!startsType(nowReading)) {
			return syntaxErrorNode("type expression");
		}
		readToken();
		return new TypeNode(previouslyRead);
	}
	
	private boolean startsType(Token token) {
		return startNotVoidType(token) || token.isLextant(Keyword.VOID);
	}

	// unaryExpression			-> UNARYOP atomicExpression
	private ParseNode parseUnaryExpression() {
		if(!startsUnaryExpression(nowReading)) {
			return syntaxErrorNode("unary expression");
		}
		Token operatorToken = nowReading;
		readToken();
		ParseNode child = parseAtomicExpression();
		
		return OperatorNode.withChildren(operatorToken, child);
	}
	private boolean startsUnaryExpression(Token token) {
		return token.isLextant(Punctuator.SUBTRACT, Punctuator.ADD, Punctuator.NOT, Keyword.LENGTH);
	}
	
	// bracket expresssion 
	private ParseNode parseBracketExpression() {
		if(!startsBracketExpression(nowReading)) {
			return syntaxErrorNode("bracket expression");
		}
		expect(Punctuator.OPEN_ROUND_BRACE);
		ParseNode expr = parseExpression();
		expect(Punctuator.CLOSE_ROUND_BRACE);
		
		return expr;
	}
	private boolean startsBracketExpression(Token token) {
		return token.isLextant(Punctuator.OPEN_ROUND_BRACE);
	}
	
	
	
	// literal -> number | identifier | booleanConstant
	private ParseNode parseLiteral() {
		if(!startsLiteral(nowReading)) {
			return syntaxErrorNode("literal");
		}
		
		if(startsIntLiteral(nowReading)) {
			return parseIntLiteral();
		}
		if(startsFloatLiteral(nowReading)) {
			return parseFloatLiteral();
		}
		if(startsCharLiteral(nowReading)) {
			return parseCharLiteral();
		}
		if(startsStringLiteral(nowReading)) {
			return parseStringLiteral();
		}
		if(startsIdentifier(nowReading)) {
			return parseIdentifier();
		}
		if(startsBooleanLiteral(nowReading)) {
			return parseBooleanLiteral();
		}

		return syntaxErrorNode("literal");
	}
	private boolean startsLiteral(Token token) {
		return startsIntLiteral(token)
				|| startsIdentifier(token)
				|| startsBooleanLiteral(token)
				|| startsFloatLiteral(token)
				|| startsCharLiteral(token)
				|| startsStringLiteral(token);
	}

	// number (literal)
	private ParseNode parseIntLiteral() {
		if(!startsIntLiteral(nowReading)) {
			return syntaxErrorNode("integer constant");
		}
		readToken();
		return new IntegerConstantNode(previouslyRead);
	}
	
	private boolean startsIntLiteral(Token token) {
		return token instanceof NumberToken;
	}
	//float
	private ParseNode parseFloatLiteral() {
		if(!startsFloatLiteral(nowReading)) {
			return syntaxErrorNode("float constant");
		}
		readToken();
		return new FloatingConstantNode(previouslyRead);
	}
	
	private boolean startsFloatLiteral(Token token) {
		return token instanceof FloatingLiteralToken;
	}
	
	//char
		private ParseNode parseCharLiteral() {
			if(!startsCharLiteral(nowReading)) {
				return syntaxErrorNode("char");
			}
			readToken();
			return new CharacterNode(previouslyRead);
		}
		
		private boolean startsCharLiteral(Token token) {
			return token instanceof CharacterToken;
		}
		
	//string
		private ParseNode parseStringLiteral() {
			if(!startsStringLiteral(nowReading)) {
				return syntaxErrorNode("string");
			}
			readToken();
			return new StringNode(previouslyRead);
		}
		
		private boolean startsStringLiteral(Token token) {
			return token instanceof StringToken;
		}
	
	// identifier (terminal)
	private ParseNode parseIdentifier() {
		if(!startsIdentifier(nowReading)) {
			return syntaxErrorNode("identifier");
		}
		readToken();
		return new IdentifierNode(previouslyRead);
	}
	private boolean startsIdentifier(Token token) {
		return token instanceof IdentifierToken;
	}

	// boolean literal
	private ParseNode parseBooleanLiteral() {
		if(!startsBooleanLiteral(nowReading)) {
			return syntaxErrorNode("boolean constant");
		}
		readToken();
		return new BooleanConstantNode(previouslyRead);
	}
	private boolean startsBooleanLiteral(Token token) {
		return token.isLextant(Keyword.TRUE, Keyword.FALSE);
	}

	private void readToken() {
		previouslyRead = nowReading;
		nowReading = scanner.next();
		//System.out.println(nowReading);
	}	
	
	// if the current token is one of the given lextants, read the next token.
	// otherwise, give a syntax error and read next token (to avoid endless looping).
	private void expect(Lextant ...lextants ) {
		if(!nowReading.isLextant(lextants)) {
			if(!previouslyRead.getLexeme().equals(";")) {
				syntaxError(nowReading, "expecting " + Arrays.toString(lextants));
			}
		}
		readToken();
	}	
	private ErrorNode syntaxErrorNode(String expectedSymbol) {
		syntaxError(nowReading, "expecting " + expectedSymbol);
		ErrorNode errorNode = new ErrorNode(nowReading);
		readToken();
		return errorNode;
	}
	private void syntaxError(Token token, String errorDescription) {
		String message = "" + token.getLocation() + " " + errorDescription;
		error(message);
	}
	private void error(String message) {
		TanLogger log = TanLogger.getLogger("compiler.Parser");
		log.severe("syntax error: " + message);
	}	
}

