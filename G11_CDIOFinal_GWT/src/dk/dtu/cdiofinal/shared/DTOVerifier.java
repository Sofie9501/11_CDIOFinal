package dk.dtu.cdiofinal.shared;

public class DTOVerifier {
	
	public static boolean VerifyIngredientBatchDTO(IngredientBatchDTO dto){
		if (FieldVerifier.numberValid(dto.getIngredientBatch_ID()) &&
			FieldVerifier.nameValid(dto.getName()) &&
			FieldVerifier.numberValid(dto.getIngredient_ID())&&
			FieldVerifier.amountValid(dto.getAmount())){
			return true;
		}
		else{
			return false;
		}	
	}
	
	public static boolean VerifyIngredientDTO(IngredientDTO dto){
		if (FieldVerifier.numberValid(dto.getID()) && 
			FieldVerifier.nameValid(dto.getName()) &&
			FieldVerifier.nameValid(dto.getSupplier())){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean VerifyOperatorDTO(OperatorDTO dto){
		if (FieldVerifier.numberValid(dto.getOprID())&&
			FieldVerifier.roleValid(dto.getRole())&&
			FieldVerifier.nameValid(dto.getName()) &&
			FieldVerifier.cprValid(dto.getCpr()))
	}

}
