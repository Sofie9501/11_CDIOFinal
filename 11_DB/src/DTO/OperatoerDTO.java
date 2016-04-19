package DTO;

public class OperatoerDTO {
	private int oprID;
	private int rolle;
	private String oprNavn;
	private String cpr;
	private String password;

	public OperatoerDTO(int oprID, String oprNavn, String cpr, String password, int rolle){
		this.oprID = oprID;
		this.rolle = rolle;
		this.oprNavn = oprNavn;
		this.cpr = cpr;
		this.password = password;
	}

	public OperatoerDTO(OperatoerDTO opr){
		this.oprID = opr.getOprID();
		this.rolle = opr.getRolle();
		this.oprNavn = opr.getOprNavn();
		this.cpr = opr.getCpr();
		this.password = opr.getPassword();
	}

	public int getOprID(){
		return oprID;
	}

	public void setOprID(int oprID){
		this.oprID = oprID;
	}
	public int getRolle(){
		return rolle;
	}
	public void setRolle(int rolle){
		this.rolle = rolle;
	}

	public String getOprNavn() { 
		return oprNavn; 
	}

	public void setOprNavn(String oprNavn) {
		this.oprNavn = oprNavn; 
	}

	public String getCpr() {
		return cpr; 
	}

	public void setCpr(String cpr) {
		this.cpr = cpr; 
	}

	public String getPassword() { return password; 
	}

	public void setPassword(String password) {
		this.password = password; 
	}

	public String toString() {
		return oprID + "\t" + oprNavn + "\t" + cpr + "\t" + password; 
	}
}
