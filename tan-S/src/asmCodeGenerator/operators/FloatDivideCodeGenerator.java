package asmCodeGenerator.operators;

import java.util.List;

import asmCodeGenerator.codeStorage.ASMCodeFragment;
import asmCodeGenerator.codeStorage.ASMOpcode;
import asmCodeGenerator.codeStorage.ASMCodeFragment.CodeType;
import asmCodeGenerator.runtime.RunTime;
import parseTree.ParseNode;

public class FloatDivideCodeGenerator implements SimpleCodeGenerator{

	@Override
	public ASMCodeFragment generate(ParseNode node, List<ASMCodeFragment> args) {
		ASMCodeFragment result = new ASMCodeFragment(CodeType.GENERATES_VALUE);
		
		for(ASMCodeFragment arg: args) {
			result.append(arg);
		}
		
		result.add(ASMOpcode.Duplicate);
		result.add(ASMOpcode.JumpFZero, RunTime.FLOAT_DIVIDE_BY_ZERO_RUNTIME_ERROR);
		result.add(ASMOpcode.FDivide); //didn't need to add this since I'm already adding divide to the stack
		
		return null;
	}

}
