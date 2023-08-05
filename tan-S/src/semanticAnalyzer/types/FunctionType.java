package semanticAnalyzer.types;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import asmCodeGenerator.codeStorage.ASMOpcode;
import semanticAnalyzer.signatures.FunctionSignature;

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
	
	public FunctionSignature getSignature() {
		return new FunctionSignature(ASMOpcode.Nop, parameterTypes, returnType);
	}
	
	public void setReturnType(Type type) {
		this.returnType = type;
	}
	public Type getReturnType() {
		return returnType;
	}
	
	public List<Type> getParamTypes() {
		return parameterTypes;
	}
	public void addType(Type type) {
		parameterTypes.add(type);
	
	}
	
	
	@Override
	public boolean equals(Object type2) {
		if (!(type2 instanceof FunctionType)) {
			return false;
		}
		
		FunctionType compareType = (FunctionType) type2;
		
		// Check that number of arguments is the same
		if (this.parameterTypes.size() != compareType.parameterTypes.size()) {
			return false;
		}

		// Check that each argument is the same
		for (int i = 0; i < this.parameterTypes.size(); i++) {
			Type t1 = this.parameterTypes.get(i);
			Type t2 = compareType.parameterTypes.get(i);
			
			if (!t1.equals(t2)) {
				return false;
			}
		}

		// Check that return type is the same
		return this.returnType.equals(compareType.returnType);
	}

}
