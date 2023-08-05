package symbolTable;

import asmCodeGenerator.codeStorage.ASMCodeFragment;
import inputHandler.TextLocation;
import semanticAnalyzer.types.PrimitiveType;
import semanticAnalyzer.types.Type;

public class Binding {
	private Type type;
	private TextLocation textLocation;
	private MemoryLocation memoryLocation;
	private String lexeme;
	private Constancy constancy;
	private int numElements;
	private boolean isParam;
	
	public Binding(Type type, TextLocation location, MemoryLocation memoryLocation, String lexeme, Constancy constancy, int numElements ) {
		super();
		this.type = type;
		this.textLocation = location;
		this.memoryLocation = memoryLocation;
		this.lexeme = lexeme;
		this.setConstancy(constancy);
		this.setNumElements(numElements);
		this.isParam = false;
	}
	
	public void setIsParam(Boolean value) {
		this.isParam = value;
	}
	public Boolean getIsParam() {
		return isParam;
	}
	
	public enum Constancy{
		IS_CONSTANT,
		IS_VARIABLE
	}

	

	public String toString() {
		return "[" + lexeme +
				" " + type +  // " " + textLocation +	
				" " + memoryLocation +
				"]";
	}	
	public String getLexeme() {
		return lexeme;
	}
	public Type getType() {
		return type;
	}
	public TextLocation getLocation() {
		return textLocation;
	}
	public int getNumElements() {
		return numElements;
	}
	public MemoryLocation getMemoryLocation() {
		return memoryLocation;
	}
	public void generateAddress(ASMCodeFragment code) {
		memoryLocation.generateAddress(code, "%% " + lexeme);
	}
	
////////////////////////////////////////////////////////////////////////////////////
//Null Binding object
////////////////////////////////////////////////////////////////////////////////////

	public static Binding nullInstance() {
		return NullBinding.getInstance();
	}
	public Constancy getConstancy() {
		return constancy;
	}
	public Boolean isConstant() {
		return this.getConstancy() == Constancy.IS_CONSTANT;
	}
	public void setConstancy(Constancy constancy) {
		this.constancy = constancy;
	}
	public void setNumElements(int num) {
		this.numElements = num;
	}
	private static class NullBinding extends Binding {
		private static NullBinding instance=null;
		private NullBinding() {
			super(PrimitiveType.ERROR,
					TextLocation.nullInstance(),
					MemoryLocation.nullInstance(),
					"the-null-binding",
					Constancy.IS_CONSTANT,
					-1);
		}

		public static NullBinding getInstance() {
			if(instance==null)
				instance = new NullBinding();
			return instance;
		}
	}
}
