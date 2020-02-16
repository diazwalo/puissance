package controller;

import game.GameClassic;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Movment;

public class Controller {
	GameClassic gc;
	KeyCode key;
	String osName;

	public Controller(GameClassic gc) {
		this.gc = gc;
		osName = System.getProperty("os.name");
	}

	public boolean setActionOnPlateau(KeyEvent e) {
		this.key = e.getCode();
		
		if(osName.contentEquals("Mac OS X")) {
				return executeMoveForQWERTY(key);
		}else {	
				return executeMoveForAZERTY(key);
		}
	}

	private boolean executeMoveForQWERTY(KeyCode key) {
		switch(key) {
		case W :
			return this.move(Movment.UP);
		case S :
			return this.move(Movment.DOWN);
		case A :
			return this.move(Movment.LEFT);
		case D :
			return this.move(Movment.RIGHT);
		default :
			return false;
		}
	}

	private boolean executeMoveForAZERTY(KeyCode key) {
		switch(key) {
		case Z :
			return this.move(Movment.UP);
		case S :
			return this.move(Movment.DOWN);
		case Q :
			return this.move(Movment.LEFT);
		case D :
			return this.move(Movment.RIGHT);
		default :
			return false;
		}
	}

	public boolean move(Movment movment) {
		boolean moveDone = this.gc.getPlateau().move(movment);
		boolean fusionDone = this.gc.getPlateau().fusion(movment);
		boolean mvtDone = moveDone || fusionDone;

		if(mvtDone) {
			this.gc.getPlateau().move(movment);
			this.gc.getPlateau().generateRandomCase();
			return true;
		}
		return false;
	}
}
