package view;

import java.util.ArrayList;

import controller.Scheduler;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.ClassHour;
import model.ClassSchedule;
import model.Day;
import model.Subject;
import model.Teacher;

public class UIView extends Application {
	
	Scheduler scheduler;
	
	Button addSubject;
	Button deleteSubject;
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
		
		// MARK: - UI
		
		window = primaryStage;
		window.setTitle("Scheduler");
		
		window.setOnCloseRequest(e -> closeProgram());
		
		// MARK: - Menu Bar
		
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

		// MARK: - TreeView
		
		TreeItem<String> root;
		
		root = new TreeItem<>();
		root.setExpanded(true);
		
		for(ArrayList<ClassSchedule> possibleSchedules: scheduler.getWantedClassesSchedules()) {
			for(ClassSchedule schedule: possibleSchedules) {
				TreeItem<String> schedLeaf = new TreeItem<>(capitalize(schedule.getTeacher().getName().toLowerCase()));
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
		
		classSchedulesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			
			 public void changed(ObservableValue<?> observable, Object oldValue,
		                Object newValue) {

		            @SuppressWarnings("unchecked")
					TreeItem<String> selectedItem = (TreeItem<String>) newValue;
		            
		            if(selectedItem.getParent().getValue() == null)
		            	return;
		            
		            System.out.println("Teacher: " + selectedItem.getValue() + " for " + selectedItem.getParent().getValue());
		            
		            // Display teacher's schedule for the given class
		            
		        }
			
		});
				
		// MARK: - Right pane
		
		addSubject = new Button("+Añadir Clase");
		deleteSubject = new Button("-Borrar Clase");
		
		addSubject.setOnAction(e -> {
			setUserAgentStylesheet(STYLESHEET_CASPIAN);
			
		});
		
		deleteSubject.setOnAction(e -> {
			
		});
		
		VBox rightMenu = new VBox(10);
		rightMenu.setPadding(new Insets(20, 20, 20, 20));
		rightMenu.getChildren().addAll(addSubject, deleteSubject);

		// MARK: - Center Pane
		
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
		
		int dayW = 70;
		int timeH = 20;
		grid.getColumnConstraints().add(new ColumnConstraints(95));
		grid.getColumnConstraints().add(new ColumnConstraints(dayW));
		grid.getColumnConstraints().add(new ColumnConstraints(dayW));
		grid.getColumnConstraints().add(new ColumnConstraints(dayW));
		grid.getColumnConstraints().add(new ColumnConstraints(dayW));
		grid.getColumnConstraints().add(new ColumnConstraints(dayW));
		grid.getColumnConstraints().add(new ColumnConstraints(dayW));
		grid.getRowConstraints().add(new RowConstraints(timeH));
		grid.getRowConstraints().add(new RowConstraints(timeH));
		grid.getRowConstraints().add(new RowConstraints(timeH));
		grid.getRowConstraints().add(new RowConstraints(timeH));
		grid.getRowConstraints().add(new RowConstraints(timeH));
		grid.getRowConstraints().add(new RowConstraints(timeH));
		grid.getRowConstraints().add(new RowConstraints(timeH));
		grid.getRowConstraints().add(new RowConstraints(timeH));
		//grid.setGridLinesVisible(true);
		
		ArrayList<UIClassHour> monUIHours = new ArrayList<>();
		createAndAddToGrid(monUIHours, 1, Color.CORAL, grid);
		ArrayList<UIClassHour> tueUIHours = new ArrayList<>();
		createAndAddToGrid(tueUIHours, 2, Color.LIGHTBLUE, grid);
		ArrayList<UIClassHour> wedUIHours = new ArrayList<>();
		createAndAddToGrid(wedUIHours, 3, Color.MEDIUMPURPLE, grid);
		ArrayList<UIClassHour> thuUIHours = new ArrayList<>();
		createAndAddToGrid(thuUIHours, 4, Color.ORANGE, grid);
		ArrayList<UIClassHour> friUIHours = new ArrayList<>();
		createAndAddToGrid(friUIHours, 5, Color.LIGHTPINK, grid);
		ArrayList<UIClassHour> satUIHours = new ArrayList<>();
		createAndAddToGrid(satUIHours, 6, Color.LIGHTGREEN, grid);
		
		grid.getChildren().addAll(monday, tuesday, wednesday, thursday, friday, saturday, h7, h9, h11, h1, h4, h6, h8);
		grid.setId("monospaced");
		
		// MARK: - Border Pane
		
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		borderPane.setLeft(classSchedulesList);
		borderPane.setCenter(grid);
		borderPane.setRight(rightMenu);
		
		Scene scene = new Scene(borderPane, 1070, 520);
		scene.getStylesheets().add("style.css");
		window.setScene(scene);
		
		window.setResizable(false);
		window.show();
	}
	
	private void closeProgram() {
		System.out.println("Schedules saved!");
		window.close();
	}
	
	@SuppressWarnings("unused")
	private TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
		TreeItem<String> item = new TreeItem<>(title);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;
	}
	
	private String capitalize(String givenString) {
		String[] arr = givenString.split(" ");
	    StringBuffer sb = new StringBuffer();

	    for (int i = 0; i < arr.length; i++) {
	        sb.append(Character.toUpperCase(arr[i].charAt(0)))
	            .append(arr[i].substring(1)).append(" ");
	    }
	    
	    return sb.toString().trim();
	}
	
	private void createAndAddToGrid(ArrayList<UIClassHour> hours, int row, Color color, GridPane grid) {
		for (int i = 0; i < 7; i++) 
			hours.add(new UIClassHour(row, i + 1, color));
		
		for (UIClassHour hour: hours) {
			GridPane.setConstraints(hour, hour.getX(), hour.getY());
			grid.getChildren().add(hour);
		}
	}
}
