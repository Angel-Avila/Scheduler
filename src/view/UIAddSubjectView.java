package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Subject;

public class UIAddSubjectView {
	
	public static Subject display() {
		Stage window = new Stage();
		
		Subject subject = new Subject("");
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Añadir Clase");
		
		Label label = new Label("Nombre de la materia: ");
		TextField nameInput = new TextField();
		
		nameInput.setMaxWidth(310);
		nameInput.setPromptText("Nombre");
		
		Button button = new Button("Añadir");
		button.setOnAction(e ->  {	
			if(nameInput.getText() != null && nameInput.getText() != "") {
				subject.setName(nameInput.getText());
				window.close();
			}
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, nameInput, button);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout, 350, 150);
		window.setScene(scene);
		window.showAndWait();

		return subject;
	}
	
}
