package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import tokens.Token;

public class FunctionDefinitionNode extends ParseNode {

	public FunctionDefinitionNode(Token token) {
		super(token);
	}
	
	
	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visitEnter(this);
		visitChildren(visitor);
		visitor.visitLeave(this);
	}



	public static ParseNode make(Token token, ParseNode type, ParseNode identifier, ParseNode parameterList, ParseNode blockStatement) {
		FunctionDefinitionNode fucntionDefinition = new FunctionDefinitionNode(token);
		fucntionDefinition.appendChild(type);
		fucntionDefinition.appendChild(identifier);
		fucntionDefinition.appendChild(parameterList);
		fucntionDefinition.appendChild(blockStatement);
		return fucntionDefinition;
	}

}
