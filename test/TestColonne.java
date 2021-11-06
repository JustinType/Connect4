package test;

import application.*;
import static libtest.Lanceur.*;
import static libtest.OutilTest.*;

import java.util.ArrayList;

/**
 * Test de la classe Colonne
 */

public class TestColonne {

	/**
	 * Vérifie que le constructeur de la classe colonne et que la méthode ajoutJeton
	 * marchent correctement grâce à la méthoge getTaille
	 *
	 * @throws Exception
	 */
	public void test_Constructeur_By_Ajout() throws Exception {
		
		// preparation des donnees
		Colonne c = new Colonne(1);
		
		// methode testee
		int taille = c.getTaille();

		// tests
		assertEquals("Mauvaise taille", 0, taille);
		assertEquals("Mauvaise colonne", 1, c.getNumColonne());
		
		// preparation des donnees
		Jeton j = new Jeton(2,1);
		for (int i = 0 ; i < 5 ; i++) {
			c.ajoutJeton(j);
		}
		
		// methode testee
		taille = c.getTaille();

		// test
		assertEquals("Mauvaise taille", 5, taille);
		
		
		// tests des exceptions
		try {
			Colonne c2 = new Colonne(-4567);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	
	
	/**
	 * Vérifie le bon fonctionnement de la methode dejaJeton
	 *
	 * @throws Exception
	 */
	public void test_dejaJeton() throws Exception {
		
		// preparation des donnees
		Colonne c = new Colonne(0);
		Jeton j = new Jeton(2,1);
		for (int i = 0 ; i < 3 ; i++) {
			c.ajoutJeton(j);
		}
		
		// methode testee
		boolean deja = c.dejaJeton(0);

		// test
		assertEquals("La place devrait être prise", true, deja);
		
		
		
		// preparation des donnees
		c.ajoutJeton(null);

		// methodes testee
		deja = c.dejaJeton(3);
		
		// test
		assertEquals("La place devrait être libre", false, deja);
		
		
		// test de l'exception
		try {
			deja = c.dejaJeton(-56);
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
		lancer(new TestColonne(), args);
	}

}
