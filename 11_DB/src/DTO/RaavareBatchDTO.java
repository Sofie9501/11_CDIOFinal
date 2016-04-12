package DTO;

public class RaavareBatchDTO{
	private int rbID;
	private int raavareID;
	private double maengde;

	public RaavareBatchDTO(int rbID, int raavareId, double maengde){
		this.rbID = rbID;
		this.raavareID = raavareId;
		this.maengde = maengde;
	}

	public int getRbId() {
		return rbID; 
	}

	public void setRbId(int rbID) {
		this.rbID = rbID; 
	}

	public int getRaavareId() {
		return raavareID; 
	}

	public void setRaavareId(int raavareId) {
		this.raavareID = raavareId; 
	}

	public double getMaengde() {
		return maengde; 
	}

	public void setMaengde(double maengde) {
		this.maengde = maengde; 
	}

	public String toString() { 
		return rbID + "\t" + raavareID +"\t" + maengde; 
	}
}
