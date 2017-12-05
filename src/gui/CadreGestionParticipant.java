package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import clinique.Clinique;
import clinique.Participant;

/**
 * Une interface graphique qui h�rite par extension de JDialog.
 * L'interface va servir � g�rer les Participants de la clinique.
 * 
 * @author Niko Girardelli
 * @version Automne 2017
 *
 */
public class CadreGestionParticipant extends JDialog {

	/** ATTRIBUTS **/
    private static final long serialVersionUID = 1L;
    
    // Composantes SWING pour l'interface la liste 
    // des participants selon sa cat�gorie.
    private JPanel panneauPrincipal;
    private JScrollPane listeDeroulante;
    private JTable tableDonnees;
    private JPanel panneauBas;
    private JPanel panneauGestion;
    private JButton boutonAjout;
    private JButton boutonSupprimer;
    
    // R�f�rence de la classe Clinique.
    private Clinique clinique;
    
    // Liste de participants permettant de modifier l'interface affich�e.
    private List<Participant> listeParticipant;
    
    // R�f�rence classe PanneauSaisie.
    private PanneauSaisieParticipant panneauSaisie;
    
    

    /**
     * Copie les r�f�rences pour ses attributs de la clinique, du panneau
     * de saisie et de ka liste des participants. Par la suite, on rends
     * le cadre modal et modifiant le comportement du clique sur le 'X'.
     * Et on initialise les composantes du cadre en appelant le sous-programme
     * InitialiserComposantes() avec la position et la dimension du cadre
     * voulu.
     * 
     * Interdit de modifier l'ent�te formel.
     */
    public CadreGestionParticipant(Clinique clinique, 
    		InterfacePanSaisieParticipant panneauSaisie,
    		List<Participant> listeParticipant, 
    		Point position, 
    		Dimension dimCadre)
    {
    	super();
    	this.clinique = clinique;
    	this.panneauSaisie = (PanneauSaisieParticipant) panneauSaisie;
    	this.listeParticipant = listeParticipant;
    	
    	// Rends le cadre modal.
    	setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    	
    	// Modifie le comportement du 'X' lors d'un clic.
    	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	
    	// Initialisation des composantes.
    	initialiserComposantes(position, dimCadre);
    	
    }
    
    /** 
     * S'occupe d'initialiser tous les composantes du cadre.
     * 
     * @param Point position
     * 		  Pour positioner le cadre.
     * 
     * @param Dimension dimCadre,
     * 		  Pour affecter les dimensions par d�faut du cadre.
     * 
     * @return void
     */
    public void initialiserComposantes(Point position, Dimension dimCadre) {
    	
    	// Utilise UtilitaireSwing pour convertir la liste � afficher
    	// en une table de donn�es, on doit convertir en array le param�tre.
    	tableDonnees = UtilitaireSwing.obtenirListe_A_Afficher(
    			listeParticipant.toArray());
    	
    	// Cr�ation de la liste d�roulante.
    	listeDeroulante = new JScrollPane(tableDonnees);
    	
    	// S'occupe de v�rifier si on affiche le panneau de saisie ou 
    	// on affiche la liste des participants.
    	afficherPanneauSaisieOuListeDeroulante();

    	// On cr�e nos panneaux et nos boutons.
    	panneauBas = new JPanel();
    	panneauGestion = new JPanel();
    	panneauPrincipal = new JPanel();
    	boutonAjout = new JButton("Ajouter");
    	boutonSupprimer = new JButton("Supprimer");
    	
    	// Ajout des �couteurs sur le bouton d'ajout et celui de suppression.
    	boutonAjout.addActionListener(new EcouteBouton());
    	boutonSupprimer.addActionListener(new EcouteBouton());
    	
    	// Ajout des boutons au panneau de gestion.
    	panneauGestion.add(boutonAjout);
    	panneauGestion.add(boutonSupprimer);
    	
    	// Ajout du panneau de gestion dans le panneau du bas.
    	// Et modifie son layout pour un CardLayout().
    	panneauBas.add(panneauGestion);
    	panneauBas.setLayout(new CardLayout());
    	
    	// Association du panneau principal au panneau de composante cadre.
    	// Ajout de la liste d�roulante et le panneauBas dans le 
    	// panneauPrincipal et modification du BorderLayout du panneauBas.
    	add(panneauPrincipal);
    	panneauPrincipal.add(listeDeroulante);
    	panneauPrincipal.add(panneauBas, BorderLayout.PAGE_END);
    	
    	// Ajout du panneu de saisie avec BorderLayout.PAGE_START.
    	add(panneauSaisie, BorderLayout.PAGE_START);
    	
    	// Ajuster la position et la dimension du cadre.
    	setLocation(position);
    	setSize(dimCadre);
    	
    	// Met le cadre visible.
    	setVisible(true);
    }
    
    /**
     * Cette m�thode s'occupe d'afficher le panneau de saisie si nous 
     * n'avions pas une liste de docteurs, d'infirmiers ou de patients.
     * Sinon elle affiche la liste. 
     *
     */
    private void afficherPanneauSaisieOuListeDeroulante() {
		
		// V�rifie si notre liste est vide.
    	if(listeParticipant.size() == 0) {
    		
    		// On rend le panneau de saisie visible 
    		// et la liste d�roulante invisible.
    		listeDeroulante.setVisible(false);
    		panneauSaisie.setVisible(true);
    		
    	}
    	
    	else {
    		
    		// On rend le panneau de saisie invisible 
    		// et la liste d�roulante visible.
    		listeDeroulante.setVisible(true);
    		panneauSaisie.setVisible(false);
    		
    	}
    	
	}

	/**
     * Une classe qui s'occupe d'�couter l'�v�nement du 
     * clique sur les boutons. Si on choisi le bouton 
     * d'ajout, on appelle le sous-programme 
     * "passerModeAjout", sinon on appelle le sous-programme
     * "supprimerSelections" lorsqu'on clique sur le bouton supprimer.
     *
     */
	private class EcouteBouton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			// On v�rifie quel bouton qui vient de d�clencher l'�v�nement 
			// du clique. Selon le bouton choisi, on appelle le
			// sous-programme d'ajout ou celui de suppression.
			if(e.getSource() == boutonAjout)
				passerModeAjout();
			else
				supprimerSelections();
			
		}
		
		/**
	     * COMING SOOON
	     *
	     */
		private void supprimerSelections() {
			// TODO Auto-generated method stub
			System.out.println("Supprime");
		}
		
		/**
	     * COMING SOOON
	     *
	     */
		private void passerModeAjout() {
			// TODO Auto-generated method stub
			System.out.println("Ajoute");
		}
		
	}
    
}
