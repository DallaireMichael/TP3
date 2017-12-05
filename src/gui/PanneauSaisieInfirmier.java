package gui;

import javax.swing.JCheckBox;

import clinique.Infirmier;

/**
 * Cette classe s'occupe de gérer la saisie des informations 
 * spécifique aux infirmiers. La classe hérite de PanneauSaisieParticipant,
 * donc nous avons accès aux méthodes de son parent.
 * 
 * @author	Niko Girardelli
 * @version	Automne 2017
 *
 */
public class PanneauSaisieInfirmier extends PanneauSaisieParticipant {


	/** ATTRIBUTS **/
    private static final long serialVersionUID = 1L;
    private JCheckBox disponibilite;

    /**
     * S'occupe de générer l'interface pour la saisie d'informations pour un patient.
     * 
     */
    public PanneauSaisieInfirmier() {
    	
    	// Appel à la méthode qui initialise les composantes pour 
    	// l'interface de saisie d'un infirmier.
    	initialiserComposantesInf();

    }
    
    /** 
     * S'occupe d'initialiser tous les composantes 
     * du panneau de saisie pour l'interface avec les infirmiers.
     * 
     * @return void
     */
    public void initialiserComposantesInf() {
    	
    	// Création du bouton pour choisir la disponibilité de l'infirmier.
    	disponibilite = new JCheckBox("Disponible");
    	
    	// Met le bouton déjà coché par défaut.
    	disponibilite.setSelected(true);
    	
    	// Ajout du bouton au panneau présentement utilisé.
    	add(disponibilite);
    	
    }
    
    /** 
     * Retourne la disponibilité choisisse 
     * (si la boîte est coché = true, sinon c'est false).
     * 
     * @return boolean 
     * 		   
     */
    public boolean getDisponibilite() {
    	
    	// Retourne l'état de la case à cocher.
	    return disponibilite.isSelected();
		
	}
    
    /** 
     * Retourne un nouveau infirmier possédant l'identification contenu 
     * dans la classe mère et sa disponibilite dans ce panneau
     * et ce sans validation.
     * 
     * @return Infirmier 
     * 		   
     */
    public Infirmier getParticipant() {
    	
    	// Création du participant grâce à la méthode
    	// dans la classe mère.
    	Infirmier infirmier = new Infirmier(
    			this.getIdentification(), 
    			getDisponibilite());
    	
	    return infirmier;
		
	}
    
    /**
     * Efface le texte des champs de saisie en 
     * le remplaçant par une chaîne vide en appelant la 
     * méthode 'reset' du parent et remet la case à cocher à true.
     * 
     */
    @Override
    public void reset() {
        
    	// Appel de la méthode 'reset' de la classe mère.
    	super.reset();
    	
    	// Met la case à cocher à true
    	disponibilite.setSelected(true);
    	
    }
    
}