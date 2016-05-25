package controller;

import java.util.ArrayList;
import java.util.Collections;

import model.ClassSchedule;
import model.Schedule;
import model.SortCategory;
import model.Subject;
import model.Teacher;

public class Scheduler {
	
	protected ArrayList<Schedule> possibleSchedules;
	protected ArrayList<ArrayList<ClassSchedule>> wantedClassesSchedules;
	
	public Scheduler() {
		possibleSchedules = new ArrayList<Schedule>();
		wantedClassesSchedules = new ArrayList<ArrayList<ClassSchedule>>();
	}
	
	public void addWantedClassSchedule(ArrayList<ClassSchedule> classSchedule) {
		wantedClassesSchedules.add(classSchedule);
	}
	
	public ArrayList<ClassSchedule> getWantedClassSchedule(int i) {
		return wantedClassesSchedules.get(i);
	}
	
	public void printWantedClasses() {
		for(ArrayList<ClassSchedule> wantedSchedules: wantedClassesSchedules) {
			for(ClassSchedule schedule: wantedSchedules) {
				System.out.println(schedule.getSubject().getName());
			}
		}
	}
	
	public Teacher getTeacher(String name) {
		
		for(ArrayList<ClassSchedule> wantedSchedules: wantedClassesSchedules) {
			for(ClassSchedule schedule: wantedSchedules) {
				if(schedule.getTeacher().getName().equals(name)) 
					return schedule.getTeacher();
			}
		}
		
		return null;
	}
	
	public Subject getSubject(String name) {
		
		for(ArrayList<ClassSchedule> wantedSchedules: wantedClassesSchedules) {
			for(ClassSchedule schedule: wantedSchedules) {
				if(schedule.getSubject().getName().equals(name)) 
					return schedule.getSubject();
			}
		}
		
		return null;
	}
	
	public ArrayList<ClassSchedule> getClassSchedules(String subject) {
		
		for(ArrayList<ClassSchedule> wantedSchedules: wantedClassesSchedules) {
			for(ClassSchedule schedule: wantedSchedules) {
				if(schedule.getSubject().getName().equals(subject)) 
					return wantedSchedules;
				else
					break;
			}
		}
		
		return null;
	}
	
	public ArrayList<Schedule> getPossibleSchedules() {
		return possibleSchedules;
	}

	public ArrayList<ArrayList<ClassSchedule>> getWantedClassesSchedules() {
		return wantedClassesSchedules;
	}

	public void calcAllPossibleSchedules() {
		possibleSchedules = new ArrayList<>();
		backTrack(new Schedule(), 0, wantedClassesSchedules.size());
	}
	
	/**
	 * @param schedule - temporal schedule to do all the changes
	 * @param k - number of classes inserted in the schedule
	 * @param n - number of classes desired to be enrolled 
	 */
	private void backTrack(Schedule schedule, int k, int n) {
		
		// Entra aquí cuando el número de clases que has metido en este schedule es el mismo que el número de clases que quieres
		// meter en el semestre
		if(k == n) {
			try {
				Schedule tempSchedule = (Schedule) schedule.clone();
				tempSchedule.setClassSchedules(schedule.extracted(schedule));
				tempSchedule.getSum();
				System.out.println("DTG: " + tempSchedule.getDaysToGo());
				System.out.println("Huecos: " + tempSchedule.getHuecos());
				System.out.println("TEMPSCHEDULE HERE ********" + tempSchedule);
				possibleSchedules.add(tempSchedule);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			
		}
		else {
			
			for(int i = k; i < wantedClassesSchedules.size(); i++) {
				for(ClassSchedule wantedClassSchedule: wantedClassesSchedules.get(i)) {
					if(schedule.insertClassSchedule(wantedClassSchedule)) {
						backTrack(schedule, k+1, n);
						schedule.deleteClassSchedule(wantedClassSchedule);
					}
				}
			}
		}
	}
		
	public void printPossibleSchedules() {
		for(Schedule schedule: possibleSchedules) {
			for(ClassSchedule classSchedule: schedule.getClassSchedules()) 
				System.out.println(schedule + ": " + classSchedule.getTeacher().getName());
			System.out.println(schedule.getClassSchedules());
			System.out.println("------------------------------------------------------------");
		}
	}
	
	public void printSchedule(Schedule schedule) {
		for(ClassSchedule classSchedule: schedule.getClassSchedules()) 
			System.out.println(schedule + ": " + classSchedule.getTeacher().getName());
		System.out.println("============================================================");
	}
	
	public void sumAllSchedules() {
		for(Schedule schedule: possibleSchedules) {
			schedule.getSum();
		}
	}
	
	public void sortByLess(SortCategory category){
        
        for (int i = 0; i < possibleSchedules.size() - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < possibleSchedules.size(); j++) {
            	
            	if(category == SortCategory.BUENO) {
            		if (possibleSchedules.get(j).getBuenos() < possibleSchedules.get(index).getBuenos()) 
            			index = j;
            		
            	} else if(category == SortCategory.BARCO) {
            		if (possibleSchedules.get(j).getBarcos() < possibleSchedules.get(index).getBarcos()) 
            			index = j;
            	
            	} else if(category == SortCategory.DAYSTOGO) {
            		if (possibleSchedules.get(j).getDaysToGo() < possibleSchedules.get(index).getDaysToGo()) 
            			index = j;
            	
            	} else if(category == SortCategory.HUECO) {
            		if (possibleSchedules.get(j).getHuecos() < possibleSchedules.get(index).getHuecos())
                        index = j;
            		
            	} else if(category == SortCategory.MAMON) {
            		if (possibleSchedules.get(j).getMamones() < possibleSchedules.get(index).getMamones())
                        index = j;
            		
            	}
                
            }
            
            Collections.swap(possibleSchedules, i, index);
        }
        
    }
	
public void sortByMore(SortCategory category){
        
        for (int i = 0; i < possibleSchedules.size() - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < possibleSchedules.size(); j++) {
            	
            	if(category == SortCategory.BUENO) {
            		if (possibleSchedules.get(j).getBuenos() > possibleSchedules.get(index).getBuenos()) 
            			index = j;
            		
            	} else if(category == SortCategory.BARCO) {
            		if (possibleSchedules.get(j).getBarcos() > possibleSchedules.get(index).getBarcos()) 
            			index = j;
            	
            	} else if(category == SortCategory.DAYSTOGO) {
            		if (possibleSchedules.get(j).getDaysToGo() > possibleSchedules.get(index).getDaysToGo()) 
            			index = j;
            	
            	} else if(category == SortCategory.HUECO) {
            		if (possibleSchedules.get(j).getHuecos() > possibleSchedules.get(index).getHuecos())
                        index = j;
            		
            	} else if(category == SortCategory.MAMON) {
            		if (possibleSchedules.get(j).getMamones() > possibleSchedules.get(index).getMamones())
                        index = j;
            		
            	}
                
            }
            
            Collections.swap(possibleSchedules, i, index);
        }
        
    }

}