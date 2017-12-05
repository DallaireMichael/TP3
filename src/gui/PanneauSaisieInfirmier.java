package gui;

import javax.swing.JCheckBox;

import clinique.Infirmier;

/**
 * Cette classe s'occupe de g�rer la saisie des informations 
 * sp�cifique aux infirmiers. La classe h�rite de PanneauSaisieParticipant,
 * donc nous avons acc�s aux m�thodes de son parent.
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
     * S'occupe de g�n�rer l'interface pour la saisie d'informations pour un patient.
     * 
     */
    public PanneauSaisieInfirmier() {
    	
    	// Appel � la m�thode qui initialise les composantes pour 
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
    	
    	// Cr�ation du bouton pour choisir la disponibilit� de l'infirmier.
    	disponibilite = new JCheckBox("Disponible");
    	
    	// Met le bouton d�j� coch� par d�faut.
    	disponibilite.setSelected(true);
    	
    	// Ajout du bouton au panneau pr�sentement utilis�.
    	add(disponibilite);
    	
    }
    
    /** 
     * Retourne la disponibilit� choisisse 
     * (si la bo�te est coch� = true, sinon c'est false).
     * 
     * @return boolean 
     * 		   
     */
    public boolean getDisponibilite() {
    	
    	// Retourne l'�tat de la case � cocher.
	    return disponibilite.isSelected();
		
	}
    
    /** 
     * Retourne un nouveau infirmier poss�dant l'identification contenu 
     * dans la classe m�re et sa disponibilite dans ce panneau
     * et ce sans validation.
     * 
     * @return Infirmier 
     * 		   
     */
    public Infirmier getParticipant() {
    	
    	// Cr�ation du participant gr�ce � la m�thode
    	// dans la classe m�re.
    	Infirmier infirmier = new Infirmier(
    			this.getIdentification(), 
    			getDisponibilite());
    	
	    return infirmier;
		
	}
    
    /**
     * Efface le texte des champs de saisie en 
     * le rempla�ant par une cha�ne vide en appelant la 
     * m�thode 'reset' du parent et remet la case � cocher � true.
     * 
     */
    @Override
    public void reset() {
        
    	// Appel de la m�thode 'reset' de la classe m�re.
    	super.reset();
    	
    	// Met la case � cocher � true
    	disponibilite.setSelected(true);
    	
    }
    
}