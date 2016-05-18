package model;

public class Teacher {
	protected String name;
	protected boolean barco;
	protected boolean bueno;
	protected boolean mamon;
	
	public Teacher(String name) {
		this.name = name;
	}
	
	public void setValues(boolean barco, boolean bueno, boolean mamon) {
		this.barco = barco;
		this.bueno = bueno;
		this.mamon = mamon;
	}
	
	public boolean isBarco() {
		return barco;
	}
	
	public boolean isBueno() {
		return bueno;
	}
	
	public boolean isMamon() {
		return mamon;
	}
}
