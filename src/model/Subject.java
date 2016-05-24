package model;

public class Subject {
	protected String name;
	
	public Subject(String subjectName) {
		name = subjectName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
