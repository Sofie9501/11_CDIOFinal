package DTO;

public class ReceptKompDTO {
	private String ravareName;
	private int raavareID;
	private double tolerence;
	private double nom_netto;
	
	public ReceptKompDTO(String ravareName, int raavareID, double tolerence, double nom_netto) {
		this.ravareName = ravareName;
		this.raavareID = raavareID;
		this.tolerence = tolerence;
		this.nom_netto = nom_netto;
	}

	public String getRavareName() {
		return ravareName;
	}
	
	public int getRaavareID(){
		return raavareID;
	}

	public double getTolerence() {
		return tolerence;
	}

	public double getNom_netto() {
		return nom_netto;
	}
	
	@Override
	public String toString() {
		return "ReceptKompDTO [ravareName=" + ravareName + ", tolerence=" + tolerence + ", nom_netto=" + nom_netto
				+ "]";
	}
	
	
	
}
