package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import semanticAnalyzer.signatures.FunctionSignature;
import semanticAnalyzer.signatures.FunctionSignatures;
import semanticAnalyzer.signatures.PromotedSignature;

import java.util.List;

import lexicalAnalyzer.Lextant;
import tokens.LextantToken;
import tokens.Token;

public class ArrayNode extends ParseNode {
	PromotedSignature signature;
	

	public ArrayNode(Token token) {
		super(token);
		assert(token instanceof LextantToken);
	}

	public ArrayNode(ParseNode node) {
		super(node);
	}
	
	
	////////////////////////////////////////////////////////////
	// attributes
	
	public Lextant getArray() {
		return lextantToken().getLextant();
	}
	public LextantToken lextantToken() {
		return (LextantToken)token;
	}
	
	public PromotedSignature getSignature() {
		return signature;
	}

	public void setSignature(PromotedSignature signature) {
		this.signature = signature;
	}
	
	////////////////////////////////////////////////////////////
	// convenience factory

	public static ParseNode withChildren(Token token, List<ParseNode> elements) {
		ArrayNode node = new ArrayNode(token);
		for(ParseNode element: elements) {
			node.appendChild(element);
		}
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

