package core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Core {
	public static void main(String[] args) {
		playGame();
	}
	
	private static boolean playGame() {
		boolean win = false;
		
		Plateau p = new Plateau();
		Scanner sc = new Scanner(System.in);
		
		while(! p.blocked() && !win) {
			Movment mvt = null;
			boolean saisieValide = false;
			
			System.out.print("\n" + p + "\n Move (Z, Q, S, D) : ");
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
			
			p.move(mvt);
			p.fusion(mvt);
			p.move(mvt);
			p.generateRandomCase();
			win = p.win();
		}
		sc.close();
		
		System.out.println("\n" + p);
		if(win) {
			System.out.println("Congratulation Random Player !");
		}else {
			System.out.println("u DuMbAsS gO iT Ur ShIt !");
		}

		return win;
	}
	
	private void test() {
		Plateau p = new Plateau();
		System.out.println("Init : \n" + p);
		
		p.move(Movment.UP);
		System.out.println("Move : \n" + p);
		
		p.fusion(Movment.UP);
		System.out.println("Fusion : \n" + p);
		
		boolean blocked = p.blocked();
		System.out.println("Blocked : " + blocked + "\n");
		
		int idx = 0;
		for (int i = 0; i < p.plateau.length; i++) {
			for (int j = 0; j < p.plateau[i].length; j++) {
				p.plateau[i][j] = new Case(new CaseContent(++idx));
			}
		}
		System.out.println("Block : \n" + p);
		
		blocked = p.blocked();
		System.out.println("Blocked : " + blocked + "\n");
	}
}
