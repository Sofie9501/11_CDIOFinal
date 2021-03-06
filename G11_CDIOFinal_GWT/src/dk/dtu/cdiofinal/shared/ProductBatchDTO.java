package dk.dtu.cdiofinal.shared;

import java.io.Serializable;
import java.sql.Date;

public class ProductBatchDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int countComponents;
	private int countFinished;
	private String name;
	private int ingredient_ID;
	private int productBatch_ID;
	private Date start_date;
	private Date end_date;
	private int status;
	private boolean isActive;
	
	public ProductBatchDTO(){
		
	}
	public ProductBatchDTO(int pb_ID, int r_ID, String name, int countComponents,
			int countFinished, Date start_date,Date end_date, int status, boolean isActive) {
		this.countComponents = countComponents;
		this.countFinished = countFinished;
		this.name = name;
		this.ingredient_ID = r_ID;
		this.productBatch_ID = pb_ID;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
		this.isActive = isActive;
		
	}

	public int getCountComponents() {
		return countComponents;
	}

	public void setCountComponents(int countComponents) {
		this.countComponents = countComponents;
	}

	public int getCountFinished() {
		return countFinished;
	}

	public void setCountFinished(int countFinished) {
		this.countFinished = countFinished;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getR_ID() {
		return ingredient_ID;
	}
	
	public void setR_ID(int r_ID){
		this.ingredient_ID = r_ID;
	}

	public int getPb_ID() {
		return productBatch_ID;
	}

	public void setPb_ID(int pb_ID) {
		this.productBatch_ID = pb_ID;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public boolean isActive(){
		return isActive;
	}
	public void setActive(boolean b){
		this.isActive();
	}
	
	

	@Override
	public String toString() {
		return "ProductBatch ID = " + productBatch_ID + "\tIngredient ID = " + ingredient_ID + "\tname = " + name +  "\nStart date = " + start_date + "\tEnd date = " + end_date +
				"\tComponents = " + countComponents + "\tFinished = " + countFinished;
	}

	

}
