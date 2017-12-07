package gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import clinique.Docteur;
import clinique.Participant;

/**
 * Panneau de saisie spécifique à un entré de Docteurs.
 * Elle ajoute une liste déroulante ayant les départements
 * valide pour les docteurs exemple : Chirurgie
 * 
 * @author Michaël Dallaire
 * @version 1.0
 *
 */
public class PanneauSaisieDocteur extends PanneauSaisieParticipant {

	/*
	 * CONSTANTES
	 */
	
	public final static String MSG_ERREUR_LISTE = "Département non-sélectionné";
	
	/*
	 * VARIABLE PRIVÉES
	 */
	
    private static final long serialVersionUID = 1L;
    
    //panneau deroulant pour la liste des département
    private JScrollPane listeDeroulante;
    
    //Liste dans laquelle on peut sélectionner un département
    private JList liste;
    
    //tableau contenant les noms des département
    private String[] tableauDepartement;

    /**
     * Constructeur
     * Sauvegarde le tableau de départements en paramètre 
     * et ajoute une liste déroulate ayant les départements
     * 
     */
    public PanneauSaisieDocteur(String[] tableauDepartement) {
        
        super();
        
        this.tableauDepartement = tableauDepartement;
        
        initListeDepartement(this.tableauDepartement);
        
        listeDeroulante = new JScrollPane(liste);
        
        add(listeDeroulante);
    }
    
    /**
     * Initialise la liste JList, elle insère tous les noms de département
     * @param String[]
     */
    public void initListeDepartement(String[] tableauDepartement) {
    	
    	/*
    	 * STRATÉGIE : Créer une liste model par défaut et à l'aide
    	 * d'une boucle, on ajoute les département pour créer une espèce
    	 * de constante à la JList
    	 * 
    	 * JList addElement
    	 */
    	
    	DefaultListModel<String> listModel = new DefaultListModel<String>();
    	
    	liste = new JList<String>(listModel);
    	
    	for(int i = 0; i < tableauDepartement.length; i++) {
    		
    		listModel.addElement(tableauDepartement[i]);
    		
    	}
    	
    	liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    /**
     * Retourne le numéro du département sélectionné dans la JList
     * @return int SelectedIndex
     */
    public int getNumDepartement() {
    	
    	return liste.getSelectedIndex();
    	
    }
    
    /**
     * Crée un nouveau Docteur avec les informations ajoutés dans champs textes
     * et le numéro du departement est ajouté avec celui
     *  selectionné dans liste
     *  
     *  @return Docteur
     */
    public Docteur getParticipant() {
    	
    	return new Docteur(getIdentification(), getNumDepartement());
    	
    }
    
    /**
     * Efface tous les champs textes et enlève la sélection 
     * de l'utilisateur dans la liste
     */
    public void reset() {
    	
    	super.reset();
    	
    	liste.clearSelection();
    }
    
    /**
     * Avise d'une erreur si l'utilisateur n'entre aucun texte dans les champs
     * nom et prénom. Avise une erreur aussi lorsque l'utilisateur
     * ne sélectionne aucun département
     * @return boolean
     */
    public boolean aviserDuneErreur() {
    	
    	/*
    	 * STRATÉGIE : Utiliser la fonction de la classe mère pour valider
    	 * les champs textes
    	 * 
    	 *  Une autre condition vérifie qu'un département est sélectionné
    	 *  avc getSelectedIndex
    	 */

    	//Retournera une erreur dès le début si aucun champs n'est rempli
    	if(super.aviserDuneErreur()) return true;
    	
    	//génère une erreur si aucun département n'est sélectionné
    	if(liste.getSelectedIndex() == -1) {
    		
    		 JOptionPane optionPane = new JOptionPane(MSG_ERREUR_LISTE,
                     JOptionPane.ERROR_MESSAGE);
             
             add(optionPane); 
             
             return true;
    	}
    	
    	return false;
    }
}
