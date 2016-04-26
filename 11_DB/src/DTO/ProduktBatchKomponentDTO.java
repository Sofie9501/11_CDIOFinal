package DTO;

public class ProduktBatchKomponentDTO {
	private int pbId;
	private int rbId;
	private double tara;
	private double netto;
	private int opr_id;
	public ProduktBatchKomponentDTO(int pbId, int rbId, double tara, double netto, int opr_id) {
		super();
		this.pbId = pbId;
		this.rbId = rbId;
		this.tara = tara;
		this.netto = netto;
		this.opr_id = opr_id;
	}
	public int getPbId() {
		return pbId;
	}
	public void setPbId(int pbId) {
		this.pbId = pbId;
	}
	public int getRbId() {
		return rbId;
	}
	public void setRbId(int rbId) {
		this.rbId = rbId;
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
	public int getOpr_id() {
		return opr_id;
	}
	public void setOpr_id(int opr_id) {
		this.opr_id = opr_id;
	}
	
	
}
