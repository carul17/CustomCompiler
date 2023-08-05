package parseTree.nodeTypes;

import java.util.List;

import asmCodeGenerator.Labeller;
import asmCodeGenerator.codeStorage.ASMCodeFragment;
import parseTree.ParseNode;
import parseTree.ParseNodeVisitor;
import tokens.Token;

public class FunctionDefinitionNode extends ParseNode {

	
	Labeller labeller = new Labeller("function");
	String label;
	String endLabel;
	ASMCodeFragment fCode;
	
	public FunctionDefinitionNode(Token token) {
		super(token);
		label = labeller.newLabel(token.getLexeme());
		endLabel = labeller.newLabel(token.getLexeme() + "end");
	}
	
	
	
	public void accept(ParseNodeVisitor visitor) {
		visitor.visitEnter(this);
		visitChildren(visitor);
		visitor.visitLeave(this);
	}



	public static ParseNode withChildren(Token token, ParseNode type, ParseNode identifier, ParseNode parameterList, ParseNode blockStatement) {
		FunctionDefinitionNode functionDefinition = new FunctionDefinitionNode(token);
		functionDefinition.appendChild(type);
		functionDefinition.appendChild(identifier);
		functionDefinition.appendChild(parameterList);
		functionDefinition.appendChild(blockStatement);
		return functionDefinition;
	}


	public String getLabel() {
		return label;
	}
	
	public String getEndLabel() {
		return endLabel;
	}



	public int getFrameSize() {
		int size = 8;		// 4 bytes for Dynamic Link, 4 bytes for Return Address

		List<ParseNode> localVars = this.child(3).getChildren();
		for (ParseNode child : localVars) {
			size += child.getType().getSize();
		}

		return size;
	}



	public int getArgSize() {
		int size = 0;
		
		List<ParseNode> paramArgs = this.child(2).getChildren();
		for (ParseNode child : paramArgs) {
			size += child.getType().getSize();
		}
		
		return size;
	}



	public void setFrag(ASMCodeFragment fCode) {
		this.fCode = fCode;
		
	}
	
	

}
