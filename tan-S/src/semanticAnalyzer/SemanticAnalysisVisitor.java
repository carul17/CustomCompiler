package semanticAnalyzer;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import lexicalAnalyzer.Keyword;
import lexicalAnalyzer.Lextant;
import lexicalAnalyzer.Punctuator;
import logging.TanLogger;
import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import parseTree.nodeTypes.*;
import semanticAnalyzer.signatures.FunctionSignature;
import semanticAnalyzer.signatures.FunctionSignatures;
import semanticAnalyzer.signatures.PromotedSignature;
import semanticAnalyzer.types.ArrayType;
import semanticAnalyzer.types.FunctionType;
import semanticAnalyzer.types.PrimitiveType;
import semanticAnalyzer.types.Type;
import symbolTable.Binding;
import symbolTable.Binding.Constancy;
import symbolTable.Scope;
import tokens.LextantToken;
import tokens.Token;

public class SemanticAnalysisVisitor extends ParseNodeVisitor.Default {
	
	@Override
	public void visitLeave(ParseNode node) {
		throw new RuntimeException("Node class unimplemented in SemanticAnalysisVisitor: " + node.getClass());
	}
	


	
	///////////////////////////////////////////////////////////////////////////
	// constructs larger than statements
	@Override
//	public void visitEnter(ProgramNode node) {
//		enterProgramScope(node);
//	}
	public void visitLeave(ProgramNode node) {
		leaveScope(node);
	}
	public void visitEnter(MainBlockNode node) {
	}
	public void visitLeave(MainBlockNode node) {
	}
	public void visitEnter(BlockStatementNode node) {
		if(node.getParent() instanceof FunctionDefinitionNode) {
			Scope paramScope = node.getLocalScope();
			Scope scope = paramScope.createFunctionScope();
			node.setScope(scope);
		} else {
			enterSubscope(node);
		}
	}
	public void visitLeave(BlockStatementNode node) {
		leaveScope(node);
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// helper methods for scoping.
	private void enterProgramScope(ParseNode node) {
		Scope scope = Scope.createProgramScope();
		node.setScope(scope);
	}	
	@SuppressWarnings("unused")
	private void enterSubscope(ParseNode node) {
		Scope baseScope = node.getLocalScope();
		Scope scope = baseScope.createSubscope();
		node.setScope(scope);
	}	
	private void leaveScope(ParseNode node) {
		node.getScope().leave();
	}
	
	///////////////////////////////////////////////////////////////////////////
	// statements and declarations
	@Override
	public void visitLeave(PrintStatementNode node) {
	}
	@Override
	public void visitLeave(IfStatementNode node) {
	}
	@Override
	public void visitLeave(WhileNode node) {
	}
	@Override
	public void visitEnter(ForNode node) {
		//the binding is a member of the for loop subscope
		enterSubscope(node);
	}
	
	public void visitLeave(ForNode node) {
		IdentifierNode identifier = (IdentifierNode) node.child(0);
		ParseNode initializer = node.child(1);
		ParseNode end = node.child(2);
		IdentifierNode endId = (IdentifierNode) node.child(4);
		
		Type type = initializer.getType();
		Type endType = end.getType();
		Constancy constancy = Constancy.IS_CONSTANT;
		if(type != PrimitiveType.INTEGER || endType != PrimitiveType.INTEGER) {
			semanticError("for loop expects integer expressions");
		}
		identifier.setType(type);
		endId.setType(endType);
		node.setType(type);
		
		addBinding(identifier, type, constancy, -1);
		addBinding(endId, endType, constancy, -1);
		
		leaveScope(node);
	}
	
	

	@Override
	public void visitLeave(DeclarationNode node) {
		if(node.child(0) instanceof ErrorNode) {
			node.setType(PrimitiveType.ERROR);
			return;
		}
		
		Constancy constancy = null;
		if (node.getToken().isLextant(Keyword.CONST)) {
			constancy = Constancy.IS_CONSTANT;
		} else {
			constancy = Constancy.IS_VARIABLE;
		}
		
		
		IdentifierNode identifier = (IdentifierNode) node.child(0);
		ParseNode initializer = node.child(1);
		
		Type declarationType = initializer.getType();
		node.setType(declarationType);
		
		identifier.setType(declarationType);
		int numElements = -1;
		if(declarationType instanceof ArrayType) {
			
			numElements = initializer.nChildren();
		}
		addBinding(identifier, declarationType, constancy, numElements);
	}
	
	///////////////FUNCTIONS////////////////////
	
	@Override
	public void visitEnter(FunctionDefinitionNode node) {
		Scope scope = Scope.createParameterScope();
		node.setScope(scope);
	}
	
	@Override
	public void visitLeave(FunctionDefinitionNode node) {
		//Scope scope = node.getScope();
		//scope.updateBindings();
		leaveScope(node);
	}
	
	@Override
	public void visitLeave(ParameterNode node) {
		TypeNode typeNode = (TypeNode) node.child(0);
		IdentifierNode id = (IdentifierNode) node.child(1);
		id.setType(typeNode.getType());
		addBinding(id, typeNode.getType(), null, -1);
		Binding binding = id.getBinding();
		binding.setIsParam(true);
	}
	
	
	@Override
	public void visitLeave(ExpressionListNode node) {
		//hand;e expression list
	}
	
	@Override
	public void visitLeave(CallStatementNode node) {
		//hand;e expression list
	}
	
	@Override
	public void visitLeave(ReturnStatementNode node) {
		//hand;e expression list
	}
	
	
	@Override
	public void visitLeave(AssignmentStatementNode node) {
		if(node.child(0) instanceof ErrorNode) {
			node.setType(PrimitiveType.ERROR);
			return;
		}
		
		IdentifierNode identifier = (IdentifierNode) node.child(0);
		ParseNode expression = node.child(1);
		
		Type identifierType = identifier.getType();
		Type expressionType = expression.getType();
		
		if(!expressionType.equals(identifierType)) {
			semanticError("types don’t match in assignment statement");
			return;
		}
		if(expressionType instanceof ArrayType) {
			identifier.getBinding().setNumElements(expression.nChildren());
		}
		
		if(identifier.getBinding().isConstant()) {
			semanticError("reassignment to const identifier");
		}
	}
	
	@Override
	public void visit(FunctionTypeNode node) {
	}
	
	
	///////////END OF FUNCTIONS//////////////////////////////
	

	///////////////////////////////////////////////////////////////////////////
	// expressions
	@Override
	public void visitLeave(OperatorNode node) {
		List<Type> childTypes;
		if(node.nChildren() == 1) {
			ParseNode child = node.child(0);
			childTypes = Arrays.asList(child.getType());
		}
		else {
			assert node.nChildren() == 2;
			ParseNode left  = node.child(0);
			ParseNode right = node.child(1);
			
			childTypes = Arrays.asList(left.getType(), right.getType());		
		}
	
		Lextant operator = operatorFor(node);
		

		FunctionSignatures signatures = FunctionSignatures.signaturesOf(operator);//might need child parameter
		
		List<PromotedSignature> promotedSignatures = PromotedSignature.promotedSignatures(signatures, childTypes);
		List <List<PromotedSignature>> byNumPromotions = new ArrayList <>();
		
		
		for(int i = 0; i <= 2; i++){
			byNumPromotions.add(new ArrayList<PromotedSignature>());
		}
		
		for(PromotedSignature promotedSignature: promotedSignatures){
			byNumPromotions.get(promotedSignature.numPromotions()).add(promotedSignature);
		}

		PromotedSignature signature = PromotedSignature.nullInstance(); //need to create nullInstance

		for(int i=0; i <= 2; i++){
			//System.out.println(byNumPromotions.get(i).size());
			boolean keepGoing = false; //our flag
			switch(byNumPromotions.get(i).size()) { //how many of our promoted characters have promotions{
				case 0:	keepGoing = true; 
						break;
				case 1:	signature = byNumPromotions.get(i).get(0);
				//found our signature this is what we want	
						break;
				default: //by default we want to be in case 2
					multipleInterpretationError();
					break;	
			}

			if(!keepGoing){
				break;
			}
		}
		
		node.setSignature(signature);
		node.setType(signature.resultType());//changed to concreteType
		
		
		if(signature.accepts(childTypes)) {
			
			node.setSignature(signature);
			
			node.setType(signature.resultType());//changed to concreteType
		}
		else {
			typeCheckError(node, childTypes);
			node.setType(PrimitiveType.ERROR);
		}
	}
	
	
	
	private Lextant operatorFor(OperatorNode node) {
		LextantToken token = (LextantToken) node.getToken();
		return token.getLextant();
	}
	
	@Override
	public void visitLeave(ArrayNode node) {
		//Type type = node.child(0).getType().concreteType();
		Type prevChildType = node.child(0).getType();
		for(ParseNode child : node.getChildren()) {
			
			Type currChildType = child.getType();
			
			if(!(prevChildType.equivalent(currChildType))){
				List<Type> childTypes = Arrays.asList(prevChildType, currChildType);
				typeCheckError(node, childTypes);
				node.setType(PrimitiveType.ERROR);
				
			}
			prevChildType = currChildType;
		}
		
		Type type = node.child(0).getType().concreteType();
		node.setType(new ArrayType(type));
	}


	///////////////////////////////////////////////////////////////////////////
	// simple leaf nodes
	@Override
	public void visit(BooleanConstantNode node) {
		node.setType(PrimitiveType.BOOLEAN);
	}
	@Override
	public void visit(ErrorNode node) {
		node.setType(PrimitiveType.ERROR);
	}
	@Override
	public void visit(IntegerConstantNode node) {
		node.setType(PrimitiveType.INTEGER);
	}
	@Override
	public void visit(FloatingConstantNode node) {
		node.setType(PrimitiveType.FLOATING);
	}
	@Override
	public void visit(CharacterNode node) {
		node.setType(PrimitiveType.CHARACTER);
	}
	@Override
	public void visit(StringNode node) {
		node.setType(PrimitiveType.STRING);
	}
	@Override
	public void visit(NewlineNode node) {
	}
	@Override
	public void visit(SpaceNode node) {
	}
	@Override
	public void visit(TabNode node) {
	}
	@Override
	public void visit(BreakNode node) {
	}
	@Override
	public void visit(ContinueNode node) {
	}
	///////////////////////////////////////////////////////////////////////////
	// IdentifierNodes, with helper methods
	@Override
	public void visit(IdentifierNode node) {
		if(!isBeingDeclared(node)) {
			
			Binding binding = node.findVariableBinding();
			
			node.setType(binding.getType());
			node.setBinding(binding);
		}
		// else parent DeclarationNode does the processing.
	}
	private boolean isBeingDeclared(IdentifierNode node) {
		ParseNode parent = node.getParent();
		
		return (parent instanceof ParameterNode && node == parent.child(1)) || 
				(parent instanceof ForNode && (node == parent.child(0) || node == parent.child(4))) || 
				(parent instanceof DeclarationNode && node == parent.child(0)) ||
				(parent instanceof FunctionDefinitionNode && node == parent.child(1)
				);
	}
	public static void addBinding(IdentifierNode identifierNode, Type type, symbolTable.Binding.Constancy constancy, int numElements) {
		Scope scope = identifierNode.getLocalScope();
		Binding binding = scope.createBinding(identifierNode, type, constancy, numElements);
		identifierNode.setBinding(binding);
	}
	
	///////////////////////////////////////////////////////////////////////////
	// error logging/printing

	private void typeCheckError(ParseNode node, List<Type> operandTypes) {
		Token token = node.getToken();
		
		logError("operator " + token.getLexeme() + " not defined for types " 
				 + operandTypes  + " at " + token.getLocation());	
	}
	private void logError(String message) {
		TanLogger log = TanLogger.getLogger("compiler.semanticAnalyzer");
		log.severe(message);
	}
	
	private void semanticError(String message) {
		TanLogger log = TanLogger.getLogger("compiler.semanticAnalyzer");
		log.severe(message);
		
	}
	private void multipleInterpretationError(){
		logError("multiple interpretations of operator possible");
	}

}