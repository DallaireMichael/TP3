package gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import clinique.Clinique;
import clinique.Docteur;

/**
 * Cette classe s'occupe de gérer la saisie des informations 
 * spécifique aux docteurs. La classe hérite de PanneauSaisieParticipant,
 * donc nous avons accès aux méthodes de son parent.
 * 
 * @author	Niko Girardelli
 * @version	Automne 2017
 *
 */
public class PanneauSaisieDocteur extends PanneauSaisieParticipant {

	/** ATTRIBUTS **/
    private static final long serialVersionUID = 1L;
    
    // Liste déroulante affichant les départements des docteurs.
    private JScrollPane listeDeroulanteDoc;
    private JList<String> liste;
    
    // Tableau contenant les départements des docteurs.
    private String [] tableauDepartement;

    /**
     * Reçoit un tableau de départements en paramètre et 
     * conserve une référence dans l'attribut tableauDepartement,
     * et on initialise la liste 
     * 
     * 
     */
    public PanneauSaisieDocteur(String[] tableauDepartement) {
    	
    	// Appel du constructeur mère.
    	super();
    	
    	// Copie référence du tableau de type "String" passé en paramètre.
    	this.tableauDepartement = tableauDepartement;
    	
    	// Appel à la méthode qui se charge d'initialiser 
    	// les composantes du panneauSaisieDocteur.
    	initialiserComposantesDoc();
    	
    }
    
    /** 
     * S'occupe d'initialiser tous les composantes 
     * du panneau de saisie pour l'interface avec les docteurs.
     * 
     * @return void
     */
    public void initialiserComposantesDoc() {
    	
	    // Création de la liste des départements.
		DefaultListModel<String> listModele = new DefaultListModel<String>();
		
		// Ajout des départements  Chirurgie, Urologie et Urgence .
		listModele.addElement(tableauDepartement[Clinique.CHIRURGIE]);
		listModele.addElement(tableauDepartement[Clinique.UROLOGIE]);
		listModele.addElement(tableauDepartement[Clinique.URGENCE]);

		// Copie de la liste en JList.
		liste = new JList<String>(listModele);
		
		// Change le type de sélection de la JList liste.
		liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Création de la liste déroulante et ajout de la 
		// liste de départements dans la liste déroulante.
    	listeDeroulanteDoc = new JScrollPane(liste);
		
		// Ajout de la liste déroulante au panneau de la classe.
		add(listeDeroulanteDoc);
		
	}
    
    /** 
     * Retourne le numéro du département selon le numéro passé en paramètre.
     * 
     * @return int 
     * 		   
     */
    public int getNumeroDepartement() {
    	
    	int numeroDep;
    	
    	// Le numéro du département correspondant à
        // la chaîne de caractère sélectionné.
    	numeroDep = liste.getSelectedIndex();
    	
	    return numeroDep;
		
	}
    
    /** 
     * Retourne un nouveau docteur possédant l'identification contenu 
     * dans la classe mère et le numéro du département dans ce panneau
     * et ce sans validation.
     * 
     * @return Docteur 
     * 		   
     */
    public Docteur getParticipant() {
    	
    	// Création du participant grâce à la méthode
    	// dans la classe mère.
    	Docteur docteur = new Docteur(
    			this.getIdentification(), 
    			getNumeroDepartement());
    	
	    return docteur;
		
	}
    
    /**
     * Efface le texte des champs de saisie en 
     * le remplaçant par une chaîne vide en appelant la 
     * méthode 'reset' du parent et efface la sélection
     * actuelle de la liste des départements.
     * 
     */
    @Override
    public void reset() {
        
    	// Appel de la méthode 'reset' de la classe mère.
    	super.reset();
    	
    	// Efface la sélection du département dans la liste.
    	liste.clearSelection();
    	
    }
    
    /**
     * S'occupe de vérifier si un des champs de saisie ne contient 
     * pas de texte (ou bien les deux sont vides). Si c'est le cas, 
     * on retourne true et un message d'erreur. On retourne true et 
     * affiche un message d'erreur si le département n'est pas choisi
     * et sinon on retourne false.
     * 
     * @return boolean.
     */
    @Override
    public boolean aviserDuneErreur() {
    	
    	// Vérifie l'identification du docteur.
    	if(super.aviserDuneErreur() == true) {
    		
    		// Affiche un message d'erreur d'aviserDuneErreur 
    		//de la classe mère et retourne true.
    		return true;
    		
    	}
    	
    	// Si on n'a pas de département de choisi lors de la 
    	// création du docteur.
    	else if(liste.getSelectedIndex() == -1) {
    	
    		JOptionPane.showMessageDialog(this,
    				"Il faut choisir un département pour le docteur.");
    		return true;
    		
    	}
    	
    	// Si les champs ne sont pas vides et on a un département de choisi.
    	else {
    		
    		return false;
    		
    	}
    	
    }
}
