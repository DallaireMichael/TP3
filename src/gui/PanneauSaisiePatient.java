package gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clinique.Patient;
import utilitaire.Constantes;

/**
 * Cette classe s'occupe de gérer la saisie des informations 
 * spécifique aux Patients. La classe hérite de PanneauSaisieParticipant,
 * donc nous avons accès aux méthodes de son parent.
 * 
 * @author	Niko Girardelli
 * @version	Automne 2017
 *
 */
public class PanneauSaisiePatient extends PanneauSaisieParticipant {

	/** ATTRIBUTS **/
    private static final long serialVersionUID = 1L;
     
    // Un champ de saisie pour le NAS.
    private JTextField champSaisieNAS;
 
    // L'étiquette du champ de saisie pour le NAS.
    private JLabel etiquetteNAS;
    
    // Le panneau contenant le champ texte et son étiquette.
    private JPanel panneauNAS;

    /**
     * Affiche deux champ de saisie qui permettent de définir
     * un participant par son nom et son prénom qui provient du parent
     * avec un espace pour un écrire le numéro d'assurance social du patient.
     * 
     */
    public PanneauSaisiePatient() {

    	// Appel du constructeur mère.
    	super();
    	
    	// Appel de la méthode qui se charge de créer 
    	// les composantes pour l'interface
    	initialiserComposantesPat();
    	
    }
    
    /** 
     * S'occupe d'initialiser toutes les composantes du panneau 
     * de saisie pour l'interface d'un patient. Crée le panneau,
     * l'étiquette et le champ de saisie pour le NAS.
     * 
     * @return void
     */
    public void initialiserComposantesPat() {

    	etiquetteNAS = new JLabel("NAS");
    	
    	champSaisieNAS = new JTextField();
    	
    	panneauNAS = new JPanel();
    	
    	// Ajustement de la longeur du champ de saisie.
    	champSaisieNAS.setPreferredSize(Constantes.DIMENSION_TEXTFIELD);
    	
    	panneauNAS.add(etiquetteNAS);
    	
    	panneauNAS.add(champSaisieNAS);
    	
    	// Ajout les panneaux de NAS au panneau de saisie d'un patient.
    	add(panneauNAS);
    	
    }
    
    /** 
     * Retourne le numéro de NAS sous forme de chaîne de caractères.
     * 
     * @return String 
     * 		   
     */
    public String getNAS() {

	    return champSaisieNAS.getText();
		
	}
    
    /** 
     * Retourne un nouveau Patient possédant l'identification contenu 
     * dans la classe mère et le NAS entré.
     * 
     * @return Patient 
     * 		   Un nouveau Patient avec notre information de donné.
     */
    public Patient getParticipant() {
    	
	    return new Patient(this.getIdentification(), getNAS());
		
	}
    
    /**
     * Efface le texte des champs de saisie en 
     * le remplaçant par une chaîne vide en appelant la 
     * méthode 'reset' du parent et en effançant celui pour 
     * le NAS aussi.
     * 
     */
    @Override
    public void reset() {
        
    	super.reset();
    	
    	champSaisieNAS.setText("");
    	
    }
    
    /**
     * S'occupe de vérifier si un des trois champs de saisie ne contiennent 
     * pas de texte (ou bien les trois sont vides). Si c'est le cas, 
     * on retourne true et un message d'erreur, sinon on retourne false.
     * 
     * @return boolean.
     */
    @Override
    public boolean aviserDuneErreur() {
    	
    	if(super.aviserDuneErreur() == true) return true;
    	
    	// Si le champ du NAS est vide.
    	else if(champSaisieNAS.getText().equals("")){
    		
    		JOptionPane.showMessageDialog(this, Constantes.MSG_CHAMP_NAS_VIDE);
    		return true;
    		
    	}
    	
    	return false;
    	
    }

}
