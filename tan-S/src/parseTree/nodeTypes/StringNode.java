package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import tokens.StringToken;
//import tokens.NumberToken;
import tokens.Token;

public class StringNode extends ParseNode {
	public StringNode(Token token) {
		super(token);
		assert(token instanceof StringToken);
	}
	public StringNode(ParseNode node) {
		super(node);
	}

////////////////////////////////////////////////////////////
// attributes
	
	public String getValue() {
		return StringToken().getValue();
	}

	public StringToken StringToken() {
		return (StringToken)token;
	}	

///////////////////////////////////////////////////////////
// accept a visitor
	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visit(this);
	}

}
