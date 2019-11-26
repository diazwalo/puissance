package view;

import core.Plateau;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ViewGame{
	private Window window;
	Plateau plateau = new Plateau();
	private Scene sc;
	private Pane pane;
	
	public ViewGame(Plateau p) {
		this.window = new Window();
		this.pane = new GridPane();
		this.plateau = p;
		createScene();
	}

	private void createScene() {
		for (int row = 0; row < plateau.getPlateau().length; row++) {
			for (int col = 0; col < plateau.getPlateau()[row].length; col++) {
				Rectangle rec = new Rectangle();
				rec.setWidth(20);
				rec.setHeight(20);
				rec.setFill(Color.ALICEBLUE);
				
				GridPane.setRowIndex(rec, col);
				GridPane.setColumnIndex(rec, row);
				this.pane.getChildren().addAll(rec);
			}
		}
		this.sc = new Scene(this.pane);
		this.window.stage.setScene(this.sc);
	}
}
