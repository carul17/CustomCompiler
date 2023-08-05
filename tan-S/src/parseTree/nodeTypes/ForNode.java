package parseTree.nodeTypes;

import asmCodeGenerator.Labeller;
import lexicalAnalyzer.Keyword;
import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import tokens.Token;

public class ForNode extends ParseNode {
	
	Labeller labeller = new Labeller("for");
	String startLabel = labeller.newLabel("start");
	String incrementLabel = labeller.newLabel("increment");
	String endLabel = labeller.newLabel("end");
	
	public ForNode(Token token) {
		super(token);
		assert(token.isLextant(Keyword.FOR));
	}

	public ForNode(ParseNode node) {
		super(node);
		// TODO Auto-generated constructor stub
	}
	
	////////////////////////////////////////////////////////////
	// convenience factory
	
	public static ForNode withChildren(Token token, ParseNode identifier, ParseNode expression1, ParseNode expression2, ParseNode block, ParseNode endId) {
		ForNode node = new ForNode(token);
		node.appendChild(identifier);
		node.appendChild(expression1);
		node.appendChild(expression2);
		node.appendChild(block);
		node.appendChild(endId);
		return node;
	}
	
	
	
	///////////////////////////////////////////////////////////
	// boilerplate for visitors
	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visitEnter(this);
		visitChildren(visitor);
		visitor.visitLeave(this);
	}

	public String getIncrementLabel() {
		return incrementLabel;
	}

	public String getEndLabel() {
		return endLabel;
	}
	public String getStartLabel() {
		return startLabel;
	}

}
