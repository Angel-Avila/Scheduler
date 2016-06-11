package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Subject subject;
	protected Teacher teacher;
	protected ArrayList<ClassHour> classes;
	
	public ClassSchedule() {
		
	}
	
	public ClassSchedule(Subject subject, ArrayList<ClassHour> classes) {
		this.subject = subject;
		this.classes = classes;
	}
	
	public ClassSchedule(Subject subject, Teacher teacher, ArrayList<ClassHour> classes) {
		this.subject = subject;
		this.classes = classes;
		this.teacher = teacher;
	}
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public ArrayList<ClassHour> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<ClassHour> classes) {
		this.classes = classes;
	}

	public void addClassHour(ClassHour classHour) {
		classes.add(classHour);
	}
	
	public void setTeacher(Teacher teacher, boolean[] teacherAtributes) {
		this.teacher = teacher;
		setTeacherAtributes(teacherAtributes);
	}
	
	public void setTeacherAtributes(boolean[] teacherAtributes) {
		teacher.setValues(teacherAtributes[0], teacherAtributes[1], teacherAtributes[2]);
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("Subject: " + subject.getName() + ", Teacher: " + teacher.getName() + '\n');
		
		for(ClassHour hour: classes)
			str.append(hour.toString() + '\n');
		
		str.setLength(str.length() - 1);
		
		return str.toString();
	}
}