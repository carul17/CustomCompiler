package asmCodeGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asmCodeGenerator.codeStorage.ASMCodeFragment;
import asmCodeGenerator.codeStorage.ASMOpcode;
import asmCodeGenerator.operators.SimpleCodeGenerator;
import asmCodeGenerator.runtime.RunTime;
import lexicalAnalyzer.Lextant;
import lexicalAnalyzer.Punctuator;
import parseTree.*;
import parseTree.nodeTypes.BooleanConstantNode;
import parseTree.nodeTypes.MainBlockNode;
import parseTree.nodeTypes.DeclarationNode;
import parseTree.nodeTypes.FloatingConstantNode;
import parseTree.nodeTypes.IdentifierNode;
import parseTree.nodeTypes.IntegerConstantNode;
import parseTree.nodeTypes.NewlineNode;
import parseTree.nodeTypes.OperatorNode;
import parseTree.nodeTypes.PrintStatementNode;
import parseTree.nodeTypes.ProgramNode;
import parseTree.nodeTypes.SpaceNode;
import semanticAnalyzer.signatures.FunctionSignature;
import static semanticAnalyzer.types.PrimitiveType.*;
import semanticAnalyzer.types.Type;
import symbolTable.Binding;
import symbolTable.Scope;
import static asmCodeGenerator.codeStorage.ASMCodeFragment.CodeType.*;
import static asmCodeGenerator.codeStorage.ASMOpcode.*;
import java.util.ArrayList;

// do not call the code generator if any errors have occurred during analysis.
public class ASMCodeGenerator {
	ParseNode root;

	public static ASMCodeFragment generate(ParseNode syntaxTree) {
		ASMCodeGenerator codeGenerator = new ASMCodeGenerator(syntaxTree);
		return codeGenerator.makeASM();
	}
	public ASMCodeGenerator(ParseNode root) {
		super();
		this.root = root;
	}
	
	public ASMCodeFragment makeASM() {
		ASMCodeFragment code = new ASMCodeFragment(GENERATES_VOID);
		
		code.append( RunTime.getEnvironment() );
		code.append( globalVariableBlockASM() );
		code.append( programASM() );
//		code.append( MemoryManager.codeForAfterApplication() );
		
		return code;
	}
	private ASMCodeFragment globalVariableBlockASM() {
		assert root.hasScope();
		Scope scope = root.getScope();
		int globalBlockSize = scope.getAllocatedSize();
		
		ASMCodeFragment code = new ASMCodeFragment(GENERATES_VOID);
		code.add(DLabel, RunTime.GLOBAL_MEMORY_BLOCK);
		code.add(DataZ, globalBlockSize);
		return code;
	}
	private ASMCodeFragment programASM() {
		ASMCodeFragment code = new ASMCodeFragment(GENERATES_VOID);
		
		code.add(    Label, RunTime.MAIN_PROGRAM_LABEL);
		code.append( programCode());
		code.add(    Halt );
		
		new FunctionSignature(ASMOpcode.Nop, INTEGER, INTEGER);
		new FunctionSignature(ASMOpcode.Nop, FLOATING, FLOATING);
		return code;
	}
	private ASMCodeFragment programCode() {
		CodeVisitor visitor = new CodeVisitor();
		root.accept(visitor);
		return visitor.removeRootCode(root);
	}


	protected class CodeVisitor extends ParseNodeVisitor.Default {
		private Map<ParseNode, ASMCodeFragment> codeMap;
		ASMCodeFragment code;
		
		public CodeVisitor() {
			codeMap = new HashMap<ParseNode, ASMCodeFragment>();
		}


		////////////////////////////////////////////////////////////////////
        // Make the field "code" refer to a new fragment of different sorts.
		private void newAddressCode(ParseNode node) {
			code = new ASMCodeFragment(GENERATES_ADDRESS);
			codeMap.put(node, code);
		}
		private void newValueCode(ParseNode node) {
			code = new ASMCodeFragment(GENERATES_VALUE);
			codeMap.put(node, code);
		}
		private void newVoidCode(ParseNode node) {
			code = new ASMCodeFragment(GENERATES_VOID);
			codeMap.put(node, code);
		}

	    ////////////////////////////////////////////////////////////////////
        // Get code from the map.
		private ASMCodeFragment getAndRemoveCode(ParseNode node) {
			ASMCodeFragment result = codeMap.get(node);
			codeMap.remove(node);
			return result;
		}
		private ASMCodeFragment getCodeValue(ParseNode node) {
			ASMCodeFragment result = codeMap.get(node);
			//codeMap.remove(node);
			return result;
		}
	    public  ASMCodeFragment removeRootCode(ParseNode tree) {
			return getAndRemoveCode(tree);
		}		
		ASMCodeFragment removeValueCode(ParseNode node) {
			ASMCodeFragment frag = getAndRemoveCode(node);
			makeFragmentValueCode(frag, node);
			return frag;
		}		
		private ASMCodeFragment removeAddressCode(ParseNode node) {
			ASMCodeFragment frag = getAndRemoveCode(node);
			assert frag.isAddress();
			return frag;
		}		
		ASMCodeFragment removeVoidCode(ParseNode node) {
			ASMCodeFragment frag = getAndRemoveCode(node);
			assert frag.isVoid();
			return frag;
		}
		
	    ////////////////////////////////////////////////////////////////////
        // convert code to value-generating code.
		private void makeFragmentValueCode(ASMCodeFragment code, ParseNode node) {
			assert !code.isVoid();
			
			if(code.isAddress()) {
				turnAddressIntoValue(code, node);
			}	
		}
		private void turnAddressIntoValue(ASMCodeFragment code, ParseNode node) {
			if(node.getType() == INTEGER) {
				code.add(LoadI);
			}	
			else if(node.getType() == BOOLEAN) {
				code.add(LoadC);
			}
			else if(node.getType() == FLOATING) {
				code.add(LoadF);
			}	
			else {
				assert false : "node " + node;
			}
			code.markAsValue();
		}
		
	    ////////////////////////////////////////////////////////////////////
        // ensures all types of ParseNode in given AST have at least a visitLeave	
		public void visitLeave(ParseNode node) {
			assert false : "node " + node + " not handled in ASMCodeGenerator";
		}
		
		
		
		///////////////////////////////////////////////////////////////////////////
		// constructs larger than statements
		public void visitLeave(ProgramNode node) {
			newVoidCode(node);
			for(ParseNode child : node.getChildren()) {
				ASMCodeFragment childCode = removeVoidCode(child);
				code.append(childCode);
			}
		}
		public void visitLeave(MainBlockNode node) {
			newVoidCode(node);
			for(ParseNode child : node.getChildren()) {
				ASMCodeFragment childCode = removeVoidCode(child);
				code.append(childCode);
			}
		}

		///////////////////////////////////////////////////////////////////////////
		// statements and declarations

		public void visitLeave(PrintStatementNode node) {
			newVoidCode(node);
			new PrintStatementGenerator(code, this).generate(node);	
		}
		public void visit(NewlineNode node) {
			newVoidCode(node);
			code.add(PushD, RunTime.NEWLINE_PRINT_FORMAT);
			code.add(Printf);
		}
		public void visit(SpaceNode node) {
			newVoidCode(node);
			code.add(PushD, RunTime.SPACE_PRINT_FORMAT);
			code.add(Printf);
		}
		

		public void visitLeave(DeclarationNode node) {
			newVoidCode(node);
			ASMCodeFragment lvalue = removeAddressCode(node.child(0));	
			ASMCodeFragment rvalue = removeValueCode(node.child(1));
			
			code.append(lvalue);
			code.append(rvalue);
			
			Type type = node.getType();
			code.add(opcodeForStore(type));
		}
		private ASMOpcode opcodeForStore(Type type) {
			if(type == INTEGER) {
				return StoreI;
			}
			if(type == BOOLEAN) {
				return StoreC;
			}
			if(type == FLOATING) {
				return StoreF;
			}
			assert false: "Type " + type + " unimplemented in opcodeForStore()";
			return null;
		}


		///////////////////////////////////////////////////////////////////////////
		// expressions
		public void visitLeave(OperatorNode node) {
			FunctionSignature signature = node.getSignature();
			Lextant operator = node.getOperator();
			Object variant = signature.getVariant();
			//System.out.println(variant.toString());
			
			ASMCodeFragment arg1 = getCodeValue(node.child(0)); //this was remove and get value
			ASMCodeFragment arg2 = getCodeValue(node.child(1)); //this was remove and get value
			
			Labeller labeller = new Labeller("compare");
			
			String startLabel = labeller.newLabel("arg1");
			String arg2Label  = labeller.newLabel("arg2");
			String subLabel   = labeller.newLabel("sub");
			String trueLabel  = labeller.newLabel("true");
			String falseLabel = labeller.newLabel("false");
			String joinLabel  = labeller.newLabel("join");
			String addLabel   = labeller.newLabel("add");
			String faddLabel   = labeller.newLabel("fadd");
			String pushLabel   = labeller.newLabel("psuhLabel");
			
			if(variant instanceof ASMOpcode) {
			newValueCode(node);
			for(ParseNode child: node.getChildren()) {	
				if(getCodeValue(child) != null) {
				code.append(getCodeValue(child));
				}
			}
			newValueCode(node);
			code.add(Label, startLabel);
			code.append(arg1);//from first child
			code.add(Label, arg2Label);
			code.append(arg2);
			code.add(PStack);
			
//			code.add(Label, addLabel);
//			code.add(Add);
//			code.add(PushI, 0);
//			newValueCode(node);
//			code.add(Label, subLabel);
//			code.add(Subtract);
//			code.add(PushF, 0.0);
			
			//newValueCode(node);
//			code.add(Label, startLabel);
//			code.append(arg1);
//			code.add(Label, arg2Label);
//			code.append(arg2);
//			newValueCode(node);
//			code.add(Label, subLabel);
//			code.add(Negate);
//			code.add(Add);
//			code.add(PushI, 0);
			
			//newValueCode(node);
			//code.add(Label, subLabel);
			//code.add(Negate);
			//code.add(Subtract);
//			code.add(PushI, 0);
			//code.add(PStack);
////			
			
		//	newValueCode(node);
	//		code.add(Label, faddLabel);
//			code.add(FAdd);
//			code.add(PushF, 0.0);
//			
//			code.add(JumpPos, trueLabel);
//			code.add(Jump, falseLabel);
//
//			code.add(Label, trueLabel);
//			code.add(PushI, 1);
//			code.add(Jump, joinLabel);
//			code.add(Label, falseLabel);
//			code.add(PushI, 0);
//			code.add(Jump, joinLabel);
//			code.add(Label, joinLabel);

		} else if(variant instanceof SimpleCodeGenerator) {
			SimpleCodeGenerator generator = (SimpleCodeGenerator)variant;
			ASMCodeFragment fragment = generator.generate(node, childCode(node));
			codeMap.put(node, fragment);
			// NEED TO DOS OMETHING WITH SIMPLE CODE GENERATOR.JAVA
		}
			
			
			if(operator == Punctuator.SUBTRACT) {
				visitUnaryOperatorNode(node);
			}
			else if(operator == Punctuator.GREATER) {
				visitComparisonOperatorNode(node, operator);
			}
			else {
				visitNormalBinaryOperatorNode(node);
			};
		}
		private void visitComparisonOperatorNode(OperatorNode node,
				Lextant operator) {


			ASMCodeFragment arg1 = removeValueCode(node.child(0));
			ASMCodeFragment arg2 = removeValueCode(node.child(1));
			
			Labeller labeller = new Labeller("compare");
			
			String startLabel = labeller.newLabel("arg1");
			String arg2Label  = labeller.newLabel("arg2");
			String subLabel   = labeller.newLabel("sub");
			String trueLabel  = labeller.newLabel("true");
			String falseLabel = labeller.newLabel("false");
			String joinLabel  = labeller.newLabel("join");
			
//			if(variant instanceof ASMOpcode) {
//				newValueCode(node);
//				for(ParseNode child: node.getChildren()) {
//					code.append(removeValueCode(child));
//				}
//				code.add(Label, startLabel);
//				code.append(arg1);//from first child
//				code.add(Label, arg2Label);
//				code.append(arg2);
//				code.add(Label, subLabel);
//				code.add(Subtract);
//
//			} else if(variant instanceof SimpleCodeGenerator) {
//				SimpleCodeGenerator generator = (SimpleCodeGenerator)variant;
//				ASMCodeFragment fragment = generator.generate(node, childCode(node));
//				codeMap.put(node, fragment);
//				// NEED TO DOS OMETHING WITH SIMPLE CODE GENERATOR.JAVA
//			}
			
			newValueCode(node);
			code.add(Label, startLabel);
			code.append(arg1);
			code.add(Label, arg2Label);
			code.append(arg2);
			code.add(Label, subLabel);
			code.add(Subtract);
			
			code.add(JumpPos, trueLabel);
			code.add(Jump, falseLabel);

			code.add(Label, trueLabel);
			code.add(PushI, 1);
			code.add(Jump, joinLabel);
			code.add(Label, falseLabel);
			code.add(PushI, 0);
			code.add(Jump, joinLabel);
			code.add(Label, joinLabel);

		}		
		private List<ASMCodeFragment> childCode(OperatorNode node) {
			List<ASMCodeFragment> result = new ArrayList<>();
			for(ParseNode child: node.getChildren()) {
				result.add(getCodeValue(child));
			}
			return result;
		}


		private void visitUnaryOperatorNode(OperatorNode node) {
			newValueCode(node);
			ASMCodeFragment arg1 = removeValueCode(node.child(0));
			FunctionSignature signature = node.getSignature();
			Object variant = signature.getVariant();
			
			if(variant instanceof ASMOpcode) {
				ASMOpcode opcode = (ASMOpcode) variant;
				code.append(arg1);
				code.add(opcode);
			} else {
				
				code.append(arg1);
				
				ASMOpcode opcode = opcodeForOperator(node.getOperator());
				code.add(opcode);							// type-dependent! (opcode is different for floats and for ints)
				}
				
		}
		
		private void visitNormalBinaryOperatorNode(OperatorNode node) {
			newValueCode(node);
			ASMCodeFragment arg1 = removeValueCode(node.child(0));
			ASMCodeFragment arg2 = removeValueCode(node.child(1));
			FunctionSignature signature = node.getSignature();
			Object variant = signature.getVariant();
			
			
			code.append(arg1);
			code.append(arg2);
			
			if(variant instanceof ASMOpcode) {
				ASMOpcode opcode = (ASMOpcode) variant;
				code.add(opcode);
			} else {
				ASMOpcode opcode = opcodeForOperator(node.getOperator());
				code.add(opcode);
				
			}
										// type-dependent! (opcode is different for floats and for ints)
		}
		private ASMOpcode opcodeForOperator(Lextant lextant) {
			assert(lextant instanceof Punctuator);
			Punctuator punctuator = (Punctuator)lextant;
			switch(punctuator) {
			case ADD: 	   		return Add;		// type-dependent!
			case SUBTRACT:		return Subtract;			// (unary subtract only) type-dependent! use to be negate
			case MULTIPLY: 		return Multiply;		// type-dependent!
			default:
				assert false : "unimplemented operator in opcodeForOperator";
			}
			return null;
		}

		///////////////////////////////////////////////////////////////////////////
		// leaf nodes (ErrorNode not necessary)
		public void visit(BooleanConstantNode node) {
			newValueCode(node);
			code.add(PushI, node.getValue() ? 1 : 0);
		}
		public void visit(IdentifierNode node) {
			newAddressCode(node);
			Binding binding = node.getBinding();
			
			binding.generateAddress(code);
		}		
		public void visit(IntegerConstantNode node) {
			newValueCode(node);
			
			code.add(PushI, node.getValue());
		}
		
		public void visit(FloatingConstantNode node) {
			newValueCode(node);
			
			code.add(PushF, node.getValue());
		}
	}

}
