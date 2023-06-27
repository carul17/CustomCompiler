package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import lexicalAnalyzer.Keyword;
import lexicalAnalyzer.Lextant;
import tokens.LextantToken;
import tokens.Token;


public class WhileNode extends ParseNode{

	public WhileNode(Token token) {
		super(token);
		assert(token.isLextant(Keyword.WHILE));
	}

	public WhileNode(ParseNode node) {
		super(node);
	}
	
	////////////////////////////////////////////////////////////
	// convenience factory
	
	public static WhileNode withChildren(Token token, ParseNode condition, ParseNode block) {
		WhileNode node = new WhileNode(token);
		node.appendChild(condition);
		node.appendChild(block);
		return node;
	}
	
	
	
	///////////////////////////////////////////////////////////
	// boilerplate for visitors
	
	public void accept(ParseNodeVisitor visitor) {
	visitor.visitEnter(this);
	visitChildren(visitor);
	visitor.visitLeave(this);
	}
}
