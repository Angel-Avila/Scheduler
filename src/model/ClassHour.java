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
}
