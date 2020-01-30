package core;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Case;
import model.CaseContent;
import model.Movment;
import model.Plateau;

public class TestPlateau {
	private Plateau plateau4par4 = new Plateau();
	private Plateau plateau8par8 = new Plateau(8);
	private Plateau plateau2par2 = new Plateau(2);

	@Before
	public void before() {
		plateau4par4 = new Plateau();
	}

	@Test
	public void test_taille_plateau() {
		// plateau4par4 est initialisÃ© a 4 de longueur si on ne donne pas de taille
		assertTrue(plateau4par4.getPlateau().length == 4);
		assertTrue(plateau4par4.getPlateau()[0].length == 4);
		
		assertTrue(plateau8par8.getPlateau().length == 8);
		assertTrue(plateau8par8.getPlateau()[0].length == 8);
		
		// plateau2par2 sera initialisé à 4 de longueur car c'est le minimum
		assertTrue(plateau2par2.getPlateau().length == 4);
		assertTrue(plateau2par2.getPlateau()[0].length == 4);
	}

	@Test
	public void test_creation_plateau_vide() {
		this.plateau4par4.fillPlateauWithZero();
		for (Case[] idx : this.plateau4par4.getPlateau()) {
			for (Case cellule : idx) {
				assertEquals(cellule.getContent(), new CaseContent(0));
			}
		}
	}

	@Test
	public void test_generation_case_aleatiore() {
		int nbrCaseDiffZero = 0;
		int[] firstCase = {-1, -1};
		int[] secondCase = {-1, -1};
		
		this.plateau4par4.fillPlateauWithZero();
		this.plateau4par4.generateRandomCase();
		this.plateau4par4.generateRandomCase();
		for (int idxL = 0; idxL < this.plateau4par4.getPlateau().length; idxL++) {
			for (int idxC = 0; idxC < this.plateau4par4.getPlateau()[idxL].length; idxC++) {
				Case caseCour = this.plateau4par4.getPlateau()[idxL][idxC];
				if(caseCour.getContent().getPow() != 0) {
					if(nbrCaseDiffZero != 0) {
						firstCase = new int[]{idxL, idxC};
					}else {
						secondCase = new int[]{idxL, idxC};
					}
					nbrCaseDiffZero += 1;
				}
			}
		}
		
		assertNotEquals(firstCase, secondCase);
		assertEquals(nbrCaseDiffZero, 2);
	}
	
	@Test
	public void test_deplacement_cases_plateau() {
		Case[][] casesAfterMoveUp = executeMove(Movment.UP);
		assertArrayEquals(casesAfterMoveUp, plateau4par4.getPlateau());
		
		Case[][] casesAfterMoveDown = executeMove(Movment.DOWN);
		assertArrayEquals(casesAfterMoveDown, plateau4par4.getPlateau());
		
		Case[][] casesAfterMoveLeft = executeMove(Movment.LEFT);
		assertArrayEquals(casesAfterMoveLeft, plateau4par4.getPlateau());
		
		Case[][] casesAfterMoveRight = executeMove(Movment.RIGHT);
		assertArrayEquals(casesAfterMoveRight, plateau4par4.getPlateau());
	}

	private Case[][] executeMove(Movment mvt) {
		plateau4par4.fillPlateauWithZero();
		
		plateau4par4.getPlateau()[1][1] = new Case(new CaseContent(1));
		plateau4par4.getPlateau()[2][1] = new Case(new CaseContent(1));
		plateau4par4.getPlateau()[1][2] = new Case(new CaseContent(1));
		
		Case[][] casesAfterMove = new Case[4][4];
		for (int i = 0; i < plateau4par4.getPlateau().length; i++) {
			for (int j = 0; j < plateau4par4.getPlateau()[i].length; j++) {
				casesAfterMove[i][j] = new Case(new CaseContent(0));
			}
		}
		
		modifieCasesByMvt(mvt, casesAfterMove);
		
		plateau4par4.move(mvt);
		return casesAfterMove;
	}

	private void modifieCasesByMvt(Movment mvt, Case[][] casesAfterMove) {
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
		}else {
			
		}
	}
}
