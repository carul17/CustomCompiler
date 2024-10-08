package parseTree;

import parseTree.nodeTypes.*;

// Visitor pattern with pre- and post-order visits
public interface ParseNodeVisitor {
	
	// non-leaf nodes: visitEnter and visitLeave
	void visitEnter(OperatorNode node);
	void visitLeave(OperatorNode node);
	
	void visitEnter(MainBlockNode node);
	void visitLeave(MainBlockNode node);
	
	void visitEnter(BlockStatementNode node);
	void visitLeave(BlockStatementNode node);

	void visitEnter(DeclarationNode node);
	void visitLeave(DeclarationNode node);
	
	void visitEnter(AssignmentStatementNode node);
	void visitLeave(AssignmentStatementNode node);

	void visitEnter(IfStatementNode node);
	void visitLeave(IfStatementNode node);
	
	void visitEnter(WhileNode node);
	void visitLeave(WhileNode node);
	
	void visitEnter(ForNode node);
	void visitLeave(ForNode node);
	
	void visitEnter(ParseNode node);
	void visitLeave(ParseNode node);
	
	void visitEnter(PrintStatementNode node);
	void visitLeave(PrintStatementNode node);
	
	void visitEnter(ProgramNode node);
	void visitLeave(ProgramNode node);
	
	void visitEnter(TypeNode node);
	void visitLeave(TypeNode node);
	
	void visitEnter(ArrayNode node);
	void visitLeave(ArrayNode node);
	
	void visitEnter(ParameterListNode node);
	void visitLeave(ParameterListNode node);
	
	void visitEnter(ParameterNode node);
	void visitLeave(ParameterNode node);
	
	void visitEnter(FunctionDefinitionNode node);
	void visitLeave(FunctionDefinitionNode node);
	
	void visitEnter(FunctionInvocationNode node);
	void visitLeave(FunctionInvocationNode node);
	
	void visitEnter(ExpressionListNode node);
	void visitLeave(ExpressionListNode node);
	
	void visitEnter(CallStatementNode node);
	void visitLeave(CallStatementNode node);
	
	void visitEnter(ReturnStatementNode node);
	void visitLeave(ReturnStatementNode node);



	// leaf nodes: visitLeaf only
	void visit(BooleanConstantNode node);
	void visit(ErrorNode node);
	void visit(IdentifierNode node);
	void visit(CharacterNode node);
	void visit(StringNode node);
	void visit(IntegerConstantNode node);
	void visit(FloatingConstantNode node);
	void visit(NewlineNode node);
	void visit(SpaceNode node);
	void visit(TabNode node);
	void visit(BreakNode node);
	void visit(ContinueNode node);
	void visit(FunctionTypeNode node);

	
	public static class Default implements ParseNodeVisitor
	{
		public void defaultVisit(ParseNode node) {	}
		public void defaultVisitEnter(ParseNode node) {
			defaultVisit(node);
		}
		public void defaultVisitLeave(ParseNode node) {
			defaultVisit(node);
		}		
		public void defaultVisitForLeaf(ParseNode node) {
			defaultVisit(node);
		}
		
		public void visitEnter(OperatorNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(OperatorNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(DeclarationNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(DeclarationNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(BlockStatementNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(BlockStatementNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(AssignmentStatementNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(AssignmentStatementNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(IfStatementNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(IfStatementNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(WhileNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(WhileNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(ForNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(ForNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(MainBlockNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(MainBlockNode node) {
			defaultVisitLeave(node);
		}				
		public void visitEnter(ParseNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(ParseNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(PrintStatementNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(PrintStatementNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(ProgramNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(ProgramNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(TypeNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(TypeNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(ArrayNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(ArrayNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(ParameterListNode node) {
			defaultVisitEnter(node);	
		}
		public void visitLeave(ParameterListNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(ParameterNode node) {
			defaultVisitEnter(node);	
		}
		public void visitLeave(ParameterNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(FunctionDefinitionNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(FunctionDefinitionNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(FunctionInvocationNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(FunctionInvocationNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(ExpressionListNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(ExpressionListNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(CallStatementNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(CallStatementNode node) {
			defaultVisitLeave(node);
		}
		public void visitEnter(ReturnStatementNode node) {
			defaultVisitEnter(node);
		}
		public void visitLeave(ReturnStatementNode node) {
			defaultVisitLeave(node);
		}
		

		public void visit(BooleanConstantNode node) {
			defaultVisitForLeaf(node);
		}
		public void visit(ErrorNode node) {
			defaultVisitForLeaf(node);
		}
		public void visit(IdentifierNode node) {
			defaultVisitForLeaf(node);
		}
		public void visit(CharacterNode node) {
			defaultVisitForLeaf(node);
		}
		public void visit(StringNode node) {
			defaultVisitForLeaf(node);
		}
		public void visit(IntegerConstantNode node) {
			defaultVisitForLeaf(node);
		}
		public void visit(FloatingConstantNode node) {
			defaultVisitForLeaf(node);
		}
		public void visit(NewlineNode node) {
			defaultVisitForLeaf(node);
		}	
		public void visit(SpaceNode node) {
			defaultVisitForLeaf(node);
		}
		public void visit(TabNode node) {
			defaultVisitForLeaf(node);
		}
		public void visit(BreakNode node) {
			defaultVisitForLeaf(node);
		}
		public void visit(ContinueNode node) {
			defaultVisitForLeaf(node);
		}
		public void visit(FunctionTypeNode node) {
			defaultVisitForLeaf(node);
			
		}
	}

}
