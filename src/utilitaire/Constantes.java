package utilitaire;

import java.awt.Dimension;

/**
 * Classe contenant toutes les constantes nécessaires au programme.
 * 
 * @author Niko Girardelli
 * @version (Copyright A2017)
 */
public class Constantes {
	
	/***************************
     * * Les constantes.
     * **************************/
	
	// Référence au fichier de sauvegarde de la clinique.
	public static final String CHEMIN_FICHIER = "src/data/clinique.bin";
	
	// Dimension pour les TextFields pour un participant.
	public static final Dimension DIMENSION_TEXTFIELD = new Dimension(200,24);
	
	// Tableau avec les noms des items pour la barre de menu.
	public static final String[] TAB_ITEMS_MENU = 
		{"Docteur", "Infirmier", "Patient" , "Quitter"};
	
	// Message lorsqu'on n'a pas rempli un champ de saisie pour l'id.
	public static final String MSG_CHAMP_ID_VIDE = 
			"Les champs pour l'identifiant ne doivent pas être vides.";
	
	// Message lorsqu'on n'a pas rempli le champ de saisie pour le NAS.
	public static final String MSG_CHAMP_NAS_VIDE = 
			"Le champ pour le NAS ne doit pas être vide.";
	
	// Message lorsqu'on n'a pas choisi un département 
	// pour le docteur à ajouter.
	public static final String MSG_SELECT_DEP_VIDE = 
			"Le département n'a pas été sélectionné.";
	
	// Message lorsqu'aucune sélection est faite lors de la suppresion.
	public static final String MSG_SELECT_AUCUNE = 
	"Il faut choisir quelqu'un de la liste afin de procédé "
	+ "à la suppression d'un participant.";
	
	// Message lorsqu'un participant fait déjà partie de la liste.
	public static final String MSG_PAT_DEJA_PRESENT = 
	"Le participant fait déjà partie de la liste.";
	
}
