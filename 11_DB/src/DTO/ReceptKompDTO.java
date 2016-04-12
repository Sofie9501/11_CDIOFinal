package DTO;

public class ReceptKompDTO{
	private int receptID;
	private int raavareID;
	private double nomNetto;
	private double tolerance;

	public ReceptKompDTO(int receptID, int raavareID, double nomNetto, double tolerance){
		this.receptID = receptID;
		this.raavareID = raavareID;
		this.nomNetto = nomNetto;
		this.tolerance = tolerance;
	}

	public int getReceptId() {
		return receptID; 
	}

	public void setReceptId(int receptID) {
		this.receptID = receptID; 
	}

	public int getRaavareId() {
		return raavareID; 
	}

	public void setRaavareId(int raavareID) {
		this.raavareID = raavareID; 
	}

	public double getNomNetto() {
		return nomNetto; 
	}

	public void setNomNetto(double nomNetto) {
		this.nomNetto = nomNetto; 
	}

	public double getTolerance() {
		return tolerance; 
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance; 
	}

	public String toString() { 
		return receptID + "\t" + raavareID + "\t" + nomNetto + "\t" + tolerance; 
	}
}
