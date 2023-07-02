package semanticAnalyzer.types;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TypeVariable implements Type {
	
	String name;
	Type constraint;
	
	public TypeVariable(String name) {
		this.name = name;
		reset();
	}

	public Type getConstraint() {
		return constraint;
	}

	public void setConstraint(Type constraint) {
		this.constraint = constraint;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String infoString() {
		// TODO Auto-generated method stub
		return toString();
	}
	
	public String toString() {
		return "<" + name + ">";
	}
	
	public void reset() {
		setConstraint(PrimitiveType.NO_TYPE);
	}
	
	public Boolean equivalent(Type otherType) {
		if(constraint == PrimitiveType.NO_TYPE) {
			setConstraint(otherType);
			return true;
		} else {
			return constraint.equivalent(otherType);
		}
	}

	@Override
	public void addTypeVariables(Set<TypeVariable> typeVariables) {
		typeVariables.add(this);
		
	}

	@Override
	public Type concreteType() {
		// TODO Auto-generated method stub
		//we want to return the constraint
		return constraint.concreteType();
	}
	

}
