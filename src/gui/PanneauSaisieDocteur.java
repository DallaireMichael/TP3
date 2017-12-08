package gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import clinique.Docteur;
import utilitaire.Constantes;

/**
 * Cette classe s'occupe de g�rer la saisie des informations 
 * sp�cifique aux docteurs. La classe h�rite de PanneauSaisieParticipant,
 * donc nous avons acc�s aux m�thodes de son parent. De plus, on ajoute un
 * liste d�roulante pour choisir le d�partement du docteur qu'on veut ajouter.
 * 
 * @author	Niko Girardelli
 * @version	Automne 2017
 *
 */
public class PanneauSaisieDocteur extends PanneauSaisieParticipant {

	/** ATTRIBUTS **/
    private static final long serialVersionUID = 1L;
    
    // Liste d�roulante affichant les d�partements des docteurs.
    private JScrollPane listeDeroulanteDoc;
    
    // Liste contenant les d�partements des docteurs.
    private JList<String> liste;
    
    // Tableau contenant les d�partements des docteurs.
    private String [] tableauDepartement;

    /**
     * Re�oit un tableau de d�partements en param�tre et 
     * conserve une r�f�rence dans l'attribut tableauDepartement,
     * et on initialise la liste 
     * 
     * 
     */
    public PanneauSaisieDocteur(String[] tableauDepartement) {
    	
    	// Appel du constructeur m�re.
    	super();
    	
    	this.tableauDepartement = tableauDepartement;
    	
    	// Appel � la m�thode qui se charge d'initialiser 
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
    	
	    // Instancie la liste mod�le qu'on va affecter � notre 
    	// liste de d�partement qui sera affich� dans la liste d�roulante.
		liste = new JList<String>(creerListeModele());
		
		// Change le type de s�lection de la JList liste.
		liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Cr�ation de la liste d�roulante et ajout de la 
		// liste de d�partements dans la liste d�roulante.
    	listeDeroulanteDoc = new JScrollPane(liste);
		
		// Ajout de la liste d�roulante au panneau de la classe.
		add(listeDeroulanteDoc);
		
	}
    
    /** 
     * Cr�ation de la liste mod�le pour les d�partements.
     * Afin de pouvoir l'affecter � un JList plus loin.
     * 
     * @return DefaultListModel<String> listeModele
     * 		   Contient tous les d�partements de la clinique.	
     */
    private DefaultListModel<String> creerListeModele() {
		
    	
		DefaultListModel<String> listeModele = new DefaultListModel<String>();
		
		// Boucle qui ajoute les d�partements � notre liste mod�le.
		// On retrouve pour l'instant les d�partements 
		// "Urologie", "Urgence" et "Chirurgie".
		for(int i = 0; i< tableauDepartement.length; i++) {
			
			listeModele.addElement(tableauDepartement[i]);
			
		}
		
		return listeModele;
	}

	/** 
     * Retourne le num�ro du d�partement selon le num�ro pass� en param�tre.
     * 
     * @return int 
     * 		   
     */
    public int getNumeroDepartement() {
    	
	    return liste.getSelectedIndex();
		
	}
    
    /** 
     * Retourne un nouveau docteur poss�dant l'identification contenu 
     * dans la classe m�re et le num�ro du d�partement dans ce panneau
     * et ce sans validation.
     * 
     * @return Docteur 
     * 		   Un nouveau docteur avec l'information qu'on �crit.
     */
    public Docteur getParticipant() {
  
	    return new Docteur(this.getIdentification(), getNumeroDepartement());
		
	}
    
    /**
     * Efface le texte des champs de saisie en 
     * le rempla�ant par une cha�ne vide en appelant la 
     * m�thode 'reset' du parent et efface la s�lection
     * actuelle de la liste des d�partements.
     * 
     */
    @Override
    public void reset() {
        
    	super.reset();
    	
    	liste.clearSelection();
    	
    }
    
    /**
     * S'occupe de v�rifier si un des champs de saisie ne contient 
     * pas de texte (ou bien les deux sont vides). Si c'est le cas, 
     * on retourne true et un message d'erreur. On retourne true et 
     * affiche un message d'erreur si le d�partement n'est pas choisi
     * et sinon on retourne false.
     * 
     * @return boolean.
     */
    @Override
    public boolean aviserDuneErreur() {
    	
    	// V�rifie l'identification du docteur a ou des champs de vides.
    	if(super.aviserDuneErreur()) return true;
    	
    	// Si on n'a pas de d�partement de choisi lors de la 
    	// cr�ation du docteur, on affiche un message d'erreur.
    	else if(liste.getSelectedIndex() == -1) {
    	
    		JOptionPane.showMessageDialog(this, Constantes.MSG_SELECT_DEP_VIDE);
    		
    		return true;
    		
    	}
    
    	return false;
    	
    }
}
