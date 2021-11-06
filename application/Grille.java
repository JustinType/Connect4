package application;

import java.io.Serializable;
import java.util.*;
import java.text.*;

/**
 * La classe Grille permet de représenter une grille
 * 
 * @author Valentin Thirion - Tristan Belmont
 */
public class Grille implements Serializable {

	/**
	 * Attribut liste représente toutes les colonnes contenus dans la grille
	 */
	private ArrayList<Colonne> liste;
	
	/**
	 * Attribut largeur représente la largeur de la grille
	 */
	private int largeur;
	
	
	/**
	 * Constructeur qui créer une grille d'au moins 4 colonnes ou de
	 * la largeur indiquée
	 * 
	 * @param l largeur indiquée 
	 * 
	 * @throws Exception
	 */
	public Grille(int l) throws Exception {
		int i = 0;
		this.liste = new ArrayList<Colonne>();
		if (l < 4) {
			this.largeur = 4;
		} else {
			this.largeur = l;
		}
		
		while (i < this.largeur) {
			this.liste.add(new Colonne(i));
			i++;
		}
	}
	
	
	/**
	 * Méthode qui retourne le plus grand nombre de jetons contenus
	 * dans une des colonnes de la grille
	 * 
	 * @return max
	 */
	public int retourneMax() {
		int max = 0;
		for (int i = 0 ; i < this.largeur ; i++) {
			int sum = this.liste.get(i).getTaille();
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}
	
	
	/**
	 * Méthode qui retourne une copie de la liste de la grille
	 * triée par remplissage et par ordre
	 * 
	 * @return liste
	 * 
	 * @throws Exception
	 */
	public ArrayList<Colonne> trierListe() throws Exception {
		// Copie de la liste 
		Grille g = new Grille(this.getNbColonnes());
		Jeton jet = new Jeton(0,1);
		int nbJetons;
		for (int i = 0 ; i < g.getNbColonnes() ; i++) {
			nbJetons = this.getColonne(i).getTaille();
			for (int j = 0 ; j < nbJetons ; j++) {
				g.getColonne(i).ajoutJeton(jet);
			}
		}
		// Tri de la liste copiée
		Collections.sort(g.liste, new triDesColonnes());
		return g.liste;
	}
	
	/**
	 * La classe triDesColonnes implémente l'interface Comparator
	 * Elle permet de trier la liste de la grille
	 * 
	 */
	class triDesColonnes implements Comparator<Colonne> { 
	    public int compare(Colonne c1, Colonne c2) { 
	        return c2.getTaille() - c1.getTaille(); 
	    } 
	} 

	
	/**
	 * Méthode qui retourne une chaîne de caractères correspondant
	 * au tri de la liste de la grille
	 * 
	 * @return s
	 * 
	 * @throws Exception
	 */
	public String afficherTri() throws Exception {
		String s = "N° colonnes triées par remplissage : ";
		ArrayList<Colonne> l = this.trierListe();
		for (int i = 0 ; i < this.getNbColonnes() ; i++) {
			s += l.get(i).getNumColonne() + "(" + l.get(i).getTaille() + ") ";
		}	
		return s;
	}
	
	
	/**
	 * Méthode qui retourne une chaîne de caractères correspondant
	 * au remplissage moyen de la grille
	 * 
	 * @return s
	 * 
	 * @throws Exception
	 */
	public String afficherRemplissageMoyen() throws Exception {
		double nbCol = this.getNbColonnes();
		double nbJetons = 0;
		for (int i = 0 ; i < this.getNbColonnes() ; i++) {
			nbJetons += this.getColonne(i).getTaille();
		}
		double remplissageMoyen = nbJetons/nbCol;
		NumberFormat nf = new DecimalFormat("#.##");
		String s = "Remplissage moyen : " + nf.format(remplissageMoyen);
		return s;
	}
	
	
	/**
	 * Méthode qui ajoute un jeton dans la colonne indiquée
	 * si la colonne existe, sinon affiche un message d'erreur
	 * 
	 * @param numColonne numéro de la colonne indiquée
	 * @param numJoueur numéro du joueur indiqué
	 * 
	 * @throws Exception
	 */
	public void ajouterJeton(int numColonne, int numJoueur) throws Exception {
		if (this.getColonne(numColonne) != null) {
			Jeton j = new Jeton(numColonne, numJoueur);
			this.getColonne(numColonne).ajoutJeton(j);
		} else {
			System.out.println("Cette colonne n'existe pas !");
		}
	}
	
	
	/**
	 * Méthode qui renvoie un booléen true si 4 jetons ou plus sont alignés
	 * verticalement, horizontalement ou en diagonale dans la grille
	 * Renvoie false autrement
	 * 
	 * @return aligne
	 * 
	 * @throws Exception
	 */
	public boolean aligner() throws Exception {
		int nb;
		boolean aligne = false;
		// Parcours de toutes les cases
		for (int i = 0 ; i < this.getNbColonnes() ; i++) {
			for (int j = 0 ; j < this.retourneMax() ; j++) {
				
				nb = 1;
				// Si la case contient un jeton alors on commence les tests
				if (this.getColonne(i).dejaJeton(j)) {
					
					// Récupère le numéro du joueur du jeton actuel
					int num = this.getColonne(i).getJeton(j).getNumJoueur();
					
					// Début des tests
					
					// Test alignement verticale
					if (j+3 < this.retourneMax()) {
						for (int o = 1 ; o < 4 ; o++) {
							if (this.getColonne(i).dejaJeton(j+o) && this.getColonne(i).getJeton(j+o).getNumJoueur() == num) {
								nb++;
							}
							if (nb == 4) {
								aligne = true;
							}
						}
					}
				
					nb = 1;
					
					// Test diagonale gauche
					if (i-3 >= 0 && j+3 <= this.retourneMax()) {
						for (int o = 1 ; o < 4  ; o++) {
								if (this.getColonne(i-o).dejaJeton(j+o) && this.getColonne(i-o).getJeton(j+o).getNumJoueur() == num) {
									nb++;
								}
								if (nb == 4) {
									aligne = true;
								}
						}
					}
					
					nb = 1;
					
					// Test diagonale droite
					if (i+3 < this.getNbColonnes() && j+3 < this.retourneMax()) {
						for (int o = 1 ; o < 4  ; o++) {
								if (this.getColonne(i+o).dejaJeton(j+o) && this.getColonne(i+o).getJeton(j+o).getNumJoueur() == num) {
									nb++;
								}
								if (nb == 4) {
									aligne = true;
								}
						}
					}
					
					nb = 1;
					
					// Test alignement horizontale (par la gauche)
					if (i-3 >= 0) {
						for (int o = 1 ; o < 4  ; o++) {
								if (this.getColonne(i-o).dejaJeton(j) && this.getColonne(i-o).getJeton(j).getNumJoueur() == num) {
									nb++;
								}
								if (nb == 4) {
									aligne = true;
								}
						}
					}
					
				}
					
			}
		}
		
		// affiche "gagné" si 4 jetons ou plus sont alignés
		if (aligne) {
			System.out.println("*****GAGNÉ !!!*****\n\n");
		}
		
		return aligne;
	}
	
	
	/**
	 * Méthode qui renvoie une chaîne de caractères représentant
	 * l'état de la grille
	 * 
	 * @return s
	 */
	public String toString() {
		String s = "";
		for (int j = 0 ; j < this.retourneMax() ; j++ ) {
			for (int i = this.liste.size()-1 ; i >= 0 ; i--) {
				try {
					if (this.getColonne(i).dejaJeton(j)) {
						if (this.getColonne(i).getJeton(j).getNumJoueur() == 1) {
							s = "[X]" + s;
						} else {
							s = "[O]" + s;
						}
					} else {
						s = "[ ]" + s;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			s = "\n" + s;
		}
		return s;
	}
	
	
	/**
	 * Getter qui renvoit la colonne indiquée si son numéro existe
	 * sinon renvoie null
	 * 
	 * @param num numéro de la colonne indiquée
	 * 
	 * @return Colonne ou null
	 */
	public Colonne getColonne(int num) {
		if (num >= 0 && num <= this.liste.size()) {
			return this.liste.get(num);
		} else {
			return null;
		}
	}
	
	/**
	 * Getter qui renvoit le nombre de colonnes dans la grille
	 * 
	 * @return nbColonnes
	 */
	public int getNbColonnes() {
		return this.liste.size();
	}
}