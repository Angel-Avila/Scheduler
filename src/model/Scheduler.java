package model;

import java.util.ArrayList;
import java.util.Collections;

public class Scheduler {
	
	protected ArrayList<Schedule> possibleSchedules;
	protected ArrayList<ArrayList<ClassSchedule>> wantedClassesSchedules;
	private int numberOfWantedSchedules;
	
	public Scheduler() {
		possibleSchedules = new ArrayList<Schedule>();
		wantedClassesSchedules = new ArrayList<ArrayList<ClassSchedule>>();
		numberOfWantedSchedules = 0;
	}
	
	public void addWantedClassSchedule(ArrayList<ClassSchedule> classSchedule) {
		wantedClassesSchedules.add(classSchedule);
		numberOfWantedSchedules++;
	}
	
	public ArrayList<ClassSchedule> getWantedClassSchedule(int i) {
		return wantedClassesSchedules.get(i);
	}
	
	public void calcAllPossibleSchedules() {
		backTrack(new Schedule(), 0, numberOfWantedSchedules);
	}
	
	private void backTrack(Schedule schedule, int k, int n) {
		
		if(k == n) 
			possibleSchedules.add(schedule);
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
			for(ClassSchedule classSchedule: schedule.classSchedules) 
				System.out.println(classSchedule.teacher.name);
			System.out.println("------------------------------------");
		}
	}
	
	public void printSchedule(Schedule schedule) {
		for(ClassSchedule classSchedule: schedule.classSchedules) 
			System.out.println(classSchedule.teacher.name);
		System.out.println("==================================");
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
            		if (possibleSchedules.get(j).buenos < possibleSchedules.get(index).buenos) 
            			index = j;
            		
            	} else if(category == SortCategory.BARCO) {
            		if (possibleSchedules.get(j).barcos < possibleSchedules.get(index).barcos) 
            			index = j;
            	
            	} else if(category == SortCategory.DAYSTOGO) {
            		if (possibleSchedules.get(j).daysToGo < possibleSchedules.get(index).daysToGo) 
            			index = j;
            	
            	} else if(category == SortCategory.HUECO) {
            		if (possibleSchedules.get(j).huecos < possibleSchedules.get(index).huecos)
                        index = j;
            		
            	} else if(category == SortCategory.MAMON) {
            		if (possibleSchedules.get(j).mamones < possibleSchedules.get(index).mamones)
                        index = j;
            		
            	}
                
            }
            
            Collections.swap(possibleSchedules, i, index);
        }
        
    }

}
