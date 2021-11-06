package test;

import application.*;
import static libtest.Lanceur.*;
import static libtest.OutilTest.*;

import java.util.ArrayList;

/**
 * Test de la classe Grille
 */

public class TestGrille {
	
	
	/**
	 * Vérifie le bon fonctionnement du constructeur
	 *
	 * @throws Exception
	 */
	public void test_Constructeur() {
		try {
			// preparation des donnees
			Grille g = new Grille(7);
			
			// methode testee
			int nb = g.getNbColonnes();
	
			// test
			assertEquals("Il devrait y avoir 7 colonnes", 7, nb);
			
			
			// preparation des donnees
			Grille g2 = new Grille(0);
			
			// methode testee
			nb = g2.getNbColonnes();
	
			// test
			assertEquals("Il devrait y avoir 4 colonnes", 4, nb);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	
	
	
	/**
	 * Vérifie le bon fonctionnement de la méthode retourneMax
	 *
	 * @throws Exception
	 */
	public void test_retourneMax() throws Exception {
		
		// preparation des donnees
		Grille g = new Grille(2);
		
		// methodes testee
		int max = g.retourneMax();

		// tests
		assertEquals("Le résultat devrait être 0", 0, max);
		
		
		// preparation des donnees
		Grille g2 = new Grille(7);
		g2.ajouterJeton(2, 1);
		for (int i = 0 ; i < 3 ; i++) {
			g2.ajouterJeton(5, 2);
		}
		
		// methodes testee
		max = g2.retourneMax();

		// tests
		assertEquals("Le résultat devrait être 3", 3, max);

	}
	
	
	
	/**
	 * Vérifie le bon fonctionnement de la méthode ajouterJeton
	 *
	 * @throws Exception
	 */
	public void test_ajouterJeton() throws Exception {
		
		// preparation des donnees
		Grille g = new Grille(2);
		
		// methodes testee
		int max = g.retourneMax();

		// tests
		assertEquals("Le résultat devrait être 0", 0, max);
		
		
		// preparation des donnees
		Grille g2 = new Grille(7);
		g2.ajouterJeton(2, 1);
		for (int i = 0 ; i < 3 ; i++) {
			g2.ajouterJeton(5, 2);
		}
		
		// methodes testee
		max = g2.retourneMax();

		// tests
		assertEquals("Le résultat devrait être 3", 3, max);
		
		// test de l'exception
		try {
			g2.ajouterJeton(-345, 2);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			g2.ajouterJeton(2, 678);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	
	/**
	 * Vérifie la détection de la victoire
	 *
	 * @throws Exception
	 */
	public void test_aligner() throws Exception {
		
		// preparation des donnees
		Grille g1 = new Grille(4);
		g1.ajouterJeton(2, 2);
		g1.ajouterJeton(0, 1);
				
		// methodes testee
		boolean a = g1.aligner();
		

		// test aucune victoire
		assertEquals("Aucune victoire ne devrait être détectée", false, a);
				
				
				
		// preparation des donnees
		for (int i = 0 ; i < 4 ; i++) {
			g1.ajouterJeton(3, 2);
		}
				
		// methodes testee
		a = g1.aligner();
		System.out.println(g1.toString());

		// test victoire verticale
		assertEquals("La victoire devrait être détectée", true, a);
				
		
		
		
		// preparation des donnees
		Grille g = new Grille(4);
		for (int i = 0 ; i < 3 ; i++) {
			g.ajouterJeton(0, 2);
		}
		g.ajouterJeton(1, 2);
		g.ajouterJeton(1, 2);
		g.ajouterJeton(2, 2);
		g.ajouterJeton(0, 1);
		a = g.aligner();
				
		// test aucune victoire
		assertEquals("Aucune victoire ne devrait être détectée", false, a);
				
		g.ajouterJeton(1, 1);
		g.ajouterJeton(2, 1);
		g.ajouterJeton(3, 1);
				
		// methodes testee
		a = g.aligner();
		System.out.println(g.toString());

		// test victoire diagonale gauche
		assertEquals("La victoire devrait être détectée", true, a);
		
		
		// preparation des donnees
		Grille g2 = new Grille(7);
		for (int i = 0 ; i < 3 ; i++) {
			g2.ajouterJeton(4, 1);
		}
		g2.ajouterJeton(3, 1);
		g2.ajouterJeton(3, 1);
		g2.ajouterJeton(2, 1);
		g2.ajouterJeton(4, 2);
		a = g2.aligner();
						
		// test aucune victoire
		assertEquals("Aucune victoire ne devrait être détectée", false, a);
						
		g2.ajouterJeton(3, 2);
		g2.ajouterJeton(2, 2);
		g2.ajouterJeton(1, 2);
						
		// methodes testee
		a = g2.aligner();
		System.out.println(g2.toString());

		// test victoire diagonale droite
		assertEquals("La victoire devrait être détectée", true, a);
		
		
		
		
		// preparation des donnees
		Grille g4 = new Grille(5);
		g4.ajouterJeton(2, 2);
		g4.ajouterJeton(4, 1);
						
		// methodes testee
		a = g4.aligner();
				

		// test aucune victoire
		assertEquals("Aucune victoire ne devrait être détectée", false, a);
						
						
						
		// preparation des donnees
		for (int i = 0 ; i < 4 ; i++) {
			g4.ajouterJeton(i, 2);
		}
						
		// methodes testee
		a = g4.aligner();
		System.out.println(g4.toString());

		// test victoire horizontale
		assertEquals("La victoire devrait être détectée", true, a);

	}
	
	
	
	
	
	/**
	 * Vérifie le bon fonctionnement du tri des colonnes
	 *
	 * @throws Exception
	 */
	public void test_tri() throws Exception {
		// preparation des donnees
		Grille g = new Grille(6);
		g.ajouterJeton(1, 1);
		g.ajouterJeton(1, 2);
		g.ajouterJeton(1, 1);
		g.ajouterJeton(1, 1);
		
		g.ajouterJeton(4, 2);
		g.ajouterJeton(4, 1);
		
		g.ajouterJeton(0, 1);

						
		// methode testee
		ArrayList<Colonne> l = g.trierListe();
				
		// test affichage
		assertEquals("Mauvais tri", 4, l.get(0).getTaille());
		assertEquals("Mauvais tri", 2, l.get(1).getTaille());
		assertEquals("Mauvais tri", 1, l.get(2).getTaille());
		assertEquals("Mauvais tri", 0, l.get(3).getTaille());
		assertEquals("Mauvais tri", 0, l.get(4).getTaille());
		
		
		// preparation des donnees
		String ss = "N° colonnes triées par remplissage : 1(4) 4(2) 0(1) 2(0) 3(0) 5(0) ";
		
		// methode testee
		String str = g.afficherTri();
		System.out.println(str);
		boolean pareil = g.afficherTri().equals(ss);
		
		// test affichage
		assertEquals("Mauvais affichage", true, pareil);
	}
	
	
		
		
	
	/**
	 * Vérifie l'affichage de la grille
	 *
	 * @throws Exception
	 */
	public void test_affichage() throws Exception {
		
		// preparation des donnees
		Grille g = new Grille(4);
		g.ajouterJeton(1, 1);
		String s = new String("\n[ ][X][ ][ ]");
				
		// methodes testee
		boolean pareil = g.toString().equals(s);
		
		// test affichage
		assertEquals("Mauvais affichage", true, pareil);
		
		
		// preparation des donnees
		g.ajouterJeton(2, 2);
		g.ajouterJeton(1, 1);
		s = "\n[ ][X][ ][ ]\n[ ][X][O][ ]";
		
		// methodes testee
		pareil = g.toString().equals(s);

				
		// test affichage
		assertEquals("Mauvais affichage", true, pareil);
	}
	
	
	
	/**
	 * Vérifie la methode remplissageMoyen
	 *
	 * @throws Exception
	 */
	public void test_remplissageMoyen() throws Exception {
		
		// preparation des donnees
		Grille g = new Grille(4);
		g.ajouterJeton(1, 1);
		boolean pareil = false;
				
		// methodes testee
		pareil = g.afficherRemplissageMoyen().equals("Remplissage moyen : 0,25");

		// test affichage
		assertEquals("Mauvais affichage", true, pareil);
		
		
		// preparation des donnees (même grille que dans le sujet)
		Grille g2 = new Grille(7);
		g2.ajouterJeton(1, 1);
		
		g2.ajouterJeton(2, 1);
		g2.ajouterJeton(2, 1);
		g2.ajouterJeton(2, 1);
		
		g2.ajouterJeton(3, 1);
		g2.ajouterJeton(3, 1);
		g2.ajouterJeton(3, 1);
		
		g2.ajouterJeton(4, 1);
		g2.ajouterJeton(4, 1);
		g2.ajouterJeton(4, 1);
		
		g2.ajouterJeton(5, 1);
		
		g2.ajouterJeton(6, 1);		
				
		boolean pareil2 = false;
						
		// methodes testee
		pareil2 = g2.afficherRemplissageMoyen().equals("Remplissage moyen : 1,71");

		// test affichage
		assertEquals("Mauvais affichage", true, pareil2);
		
	}
	
	
	
	
	/**
	 * Méthode de lancement des tests
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		lancer(new TestGrille(), args);
	}

}
