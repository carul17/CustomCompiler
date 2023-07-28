package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import tokens.Token;

public class ParameterNode extends ParseNode{

	public ParameterNode(Token token) {
		super(token);
	}
	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visitEnter(this);
		visitChildren(visitor);
		visitor.visitLeave(this);
	}

	public static ParseNode make(Token nowReading, ParseNode type, ParseNode identifier) {
		ParameterNode node = new ParameterNode(nowReading);
		node.appendChild(type);
		node.appendChild(identifier);
		return node;
	}

}
