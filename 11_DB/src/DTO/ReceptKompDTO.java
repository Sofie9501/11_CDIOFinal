package DTO;

public class ReceptKompDTO {
	int receptID;
	private String ravareName;
	private int raavareID;
	private double tolerence;
	private double nom_netto;
	
	public ReceptKompDTO(int receptID, String ravareName, int raavareID, double tolerence, double nom_netto) {
		this.receptID = receptID;
		this.ravareName = ravareName;
		this.raavareID = raavareID;
		this.tolerence = tolerence;
		this.nom_netto = nom_netto;
	}
	public int getReceptID(){
		return receptID;
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
