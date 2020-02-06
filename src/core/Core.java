package core;

import game.GameClassic;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewGame;

public class Core extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		GameClassic gc = new GameClassic(4);
		ViewGame vg = new ViewGame(gc);
		
		vg.createScene(primaryStage);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
