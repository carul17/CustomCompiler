package semanticAnalyzer.signatures;
import static semanticAnalyzer.types.PrimitiveType.*;

import asmCodeGenerator.codeStorage.ASMCodeFragment;
import asmCodeGenerator.codeStorage.ASMCodeFragment.CodeType;
import asmCodeGenerator.codeStorage.ASMOpcode;
import semanticAnalyzer.types.PrimitiveType;
import semanticAnalyzer.types.Type;

public enum Promotion {

	CHAR_TO_INT(CHARACTER, INTEGER, ASMOpcode.Nop),
	CHAR_TO_FLOAT(CHARACTER, FLOATING, ASMOpcode.ConvertF),
	INT_TO_FLOAT(INTEGER, FLOATING, ASMOpcode.ConvertF),
	NONE(NO_TYPE, NO_TYPE, ASMOpcode.Nop);
	
	Promotion(PrimitiveType fromType, PrimitiveType toType, ASMOpcode opcode){
		this.fromType = fromType;
		this.toType = toType;
		this.opcode = opcode;
	}
	
	public String stringValue() {
		switch(this) {
		case CHAR_TO_INT: return "charToInt";
		case CHAR_TO_FLOAT: return "charToFloat";
		case INT_TO_FLOAT: return "intToFloat";
		default: return "none";
		}
	}
	
	Boolean isNull(){
	 return this == NONE;
	}
	
	Boolean appliesTo(Type type){
		return fromType == type;
	}

	Type apply(Type type){
		assert(appliesTo(type));
		return toType;
	}	
	
	public ASMOpcode codeFor(){
		//ASMCodeFragment result = new ASMCodeFragment(CodeType.GENERATES_VALUE);
		//result.add(opcode); //int to float and char float needs an opcode
		return this.opcode;
	}

	
	Type fromType;
	Type toType;
	ASMOpcode opcode;

}

