package model;

import java.util.Random;

public class Plateau {
	protected Case[][] plateau;
	private int score;
	private boolean win = false;
	
	public Plateau (int taille) {
		if(taille < 4) {
			this.plateau = new Case[4][4];
		}else {
			this.plateau = new Case[taille][taille];
		}
		this.initialisePlateau();
		this.score = 0;
	}

	public Plateau () {
		this(4);
	}
	
	public Case[][] getPlateau() {
		return this.plateau;
	}
	
	public void setPlateau(Case[][] plateau) {
		this.plateau = plateau;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public String getScoreToString() {
		final int MAX_SIZE = 7; 
		String scoreToString = this.score + "";
		int bounds = MAX_SIZE - scoreToString.length();
		
		for (int i = 0; i < bounds; i++) {
			scoreToString = "0" + scoreToString;
		}
		
		return scoreToString;
	}
	
	protected void initialisePlateau() {
		this.fillPlateauWithZero();
		this.generateRandomCase();
		this.generateRandomCase();
	}
	
	public void fillPlateauWithZero() {
		for (int idxTabX = 0; idxTabX < this.plateau.length; idxTabX++) {
			for (int idxTabY = 0; idxTabY < this.plateau[idxTabX].length; idxTabY++) {
				this.plateau[idxTabX][idxTabY] = new Case(new CaseContent(0));
			}
		}
	}

	public void generateRandomCase() {
		int[] posRan = this.getRandomPosition(this.plateau.length, this.plateau.length);
		
		while(! this.plateau[posRan[0]][posRan[1]].equals(new Case(new CaseContent(0)))) {
			posRan = this.getRandomPosition(this.plateau.length, this.plateau.length);
		}
		
		Random ran = new Random();
		int perCent = ran.nextInt(100);
		if(perCent < 80) {
			this.plateau[posRan[0]][posRan[1]] = new Case(new CaseContent(1));
		}else {
			this.plateau[posRan[0]][posRan[1]] = new Case(new CaseContent(2));
		}
	}
	
	protected int[] getRandomPosition(int rangeX, int rangeY) {
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
		/*int idxTabX;
		int idxTabY;
		int bounds;
		int incr;*/
		
		if(mvt.equals(Movment.DOWN) || mvt.equals(Movment.RIGHT)) {
			/*idxTabX = this.plateau.length-2;
			idxTabY = this.plateau[idxTabX].length -2;
			bounds  = -1;
			incr = -1;*/
			
			for (int idxTabX = this.plateau.length-1; idxTabX >= 0; idxTabX--) {
				for (int idxTabY = this.plateau[idxTabX].length; idxTabY >= 0; idxTabY--) {
					int[] posTempo = new int[] {idxTabX + mvt.getMovment()[0], idxTabY + mvt.getMovment()[1]};
					if(posTempo[0] >= 0 && posTempo[0] < this.plateau.length && 
					   posTempo[1] >= 0 && posTempo[1] < this.plateau[posTempo[0]].length) {
						if(! this.plateau[idxTabX][idxTabY].equals(new Case(new CaseContent(0))) &&
						   this.plateau[posTempo[0]][posTempo[1]].equals(this.plateau[idxTabX][idxTabY])) {
							this.plateau[posTempo[0]][posTempo[1]].getContent().incPow();
							this.plateau[idxTabX][idxTabY].getContent().setPow(0);
							this.majScore(this.plateau[posTempo[0]][posTempo[1]].getContent().getPow());
							count++;
						}
					}
				}
			}
		}else {
			/*idxTabX = - 1;
			idxTabY = - 1;
			bounds = this.plateau.length;
			incr = 1;*/
			
			for (int idxTabX = 0; idxTabX < this.plateau.length; idxTabX++) {
				for (int idxTabY = 0; idxTabY < this.plateau[idxTabX].length; idxTabY++) {
					int[] posTempo = new int[] {idxTabX + mvt.getMovment()[0], idxTabY + mvt.getMovment()[1]};
					if(posTempo[0] >= 0 && posTempo[0] < this.plateau.length && 
					   posTempo[1] >= 0 && posTempo[1] < this.plateau[posTempo[0]].length) {
						if(! this.plateau[idxTabX][idxTabY].equals(new Case(new CaseContent(0))) &&
						   this.plateau[posTempo[0]][posTempo[1]].equals(this.plateau[idxTabX][idxTabY])) {
							this.plateau[posTempo[0]][posTempo[1]].getContent().incPow();
							this.plateau[idxTabX][idxTabY].getContent().setPow(0);
							this.majScore(this.plateau[posTempo[0]][posTempo[1]].getContent().getPow());
							count++;
						}
					}
				}
			}
		}
		
		/*for (idxTabX++; idxTabX < this.plateau.length; idxTabX += incr) {
			for (idxTabY++; idxTabY < this.plateau[idxTabX].length; idxTabY += incr) {
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
		}*/
		
		return count != 0;
	}
	
	public void majScore(int pow) {
		this.score += Math.pow(2, pow);
	}
	
	public boolean isBlocked() {
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
	
	public boolean isWin() {
		//11 pour 2048
		final int WIN_POW = 7;
		if(! this.win) {
			for (int idxTabX = 0; idxTabX < this.plateau.length; idxTabX++) {
				for (int idxTabY = 0; idxTabY < this.plateau[idxTabX].length; idxTabY++) {
					if(this.plateau[idxTabX][idxTabY].getContent().getPow() >= WIN_POW) {
						this.win = true;
						return true;
					}
				}
			}
		}
		return false;
	}
}
