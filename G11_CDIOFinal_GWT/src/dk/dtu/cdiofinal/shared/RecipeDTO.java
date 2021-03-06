package dk.dtu.cdiofinal.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipeDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private int ID;
	private String name;
	private boolean isActive;
	private ArrayList<RecipeComponentDTO> componentList;
	
	public RecipeDTO(){
		componentList =  new ArrayList<RecipeComponentDTO>();
	}
	
	public RecipeDTO(int ID, String name, boolean isActive){
		this();
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
	
	
	public void addComponent(RecipeComponentDTO comp){
		componentList.add(comp);
	}
	
	public ArrayList<RecipeComponentDTO> getComponents(){
		return componentList;
	}

	@Override
	public String toString() {
		return "ID = " + ID + "\tname = " + name + "\tisActive = " + isActive;
	}

	
}
