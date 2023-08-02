package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import lexicalAnalyzer.Keyword;
import lexicalAnalyzer.Lextant;
import tokens.LextantToken;
import tokens.Token;


public class ContinueNode extends ParseNode{

	public ContinueNode(Token token) {
		super(token);
		assert(token.isLextant(Keyword.CONTINUE));
	}

	public ContinueNode(ParseNode node) {
		super(node);
	}
	
	
	///////////////////////////////////////////////////////////
	// boilerplate for visitors
	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visit(this);
	}

}