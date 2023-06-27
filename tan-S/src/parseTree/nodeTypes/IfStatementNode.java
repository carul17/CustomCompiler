package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import lexicalAnalyzer.Keyword;
import lexicalAnalyzer.Lextant;
import tokens.LextantToken;
import tokens.Token;


public class IfStatementNode extends ParseNode{

	public IfStatementNode(Token token) {
		super(token);
		assert(token.isLextant(Keyword.IF));
	}

	public IfStatementNode(ParseNode node) {
		super(node);
	}
	
	////////////////////////////////////////////////////////////
	// convenience factory
	
	public static IfStatementNode withChildren(Token token, ParseNode condition, ParseNode block) {
		IfStatementNode node = new IfStatementNode(token);
		node.appendChild(condition);
		node.appendChild(block);
		return node;
	}
	
	public static IfStatementNode withChildren(Token token, ParseNode condition, ParseNode block, ParseNode elseParseNode) {
		IfStatementNode node = new IfStatementNode(token);
		node.appendChild(condition);
		node.appendChild(block);
		node.appendChild(elseParseNode);
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
