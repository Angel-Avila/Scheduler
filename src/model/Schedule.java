package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
	protected ClassDay[] schoolWeek;
	protected ArrayList<ClassSchedule> classSchedules;
	protected int huecos;
	protected int barcos;
	protected int mamones;
	protected int buenos;
	protected int daysToGo;
	
	public Schedule() {
		schoolWeek = new ClassDay[6];
		
		schoolWeek[0] = new ClassDay(Day.MONDAY);
		schoolWeek[1] = new ClassDay(Day.TUESDAY);
		schoolWeek[2] = new ClassDay(Day.WEDNESDAY);
		schoolWeek[3] = new ClassDay(Day.THURSDAY);
		schoolWeek[4] = new ClassDay(Day.FRIDAY);
		schoolWeek[5] = new ClassDay(Day.SATURDAY);
		
		classSchedules = new ArrayList<ClassSchedule>();
		huecos = 0;
		barcos = 0;
		mamones = 0;
		buenos = 0;
		daysToGo = 0;
	}
	
	public Schedule(Schedule schedule) {
		schoolWeek = schedule.getSchoolWeek();
		classSchedules = extracted(schedule);
		huecos = schedule.getHuecos();
		barcos = schedule.getBarcos();
		mamones = schedule.getMamones();
		buenos = schedule.getBuenos();
		daysToGo = schedule.getDaysToGo();
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		for(ClassSchedule sched: classSchedules)
			str.append(sched.toString() + '\n');
		
		str.setLength(str.length() - 1);
		
		return str.toString();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ClassSchedule> extracted(Schedule schedule) {
		return (ArrayList<ClassSchedule>) schedule.getClassSchedules().clone();
	}
	
	public ClassDay[] getSchoolWeek() {
		return schoolWeek;
	}

	public void setSchoolWeek(ClassDay[] schoolWeek) {
		this.schoolWeek = schoolWeek;
	}

	public ArrayList<ClassSchedule> getClassSchedules() {
		return classSchedules;
	}

	public void setClassSchedules(ArrayList<ClassSchedule> classSchedules) {
		this.classSchedules = classSchedules;
	}

	public int getHuecos() {
		return huecos;
	}

	public void setHuecos(int huecos) {
		this.huecos = huecos;
	}

	public int getBarcos() {
		return barcos;
	}

	public void setBarcos(int barcos) {
		this.barcos = barcos;
	}

	public int getMamones() {
		return mamones;
	}

	public void setMamones(int mamones) {
		this.mamones = mamones;
	}

	public int getBuenos() {
		return buenos;
	}

	public void setBuenos(int buenos) {
		this.buenos = buenos;
	}

	public int getDaysToGo() {
		return daysToGo;
	}

	public void setDaysToGo(int daysToGo) {
		this.daysToGo = daysToGo;
	}

	public boolean insertClassSchedule(ClassSchedule classSchedule) {
		for(ClassHour class1: classSchedule.classes) {
			for(ClassSchedule tempSchedule: classSchedules) {
				for(ClassHour class2: tempSchedule.classes) {
					if(crashes(class1, class2) || class1.subject == class2.subject) 
						return false;
				}
			}
		}
		
		classSchedules.add(classSchedule);
		
		for(ClassHour class1: classSchedule.classes) {
			int index = getWeekIndex(class1);
			schoolWeek[index].addHour(class1);
		}
		
		return true;
	}
	
	public void deleteClassSchedule(ClassSchedule classSchedule) {
		classSchedules.remove(classSchedule);
		
		for(ClassHour class1: classSchedule.classes) {
			int index = getWeekIndex(class1);
			schoolWeek[index].deleteHour(class1);
		}
	}
	
	// MARK: - Helper functions
	
	private int getWeekIndex(ClassHour c) {
		int index = 0;
		
		if(c.day == Day.TUESDAY) 
			index = 1;
		else if(c.day == Day.WEDNESDAY) 
			index = 2;
		else if(c.day == Day.THURSDAY) 
			index = 3;
		else if(c.day == Day.FRIDAY) 
			index = 4;
		else if(c.day == Day.SATURDAY) 
			index = 5;
		
		return index;
	}
	
	private boolean crashes(ClassHour class1, ClassHour class2) {
		if(class1.day != class2.day)
			return false;
		
		if (betweenInclusive_Exclusive(class1.start, class2.start, class2.end) ||
				betweenExclusive_Inclusive(class1.end, class2.start, class2.end))
			return true;
		
		return false;
	}
	
	private boolean betweenInclusive_Exclusive (int x, int min, int max) {
	       return x>=min && x<max;    
	}
	
	private boolean betweenExclusive_Inclusive (int x, int min, int max) {
	       return x>min && x<=max;    
	}

	public void getSum() {
		for(ClassDay day: schoolWeek) {
			
			if(day.isGoing())
				daysToGo++;
			
			huecos += day.getHuecos();
		}
		
		for(ClassSchedule schedule: classSchedules) {
				if(schedule.teacher.isBueno())
					buenos++;
				if(schedule.teacher.isBarco())
					barcos++;
				if(schedule.teacher.isMamon())
					mamones++;
		}
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}