package test;

import application.*;
import static libtest.Lanceur.*;
import static libtest.OutilTest.*;

import java.util.ArrayList;

/**
 * Test de la classe Ordinateur
 */

public class TestOrdinateur {

	/**
	 * Vérifie que la classe Ordinateur marche correctement
	 *
	 * @throws Exception
	 */
	public void test_placerJeton() throws Exception {
		
		// preparation des donnees
		Ordinateur o = new Ordinateur(1);
		Grille g = new Grille(4);

		
		// methodes testee
		o.placerJeton(g);
		boolean jetonPlace = false;
		int i = 0;
		while (i < 4) {
			if (g.getColonne(i).dejaJeton(0)) {
				jetonPlace = true;
			}
			i++;
		}

		// test
		assertEquals("L'ordinateur ne fait rien", true, jetonPlace);
	}
		

	// Impossible de tester un humain car on utilise un scanner !
	// Vu que l'ordinateur joue aléatoirement on peut juste tester
	// qu'il place bien un jeton quelque part
	// Il suffit de lancer plusieurs fois le test pour voir que
	// l'ordinateur ne place pas toujours le jeton au même endroit
	

	/**
	 * Méthode de lancement des tests
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		lancer(new TestOrdinateur(), args);
	}

}

