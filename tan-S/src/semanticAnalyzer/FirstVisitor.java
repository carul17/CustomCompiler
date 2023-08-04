package semanticAnalyzer;

import parseTree.ParseNode;
import semanticAnalyzer.SemanticAnalysisVisitor;
import parseTree.ParseNodeVisitor;
import parseTree.nodeTypes.ArrayNode;
import parseTree.nodeTypes.AssignmentStatementNode;
import parseTree.nodeTypes.BlockStatementNode;
import parseTree.nodeTypes.BooleanConstantNode;
import parseTree.nodeTypes.BreakNode;
import parseTree.nodeTypes.CallStatementNode;
import parseTree.nodeTypes.CharacterNode;
import parseTree.nodeTypes.ContinueNode;
import parseTree.nodeTypes.DeclarationNode;
import parseTree.nodeTypes.ErrorNode;
import parseTree.nodeTypes.ExpressionListNode;
import parseTree.nodeTypes.FloatingConstantNode;
import parseTree.nodeTypes.ForNode;
import parseTree.nodeTypes.FunctionDefinitionNode;
import parseTree.nodeTypes.FunctionTypeNode;
import parseTree.nodeTypes.IdentifierNode;
import parseTree.nodeTypes.IfStatementNode;
import parseTree.nodeTypes.IntegerConstantNode;
import parseTree.nodeTypes.MainBlockNode;
import parseTree.nodeTypes.NewlineNode;
import parseTree.nodeTypes.OperatorNode;
import parseTree.nodeTypes.ParameterListNode;
import parseTree.nodeTypes.PrintStatementNode;
import parseTree.nodeTypes.ProgramNode;
import parseTree.nodeTypes.SpaceNode;
import parseTree.nodeTypes.StringNode;
import parseTree.nodeTypes.TabNode;
import parseTree.nodeTypes.TypeNode;
import parseTree.nodeTypes.WhileNode;
import semanticAnalyzer.types.FunctionType;
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
	public void visitLeave(FunctionDefinitionNode node) {
		TypeNode type = (TypeNode) node.child(0);
		IdentifierNode iden = (IdentifierNode) node.child(1);
		ParameterListNode pList = (ParameterListNode) node.child(2);
		Type fType = FunctionType.create(type.getType(), pList.getTypes());
		SemanticAnalysisVisitor.addBinding(iden, fType, null, 0);
		
	}
	
	private void enterProgramScope(ParseNode node) {
		Scope scope = Scope.createProgramScope();
		node.setScope(scope);
	}	
}
