package parseTree.nodeTypes;

import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import semanticAnalyzer.types.Type;
import tokens.Token;

public class ParameterListNode extends ParseNode {

	public ParameterListNode(Token token) {
		super(token);
	}

	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visitEnter(this);
		visitChildren(visitor);
		visitor.visitLeave(this);
	}


	public Type[] getTypes() {
		Type[] t = new Type[nChildren()];
		
		for(int i = 0; i < nChildren(); i++) {
			t[i] = child(i).getType();
		}
		
		return t;
	}
}
