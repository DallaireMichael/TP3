package gui;

import javax.swing.JCheckBox;

import clinique.Infirmier;

/**
 * Cette classe s'occupe de gérer la saisie des informations 
 * spécifique aux infirmiers. La classe hérite de PanneauSaisieParticipant,
 * donc nous avons accès aux méthodes de son parent. De plus, un infirmier 
 * a une case à cocher afin de définir sa disponibilité.
 * 
 * @author	Niko Girardelli
 * @version	Automne 2017
 *
 */
public class PanneauSaisieInfirmier extends PanneauSaisieParticipant {


	/** ATTRIBUTS **/
    private static final long serialVersionUID = 1L;
    
    // Bouton à cocher dans l'interface pour la disponibilité.
    private JCheckBox disponibilite;

    /**
     * S'occupe de générer l'interface pour 
     * la saisie d'informations pour un patient.
     * 
     */
    public PanneauSaisieInfirmier() {
    	
    	// Appel du constructeur mère.
    	super();
    	
    	initialiserComposantesInf();

    }
    
    /** 
     * S'occupe d'initialiser toutes les composantes 
     * du panneau de saisie pour l'interface avec les infirmiers.
     * On crée un bouton à coché, on le met coché et on l'ajoute au panneau.
     * 
     * @return void
     */
    public void initialiserComposantesInf() {
    	
    	disponibilite = new JCheckBox("Disponible");
    	
    	disponibilite.setSelected(true);

    	add(disponibilite);
    	
    }
    
    /** 
     * Retourne la disponibilité choisisse 
     * (si la boîte est coché = true, sinon c'est false).
     * 
     * @return boolean 
     * 		   Retourne l'état de la case à cocher.
     */
    public boolean getDisponibilite() {
    	
	    return disponibilite.isSelected();
		
	}
    
    /** 
     * Retourne un nouveau infirmier possédant l'identification contenu 
     * dans la classe mère et sa disponibilite dans ce panneau
     * et ce sans validation.
     * 
     * @return Infirmier 
     * 		   Un infirmier avec le nom, prénom et sa disponibilité.
     */
    public Infirmier getParticipant() {
    	
	    return new Infirmier(this.getIdentification(), getDisponibilite());
		
	}
    
    /**
     * Efface le texte des champs de saisie en 
     * le remplaçant par une chaîne vide en appelant la 
     * méthode 'reset' du parent et remet la case à cocher à true.
     * 
     */
    @Override
    public void reset() {
        
    	super.reset();
    	
    	disponibilite.setSelected(true);
    	
    }
    
}