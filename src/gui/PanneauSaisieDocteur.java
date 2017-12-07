package gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import clinique.Docteur;
import clinique.Participant;

/**
 * Panneau de saisie sp�cifique � un entr� de Docteurs.
 * Elle ajoute une liste d�roulante ayant les d�partements
 * valide pour les docteurs exemple : Chirurgie
 * 
 * @author Micha�l Dallaire
 * @version 1.0
 *
 */
public class PanneauSaisieDocteur extends PanneauSaisieParticipant {

	/*
	 * CONSTANTES
	 */
	
	public final static String MSG_ERREUR_LISTE = "D�partement non-s�lectionn�";
	
	/*
	 * VARIABLE PRIV�ES
	 */
	
    private static final long serialVersionUID = 1L;
    
    //panneau deroulant pour la liste des d�partement
    private JScrollPane listeDeroulante;
    
    //Liste dans laquelle on peut s�lectionner un d�partement
    private JList liste;
    
    //tableau contenant les noms des d�partement
    private String[] tableauDepartement;

    /**
     * Constructeur
     * Sauvegarde le tableau de d�partements en param�tre 
     * et ajoute une liste d�roulate ayant les d�partements
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
     * Initialise la liste JList, elle ins�re tous les noms de d�partement
     * @param String[]
     */
    public void initListeDepartement(String[] tableauDepartement) {
    	
    	/*
    	 * STRAT�GIE : Cr�er une liste model par d�faut et � l'aide
    	 * d'une boucle, on ajoute les d�partement pour cr�er une esp�ce
    	 * de constante � la JList
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
     * Retourne le num�ro du d�partement s�lectionn� dans la JList
     * @return int SelectedIndex
     */
    public int getNumDepartement() {
    	
    	return liste.getSelectedIndex();
    	
    }
    
    /**
     * Cr�e un nouveau Docteur avec les informations ajout�s dans champs textes
     * et le num�ro du departement est ajout� avec celui
     *  selectionn� dans liste
     *  
     *  @return Docteur
     */
    public Docteur getParticipant() {
    	
    	return new Docteur(getIdentification(), getNumDepartement());
    	
    }
    
    /**
     * Efface tous les champs textes et enl�ve la s�lection 
     * de l'utilisateur dans la liste
     */
    public void reset() {
    	
    	super.reset();
    	
    	liste.clearSelection();
    }
    
    /**
     * Avise d'une erreur si l'utilisateur n'entre aucun texte dans les champs
     * nom et pr�nom. Avise une erreur aussi lorsque l'utilisateur
     * ne s�lectionne aucun d�partement
     * @return boolean
     */
    public boolean aviserDuneErreur() {
    	
    	/*
    	 * STRAT�GIE : Utiliser la fonction de la classe m�re pour valider
    	 * les champs textes
    	 * 
    	 *  Une autre condition v�rifie qu'un d�partement est s�lectionn�
    	 *  avc getSelectedIndex
    	 */

    	//Retournera une erreur d�s le d�but si aucun champs n'est rempli
    	if(super.aviserDuneErreur()) return true;
    	
    	//g�n�re une erreur si aucun d�partement n'est s�lectionn�
    	if(liste.getSelectedIndex() == -1) {
    		
    		 JOptionPane optionPane = new JOptionPane(MSG_ERREUR_LISTE,
                     JOptionPane.ERROR_MESSAGE);
             
             add(optionPane); 
             
             return true;
    	}
    	
    	return false;
    }
}
