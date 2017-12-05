package gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clinique.Patient;

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
    
    // Composantes SWING pour l'interface afin d'afficher 
    // un champ de saisie pour le NAS.
    private JTextField champSaisieNAS;
    private JLabel etiquetteNAS;
    private JPanel panneauNAS;
     
    // Dimension du champ de saisie pour le NAS.
    private Dimension dimensionChampSaisie = new Dimension(200, 24);

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
     * S'occupe d'initialiser tous les composantes du panneau 
     * de saisie pour l'interface d'un patient.
     * 
     * @return void
     */
    public void initialiserComposantesPat() {

    	// On cr�e nos panneaux, nos �tiquettes et champ de saisie
    	// pour le num�ro d'assurance sociale.
    	etiquetteNAS = new JLabel("NAS");
    	champSaisieNAS = new JTextField();
    	panneauNAS = new JPanel();
    	
    	// Ajustement de la longeur du champ de saisie.
    	champSaisieNAS.setPreferredSize(dimensionChampSaisie);
    	
    	// Ajout du champ de saisie du NAS et son �tiquette au panneau de NAS.
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
    	
    	String numeroNAS;
    	
    	// le num�ro de NAS provenant du champ de saisie.
    	numeroNAS = champSaisieNAS.getText();
    	
	    return numeroNAS;
		
	}
    
    /** 
     * Retourne un nouveau Patient poss�dant l'identification contenu 
     * dans la classe m�re et le NAS.
     * 
     * @return Patient 
     * 		   
     */
    public Patient getParticipant() {
    	
    	// Cr�ation du participant gr�ce � la m�thode
    	// dans la classe m�re.
    	Patient patient = new Patient(
    			this.getIdentification(), 
    			getNAS());
    	
	    return patient;
		
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
        
    	// Appel de la m�thode 'reset' de la classe m�re.
    	super.reset();
    	
    	// Efface le texte du champ de saisie pour le NAS.
    	champSaisieNAS.setText("");
    	
    }
    
    /**
     * S'occupe de v�rifier si un des champs de saisie ne contient 
     * pas de texte (ou bien les deux sont vides). Si c'est le cas, 
     * on retourne true et un message d'erreur, sinon on retourne false.
     * 
     * @return boolean.
     */
    @Override
    public boolean aviserDuneErreur() {
    	
    	// V�rifie l'identification du patient.
    	if(super.aviserDuneErreur() == true) {
    		
    		// Affiche un message d'erreur et retourne true.
    		JOptionPane.showMessageDialog(this,
    				"Les champs de l'identifiant ne doivent pas �tre vides.");
    		return true;
    		
    	}
    	
    	// Si le champ du NAS est vide.
    	else if(champSaisieNAS.getText() == ""){
    		
    		// Affiche un message d'erreur et retourne true 
    		JOptionPane.showMessageDialog(this,
    				"Le champ pour le NAS ne doit pas �tre vide.");
    		return true;
    		
    	}
    	
    	// Si les champs ne sont pas vides.
    	else {
    		
    		return false;
    		
    	}
    	
    }

}
