package gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clinique.Patient;

/**
 * Panneau spécifique pour les patients.
 * Ajoute un panneau avec un champs texte qui accueille
 * le NAS du patient et il est obligatoire 
 * 
 * @author Michaël Dallaire
 * @version 1.0
 *
 */
public class PanneauSaisiePatient extends PanneauSaisieParticipant {
	
	/*
	 * CONSTANTES
	 */
	
	public static final String MSG_ERREUR_NAS = 
			"Veuillez entrez le numéro d'assurance sociale";
	
	/*
	 * VARIABLES PRIVÉES
	 */
	
    private static final long serialVersionUID = 1L;
    
    //Champs texte pour le numéro d'assurance sociale
    private JTextField NASTextField;

    /**
     * Constructeur
     * initialise le nouveau panneau de patient avec un champ
     * permmettant d'entrer un NAS
     */
    public PanneauSaisiePatient() {
    	
    	/*
    	 * STRATÉGIE : reprendre ce qui a été fait pour les panneaux de la
    	 * saisie de participant générique pour le nom et prénom
    	 * 
    	 * Créer un panneau qui ajoute les label et champs texte 
    	 * puis ajouter ce panneau à l'instance présente
    	 */
        
        super();
        
        JPanel panneauNAS = new JPanel();
        
        JLabel NASLabel = new JLabel("NAS : ");
        
        NASTextField = new JTextField();
        
        //Change la dimension du champ texte NAS
        NASTextField.setPreferredSize(new Dimension(TEXT_FIELD_LARG,
                TEXT_FIELD_LNG));
        
        panneauNAS.add(NASLabel);
        
        panneauNAS.add(NASTextField);
        
        add(panneauNAS);
    }
    
    /**
     * Prend le texte du champ du NAS et le retourne
     * @return String
     */
    public String getNAS() {
    	
    	return NASTextField.getText();
    	
    }
    
    /**
     * Retourne une nouvelle instance de Patient avec l'information
     * entré dans les champs textes
     * @return Patient
     */
    public Patient getParticipant() {
    	
    	return new Patient(getIdentification(), getNAS());
    	
    }
    
    /**
     * Remet tous les champs textes vide
     */
    public void reset() {
    	
    	super.reset();
    	
    	NASTextField.setText("");
    	
    }
    
    /**
     * Avise d'une erreur si l'un des champs texte n'est pas rempli
     * NAS est obligatoire
     * @return boolean
     */
    public boolean aviserDuneErreur() {
    	
    	if(super.aviserDuneErreur()) return true;
    	
    	//Vérifie que le champ texte NAS n'est pas vide
    	if(NASTextField.getText().equals("")) {

    		JOptionPane optionPane = new JOptionPane(MSG_ERREUR_NAS,
                    JOptionPane.ERROR_MESSAGE);
            
            add(optionPane); 
            
            return true;
    
    	}
    	
    	return false;
    	
    }
    

}
