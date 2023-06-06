package parseTree.nodeTypes;

import lexicalAnalyzer.Keyword;
import lexicalAnalyzer.Lextant;
import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import tokens.LextantToken;
import tokens.Token;

public class AssignmentStatementNode extends ParseNode {

	public AssignmentStatementNode(Token token) {
		super(token);
		//assert(token.isLextant(Keyword.VAR));
		// TODO Auto-generated constructor stub
	}
	
	public AssignmentStatementNode(ParseNode node) {
		super(node);
	}	
	
	////////////////////////////////////////////////////////////
	// attributes
	
	public Lextant getDeclarationType() {
		return lextantToken().getLextant();
	}
	public LextantToken lextantToken() {
		return (LextantToken)token;
	}	
	
	
	////////////////////////////////////////////////////////////
	// convenience factory
	
	public static AssignmentStatementNode withChildren(Token token, ParseNode declaredName, ParseNode initializer) {
		AssignmentStatementNode node = new AssignmentStatementNode(token);
		node.appendChild(declaredName);
		node.appendChild(initializer);
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
