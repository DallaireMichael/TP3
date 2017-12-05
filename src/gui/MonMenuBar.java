package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Classe qui permet d'afficher le menu qu'on retrouve
 * en haut � gauche de la fen�tre. On y retrouve des onglets, dont
 * Docteur, Infirmier, Patient et Quitter. 
 * 
 * @author	Niko Girardelli
 * @version Automne 2017
 *
 */
public class MonMenuBar extends JMenuBar{

	/** ATTRIBUTS **/
	private static final long serialVersionUID = 1L;
	private JMenuBar barreMenu;
	private JMenu menu;
	private JMenuItem docteurItemMenu;
	private JMenuItem infirmierItemMenu;
	private JMenuItem patientItemMenu;
	private JMenuItem quitterItemMenu;
	private CadreClinique cadreClinique;
	
	/**
	 * Permet d'initialiser le menu dans le cadre de la Clinique.
	 * Selon le choix de l'onglet choisi, on affiche la fen�tre qui 
	 * permet de g�rer les docteurs, infirmiers, patients de la clinique.
	 * On peut aussi choisir quitter pour quitter l'application.
	 * 
	 * @param CadreClinique cadreClinique
	 * 		  R�f�rence au cadre la clinique, afin d'appeler ses 
	 * 		  sous-programmes qui g�rent la gestion du personnels ou des
	 * 		  patients de la clinique.
	 */
	public MonMenuBar(CadreClinique cadreClinique) {
		
		// Copie de la r�f�rence cadreClinique � notre attibut
		// cadreClinique.
		this.cadreClinique = cadreClinique;
		
		// Appel � la m�thode qui se charge d'initialiser les composantes.
		initialiserComposantes();
		
	}
	
	/***
	 * S'occupe d'initialiser toutes les composantes 
	 * n�cessaires pour notre barre de menu dans l'application.
	 * 
	 * @return void
	 */
    private void initialiserComposantes() {
		
    	// Cr�ation de la barre de menu.
		barreMenu = new JMenuBar();
		
		// Cr�ation du menu "Gestion" et ajout dans la barre de menu.
		menu = new JMenu("Gestion");
		menu.getAccessibleContext().setAccessibleDescription(
				"Le menu qu s'occupe de la gestion de la clinique.");
		barreMenu.add(menu);
		
		//Cr�ation du premier onglet du menu : Docteur.
		docteurItemMenu = new JMenuItem("Docteur");
		docteurItemMenu.addActionListener(new itemMenuEcouteur());
		
		//Cr�ation du deuxi�me onglet du menu : Infirimier.
		infirmierItemMenu = new JMenuItem("Infirmier");
		infirmierItemMenu.addActionListener(new itemMenuEcouteur());
		
		//Cr�ation du troisi�me onglet du menu : Patient.
		patientItemMenu = new JMenuItem("Patient"); 
		patientItemMenu.addActionListener(new itemMenuEcouteur());
		
		//Cr�ation du dernier onglet du menu : Quitter.
		quitterItemMenu = new JMenuItem("Quitter");
		quitterItemMenu.addActionListener(new itemMenuEcouteur());
		
		// Ajout des items du menu au menu "Gestion".
		menu.add(docteurItemMenu);
		menu.add(infirmierItemMenu);
		menu.add(patientItemMenu);
		menu.add(quitterItemMenu);
		
		// Ajout de la barre du menu � la classe. 
		add(barreMenu);
		
	}


	/**
     * Une classe qui s'occupe d'�couter l'�v�nement du 
     * clique sur les items du menu. 
     */
	private class itemMenuEcouteur implements ActionListener {
		
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
			else	
				// Quitte application lorsqu'on choisit l'item "Quitter".
				System.exit(0);
		}
		
	}

}
