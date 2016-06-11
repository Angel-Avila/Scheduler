package model;

import java.io.Serializable;

public class ClassDay implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Day day;
	protected ClassHour[] hours;
	
	public ClassDay(Day day) {
		this.day = day;
		hours = new ClassHour[7];
		
		for (int i = 0; i < hours.length; i++) {
			hours[i] = new ClassHour(this.day);
 		}
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		for(ClassHour hour: hours) {
			if(hour.start != -1) {
				str.append(hour.toString());
				str.append('\n');
			}
		}
		
		return str.toString();
	}
	
	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public ClassHour[] getHours() {
		return hours;
	}

	public void setHours(ClassHour[] hours) {
		this.hours = hours;
	}

	public boolean isGoing() {
		for(ClassHour hour: hours)
			if(hour.start != -1)
				return true;
		
		return false;
	}
	
	public void addHour(int start, int end, Subject subject, Day day) {
		int index = getIndex(start);
		
		hours[index] = new ClassHour(start, end, subject, day);
	}
	
	public void addHour(ClassHour hour) {
		hours[getIndex(hour.start)] = hour;
	}
	
	public void deleteHour(int start) {
		int index = getIndex(start);
		
		hours[index] = new ClassHour(this.day);
	}
	
	public void deleteHour(ClassHour hour) {
		deleteHour(hour.start);
	}
	
	public int getHuecos() {
		int huecos = 0;
		for(int i = 0; i < 6; i ++) {
			
			if(hours[i].start != -1) {
				
				int tempHuecos = 0;
				boolean classAfter = false;
				
				for(int j = i + 1; j < 7; j++) {
					if(hours[j].start == -1)
						tempHuecos++;
					else {
						classAfter = true;
						break;
					}
				}
				
				if(classAfter)
					huecos += tempHuecos;
			}
		}
		return huecos;
	}
	
	private int getIndex(int start) {
		int index = 0;
		
		if(start == 9) 
			index = 1;
		else if(start == 11) 
			index = 2;
		else if(start == 13) 
			index = 3;
		else if(start == 15) 
			index = 4;
		else if(start == 18) 
			index = 5;
		else if(start == 20) 
			index = 6;
		
		return index;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
