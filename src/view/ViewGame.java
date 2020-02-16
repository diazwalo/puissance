package view;

import java.awt.GraphicsEnvironment;

import controller.Controller;
import game.GameClassic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Content;

public class ViewGame {
	private Stage primaryStage;
	private Stage endStage;
	private Scene sc;
	
	private static GraphicsEnvironment ge;
	
	private HBox viewGame;
	private ViewInformation viewInfo;
	private ViewPlateau viewPlateau;
	private ViewEndScreen viewEndScreen;
	
	private HBox containerButtonInformation;
	
	private Texture texture;
	
	private GameClassic gc;
	
	private Controller controller;
	
	public ViewGame(GameClassic gc) {
		this.gc = gc;
		this.viewGame = new HBox();
		this.viewEndScreen = new ViewEndScreen(primaryStage, gc);
		
		this.containerButtonInformation = new HBox();

		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Content c = gc.getPlateau().getContent();
		this.texture = new Texture(c.getContent().length, c.getContent(), c.isImgContent(), new double[] {240.0/255, 195/255, 0.0});
		
		this.viewPlateau = new ViewPlateau(this.gc, this.texture);
	    this.viewInfo = new ViewInformation(this.gc, this.containerButtonInformation);
	    
	    this.controller = new Controller(gc);
	}
	
	public void createScene(Stage stage) {
		this.primaryStage = stage;
		this.primaryStage.setTitle("La Puissance");
	    this.primaryStage.getIcons().add(new Image("laPuissance.jpg"));
	    this.primaryStage.setMaximized(true);
	    this.primaryStage.setResizable(false);
	    this.primaryStage.setMaxHeight(getWinHeight());
	    this.primaryStage.setMaxWidth(getWinWidth());

	    this.viewPlateau.refreshViewPlateau();
	    this.viewGame.getChildren().add(this.viewPlateau.getViewPlateau());
	    this.viewInfo.createViewInformation(this.viewPlateau.getSizeCell());
	    this.viewGame.getChildren().add(this.viewInfo.getViewInformation());
	    
	    this.containerButtonInformation = this.viewInfo.getContainerButton();
		applicateStyleOnViewGame();
		
		this.sc = new Scene(this.viewGame);
		this.addEventToStage();
		this.primaryStage.setScene(this.sc);
		
		this.primaryStage.show();
	}
	
	private void applicateStyleOnViewGame() {
		this.viewGame.setBackground(new Background(new BackgroundFill(new Color(240.0/255, 195.0/255, 0.0, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
	    this.viewGame.setAlignment(Pos.CENTER);
	}
	
	private void addEventToStage() {
		this.primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if(this.controller.setActionOnPlateau(e)) {
				this.viewPlateau.refreshViewPlateau();
				this.viewInfo.refreshViewInformation();
				this.verifEnd();
			}
		});
			
		this.setOnActionInformation();
	}
	
	private void setOnActionInformation() {
		((Button) this.containerButtonInformation.getChildren().get(1)).setOnAction(e -> {
			System.exit(0);
		});
		
		((Button) this.containerButtonInformation.getChildren().get(0)).setOnAction(e -> {
			this.gc.resetPlateau();
			this.viewPlateau.refreshViewPlateau();
			this.viewInfo.refreshViewInformation();
		});
	}
	
	public void verifEnd() {
		if(this.gc.getPlateau().isWin()) {
			this.endStage = this.viewEndScreen.createEndScreen(true);
			this.setOnActionEndScreen(endStage, true);
		}else if(this.gc.getPlateau().isBlocked()) {
			this.endStage = this.viewEndScreen.createEndScreen(false);
			this.setOnActionEndScreen(endStage, false);
		}
	}
	
	private void setOnActionEndScreen(Stage endStage, boolean win) {
		HBox containerButtonEndScreen = this.viewEndScreen.getContainerButtonEndScreen();
		((Button) containerButtonEndScreen.getChildren().get(0)).setOnAction(e -> {
			if(win) {
				endStage.hide();
				this.primaryStage.setScene(this.sc);
				this.primaryStage.show();
			}else {
				endStage.hide();
				this.primaryStage.setScene(this.sc);
				this.primaryStage.show();
				this.gc.resetPlateau();
				this.viewPlateau.refreshViewPlateau();
				this.viewInfo.refreshViewInformation();
			}
		});
		
		((Button) containerButtonEndScreen.getChildren().get(1)).setOnAction(e ->{
			System.exit(0);
		});
	}
	
	public static double getWinHeight() {
		return ge.getMaximumWindowBounds().getHeight();
	}
	
	public static double getWinWidth() {
		return ge.getMaximumWindowBounds().getWidth();
	}
}
