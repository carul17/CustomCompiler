package parseTree.nodeTypes;

import asmCodeGenerator.Labeller;
import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import tokens.Token;

public class FunctionDefinitionNode extends ParseNode {

	
	Labeller labeller = new Labeller("function");
	String label;
	String endLabel;
	
	public FunctionDefinitionNode(Token token) {
		super(token);
		label = labeller.newLabel(token.getLexeme());
		endLabel = labeller.newLabel(token.getLexeme() + "end");
	}
	
	
	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visitEnter(this);
		visitChildren(visitor);
		visitor.visitLeave(this);
	}



	public static ParseNode withChildren(Token token, ParseNode type, ParseNode identifier, ParseNode parameterList, ParseNode blockStatement) {
		FunctionDefinitionNode functionDefinition = new FunctionDefinitionNode(token);
		functionDefinition.appendChild(type);
		functionDefinition.appendChild(identifier);
		functionDefinition.appendChild(parameterList);
		functionDefinition.appendChild(blockStatement);
		return functionDefinition;
	}


	public String getLabel() {
		return label;
	}
	
	public String getEndLabel() {
		return endLabel;
	}
	
	

}
