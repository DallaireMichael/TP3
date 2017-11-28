package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
/**
 * � vous de commenter
 * 
 * @author Micha�l Dallaire
 * @version 1.0
 *
 */
public class MonMenuBar extends JMenuBar{

	/**
	 * Strat�gie : Utiliser JMenuBar de Swing pour cr�er les onglets
	 * Ne devrait pas prendre de sous m�thodes
	 *
	 * � l'exception qu'une sous m�thode d�clenche les sous m�thodes de clinique
	 * de fa�on similaire
	 */
    
    //CONSTANTES
    
    //Texte pour la barre de menu Gestion
    public final static String GESTION_MENU_TEXTE = "Gestion";
    
    //Liste des participants d'une clinique � ajouter au menu
    public final static String[] TAB_MENU_ITEM_TEXT = new String[] 
            {"Docteur", "Patient", "Infirmier"};
    
    //Une des options est de quitter l'application
    public final static String GESTION_MENU_ITEM_QUITTER = "Quitter";
    
    //VARIABLES PRIV�ES
    
    //La partie affich�e du menu qui permet d'acc�der � la liste
    private JMenu gestionMenu;
    
    
	public MonMenuBar(CadreClinique cadreClinique) {
		
	    /**
	     * Inspir� de l'exemple de :
	     * https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html#create
	     */
	    
	    gestionMenu = new JMenu(GESTION_MENU_TEXTE);
	    
	    add(gestionMenu);
	    
	    creeListeMenuItem(gestionMenu);
	    
	    JMenuItem menuItemQuitter = new JMenuItem(GESTION_MENU_ITEM_QUITTER);
	    
	    gestionMenu.add(menuItemQuitter);
	}
	
	/**
	 * Re�oit un menu et lui ajoute tous les items pour cr�er la liste
	 * @param gestionMenu
	 */
	public void creeListeMenuItem(JMenu gestionMenu){
	    
	    /**
	     * Strat�gie : Une boucle passe � travers le tableau de texte 
	     * cr�e un objet JMenuItem pour chaque et l'ajoute a gestionMenu
	     */
	    
	    //Permet de cr�er un JMenuItem pour chaque onglet voulu
	    JMenuItem tmpItem;
	    
	    //Passe � travers un tableau de cha�ne de caract�res 
	    //Pour cr�er tous les item du menu
	    for(int i = 0; i < TAB_MENU_ITEM_TEXT.length; i++){
	        
	        tmpItem = new JMenuItem(TAB_MENU_ITEM_TEXT[i]);
	        
	        gestionMenu.add(tmpItem);
	        
	    }
	    
	}

}
