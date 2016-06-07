package dk.dtu.cdiofinal.shared;

import java.io.Serializable;

public class IngredientDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
	private String name;
	private String supplier;
	private boolean active;
	
	
	public IngredientDTO(){
		
	}
	
	
	public IngredientDTO(int ID, String name, String supplier, boolean active){
		this.ID = ID;
		this.name = name;
		this.supplier = supplier;
		this.active = active;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "IngredientDTO [ID=" + ID + ", name=" + name + ", supplier=" + supplier + ", active=" + active + "]";
	}
	
	

}
