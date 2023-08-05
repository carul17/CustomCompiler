package semanticAnalyzer;

import parseTree.ParseNode;
import semanticAnalyzer.SemanticAnalysisVisitor;
import parseTree.ParseNodeVisitor;
import parseTree.nodeTypes.*;
import semanticAnalyzer.types.FunctionType;
import semanticAnalyzer.types.PrimitiveType;
import semanticAnalyzer.types.Type;
import symbolTable.Binding;
import symbolTable.Scope;
import semanticAnalyzer.SemanticAnalyzer;
import tokens.Token;

public class FirstVisitor extends ParseNodeVisitor.Default {
	
	@Override
	public void visitEnter(ProgramNode node) {
		enterProgramScope(node);
	}
	
	
	 
	@Override
	public void visitEnter(FunctionDefinitionNode node) {
		TypeNode typeNode = (TypeNode) node.child(0);
		IdentifierNode iden = (IdentifierNode) node.child(1);
		ParameterListNode pList = (ParameterListNode) node.child(2);
		Type type = PrimitiveType.getTypeFromString(typeNode.getTypeString());
		Type fType = FunctionType.create(type, pList.getTypes());
		iden.setType(fType);
		SemanticAnalysisVisitor.addBinding(iden, fType, null, -1);
		enterSubscope(node);
	}
	
	@Override
	public void visitLeave(FunctionDefinitionNode node) {
		leaveScope(node);
	}
	
	/*@Override
	public void visitLeave(ParameterListNode node) {
		for(ParseNode child : node.getChildren()) {
			TypeNode typeNode = (TypeNode)child.child(0);
			IdentifierNode id = (IdentifierNode)child.child(1);
			SemanticAnalysisVisitor.addBinding(id, typeNode.getType(), null, -1);
		}
	}*/
	
	@Override
	public void visitLeave(ParameterNode node) {
		TypeNode typeNode = (TypeNode) node.child(0);
		node.setType(typeNode.getType());
	}
	
	@Override
	public void visitLeave(TypeNode node) {
		node.setType(PrimitiveType.getTypeFromString(node.getTypeString()));
	}
	
	
	////// Helpers ///////
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
}
