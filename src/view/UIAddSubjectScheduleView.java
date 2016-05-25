package view;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ClassHour;
import model.ClassSchedule;
import model.Day;
import model.Subject;
import model.Teacher;
import util.Utils;

public class UIAddSubjectScheduleView {

	public static ClassSchedule display(Subject subject) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Añadir Clase");
		
		ClassSchedule schedule = new ClassSchedule();
		ArrayList<ClassHour> hours = new ArrayList<>();
		
		// MARK: - Grid for the hours ========================================================================================
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(40);
		grid.setHgap(23);
		
		Label monday = new Label("Lunes");
		Label tuesday = new Label("Martes");
		Label wednesday = new Label("Miércoles");
		Label thursday = new Label("Jueves");
		Label friday = new Label("Viernes");
		Label saturday = new Label("Sábado");
		
		Label h7 = new Label("07:00 - 09:00");
		Label h9 = new Label("09:00 - 11:00");
		Label h11 = new Label("11:00 - 13:00");
		Label h1 = new Label("13:00 - 15:00");
		Label h4 = new Label("16:00 - 18:00");
		Label h6 = new Label("18:00 - 20:00");
		Label h8 = new Label("20:00 - 22:00");
		
		GridPane.setConstraints(monday, 1, 0);
		GridPane.setConstraints(tuesday, 2, 0);
		GridPane.setConstraints(wednesday, 3, 0);
		GridPane.setConstraints(thursday, 4, 0);
		GridPane.setConstraints(friday, 5, 0);
		GridPane.setConstraints(saturday, 6, 0);
		
		GridPane.setConstraints(h7, 0, 1);
		GridPane.setConstraints(h9, 0, 2);
		GridPane.setConstraints(h11, 0, 3);
		GridPane.setConstraints(h1, 0, 4);
		GridPane.setConstraints(h4, 0, 5);
		GridPane.setConstraints(h6, 0, 6);
		GridPane.setConstraints(h8, 0, 7);
		
		grid.getColumnConstraints().add(new ColumnConstraints(95));
		for(int i = 0; i < 6; i++)
			grid.getColumnConstraints().add(new ColumnConstraints(70));

		for(int i = 0; i < 8; i++)
			grid.getRowConstraints().add(new RowConstraints(20));

		ArrayList<UIClassHour> monUIHours = new ArrayList<>();
		Utils.createAndAddToGrid(monUIHours, 1, Color.CORAL, grid);
		ArrayList<UIClassHour> tueUIHours = new ArrayList<>();
		Utils.createAndAddToGrid(tueUIHours, 2, Color.LIGHTBLUE, grid);
		ArrayList<UIClassHour> wedUIHours = new ArrayList<>();
		Utils.createAndAddToGrid(wedUIHours, 3, Color.MEDIUMPURPLE, grid);
		ArrayList<UIClassHour> thuUIHours = new ArrayList<>();
		Utils.createAndAddToGrid(thuUIHours, 4, Color.ORANGE, grid);
		ArrayList<UIClassHour> friUIHours = new ArrayList<>();
		Utils.createAndAddToGrid(friUIHours, 5, Color.LIGHTPINK, grid);
		ArrayList<UIClassHour> satUIHours = new ArrayList<>();
		Utils.createAndAddToGrid(satUIHours, 6, Color.LIGHTGREEN, grid);
		ArrayList<ArrayList<UIClassHour>> weekUIHours = new ArrayList<>();
		weekUIHours.add(monUIHours);
		weekUIHours.add(tueUIHours);
		weekUIHours.add(wedUIHours);
		weekUIHours.add(thuUIHours);
		weekUIHours.add(friUIHours);
		weekUIHours.add(satUIHours);
		
		// Enable clicks on the grid elements
		Utils.setOnMouseClickedForWeek(weekUIHours);
		
		grid.getChildren().addAll(monday, tuesday, wednesday, thursday, friday, saturday, h7, h9, h11, h1, h4, h6, h8);
		grid.setId("monospaced");
		
		// MARK: - Teacher name input ========================================================================================
		
		HBox teacherBox = new HBox(10);
		
		Label teacherLabel = new Label("Maestro: ");
		TextField teacherNameInput = new TextField();
		
		teacherBox.getChildren().addAll(teacherLabel, teacherNameInput);
		teacherBox.setAlignment(Pos.CENTER);
		
		teacherNameInput.setMaxWidth(310);
		
		// MARK: - CheckBoxes for teacher atributes ===========================================================================
		
		CheckBox barco = new CheckBox("Barco");
		CheckBox bueno = new CheckBox("Bueno");
		CheckBox mamon = new CheckBox("Mamón");
		
		HBox attBox = new HBox(10);
		attBox.getChildren().addAll(barco, bueno, mamon);
		attBox.setAlignment(Pos.CENTER);
		
		// MARK: - Button to send the schedule =================================================================================
		
		Button button = new Button("Añadir");
		button.setOnAction(e ->  {	
			
			// If the teacher doesn't have a name or the user hasn't entered at least one class, the button will do nothing
			if(teacherNameInput.getText() != null && teacherNameInput.getText() != "") {
				
				boolean atLeastOneClass = false;
				
				// We loop through each day of the week
				for(ArrayList<UIClassHour> dayUI: weekUIHours) {
					// And create the needed values to create a ClassHour
					boolean firstAppearance = false;
					Day day = Day.MONDAY;
					int start = 0, end = 0;
					
					// then we loop through each hour of the day
					for(UIClassHour hour: dayUI) {
						
						// the end of each class should be 2 hours after it started, however for longer classes,
						if(hour.isSelected()) {
							if(firstAppearance == false) {
								firstAppearance = true;
								atLeastOneClass = true;
								start = Utils.getHourStartforY(hour);
								end = start + 2;
								day = Utils.getDayforX(hour);
							} 
							
							// if there was an hour of class before the one we are checking in the loop, we add 2 hours to the end
							// since each ClassHour is equivalent to 2 hours
							else 
								end += 2;
						}
					}
					// If there was a class that day, we create a new ClassHour object and add it to our hours array
					if(firstAppearance)
						hours.add(new ClassHour(start, end, subject, day));
				}
				
				// Button doesn't do anything if there isn't at least one class in the schedule
				if(!atLeastOneClass)
					return;
				
				// We set the atributes we got from the nested loops to our schedule 
				schedule.setSubject(subject);
				schedule.setTeacher(new Teacher(teacherNameInput.getText(), barco.isSelected(), bueno.isSelected(), mamon.isSelected()));
				schedule.setClasses(hours);

				// Aaaaand close our window
				window.close();
			}
		});

		// MARK: - Adding all the previous layout =================================================================================
		
		VBox layout = new VBox(30);
		layout.getChildren().addAll(grid, teacherBox, attBox, button);
		layout.setAlignment(Pos.CENTER);
		
		// MARK: - Set the scene and the style to the window and display it =======================================================
		
		Scene scene = new Scene(layout, 730, 700);
		scene.getStylesheets().add("style.css");
		window.setScene(scene); 
		window.showAndWait();

		return schedule;
	}
}
