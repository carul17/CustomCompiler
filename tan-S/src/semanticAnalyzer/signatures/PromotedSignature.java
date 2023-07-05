package semanticAnalyzer.signatures;

import semanticAnalyzer.types.*;
import java.util.List;
import java.util.ArrayList;

public class PromotedSignature{
	FunctionSignature signature;
	List <Promotion> promotions;
	List<Type> typeVariableSettings;
	public static List<Promotion> nullPromotions = new ArrayList<>();

	public PromotedSignature(FunctionSignature signature, List<Promotion>promotions){
		this.signature = signature;
		this.promotions = new ArrayList<Promotion>(promotions); //copies the list promotions
		this.typeVariableSettings = signature.typeVariableSettings(); //create typeVariableSettings method inside of FunctionSignature
	}
	public static List<PromotedSignature> promotedSignatures(FunctionSignatures signatures, List<Type> types){
		List<PromotedSignature> result = new ArrayList<PromotedSignature>();
		for(FunctionSignature signature : signatures) {
			result.addAll(findAll(signature, types));
		}
		return result;
	}
	
	private static List<PromotedSignature> findAll(FunctionSignature signature, List<Type> types){//missing name of last parameter
		List<PromotedSignature> promotedSignatures = new ArrayList<>();
		List<Promotion> promotions = new ArrayList<Promotion>();
		List<Type> promotedTypes = new ArrayList<Type>();
		for(int i = 0; i < types.size(); i++){
			promotions.add(Promotion.NONE);
			promotedTypes.add(PrimitiveType.NO_TYPE);
		}
		findAllRecursive(signature, types, promotions, promotedTypes, promotedSignatures, 0);
		return promotedSignatures; //I think we need this?

	}
	
	private static void findAllRecursive(FunctionSignature signature, List<Type> types, List<Promotion> promotions, List<Type> promotedTypes, List<PromotedSignature> promotedSignatures, int index){ //let eclipse create this like the params
		if(index >= types.size()){
			if(signature.accepts(promotedTypes)){
				promotedSignatures.add(new PromotedSignature(signature, promotions));
	
			}	
			return;
		}
		Type type = types.get(index);
		for(Promotion promotion: Promotion.values()){
			
			if(promotion.appliesTo(type)){
				promotedTypes.set(index, promotion.apply(type));
				System.out.println(promotion.apply(type).infoString());
			}
			findAllRecursive(signature, types, promotions, promotedTypes, promotedSignatures, index + 1);
		}
		promotedTypes.set(index, type); //might be somewhere else 
		return;
	}
	
	public int numPromotions(){
		int result = 0;
		for(Promotion promotion: promotions){
			if(!promotion.isNull()){
					result = result + 1;
			}
		}
		return result;
	}

	
	public Type resultType(){
		setTypeVariables();
		return signature.resultType().concreteType();
	}

	private void setTypeVariables(){
		signature.setTypeVariables(typeVariableSettings); //not sure where this is being changed
	}
	
	
	private static PromotedSignature neverMatchedSignature = new PromotedSignature(FunctionSignature.nullInstance(), nullPromotions ) {
		public boolean accepts(List<Type> types) {
			System.out.println("hi");
			return false;
		}
		public boolean isNull() {
			return true;
		}
	};
	public static PromotedSignature nullInstance() {
		return neverMatchedSignature;
	}
	
	
	public boolean accepts(List<Type> types) {
		return this.signature.accepts(types);
		}
	private boolean assignableTo(Type variableType, Type valueType) {
		if(valueType == PrimitiveType.ERROR) {
			return true;
		}	
		return variableType.equivalent(valueType);
	}
	public Object getVariant() {
		// TODO Auto-generated method stub
		return signature.getVariant();
	}
	public Promotion promotion(int i) {
		// TODO Auto-generated method stub
		return promotions.get(i);
	}





}

