package DTO;

public class RecipeCompDTO {
	
	private float net;
	private float tolerance;
	
	public RecipeCompDTO(float net, float tolerance) {
		super();
		this.net = net;
		this.tolerance = tolerance;
	}

	public float getNet() {
		return net;
	}

	public void setNet(float net) {
		this.net = net;
	}

	public float getTolerance() {
		return tolerance;
	}

	public void setTolerance(float tolerance) {
		this.tolerance = tolerance;
	}
	
	
	

}
