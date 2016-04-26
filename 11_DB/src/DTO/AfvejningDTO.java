package DTO;

public class AfvejningDTO {
	private int pbId;
	private int raavareId;
	private String raavareNavn;
	private double nomNetto;
	private double tolerance;
	
	public AfvejningDTO(int pbId, int raavareId, String raavareNavn, double nomNetto, double tolerance) {
		super();
		this.pbId = pbId;
		this.raavareId = raavareId;
		this.raavareNavn = raavareNavn;
		this.nomNetto = nomNetto;
		this.tolerance = tolerance;
	}

	public int getPbId() {
		return pbId;
	}

	public int getRaavareId() {
		return raavareId;
	}

	public String getRaavareNavn() {
		return raavareNavn;
	}

	public double getNomNetto() {
		return nomNetto;
	}

	public double getTolerance() {
		return tolerance;
	}

	@Override
	public String toString() {
		return "AfvejningDTO [pbId=" + pbId + ", raavareId=" + raavareId + ", raavareNavn=" + raavareNavn
				+ ", nomNetto=" + nomNetto + ", tolerance=" + tolerance + "]";
	}
	
	
}