package view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class UIClassHour extends StackPane {
	
	private int x, y;
	private Rectangle border = new Rectangle(70, 50);
	private Color color;
	
	public UIClassHour(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		
		border.setFill(color);
		
		getChildren().add(border);
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
	}
	
	
}
