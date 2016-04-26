package DTO;

public class ReceptKompDTO {
	private String ravareName;
	private double tolerence;
	private double nom_netto;
	
	public ReceptKompDTO(String ravareName, double tolerence, double nom_netto) {
		this.ravareName = ravareName;
		this.tolerence = tolerence;
		this.nom_netto = nom_netto;
	}

	public String getRavareName() {
		return ravareName;
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
