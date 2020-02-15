package view;

import game.GameClassic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ViewInformation {
	private VBox viewInformation;
	private HBox containerButton;
	
	private Label labelScore;
	private Label labelTitleScore;
	private Button restart;
	private Button exitGame;
	
	private GameClassic gc;
	
	public ViewInformation(GameClassic gc, HBox containerButton) {
		this.viewInformation = new VBox();
		this.containerButton = containerButton;
		
		this.gc = gc;
	}
	
	public VBox getViewInformation() {
		return this.viewInformation;
	}
	
	public void createViewInformation(double sizeCell) {
		this.viewInformation.setStyle("-fx-background-color : #F0C300;"
				+ "-fx-color : #F0C300;");
		
		this.viewInformation.setMaxSize(ViewGame.getWinWidth() - sizeCell*this.gc.getPlateau().getPlateau().length, ViewGame.getWinHeight());
		
		this.labelTitleScore = new Label("SCORE :");
		this.labelTitleScore.setStyle("-fx-font-family: \"arial\";"
				+ "-fx-color: white;"
				+ "-fx-display: inline;"
				+ "-fx-text-align: center;"
				+ "-fx-border-width: 3px;"
				+ "-fx-border-style: solid;"
				+ "-fx-border-color: black;"
				+ "-fx-border-radius:15px;"
				+ "-fx-padding: 10px;"
				+ "-fx-font-size : " + (ViewGame.getWinWidth() - sizeCell*this.gc.getPlateau().getPlateau().length)/6 + ";");
		
		this.labelScore = new Label("" + this.gc.getPlateau().getScoreToString());
		this.labelScore.setStyle("-fx-font-family: \"arial\";"
				+ "-fx-color: white;"
				+ "-fx-display: inline;"
				+ "-fx-text-align: center;"
				+ "-fx-border-width: 3px;"
				+ "-fx-border-style: solid;"
				+ "-fx-border-color: black;"
				+ "-fx-border-radius:15px;"
				+ "-fx-padding: 10px;"
				+ "-fx-font-size : " + (ViewGame.getWinWidth() - sizeCell*this.gc.getPlateau().getPlateau().length)/6 + ";");
		
		this.labelScore.setTextFill(Color.RED);
		this.labelTitleScore.setTextFill(Color.RED);

		this.labelScore.setPadding(new Insets(40));
		
		this.containerButton = new HBox();
		this.restart = new Button("RESTART");
		this.restart.setStyle("-fx-border: solid;"
				+ "-fx-background-color : #F0C300;"
				+ "-fx-border-width: 3px;"
				+ "-fx-border-color: black;"
				+ "-fx-border-radius:15px;"
				+ "-fx-font-size : " + (ViewGame.getWinWidth() - sizeCell*this.gc.getPlateau().getPlateau().length)/12 + ";");
		this.exitGame = new Button("EXIT GAME");
		this.exitGame.setStyle("-fx-border: solid;"
				+ "-fx-background-color : #F0C300;"
				+ "-fx-border-width: 3px;"
				+ "-fx-border-color: black;"
				+ "-fx-border-radius:15px;"
				+ "-fx-font-size : " + (ViewGame.getWinWidth() - sizeCell*this.gc.getPlateau().getPlateau().length)/12 + ";");
		this.exitGame.setTextFill(Color.RED);
		this.restart.setTextFill(Color.RED);
		
		this.containerButton.getChildren().add(this.restart);
		this.containerButton.getChildren().add(this.exitGame);
		
		this.viewInformation.setAlignment(Pos.CENTER);
		this.containerButton.setAlignment(this.viewInformation.getAlignment());
		this.viewInformation.getChildren().add(labelTitleScore);
		this.viewInformation.getChildren().add(labelScore);
		this.viewInformation.getChildren().add(new Label());
		this.viewInformation.getChildren().add(this.containerButton);
	}

	protected void refreshViewInformation() {
		this.labelScore.setText("" + this.gc.getPlateau().getScoreToString());
	}
	
	protected HBox getContainerButton() {
		return this.containerButton;
	}
}
