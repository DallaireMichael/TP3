package gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clinique.Patient;

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
    
    // Composantes SWING pour l'interface afin d'afficher 
    // un champ de saisie pour le NAS.
    private JTextField champSaisieNAS;
    private JLabel etiquetteNAS;
    private JPanel panneauNAS;
     
    // Dimension du champ de saisie pour le NAS.
    private Dimension dimensionChampSaisie = new Dimension(200, 24);

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
     * S'occupe d'initialiser tous les composantes du panneau 
     * de saisie pour l'interface d'un patient.
     * 
     * @return void
     */
    public void initialiserComposantesPat() {

    	// On crée nos panneaux, nos étiquettes et champ de saisie
    	// pour le numéro d'assurance sociale.
    	etiquetteNAS = new JLabel("NAS");
    	champSaisieNAS = new JTextField();
    	panneauNAS = new JPanel();
    	
    	// Ajustement de la longeur du champ de saisie.
    	champSaisieNAS.setPreferredSize(dimensionChampSaisie);
    	
    	// Ajout du champ de saisie du NAS et son étiquette au panneau de NAS.
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
    	
    	String numeroNAS;
    	
    	// le numéro de NAS provenant du champ de saisie.
    	numeroNAS = champSaisieNAS.getText();
    	
	    return numeroNAS;
		
	}
    
    /** 
     * Retourne un nouveau Patient possédant l'identification contenu 
     * dans la classe mère et le NAS.
     * 
     * @return Patient 
     * 		   
     */
    public Patient getParticipant() {
    	
    	// Création du participant grâce à la méthode
    	// dans la classe mère.
    	Patient patient = new Patient(
    			this.getIdentification(), 
    			getNAS());
    	
	    return patient;
		
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
        
    	// Appel de la méthode 'reset' de la classe mère.
    	super.reset();
    	
    	// Efface le texte du champ de saisie pour le NAS.
    	champSaisieNAS.setText("");
    	
    }
    
    /**
     * S'occupe de vérifier si un des champs de saisie ne contient 
     * pas de texte (ou bien les deux sont vides). Si c'est le cas, 
     * on retourne true et un message d'erreur, sinon on retourne false.
     * 
     * @return boolean.
     */
    @Override
    public boolean aviserDuneErreur() {
    	
    	// Vérifie l'identification du patient.
    	if(super.aviserDuneErreur() == true) {
    		
    		// Affiche un message d'erreur et retourne true.
    		JOptionPane.showMessageDialog(this,
    				"Les champs de l'identifiant ne doivent pas être vides.");
    		return true;
    		
    	}
    	
    	// Si le champ du NAS est vide.
    	else if(champSaisieNAS.getText() == ""){
    		
    		// Affiche un message d'erreur et retourne true 
    		JOptionPane.showMessageDialog(this,
    				"Le champ pour le NAS ne doit pas être vide.");
    		return true;
    		
    	}
    	
    	// Si les champs ne sont pas vides.
    	else {
    		
    		return false;
    		
    	}
    	
    }

}
