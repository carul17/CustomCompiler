package semanticAnalyzer.signatures;

import semanticAnalyzer.types.*;
import java.util.List;
import java.util.ArrayList;

public class PromotedSignature{
	FunctionSignature signature;
	List <Promotion> promotions;
	List<Type> typeVariableSettings;
	public static List<Promotion> nullPromotions = new ArrayList<>();
	public static List<Type> nullTypes = new ArrayList<>();
	static boolean matchFound = false;
	List<Type> promotedTypesAfterApply;

	public PromotedSignature(FunctionSignature signature, List<Promotion>promotions, List<Type> promotedTypes){
		this.signature = signature;
		this.promotions = new ArrayList<Promotion>(promotions);
		this.promotedTypesAfterApply = promotedTypes;
		this.typeVariableSettings = signature.typeVariableSettings(); //create typeVariableSettings method inside of FunctionSignature
	}
	public static List<PromotedSignature> promotedSignatures(FunctionSignatures signatures, List<Type> types){
		List<PromotedSignature> result = new ArrayList<PromotedSignature>();
		
		//check level 1
		for(FunctionSignature signature : signatures) {
			if(signature.accepts(types)) {
				List<Promotion> promotions = new ArrayList<Promotion>();
				for(int i = 0; i < types.size(); i++){
					promotions.add(Promotion.NONE);
				}
				result.add(new PromotedSignature(signature, promotions, types));
				return result;
			}
		}
		
		//check level 2 and 3
		for(FunctionSignature signature : signatures) {
			
			result.addAll(findAll(signature, types));
			if(matchFound) {
				//break;
			}
			//System.out.println("Entering next signature of operator");
		}
		
		return result;
	}
	
	private static List<PromotedSignature> findAll(FunctionSignature signature, List<Type> types){//missing name of last parameter
		List<PromotedSignature> promotedSignatures = new ArrayList<>();
		List<Promotion> promotions = new ArrayList<Promotion>();
		List<Type> promotedTypes = new ArrayList<Type>();
		for(int i = 0; i < types.size(); i++){
			promotions.add(Promotion.NONE);
			promotedTypes.add(types.get(i));
		}
		
		findAllRecursive(signature, types, promotions, promotedTypes, promotedSignatures, 0);
		return promotedSignatures; //I think we need this?

	}
	
	private static boolean findAllRecursive(FunctionSignature signature, List<Type> types, List<Promotion> promotions, List<Type> promotedTypes, List<PromotedSignature> promotedSignatures, int index){ //let eclipse create this like the params
		if(index >= types.size()){
			if(signature.accepts(promotedTypes)){
				
				//print for debugging
				/*System.out.println("base case: " + signature.accepts(promotedTypes));
				for(Type promotedType : promotedTypes) {
					System.out.println("promoted types: " + promotedType.infoString());
				}*/
				
				PromotedSignature found = new PromotedSignature(signature, promotions, promotedTypes);
				promotedSignatures.add(found);
				//System.out.println(found.resultType().infoString());
				return true;
			}	
			return false;
		}
		Type type = types.get(index);
		
		for(Promotion promotion: Promotion.values()){
			 //System.out.println("enter for each promotion");
			
			if(promotion.appliesTo(type)){
				
				promotedTypes.set(index, promotion.apply(type));
				promotions.set(index, promotion);
				//System.out.println(type.infoString() + promotion.apply(type).infoString());
			}
			
			if(findAllRecursive(signature, types, promotions, promotedTypes, promotedSignatures, index + 1)) {
				
				matchFound = true;
				//System.out.println("matchfound: " + matchFound);
				return true;
			}
			
		}
		
		promotedTypes.set(index, type);
		//might be somewhere else 
		return false;
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
	
	
	private static PromotedSignature neverMatchedSignature = new PromotedSignature(FunctionSignature.nullInstance(), nullPromotions, nullTypes) {
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
		//signature.setTypeVariables(typeVariableSettings);
		
        return signature.accepts(this.promotedTypesAfterApply);
	}
	
	public Object getVariant() {
		
		return signature.getVariant();
	}
	public Promotion promotion(int i) {
		
		return promotions.get(i);
	}





}

