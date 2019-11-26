package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class Window extends Application{
	public Stage stage;
	
	public void start(Stage stage) {
		this.stage = stage;
		
	    stage.setTitle("La Puissance");
	    //stage.getIcons().add(new Image(chemin));
	    stage.setMaximized(true);
	    stage.setResizable(false);
	    stage.show();
	    
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
