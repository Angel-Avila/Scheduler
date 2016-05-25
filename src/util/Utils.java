package util;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.ClassHour;
import model.ClassSchedule;
import model.Day;
import view.UIClassHour;

public class Utils {
	
	/**
	 * Creates the hour objects we need for the schedules grids and adds them to the grid
	 * @param hours - UI objects used to represent hours
	 * @param row - the row for which we are creating the UIhours
	 * @param color - color for the hours object
	 * @param grid - schedule grid to which we are adding the UI objects 
	 */
	public static void createAndAddToGrid(ArrayList<UIClassHour> hours, int row, Color color, GridPane grid) {
		for (int i = 0; i < 7; i++) 
			hours.add(new UIClassHour(row, i + 1, color));
		
		for (UIClassHour hour: hours) {
			GridPane.setConstraints(hour, hour.getX(), hour.getY());
			grid.getChildren().add(hour);
		}
	}
	
	/**
	 * Creates the hour objects we need for the schedules grids, adds them to the grid and gives them an action on click
	 * @param hours - UI objects used to represent hours
	 * @param row - the row for which we are creating the UIhours
	 * @param color - color for the hours object
	 * @param grid - schedule grid to which we are adding the UI objects 
	 * @param value - event that should happen when the objects are clicked
	 */
	public static void createAndAddToGridWithEvent(ArrayList<UIClassHour> hours, int row, Color color, GridPane grid, EventHandler<? super MouseEvent> value) {
		for (int i = 0; i < 7; i++) 
			hours.add(new UIClassHour(row, i + 1, color, value));
		
		for (UIClassHour hour: hours) {
			GridPane.setConstraints(hour, hour.getX(), hour.getY());
			grid.getChildren().add(hour);
		}
	}
	
	/**
	 * Selects/deselects the UIClassHour object when clicked and changes its color
	 * @param week - all the UICLassHour objects from our schedule grid
	 */
	public static void setOnMouseClickedForWeek(ArrayList<ArrayList<UIClassHour>> week) {
		for(ArrayList<UIClassHour> day: week) {
			for(UIClassHour hour: day) {
				hour.setOnMouseClicked(e -> {
					if(hour.isSelected()) {
						hour.setSelected(false);
						hour.revertColor();
					} else {
						hour.setSelected(true);
						hour.setColor(Color.DARKRED);
					}
				});
			}
		}
	}
	
	/**
	 * Colors our UIClassHour objects to our predetermined colors
	 * @param week - all the UICLassHour objects from our schedule grid
	 */
	public static void colorUIClassHours(ArrayList<ArrayList<UIClassHour>> week) {
		for(ArrayList<UIClassHour> day: week) {
			for(UIClassHour hour: day) {
				if(hour.getX() == 1)
					hour.setColor(Color.CORAL);
				else if(hour.getX() == 2)
					hour.setColor(Color.LIGHTBLUE);
				else if(hour.getX() == 3)
					hour.setColor(Color.MEDIUMPURPLE);
				else if(hour.getX() == 4)
					hour.setColor(Color.ORANGE);
				else if(hour.getX() == 5)
					hour.setColor(Color.LIGHTPINK);
				else if(hour.getX() == 6)
					hour.setColor(Color.LIGHTGREEN);
			}
		}
	}
	
	/**
	 * Colors our UIClassHour objects to a specified color
	 * @param week - all the UICLassHour objects from our schedule grid
	 * @param color - color the whole grid is going to be colored to
	 */
	public static void colorUIClassHours(ArrayList<ArrayList<UIClassHour>> week, Color color) {
		for(ArrayList<UIClassHour> day: week) 
			for(UIClassHour hour: day) 
				hour.setColor(color);
	}
	
	/**
	 * Hides the text of our UIClassHour objects
	 * @param week - all the UICLassHour objects from our schedule grid 
	 */
	public static void hideTextUIClassHours(ArrayList<ArrayList<UIClassHour>> week) {
		for(ArrayList<UIClassHour> day: week) 
			for(UIClassHour hour: day) 
				hour.hideText();
	}
	
	/**
	 * Gets the x value an UIClassHour object should have on a grid depending on the day
	 * @param hour - object representing the ClassHour where our UI object should go
	 * @return x position on the grid
	 */
	public static int getXforDay(ClassHour hour) {
		if(hour.getDay() == Day.MONDAY)
			return 0;
		if(hour.getDay() == Day.TUESDAY)
			return 1;
		if(hour.getDay() == Day.WEDNESDAY)
			return 2;
		if(hour.getDay() == Day.THURSDAY)
			return 3;
		if(hour.getDay() == Day.FRIDAY)
			return 4;
		return 5;
	}
	
	/**
	 * Reverse logic for the getXforDay function
	 */
	public static Day getDayforX(UIClassHour hour) {
		if(hour.getX() == 1)
			return Day.MONDAY;
		if(hour.getX() == 2)
			return Day.TUESDAY;
		if(hour.getX() == 3)
			return Day.WEDNESDAY;
		if(hour.getX() == 4)
			return Day.THURSDAY;
		if(hour.getX() == 5)
			return Day.FRIDAY;
		return Day.SATURDAY;
	}
	
	/**
	 * Gets the y values an UIClassHour object should have on a grid depending on the starting hour
	 * @param hour - object representing the ClassHour where our UI object should go
	 * @return y position on the grid (start, end)
	 */
	public static int[] getYforHour(ClassHour hour) {
		int[] a = {-1, -1};
		if(hour.getStart() == 7)
			a[0] = 0;
		else if(hour.getStart() == 9)
			a[0] = 1;
		else if(hour.getStart() == 11)
			a[0] = 2;
		else if(hour.getStart() == 13)
			a[0] = 3;
		else if(hour.getStart() == 16)
			a[0] = 4;
		else if(hour.getStart() == 18)
			a[0] = 5;
		else {
			a[0] = 6;
			return a;
		}
		
		int diff = hour.getEnd() - hour.getStart();
		
		if(diff > 2) {
			a[1] = a[0] + 1;
			if(diff > 4)
				a[1] += 1;
			if(diff > 6)
				a[1] += 1;
		}
		return a;
	}
	
	/**
	 * Reverse logic for the getYforHour function
	 */
	public static int getHourStartforY(UIClassHour hour) {
		if(hour.getY() == 1)
			return 7;
		else if(hour.getY() == 2)
			return 9;
		else if(hour.getY() == 3)
			return 11;
		else if(hour.getY() == 4)
			return 13;
		else if(hour.getY() == 5)
			return 16;
		else if(hour.getY() == 6)
			return 18;
		
		return 20;
	}
	
	public static void displayClassSched(ClassSchedule schedule, ArrayList<ArrayList<UIClassHour>> weekUIHours, Color color) {
		//System.out.println(schedule.getSubject().getName());
		
		for(ClassHour hour: schedule.getClasses()) {
			//System.out.println(hour.toString());
			int[] y = Utils.getYforHour(hour);
			
			weekUIHours.get(Utils.getXforDay(hour)).get(y[0]).setColor(color);
			weekUIHours.get(Utils.getXforDay(hour)).get(y[0]).setText(getInitials(schedule.getSubject().getName()));
			if(y[1] != -1) {
				for(int i = y[0]; i <= y[1]; i++)  {
					weekUIHours.get(Utils.getXforDay(hour)).get(i).setColor(color);
					weekUIHours.get(Utils.getXforDay(hour)).get(i).setText(getInitials(schedule.getSubject().getName()));
				}
			}
		}
	}
	
	/**
	 * Capitalizes a given string
	 * @param givenString
	 * @return capitalized version of the string
	 */
	public static String capitalize(String givenString) {
		String[] arr = givenString.split(" ");
	    StringBuffer sb = new StringBuffer();

	    for (int i = 0; i < arr.length; i++) {
	        sb.append(Character.toUpperCase(arr[i].charAt(0)))
	            .append(arr[i].substring(1)).append(" ");
	    }
	    
	    return sb.toString().trim();
	}
	
	/**
	 * Gets the first letter of each word of a given string
	 * @param givenString
	 * @return initials of the string
	 */
	public static String getInitials(String givenString) {
		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < arr.length; i++) 
			sb.append(arr[i].substring(0, 1));
		
		return sb.toString().trim();
	}
	
	/**
	 * Makes a new branch in a treeview with a parent
	 * @param title - the branch title
	 * @param parent - the parent of the branch
	 * @return the new TreeItem object created from the title
	 */
	public static TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
		TreeItem<String> item = new TreeItem<>(title);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;
	}
}
