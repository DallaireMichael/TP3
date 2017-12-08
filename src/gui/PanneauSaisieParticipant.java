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
 * Classe mère des tous les panneaux utiles pour la clinique.
 * Elle permet de saisir l'identification d'un participant
 * dans un champ pour le nom et un champ pour le prénom.
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
    
    // Le texte écrit à côté du champ pour le nom.
    private JLabel etiquetteNom;
    
    // Champ de saisie pour le prénom.
    private JTextField champSaisiePrenom;
    
    // Le texte écrit à côté du champ pour le prénom.
    private JLabel etiquettePrenom;
    
    // Panneau pour le nom.
    private JPanel panneauNom;
    
    // Panneau pour le prénom.
    private JPanel panneauPrenom;

    /**
     * Affiche deux champ de saisie qui permettent de définir
     * un participant par son nom et son prénom.
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
     * On affiche deux champs de saisie qui demandent un nom et un prénom.
     *  
     * @return void
     */
    public void initialiserComposantes() {

    	// On crée nos panneaux, nos étiquettes et champs de saisie.
    	etiquetteNom = new JLabel("Nom");
    	etiquettePrenom = new JLabel("Prénom");
    	champSaisieNom = new JTextField();
    	champSaisiePrenom = new JTextField();
    	panneauNom = new JPanel();
    	panneauPrenom = new JPanel();
    	
    	// Ajustement de la longeur des champs de saisie.
    	champSaisieNom.setPreferredSize(Constantes.DIMENSION_TEXTFIELD);
    	champSaisiePrenom.setPreferredSize(Constantes.DIMENSION_TEXTFIELD);
    	
    	// Ajout du champs de saisie du nom et son étiquette au panneau de nom.
    	panneauNom.add(etiquetteNom);
    	panneauNom.add(champSaisieNom);
    	
    	// Ajout du champs de saisie du prénom 
    	// et son étiquette au panneau de prénom.
    	panneauPrenom.add(etiquettePrenom);
    	panneauPrenom.add(champSaisiePrenom);
    	
    	// Ajout les panneaux de nom et celui de prénom au panneau de saisie.
    	// d'un participant.
    	add(panneauNom);
    	add(panneauPrenom);
    	
    }
    
    /**
     * S'occupe de retourne une nouvelle 'Identification' 
     * possédant le nom et le prénom contenu dans les boîtes 
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
     * possédant l'identificant contenu dans les boîtes 
     * de textes de ce panneau même si les champs de saisie sont vides.
     * 
     * @return Participant nouveauParticipant
     * 		   Nouveau participant avec notre id d'écrit.	
     */
    @Override
    public Participant getParticipant() {
    	
    	return new Participant(getIdentification());
    	
    }

    /**
     * S'occupe de vérifier si un des champs de saisie ne contient 
     * pas de texte (ou bien les deux sont vides). Si c'est le cas, 
     * on retourne true et un message d'erreur, sinon on retourne false.
     * 
     */
    @Override
    public boolean aviserDuneErreur() {
    	
    	// Vérifie si un ou les champs ne contienent pas de mots.
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
     * le remplaçant par une chaîne vide.
     * 
     */
    @Override
    public void reset() {
        
    	champSaisieNom.setText("");
    	
    	champSaisiePrenom.setText("");
    	
    }
   
}
