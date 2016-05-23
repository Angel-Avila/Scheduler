package controller;

import java.util.ArrayList;
import java.util.Collections;

import model.ClassSchedule;
import model.Schedule;
import model.SortCategory;

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
	
	public ArrayList<Schedule> getPossibleSchedules() {
		return possibleSchedules;
	}

	public ArrayList<ArrayList<ClassSchedule>> getWantedClassesSchedules() {
		return wantedClassesSchedules;
	}

	public void calcAllPossibleSchedules() {
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
	
	public void sortBy(SortCategory category){
        
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

}