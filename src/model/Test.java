/*package model;

import java.util.ArrayList;

import controller.Scheduler;

public class Test {

	public static void main(String[] args) {
		
		Scheduler scheduler = new Scheduler();
				
		Subject herramientasDisenio = new Subject("Herramientas Computacionales para el Diseño");
		Subject disenioSoftware = new Subject("Diseño de Software");
		
		ArrayList<ClassSchedule> herramientasDisenioSchedules = new ArrayList<ClassSchedule>();
		ArrayList<ClassSchedule> disenioSoftwareSchedules = new ArrayList<ClassSchedule>();
		
		// MARK: - Schedules for "Herramientas de diseño de software"
		
		herramientasDisenioSchedules.add(new ClassSchedule(herramientasDisenio, new ArrayList<ClassHour>()));
		herramientasDisenioSchedules.get(0).addClassHour(new ClassHour(9, 13, herramientasDisenio, Day.THURSDAY));
		herramientasDisenioSchedules.get(0).setTeacher(new Teacher("DEHESA GUZMAN LUCERO PAMELA"), new boolean[]{false,false,false});
		
		herramientasDisenioSchedules.add(new ClassSchedule(herramientasDisenio, new ArrayList<ClassHour>()));
		herramientasDisenioSchedules.get(1).addClassHour(new ClassHour(7, 9, herramientasDisenio, Day.MONDAY));
		herramientasDisenioSchedules.get(1).addClassHour(new ClassHour(7, 9, herramientasDisenio, Day.THURSDAY));
		herramientasDisenioSchedules.get(1).setTeacher(new Teacher("MAYA NAVARRO JORGE ARTURO"), new boolean[]{false,false,false});
		/*
		herramientasDisenioSchedules.add(new ClassSchedule(herramientasDisenio, new ArrayList<ClassHour>()));
		herramientasDisenioSchedules.get(2).addClassHour(new ClassHour(13, 15, herramientasDisenio, Day.MONDAY));
		herramientasDisenioSchedules.get(2).addClassHour(new ClassHour(13, 15, herramientasDisenio, Day.THURSDAY));
		herramientasDisenioSchedules.get(2).setTeacher(new Teacher("GUTIERREZ MEDRANO CARLOS GUSTAVO"), new boolean[]{false,false,false});
		
		herramientasDisenioSchedules.add(new ClassSchedule(herramientasDisenio, new ArrayList<ClassHour>()));
		herramientasDisenioSchedules.get(3).addClassHour(new ClassHour(18, 20, herramientasDisenio, Day.TUESDAY));
		herramientasDisenioSchedules.get(3).addClassHour(new ClassHour(18, 20, herramientasDisenio, Day.THURSDAY));
		herramientasDisenioSchedules.get(3).setTeacher(new Teacher("MAYA NAVARRO JORGE ARTURO"), new boolean[]{false,false,false});
		*/
		// MARK: - Schedules for "Diseño de Software"
		/*
		disenioSoftwareSchedules.add(new ClassSchedule(disenioSoftware, new ArrayList<ClassHour>()));	
		disenioSoftwareSchedules.get(0).addClassHour(new ClassHour(7, 9, disenioSoftware, Day.TUESDAY));
		disenioSoftwareSchedules.get(0).addClassHour(new ClassHour(7, 9, disenioSoftware, Day.FRIDAY));
		disenioSoftwareSchedules.get(0).setTeacher(new Teacher("VILLALOBOS MANZO RODRIGO"), new boolean[]{false,false,false});
		
		disenioSoftwareSchedules.add(new ClassSchedule(disenioSoftware, new ArrayList<ClassHour>()));	
		disenioSoftwareSchedules.get(1).addClassHour(new ClassHour(7, 9, disenioSoftware, Day.MONDAY));
		disenioSoftwareSchedules.get(1).addClassHour(new ClassHour(7, 9, disenioSoftware, Day.THURSDAY));
		disenioSoftwareSchedules.get(1).setTeacher(new Teacher("TORRES BERNAL RAYMUNDO"), new boolean[]{false,false,false});
		
		scheduler.addWantedClassSchedule(herramientasDisenioSchedules);
		scheduler.addWantedClassSchedule(disenioSoftwareSchedules);
		
		scheduler.calcAllPossibleSchedules();
		scheduler.printPossibleSchedules();
	}
	
}*/