package view;

import java.awt.GraphicsEnvironment;

import game.GameClassic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Movment;

public class ViewGame {
	private GameClassic gc;
	private Stage stage;
	private Scene sc;
	private Pane pane;
	private HBox core;
	private GraphicsEnvironment ge;
	private double sizeCell;
	private VBox informations;
	private Label labelScore;
	private Label labelTitleScore;
	private HBox containerButton;
	private Button restart;
	private Button exitGame;
	private Button keepPlaying;
	private Button exitTheGame;
	
	public ViewGame(GameClassic gc) {
		this.gc = gc;
		this.pane = new GridPane();
		this.core = new HBox();
		this.informations = new VBox();

		this.ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	}

	public void createScene(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("La Puissance");
	    this.stage.getIcons().add(new Image("laPuissance.jpg"));
	    this.stage.setMaximized(true);
	    this.stage.setResizable(false);
	    this.stage.setMaxHeight(this.getWinHeight());
	    this.stage.setMaxWidth(this.getWinWidth());
	    
		this.refreshViewPlateau();
		this.core.getChildren().add(pane);
		this.createViewInformation();
		
		this.informations.getChildren().add(labelTitleScore);
		this.informations.getChildren().add(labelScore);
		this.informations.getChildren().add(new Label());
		this.informations.getChildren().add(this.containerButton);
		
		this.informations.setStyle("-fx-text-align: center;");
		this.core.getChildren().add(informations);
		
		this.sc = new Scene(this.core);
		this.addEventToStage();
		this.stage.setScene(this.sc);
		
		this.stage.show();
	}
	
	private void createViewInformation() {
		this.core.setStyle("-fx-background-color : #F0C300;"
				+ "-fx-color : #F0C300;");
		
		this.informations.setMaxSize(this.getWinWidth() - this.sizeCell*this.gc.getPlateau().getPlateau().length, this.getWinHeight());
		
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
				+ "-fx-font-size : " + (this.getWinWidth() - this.sizeCell*this.gc.getPlateau().getPlateau().length)/6 + ";");
		
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
				+ "-fx-font-size : " + (this.getWinWidth() - this.sizeCell*this.gc.getPlateau().getPlateau().length)/6 + ";");
		
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
				+ "-fx-font-size : " + (this.getWinWidth() - this.sizeCell*this.gc.getPlateau().getPlateau().length)/12 + ";");
		this.exitGame = new Button("EXIT GAME");
		this.exitGame.setStyle("-fx-border: solid;"
				+ "-fx-background-color : #F0C300;"
				+ "-fx-border-width: 3px;"
				+ "-fx-border-color: black;"
				+ "-fx-border-radius:15px;"
				+ "-fx-font-size : " + (this.getWinWidth() - this.sizeCell*this.gc.getPlateau().getPlateau().length)/12 + ";");
		this.exitGame.setTextFill(Color.RED);
		this.restart.setTextFill(Color.RED);
		
		
		
		this.containerButton.getChildren().add(this.restart);
		this.containerButton.getChildren().add(this.exitGame);
		
		this.informations.setAlignment(Pos.CENTER);
		this.containerButton.setAlignment(this.informations.getAlignment());
		
		//this.applyStyleOnInformation();
	}

	private void applyStyleOnInformation() {
		// TODO Auto-generated method stub
		this.applyStyleOnLabel(this.labelTitleScore);
		//double paddLeft = this.gc.getPlateau().getScoreToString().length() /2 * 12;
		//this.labelScore.setPadding(new Insets(0, 0, 0, paddLeft));
		//this.applyStyleOnLabel(this.labelScore);
		//this.applyStyleOnButton(this.restart);
		//this.applyStyleOnButton(this.exitGame);
	}

	private void refreshViewPlateau() {
		for (int row = 0; row < gc.getPlateau().getPlateau().length; row++) {
			for (int col = 0; col < gc.getPlateau().getPlateau()[row].length; col++) {
				StackPane stack = drawStack(row, col);
				
				GridPane.setRowIndex(stack, col);
				GridPane.setColumnIndex(stack, row);
				this.pane.getChildren().addAll(stack);
			}
		}
	}

	/**
	 * Dessine la cellule du tableau à l'indice indiqué par la colone et la ligne passé en parametre
	 * @param row
	 * @param col
	 * @return
	 */
	private StackPane drawStack(int row, int col) {
		StackPane stack = new StackPane();
		Rectangle rec = new Rectangle();
		Text text = new Text();
		
		double pow = this.gc.getPlateau().getPlateau()[row][col].getContent().getPow();
		rec.setWidth(this.getWinHeight() / this.gc.getPlateau().getPlateau().length);
		rec.setHeight(this.getWinHeight() / this.gc.getPlateau().getPlateau().length);
		this.sizeCell = rec.getWidth();
		
		if(! this.gc.getPlateau().isImgContent()) {
			double color = Double.parseDouble(this.gc.getPlateau().getFillForPow((int)pow));
			
			rec.setFill(new Color(color, color, color, 1.0));
			
			text = new Text(gc.getPlateau().getPlateau()[row][col].getContent().toString());
			text.setFill(Color.DARKRED); 
			/*new Color(1-color, 1-color, 1-color, 1.0)  
			 * si on veut l'inverse de la couleur de l'ecriture
			 */
			text.setFont(Font.font(0.3 * rec.getHeight()));
		}else {
			if(pow == 0) {
				rec.setFill(new Color(0.0, 0.0, 0.0, 1.0));
			}else {
				rec.setFill(new ImagePattern(new Image(this.gc.getPlateau().getFillForPow((int)pow))));
			}
		}
		
		rec.setStroke(new Color((240.0/255), (195.0/255), 0.0, 1.0));
		stack.getChildren().addAll(rec, text);
		return stack;
	}
	
	private void refreshViewInformation() {
		this.labelScore.setText("" + this.gc.getPlateau().getScoreToString());
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
	
	private void setOnActionInformation() {
		// TODO Auto-generated method stub
		this.exitGame.setOnAction(e -> {
			System.exit(0);
		});
		
		this.restart.setOnAction(e -> {
			this.gc.resetPlateau();
			this.refreshViewPlateau();
			this.refreshViewInformation();
		});
	}

	public void move(Movment movment) {
		boolean moveDone = this.gc.getPlateau().move(movment);
		boolean fusionDone = this.gc.getPlateau().fusion(movment);
		boolean mvtDone = moveDone || fusionDone;
		
		if(mvtDone) {
			this.gc.getPlateau().move(movment);
			this.gc.getPlateau().generateRandomCase();
			this.refreshViewPlateau();
			this.refreshViewInformation();
		}
		
		this.verifEnd();
	}
	
	public void verifEnd() {
		if(this.gc.getPlateau().isWin()) {
			Stage endStage = this.createEndScreen(true);
			this.setOnActionEndScreen(endStage, true);
			this.applyStyleVerifEnd();
		}
		if(this.gc.getPlateau().isBlocked()) {
			Stage endStage = this.createEndScreen(false);
			this.setOnActionEndScreen(endStage, false);
			this.applyStyleVerifEnd();
			this.applyStyleVerifEnd();
		}
	}
	
	private void applyStyleVerifEnd() {
		// TODO Auto-generated method stub
		
	}

	private void setOnActionEndScreen(Stage endStage, boolean win) {
		// TODO Auto-generated method stub
		keepPlaying.setOnAction(e -> {
			//this.createScene(stage);
			if(win) {
				endStage.hide();
				this.stage.setScene(this.sc);
				this.stage.show();
			}else {
				endStage.hide();
				this.stage.setScene(this.sc);
				this.stage.show();
				this.gc.resetPlateau();
				this.refreshViewPlateau();
				this.refreshViewInformation();
			}
		});
		
		exitTheGame.setOnAction(e ->{
			System.exit(0);
		});
	}

	private Stage createEndScreen(boolean win) {
		// TODO Auto-generated method stub
		Scene winScreenScene;
		Label labelEndGame = new Label(this.gc.endGameScreen(win));
		if(win) {
			this.keepPlaying = new Button("CONTINUE");
		}else {
			this.keepPlaying = new Button("RESTART");
		}
		
		this.exitTheGame = new Button("EXIT");
		
		VBox endScreen = new VBox();
		HBox choice = new HBox();
		
		endScreen.getChildren().add(labelEndGame);
		choice.getChildren().add(this.keepPlaying);
		choice.getChildren().add(this.exitTheGame);
		endScreen.getChildren().add(choice);
		
		winScreenScene = new Scene(endScreen);
		
		//this.stage.hide();
		
		Stage endStage = new Stage();
		endStage.setScene(winScreenScene);
		endStage.centerOnScreen();
		endStage.initModality(Modality.WINDOW_MODAL);
		endStage.initOwner(this.stage);
		endStage.show();
		return endStage;
	}
	
	public void applyStyleOnLabel(Label l) {
		l.setPadding(new Insets(this.getWinHeight() / 2, 0, 0, 0));
	}
	
	public void applyStyleOnButton(Button b) {
		b.setPadding(new Insets(this.getWinHeight() / 2, 0, 0, 0));
	}

	public double getWinHeight() {
		return this.ge.getMaximumWindowBounds().getHeight();
	}
	
	public double getWinWidth() {
		return this.ge.getMaximumWindowBounds().getWidth();
	}
}
