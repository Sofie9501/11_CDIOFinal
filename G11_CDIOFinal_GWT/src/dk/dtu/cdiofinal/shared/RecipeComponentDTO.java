package dk.dtu.cdiofinal.shared;

import java.io.Serializable;

public class RecipeComponentDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8726826892932478063L;
	private int recipe_ID;
	private int ingredient_ID;
	private double nom_netto;
	private double tolerance;
	private String ingredientName;
	
	public RecipeComponentDTO(){
		
	}
	
	public RecipeComponentDTO(int recipe_ID, int ingredient_ID, String ingredientName , 
					double tolerance, double nom_netto) {
		this.recipe_ID = recipe_ID;
		this.ingredient_ID = ingredient_ID;
		this.ingredientName = ingredientName;
		this.nom_netto = nom_netto;
		this.tolerance = tolerance;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public int getRecipe_ID() {
		return recipe_ID;
	}

	public void setRecipe_ID(int recipe_ID) {
		this.recipe_ID = recipe_ID;
	}

	public int getIngredient_ID() {
		return ingredient_ID;
	}

	public void setIngredient_ID(int ingredient_ID) {
		this.ingredient_ID = ingredient_ID;
	}

	public double getNom_netto() {
		return nom_netto;
	}

	public void setNom_netto(double nom_netto) {
		this.nom_netto = nom_netto;
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

	@Override
	public String toString() {
		return "recipeComp ID = " + recipe_ID + "\tIngredient ID = " + ingredient_ID
				+ "\tnom_netto = " + nom_netto + "\tTolerance = " + tolerance;
	}
	
	
}
