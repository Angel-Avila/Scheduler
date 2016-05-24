package model;

public class ClassHour {
	protected int start;
	protected int end;
	protected Subject subject;
	protected Day day;
	
	public ClassHour(Day day) {
		start = -1;
		end = -1;
		this.day = day;
	}
	
	public ClassHour(int start, int end, Subject subject, Day day) {
		this.start = start;
		this.end = end;
		this.subject = subject;
		this.day = day;
	}

	public String toString() {
		return getDayString() + ": " + (start >= 10 ? start: ("0" + start)) + ":00-" + (end >= 10 ? end: ("0" + end)) + ":00";
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}
	
	public String getDayString() {
		if(day == Day.MONDAY)
			return "Lunes";
		if(day == Day.TUESDAY)
			return "Martes";
		if(day == Day.WEDNESDAY)
			return "Miércoles";
		if(day == Day.THURSDAY)
			return "Jueves";
		if(day == Day.FRIDAY)
			return "Viernes";
		return "Sábado";
	}
}
