package view;

import java.awt.GraphicsEnvironment;

import game.GameClassic;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Content;
import model.Movment;

public class ViewGame {
	private Stage stage;
	private Scene sc;
	
	private static GraphicsEnvironment ge;
	
	private HBox viewGame;
	private ViewInformation viewInfo;
	private ViewPlateau viewPlateau;
	
	private HBox containerButton;
	private Button keepPlaying;
	private Button exitTheGame;
	
	private Texture texture;
	
	private GameClassic gc;
	
	public ViewGame(GameClassic gc) {
		this.gc = gc;
		//this.pane = new GridPane();
		this.viewGame = new HBox();
		//this.informations = new VBox();
		
		this.containerButton = new HBox();

		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Content c = gc.getPlateau().getContent();
		this.texture = new Texture(c.getContent().length, c.getContent(), c.isImgContent(), new double[] {240.0/255, 195/255, 0.0});
		
		this.viewPlateau = new ViewPlateau(this.gc, this.texture);
	    this.viewInfo = new ViewInformation(this.gc, this.containerButton);
	}

	public void createScene(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("La Puissance");
	    this.stage.getIcons().add(new Image("laPuissance.jpg"));
	    this.stage.setMaximized(true);
	    this.stage.setResizable(false);
	    this.stage.setMaxHeight(getWinHeight());
	    this.stage.setMaxWidth(getWinWidth());
	    
//		this.refreshViewPlateau();
//		this.viewGame.getChildren().add(pane);
//		this.createViewInformation();
//		
//		this.informations.getChildren().add(labelTitleScore);
//		this.informations.getChildren().add(labelScore);
//		this.informations.getChildren().add(new Label());
//		this.informations.getChildren().add(this.containerButton);
//		
//		this.informations.setStyle("-fx-text-align: center;");
//		this.viewGame.getChildren().add(informations);
	    
	    this.viewPlateau.refreshViewPlateau();
	    this.viewGame.getChildren().add(this.viewPlateau.getViewPlateau());
	    this.viewInfo.createViewInformation(this.viewPlateau.getSizeCell());
	    this.viewGame.getChildren().add(this.viewInfo.getViewInformation());
	    
	    this.containerButton = this.viewInfo.getContainerButton();
	    
		this.sc = new Scene(this.viewGame);
		this.addEventToStage();
		this.stage.setScene(this.sc);
		
		this.stage.show();
	}
//	
//	private void createViewInformation() {
//		this.viewGame.setStyle("-fx-background-color : #F0C300;"
//				+ "-fx-color : #F0C300;");
//		
//		this.informations.setMaxSize(this.getWinWidth() - this.sizeCell*this.gc.getPlateau().getPlateau().length, this.getWinHeight());
//		
//		this.labelTitleScore = new Label("SCORE :");
//		this.labelTitleScore.setStyle("-fx-font-family: \"arial\";"
//				+ "-fx-color: white;"
//				+ "-fx-display: inline;"
//				+ "-fx-text-align: center;"
//				+ "-fx-border-width: 3px;"
//				+ "-fx-border-style: solid;"
//				+ "-fx-border-color: black;"
//				+ "-fx-border-radius:15px;"
//				+ "-fx-padding: 10px;"
//				+ "-fx-font-size : " + (this.getWinWidth() - this.sizeCell*this.gc.getPlateau().getPlateau().length)/6 + ";");
//		
//		this.labelScore = new Label("" + this.gc.getPlateau().getScoreToString());
//		this.labelScore.setStyle("-fx-font-family: \"arial\";"
//				+ "-fx-color: white;"
//				+ "-fx-display: inline;"
//				+ "-fx-text-align: center;"
//				+ "-fx-border-width: 3px;"
//				+ "-fx-border-style: solid;"
//				+ "-fx-border-color: black;"
//				+ "-fx-border-radius:15px;"
//				+ "-fx-padding: 10px;"
//				+ "-fx-font-size : " + (this.getWinWidth() - this.sizeCell*this.gc.getPlateau().getPlateau().length)/6 + ";");
//		
//		this.labelScore.setTextFill(Color.RED);
//		this.labelTitleScore.setTextFill(Color.RED);
//
//		this.labelScore.setPadding(new Insets(40));
//		
//		this.containerButton = new HBox();
//		this.restart = new Button("RESTART");
//		this.restart.setStyle("-fx-border: solid;"
//				+ "-fx-background-color : #F0C300;"
//				+ "-fx-border-width: 3px;"
//				+ "-fx-border-color: black;"
//				+ "-fx-border-radius:15px;"
//				+ "-fx-font-size : " + (this.getWinWidth() - this.sizeCell*this.gc.getPlateau().getPlateau().length)/12 + ";");
//		this.exitGame = new Button("EXIT GAME");
//		this.exitGame.setStyle("-fx-border: solid;"
//				+ "-fx-background-color : #F0C300;"
//				+ "-fx-border-width: 3px;"
//				+ "-fx-border-color: black;"
//				+ "-fx-border-radius:15px;"
//				+ "-fx-font-size : " + (this.getWinWidth() - this.sizeCell*this.gc.getPlateau().getPlateau().length)/12 + ";");
//		this.exitGame.setTextFill(Color.RED);
//		this.restart.setTextFill(Color.RED);
//		
//		
//		
//		this.containerButton.getChildren().add(this.restart);
//		this.containerButton.getChildren().add(this.exitGame);
//		
//		this.informations.setAlignment(Pos.CENTER);
//		this.containerButton.setAlignment(this.informations.getAlignment());
//		
//	}

//	private void refreshViewPlateau() {
//		for (int row = 0; row < gc.getPlateau().getPlateau().length; row++) {
//			for (int col = 0; col < gc.getPlateau().getPlateau()[row].length; col++) {
//				StackPane stack = drawStack(row, col);
//				
//				GridPane.setRowIndex(stack, col);
//				GridPane.setColumnIndex(stack, row);
//				this.pane.getChildren().addAll(stack);
//			}
//		}
//	}

	/**
	 * Dessine la cellule du tableau à l'indice indiqué par la colone et la ligne passé en parametre
	 * @param row
	 * @param col
	 * @return
	 */
//	private StackPane drawStack(int row, int col) {
//		StackPane stack = new StackPane();
//		Rectangle rec = new Rectangle();
//		Text text = new Text();
//		
//		double pow = this.gc.getPlateau().getPlateau()[row][col].getContent().getPow();
//		rec.setWidth(this.getWinHeight() / this.gc.getPlateau().getPlateau().length);
//		rec.setHeight(this.getWinHeight() / this.gc.getPlateau().getPlateau().length);
//		this.sizeCell = rec.getWidth();
//
//		rec.setFill(texture.getTexturePaint((int)pow));
//		
//		if(! this.gc.getPlateau().isImgContent()) {
//			text = new Text(gc.getPlateau().getPlateau()[row][col].getContent().toString());
//			text.setFill(((Color)(rec.getFill())).invert()); 
//			text.setFont(Font.font(0.3 * rec.getHeight()));
//		}
//		
//		rec.setStroke(this.texture.getStrokeColor());
//		stack.getChildren().addAll(rec, text);
//		return stack;
//	}
//	
//	private void refreshViewInformation() {
//		this.labelScore.setText("" + this.gc.getPlateau().getScoreToString());
//	}

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
		((Button) this.containerButton.getChildren().get(1)).setOnAction(e -> {
			System.exit(0);
		});
		
		((Button) this.containerButton.getChildren().get(0)).setOnAction(e -> {
			this.gc.resetPlateau();
			this.viewPlateau.refreshViewPlateau();
			this.viewInfo.refreshViewInformation();
		});
	}

	public void move(Movment movment) {
		boolean moveDone = this.gc.getPlateau().move(movment);
		boolean fusionDone = this.gc.getPlateau().fusion(movment);
		boolean mvtDone = moveDone || fusionDone;
		
		if(mvtDone) {
			this.gc.getPlateau().move(movment);
			this.gc.getPlateau().generateRandomCase();
			this.viewPlateau.refreshViewPlateau();
			this.viewInfo.refreshViewInformation();
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
		}
	}
	
	private void applyStyleVerifEnd() {
		
	}

	private void setOnActionEndScreen(Stage endStage, boolean win) {
		keepPlaying.setOnAction(e -> {
			if(win) {
				endStage.hide();
				this.stage.setScene(this.sc);
				this.stage.show();
			}else {
				endStage.hide();
				this.stage.setScene(this.sc);
				this.stage.show();
				this.gc.resetPlateau();
				this.viewPlateau.refreshViewPlateau();
				this.viewInfo.refreshViewInformation();
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
		
		Stage endStage = new Stage();
		endStage.setScene(winScreenScene);
		endStage.centerOnScreen();
		endStage.initModality(Modality.WINDOW_MODAL);
		endStage.initOwner(this.stage);
		endStage.show();
		return endStage;
	}
	
	public static double getWinHeight() {
		return ge.getMaximumWindowBounds().getHeight();
	}
	
	public static double getWinWidth() {
		return ge.getMaximumWindowBounds().getWidth();
	}
}
