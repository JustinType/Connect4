package application;

import java.util.*;

/**
 * La classe Humain représente un joueur du Puissance 4 de type humain
 * Elle hérite de la classe Joueur
 * 
 * @author Valentin Thirion - Tristan Belmont
 */
public class Humain extends Joueur {

	/**
	 * Constructeur qui créer un joueur de type humain 
	 * à partir du constructeur de la classe joueur
	 * 
	 * @param numJ numéro du Joueur indiqué
	 */
	public Humain(int numJ) {
		super(numJ);
	}
		
	
	/**
	 * Méthode qui permet de placer un jeton dans une grille donnée
	 * à partir des réponses de l'utilisateur
	 * 
	 * @param g Grille indiquée
	 * 
	 * @throws InputMismatchException
	 * @throws Exception
	 */
	public void placerJeton(Grille g) throws Exception, InputMismatchException {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("\n***Tour du Joueur " + this.numJoueur + "***");
			System.out.println("\nNuméro de la colonne : ");
			int numCol = sc.nextInt();
			while (numCol <= 0 || numCol > g.getNbColonnes()) {
				System.out.println("Cette colonne n'existe pas ! \n");
				System.out.println("Numéro de la colonne : ");
				numCol = sc.nextInt();
			}
			g.ajouterJeton(numCol-1, numJoueur);
			String s = g.toString();
			System.out.println("Coup réalisé :\n");
			System.out.println(s);
		} catch (InputMismatchException	e) {
			System.out.println("Vous devez entrer un nombre entier !\n");
			this.placerJeton(g);
		}
	}
}
