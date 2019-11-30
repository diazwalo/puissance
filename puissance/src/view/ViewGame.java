package view;

import java.awt.GraphicsEnvironment;

import core.Plateau;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewGame {
	private Plateau plateau = new Plateau();
	private Stage stage;
	private Scene sc;
	private Pane pane;
	private VBox core;
	private GraphicsEnvironment ge;
	
	public ViewGame(Plateau p) {
		this.pane = new GridPane();
		this.core = new VBox();
		this.plateau = p;
		this.ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	}

	public void createScene(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("La Puissance");
	    //stage.getIcons().add(new Image(chemin));
	    this.stage.setMaximized(true);
	    this.stage.setResizable(false);
	    this.stage.setMaxHeight(this.getWinHeight());
	    this.stage.setMaxWidth(this.getWinWidth());
	    
		for (int row = 0; row < plateau.getPlateau().length; row++) {
			for (int col = 0; col < plateau.getPlateau()[row].length; col++) {
				StackPane stack = new StackPane();
				Rectangle rec = new Rectangle();
				
				rec.setWidth(this.getWinWidth() / this.plateau.getPlateau().length);
				rec.setHeight(this.getWinHeight() / this.plateau.getPlateau().length);
				rec.setStroke(Color.BLACK);
				rec.setFill(Color.ALICEBLUE);
				
				Text text = new Text(plateau.getPlateau()[row][col].getContent().toString());
				text.setFont(Font.font(0.3 * rec.getHeight()));
				
				stack.getChildren().addAll(rec, text);
				
				GridPane.setRowIndex(stack, col);
				GridPane.setColumnIndex(stack, row);
				this.pane.getChildren().addAll(stack);
			}
		}
		this.core.getChildren().add(pane);
		this.sc = new Scene(this.core);
		this.stage.setScene(this.sc);
		
		this.stage.show();
	}
	
	public double getWinHeight() {
		return this.ge.getMaximumWindowBounds().getHeight();
	}
	
	public double getWinWidth() {
		return this.ge.getMaximumWindowBounds().getWidth();
	}
}
