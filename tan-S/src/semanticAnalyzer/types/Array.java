package semanticAnalyzer.types;

import java.util.Set;

public class Array implements Type {

	Type subtype;
	
	public Array(Type subtype) {
		this.subtype = subtype;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String infoString() {
		// TODO Auto-generated method stub
		return null;
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

}
