package application;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Colonne permet de représenter une colonne
 * 
 * @author Valentin Thirion - Tristan Belmont
 */
public class Colonne implements Serializable {

	/**
	 * Attribut liste représente tous les jetons contenus dans la colonne
	 */
	private ArrayList<Jeton> liste;
	
	/**
	 * Attribut numColonne représente le numéro de la colonne
	 */
	private int numColonne;
	
	
	/**
	 * Constructeur qui créer une colonne si le numéro de la colonne est valide
	 * 
	 * @param numCol numéro de la colonne indiquée
	 * 
	 * @throws InvalidColonne
	 */
	public Colonne(int num) throws InvalidColonne {
		if (num < 0) {
			throw new InvalidColonne("\nNuméro de colonne du jeton invalide");
		} else {
			this.numColonne = num;
		}
		this.liste = new ArrayList<Jeton>();
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
	 * Méthode qui ajoute un jeton dans la colonne
	 * 
	 * @param j Jeton à ajouter
	 */
	public void ajoutJeton(Jeton j) {
		this.liste.add(j);
	}
	
	
	/**
	 * Méthode qui renvoie un booléen true s'il y a un jeton
	 * à la place indiquée, renvoie false sinon
	 * 
	 * @param place place indiquée
	 * 
	 * @return deja
	 * 
	 * @throws InvalidPlace si la place indiquée est négative
	 */
	public boolean dejaJeton(int place) throws InvalidPlace {
		boolean deja = false;
		if (place < 0) {
			throw new InvalidPlace("Place invalide");
		} else if (this.getTaille() > place && this.liste.get(place) != null) {
			deja = true;
		}
		return deja; 
	}
	
	/**
	 * La classe InvalidPlace hérite de la classe Exception
	 * Elle représente une place invalide
	 * 
	 */
	class InvalidPlace extends Exception {
	    public InvalidPlace (String s) {  
	        super(s);  
	    }
	} 
	
	
	/**
	 * Getter qui renvoit la taille de la colonne
	 * 
	 * @return taille
	 */
	public int getTaille() {
		return this.liste.size();
	}
	
	/**
	 * Getter qui renvoit le numéro de la colonne
	 * 
	 * @return numColonne
	 */
	public int getNumColonne() {
		return this.numColonne;
	}
	
	/**
	 * Getter qui renvoit le jeton à la place indiquée
	 * 
	 * @param i place indiquée
	 * 
	 * @return jeton
	 */
	public Jeton getJeton(int i) {
		return this.liste.get(i);
	}
}
