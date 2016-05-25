package view;

import java.util.ArrayList;

import controller.Scheduler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ClassHour;
import model.ClassSchedule;
import model.Day;
import model.SortCategory;
import model.Subject;
import model.Teacher;
import util.Utils;

public class UIView extends Application {
	
	Scheduler scheduler;
	
	Button addSubject;
	Button deleteSubject;
	Button addSubjectSchedule;
	Button deleteSubjectSchedule;
	Button subjectsView;
	Button schedulesView;
	
	Button sortBarco;
	Button sortBueno;
	Button sortMamon;
	Button sortDTG;
	Button sortHuecos;
	
	Label sortLabel;
	
	TreeView<String> subjectsTreeview;
	ListView<String> possibleSchedsLV;
	BorderPane borderPane;
	
	boolean subjectsSelected;
	boolean schedulesSelected;
	
	boolean sortMostBa;
	boolean sortMostBu;
	boolean sortMostMa;
	boolean sortMostD;
	boolean sortMostH;
	
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
		
		RadioMenuItem subjectsViewMenu = new RadioMenuItem("Clases");
		RadioMenuItem schedulesViewMenu = new RadioMenuItem("Horarios");
		subjectsViewMenu.setToggleGroup(viewToggle);
		schedulesViewMenu.setToggleGroup(viewToggle);
		subjectsViewMenu.setSelected(true);
		
		viewMenu.getItems().add(subjectsViewMenu);
		viewMenu.getItems().add(schedulesViewMenu);
		
		menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu);

		// MARK: - TreeView ================================================================================================
		
		TreeItem<String> rootSubjects;
		
		rootSubjects = new TreeItem<>();
		rootSubjects.setExpanded(true);
		
		for(ArrayList<ClassSchedule> possibleSchedules: scheduler.getWantedClassesSchedules()) {
			for(ClassSchedule schedule: possibleSchedules) {
				TreeItem<String> schedLeaf = new TreeItem<>(Utils.capitalize(schedule.getTeacher().getName().toLowerCase()));
				boolean found = false;
				
				for(TreeItem<String> subjectNode: rootSubjects.getChildren()) {
					if(subjectNode.getValue().contentEquals(schedule.getSubject().getName())) {
						subjectNode.getChildren().add(schedLeaf);
						found = true;
						break;
					}
				}
				
				if(!found) {
					TreeItem<String> subjectNode = new TreeItem<>(schedule.getSubject().getName());
					rootSubjects.getChildren().add(subjectNode);
					subjectNode.getChildren().add(schedLeaf);
				}
				
			}
		}
		
		
		subjectsTreeview = new TreeView<>(rootSubjects);
		subjectsTreeview.setShowRoot(false);
				
		// MARK: - Right pane ================================================================================================
		
		addSubject = new Button("+Añadir Clase");
		deleteSubject = new Button("-Borrar Clase");
		addSubjectSchedule = new Button("+Añadir Horario");
		deleteSubjectSchedule = new Button("-Borrar Horario");
		
		sortBarco = new Button("(+)Barcos");
		sortBueno = new Button("(+)Buenos");
		sortMamon = new Button("(+)Mamones");
		sortDTG = new Button("(+)Días");
		sortHuecos = new Button("(+)Huecos");

		sortLabel = new Label("Sorting by ");
		
		addSubject.setMinWidth(117);
		deleteSubject.setMinWidth(117);
		addSubjectSchedule.setMaxWidth(117);
		deleteSubjectSchedule.setMinWidth(117);
		
		sortBarco.setMinWidth(117);
		sortBueno.setMinWidth(117);
		sortMamon.setMinWidth(117);
		sortDTG.setMinWidth(117);
		sortHuecos.setMinWidth(117);
		
		addSubjectSchedule.setVisible(false);
		deleteSubjectSchedule.setVisible(false);
		
		sortMostBa = false;
		sortMostBu = false;
		sortMostMa = false;
		sortMostD = false;
		sortMostH = false;
		
		VBox rightMenuSubjects = new VBox(10);
		rightMenuSubjects.setPadding(new Insets(20, 20, 20, 20));
		rightMenuSubjects.getChildren().addAll(addSubject, deleteSubject, addSubjectSchedule, deleteSubjectSchedule);
		
		VBox rightMenuSchedules = new VBox(10);
		rightMenuSchedules.setPadding(new Insets(20, 20, 20, 20));
		rightMenuSchedules.getChildren().addAll(sortBarco, sortBueno, sortMamon, sortDTG, sortHuecos, sortLabel);

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
		
		// MARK: - Bottom Pane ================================================================================================
		
		possibleSchedsLV = new ListView<>();
		
		for (int i = 0; i < scheduler.getPossibleSchedules().size(); i++)
			possibleSchedsLV.getItems().add(Integer.toString(i + 1));
		
		subjectsSelected = true;
		schedulesSelected = false;
		
		subjectsView = new Button("Clases");
		schedulesView = new Button("Horarios");
		subjectsView.setId("rich-blue");
		schedulesView.setId("rich-red-selected");
		subjectsView.setMinWidth(545);
		schedulesView.setMinWidth(545);
		subjectsView.setMinHeight(40);
		schedulesView.setMinHeight(40);
		
		HBox bottomMenu = new HBox(0);
		bottomMenu.getChildren().addAll(subjectsView, schedulesView);
		
		// MARK: - Border Pane ================================================================================================
		
		borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		borderPane.setLeft(subjectsTreeview);
		borderPane.setCenter(grid);
		borderPane.setRight(rightMenuSubjects);
		borderPane.setBottom(bottomMenu);
		
		// MARK: - On Actions
		
		addSubject.setOnAction(e -> {
			//setUserAgentStylesheet(STYLESHEET_CASPIAN);
			Subject subject = UIAddSubjectView.display();
			if(subject.getName().length() > 0) {
				ArrayList<ClassSchedule> temp = new ArrayList<>();
				temp.add(new ClassSchedule(subject, new ArrayList<ClassHour>()));
				scheduler.addWantedClassSchedule(temp);
				
				Utils.makeBranch(subject.getName(), rootSubjects);
				scheduler.printWantedClasses();
			}
		});
		
		
		deleteSubject.setOnAction(e -> {
			
		});
		
		subjectsView.setOnAction(e -> {
			System.out.println("blue");
			if (!subjectsSelected) {
				subjectsView.setId("rich-blue");
				schedulesView.setId("rich-red-selected");
				
				borderPane.setLeft(subjectsTreeview);
				borderPane.setRight(rightMenuSubjects);
				
				Utils.colorUIClassHours(weekUIHours);
				Utils.hideTextUIClassHours(weekUIHours);
				
				subjectsSelected = true;
				schedulesSelected = false;
			}
		});
		
		schedulesView.setOnAction(e -> {
			System.out.println("red");
			if (!schedulesSelected) {
				subjectsView.setId("rich-blue-selected");
				schedulesView.setId("rich-red");
				
				borderPane.setLeft(possibleSchedsLV);
				borderPane.setRight(rightMenuSchedules);
				
				Utils.colorUIClassHours(weekUIHours, Color.LIGHTGRAY);
				Utils.hideTextUIClassHours(weekUIHours);
				
				scheduler.calcAllPossibleSchedules();
				scheduler.sumAllSchedules();
				
				int size = possibleSchedsLV.getItems().size();
				for (int i = 0; i < size; i++)
					possibleSchedsLV.getItems().remove(0);
				
				for (int i = 0; i < scheduler.getPossibleSchedules().size(); i++)
					possibleSchedsLV.getItems().add(Integer.toString(i + 1));
				
				subjectsSelected = false;
				schedulesSelected = true;
			}
		});
		
		sortBarco.setOnAction(e -> {
			if(sortMostBa) {
				scheduler.sortByLess(SortCategory.BARCO);
				sortBarco.setText("(+)Barcos");
				sortMostBa = false;
			} else {
				scheduler.sortByMore(SortCategory.BARCO);
				sortBarco.setText("(-)Barcos");
				sortMostBa = true;
			}
		});
		
		sortBueno.setOnAction(e -> {
			if(sortMostBu) {
				scheduler.sortByLess(SortCategory.BUENO);
				sortBueno.setText("(+)Buenos");
				sortMostBu = false;
			} else {
				scheduler.sortByMore(SortCategory.BUENO);
				sortBueno.setText("(-)Buenos");
				sortMostBu = true;
			}
		});
		
		sortMamon.setOnAction(e -> {
			if(sortMostMa) {
				scheduler.sortByLess(SortCategory.MAMON);
				sortMamon.setText("(+)Mamones");
				sortMostMa = false;
			} else {
				scheduler.sortByMore(SortCategory.MAMON);
				sortMamon.setText("(-)Mamones");
				sortMostMa = true;
			}
		});
		
		sortDTG.setOnAction(e -> {
			if(sortMostD) {
				scheduler.sortByLess(SortCategory.DAYSTOGO);
				sortDTG.setText("(+)Días");
				sortMostD = false;
				sortLabel.setText("Sorting by less DTG");
			} else {
				scheduler.sortByMore(SortCategory.DAYSTOGO);
				sortDTG.setText("(-)Días");
				sortMostD = true;
				sortLabel.setText("Sorting by more DTG");
			}
		});
		
		sortHuecos.setOnAction(e -> {
			if(sortMostH) {
				scheduler.sortByLess(SortCategory.HUECO);
				sortHuecos.setText("(+)Huecos");
				sortMostH = false;
				sortLabel.setText("Sorting by less huecos");
			} else {
				scheduler.sortByMore(SortCategory.HUECO);
				sortHuecos.setText("(-)Huecos");
				sortMostH = true;
				sortLabel.setText("Sorting by more huecos");
			}
		});
		
		// MARK: - TreeView Listener ================================================================================================
		
		subjectsTreeview.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
	        
			TreeItem<String> selectedItem = (TreeItem<String>) newValue;
			Utils.hideTextUIClassHours(weekUIHours);
			Utils.colorUIClassHours(weekUIHours);
			
			// Meaning a subject was selected, not a teacher
		    if(selectedItem.getParent().getValue() == null) {
		    	addSubjectSchedule.setVisible(true);
		    	deleteSubjectSchedule.setVisible(true);
		    	
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
            for(ArrayList<ClassSchedule> wantedSchedules: scheduler.getWantedClassesSchedules()) {
    			for(ClassSchedule schedule: wantedSchedules) {
    				try {
	    				if (selectedItem.getValue().equals(Utils.capitalize(schedule.getTeacher().getName().toLowerCase())) &&
	    						selectedItem.getParent().getValue().equals(schedule.getSubject().getName())) 
	    				
	    					Utils.displayClassSched(schedule, weekUIHours, Color.DARKRED);
    				} catch (NullPointerException e) {
    					
    				}
    			}
            }
		});
		
		possibleSchedsLV.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
			
			Utils.colorUIClassHours(weekUIHours, Color.LIGHTGRAY);
			Utils.hideTextUIClassHours(weekUIHours);
			
			Color[] colors = new Color[15];
			colors[0] = Color.CHOCOLATE;
			colors[1] = Color.STEELBLUE;
			colors[2] = Color.LIMEGREEN;
			colors[3] = Color.DARKVIOLET;
			colors[4] = Color.DEEPSKYBLUE;
			colors[5] = Color.DARKRED;
			colors[6] = Color.CORAL;
			colors[7] = Color.MEDIUMPURPLE;
			colors[8] = Color.YELLOW;
			colors[9] = Color.DEEPPINK;
			colors[10] = Color.BURLYWOOD;
			colors[11] = Color.ORANGERED;
			colors[12] = Color.MAGENTA;
			colors[13] = Color.SIENNA;
			colors[14] = Color.MEDIUMSEAGREEN;
			/*
			int o = 0;
			for(Schedule schedule: scheduler.getPossibleSchedules()) {
				
				System.out.println("Schedule#" + o + " ============================================");
				System.out.println("Days to Go: " +schedule.getDaysToGo());
				System.out.println("Huecos: " + schedule.getHuecos());
				System.out.println("SCHEDULE TO STRING: " + schedule.toString());
				o++;
			}
			*/
			try {
				int selectedItem = Integer.parseInt(newValue) - 1;
				
				for(int i = 0; i < scheduler.getPossibleSchedules().get(selectedItem).getClassSchedules().size(); i++)
					Utils.displayClassSched(
							scheduler.getPossibleSchedules().get(selectedItem).getClassSchedules().
							get(i), weekUIHours, colors[(i > 14) ? i - 15 : i]);
			} catch (NumberFormatException e) {
				
			}
		});
		
		// MARK: - Scene/Window/CSS ================================================================================================
		
		Scene scene = new Scene(borderPane, 1090, 600);
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
