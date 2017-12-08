package gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clinique.Identification;
import clinique.Participant;
import utilitaire.Constantes;

/**
 * Classe m�re des tous les panneaux utiles pour la clinique.
 * Elle permet de saisir l'identification d'un participant
 * dans un champ pour le nom et un champ pour le pr�nom.
 * 
 * @author	Niko Girardelli
 * @version	Automne 2017
 *
 */

public class PanneauSaisieParticipant extends JPanel 
implements InterfacePanSaisieParticipant {
	
	/** ATTRIBUTS **/
    private static final long serialVersionUID = 1L;
    
    // Champ de saisie pour le nom.
    private JTextField champSaisieNom;
    
    // Le texte �crit � c�t� du champ pour le nom.
    private JLabel etiquetteNom;
    
    // Champ de saisie pour le pr�nom.
    private JTextField champSaisiePrenom;
    
    // Le texte �crit � c�t� du champ pour le pr�nom.
    private JLabel etiquettePrenom;
    
    // Panneau pour le nom.
    private JPanel panneauNom;
    
    // Panneau pour le pr�nom.
    private JPanel panneauPrenom;

    /**
     * Affiche deux champ de saisie qui permettent de d�finir
     * un participant par son nom et son pr�nom.
     * 
     */
    public PanneauSaisieParticipant() {
    	
    	// Fixe la disposition de notre panneau de saisie 
    	// d'un participant en PAGE_AXIS.
    	this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	
    	initialiserComposantes();
    	
    }
    
    /** 
     * S'occupe d'initialiser toutes les composantes du panneau de saisie.
     * On affiche deux champs de saisie qui demandent un nom et un pr�nom.
     *  
     * @return void
     */
    public void initialiserComposantes() {

    	// On cr�e nos panneaux, nos �tiquettes et champs de saisie.
    	etiquetteNom = new JLabel("Nom");
    	etiquettePrenom = new JLabel("Pr�nom");
    	champSaisieNom = new JTextField();
    	champSaisiePrenom = new JTextField();
    	panneauNom = new JPanel();
    	panneauPrenom = new JPanel();
    	
    	// Ajustement de la longeur des champs de saisie.
    	champSaisieNom.setPreferredSize(Constantes.DIMENSION_TEXTFIELD);
    	champSaisiePrenom.setPreferredSize(Constantes.DIMENSION_TEXTFIELD);
    	
    	// Ajout du champs de saisie du nom et son �tiquette au panneau de nom.
    	panneauNom.add(etiquetteNom);
    	panneauNom.add(champSaisieNom);
    	
    	// Ajout du champs de saisie du pr�nom 
    	// et son �tiquette au panneau de pr�nom.
    	panneauPrenom.add(etiquettePrenom);
    	panneauPrenom.add(champSaisiePrenom);
    	
    	// Ajout les panneaux de nom et celui de pr�nom au panneau de saisie.
    	// d'un participant.
    	add(panneauNom);
    	add(panneauPrenom);
    	
    }
    
    /**
     * S'occupe de retourne une nouvelle 'Identification' 
     * poss�dant le nom et le pr�nom contenu dans les bo�tes 
     * de textes de ce panneau.
     * 
     * @return Identification nouvelleId
     */
    public Identification getIdentification() {
    	
    	return new Identification(
    			champSaisieNom.getText(), 
    			champSaisiePrenom.getText());
    	
    }
    
    /**
     * S'occupe de retourne une nouvelle 'Participant' 
     * poss�dant l'identificant contenu dans les bo�tes 
     * de textes de ce panneau m�me si les champs de saisie sont vides.
     * 
     * @return Participant nouveauParticipant
     * 		   Nouveau participant avec notre id d'�crit.	
     */
    @Override
    public Participant getParticipant() {
    	
    	return new Participant(getIdentification());
    	
    }

    /**
     * S'occupe de v�rifier si un des champs de saisie ne contient 
     * pas de texte (ou bien les deux sont vides). Si c'est le cas, 
     * on retourne true et un message d'erreur, sinon on retourne false.
     * 
     */
    @Override
    public boolean aviserDuneErreur() {
    	
    	// V�rifie si un ou les champs ne contienent pas de mots.
    	if(champSaisieNom.getText().equals("") || 
    			champSaisiePrenom.getText().equals("")) {
    		
    		// Affiche un message d'erreur et retourne true.
    		JOptionPane.showMessageDialog(this, Constantes.MSG_CHAMP_ID_VIDE);
    		
    		return true;
    		
    	}
    	
    	// Si les champs ne sont pas vides.
    	return false;
    
    }

    /**
     * Efface le texte des champs de saisie en 
     * le rempla�ant par une cha�ne vide.
     * 
     */
    @Override
    public void reset() {
        
    	champSaisieNom.setText("");
    	
    	champSaisiePrenom.setText("");
    	
    }
   
}
