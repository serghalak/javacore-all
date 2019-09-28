package horsman.v11.t1.ch05;

public enum Size {
	
	SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");
	
	private String abbreviation;
	
	Size(String abbreviation){ 
		this.abbreviation = abbreviation;
	}
	
	public String getAЬbreviation() { 
		return abbreviation;
	}
}
