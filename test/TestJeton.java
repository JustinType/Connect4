package test;

import application.*;
import static libtest.Lanceur.*;
import static libtest.OutilTest.*;

/**
 * Test de la classe Jeton
 */

public class TestJeton {

	/**
	 * Vérifie que le constructeur d'un jeton marche correctement
	 *
	 * @throws Exception
	 */
	public void test_Constructeur() throws Exception {
		
		// preparation des donnees
		Jeton j = new Jeton(12,1);
		
		// methodes testees
		int numC = j.getNumColonne();
		int numJ = j.getNumJoueur();

		// tests
		assertEquals("Mauvais nombre de colonnes", 12, numC);
		assertEquals("Mauvais joueur", 1, numJ);
		
		
		// tests des exceptions
		try {
			Jeton j2 = new Jeton(-21,1);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			Jeton j3 = new Jeton(4,671);
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	/**
	 * Méthode de lancement des tests
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		lancer(new TestJeton(), args);
	}

}