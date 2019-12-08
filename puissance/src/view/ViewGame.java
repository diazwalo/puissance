package view;

import java.awt.GraphicsEnvironment;

import core.Movment;
import core.Plateau;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewGame {
	private Plateau plateau;
	private Stage stage;
	private Scene sc;
	private Pane pane;
	private HBox core;
	private GraphicsEnvironment ge;
	private double sizeCell;
	private VBox informations;
	private Label labelScore;
	private Label labelTitleScore;
	private Button keepPlaying;
	private Button quitte;
	private Button restart;
	private Button exitGame;
	
	public ViewGame(Plateau p) {
		this.pane = new GridPane();
		this.core = new HBox();
		this.informations = new VBox();
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
	    
		this.refreshViewPlateau();
		this.core.getChildren().add(pane);
		this.createViewInformation();
		this.informations.getChildren().add(labelTitleScore);
		this.informations.getChildren().add(labelScore);
		this.informations.getChildren().add(restart);
		this.informations.getChildren().add(exitGame);
		this.informations.setStyle("-fx-text-align: center;");
		this.core.getChildren().add(informations);
		
		this.sc = new Scene(this.core);
		this.addEventToStage();
		this.stage.setScene(this.sc);
		
		this.stage.show();
	}
	
	private void createViewInformation() {
		this.core.setStyle("-fx-background-color:#900066;"
				+ "-fx-color : #050505;");
		this.informations.setStyle("-fx-color : #002080;");
		
		this.informations.setMaxSize(this.getWinWidth() - this.sizeCell*this.plateau.getPlateau().length, this.getWinHeight());
		
		this.labelTitleScore = new Label("SCORE :");
		this.labelScore = new Label("" + this.plateau.getScoreToString());
		
		this.restart = new Button("RESTART");
		this.exitGame = new Button("EXIT GAME");
	}

	private void refreshViewPlateau() {
		for (int row = 0; row < plateau.getPlateau().length; row++) {
			for (int col = 0; col < plateau.getPlateau()[row].length; col++) {
				StackPane stack = new StackPane();
				Rectangle rec = new Rectangle();
				double pow = this.plateau.getPlateau()[row][col].getContent().getPow();
				double color = (pow * 0.042) % 1;
				
				rec.setWidth(this.getWinHeight() / this.plateau.getPlateau().length);
				rec.setHeight(this.getWinHeight() / this.plateau.getPlateau().length);
				rec.setFill(new Color(color + 0.1, color = 0.2, color + 0.3, 1.0));
				rec.setStroke(Color.BLACK);
				this.sizeCell = rec.getWidth();
				
				Text text = new Text(plateau.getPlateau()[row][col].getContent().toString());
				text.setFill(new Color(1-color, 1-color, 1-color, 1.0));
				text.setFont(Font.font(0.3 * rec.getHeight()));
				
				stack.getChildren().addAll(rec, text);
				
				GridPane.setRowIndex(stack, col);
				GridPane.setColumnIndex(stack, row);
				this.pane.getChildren().addAll(stack);
			}
		}
	}
	
	private void refreshViewInformation() {
		this.labelScore.setText("" + this.plateau.getScoreToString());
	}

	private void addEventToStage() {
		this.stage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			KeyCode key = e.getCode();
			String osName = System.getProperty("os.name");
			if(osName.contentEquals("Mac OS X")) {
				if(key.equals(KeyCode.W) || key.equals(KeyCode.S) || key.equals(KeyCode.A) || key.equals(KeyCode.D)) {
					switch(key) {
					case W :
						this.move(Movment.UP);
						break;
					case S :
						this.move(Movment.DOWN);
						break;
					case A :
						this.move(Movment.LEFT);
						break;
					case D :
						this.move(Movment.RIGHT);
						break;
					default:
						break;
					}
				}

			}else {	
				if(key.equals(KeyCode.Z) || key.equals(KeyCode.S) || key.equals(KeyCode.Q) || key.equals(KeyCode.D)) {
	
					switch(key) {
					case Z :
						this.move(Movment.UP);
						break;
					case S :
						this.move(Movment.DOWN);
						break;
					case Q :
						this.move(Movment.LEFT);
						break;
					case D :
						this.move(Movment.RIGHT);
						break;
					default:
						break;
					}
				}
			}
		});
		this.setOnActionInformation();
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private void setOnActionInformation() {
		// TODO Auto-generated method stub
		this.exitGame.setOnAction(e -> {
			System.exit(0);
		});
		
		this.restart.setOnAction(e -> {
			this.plateau = new Plateau(this.plateau.getPlateau().length);
			this.refreshViewPlateau();
			this.refreshViewInformation();
		});
	}

	public void move(Movment movment) {
		boolean moveDone = this.plateau.move(movment);
		boolean fusionDone = this.plateau.fusion(movment);
		boolean mvtDone = moveDone || fusionDone;
		
		if(mvtDone) {
			this.plateau.move(movment);
			this.plateau.generateRandomCase();
			this.refreshViewPlateau();
			this.refreshViewInformation();
		}
		
		this.verifEnd();
	}
	
	public void verifEnd() {
		if(this.plateau.win()) {
			Scene winScreenScene;
			Label labelGameWin = new Label("You have reached 2048 !");
			this.keepPlaying = new Button("CONTINUE");
			this.quitte = new Button("EXIT");
			
			VBox endScreen = new VBox();
			HBox choice = new HBox();
			
			
			endScreen.getChildren().add(labelGameWin);
			choice.getChildren().add(this.keepPlaying);
			choice.getChildren().add(this.quitte);
			endScreen.getChildren().add(choice);
			
			winScreenScene = new Scene(endScreen);
			
			this.stage.hide();
			Stage endStage = new Stage();
			endStage.setScene(winScreenScene);
			endStage.show();
			
			keepPlaying.setOnAction(e -> {
				//this.createScene(stage);
				endStage.hide();
				this.stage.setScene(this.sc);
				this.stage.show();
			});
			
			quitte.setOnAction(e ->{
				System.exit(0);
			});
		}
		if(this.plateau.blocked()) {
			System.exit(1);
		}
	}
	
	public double getWinHeight() {
		return this.ge.getMaximumWindowBounds().getHeight();
	}
	
	public double getWinWidth() {
		return this.ge.getMaximumWindowBounds().getWidth();
	}
}
