package data;

public class ProduktBatchKompDTO {
	int pbID;
	int rbID;
	double tara;
	double netto;
	int oprID;

	public ProduktBatchKompDTO(int pbId, int rbId, double tara, double netto, int oprId){
		this.pbID = pbId;
		this.rbID = rbId;
		this.tara = tara;
		this.netto = netto;
		this.oprID = oprId;
	}
	
	public int getPbId() {
		return pbID; 
		}
	
	public void setPbId(int pbId) {
		this.pbID = pbId; 
		}
	
	public int getRbId() {
		return rbID; 
		}
	
	public void setRbId(int rbId) {
		this.rbID = rbId; 
		}
	
	public double getTara() {
		return tara; 
		}
	
	public void setTara(double tara) {
		this.tara = tara; 
		}
	
	public double getNetto() {
		return netto; 
		}
	
	public void setNetto(double netto) {
		this.netto = netto; 
		}
	
	public int getOprId() {
		return oprID; 
		}
	
	public void setOprId(int oprId) {
		this.oprID = oprId; 
		}
	
	public String toString() { 
		return pbID + "\t" + rbID +"\t" + tara +"\t" + netto + "\t" + oprID ; 
	}
}
