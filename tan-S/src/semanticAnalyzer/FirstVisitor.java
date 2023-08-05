package semanticAnalyzer;

import parseTree.ParseNode;
import semanticAnalyzer.SemanticAnalysisVisitor;
import parseTree.ParseNodeVisitor;
import parseTree.nodeTypes.*;
import semanticAnalyzer.types.FunctionType;
import semanticAnalyzer.types.PrimitiveType;
import semanticAnalyzer.types.Type;
import semanticAnalyzer.signatures.FunctionSignature;
import symbolTable.Binding;
import symbolTable.Scope;
import semanticAnalyzer.SemanticAnalyzer;
import tokens.Token;
import java.util.List;

import asmCodeGenerator.codeStorage.ASMOpcode;

import java.util.ArrayList;

public class FirstVisitor extends ParseNodeVisitor.Default {
	
	@Override
	public void visitEnter(ProgramNode node) {
		enterProgramScope(node);
	}
	
	
	 
	@Override
	public void visitEnter(FunctionDefinitionNode node) {
		
		
		
		
		
	}
	
	@Override
	public void visitLeave(FunctionDefinitionNode node) {
		TypeNode typeNode = (TypeNode) node.child(0);
		IdentifierNode id = (IdentifierNode) node.child(1);
		ParameterListNode pList = (ParameterListNode) node.child(2);
		
		Type returnType = PrimitiveType.getTypeFromString(typeNode.getTypeString());
		Type fType = FunctionType.create(returnType, pList.getTypes());
		FunctionSignature signature = new FunctionSignature(ASMOpcode.Nop, pList.getTypes(), returnType);
		FunctionType funcType = (FunctionType) fType;
		funcType.setReturnType(returnType);
		id.setType(fType);
		node.setType(fType);
		node.setSignature(signature);
		SemanticAnalysisVisitor.addBinding(id, fType, null, -1);
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
