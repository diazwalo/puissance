package core;

import model.Case;
import model.CaseContent;
import model.Movment;
import model.Plateau;

public class TestPlateauMethode {
	Plateau plateau4par4;
	protected TestPlateauMethode(Plateau plateau4par4) {
		// TODO Auto-generated constructor stub
		this.plateau4par4 = plateau4par4;
	}

	/**
	 * Applique le mouvment passé en parametre sur le plateau contenant 3 cases.
	 * Retourne le tableau contenant le resultat attendu apres l'operation "move"
	 * @param mvt
	 * @return Case[][] expected
	 */
	protected Case[][] executeMove(Movment mvt) {
		fillPlateauWith3Cases();
		
		Case[][] casesAfterMove = new Case[4][4];
		for (int i = 0; i < plateau4par4.getPlateau().length; i++) {
			for (int j = 0; j < plateau4par4.getPlateau()[i].length; j++) {
				casesAfterMove[i][j] = new Case(new CaseContent(0));
			}
		}
		
		moveCasesByMvt(mvt, casesAfterMove);
		plateau4par4.move(mvt);
		
		return casesAfterMove;
	}

	/**
	 * Place dans le tableau 3 cases à la puissance 1.
	 * On obtient un resultat de cette forme :
	 * 
	 *  [  ,  ,  ,  ]
	 *  [  , 2, 2,  ]
	 *  [  , 2,  ,  ]
	 *  [  ,  ,  ,  ]
	 * 
	 */
	protected void fillPlateauWith3Cases() {
		plateau4par4.fillPlateauWithZero();
		
		plateau4par4.getPlateau()[1][1] = new Case(new CaseContent(1));
		plateau4par4.getPlateau()[2][1] = new Case(new CaseContent(1));
		plateau4par4.getPlateau()[1][2] = new Case(new CaseContent(1));
	}

	/**
	 * Modifie le tablea de case passé en parametre pour qu'il contienne le
	 * resultat attendu apres le mouvment mvt lui aussi en parametre
	 * 
	 * @param mvt
	 * @param casesAfterMove
	 */
	protected void moveCasesByMvt(Movment mvt, Case[][] casesAfterMove) {
		// TODO Auto-generated method stub
		if(mvt.equals(Movment.UP)) {
			casesAfterMove[1][1] = new Case(new CaseContent(1));
			casesAfterMove[2][0] = new Case(new CaseContent(1));
			casesAfterMove[1][0] = new Case(new CaseContent(1));
		}else if(mvt.equals(Movment.DOWN)) {
			casesAfterMove[1][2] = new Case(new CaseContent(1));
			casesAfterMove[1][3] = new Case(new CaseContent(1));
			casesAfterMove[2][3] = new Case(new CaseContent(1));
		}else if(mvt.equals(Movment.LEFT)) {
			casesAfterMove[0][1] = new Case(new CaseContent(1));
			casesAfterMove[1][1] = new Case(new CaseContent(1));
			casesAfterMove[0][2] = new Case(new CaseContent(1));
		}else if(mvt.equals(Movment.RIGHT)) {
			casesAfterMove[2][1] = new Case(new CaseContent(1));
			casesAfterMove[3][1] = new Case(new CaseContent(1));
			casesAfterMove[3][2] = new Case(new CaseContent(1));
		}
	}

	/**
	 * Applique le mouvment passé en parametre sur le plateau contenant 3 cases.
	 * Puis applique la fusion des cases du à ce meme mvt.
	 * Retourne le tableau contenant le resultat attendu apres l'operation "fusion"
	 * 
	 * @param mvt
	 * @return Case[][] expected
	 */
	protected Case[][] executeFusion(Movment mvt) {
		Case[][] casesAfterFusion = executeMove(mvt);
		
		fusionCasesByMvt(mvt, casesAfterFusion);
		plateau4par4.fusion(mvt);
		
		return casesAfterFusion;
	}
	
	/**
	 * /**
	 * Modifie le tablea de case passé en parametre pour qu'il contienne le
	 * resultat attendu apres la fusion du au mvt lui aussi en parametre
	 * 
	 * @param mvt
	 * @param casesAfterFusion
	 */
	protected void fusionCasesByMvt(Movment mvt, Case[][] casesAfterFusion) {
		if(mvt.equals(Movment.UP)) {
			casesAfterFusion[1][1] = new Case(new CaseContent(0));
			casesAfterFusion[2][0] = new Case(new CaseContent(1));
			casesAfterFusion[1][0] = new Case(new CaseContent(2));
		}else if(mvt.equals(Movment.DOWN)) {
			casesAfterFusion[1][2] = new Case(new CaseContent(0));
			casesAfterFusion[1][3] = new Case(new CaseContent(2));
			casesAfterFusion[2][3] = new Case(new CaseContent(1));
		}else if(mvt.equals(Movment.LEFT)) {
			casesAfterFusion[0][1] = new Case(new CaseContent(2));
			casesAfterFusion[1][1] = new Case(new CaseContent(0));
			casesAfterFusion[0][2] = new Case(new CaseContent(1));
		}else if(mvt.equals(Movment.RIGHT)) {
			casesAfterFusion[2][1] = new Case(new CaseContent(0));
			casesAfterFusion[3][1] = new Case(new CaseContent(2));
			casesAfterFusion[3][2] = new Case(new CaseContent(1));
		}
	}
	
	/**
	 * Bloque totallement le tableau de Cases.
	 */
	protected void setPlateauBlocked() {
		int idx = 0;
		for (int i = 0; i < plateau4par4.getPlateau().length; i++) {
			for (int j = 0; j < plateau4par4.getPlateau()[i].length; j++) {
				plateau4par4.getPlateau()[i][j] = new Case(new CaseContent(++idx));
			}
		}
	}
	
	/*
	 * Bloque presque le tablea de Cases.
	 * (tableau plein mais 2 cases peuvent être fusionnées).
	 */
	protected void setPlateauAlmostBlocked() {
		setPlateauBlocked();
		plateau4par4.getPlateau()[0][0] = new Case(new CaseContent(0));
	}
	
	protected void setTheGameWin() {
		plateau4par4.getPlateau()[0][0] = new Case(new CaseContent(plateau4par4.getMinimalPowToWin()));
	}
}
