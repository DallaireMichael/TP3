package gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clinique.Identification;
import clinique.Participant;

/**
 * Classe m�re des tous les panneaux utiles pour la clinique.
 * Elle permet de saisir l'identification d'un participant.
 * 
 * @author	Niko Girardelli
 * @version	Automne 2017
 *
 */

public class PanneauSaisieParticipant extends JPanel 
implements InterfacePanSaisieParticipant {
	
	/** ATTRIBUTS **/
    private static final long serialVersionUID = 1L;
    private JTextField champSaisieNom;
    private JLabel etiquetteNom;
    private JTextField champSaisiePrenom;
    private JLabel etiquettePrenom;
    private JPanel panneauNom;
    private JPanel panneauPrenom;
    private Dimension dimensionChampSaisie = new Dimension(200, 24);

    /**
     * Affiche deux champ de saisie qui permettent de d�finir
     * un participant par son nom et son pr�nom.
     * 
     */
    public PanneauSaisieParticipant() {
    	
    	// Fixe la disposition de notre panneau de saisie 
    	// d'un participant en PAGE_AXIS.
    	this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	
    	// Appel le sous-programme qui initialise les composantes du panneau.
    	initialiserComposantes();
    	
    }
    
    /** 
     * S'occupe d'initialiser tous les composantes du panneau de saisie.
     * 
     * @return void
     */
    public void initialiserComposantes() {

    	// On cr�e nos panneaux, nos �tiquettes et champs de saisie.
    	etiquetteNom = new JLabel("Nom");
    	etiquettePrenom = new JLabel("Pr�nom");
    	champSaisieNom = new JTextField();
    	champSaisiePrenom = new JTextField();
    	panneauNom = new JPanel();
    	panneauPrenom = new JPanel();
    	
    	// Ajout des �couteur d'�v�nements sur les champs de saisie.
    	//champSaisieNom.addActionListener(new champTexteEcouteur());
    	//champSaisiePrenom.addActionListener(new champTexteEcouteur());
    	
    	// Ajustement de la longeur des champs de saisie.
    	champSaisieNom.setPreferredSize(dimensionChampSaisie);
    	champSaisiePrenom.setPreferredSize(dimensionChampSaisie);
    	
    	// Ajout du champs de saisie du nom et son �tiquette au panneau de nom.
    	panneauNom.add(etiquetteNom);
    	panneauNom.add(champSaisieNom);
    	
    	// Ajout du champs de saisie du pr�nom et son �tiquette au panneau de pr�nom.
    	panneauPrenom.add(etiquettePrenom);
    	panneauPrenom.add(champSaisiePrenom);
    	
    	// Ajout les panneaux de nom et celui de pr�nom au panneau de saisie
    	// d'un participant.
    	add(panneauNom);
    	add(panneauPrenom);
    	
    }
    
    /**
     * S'occupe de retourne une nouvelle 'Identification' 
     * poss�dant le nom et le pr�nom contenu dans les bo�tes 
     * de textes de ce panneaus.
     * 
     * @return Identification nouvelleId
     */
    public Identification getIdentification() {
        
    	// Cr�ation du nouvel identifiant.
    	Identification nouvelleId = new Identification(
    			champSaisieNom.getText(), 
    			champSaisiePrenom.getText());
    	
    	return nouvelleId;
    	
    }
    
    /**
     * S'occupe de retourne une nouvelle 'Participant' 
     * poss�dant l'identificant contenu dans les bo�tes 
     * de textes de ce panneau m�me si les champs de saisie sont vides.
     * 
     * @return Participant nouveauParticipant
     */
    @Override
    public Participant getParticipant() {
        
    	// Cr�ation du nouvel identifiant gr�ce � getIdentification.
    	Participant nouveauParticipant = new Participant(getIdentification());
    	
    	return nouveauParticipant;
    	
    }

    /**
     * S'occupe de v�rifier si un des champs de saisie ne contient 
     * pas de texte (ou bien les deux sont vides). Si c'est le cas, 
     * on retourne true et un message d'erreur, sinon on retourne false.
     * 
     */
    @Override
    public boolean aviserDuneErreur() {
    	
    	// V�rifie si un des champs ne contient pas de mots.
    	if(champSaisieNom.getText() == "" || champSaisiePrenom.getText() == "") {
    		
    		// Affiche un message d'erreur et retourne true.
    		System.out.print("Les champs ne doivent pas �tre vides.");
    		return true;
    		
    	}
    	
    	// Si les champs ne sont pas vides.
    	else {
    		
    		return false;
    		
    	}
    	
    }

    /**
     * Efface le texte des champs de saisie en 
     * le rempla�ant par une cha�ne vide.
     * 
     */
    @Override
    public void reset() {
        
    	champSaisieNom.setText("");
    	champSaisiePrenom.setText("");
    	
    }
   
    /**
     * Une classe qui s'occupe d'�couter l'�v�nement du 
     * clique sur les items du menu. 
     *
     */
    /*
	private class champTexteEcouteur implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			// On v�rifie quel item du menu vient d'�tre appuy� afin de 
			// d�clencher l'�v�nement du clique. Selon l'item choisi, on
			// appelle le sous-programme qui s'occupe d'afficher la fen�tre 
			// de gestion des docteurs, infirmiers, patients ou bien de
			// fermer l'application.
			if(e.getSource() == docteurItemMenu)
				cadreClinique.gererDocteur();
			if(e.getSource() == infirmierItemMenu)
				cadreClinique.gererInfirmier();
			if(e.getSource() == patientItemMenu)
				cadreClinique.gererPatient();
			//else	
				// Quitter application
	
		}
		
	}*/
}
