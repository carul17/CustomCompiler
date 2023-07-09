package asmCodeGenerator;

import static asmCodeGenerator.codeStorage.ASMOpcode.*;
import parseTree.ParseNode;
import parseTree.nodeTypes.IdentifierNode;
import parseTree.nodeTypes.NewlineNode;
import parseTree.nodeTypes.PrintStatementNode;
import parseTree.nodeTypes.SpaceNode;
import parseTree.nodeTypes.TabNode;
import semanticAnalyzer.types.ArrayType;
import semanticAnalyzer.types.PrimitiveType;
import semanticAnalyzer.types.Type;
import symbolTable.Binding;
import asmCodeGenerator.ASMCodeGenerator.CodeVisitor;
import asmCodeGenerator.codeStorage.ASMCodeFragment;
import asmCodeGenerator.runtime.RunTime;

public class PrintStatementGenerator {
	ASMCodeFragment code;
	ASMCodeGenerator.CodeVisitor visitor;
	
	
	public PrintStatementGenerator(ASMCodeFragment code, CodeVisitor visitor) {
		super();
		this.code = code;
		this.visitor = visitor;
	}

	public void generate(PrintStatementNode node) {
		
		for(ParseNode child : node.getChildren()) {
			if(child instanceof NewlineNode || child instanceof SpaceNode || child instanceof TabNode) {
				ASMCodeFragment childCode = visitor.removeVoidCode(child);
				code.append(childCode);
			}
			else {
				
				appendPrintCode(child);
			}
		}
	}

	private void appendPrintCode(ParseNode node) {
		code.append(visitor.removeValueCode(node));
		
		convertToStringIfBoolean(node);
		convertToValueIfString(node);
		convertToValueIfArray(node);
		
		if(node.getType() instanceof ArrayType) {
			
			Type subtype = ((ArrayType)node.getType()).getSubtype();
			String format = printFormat(subtype);
			int numElements;
			if(node instanceof IdentifierNode) {
				IdentifierNode identifier = (IdentifierNode)node;
				numElements = identifier.getBinding().getNumElements();
			}
			else {
				numElements = node.nChildren();
			}
			int offset = 0;
			code.add(PushI, '[');
			code.add(PushD, RunTime.CHARACTER_PRINT_FORMAT);
			code.add(Printf);
			for(int i = 0; i < numElements; i++) {
				code.add(Duplicate);
				Macros.readTypeOffset(code, offset, subtype);
				code.add(PushD, format);
				code.add(Printf);
				if(i < numElements - 1) {
					code.add(PushI, ',');
					code.add(PushD, RunTime.CHARACTER_PRINT_FORMAT);
					code.add(Printf);
					code.add(PushI, ' ');
					code.add(PushD, RunTime.CHARACTER_PRINT_FORMAT);
					code.add(Printf);
				}
				
				if(subtype == PrimitiveType.FLOATING) {
					offset += 8;
				}
				else {
					offset += 4;
				}
			}
			code.add(Pop);
			code.add(PushI, ']');
			code.add(PushD, RunTime.CHARACTER_PRINT_FORMAT);
			code.add(Printf);
			
		}
		else {
			String format = printFormat(node.getType());
			code.add(PushD, format);
			code.add(Printf);
		}
	}
	private void convertToValueIfString(ParseNode node) {
		if(node.getType() != PrimitiveType.STRING){
			return;
		}
		code.add(PushI, 12);
		code.add(Add);
	}
	private void convertToValueIfArray(ParseNode node) {
		
		if(!(node.getType() instanceof ArrayType)){
			return;
		}
		
		code.add(PushI, 16);
		code.add(Add);
	}
	private void convertToStringIfBoolean(ParseNode node) {
		if(node.getType() != PrimitiveType.BOOLEAN) {
			return;
		}
		
		Labeller labeller = new Labeller("print-boolean");
		String trueLabel = labeller.newLabel("true");
		String endLabel = labeller.newLabel("join");

		code.add(JumpTrue, trueLabel);
		code.add(PushD, RunTime.BOOLEAN_FALSE_STRING);
		code.add(Jump, endLabel);
		code.add(Label, trueLabel);
		code.add(PushD, RunTime.BOOLEAN_TRUE_STRING);
		code.add(Label, endLabel);
	}


	private static String printFormat(Type type) {
		if(type instanceof ArrayType) {
			
			return RunTime.ARRAY_PRINT_FORMAT;
		}
		assert type instanceof PrimitiveType;
		
		switch((PrimitiveType)type) {
		case INTEGER:	return RunTime.INTEGER_PRINT_FORMAT;
		case BOOLEAN:	return RunTime.BOOLEAN_PRINT_FORMAT;
		case FLOATING:	return RunTime.FLOATING_PRINT_FORMAT;
		case CHARACTER:	return RunTime.CHARACTER_PRINT_FORMAT;
		case STRING:	return RunTime.STRING_PRINT_FORMAT;
		default:		
			assert false : "Type " + type + " unimplemented in PrintStatementGenerator.printFormat()";
			return "";
		}
	}
}
