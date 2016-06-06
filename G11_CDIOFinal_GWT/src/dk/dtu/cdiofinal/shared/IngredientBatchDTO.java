package dk.dtu.cdiofinal.shared;

public class IngredientBatchDTO {
	
	private int ingredientBatch_ID;
	private String name;
	private int ingredient_ID;
	private double amount;
	private boolean isActive;
	
	public IngredientBatchDTO(int ingredientBatch_ID, String name, int ingredient_ID, double amount, boolean isActive) {
		this.ingredientBatch_ID = ingredientBatch_ID;
		this.name = name;
		this.ingredient_ID = ingredient_ID;
		this.amount = amount;
		this.isActive = isActive;
	}

	public int getIngredientBatch_ID() {
		return ingredientBatch_ID;
	}

	public void setIngredientBatch_ID(int ingredientBatch_ID) {
		this.ingredientBatch_ID = ingredientBatch_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIngredient_ID() {
		return ingredient_ID;
	}

	public void setIngredient_ID(int ingredient_ID) {
		this.ingredient_ID = ingredient_ID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "IngredientBatchDTO [ingredientBatch_ID=" + ingredientBatch_ID + ", name=" + name + ", ingredient_ID="
				+ ingredient_ID + ", amount=" + amount + ", isActive=" + isActive + "]";
	}

	

}
