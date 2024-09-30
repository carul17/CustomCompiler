package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import tokens.Token;

public class CallStatementNode extends ParseNode {

	public CallStatementNode(Token token) {
		super(token);
	}
	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visitEnter(this);
		visitChildren(visitor);
		visitor.visitLeave(this);
	}

	
	
	public static ParseNode withChildren(Token nowReading, ParseNode functionInvocationNode) {
		CallStatementNode node = new CallStatementNode(nowReading);
		node.appendChild(functionInvocationNode);
		return node;
	}


}
