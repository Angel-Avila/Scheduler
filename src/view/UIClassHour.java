package view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class UIClassHour extends StackPane {
	
	private int x, y;
	private Rectangle border = new Rectangle(70, 50);
	private Color color;
	private final Color originalColor;
	private boolean selected;
	private Text text = new Text();
	
	public UIClassHour(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.originalColor = color;
		
		selected = false;
		
		border.setFill(color);
		border.setStroke(originalColor);
		getChildren().addAll(border, text);
	}
	
	public UIClassHour(int x, int y, Color color, EventHandler<? super MouseEvent> value) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.originalColor = color;
		
		border.setFill(color);
		border.setStroke(originalColor);
		setOnMouseClicked(value);
		getChildren().addAll(border, text);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		border.setFill(color);
		border.setStroke(color);
	}
	
	public void revertColor() {
		border.setFill(originalColor);
		border.setStroke(originalColor);
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void onMouseClicked(EventHandler<? super MouseEvent> value) {
		setOnMouseClicked(value);
	}
	
	public void setText(String text) {
		this.text.setVisible(true);
		this.text.setText(text);
		this.text.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
		this.text.setFill(Color.WHITE);
	}
	
	public void hideText() {
		text.setVisible(false);
	}
}
