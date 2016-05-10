package DTO;

public class RaavareBatchDTO {
	private int rbID;
	private String raavareNavn;
	private double maengde;
	private int raavareID;

	public RaavareBatchDTO(int rbID, String raavareNavn, double maengde, int raavareID){
		this.rbID = rbID;
		this.raavareNavn = raavareNavn;
		this.maengde = maengde;
		this.raavareID = raavareID;
	}

	public int getRbID() {
		return rbID;
	}

	public String getRaavareNavn() {
		return raavareNavn;
	}

	public double getMaengde() {
		return maengde;
	}

	public int getRaavareID() {
		return raavareID;
	}

	@Override
	public String toString() {
		return "RaavareBatchDTO [rbID=" + rbID + ", raavareNavn=" + raavareNavn + ", maengde=" + maengde
				+ ", raavareID=" + raavareID + "]";
	}

}
