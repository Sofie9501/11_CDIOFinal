package dk.dtu.cdiofinal.shared;

import java.util.ArrayList;

public class DTOVerifier {
	/************************************************************************
	 * 
	 * All Metodes here are used to verify the content of a specific DTO
	 * 
	 ************************************************************************/

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
				FieldVerifier.cprValid(dto.getCpr()) &&
				FieldVerifier.passwordValid(dto.getPassword())){
			return true;
		}
		else{
			return false;
		}
	}

	public static boolean VerifyProductBatchDTO(ProductBatchDTO dto){
		if (FieldVerifier.numberValid(dto.getPb_ID()) &&
				FieldVerifier.numberValid(dto.getR_ID()) ){
			return true;
		}
		else{
			return false;
		}
	}

	public static boolean VerifyRecipeDTO(RecipeDTO dto){
		if (FieldVerifier.numberValid(dto.getID()) &&
				FieldVerifier.nameValid(dto.getName()) &&
				checkList(dto.getComponents())){
			return true;
		}
		else{
			return false;
		}
	}

	private static boolean VerifyRecipeComponentDTO(RecipeComponentDTO dto) {
		if (FieldVerifier.numberValid(dto.getRecipe_ID()) &&
				FieldVerifier.numberValid(dto.getIngredient_ID()) &&
				FieldVerifier.amountValid(dto.getNom_netto()) &&
				FieldVerifier.amountValid(dto.getTolerance())){
			return true;
		}
		else{
			return false;
		}
	}
	
	// Method used to Verify a ArrayList of ComponentDTOs
	private static boolean checkList(ArrayList<RecipeComponentDTO> list) {
		for (int i = 0; i < list.size(); i++) {
			if (!VerifyRecipeComponentDTO(list.get(i))){
				return false;
			}
		}
		return true;
	}

}
