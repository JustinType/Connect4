package application;

import java.util.*;
import java.io.*;

/**
 * La classe Jeu permet de représenter un jeu de Puissance 4 et de le lancer
 * 
 * @author Valentin Thirion - Tristan Belmont
 */
public class Jeu {

	/**
	 * Attribut grille représente la grille du Puissance 4
	 */
	private Grille grille;
	
	/**
	 * Attribut j1 représente le joueur numéro 1
	 */
	private Joueur j1;
	
	/**
	 * Attribut j2 représente le joueur numéro 2
	 */
	private Joueur j2;
	
	
	/**
	 * Constructeur de base qui créer une grille de 4 colonnes vides
	 * 
	 * @throws Exception
	 */
	public Jeu() throws Exception {
		this.grille = new Grille(4);
	}
	
	
	/**
	 * Constructeur qui créer un Puissance 4 d'au moins 4 colonnes ou de
	 * la largeur indiquée et qui initialise le mode de jeu du Puissance 4
	 * 
	 * @param l largeur indiquée 
	 * 
	 * @throws InputMismatchException
	 * @throws Exception
	 */
	public Jeu(int l) throws InputMismatchException, Exception {
		this.grille = new Grille(l);
		this.setMode();
	}
	
	
	/**
	 * Méthode qui initialise le mode de jeu du Puissance 4 
	 * en fonction des réponses de l'utilisateur
	 * 
	 * @throws InputMismatchException
	 * @throws Exception
	 */
	public void setMode() throws InputMismatchException, Exception {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("\n***Quel mode de jeu choisissez-vous ?*** \n");
			System.out.println("Tapez 1 pour joueur VS joueur \n ");
			System.out.println("Tapez 2 pour joueur VS ordinateur \n ");
			System.out.println("Tapez 3 pour ordinateur VS ordinateur \n ");
			int mode = sc.nextInt();
			while (mode < 1 || mode > 3) {
				System.out.println("Vous devez choisir un entier entre 1 et 3 :\n");
				mode = sc.nextInt();
			}
			if (mode == 1) {
				this.j1 = new Humain(1);
				this.j2 = new Humain(2);
			} else if (mode == 2) {
				this.j1 = new Humain(1);
				this.j2 = new Ordinateur(2);
			} else {
				this.j1 = new Ordinateur(1);
				this.j2 = new Ordinateur(2);
			}
		} catch (InputMismatchException e) {
			System.out.println("Vous devez entrer un nombre entier entre 1 et 3 !\n\n");
			this.setMode();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

		
	/**
	 * Méthode qui permet de sauvegarder une partie dans un fichier binaire
	 * avec le nom donné
	 * 
	 * @param nom nom donné au fichier de sauvegarde
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	public void sauve(String nom) throws IOException, Exception {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nom));
			oos.writeObject(this.getGrille());
			oos.writeObject(this.getJoueur1());
			oos.writeObject(this.getJoueur2());
			oos.close();
		} catch (IOException e) {
			System.out.println("Erreur d’entrées ou de sorties\n");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Méthode qui permet de charger une partie sauvegardée
	 * 
	 * @param nom nom du fichier de sauvegarde à charger
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	public void charge(String nom) throws FileNotFoundException, IOException, Exception {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nom));
			Grille g = (Grille)(ois.readObject());
			Joueur j1 = (Joueur)(ois.readObject());
			Joueur j2 = (Joueur)(ois.readObject());
			ois.close();
			this.setGrille(g);
			this.setJoueur1(j1);
			this.setJoueur2(j2);
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé\n");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erreur d’entrées ou de sorties\n");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Méthode qui renvoie un booléen true si l'utilisateur veut arrêter
	 * la partie, sinon renvoie false
	 * 
	 * @return fini réponse de l'utilisateur
	 * 
	 * @throws InputMismatchException
	 * @throws Exception
	 */
	public boolean demanderArret() throws InputMismatchException, Exception {
		boolean fini = false;
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("\n***Voulez-vous arrêter la partie ?***\n");
			System.out.println("1 = OUI  /  2 = NON : \n");
			int choix = sc.nextInt();
			while (choix != 1 && choix != 2) {
				System.out.println("\n1 = OUI  /  2 = NON : \n");
				choix = sc.nextInt();
			}
			if (choix == 1) {
				fini = true;
			}		
		} catch (InputMismatchException	e) {
			System.out.println("Vous devez entrer un nombre entier !\n");
			this.demanderArret();
		} catch (Exception e) {
			System.out.println(e);
		}
		return fini;
	}
	
	
	/**
	 * Méthode qui demande à l'utilisateur s'il veut sauvegarder la partie
	 * En fonction de sa réponse, appelle ou non la méthode sauve
	 * 
	 * @throws InputMismatchException
	 * @throws Exception
	 */
	public void demanderSauvegarde() throws InputMismatchException, Exception {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("***Voulez-vous sauvegarder la partie ?*** \n");
			System.out.println("1 = OUI  /  2 = NON : \n");
			int sauv = sc.nextInt();
			while (sauv != 1 && sauv != 2) {
				System.out.println("\n1 = OUI  /  2 = NON : \n");
				sauv = sc.nextInt();
			}
			if (sauv == 1) {
				Scanner sc2 = new Scanner(System.in);
				System.out.println("Nom de la partie à sauvegarder : \n");
				String s = sc2.nextLine();
				this.sauve(s);
			}		
		} catch (InputMismatchException	e) {
			System.out.println("Vous devez entrer un nombre entier !\n");
			this.demanderArret();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * Getter qui renvoit la grille du Puissance 4
	 * 
	 * @return grille
	 */
	public Grille getGrille() {
		return this.grille;
	}
	
	/**
	 * Setter qui remplace la grille du puissance 4 par celle indiquée
	 * 
	 * @param g grille indiquée
	 */
	public void setGrille(Grille g) {
		this.grille = g;
	}
	
	/**
	 * Getter qui renvoit le joueur numéro 1
	 * 
	 * @return j1
	 */
	public Joueur getJoueur1() {
		return this.j1;
	}
	
	/**
	 * Setter qui remplace le joueur numéro 1 par celui indiqué
	 * 
	 * @param j joueur indiqué
	 */
	public void setJoueur1(Joueur j) {
		this.j1 = j;
	}
	
	/**
	 * Getter qui renvoit le joueur numéro 2
	 * 
	 * @return j2
	 */
	public Joueur getJoueur2() {
		return this.j2;
	}
	
	/**
	 * Setter qui remplace le joueur numéro 2 par celui indiqué
	 * 
	 * @param 2 joueur indiqué
	 */
	public void setJoueur2(Joueur j) {
		this.j2 = j;
	}
	
	
	
	
	
	/**
	 * Méthode main qui lance une partie de Puissance 4
	 * 
	 * @param args toutes les réponses de l'utilisateur
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		try {
			// Initialisation d'un jeu :
			Jeu j;
			Scanner sc = new Scanner(System.in);
			System.out.println("*****PUISSANCE 4***** \n\n\n");
			System.out.println("Voulez-vous charger une partie déjà existante ? : \n");
			System.out.println("1 = OUI  /  2 = NON : \n");
			int choix = sc.nextInt();
			while (choix != 1 && choix != 2) {
				System.out.println("\n1 = OUI  /  2 = NON : \n");
				choix = sc.nextInt();
			}
			if (choix == 1) {
				Scanner sc2 = new Scanner(System.in);
				System.out.println("Nom de la partie à reprendre ? :");
				j = new Jeu();
				String s = sc2.nextLine();
			    j.charge(s);
			    System.out.println("\n***Etat de la partie reprise :*** ");
				s = j.getGrille().toString();
				System.out.println(s);
			} else {
				System.out.println("\nNombre de colonnes de la nouvelle partie ? : \n");
				choix = sc.nextInt();
				j = new Jeu(choix);
			}

			// Déroulement du jeu :
			boolean fini = false;
			boolean gagner = false;
			System.out.println("\n\n*****ATTENTION*****");
			System.out.println("\nIl est possible que le message d'erreur : 'Cette colonne n'existe pas !' s'affiche, si c'est le cas ignorez le\n\n");
			while (!fini && !gagner) {
				j.getJoueur1().placerJeton(j.getGrille());
				gagner = j.getGrille().aligner();
				j.getJoueur2().placerJeton(j.getGrille());
				System.out.println(j.getGrille().afficherTri());
				System.out.println(j.getGrille().afficherRemplissageMoyen());
				gagner = j.getGrille().aligner();
				if (!gagner) {
					fini = j.demanderArret();
				}
			}
			j.demanderSauvegarde();
			System.out.println("\n*****FIN DU JEU***** ");
			
		} catch (InputMismatchException	e) {
			System.out.println("Vous devez entrer un nombre entier !\n");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
