package asmCodeGenerator.operators;



import java.util.List;

import parseTree.ParseNode;
import asmCodeGenerator.codeStorage.ASMCodeFragment;
import asmCodeGenerator.codeStorage.ASMCodeFragment.CodeType;
import asmCodeGenerator.codeStorage.ASMOpcode;


public interface SimpleCodeGenerator {
	public ASMCodeFragment generate(ParseNode node, List<ASMCodeFragment> args);
}

class AddCodeGenerator implements SimpleCodeGenerator{

	@Override
	public ASMCodeFragment generate(ParseNode node, List<ASMCodeFragment> args) {
		// TODO Auto-generated method stub
		ASMCodeFragment result = new ASMCodeFragment(CodeType.GENERATES_VALUE);
		for(ASMCodeFragment arg: args) {
			result.append(arg);
		}
		
		result.add(ASMOpcode.Duplicate);
		result.add(ASMOpcode.FAdd);
		
		return result;
	}
	
}
