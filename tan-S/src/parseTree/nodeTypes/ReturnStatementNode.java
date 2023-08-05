package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import tokens.Token;

public class ReturnStatementNode extends ParseNode {

	public ReturnStatementNode(Token token) {
		super(token);
	}
	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visitEnter(this);
		visitChildren(visitor);
		visitor.visitLeave(this);
	}

	public static ParseNode withChildren(Token nowReading, ParseNode expr) {
		ReturnStatementNode node = new ReturnStatementNode(nowReading);
		node.appendChild(expr);
		return node;
	}


}
