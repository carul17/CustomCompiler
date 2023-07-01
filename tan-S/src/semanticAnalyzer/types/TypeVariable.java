package semanticAnalyzer.types;

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
	

}
