package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import lexicalAnalyzer.Keyword;
import lexicalAnalyzer.Lextant;
import tokens.LextantToken;
import tokens.Token;


public class BreakNode extends ParseNode{

	public BreakNode(Token token) {
		super(token);
		assert(token.isLextant(Keyword.BREAK));
	}

	public BreakNode(ParseNode node) {
		super(node);
	}
	
	////////////////////////////////////////////////////////////
	// convenience factory
	
	public static BreakNode withChildren(Token token, ParseNode block) {
		BreakNode node = new BreakNode(token);
		node.appendChild(block);
		return node;
	}
	
	
	
	///////////////////////////////////////////////////////////
	// boilerplate for visitors
	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visit(this);

	}

}