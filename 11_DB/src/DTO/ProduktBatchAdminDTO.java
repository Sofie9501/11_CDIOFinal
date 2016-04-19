package DTO;

import java.sql.Date;

public class ProduktBatchAdminDTO {
	private long antal;
	private int receptId;
	private int pbId;
	private Date date;
	private int status;
	
	public ProduktBatchAdminDTO(int pbId, int receptId, long antal, Date date, int status){
		this.antal= antal;
		this.receptId = receptId;
		this.pbId = pbId;
		this.date = date;
		this.status = status;
	}

	public long getAntal() {
		return antal;
	}
	
	public int getReceptId() {
		return receptId;
	}
	
	public int getPbId() {
		return pbId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public int getSatus() {
		return status;
	}
	
	public String toString(){
		return antal + "\t" + receptId + "\t" + pbId + "\t" + date + "\t" + status;
	}

	
	
	

}
