package core;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Case;
import model.CaseContent;
import model.Movment;
import model.Plateau;

public class TestPlateau {
	protected Plateau plateau4par4 = new Plateau();
	protected Plateau plateau8par8 = new Plateau(8);
	protected Plateau plateau2par2 = new Plateau(2);
	protected TestPlateauMethode methode;

	@Before
	public void before() {
		plateau4par4 = new Plateau();
		methode = new TestPlateauMethode(plateau4par4);
	}

	@Test
	public void test_taille_plateau() {
		// plateau4par4 est initialisé à 4 de longueur si on ne donne pas de taille
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
		Case[][] casesAfterMoveUp = methode.executeMove(Movment.UP);
		assertArrayEquals(casesAfterMoveUp, plateau4par4.getPlateau());
		
		Case[][] casesAfterMoveDown = methode.executeMove(Movment.DOWN);
		assertArrayEquals(casesAfterMoveDown, plateau4par4.getPlateau());
		
		Case[][] casesAfterMoveLeft = methode.executeMove(Movment.LEFT);
		assertArrayEquals(casesAfterMoveLeft, plateau4par4.getPlateau());
		
		Case[][] casesAfterMoveRight = methode.executeMove(Movment.RIGHT);
		assertArrayEquals(casesAfterMoveRight, plateau4par4.getPlateau());
	}
	
	@Test
	public void test_fusion_cases_plateau() {
		Case[][] casesAfterFusionUp = methode.executeFusion(Movment.UP);
		assertArrayEquals(casesAfterFusionUp, plateau4par4.getPlateau());
		
		Case[][] casesAfterFusionDown = methode.executeFusion(Movment.DOWN);
		assertArrayEquals(casesAfterFusionDown, plateau4par4.getPlateau());
		
		Case[][] casesAfterFusionLeft = methode.executeFusion(Movment.LEFT);
		assertArrayEquals(casesAfterFusionLeft, plateau4par4.getPlateau());
		
		Case[][] casesAfterFusionRight = methode.executeFusion(Movment.RIGHT);
		assertArrayEquals(casesAfterFusionRight, plateau4par4.getPlateau());
	}
	
	@Test
	public void test_plateau_bloque() {
		methode.fillPlateauWith3Cases();
		assertFalse(plateau4par4.isBlocked());
		
		methode.setPlateauAlmostBlocked();
		assertFalse(plateau4par4.isBlocked());
		
		methode.setPlateauBlocked();
		assertTrue(plateau4par4.isBlocked());
	}
	
	@Test
	public void test_gagner_partie() {
		methode.fillPlateauWith3Cases();
		assertFalse(plateau4par4.isWin());
		
		methode.setTheGameWin();
		assertTrue(plateau4par4.isWin());
	}
}