package application;

import java.io.Serializable;

/**
 * La classe abstraite Joueur représente un joueur du Puissance 4
 * 
 * @author Valentin Thirion - Tristan Belmont
 */
public abstract class Joueur implements Serializable {
	
	/**
	 * Attribut numJoueur représente le numéro du joueur
	 */
	public int numJoueur;
	
	/**
	 * Constructeur qui créer un joueur à partir du numéro indiqué
	 * 
	 * @param numJ numéro du Joueur indiqué
	 */
	public Joueur(int numJ) {
		this.numJoueur = numJ;
	}
	
	/**
	 * Méthode qui permet de placer un jeton dans une grille donnée
	 * Cette méthode est différente en fonction du type du joueur
	 * 
	 *  @param g Grille indiquée
	 * 
	 * @throws Exception
	 */
	public abstract void placerJeton(Grille g) throws Exception;
}
