package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import utilitaire.Constantes;

/**
 * Classe qui permet d'afficher le menu qu'on retrouve
 * en haut à gauche de la fenêtre. On y retrouve les onglets, dont
 * Docteur, Infirmier, Patient et Quitter. 
 * 
 * @author	Niko Girardelli
 * @version Automne 2017
 *
 */
public class MonMenuBar extends JMenuBar{

	/** ATTRIBUTS **/
	private static final long serialVersionUID = 1L;
	
	// La barre de menu.
	private JMenuBar barreMenu;
	
	// Le menu qui sera affiché dans la barre de menu.
	private JMenu menu;
	
	// Référence de la classe CadreClinique.
	private CadreClinique cadreClinique;
	
	/**
	 * Permet d'initialiser le menu dans le cadre de la Clinique.
	 * Selon le choix de l'onglet choisi, on affiche la fenêtre qui 
	 * permet de gérer les docteurs, infirmiers, patients de la clinique.
	 * On peut aussi choisir quitter pour quitter l'application.
	 * 
	 * @param CadreClinique cadreClinique
	 * 		  Référence au cadre la clinique, afin d'appeler ses 
	 * 		  sous-programmes qui gèrent la gestion du personnels ou des
	 * 		  patients de la clinique.
	 */
	public MonMenuBar(CadreClinique cadreClinique) {
		
		this.cadreClinique = cadreClinique;
		
		initialiserComposantes();
		
	}
	
	/***
	 * S'occupe d'initialiser toutes les composantes 
	 * nécessaires pour notre barre de menu dans l'application.
	 * 
	 * @return void
	 */
    private void initialiserComposantes() {
		
    	// Création de la barre de menu.
		barreMenu = new JMenuBar();
		
		// Création du menu "Gestion".
		menu = new JMenu("Gestion");
		
		// Ajout dans la barre de menu.
		barreMenu.add(menu);
		
		//Création des onglets du menu.
		creerItemsMenu();
		
		// Ajout de la barre du menu à la classe. 
		add(barreMenu);
		
	}
    
    /**
     * Boucle qui instancie les items du menu grâce 
     * à une constante qui est un tableau de type String 
     * contenant les noms des items pour la barre de menu.
     * On retrouve à la fin de la création, un menu contenant
     * un onglet "Docteur", "Infirmier", "Patient" et "Quitter".
     *  
     */

	public void creerItemsMenu() {
			
		for(int i = 0; i < Constantes.TAB_ITEMS_MENU.length; i++) {
			
			//Création d'un item pour le menu.
			JMenuItem itemMenu = new JMenuItem(Constantes.TAB_ITEMS_MENU[i]);
			
			// Ajout de l'écouteur d'évènement.
			itemMenu.addActionListener(new itemMenuEcouteur());
			
			// Ajout de l'item au menu "Gestion".
			menu.add(itemMenu);
			
		}
			
	}


	/**
     * Une classe qui s'occupe d'écouter l'évènement du 
     * clique sur les items du menu. 
     */
	private class itemMenuEcouteur implements ActionListener {
		
		/* On vérifie quel item du menu vient d'être appuyé afin de 
		* déclencher l'évènement du clique. Selon l'item choisi, on
		* appelle le sous-programme qui s'occupe d'afficher la fenêtre 
		* de gestion/saisie correspondante.
		*/ 
		public void actionPerformed(ActionEvent e) {
			
			// La gestion et saisie des docteurs.
			if(((AbstractButton)e.getSource()).getText() == 
					Constantes.TAB_ITEMS_MENU[0]) {
				
				cadreClinique.gererDocteur();
				
			}
			
			// La gestion et saisie des infirmiers.
			else if(((AbstractButton)e.getSource()).getText() ==
					Constantes.TAB_ITEMS_MENU[1]) {
				
				cadreClinique.gererInfirmier();
				
			}
				
			// La gestion et saisie des infirmiers.
			else if(((AbstractButton)e.getSource()).getText() == 
					Constantes.TAB_ITEMS_MENU[2]) {
				
				cadreClinique.gererPatient();
				
			}
				
			// Quitte application lorsqu'on choisit l'item "Quitter".
			else System.exit(0);

		}
		
	}

}
