package semanticAnalyzer.signatures;

import java.util.HashSet;
import java.util.List;

import semanticAnalyzer.types.PrimitiveType;
import semanticAnalyzer.types.Type;
import semanticAnalyzer.types.TypeVariable;
import lexicalAnalyzer.Lextant;
import lexicalAnalyzer.Punctuator;
import java.util.ArrayList;

//immutable
public class FunctionSignature {
	private static final boolean ALL_TYPES_ACCEPT_ERROR_TYPES = true;
	private Type resultType;
	private Type[] paramTypes;
	Object whichVariant;
	private HashSet<TypeVariable> typeVariables;
	
	
	///////////////////////////////////////////////////////////////
	// construction
	
	public FunctionSignature(Object whichVariant, Type ...types) {
		assert(types.length >= 1);
		storeParamTypes(types);
		resultType = types[types.length-1];
		this.whichVariant = whichVariant;
		findTypeVariables();
	}
	public FunctionSignature(Object whichVariant, List<Type> paramTypes, Type returnType) {
		this.paramTypes = paramTypes.toArray(new Type[0]);
		
		resultType = returnType;
		this.whichVariant = whichVariant;
		findTypeVariables();
	}
	private void storeParamTypes(Type[] types) {
		paramTypes = new Type[types.length-1];
		for(int i=0; i<types.length-1; i++) {
			paramTypes[i] = types[i];
		}
	}
	
	
	///////////////////////////////////////////////////////////////
	// accessors
	
	public Object getVariant() {
		return whichVariant;
	}
	public Type resultType() {
		return resultType;
	}
	public void printParamTypes() {
		for(int i = 0; i < paramTypes.length; i++) {
			System.out.println(paramTypes[i].infoString());
		}
	}
	public boolean isNull() {
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////
	// main query

	public boolean accepts(List<Type> types) {
		if(types.size() != paramTypes.length) {
			return false;
		}
		resetTypeVariables();
		
		for(int i=0; i<paramTypes.length; i++) {
			if(!assignableTo(paramTypes[i], types.get(i))) {
				return false;
			}
		}		
		return true;
	}
	private boolean assignableTo(Type variableType, Type valueType) {
		if(valueType == PrimitiveType.ERROR && ALL_TYPES_ACCEPT_ERROR_TYPES) {
			return true;
		}	
		return variableType.equivalent(valueType);
	}
	
	// Null object pattern
	private static FunctionSignature neverMatchedSignature = new FunctionSignature(1, PrimitiveType.ERROR) {
		public boolean accepts(List<Type> types) {
			return false;
		}
		public boolean isNull() {
			return true;
		}
	};
	public static FunctionSignature nullInstance() {
		return neverMatchedSignature;
	}
	
	///////////////////////////////////////////////////////////////////
	// Signatures for tan-0 operators
	// this section will probably disappear in tan-1 (in favor of FunctionSignatures)
	
	private static FunctionSignature addSignature = new FunctionSignature(1, PrimitiveType.INTEGER, PrimitiveType.INTEGER, PrimitiveType.INTEGER);
	private static FunctionSignature subtractSignature = new FunctionSignature(1, PrimitiveType.INTEGER, PrimitiveType.INTEGER);
	private static FunctionSignature multiplySignature = new FunctionSignature(1, PrimitiveType.INTEGER, PrimitiveType.INTEGER, PrimitiveType.INTEGER);
	private static FunctionSignature greaterSignature = new FunctionSignature(1, PrimitiveType.INTEGER, PrimitiveType.INTEGER, PrimitiveType.BOOLEAN);

	
	// the switch here is ugly compared to polymorphism.  This should perhaps be a method on Lextant.
	public static FunctionSignature signatureOf(Lextant lextant) {
		assert(lextant instanceof Punctuator);	
		Punctuator punctuator = (Punctuator)lextant;
		
		switch(punctuator) {
		case ADD:		return addSignature;
		case SUBTRACT:  return subtractSignature;
		case MULTIPLY:	return multiplySignature;
		case GREATER:	return greaterSignature;

		default:
			return neverMatchedSignature;
		}
	}
	
	
    private void resetTypeVariables() {
        for(TypeVariable variable: typeVariables) {
            variable.reset();
        }
    }
	
    private void findTypeVariables() {
        typeVariables = new HashSet<TypeVariable>();
        
        for(Type type: paramTypes) {
            
            type.addTypeVariables(typeVariables);
        }
        
        resultType.addTypeVariables(typeVariables);
    }
    
    public List<Type> typeVariableSettings(){
    	List<Type> results = new ArrayList<Type>();
    	for(TypeVariable typeVariable: typeVariables){
    		results.add(typeVariable.concreteType());
    	}
    	return results;
    }
	
    public void setTypeVariables(List<Type> typeVariableSettings){
    	int i = 0;
    	for(TypeVariable typeVariable: typeVariables){
    		typeVariable.setConstraint(typeVariableSettings.get(i));
    		i = i + 1;
    	}
    }



}