package core;

import java.util.Random;

public class Plateau {
	Case[][] plateau;
	
	public Plateau (int taille) {
		this.plateau = new Case[taille][taille];
		this.initialisePlateau();
	}

	public Plateau () {
		this(4);
	}
	
	public Case[][] getPlateau() {
		return this.plateau;
	}
	
	private void initialisePlateau() {
		for (int idxTabX = 0; idxTabX < this.plateau.length; idxTabX++) {
			for (int idxTabY = 0; idxTabY < this.plateau[idxTabX].length; idxTabY++) {
				this.plateau[idxTabX][idxTabY] = new Case(new CaseContent(0));
			}
		}
		this.generateRandomCase();
		this.generateRandomCase();
	}
	
	public void generateRandomCase() {
		int[] posRan = this.getRandomPosition(this.plateau.length, this.plateau.length);
		
		while(! this.plateau[posRan[0]][posRan[1]].equals(new Case(new CaseContent(0)))) {
			posRan = this.getRandomPosition(this.plateau.length, this.plateau.length);
		}
		
		Random ran = new Random();
		int perCent = ran.nextInt(100);
		if(perCent < 75) {
			this.plateau[posRan[0]][posRan[1]] = new Case(new CaseContent(1));
		}else {
			this.plateau[posRan[0]][posRan[1]] = new Case(new CaseContent(2));
		}
	}
	
	private int[] getRandomPosition(int rangeX, int rangeY) {
		int[] randomPos = new int[] {0, 0};
		Random ran = new Random();
		randomPos[0] = ran.nextInt(rangeX);
		randomPos[1] = ran.nextInt(rangeY);
		return randomPos;
	}
	
	public String toString() {
		String res = "";
		
		for (int idxTabX = 0; idxTabX < plateau.length; idxTabX++) {
			res += "          | ";
			for (int idxTabY = 0; idxTabY < plateau[idxTabX].length; idxTabY++) {
				if(idxTabY != 0) {
					res += " . ";
				}
				res += this.plateau[idxTabY][idxTabX].toString();
			}
			res += " |\n";
		}
		
		return res;
	}
	
	public boolean move(Movment mvt) {
		boolean moveFinish = false;
		int count = 0;

		while(! moveFinish) {
			moveFinish = true;
			for (int idxTabX = 0; idxTabX < this.plateau.length; idxTabX++) {
				for (int idxTabY = 0; idxTabY < this.plateau[idxTabX].length; idxTabY++) {
					int[] posTempo = new int[] {idxTabX + mvt.getMovment()[0], idxTabY + mvt.getMovment()[1]};
					if(posTempo[0] >= 0 && posTempo[0] < this.plateau.length && 
					   posTempo[1] >= 0 && posTempo[1] < this.plateau[posTempo[0]].length) {
						if(! this.plateau[idxTabX][idxTabY].equals(new Case(new CaseContent(0))) && 
						   this.plateau[posTempo[0]][posTempo[1]].equals(new Case(new CaseContent(0)))) {
							this.plateau[posTempo[0]][posTempo[1]] = this.plateau[idxTabX][idxTabY];
							this.plateau[idxTabX][idxTabY] = new Case(new CaseContent(0));
							moveFinish = false;
							count++;
						}
					}
				}
			}
		}
		
		return count != 0;
	}
	
	public boolean fusion(Movment mvt) {
		int count = 0;
		
		if(mvt.equals(Movment.DOWN) || mvt.equals(Movment.RIGHT)) {
			for (int idxTabX = this.plateau.length-1; idxTabX >= 0; idxTabX--) {
				for (int idxTabY = this.plateau[idxTabX].length; idxTabY >= 0; idxTabY--) {
					int[] posTempo = new int[] {idxTabX + mvt.getMovment()[0], idxTabY + mvt.getMovment()[1]};
					if(posTempo[0] >= 0 && posTempo[0] < this.plateau.length && 
					   posTempo[1] >= 0 && posTempo[1] < this.plateau[posTempo[0]].length) {
						if(! this.plateau[idxTabX][idxTabY].equals(new Case(new CaseContent(0))) &&
						   this.plateau[posTempo[0]][posTempo[1]].equals(this.plateau[idxTabX][idxTabY])) {
							this.plateau[posTempo[0]][posTempo[1]].getContent().incPow();
							this.plateau[idxTabX][idxTabY].getContent().setPow(0);
							count++;
						}
					}
				}
			}
		}else {
			for (int idxTabX = 0; idxTabX < this.plateau.length; idxTabX++) {
				for (int idxTabY = 0; idxTabY < this.plateau[idxTabX].length; idxTabY++) {
					int[] posTempo = new int[] {idxTabX + mvt.getMovment()[0], idxTabY + mvt.getMovment()[1]};
					if(posTempo[0] >= 0 && posTempo[0] < this.plateau.length && 
					   posTempo[1] >= 0 && posTempo[1] < this.plateau[posTempo[0]].length) {
						if(! this.plateau[idxTabX][idxTabY].equals(new Case(new CaseContent(0))) &&
						   this.plateau[posTempo[0]][posTempo[1]].equals(this.plateau[idxTabX][idxTabY])) {
							this.plateau[posTempo[0]][posTempo[1]].getContent().incPow();
							this.plateau[idxTabX][idxTabY].getContent().setPow(0);
							count++;
						}
					}
				}
			}
		}
		return count != 0;
	}
	
	public boolean blocked() {
		Movment[] mvts = Movment.values();
		for (Movment movment : mvts) {
			for (int idxTabX = 0; idxTabX < this.plateau.length; idxTabX++) {
				for (int idxTabY = 0; idxTabY < this.plateau[idxTabX].length; idxTabY++) {
					int[] posTempo = new int[] {idxTabX + movment.getMovment()[0], idxTabY + movment.getMovment()[1]};
					if(posTempo[0] >= 0 && posTempo[0] < this.plateau.length && 
					   posTempo[1] >= 0 && posTempo[1] < this.plateau[posTempo[0]].length) {
						if(this.plateau[idxTabX][idxTabY].equals(new Case(new CaseContent(0))) ||
						   this.plateau[posTempo[0]][posTempo[1]].equals(this.plateau[idxTabX][idxTabY])) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean win() {
		for (int idxTabX = 0; idxTabX < this.plateau.length; idxTabX++) {
			for (int idxTabY = 0; idxTabY < this.plateau[idxTabX].length; idxTabY++) {
				if(this.plateau[idxTabX][idxTabY].getContent().getPow() >= 11) {
					return true;
				}
			}
		}
		return false;
	}
}
