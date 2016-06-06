package dk.dtu.cdiofinal.shared;

public class RecipeComponentDTO {
	
	private int recipeComp_ID;
	private int ingredient_ID;
	private double nom_netto;
	private double tolerance;
	
	public RecipeComponentDTO(int recipeComp_ID, int ingredient_ID, double nom_netto, double tolerance) {
		super();
		this.recipeComp_ID = recipeComp_ID;
		this.ingredient_ID = ingredient_ID;
		this.nom_netto = nom_netto;
		this.tolerance = tolerance;
	}

	public int getRecipeComp_ID() {
		return recipeComp_ID;
	}

	public void setRecipeComp_ID(int recipeComp_ID) {
		this.recipeComp_ID = recipeComp_ID;
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
	
	
}
