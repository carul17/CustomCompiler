package parseTree.nodeTypes;

import java.util.List;
import java.util.ArrayList;

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


	public List<Type> getTypes() {
		List<Type> t = new ArrayList<Type>();
		
		for(int i = 0; i < nChildren(); i++) {
			t.add(child(i).getType());
		}
		
		return t;
	}
}
