package model;

public class ClassDay {
	protected Day day;
	protected ClassHour[] hours;
	private int numberOfClasses;
	
	public ClassDay(Day day) {
		this.day = day;
		hours = new ClassHour[7];
		
		for (int i = 0; i < hours.length; i++) {
			hours[i] = new ClassHour(this.day);
 		}
		
		numberOfClasses = 0;
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
		return numberOfClasses > 0;
	}
	
	public void addHour(int start, int end, Subject subject, Day day) {
		int index = getIndex(start);
		
		hours[index] = new ClassHour(start, end, subject, day);
		numberOfClasses++;
	}
	
	public void addHour(ClassHour hour) {
		hours[getIndex(hour.start)] = hour;
		numberOfClasses++;
	}
	
	public void deleteHour(int start) {
		int index = getIndex(start);
		
		hours[index] = new ClassHour(this.day);
		numberOfClasses--;
	}
	
	public void deleteHour(ClassHour hour) {
		deleteHour(hour.start);
	}
	
	public int getHuecos() {
		int huecos = 0;
		for(int i = 0; i < 7; i ++) {
			if(hours[i].start != -1) {
				boolean classBefore = false;
				
				for(int j = 0; j < i; j++) {
					if(hours[j].start != -1) {
						classBefore = true;
						break;
					}
				}
				
				if(classBefore) {
					boolean classAfter = false;
					
					for(int j = i + 1; j < 7; j++) {
						if(hours[j].start != -1) {
							classAfter = true;
							break;
						}
					}
					
					if(classBefore && classAfter)
						huecos++;
				}
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
}
