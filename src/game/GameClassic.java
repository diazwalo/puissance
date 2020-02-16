package game;

import java.util.Scanner;

import model.Movment;
import model.Plateau;

public class GameClassic {
	private Plateau p;
	private int taille;
	
	public GameClassic(int taille) {
		this.taille = taille;
		this.p = new Plateau(this.taille);
	}
	
	public Plateau getPlateau() {
		return this.p;
	}
	
	public void resetPlateau() {
		this.p.resetPlateau();
	}
	
	public boolean playGame() {
		boolean win = false;
		Scanner sc = new Scanner(System.in);
		
		while(! p.isBlocked() && !win) {
			clearScreen();
			System.out.print("\n" + p + "\n Move (Z, Q, S, D) : ");
			
			boolean mvtDone = false;
			Movment mvt = null;
			while(! mvtDone) {
				
				mvt = saisieMovment(sc);
				
				boolean moveDone = p.move(mvt);
				boolean fusionDone = p.fusion(mvt);
				mvtDone = moveDone || fusionDone;
			}
			p.move(mvt);
			p.generateRandomCase();
			win = p.isWin();
		}
		sc.close();
		
		System.out.println("\n" + p);
		endGameScreen(win);
		
		return win;
	}
	
	public static void clearScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
	public String endGameScreen(boolean win) {
		if(win) {
			return "Congratulation Random Player !";
		}else {
			return "u DuMbAsS gO iT Ur ShIt !";
		}
	}
	
	public static Movment saisieMovment(Scanner sc) {
		Movment mvt = null;
		boolean saisieValide = false;
		
		while(! saisieValide) {
			saisieValide = true;
			String line = sc.nextLine();
			if(line.toLowerCase().equals("q")) {
				mvt = Movment.LEFT;
			}else if(line.toLowerCase().equals("d")) {
				mvt = Movment.RIGHT;
			}else if(line.toLowerCase().equals("z")) {
				mvt = Movment.UP;
			}else if(line.toLowerCase().equals("s")) {
				mvt = Movment.DOWN;
			}else {
				saisieValide = false;
			}
		}
		return mvt;
	}
}
