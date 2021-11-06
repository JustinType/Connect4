package application;

import java.io.Serializable;

/**
 * La classe Jeton permet de représenter un jeton
 * 
 * @author Valentin Thirion - Tristan Belmont
 */
public class Jeton implements Serializable {
	/**
	 * Attribut numColonne représente le numéro de la colonne du Jeton
	 */
	private int numColonne;
	
	/**
	 * Attribut numJoueur représente le numéro du joueur du Jeton
	 */
	private int numJoueur;
	
	
	/**
	 * Constructeur qui créer un jeton si le numéro de la colonne
	 * et du joueur sont valides
	 * 
	 * @param numCol numéro de la colonne indiquée
	 * @param numJ numéro du joueur indiqué
	 * 
	 * @throws InvalidColonne
	 * @throws InvalidJoueur
	 */
	public Jeton(int numCol, int numJ) throws InvalidColonne, InvalidJoueur {
		if (numCol < 0) {
			throw new InvalidColonne("\nNuméro de colonne du jeton invalide");
		} else {
			this.numColonne = numCol;
		}
		if (numJ != 1 && numJ != 2) {
			throw new InvalidJoueur("\nNuméro du joueur invalide");
		} else {
			this.numJoueur = numJ;
		}
	}
	
	/**
	 * La classe InvalidColonne hérite de la classe Exception
	 * Elle représente une colonne invalide
	 * 
	 */
	class InvalidColonne extends Exception {
	    public InvalidColonne (String s) {  
	        super(s);  
	    }
	} 
	
	/**
	 * La classe InvalidJoueur hérite de la classe Exception
	 * Elle représente un joueur invalide
	 * 
	 */
	class InvalidJoueur extends Exception {
	    public InvalidJoueur (String s) {  
	        super(s);  
	    }
	}
	 
	
	/**
	 * Getter qui renvoit le numéro de la colonne du jeton
	 * 
	 * @return numColonne
	 */
	public int getNumColonne() {
		return numColonne;
	}

	
	/**
	 * Getter qui renvoit le numéro du joueur du jeton
	 * 
	 * @return numJoueur
	 */
	public int getNumJoueur() {
		return numJoueur;
	}
}