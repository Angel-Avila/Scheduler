package model;

import java.io.Serializable;

public class Teacher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected boolean barco;
	protected boolean bueno;
	protected boolean mamon;
	
	public Teacher(String name) {
		this.name = name;
	}
	
	public Teacher(String name, boolean barco, boolean bueno, boolean mamon) {
		this.name = name;
		setValues(barco, bueno, mamon);
	}
	
	public void setValues(boolean barco, boolean bueno, boolean mamon) {
		this.barco = barco;
		this.bueno = bueno;
		this.mamon = mamon;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBarco(boolean barco) {
		this.barco = barco;
	}

	public void setBueno(boolean bueno) {
		this.bueno = bueno;
	}

	public void setMamon(boolean mamon) {
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
