package dk.dtu.cdiofinal.shared;

public class RecipeDTO {
	
	private int ID;
	private String name;
	private boolean isActive;
	
	public RecipeDTO(int ID, String name, boolean isActive){
		this.ID = ID;
		this.name = name;
		this.isActive = isActive;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		this.isActive = active;
	}

	@Override
	public String toString() {
		return "ID = " + ID + "\tname = " + name + "\tisActive = " + isActive;
	}

	
}
