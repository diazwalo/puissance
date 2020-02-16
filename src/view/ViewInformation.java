package view;

import game.GameClassic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class ViewInformation {
	private VBox viewInformation;
	private HBox containerButtonInformation;
	
	private Label labelScore;
	private Label labelTitleScore;
	private Button buttonRestart;
	private Button buttonExitGame;
	
	private GameClassic gc;
	
	public ViewInformation(GameClassic gc, HBox containerButtonInformation) {
		this.viewInformation = new VBox();
		this.containerButtonInformation = containerButtonInformation;
		
		this.gc = gc;
	}
	
	public VBox getViewInformation() {
		return this.viewInformation;
	}
	
	protected HBox getContainerButton() {
		return this.containerButtonInformation;
	}

	public void createViewInformation(double sizeCell) {
		this.labelTitleScore = new Label("SCORE :");
		this.labelScore = new Label(this.gc.getPlateau().getScoreToString());
		
		this.buttonRestart = new Button("RESTART");
		this.buttonExitGame = new Button("EXIT GAME");
		
		this.containerButtonInformation = new HBox();
		this.containerButtonInformation.getChildren().add(this.buttonRestart);
		this.containerButtonInformation.getChildren().add(this.buttonExitGame);
		
		this.viewInformation.getChildren().add(labelTitleScore);
		this.viewInformation.getChildren().add(labelScore);
		this.viewInformation.getChildren().add(new Label());
		this.viewInformation.getChildren().add(this.containerButtonInformation);
		
		this.setStyleOnInformation(sizeCell);
	}
	
	public void setStyleOnInformation(double sizeCell) {
		this.viewInformation.setBackground(new Background(new BackgroundFill(new Color(240.0/255, 195.0/255, 0.0, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
		this.viewInformation.setAlignment(Pos.CENTER);
		this.containerButtonInformation.setAlignment(this.viewInformation.getAlignment());
		
		this.applicateStyleOnLabel(sizeCell, this.labelTitleScore);
		this.applicateStyleOnLabel(sizeCell, this.labelScore);
		
		this.applicateStyleOnButton(sizeCell, this.buttonRestart);
		this.applicateStyleOnButton(sizeCell, this.buttonExitGame);
	}
	
	private void applicateStyleOnLabel(double sizeCell, Label l) {
		l.setTextFill(Color.RED);
		l.setTextAlignment(TextAlignment.CENTER);
		l.setFont(Font.font("Arial", FontWeight.BOLD, (ViewGame.getWinWidth() - sizeCell*this.gc.getPlateau().getPlateau().length)/6));
		l.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
		l.setBackground(new Background(new BackgroundFill(new Color(240.0/255, 195.0/255, 0.0, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	private void applicateStyleOnButton(double sizeCell, Button b) {
		b.setTextFill(Color.RED);
		b.setTextAlignment(TextAlignment.CENTER);
		b.setFont(Font.font("Arial", (ViewGame.getWinWidth() - sizeCell*this.gc.getPlateau().getPlateau().length)/16));
		b.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
		b.setBackground(new Background(new BackgroundFill(new Color(240.0/255, 195.0/255, 0.0, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	protected void refreshViewInformation() {
		this.labelScore.setText("" + this.gc.getPlateau().getScoreToString());
	}
}
