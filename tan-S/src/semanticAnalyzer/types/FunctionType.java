package semanticAnalyzer.types;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FunctionType implements Type{
	
	public static final int TYPE_SIZE = 4;
	private Type returnType;
	private List<Type> parameterTypes;

	private FunctionType(Type returnType, List<Type>parameterTypes) {
		this.returnType = returnType;
		this.parameterTypes = parameterTypes;
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
		String p = parameterTypes.stream().map(Type::infoString).collect(Collectors.joining(","));
		return "function + Parameters: " + p;
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
	
	public static FunctionType create(Type returnType, List<Type> paramterTypes) {
		return new FunctionType(returnType, paramterTypes);
		
	}

}
