package semanticAnalyzer.types;

import java.util.Set;

public class FunctionType implements Type{
	
	public static final int TYPE_SIZE = 4;
	private Type returnType;
	private Type[] paramterTypes;

	private FunctionType(Type returnType, Type[] paramterTypes) {
		this.returnType = returnType;
		this.paramterTypes = paramterTypes;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return TYPE_SIZE;
	}

	@Override
	public boolean getIsReference() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String infoString() {
		// TODO Auto-generated method stub
		return "function";
	}

	@Override
	public Boolean equivalent(Type otherType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTypeVariables(Set<TypeVariable> typeVariables) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type concreteType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static FunctionType create(Type returnType, Type... paramterTypes) {
		return new FunctionType(returnType, paramterTypes);
		
	}

}
