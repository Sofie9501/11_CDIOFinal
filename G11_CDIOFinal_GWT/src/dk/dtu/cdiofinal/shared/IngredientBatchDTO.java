package dk.dtu.cdiofinal.shared;

import java.io.Serializable;

public class IngredientBatchDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ingredientBatchID;
	private String name;
	private int ingredientID;
	private double amount;
	private boolean isActive;
	private String date;
	
	public IngredientBatchDTO(){
		
	}
	
	public IngredientBatchDTO(int ingredientBatch_ID, String name, int ingredient_ID, double amount, boolean isActive
			, String date){
		this.ingredientBatchID = ingredientBatch_ID;
		this.name = name;
		this.ingredientID = ingredient_ID;
		this.amount = amount;
		this.isActive = isActive;
		this.date = date;
	}

	public int getIngredientBatch_ID() {
		return ingredientBatchID;
	}

	public void setIngredientBatch_ID(int ingredientBatch_ID) {
		this.ingredientBatchID = ingredientBatch_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIngredient_ID() {
		return ingredientID;
	}

	public void setIngredient_ID(int ingredient_ID) {
		this.ingredientID = ingredient_ID;
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
	
	public String getDate(){
		return date;
	}

	@Override
	public String toString() {
		return "IngredientBatchDTO [ingredientBatch_ID=" + ingredientBatchID + ", name=" + name + ", ingredient_ID="
				+ ingredientID + ", amount=" + amount + ", isActive=" + isActive +  ", date=" + date + "]";
	}

	

}
