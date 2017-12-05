package gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clinique.Identification;
import clinique.Participant;

/**
 * Classe mère des tous les panneaux utiles pour la clinique.
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
     * Affiche deux champ de saisie qui permettent de définir
     * un participant par son nom et son prénom.
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

    	// On crée nos panneaux, nos étiquettes et champs de saisie.
    	etiquetteNom = new JLabel("Nom");
    	etiquettePrenom = new JLabel("Prénom");
    	champSaisieNom = new JTextField();
    	champSaisiePrenom = new JTextField();
    	panneauNom = new JPanel();
    	panneauPrenom = new JPanel();
    	
    	// Ajout des écouteur d'évènements sur les champs de saisie.
    	//champSaisieNom.addActionListener(new champTexteEcouteur());
    	//champSaisiePrenom.addActionListener(new champTexteEcouteur());
    	
    	// Ajustement de la longeur des champs de saisie.
    	champSaisieNom.setPreferredSize(dimensionChampSaisie);
    	champSaisiePrenom.setPreferredSize(dimensionChampSaisie);
    	
    	// Ajout du champs de saisie du nom et son étiquette au panneau de nom.
    	panneauNom.add(etiquetteNom);
    	panneauNom.add(champSaisieNom);
    	
    	// Ajout du champs de saisie du prénom et son étiquette au panneau de prénom.
    	panneauPrenom.add(etiquettePrenom);
    	panneauPrenom.add(champSaisiePrenom);
    	
    	// Ajout les panneaux de nom et celui de prénom au panneau de saisie
    	// d'un participant.
    	add(panneauNom);
    	add(panneauPrenom);
    	
    }
    
    /**
     * S'occupe de retourne une nouvelle 'Identification' 
     * possédant le nom et le prénom contenu dans les boîtes 
     * de textes de ce panneaus.
     * 
     * @return Identification nouvelleId
     */
    public Identification getIdentification() {
        
    	// Création du nouvel identifiant.
    	Identification nouvelleId = new Identification(
    			champSaisieNom.getText(), 
    			champSaisiePrenom.getText());
    	
    	return nouvelleId;
    	
    }
    
    /**
     * S'occupe de retourne une nouvelle 'Participant' 
     * possédant l'identificant contenu dans les boîtes 
     * de textes de ce panneau même si les champs de saisie sont vides.
     * 
     * @return Participant nouveauParticipant
     */
    @Override
    public Participant getParticipant() {
        
    	// Création du nouvel identifiant grâce à getIdentification.
    	Participant nouveauParticipant = new Participant(getIdentification());
    	
    	return nouveauParticipant;
    	
    }

    /**
     * S'occupe de vérifier si un des champs de saisie ne contient 
     * pas de texte (ou bien les deux sont vides). Si c'est le cas, 
     * on retourne true et un message d'erreur, sinon on retourne false.
     * 
     */
    @Override
    public boolean aviserDuneErreur() {
    	
    	// Vérifie si un des champs ne contient pas de mots.
    	if(champSaisieNom.getText() == "" || champSaisiePrenom.getText() == "") {
    		
    		// Affiche un message d'erreur et retourne true.
    		System.out.print("Les champs ne doivent pas être vides.");
    		return true;
    		
    	}
    	
    	// Si les champs ne sont pas vides.
    	else {
    		
    		return false;
    		
    	}
    	
    }

    /**
     * Efface le texte des champs de saisie en 
     * le remplaçant par une chaîne vide.
     * 
     */
    @Override
    public void reset() {
        
    	champSaisieNom.setText("");
    	champSaisiePrenom.setText("");
    	
    }
   
    /**
     * Une classe qui s'occupe d'écouter l'évènement du 
     * clique sur les items du menu. 
     *
     */
    /*
	private class champTexteEcouteur implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			// On vérifie quel item du menu vient d'être appuyé afin de 
			// déclencher l'évènement du clique. Selon l'item choisi, on
			// appelle le sous-programme qui s'occupe d'afficher la fenêtre 
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
