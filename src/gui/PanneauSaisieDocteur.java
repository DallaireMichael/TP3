package gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import clinique.Clinique;
import clinique.Docteur;

/**
 * Cette classe s'occupe de g�rer la saisie des informations 
 * sp�cifique aux docteurs. La classe h�rite de PanneauSaisieParticipant,
 * donc nous avons acc�s aux m�thodes de son parent.
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
    	
    	// Copie r�f�rence du tableau de type "String" pass� en param�tre.
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
    	
	    // Cr�ation de la liste des d�partements.
		DefaultListModel<String> listModele = new DefaultListModel<String>();
		
		// Ajout des d�partements  Chirurgie, Urologie et Urgence .
		listModele.addElement(tableauDepartement[Clinique.CHIRURGIE]);
		listModele.addElement(tableauDepartement[Clinique.UROLOGIE]);
		listModele.addElement(tableauDepartement[Clinique.URGENCE]);

		// Copie de la liste en JList.
		liste = new JList<String>(listModele);
		
		// Change le type de s�lection de la JList liste.
		liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Cr�ation de la liste d�roulante et ajout de la 
		// liste de d�partements dans la liste d�roulante.
    	listeDeroulanteDoc = new JScrollPane(liste);
		
		// Ajout de la liste d�roulante au panneau de la classe.
		add(listeDeroulanteDoc);
		
	}
    
    /** 
     * Retourne le num�ro du d�partement selon le num�ro pass� en param�tre.
     * 
     * @return int 
     * 		   
     */
    public int getNumeroDepartement() {
    	
    	int numeroDep;
    	
    	// Le num�ro du d�partement correspondant �
        // la cha�ne de caract�re s�lectionn�.
    	numeroDep = liste.getSelectedIndex();
    	
	    return numeroDep;
		
	}
    
    /** 
     * Retourne un nouveau docteur poss�dant l'identification contenu 
     * dans la classe m�re et le num�ro du d�partement dans ce panneau
     * et ce sans validation.
     * 
     * @return Docteur 
     * 		   
     */
    public Docteur getParticipant() {
    	
    	// Cr�ation du participant gr�ce � la m�thode
    	// dans la classe m�re.
    	Docteur docteur = new Docteur(
    			this.getIdentification(), 
    			getNumeroDepartement());
    	
	    return docteur;
		
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
        
    	// Appel de la m�thode 'reset' de la classe m�re.
    	super.reset();
    	
    	// Efface la s�lection du d�partement dans la liste.
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
    	
    	// V�rifie l'identification du docteur.
    	if(super.aviserDuneErreur() == true) {
    		
    		// Affiche un message d'erreur d'aviserDuneErreur 
    		//de la classe m�re et retourne true.
    		return true;
    		
    	}
    	
    	// Si on n'a pas de d�partement de choisi lors de la 
    	// cr�ation du docteur.
    	else if(liste.getSelectedIndex() == -1) {
    	
    		JOptionPane.showMessageDialog(this,
    				"Il faut choisir un d�partement pour le docteur.");
    		return true;
    		
    	}
    	
    	// Si les champs ne sont pas vides et on a un d�partement de choisi.
    	else {
    		
    		return false;
    		
    	}
    	
    }
}
