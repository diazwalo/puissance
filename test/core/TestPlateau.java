package core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestPlateau {
	private Plateau plateau4par4 = new Plateau();
	private Plateau plateau8par8 = new Plateau(8);
	private Plateau plateau2par2False = new Plateau(2);

	@Before
	public void before() {
		plateau4par4 = new Plateau();
	}

	@Test
	public void testCreation() {
		// plateau4par4 est initialisé a 4 de longueur si on ne donne pas de taille
		assertTrue(plateau4par4.getPlateau().length == 4);
		assertTrue(plateau4par4.getPlateau()[0].length == 4);
		
		assertTrue(plateau8par8.getPlateau().length == 8);
		assertTrue(plateau8par8.getPlateau()[0].length == 8);
		
		// plateau2par2 sera initialisé à 4 de longueur car c'est le minimum
		assertTrue(plateau2par2False.getPlateau().length == 4);
		assertTrue(plateau2par2False.getPlateau()[0].length == 4);
	}

	@Test
	public void testFillPlateauWithZero() {
		this.plateau4par4.fillPlateauWithZero();
		for (Case[] idx : this.plateau4par4.getPlateau()) {
			for (Case cellule : idx) {
				assertEquals(cellule.getContent(), new CaseContent(0));
			}
		}
	}

	@Test
	public void testGenerateRandomCase() {
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
}
