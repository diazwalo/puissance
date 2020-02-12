package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plateau {
	protected Case[][] plateau;
	protected Content content;
	protected view.Texture texture;
	private int score;
	private boolean win = false;
	private final int MINIMAL_POW_TO_WIN;
	
	public Plateau (int taille) {
		if(taille < 4) {
			this.plateau = new Case[4][4];
		}else {
			this.plateau = new Case[taille][taille];
		}
		this.initialisePlateau();
		this.score = 0;
		this.MINIMAL_POW_TO_WIN = 11; //Car 2^11 6 -> 2048
		this.content = new Content();
		this.fillContent();
//		this.fillContentWithImg(new String [] {
//				"lize_1.jfif",
//				"nishiki_2.jfif",
//				"shuu_3.jfif", 
//				"ayato_4.jfif",
//				"yamori_5.jfif",
//				"yukinori_6.jfif",
//				"juzo_7.jfif",
//				"akira_8.jfif",
//				"kotaro_9.jfif",
//				"renji_10.jfif",
//				"ken_11.jfif",
//				"kuzen_12.jpg",
//				"kisho_13.jfif",
//				"hideyoshi_14.jfif",
//				"eto_15.jfif",
//				"kichimura_16.jfif"
//		});
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
	
	public void majScore(int pow) {
		this.score += Math.pow(2, pow);
	}
	
	public Content getContent() {
		return this.content;
	}

	public int getMinimalPowToWin() {
		return this.MINIMAL_POW_TO_WIN;
	}
	
	public void fillContent() {
		for (int i = 0; i < 17; i++) {
			double color = (i * 0.042) % 1;
			this.content.setFillForPow(i, color+"");
		}
	}
	
	public void fillContentWithImg(String[] pathImg) {
		this.content.setFillForPow(0, "0.0");
		for (int i = 0; i < pathImg.length; i++) {
			this.content.setFillForPow((i+1), pathImg[i]);
		}
		this.content.setImgContent(true);
	}
	
	public String getFillForPow(int pow) {
		return this.content.getFillForPow(pow);
	}
	
	public boolean isImgContent() {
		return this.content.isImgContent();
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
				this.plateau[idxTabY][idxTabX] = new Case(new CaseContent(0));
			}
		}
	}

	public void generateRandomCase() {
		List<int[]> emptyCase = getEmptyCase();
		
		Random ran = new Random();
		int[] idxCaseRandom = emptyCase.get(ran.nextInt(emptyCase.size()));
		int perCent = ran.nextInt(100);
		
		if(perCent < 80) {
			this.plateau[idxCaseRandom[0]][idxCaseRandom[1]] = new Case(new CaseContent(1));
		}else {
			this.plateau[idxCaseRandom[0]][idxCaseRandom[1]] = new Case(new CaseContent(2));
		}
	}
	
	public List<int[]> getEmptyCase() {
		List<int[]> emptyCase = new ArrayList<>();
		
		for (int idxTabX = 0; idxTabX < this.plateau.length; idxTabX++) {
			for (int idxTabY = 0; idxTabY < this.plateau[idxTabX].length; idxTabY++) {
				if(this.plateau[idxTabY][idxTabX].getContent().getPow() == 0) {
					emptyCase.add(new int[] {idxTabY, idxTabX});
				}
			}
		}
		
		return emptyCase;
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
		if(! this.win) {
			for (int idxTabX = 0; idxTabX < this.plateau.length; idxTabX++) {
				for (int idxTabY = 0; idxTabY < this.plateau[idxTabX].length; idxTabY++) {
					if(this.plateau[idxTabX][idxTabY].getContent().getPow() >= MINIMAL_POW_TO_WIN) {
						this.win = true;
						return true;
					}
				}
			}
		}
		return false;
	}
}
