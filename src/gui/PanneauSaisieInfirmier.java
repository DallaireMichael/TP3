package gui;

import javax.swing.JCheckBox;

import clinique.Infirmier;

/**
 * Panneau de saisie spécifique à un entré des infirmiers
 * Ajoute une méthode qui permet d'obtenir 
 * 
 * @author Michaël Dallaire
 * @version 1.0
 *
 */
public class PanneauSaisieInfirmier extends PanneauSaisieParticipant {
	
	/*
	 * VARIABLES PRIVÉES
	 */
	
    private static final long serialVersionUID = 1L;
    
    //
    private JCheckBox checkBoxDispo;

    /**
     * Constructeur
     * 
     * Initialise le panneau générique et une case à coché pour l'état de 
     * diponibilité d'un infirmier
     * 
     */
    public PanneauSaisieInfirmier() {
        
        super();
        
        checkBoxDispo = new JCheckBox("Disponibilité");
        
        checkBoxDispo.setSelected(true);
        
        add(checkBoxDispo);
    }
    
    /**
     * Retourne l'état de la case à cocher
     * @return boolean
     */
    public boolean getDispo() {
    	
    	return checkBoxDispo.isSelected();
    	
    }
    
    /**
     * Retourne une nouvelle instance d'infirmier avec toute l'information
     * entré dans les chanps texte et la case à coché
     * @return Infirmier
     */
    public Infirmier getParticipant() {
    	
    	return new Infirmier(getIdentification(), getDispo());
    	
    }
    
    /**
     * Vide les champs textes et remet la case à cocher à true
     * 
     */
    public void reset() {
    	
    	super.reset();
    	
    	checkBoxDispo.setSelected(true);
    	
    }
}