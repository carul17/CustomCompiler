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
import parseTree.nodeTypes.*;
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
			else if(node.getType() == CHARACTER) {
				code.add(LoadC);
			}
			else if(node.getType() == STRING) {
				code.add(LoadI);
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
		
		public void visitLeave(BlockStatementNode node) {
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
		public void visit(TabNode node) {
			newVoidCode(node);
			code.add(PushD, RunTime.TAB_PRINT_FORMAT);
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
		
		public void visitLeave(AssignmentStatementNode node) {
			newVoidCode(node);
			ASMCodeFragment lvalue = removeAddressCode(node.child(0));
			
			ASMCodeFragment rvalue = removeValueCode(node.child(1));
			
			
			code.append(lvalue);
			code.append(rvalue);
			
			Type type = node.child(0).getType();
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
			if(type == CHARACTER) {
				return StoreC;
			}
			if(type == STRING) {
				return StoreI;
			}
			assert false: "Type " + type + " unimplemented in opcodeForStore()";
			return null;
		}


		///////////////////////////////////////////////////////////////////////////
		// expressions
		public void visitLeave(OperatorNode node) {
			FunctionSignature signature = node.getSignature();
			Lextant operator = node.getOperator();
			Object variant = signature.getVariant();;
			
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
			String pushLabel   = labeller.newLabel("pushLabel");
			
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
			

		} else if(variant instanceof SimpleCodeGenerator) {
			SimpleCodeGenerator generator = (SimpleCodeGenerator)variant;
			ASMCodeFragment fragment = generator.generate(node, childCode(node));
			codeMap.put(node, fragment);
			// NEED TO DOS OMETHING WITH SIMPLE CODE GENERATOR.JAVA
		}
			Lextant lextant = node.getOperator();
			assert(lextant instanceof Punctuator);
			Punctuator punctuator = (Punctuator)lextant;
			//System.out.print(node.child(0).getType().toString());
			
			
			if(operator == Punctuator.GREATER
					|| operator == Punctuator.LESS
					|| operator == Punctuator.GREATEREQUAL
					|| operator == Punctuator.LESSEREQUAL
					|| operator == Punctuator.EQUAL
					|| operator == Punctuator.NOTEQUAL
					|| operator == Punctuator.AND)
				{ //handing greater than symbol
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
			
			
			newValueCode(node);
			code.add(Label, startLabel);
			code.append(arg1);
			code.add(Label, arg2Label);
			code.append(arg2);
			code.add(Label, subLabel);
			
			ASMOpcode subtract;
			ASMOpcode jumpPos;
			ASMOpcode jumpNeg;
			ASMOpcode jumpTrue;
			ASMOpcode jumpFalse;
			ASMOpcode and;
			ASMOpcode or;
			
			Type type = node.child(0).getType();
			
				
			if(type == FLOATING){
				subtract = FSubtract;
				jumpPos = JumpFPos;
				jumpNeg = JumpFNeg;
				jumpPos = JumpFPos;
				jumpFalse = JumpFZero;
				jumpTrue = JumpTrue;
				
			} else {
				subtract = Subtract;
				jumpPos = JumpPos;
				jumpNeg = JumpNeg;
				jumpPos = JumpPos;
				jumpFalse = JumpFalse;
				jumpTrue = JumpTrue;
			}
			
			and = And;
			or= Or;
			
			if(operator == Punctuator.AND) {
				code.add(and);
				return;
			} else if(operator == Punctuator.OR){
				code.add(or);
			}else {
				code.add(subtract);
			}
			
			
			
			if(operator == Punctuator.GREATER) {
				code.add(jumpPos, trueLabel);
				code.add(Jump, falseLabel);
			}
			else if(operator == Punctuator.GREATEREQUAL) {
				String tempLabel  = labeller.newLabel("temp");
				code.add(Duplicate);
				
				code.add(jumpPos, tempLabel);
				code.add(jumpFalse, trueLabel);
				code.add(Jump, falseLabel);
				
				
				code.add(Label, tempLabel);
				code.add(Pop);
				code.add(Jump, trueLabel);
			}
			else if(operator == Punctuator.LESS) {
				code.add(jumpNeg, trueLabel);
				code.add(Jump, falseLabel);
			}
			
			else if(operator == Punctuator.LESSEREQUAL) {
				String tempLabel  = labeller.newLabel("temp");
				code.add(Duplicate);
				
				code.add(jumpNeg, tempLabel);
				code.add(jumpFalse, trueLabel);
				code.add(Jump, falseLabel);
				

				code.add(Label, tempLabel);
				code.add(Pop);
				code.add(Jump, trueLabel);
			}
			else if(operator == Punctuator.EQUAL) {
					code.add(jumpFalse, trueLabel);
					code.add(Jump, falseLabel);
			}
			else if(operator == Punctuator.NOTEQUAL) {
				if(type == FLOATING) {
					code.add(jumpFalse, falseLabel);
					code.add(Jump, trueLabel);
				}
				else {
					code.add(jumpTrue, trueLabel);
					code.add(Jump, falseLabel);
				}
			}
		
			
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


		private void visitUnaryOperatorNode(OperatorNode node) { //this method would be great for negating
			newValueCode(node);
			ASMCodeFragment arg1 = removeValueCode(node.child(0));
			FunctionSignature signature = node.getSignature();
			Object variant = signature.getVariant();
			
			code.append(arg1);
			
			if(variant instanceof ASMOpcode) {
				ASMOpcode opcode = (ASMOpcode) variant;
				code.add(opcode);
			} else {
				
				ASMOpcode opcode = opcodeForOperator(node.getOperator());
				code.add(opcode);
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
			} else if(signature.resultType() == FLOATING) {
				Lextant lextant = node.getOperator();
				assert(lextant instanceof Punctuator);
				Punctuator punctuator = (Punctuator)lextant;
				if(punctuator.getLexeme().equals("DIVIDE")) {
					code.add(FDivide);
				}
				
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
			case DIVIDE:		return Divide;
			case GREATER:		return FSubtract;
			case LESS:			return FSubtract;
			case GREATEREQUAL: 	return FSubtract;
			case AND:			return And;
			case OR:			return Or;
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
		public void visit(CharacterNode node) {
			newValueCode(node);
			code.add(PushI, node.getValue());
			
			
		}
		public void visit(StringNode node) {
			newValueCode(node);
			
			Labeller labeller = new Labeller("string");
			String dlabel = labeller.newLabel(node.getValue());
			code.add(DLabel, dlabel);
			code.add(DataI, 3);
			code.add(DataI, 9);
			code.add(DataI, node.getValue().length());
			code.add(DataS, node.getValue());
			code.add(PushD, dlabel);
		}
	}

}
