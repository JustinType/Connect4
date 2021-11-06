package application;

/**
 * La classe Ordinateur représente un joueur du Puissance 4 de type ordinateur
 * Elle hérite de la classe Joueur
 * 
 * @author Valentin Thirion - Tristan Belmont
 */
public class Ordinateur extends Joueur {
	
	/**
	 * Constructeur qui créer un joueur de type ordinateur
	 * à partir du constructeur de la classe joueur
	 * 
	 * @param numJ numéro du Joueur indiqué
	 */
	public Ordinateur(int numJ) {
		super(numJ);
	}
	
	
	/**
	 * Méthode qui permet de placer un jeton dans une grille donnée
	 * de façon automatique
	 * 
	 * @param g Grille indiquée
	 * 
	 * @throws Exception
	 */
	public void placerJeton(Grille g) throws Exception {
		System.out.println("\n\n***Tour de l'Ordinateur " + this.numJoueur + "***");
		int numCol = (int)Math.floor(Math.random()*g.getNbColonnes());
		g.ajouterJeton(numCol, numJoueur);
		System.out.println("Coup réalisé :\n");
		System.out.println(g.toString());
	}

}
