package semanticAnalyzer.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum PrimitiveType implements Type {
	BOOLEAN(1, false),
	INTEGER(4, false),
	FLOATING(8, false),
	CHARACTER(1, false),
	STRING(4, true),
	VOID(0, false),
	ERROR(0, false),			// use as a value when a syntax error has occurred
	NO_TYPE(0, false, "");		// use as a value when no type has been assigned.
	
	private int sizeInBytes;
	private boolean isReference;
	private String infoString;
	
	private PrimitiveType(int size, boolean isReference) {
		this.sizeInBytes = size;
		this.isReference = isReference;
		this.infoString = toString();
	}
	private PrimitiveType(int size, String infoString) {
		this.sizeInBytes = size;
		this.infoString = infoString;
	}
	private PrimitiveType(int size, boolean isReference, String infoString) {
		this.sizeInBytes = size;
		this.isReference = isReference;
		this.infoString = infoString;
	}
	public int getSize() {
		return sizeInBytes;
	}
	public boolean getIsReference() {
		return isReference;
	}
	public String infoString() {
		return infoString;
	}
	
	public Boolean equivalent(Type otherType) {
		return this == otherType;
	}
	
	public static Type getTypeFromString(String typeString) {
		switch(typeString) {
			case "int": return PrimitiveType.INTEGER;
			case "char": return PrimitiveType.CHARACTER;
			case "string": return PrimitiveType.STRING;
			case "float": return PrimitiveType.FLOATING;
			case "bool": return PrimitiveType.BOOLEAN;
			case "void": return PrimitiveType.VOID;
			default: return PrimitiveType.ERROR;
		}
	}
	
	
    /* no type variables here */
    @Override
    public void addTypeVariables(Set<TypeVariable> typeVariables) {
        return;
    }
	@Override
	public Type concreteType() {
		// TODO Auto-generated method stub
		return this;
	}
}
