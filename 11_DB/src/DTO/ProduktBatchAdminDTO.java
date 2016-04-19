package DTO;

import java.sql.Date;

public class ProduktBatchAdminDTO {
	private long antal_komp;
	private long antal_done;
	private String recept_navn;
	private int receptId;
	private int pbId;
	private Date date;
	private int status;
	
	public ProduktBatchAdminDTO(int pbId, int receptId, String receept_navn, long antal_komp, long antal_done, Date date, int status){
		this.receptId = receptId;
		this.pbId = pbId;
		this.date = date;
		this.status = status;
		this.recept_navn=receept_navn;
		this.antal_komp=antal_komp;
		this.antal_done=antal_done;
	}

	public long getAntal_komp() {
		return antal_komp;
	}
	
	public long getAntal_done(){
		return antal_done;
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
	
	public String getRecept_navn(){
		return recept_navn;
	}
	
	public String toString(){
		return pbId + "\t" + receptId + "\t" + recept_navn + "\t" + antal_komp + "\t" + antal_done + "\t" + date + "\t" + status;
	}

	
	
	

}
