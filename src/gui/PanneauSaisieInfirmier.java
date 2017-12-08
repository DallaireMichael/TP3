package gui;

import javax.swing.JCheckBox;

import clinique.Infirmier;

/**
 * Cette classe s'occupe de g�rer la saisie des informations 
 * sp�cifique aux infirmiers. La classe h�rite de PanneauSaisieParticipant,
 * donc nous avons acc�s aux m�thodes de son parent. De plus, un infirmier 
 * a une case � cocher afin de d�finir sa disponibilit�.
 * 
 * @author	Niko Girardelli
 * @version	Automne 2017
 *
 */
public class PanneauSaisieInfirmier extends PanneauSaisieParticipant {


	/** ATTRIBUTS **/
    private static final long serialVersionUID = 1L;
    
    // Bouton � cocher dans l'interface pour la disponibilit�.
    private JCheckBox disponibilite;

    /**
     * S'occupe de g�n�rer l'interface pour 
     * la saisie d'informations pour un patient.
     * 
     */
    public PanneauSaisieInfirmier() {
    	
    	// Appel du constructeur m�re.
    	super();
    	
    	initialiserComposantesInf();

    }
    
    /** 
     * S'occupe d'initialiser toutes les composantes 
     * du panneau de saisie pour l'interface avec les infirmiers.
     * On cr�e un bouton � coch�, on le met coch� et on l'ajoute au panneau.
     * 
     * @return void
     */
    public void initialiserComposantesInf() {
    	
    	disponibilite = new JCheckBox("Disponible");
    	
    	disponibilite.setSelected(true);

    	add(disponibilite);
    	
    }
    
    /** 
     * Retourne la disponibilit� choisisse 
     * (si la bo�te est coch� = true, sinon c'est false).
     * 
     * @return boolean 
     * 		   Retourne l'�tat de la case � cocher.
     */
    public boolean getDisponibilite() {
    	
	    return disponibilite.isSelected();
		
	}
    
    /** 
     * Retourne un nouveau infirmier poss�dant l'identification contenu 
     * dans la classe m�re et sa disponibilite dans ce panneau
     * et ce sans validation.
     * 
     * @return Infirmier 
     * 		   Un infirmier avec le nom, pr�nom et sa disponibilit�.
     */
    public Infirmier getParticipant() {
    	
	    return new Infirmier(this.getIdentification(), getDisponibilite());
		
	}
    
    /**
     * Efface le texte des champs de saisie en 
     * le rempla�ant par une cha�ne vide en appelant la 
     * m�thode 'reset' du parent et remet la case � cocher � true.
     * 
     */
    @Override
    public void reset() {
        
    	super.reset();
    	
    	disponibilite.setSelected(true);
    	
    }
    
}