package gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clinique.Patient;
import utilitaire.Constantes;

/**
 * Cette classe s'occupe de g�rer la saisie des informations 
 * sp�cifique aux Patients. La classe h�rite de PanneauSaisieParticipant,
 * donc nous avons acc�s aux m�thodes de son parent.
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
 
    // L'�tiquette du champ de saisie pour le NAS.
    private JLabel etiquetteNAS;
    
    // Le panneau contenant le champ texte et son �tiquette.
    private JPanel panneauNAS;

    /**
     * Affiche deux champ de saisie qui permettent de d�finir
     * un participant par son nom et son pr�nom qui provient du parent
     * avec un espace pour un �crire le num�ro d'assurance social du patient.
     * 
     */
    public PanneauSaisiePatient() {

    	// Appel du constructeur m�re.
    	super();
    	
    	// Appel de la m�thode qui se charge de cr�er 
    	// les composantes pour l'interface
    	initialiserComposantesPat();
    	
    }
    
    /** 
     * S'occupe d'initialiser toutes les composantes du panneau 
     * de saisie pour l'interface d'un patient. Cr�e le panneau,
     * l'�tiquette et le champ de saisie pour le NAS.
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
     * Retourne le num�ro de NAS sous forme de cha�ne de caract�res.
     * 
     * @return String 
     * 		   
     */
    public String getNAS() {

	    return champSaisieNAS.getText();
		
	}
    
    /** 
     * Retourne un nouveau Patient poss�dant l'identification contenu 
     * dans la classe m�re et le NAS entr�.
     * 
     * @return Patient 
     * 		   Un nouveau Patient avec notre information de donn�.
     */
    public Patient getParticipant() {
    	
	    return new Patient(this.getIdentification(), getNAS());
		
	}
    
    /**
     * Efface le texte des champs de saisie en 
     * le rempla�ant par une cha�ne vide en appelant la 
     * m�thode 'reset' du parent et en effan�ant celui pour 
     * le NAS aussi.
     * 
     */
    @Override
    public void reset() {
        
    	super.reset();
    	
    	champSaisieNAS.setText("");
    	
    }
    
    /**
     * S'occupe de v�rifier si un des trois champs de saisie ne contiennent 
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
