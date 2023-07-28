package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import tokens.Token;

public class FunctionTypeNode extends ParseNode {

	public FunctionTypeNode(Token token) {
		super(token);
	}
	
	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visit(this);
	}

}
