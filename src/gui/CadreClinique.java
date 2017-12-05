package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import clinique.Clinique;
import clinique.Participant;
import utilitaire.UtilitaireFichier;

/**
 * Classe qui montre le cadre principal de l'application de 
 * clinique m�dicale.
 * 
 * Dans le cadre du cours inf111, travail pratique num�ro 3
 * (voir �nonc� INF111 A17 tp3 partie 2 fourni).
 * 
 * @author Pierre B�lisle
 * @version Copyright A2017
 */
public class CadreClinique extends JFrame implements Runnable{

	/**
	 * CONSTANTES 
	 */
	// La taille du cadre a �t� choisie arbitrairement.
	public static final int LARGEUR = 1000;
	public static final int HAUTEUR = 400;

	// Pour fixer la taille, il faut une instance de la classe Dimension.
	public static final Dimension TAILLE_CADRE = 
															new Dimension(LARGEUR,HAUTEUR);

	// Pour centrer le cadre, on obtient la taille de l'�cran par le Toolkit.
	public static final Dimension TAILLE_ECRAN = 
															Toolkit.getDefaultToolkit().getScreenSize();	

	// La taille du cadre pour la gestion des participants.
	public static final int LARGEUR_CADRE_GESTION = 500;
	public static final int HAUTEUR_CADRE_GESTION = 400;

	// Le centre � partir de la taille du cadre.
	public final int CENTRE_X = 
			(TAILLE_ECRAN.width - LARGEUR_CADRE_GESTION) / 2;
	
	public final int CENTRE_Y = 
			(TAILLE_ECRAN.height -HAUTEUR_CADRE_GESTION) / 2;
	
	// On calcule la position au centre.
	private final Point POINT = new Point(CENTRE_X, CENTRE_Y);
	
	private final Dimension DIMENSION_CADRE = 
			new Dimension(LARGEUR_CADRE_GESTION, 
									HAUTEUR_CADRE_GESTION);

	// La seule clinique de rendez-vous de l'application 
	// sera instanci�e au constructeur.
	private Clinique clinique;
	
	

	/**
	 *	On tente d'obtenir le fichier clinique par d�faut (Clinique.NOM_FIC).
	 *  S'il n'existe pas, on instancie une clinique neuve.   
	 *  
	 *  Pour l'instant, il n'y a qu'une seule clinique dans tout le projet. 
	 */
	public CadreClinique(){
		
		try{
			
			File fic = new File(Clinique.NOM_FIC);
			
			clinique = 
					UtilitaireFichier.obtenirClinique(
							new FileInputStream (fic));
		}
		catch(Exception e){
			clinique = new Clinique();
		}
	}
	
	/**
	 * Initialise les composants, et la configuration
	 * de base du cadre au monent d�cid� par Thread.start().
	 */
	public void run(){

		// On obtient le panneau du JFrame.
		JPanel panneau = (JPanel) getContentPane();
		
		// Dimension plein �cran du  CadreClinique (this).
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// On ferme sur X.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On ajoute le panneau pour les date.
		PanneauSelecteurDate panneauSelecteurDate = 
				new PanneauSelecteurDate("Rendez-vous clinique", clinique);

		// Le menu que les �tudiants doivent �crire.
		MonMenuBar menuBar = new MonMenuBar(this);
		setJMenuBar(menuBar);
		
		// La grille qui pr�sente le calendrier (dateChooser).
		panneau.add(panneauSelecteurDate);
		
		//On montre la fen�tre
		setVisible(true);		
	}

	/**
	 * Permet de d�marrer la fen�tre de dialogue pour la
	 * gestion des docteurs.
	 */
	public void gererDocteur() {

				// Instanciation liste Docteur
				List<Participant> listeDocteurs = clinique.getDocteurs();
				
				// V�rifie s'il y a eu aucune sauvegarde derni�rement.
				if(listeDocteurs.size() == 0){

			         listeDocteurs = UtilitaireFichier.getParticipantsFichierTexte("noms.txt", "docteur");

			         clinique.setDocteurs(listeDocteurs);

				}
		
				// Les param�tres sont sur plusieurs lignes.
				new CadreGestionParticipant(clinique,
				
								// Le panneau qui saisit les infos d'un docteur.
								new PanneauSaisieDocteur(clinique.getDepartements()),
						
								// La liste des docteurs.
								listeDocteurs,
								
				                // La position et la dimension.
								POINT,
								DIMENSION_CADRE);
				
		
	}
	
	/**
	 * Permet de d�marrer la fen�tre de dialogue pour la
	 * gestion des informiers.
	 */
	public void gererInfirmier() {
		
				// Instanciation liste Infirmier
				List<Participant> listeInfirmiers = clinique.getInfirmiers();
				
				// V�rifie s'il y a eu aucune sauvegarde derni�rement.
				if(listeInfirmiers.size() == 0){
		
					listeInfirmiers = UtilitaireFichier.getParticipantsFichierTexte("noms.txt", "infirmier");
		
			         clinique.setInfirmiers(listeInfirmiers);
		
				}
				
				// Les param�tres sont sur plusieurs lignes. 
				new CadreGestionParticipant(clinique,
				
						// Le panneau qui saisit les infos d'un infirmier.
						new PanneauSaisieInfirmier(),
						
						// La liste des infirmiers.
						listeInfirmiers,
						
		                // La position et la dimension.
						POINT,
						DIMENSION_CADRE);
						
		
	}
	
	/**
	 * Permet de d�marrer la fen�tre de dialogue pour la
	 * gestion des patients.
	 */
	public void gererPatient() {
		
				// Instanciation liste Patients
				List<Participant> listePatients = clinique.getPatients();
				
				// V�rifie s'il y a eu aucune sauvegarde derni�rement.
				if(listePatients.size() == 0){
		
					listePatients = UtilitaireFichier.getParticipantsFichierTexte("noms.txt", "patient");
		
			         clinique.setPatients(listePatients);
		
				}

				// Les param�tres sont sur plusieurs lignes. 
				new CadreGestionParticipant(clinique,
						
					    // Le panneau qui saisit les infos d'un patient.
						new PanneauSaisiePatient(),
		
						// La liste de patients.
						listePatients,
						
		                // La position et la dimension.
						POINT,
						DIMENSION_CADRE);
				
	}
}