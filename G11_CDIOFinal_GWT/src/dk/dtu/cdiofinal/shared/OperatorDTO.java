package dk.dtu.cdiofinal.shared;

import java.io.Serializable;

public class OperatorDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int oprID;
	private int role;
	private String name;
	private String cpr;
	private String password;
	private boolean isActive;
	
	public OperatorDTO(int oprID, String name, String cpr, 
			String password,int role, boolean isActive){
		this.oprID = oprID;
		this.role = role;
		this.name = name;
		setCpr(cpr);
		this.password = password;
		this.isActive = isActive;
	}

	public int getOprID(){
		return oprID;
	}

	public void setOprID(int oprID){
		this.oprID = oprID;
	}
	public int getRole(){
		return role;
	}
	public void setRole(int role){
		this.role = role;
	}

	public String getName() { 
		return name; 
	}

	public void setName(String name) {
		this.name = name; 
	}

	public String getCpr(){
		return cpr; 
	}

	public void setCpr(String cpr) {
		this.cpr = cpr.replace("-", "");
	}

	public String getPassword() { 
		return password; 
	}

	public void setPassword(String password) {
		this.password = password; 
	}
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String toString() {
		return oprID + "\t" + name + "\t" + cpr + "\t" + password; 
	}
}
