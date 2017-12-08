package gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import clinique.Docteur;
import utilitaire.Constantes;

/**
 * Cette classe s'occupe de gérer la saisie des informations 
 * spécifique aux docteurs. La classe hérite de PanneauSaisieParticipant,
 * donc nous avons accès aux méthodes de son parent. De plus, on ajoute un
 * liste déroulante pour choisir le département du docteur qu'on veut ajouter.
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
    
    // Liste contenant les départements des docteurs.
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
    	
	    // Instancie la liste modèle qu'on va affecter à notre 
    	// liste de département qui sera affiché dans la liste déroulante.
		liste = new JList<String>(creerListeModele());
		
		// Change le type de sélection de la JList liste.
		liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Création de la liste déroulante et ajout de la 
		// liste de départements dans la liste déroulante.
    	listeDeroulanteDoc = new JScrollPane(liste);
		
		// Ajout de la liste déroulante au panneau de la classe.
		add(listeDeroulanteDoc);
		
	}
    
    /** 
     * Création de la liste modèle pour les départements.
     * Afin de pouvoir l'affecter à un JList plus loin.
     * 
     * @return DefaultListModel<String> listeModele
     * 		   Contient tous les départements de la clinique.	
     */
    private DefaultListModel<String> creerListeModele() {
		
    	
		DefaultListModel<String> listeModele = new DefaultListModel<String>();
		
		// Boucle qui ajoute les départements à notre liste modèle.
		// On retrouve pour l'instant les départements 
		// "Urologie", "Urgence" et "Chirurgie".
		for(int i = 0; i< tableauDepartement.length; i++) {
			
			listeModele.addElement(tableauDepartement[i]);
			
		}
		
		return listeModele;
	}

	/** 
     * Retourne le numéro du département selon le numéro passé en paramètre.
     * 
     * @return int 
     * 		   
     */
    public int getNumeroDepartement() {
    	
	    return liste.getSelectedIndex();
		
	}
    
    /** 
     * Retourne un nouveau docteur possédant l'identification contenu 
     * dans la classe mère et le numéro du département dans ce panneau
     * et ce sans validation.
     * 
     * @return Docteur 
     * 		   Un nouveau docteur avec l'information qu'on écrit.
     */
    public Docteur getParticipant() {
  
	    return new Docteur(this.getIdentification(), getNumeroDepartement());
		
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
        
    	super.reset();
    	
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
    	
    	// Vérifie l'identification du docteur a ou des champs de vides.
    	if(super.aviserDuneErreur()) return true;
    	
    	// Si on n'a pas de département de choisi lors de la 
    	// création du docteur, on affiche un message d'erreur.
    	else if(liste.getSelectedIndex() == -1) {
    	
    		JOptionPane.showMessageDialog(this, Constantes.MSG_SELECT_DEP_VIDE);
    		
    		return true;
    		
    	}
    
    	return false;
    	
    }
}
