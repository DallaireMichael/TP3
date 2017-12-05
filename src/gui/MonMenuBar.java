package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
/**
 * À vous de commenter
 * 
 * @author Michaël Dallaire
 * @version 1.0
 *
 */
public class MonMenuBar extends JMenuBar implements ActionListener{

	/**
	 * Stratégie : Utiliser JMenuBar de Swing pour créer les onglets
	 * Ne devrait pas prendre de sous méthodes
	 *
	 * À l'exception qu'une sous méthode déclenche les sous méthodes de clinique
	 * de façon similaire
	 */
    
    /*
     * CONSTANTES
     */
    
    //Texte pour la barre de menu Gestion
    public final static String GESTION_MENU_TEXTE = "Gestion";
    
    //Liste des participants d'une clinique à ajouter au menu
    public final static String[] TAB_MENU_ITEM_TEXT = new String[] 
            {"Docteur", "Patient", "Infirmier"};
    
    //Une des options est de quitter l'application
    public final static String GESTION_MENU_ITEM_QUITTER = "Quitter";
    
    
    /*
     * VARIABLES PRIVÉES
     */
    
    //La partie affichée du menu qui permet d'accéder à la liste
    private JMenu gestionMenu;
    
    //Sauvegarde du cadre de clinique
    private CadreClinique cadreClinique;
    
    
    /**
     * CONSTRUCTEUR
     * @param cadreClinique
     */
	public MonMenuBar(CadreClinique cadreClinique) {
		
	    /**
	     * Inspiré de l'exemple de :
	     * https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html#create
	     */
	    this.cadreClinique = cadreClinique;
	    
	    gestionMenu = new JMenu(GESTION_MENU_TEXTE);
	    
	    add(gestionMenu);
	    
	    creeListeMenuItem(gestionMenu);
	    
	    JMenuItem menuItemQuitter = new JMenuItem(GESTION_MENU_ITEM_QUITTER);
	    
	    menuItemQuitter.addActionListener(this);
	    
	    gestionMenu.add(menuItemQuitter);
	}
	
	/**
	 * Reçoit un menu et lui ajoute tous les items pour créer la liste
	 * @param gestionMenu
	 */
	public void creeListeMenuItem(JMenu gestionMenu){
	    
	    /**
	     * Stratégie : Une boucle passe à travers le tableau de texte 
	     * crée un objet JMenuItem pour chaque et l'ajoute a gestionMenu
	     */
	    
	    //Permet de créer un JMenuItem pour chaque onglet voulu
	    JMenuItem tmpItem;
	    
	    //Passe à travers un tableau de chaîne de caractères 
	    //Pour créer tous les item du menu
	    for(int i = 0; i < TAB_MENU_ITEM_TEXT.length; i++){
	        
	        tmpItem = new JMenuItem(TAB_MENU_ITEM_TEXT[i]);
	        
	        tmpItem.addActionListener(this);
	        
	        gestionMenu.add(tmpItem);
	        
	    }
	    
	}
	
	//Gestion des actions a activer lors de la selection dans le menu
	public void actionPerformed(ActionEvent e){
	    
	    //Vérifie lequel des items du menu a été sélectionné
	    if(((AbstractButton) e.getSource()).getText() == TAB_MENU_ITEM_TEXT[0]){
	        
	         cadreClinique.gererDocteur();
	         
	    }else if(((AbstractButton) e.getSource()).getText() ==
	                TAB_MENU_ITEM_TEXT[1]){
	            
	            cadreClinique.gererPatient();
	            
	        }else if(((AbstractButton) e.getSource()).getText() ==
	                    TAB_MENU_ITEM_TEXT[2]){
	                
	                cadreClinique.gererInfirmier();
	                
	            }else{
	                
	                System.exit(0);
	                
	            }
	 }
	    
}


