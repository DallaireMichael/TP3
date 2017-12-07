package gui;

import javax.swing.JCheckBox;

import clinique.Infirmier;

/**
 * Panneau de saisie sp�cifique � un entr� des infirmiers
 * Ajoute une m�thode qui permet d'obtenir 
 * 
 * @author Micha�l Dallaire
 * @version 1.0
 *
 */
public class PanneauSaisieInfirmier extends PanneauSaisieParticipant {
	
	/*
	 * VARIABLES PRIV�ES
	 */
	
    private static final long serialVersionUID = 1L;
    
    //
    private JCheckBox checkBoxDispo;

    /**
     * Constructeur
     * 
     * Initialise le panneau g�n�rique et une case � coch� pour l'�tat de 
     * diponibilit� d'un infirmier
     * 
     */
    public PanneauSaisieInfirmier() {
        
        super();
        
        checkBoxDispo = new JCheckBox("Disponibilit�");
        
        checkBoxDispo.setSelected(true);
        
        add(checkBoxDispo);
    }
    
    /**
     * Retourne l'�tat de la case � cocher
     * @return boolean
     */
    public boolean getDispo() {
    	
    	return checkBoxDispo.isSelected();
    	
    }
    
    /**
     * Retourne une nouvelle instance d'infirmier avec toute l'information
     * entr� dans les chanps texte et la case � coch�
     * @return Infirmier
     */
    public Infirmier getParticipant() {
    	
    	return new Infirmier(getIdentification(), getDispo());
    	
    }
    
    /**
     * Vide les champs textes et remet la case � cocher � true
     * 
     */
    public void reset() {
    	
    	super.reset();
    	
    	checkBoxDispo.setSelected(true);
    	
    }
}