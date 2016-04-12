package data;

public class ProduktBatchDTO {
	private int pbID;
	private int status;
	private int receptID;

	public ProduktBatchDTO(int pbID, int status, int receptID){
		this.pbID = pbID;
		this.status = status;
		this.receptID = receptID;
	}

	public int getPbId() {
		return pbID; 
	}

	public void setPbId(int pbId) {
		this.pbID = pbId; 
	}

	public int getStatus() {
		return status; 
	}

	public void setStatus(int status) {
		this.status = status; 
	}

	public int getReceptId() {
		return receptID; 
	}

	public void setReceptId(int receptId) {
		this.receptID = receptId; 
	}

	public String toString() {
		return pbID + "\t" + status + "\t" + receptID; 
	}
}

