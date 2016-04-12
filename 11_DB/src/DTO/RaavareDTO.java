package DTO;

public class RaavareDTO {
	private int raavareID;                     
	private String raavareNavn;                
	private String leverandoer;         

	public RaavareDTO(int raavareID, String raavareNavn, String leverandoer){
		this.raavareID = raavareID;
		this.raavareNavn = raavareNavn;
		this.leverandoer = leverandoer;
	}

	public int getRaavareID() { 
		return raavareID; 
	}

	public void setRaavareId(int raavareId) {
		this.raavareID = raavareId; 
	}

	public String getRaavareNavn() {
		return raavareNavn; 
	}

	public void setRaavareNavn(String raavareNavn) {
		this.raavareNavn = raavareNavn; 
	}

	public String getLeverandoer() {
		return leverandoer;
	}

	public void setLeverandoer(String leverandoer) {
		this.leverandoer = leverandoer; 
	}

	public String toString() { 
		return raavareID + "\t" + raavareNavn +"\t" + leverandoer; 
	}
}
