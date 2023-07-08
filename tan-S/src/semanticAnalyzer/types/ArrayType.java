package semanticAnalyzer.types;

import java.util.Set;

public class ArrayType implements Type {

	Type subtype;
	boolean isReference = true;
	
	public ArrayType(Type subtype) {
		this.subtype = subtype;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return subtype.getSize();
	}
	@Override
	public boolean getIsReference() {
		// TODO Auto-generated method stub
		return isReference;
	}

	@Override
	public String infoString() {
		// TODO Auto-generated method stub
		return "ArrayType";
	}

	

	@Override
	public void addTypeVariables(Set<TypeVariable> typeVariables) {
		// TODO Auto-generated method stub

	}

	@Override
	public Type concreteType() {
		// TODO Auto-generated method stub
		return new ArrayType(subtype.concreteType());
	}
	
	public Type getSubtype() {
		// TODO Auto-generated method stub
		return subtype.concreteType();
	}
	
	@Override
	public Boolean equivalent(Type otherType) {
		if(otherType instanceof ArrayType) {
			return this.subtype.equivalent(((ArrayType)otherType).getSubtype());
		}
		return false;
	}

}
