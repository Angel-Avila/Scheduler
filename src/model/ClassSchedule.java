package model;

import java.util.ArrayList;

public class ClassSchedule {

	protected Subject subject;
	protected Teacher teacher;
	protected ArrayList<ClassHour> classes;
	
	public ClassSchedule(Subject subject, ArrayList<ClassHour> classes) {
		this.subject = subject;
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
}