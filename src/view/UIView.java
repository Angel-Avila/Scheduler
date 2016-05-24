package view;

import java.util.ArrayList;

import controller.Scheduler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ClassHour;
import model.ClassSchedule;
import model.Day;
import model.Subject;
import model.Teacher;
import util.Utils;

public class UIView extends Application {
	
	Scheduler scheduler;
	
	Button addSubject;
	Button deleteSubject;
	Button addSubjectSchedule;
	Button deleteSubjectSchedule;
	//ListView<String> classSchedulesList;
	TreeView<String> classSchedulesList;
	Stage window;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		scheduler = new Scheduler();
		
		Subject herramientasDisenio = new Subject("Herramientas Diseño");
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
		
		// MARK: - UI ================================================================================================
		
		window = primaryStage;
		window.setTitle("Scheduler");
		
		window.setOnCloseRequest(e -> closeProgram());
		
		// MARK: - Menu Bar ================================================================================================
		
		MenuBar menuBar = new MenuBar();
		
		Menu fileMenu = new Menu("_File");
		
		MenuItem newFile = new MenuItem("Nuevo Archivo...");
		MenuItem openFile = new MenuItem("Abrir Archivo...");
		MenuItem save = new MenuItem("Guardar");
		
		newFile.setOnAction(e -> System.out.println("Woo"));
		
		fileMenu.getItems().add(newFile);
		fileMenu.getItems().add(openFile);
		fileMenu.getItems().add(new SeparatorMenuItem());
		fileMenu.getItems().add(save);
		
		Menu editMenu = new Menu("_Edit");
		
		MenuItem undo = new MenuItem("Deshacer");
		MenuItem redo = new MenuItem("Rehacer");
		undo.setDisable(true);
		redo.setDisable(true);
		
		editMenu.getItems().add(undo);
		editMenu.getItems().add(redo);
		
		Menu viewMenu = new Menu("_View");
		ToggleGroup viewToggle = new ToggleGroup();
		
		RadioMenuItem subjectsView = new RadioMenuItem("Clases");
		RadioMenuItem schedulesView = new RadioMenuItem("Horarios");
		subjectsView.setToggleGroup(viewToggle);
		schedulesView.setToggleGroup(viewToggle);
		subjectsView.setSelected(true);
		
		viewMenu.getItems().add(subjectsView);
		viewMenu.getItems().add(schedulesView);
		
		menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu);

		// MARK: - TreeView ================================================================================================
		
		TreeItem<String> root;
		
		root = new TreeItem<>();
		root.setExpanded(true);
		
		for(ArrayList<ClassSchedule> possibleSchedules: scheduler.getWantedClassesSchedules()) {
			for(ClassSchedule schedule: possibleSchedules) {
				TreeItem<String> schedLeaf = new TreeItem<>(Utils.capitalize(schedule.getTeacher().getName().toLowerCase()));
				boolean found = false;
				
				for(TreeItem<String> subjectNode: root.getChildren()) {
					if(subjectNode.getValue().contentEquals(schedule.getSubject().getName())) {
						subjectNode.getChildren().add(schedLeaf);
						found = true;
						break;
					}
				}
				
				if(!found) {
					TreeItem<String> subjectNode = new TreeItem<>(schedule.getSubject().getName());
					root.getChildren().add(subjectNode);
					subjectNode.getChildren().add(schedLeaf);
				}
				
			}
		}
		
		
		classSchedulesList = new TreeView<>(root);
		classSchedulesList.setShowRoot(false);
				
		// MARK: - Right pane ================================================================================================
		
		addSubject = new Button("+Añadir Clase");
		deleteSubject = new Button("-Borrar Clase");
		addSubjectSchedule = new Button("+Añadir Horario");
		deleteSubjectSchedule = new Button("-Borrar Horario");
		
		addSubject.setMinWidth(117);
		deleteSubject.setMinWidth(117);
		addSubjectSchedule.setMaxWidth(117);
		deleteSubjectSchedule.setMinWidth(117);
		
		addSubjectSchedule.setVisible(false);
		deleteSubjectSchedule.setVisible(false);
		
		
		addSubject.setOnAction(e -> {
			//setUserAgentStylesheet(STYLESHEET_CASPIAN);
			Subject subject = UIAddSubjectView.display();
			if(subject.getName().length() > 0) {
				ArrayList<ClassSchedule> temp = new ArrayList<>();
				temp.add(new ClassSchedule(subject, new ArrayList<ClassHour>()));
				scheduler.addWantedClassSchedule(temp);
				
				Utils.makeBranch(subject.getName(), root);
				scheduler.printWantedClasses();
			}
		});
		
		
		deleteSubject.setOnAction(e -> {
			
		});
		
		VBox rightMenu = new VBox(10);
		rightMenu.setPadding(new Insets(20, 20, 20, 20));
		rightMenu.getChildren().addAll(addSubject, deleteSubject, addSubjectSchedule, deleteSubjectSchedule);

		// MARK: - Center Pane ================================================================================================
		
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
		
		grid.getChildren().addAll(monday, tuesday, wednesday, thursday, friday, saturday, h7, h9, h11, h1, h4, h6, h8);
		grid.setId("monospaced");
		
		// MARK: - TreeView Listener ================================================================================================
		
		classSchedulesList.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
	        
			TreeItem<String> selectedItem = (TreeItem<String>) newValue;
		            
			// Meaning a subject was selected, not a teacher
		    if(selectedItem.getParent().getValue() == null) {
		    	addSubjectSchedule.setVisible(true);
		    	deleteSubjectSchedule.setVisible(true);
		    	Utils.colorUIClassHours(weekUIHours);
		    	
		    	addSubjectSchedule.setOnAction(e -> {
		    		// Get reference for the classSchedules of the selected subject
		    		ArrayList<ClassSchedule> scheds = scheduler.getClassSchedules(selectedItem.getValue());
		    		// Get reference of the selected subject 
		    		Subject sub = scheduler.getSubject(selectedItem.getValue());
		    		// Display the view to add the schedule and teacher for the selected subject and get it returned
					ClassSchedule classSched = UIAddSubjectScheduleView.display(sub);
					
					// Because of the onAction of adding a subject and structure of the code, the first element in the list of 
					// schedules for a new subject is null so we get rid of that annoyance by inserting the new schedule there
					if(scheds.size() == 1 && scheds.get(0).getTeacher() == null)
						scheds.set(0, classSched);
					// if schedules have already been inserted to this subject, we just add it to the list
					else
						scheds.add(classSched);
					
					// finally we add it to our treeView
					Utils.makeBranch(Utils.capitalize(classSched.getTeacher().getName().toLowerCase()), selectedItem);
				});
				
				deleteSubjectSchedule.setOnAction(e -> {
					
				});
		    	
		    	return;
		    }
		    
		    // This runs only when a teacher was selected:
		    
		    addSubjectSchedule.setVisible(false);
		    deleteSubjectSchedule.setVisible(false);
		    
            System.out.println("Teacher: " + selectedItem.getValue() + " for " + selectedItem.getParent().getValue());
		            
            // Display teacher's schedule for the given class
            Utils.colorUIClassHours(weekUIHours);
           
            for(ArrayList<ClassSchedule> wantedSchedules: scheduler.getWantedClassesSchedules()) {
    			for(ClassSchedule schedule: wantedSchedules) {
    				
    				if (selectedItem.getValue().equals(Utils.capitalize(schedule.getTeacher().getName().toLowerCase())) &&
    						selectedItem.getParent().getValue().equals(schedule.getSubject().getName())) {
    				
    					for(ClassHour hour: schedule.getClasses()) {
    						System.out.println(hour.toString());
    						int[] y = Utils.getYforHour(hour);
    						weekUIHours.get(Utils.getXforDay(hour)).get(y[0]).setColor(Color.DARKRED);
    						if(y[1] != -1) {
    							for(int i = y[0]; i <= y[1]; i++) 
    								weekUIHours.get(Utils.getXforDay(hour)).get(i).setColor(Color.DARKRED);
    						}
    					}
    					
    				}
    			}
            }
		});
		
		// MARK: - Border Pane ================================================================================================
		
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		borderPane.setLeft(classSchedulesList);
		borderPane.setCenter(grid);
		borderPane.setRight(rightMenu);
		
		Scene scene = new Scene(borderPane, 1090, 520);
		scene.getStylesheets().add("style.css");
		window.setScene(scene);
		
		window.setResizable(false);
		window.show();
	}
	
	private void closeProgram() {
		System.out.println("Schedules saved!");
		window.close();
	}
}
