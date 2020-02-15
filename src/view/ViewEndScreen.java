package view;

import game.GameClassic;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewEndScreen {
	private Stage primaryStage;
	
	private Button keepPlaying;
	private Button exitTheGame;
	private HBox containerButtonEndScreen;

	private GameClassic gc;
	
	public ViewEndScreen(Stage primaryStage, GameClassic gc) {
		this.primaryStage = primaryStage;
		this.gc = gc;
	}
	
	protected Stage createEndScreen(boolean win) {
		Label labelEndGame = new Label(this.gc.endGameScreen(win));
		if(win) {
			this.keepPlaying = new Button("CONTINUE");
		}else {
			this.keepPlaying = new Button("RESTART");
		}
		
		this.exitTheGame = new Button("EXIT");
		
		VBox endScreen = new VBox();
		this.containerButtonEndScreen = new HBox();
		
		endScreen.getChildren().add(labelEndGame);
		containerButtonEndScreen.getChildren().add(this.keepPlaying);
		containerButtonEndScreen.getChildren().add(this.exitTheGame);
		endScreen.getChildren().add(containerButtonEndScreen);
		
		Scene winScreenScene = new Scene(endScreen);
		
		Stage endStage = new Stage();
		endStage.setScene(winScreenScene);
		endStage.centerOnScreen();
		endStage.initModality(Modality.WINDOW_MODAL);
		endStage.initOwner(this.primaryStage);
		endStage.show();
		return endStage;
	}
	
	protected HBox getContainerButtonEndScreen() {
		return this.containerButtonEndScreen;
	}
}
