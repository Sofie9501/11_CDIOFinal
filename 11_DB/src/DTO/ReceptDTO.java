package DTO;

public class ReceptDTO {
	private int receptID;
	private String receptNavn;

	public ReceptDTO(int receptId, String receptNavn){
		this.receptID = receptId;
		this.receptNavn = receptNavn;
	}

	public int getReceptId() {
		return receptID; 
	}

	public void setReceptId(int receptID) {
		this.receptID = receptID; 
	}

	public String getReceptNavn() {
		return receptNavn; 
	}

	public void setReceptNavn(String receptNavn) {
		this.receptNavn = receptNavn; 
	}

	public String toString() { 
		return receptID + "\t" + receptNavn; 
	}
}
