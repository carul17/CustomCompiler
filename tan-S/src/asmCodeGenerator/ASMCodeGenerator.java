package asmCodeGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asmCodeGenerator.codeStorage.ASMCodeFragment;
import asmCodeGenerator.codeStorage.ASMOpcode;
import asmCodeGenerator.operators.IntegerDivideCodeGenerator;
import asmCodeGenerator.operators.SimpleCodeGenerator;
import asmCodeGenerator.runtime.MemoryManager;
import asmCodeGenerator.runtime.RunTime;
import lexicalAnalyzer.Keyword;
import lexicalAnalyzer.Lextant;
import lexicalAnalyzer.Punctuator;
import parseTree.*;
import parseTree.nodeTypes.*;
import semanticAnalyzer.signatures.FunctionSignature;
import semanticAnalyzer.signatures.PromotedSignature;

import static semanticAnalyzer.types.PrimitiveType.*;

import semanticAnalyzer.types.ArrayType;
import semanticAnalyzer.types.PrimitiveType;
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
		
		code.append( MemoryManager.codeForInitialization());
		code.append( RunTime.getEnvironment() );
		
		code.append( globalVariableBlockASM() );
		
		code.append( programASM() );
		
		code.append( MemoryManager.codeForAfterApplication() );
		
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
		//code.add(    Halt );
		
		new FunctionSignature(ASMOpcode.Nop, INTEGER, INTEGER);
		new FunctionSignature(ASMOpcode.Nop, FLOATING, FLOATING);
		return code;
	}
	private ASMCodeFragment programCode() {
		ASMCodeFragment code = new ASMCodeFragment(GENERATES_VOID);
		CodeVisitor visitor = new CodeVisitor();
		root.accept(visitor);
		code.append(visitor.removeRootCode(root));
		code.add(Halt);
		code.append(visitor.function);
		return code;
		
	}


	protected class CodeVisitor extends ParseNodeVisitor.Default {
		private Map<ParseNode, ASMCodeFragment> codeMap;
		ASMCodeFragment code;
		ASMCodeFragment function;
		
		public CodeVisitor() {
			codeMap = new HashMap<ParseNode, ASMCodeFragment>();
			function = new ASMCodeFragment(GENERATES_VOID);
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
			else if(node.getType() instanceof ArrayType) {
				
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
		public void visit(BreakNode node) {
			newVoidCode(node);
			
			ParseNode parent = node.getParent();
			while(!(parent instanceof ForNode || parent instanceof WhileNode)) {
				parent = parent.getParent();
			}
			
			if(parent instanceof ForNode) {
				ForNode forNode = (ForNode)parent;
				code.add(Jump, forNode.getEndLabel());
			}
			else if(parent instanceof WhileNode) {
				WhileNode whileNode = (WhileNode)parent;
				code.add(Jump, whileNode.getEndLabel());
			}
		}
		
		public void visit(ContinueNode node) {
			newVoidCode(node);
			
			ParseNode parent = node.getParent();
			while(!(parent instanceof ForNode || parent instanceof WhileNode)) {
				parent = parent.getParent();
			}
			
			if(parent instanceof ForNode) {
				ForNode forNode = (ForNode)parent;
				code.add(Jump, forNode.getIncrementLabel());
			}
			else if(parent instanceof WhileNode) {
				WhileNode whileNode = (WhileNode)parent;
				code.add(Jump, whileNode.getConditionLabel());
			}
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
		
		public void visitLeave(CallStatementNode node) {
			newVoidCode(node);
			
			ExpressionListNode arguments = (ExpressionListNode) node.child(1);
			
			for(int i = arguments.nChildren() - 1; i >= 0; i-- ) {
				Macros.loadIFrom(code,  RunTime.STACK_POINTER);
				code.add(PushI, arguments.child(i).getType().getSize());
				code.add(Subtract);
				Macros.storeITo(code, RunTime.STACK_POINTER);
				Macros.loadIFrom(code, RunTime.STACK_POINTER);
				code.append(removeValueCode(arguments.child(i)));
				code.add(StoreI);
				
				
			}
			ASMCodeFragment value = removeAddressCode(node.child(0));
			code.append(value);
			code.add(LoadI);
			code.add(CallV); 
		}
		
		public void visitLeave(ReturnStatementNode node) {
			FunctionDefinitionNode func = node.getAncestorFunction();
			assert func != null;
			newVoidCode(node);
			if(node.nChildren() == 1) {
				code.append(removeValueCode(node.child(0)));
			}
			
			code.add(Jump, func.getEndLabel());
			
		}
		
		public void visitLeave(FunctionDefinitionNode node) {
			ASMCodeFragment fCode = new ASMCodeFragment(GENERATES_VOID);
			BlockStatementNode block = (BlockStatementNode)node.child(3);
			String label = node.getLabel();
			String endLabel = node.getEndLabel();
			
			fCode.add(Label, label);
			//fCode.add(PStack);
			
			Macros.loadIFrom(fCode, RunTime.STACK_POINTER);
			fCode.add(PushI, 4);
			//fCode.add(PStack);
			fCode.add(Subtract);
			Macros.loadIFrom(fCode, RunTime.FRAME_POINTER);
			//fCode.add(PStack);
			fCode.add(StoreI);
			
			Macros.loadIFrom(fCode,  RunTime.STACK_POINTER);
			
			fCode.add(PushI, 8);
			fCode.add(Subtract);
			
			fCode.add(Exchange);
			fCode.add(StoreI);
			
			Macros.loadIFrom(fCode, RunTime.STACK_POINTER);
			
			
			Macros.storeITo(fCode, RunTime.FRAME_POINTER);
			
			
			Macros.loadIFrom(fCode, RunTime.STACK_POINTER);
			
			fCode.add(PushI, 8);
			fCode.add(Subtract);
			Macros.storeITo(fCode, RunTime.STACK_POINTER);
			
			
			//local var
			Macros.loadIFrom(fCode, RunTime.STACK_POINTER);
			
			fCode.add(PushI, block.getScope().getAllocatedSize());
			
			fCode.add(Subtract);
			Macros.storeITo(fCode,  RunTime.STACK_POINTER);
			
			
			fCode.append(removeVoidCode(node.child(3)));
			
			
			//end
			fCode.add(Label, endLabel);
			Macros.loadIFrom(fCode, RunTime.FRAME_POINTER);
			fCode.add(PushI, 8);
			fCode.add(Subtract);
			fCode.add(LoadI);
			
			Macros.loadIFrom(fCode, RunTime.FRAME_POINTER);
			fCode.add(PushI, 4);
			fCode.add(Subtract);
			fCode.add(LoadI);
			Macros.storeITo(fCode, RunTime.FRAME_POINTER);
			
			fCode.add(Return);
			
			function.append(fCode);
			newVoidCode(node);
			code.append(removeAddressCode(node.child(1)));
			
			code.add(PushD, label);
			code.add(StoreI);
		}
		
		public void visitLeave(IfStatementNode node) {
			newVoidCode(node);
			
			Labeller labeller = new Labeller("if");
			String endLabel = labeller.newLabel("end");
			String elseLabel = labeller.newLabel("else");
			String jumpLabel = endLabel;

			ASMCodeFragment conditionValue = removeValueCode(node.child(0));
			ASMCodeFragment block = removeVoidCode(node.child(1));
			ASMCodeFragment elseBlock = block;
			boolean hasElse = node.nChildren() == 3;
			if(hasElse) {
				elseBlock = removeVoidCode(node.child(2));
				jumpLabel = elseLabel;
			}
			
			code.append(conditionValue);
			code.add(JumpFalse, jumpLabel);
			code.append(block);
			code.add(Jump, endLabel);
			if(hasElse) {
				code.add(Label, elseLabel);
				code.append(elseBlock);
			}
			code.add(Label, endLabel);
		}
		
		public void visitLeave(WhileNode node) {
			newVoidCode(node);
			

			ASMCodeFragment conditionValue = removeValueCode(node.child(0));
			ASMCodeFragment block = removeVoidCode(node.child(1));
			ASMCodeFragment elseBlock = block;
			
			code.add(Label, node.getConditionLabel());
			code.append(conditionValue);
			code.add(JumpFalse, node.getEndLabel());
			code.append(block);
			code.add(Jump, node.getConditionLabel());
			
			code.add(Label, node.getEndLabel());
		}
		
		public void visitLeave(ForNode node) {
			newVoidCode(node);
			
			

			ASMCodeFragment id = removeAddressCode(node.child(0));	
			ASMCodeFragment initial = removeValueCode(node.child(1));
			ASMCodeFragment endValue = removeValueCode(node.child(2));
			ASMCodeFragment block = removeVoidCode(node.child(3));
			ASMCodeFragment endId = removeAddressCode(node.child(4));	
			Type type = node.getType();
			
			code.append(id);
			code.append(initial);
			code.add(opcodeForStore(type));
			
			code.append(endId);
			code.append(endValue);
			code.add(opcodeForStore(type));
			
			Binding binding = ((IdentifierNode)node.child(0)).getBinding();
			Binding endBinding = ((IdentifierNode)node.child(4)).getBinding();
			
			//code.append(initial);
			//code.append(id);
			
			
			code.add(Label, node.getStartLabel());
			
			binding.generateAddress(code);
			code.add(LoadI);

			endBinding.generateAddress(code);
			code.add(LoadI);
			
			code.add(Subtract);
			code.add(JumpPos, node.getEndLabel());
			code.append(block);
			code.add(Label, node.getIncrementLabel());
			binding.generateAddress(code);
			code.add(LoadI);
			
			code.add(PushI, 1);
			code.add(Add);
			
			binding.generateAddress(code);
			code.add(Exchange);
			code.add(opcodeForStore(type));
			code.add(Jump, node.getStartLabel());
			
			code.add(Label, node.getEndLabel());
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
			if(type instanceof ArrayType) {
				return StoreI;
			}
			assert false: "Type " + type + " unimplemented in opcodeForStore()";
			return null;
		}


		///////////////////////////////////////////////////////////////////////////
		// expressions
		
		public void visitLeave(TypeNode node) {
			newValueCode(node);
		}
		
		
		public void visitLeave(OperatorNode node) {
			PromotedSignature signature = node.getSignature();
			Lextant operator = node.getOperator();
			Object variant = signature.getVariant();
			ASMCodeFragment arg2 = null;
			
			
			
			ASMCodeFragment arg1 = getCodeValue(node.child(0)); //this was remove and get value
			
			
			
			
			
			if(!node.getOperator().getLexeme().equals("!") && !(node.nChildren() == 1)){
				arg2 = getCodeValue(node.child(1)); //this was remove and get value
			}
			
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
				int i =0;
				for(ParseNode child: node.getChildren()) {	
					if(getCodeValue(child) != null) {
					code.append(getCodeValue(child));
					
					code.add(signature.promotion(i).codeFor());
					
					}
					i++; //might need to put in if
				}
			
			
			newValueCode(node);
			
			code.add(Label, startLabel);
			code.append(arg1);//from first child
			
			} else if(variant instanceof SimpleCodeGenerator) {
				newValueCode(node);
				int i =0;
				for(ParseNode child: node.getChildren()) {	
					if(getCodeValue(child) != null) {
					code.append(getCodeValue(child));
					
					code.add(signature.promotion(i).codeFor());
					
					//print for debugging
					//code.add(PStack);
					
					
					}
					i++; 
				}
				if(node.getOperator() == Punctuator.INDEXING) {
					newAddressCode(node);
				}
				else {
				newValueCode(node);
				}
			code.add(Label, startLabel);
			code.append(arg1);
			code.add(Label, arg2Label);
			
			if(!node.getOperator().getLexeme().equals("!") && !(node.nChildren() == 1)){
				code.append(arg2);
			}
			
			code.add(ASMOpcode.Duplicate);
			if (signature.resultType().toString().equals("FLOATING")) {
				code.add(ASMOpcode.JumpFZero, RunTime.FLOAT_DIVIDE_BY_ZERO_RUNTIME_ERROR);
				code.add(ASMOpcode.FDivide);
			} else {
				code.add(ASMOpcode.Duplicate);
				code.add(ASMOpcode.JumpFalse, RunTime.INTEGER_DIVIDE_BY_ZERO_RUNTIME_ERROR);
				code.add(ASMOpcode.Divide);
			}
			
			}
			
			Lextant lextant = node.getOperator();
			;
			
			if(operator == Punctuator.GREATER
					|| operator == Punctuator.LESS
					|| operator == Punctuator.GREATEREQUAL
					|| operator == Punctuator.LESSEREQUAL
					|| operator == Punctuator.EQUAL
					|| operator == Punctuator.NOTEQUAL
					|| operator == Punctuator.AND)
				{
				visitComparisonOperatorNode(node, operator);
			}
			else if(operator == Keyword.NEW) {
				Type type = node.getType();
				
				
				int length;
				code.add(PushI, 16);
				code.add(PushI, type.getSize());
				if(node.child(1) instanceof CharacterNode) {
					length = ((CharacterNode)node.child(1)).getValue();
				}
				else {
					length =  ((IntegerConstantNode)node.child(1)).getValue();
				}
				code.add(PushI, length);
				code.add(Multiply);
				
				code.add(Call, MemoryManager.MEM_MANAGER_ALLOCATE);
				
				code.add(Duplicate);
				code.add(PushI, 5);
				code.add(StoreI);
				
				
				code.add(Duplicate);
				code.add(PushI, 4);
				code.add(Add);
				code.add(PushI, 0);
				code.add(StoreI);
				
				code.add(Duplicate);
				code.add(PushI, 8);
				code.add(Add);
				code.add(PushI, ((ArrayType)type).getSubtype().getSize());
				code.add(StoreI);
				
				code.add(Duplicate);
				code.add(PushI, 12);
				code.add(Add);
				
				code.add(PushI, length);
				code.add(StoreI);
			}
			
			
			else if(node.nChildren() == 1){
				visitUnaryOperatorNode(node);
				
			}
			else {
				visitNormalBinaryOperatorNode(node);
			}
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
			int i = 0;
			for(ParseNode child: node.getChildren()) {
				code.append(getCodeValue(child));
				
				code.add(node.getSignature().promotion(i).codeFor());
				
				result.add(code);
				i++;
			}
			return result;
		}


		private void visitUnaryOperatorNode(OperatorNode node) { //this method would be great for negating
			newValueCode(node);
			ASMCodeFragment arg1 = removeValueCode(node.child(0));
			PromotedSignature signature = node.getSignature();
			Object variant = signature.getVariant();
			
			code.append(arg1);
			
			
			if(variant instanceof ASMOpcode) {
				ASMOpcode opcode = (ASMOpcode) variant;
				code.add(opcode);
			} else {
				
				ASMOpcode opcode = opcodeForOperator(node.getOperator());
				code.add(opcode);
				}
			
			if(node.getOperator() == Keyword.LENGTH) {
				code.add(PushI, 12);
				code.add(Add);
				code.add(LoadI);
				
			}
		}
		
		private void visitNormalBinaryOperatorNode(OperatorNode node) {
			if(node.getOperator() == Punctuator.INDEXING) {
				newAddressCode(node);
			}
			else {
			newValueCode(node);
			}
		
			ASMCodeFragment arg1 = removeValueCode(node.child(0));
			ASMCodeFragment arg2 = null;
			if(!node.getOperator().getLexeme().equals("!") && !(node.nChildren() == 1)){
				 arg2 = removeValueCode(node.child(1));
			}
			PromotedSignature signature = node.getSignature();
			Object variant = signature.getVariant();
			
			code.append(arg1);
			if(!node.getOperator().getLexeme().equals("!") && !(node.nChildren() == 1) && !(node.getOperator() == Punctuator.INDEXING)){
				code.append(arg2);
			}
			
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
				
			if(node.getOperator() == Punctuator.INDEXING) {
				int index = 0;
				if(node.child(1) instanceof CharacterNode) {
					index = ((CharacterNode)node.child(1)).getValue();
				}
				else if(node.child(1) instanceof IntegerConstantNode){
					index =  ((IntegerConstantNode)node.child(1)).getValue();
				}
				
				//runtime error
				
				code.add(Duplicate);
				
				code.add(PushI, 12);
				code.add(Add);
				code.add(LoadI);
				if(node.child(1) instanceof IdentifierNode) {
					Binding binding = ((IdentifierNode)node.child(1)).getBinding();
					binding.generateAddress(code);
					code.add(LoadI);
				}
				else {
					code.add(PushI, index);
				}
				code.add(Subtract);
				code.add(ASMOpcode.JumpNeg, RunTime.INDEX_OUT_OF_BOUNDS_RUNTIME_ERROR);
				if(index < 0 ) {
					code.add(Jump, RunTime.INDEX_OUT_OF_BOUNDS_RUNTIME_ERROR);
				}
				
				/////
				
				code.add(Duplicate);
				code.add(PushI, 8);
				code.add(Add);
				code.add(LoadI);
				
				if(node.child(1) instanceof IdentifierNode) {
					Binding binding = ((IdentifierNode)node.child(1)).getBinding();
					binding.generateAddress(code);
					code.add(LoadI);
				}
				else {
					code.add(PushI, index);
				}
				code.add(Multiply);
				
				code.add(PushI, 16);
				code.add(Add);
				
				code.add(Add);
				
				turnAddressIntoValue(code, node);
			}
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
		
		public void visitLeave(ArrayNode node) {
			newValueCode(node);
			
			
			
			Type subtype = ((ArrayType)node.getType()).getSubtype();
			
			int sizeInBytes = 16 + subtype.getSize() * node.nChildren();
			
			
			
			Labeller labeller = new Labeller("array");
			String dlabel = labeller.newLabel(node.getType().toString());
			
			//code.add(DLabel, dlabel);
			
			code.add(PushI, sizeInBytes);
			code.add(Call, MemoryManager.MEM_MANAGER_ALLOCATE);
		
			code.add(Duplicate);
			code.add(PushI, 5);
			code.add(StoreI);
			
			
			code.add(Duplicate);
			code.add(PushI, 4);
			code.add(Add);
			code.add(PushI, 0);
			code.add(StoreI);
			
			code.add(Duplicate);
			code.add(PushI, 8);
			code.add(Add);
			code.add(PushI, subtype.getSize());
			code.add(StoreI);
			
			code.add(Duplicate);
			code.add(PushI, 12);
			code.add(Add);
			code.add(PushI, node.nChildren());
			code.add(StoreI);
			
			
			int i = 0;
			int offset = 0;
			for(ParseNode child: node.getChildren()) {
				Type type = child.getType().concreteType();
				if(type == INTEGER) {
					code.add(Duplicate);
					code.add(PushI, 16 + offset);
					code.add(Add);
					code.add(PushI, ((IntegerConstantNode)child).getValue());
					
					code.add(StoreI);
					offset += 4;
				}
				if(type == FLOATING) {
					code.add(Duplicate);
					code.add(PushI, 16 + offset);
					code.add(Add);
					code.add(PushF, ((FloatingConstantNode)child).getValue());
					code.add(StoreF);
					offset += 8;
				}
				if(type == CHARACTER) {
					code.add(Duplicate);
					code.add(PushI, 16 + offset);
					code.add(Add);
					code.add(PushI, ((CharacterNode)child).getValue());
					code.add(StoreI);
					offset += 4;
				}
				
				
				i++;
			}
			
			//code.add(PushD, dlabel);
			//code.add(PStack);
		}
	}

}
