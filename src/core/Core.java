package core;

import game.GameClassic;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Case;
import model.CaseContent;
import model.Movment;
import model.Plateau;
import view.ViewGame;

public class Core extends Application{
	private void test() {
		Plateau p = new Plateau();
		System.out.println("Init : \n" + p);
		
		p.move(Movment.UP);
		System.out.println("Move : \n" + p);
		
		p.fusion(Movment.UP);
		System.out.println("Fusion : \n" + p);
		
		boolean blocked = p.isBlocked();
		System.out.println("Blocked : " + blocked + "\n");
		
		int idx = 0;
		for (int i = 0; i < p.getPlateau().length; i++) {
			for (int j = 0; j < p.getPlateau()[i].length; j++) {
				p.getPlateau()[i][j] = new Case(new CaseContent(++idx));
			}
		}
		System.out.println("Block : \n" + p);
		
		blocked = p.isBlocked();
		System.out.println("Blocked : " + blocked + "\n");
	}

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
