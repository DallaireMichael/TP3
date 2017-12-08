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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clinique.Clinique;
import clinique.Participant;
import utilitaire.Constantes;
import utilitaire.UtilitaireFichier;

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

    // La liste d�roulante qui montre les participants de la clinique.
    private JScrollPane listeDeroulante;
    
    // La table de donn�es contenant les participants.
    private JTable tableDonnees;
    
    // Le panneau qui s'occupe d'interchanger les boutons selon 
    // l'interface de saisie ou celle de gestion.
    private JPanel panneauBas;
    
    // Le panneau qui s'occupe des boutons au bas de la fen�tre.
    private JPanel panneauGestion;
    
    // Le panneau qui contient tous les panneaux de l'interface.
    // Il sera ajout� au panneau de ce cadre.
    private JPanel panneauPrincipal;
    
    // Les boutons "Ajouter" et "Supprimer"
    private JButton boutonAjout;
    private JButton boutonSupprimer;
    
    // Les boutons "Ok" et "Annule".
	private JButton boutonOk;
	private JButton boutonAnnule;
    
    // R�f�rence de la classe Clinique.
    private Clinique clinique;
    
    // Liste de participants permettant de modifier l'interface affich�e.
    private List<Participant> listeParticipant;
    
    // R�f�rence classe PanneauSaisie.
    private PanneauSaisieParticipant panneauSaisie;
    
    // R�f�rence de la sous-classe PresenterBouton.
    private PanneauBoutonsSaisie panneauBoutonsSaisie;
    
    

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
     * S'occupe d'initialiser toutes les composantes du panneau cadre.
     * On utilise UtilitaireSwing pour convertir la liste � afficher
     * en une table de donn�es. Par la suite, on ajoute la table de 
     * donn�es � notre liste d�roulante. On ajoute un �couteur d'�v�nement
     * � nos boutons, ce qui permettra de changer l'interface.
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
    	
    	// Cr�ation de la table de donn�es avec notre liste de participants.
    	tableDonnees = UtilitaireSwing.obtenirListe_A_Afficher(
    			listeParticipant.toArray());
    	
    	// Cr�ation de la liste d�roulante avec notre table de donn�es.
    	listeDeroulante = new JScrollPane(tableDonnees);

    	// S'occupe de cr�er nos panneaux.
    	creationPanneaux();
    	
    	// S'occupee de cr�er nos boutons.
    	creationBoutons();
    	
    	// S'occupe de v�rifier si on affiche le panneau de saisie ou 
    	// on affiche la liste des participants.
    	afficherPanneauSaisieOuListeDeroulante();
    	
    	// Ajustement de la position du cadre.
    	setLocation(position);
    	
    	// Ajustement de la dimension du cadre.
    	setSize(dimCadre);
    	
    	// Met le cadre visible.
    	setVisible(true);
    }
    
    /**
     * Cette m�thode s'occupe de g�n�rer les boutons "Ajout" 
     * et "Supprimer"
     *
     */
    private void creationBoutons() {

    	// On cr�e nos boutons.
    	boutonAjout = new JButton("Ajouter");
    	boutonSupprimer = new JButton("Supprimer");
    	
    	// Ajout de l'�couteur d'�v�nement sur les boutons.
    	boutonAjout.addActionListener(new EcouteBouton());
    	boutonSupprimer.addActionListener(new EcouteBouton());
    	
    	// Ajout des boutons au panneau de gestion.
    	panneauGestion.add(boutonAjout);
    	panneauGestion.add(boutonSupprimer);
		
	}

	/**
     * Cette m�thode s'occupe de g�n�rer les panneaux d'affichage 
     * qui serviront � notre interface de getion d'un participant.
     * On verra une liste d�roulante contenant notre participant selon 
     * leur r�le dans la clinique (docteur, infirmier et patient) avec 
     * deux boutons pour ajouter ou supprimer quelqu'un de la liste.
     * Sinon on peut afficher l'interface pour ajouter un participant. 
     *
     */
    private void creationPanneaux() {
		
    	// Cr�ation des panneaux.
    	panneauBas = new JPanel();
    	panneauGestion = new JPanel();
    	panneauPrincipal = new JPanel();
    	
    	// Modifie le layout du panneau du bas pour un CardLayout().
    	panneauBas.setLayout(new CardLayout());
    	
    	// Ajoute le panneau de gestion dans le panneau du bas.
    	panneauBas.add(panneauGestion);
    	
    	// Ajout de la sous-classe BoutonsSaisie au panneauBas.
    	panneauBoutonsSaisie = new PanneauBoutonsSaisie();
    	panneauBas.add(panneauBoutonsSaisie);
    	
    	// Association du panneau principal au panneau de composante cadre.
    	add(panneauPrincipal);
    	
    	// Ajout de la liste d�roulante et le panneauBas dans le 
    	// panneauPrincipal et modification du BorderLayout du panneauBas.
    	panneauPrincipal.add(listeDeroulante);
    	panneauPrincipal.add(panneauBas, BorderLayout.PAGE_END);
    	
    	// Ajout du panneu de saisie avec BorderLayout.PAGE_START.
    	add(panneauSaisie, BorderLayout.PAGE_START);
	}

	/**
     * Cette m�thode s'occupe d'afficher le panneau de saisie si on
     * n'a pas d'�l�ment dans notre liste de participant, sinon 
     * on affiche la liste d�roulante si nous avions des 
     * participants dans notre liste voulu.
     *
     */
    private void  afficherPanneauSaisieOuListeDeroulante() {
		
    	if(listeParticipant.isEmpty()) afficherPanneauSaisie();
    	
    	else afficherListeDeroulante();

	}
    
    /**
     * Cette m�thode s'occupe d'afficher le panneau de saisie
     * en le mettant visible et la liste d�roulante invisible.
     * De plus, on affiche les boutons "Ok" et "Annule".
     *
     */
    private void afficherPanneauSaisie() {
		
		listeDeroulante.setVisible(false);
		
		panneauSaisie.setVisible(true);
		
		((CardLayout) panneauBas.getLayout()).last(panneauBas);
		
	}
    
    /**
     * Cette m�thode s'occupe d'afficher la liste d�roulante
     * en rendant le panneau de saisie invisible et 
     * en affichant les boutons "Ajouter" et "Supprimer".
     *
     */
    private void afficherListeDeroulante() {
	
		listeDeroulante.setVisible(true);
		
		panneauSaisie.setVisible(false);
		
		((CardLayout) panneauBas.getLayout()).first(panneauBas);
    	
	}
    
    /**
     * Cette m�thode s'occupe d'ajuster la JTable apr�s l'ajout
     * d'un participant dans la liste. On supprime et recr�e une JTable.
     * Et on rafra�chit l'interface par la suite.
     */
    private void miseAJourTableDonnees() {
		
    	listeDeroulante.remove(tableDonnees);
    	
    	tableDonnees = UtilitaireSwing.obtenirListe_A_Afficher(
    			listeParticipant.toArray());
    	
    	listeDeroulante.setViewportView(tableDonnees);
    	
		UtilitaireSwing.rafraichirCadre(panneauPrincipal);
    	
	}
    
    /**
     * Cette sous-classe priv�e sert � afficher les bons boutons 
     * pour l'interface de saisie. On voit les boutons "Ok" et "Annule"
     * et le panneau sera impl�ment� dans le panneau du bas.
     * 
     */
    private class PanneauBoutonsSaisie extends JPanel {
    	
    	/** ATTRIBUT **/
		private static final long serialVersionUID = 1L;
    	
    	/**
    	 * S'occupe d'instancier les boutons "Ok" et "Annule"
    	 * et de l'ajouter au PanneauBoutonsSaisie.
    	 * 
    	 */
    	public PanneauBoutonsSaisie() {
    		
    		// On cr�e nos panneaux et nos boutons.
        	boutonOk = new JButton("Ok");
        	boutonAnnule = new JButton("Annule");
        	
        	// Ajout des �couteurs sur le bouton d'ajout
        	// et celui de suppression.
        	boutonOk.addActionListener(new EcouteBouton());
        	boutonAnnule.addActionListener(new EcouteBouton());
        	
        	// Ajout des boutons au panneau de gestion.
        	add(boutonOk);
        	add(boutonAnnule);
    		
    	}
    	
	}

	/**
     * Une sous-classe qui s'occupe d'�couter l'�v�nement du 
     * clique sur les boutons sur les boutons "Ajout", 
     * "Supprime", "Ok" et "Annule". Selon le bouton choisi,
     * on effectue diff�rente action d�crite dans les commentaires 
     * de ces sous-programmes. 
     *
     */
	private class EcouteBouton implements ActionListener {
		
		// On v�rifie quel bouton qui vient de d�clencher l'�v�nement 
		// du clique.
		public void actionPerformed(ActionEvent e) {
		
			// Si le bouton est "Ajouter", on affiche l'interface de saisie.
			if(e.getSource() == boutonAjout) passerAuModeAjout();
			
			// Si le bouton est "Annule", on affiche l'interface de gestion.
			else if(e.getSource() == boutonAnnule) annuler();
			
			// Si le bouton est "Ok", on ajoute le participant que 
			// nous venons de cr�er s'il est valide.
			else  if(e.getSource() == boutonOk) ajouterSiValide();
			
			// Si le bouton est "Supprimer", on supprimer 
			// les participants s�lectionn�s de la clinique.
			else supprimerSelections();
			
		}

		/**
	     * R�cup�re les indices des lignes s�lectionn�es � partir 
	     * de la table participant (multi-s�letion) et 
	     * retire le participant de la liste � afficher. 
	     * Le participant n'est disponible dans la table.
	     *
	     */
		private void supprimerSelections() {
			
			// Si aucune s�lection est faite
			if(tableDonnees.getSelectedRowCount() == 0) {
				
				JOptionPane.showMessageDialog(CadreGestionParticipant.this,
						Constantes.MSG_SELECT_AUCUNE);
				
			}
			
			// R�cup�re les indices des lignes s�lectionn�es gr�ce � la m�thode
			// "getSelectedRowCount()" qui retourne le nombre de rang�e de 
			// s�lectionn�es.
			for(int i = 0; i < tableDonnees.getSelectedRowCount(); i++) {
				
				// Gr�ce � la m�thode "getSelectedRow()", on supprime le 
				// premier �l�ment des rang�es voulu.
				((DefaultTableModel)tableDonnees.getModel()).getDataVector().
				remove(tableDonnees.getSelectedRow());
				
				// Supprime les participants s�lectionn�es de la 
				// liste des participants.
				listeParticipant.remove(tableDonnees.getSelectedRow());

			}
			
			// S'occupe de sauvergarder les modifications.
			UtilitaireFichier.sauvegarderClinique(clinique, Constantes.CHEMIN_FICHIER);
			
			// Si la liste est maintenant vide, on passe au mode ajout.
			if(tableDonnees.getRowCount() == 0) afficherPanneauSaisie();
			
			// On rafra�chit l'affichage si notre liste n'est pas vide.
			else {
				
				// Permet de rafra�chir le panneau cadre.
				UtilitaireSwing.rafraichirCadre(panneauPrincipal);
				
				// Enl�ve les s�lections de la liste d�roulante.
				tableDonnees.clearSelection();
				
			}
			
		}
		
		/**
	     * Rend la liste d�roulante invisible et rend 
	     * le panneau de saisie visible en affichant 
	     * les boutons pour l'interface de saisie ("Ok" et "Annule")
	     * et en rafra�chissant le panneau principal.
	     *
	     */
		private void passerAuModeAjout() {
			
			afficherPanneauSaisie();
			
			UtilitaireSwing.rafraichirCadre(panneauPrincipal);
			
		}
		
		/**
		 * Lorsqu'on clique sur le bouton "Annule":
	     * Rend le panneau de saisie invisible et rend 
	     * la liste d�roulante visible en affichant 
	     * les boutons pour l'interface de gestion ("Ajouter" et "Supprimer")
	     * 
	     *
	     */
		private void annuler() {
			
			afficherListeDeroulante();
			
		}
		
		/**
	     * V�rifie si les informations du participant r�ponds aux 
	     * exigences, donc qu'il n'y a pas d'erreur. Si aucune erreur est 
	     * trouv�, on ajoute la participant � la liste � afficher, la clinique 
	     * est sauvegard�e et on mets � jour l'interface. C'est-�-dire qu'on 
	     * remet les champs de saisie vides et qu'on rafra�chit l'interface.
	     *
	     */
		private void ajouterSiValide() {
			
			/** ATTRIBUT **/
			// R�f�rence sur un objet de type Participant.
			Participant nouveauParticipant;
			
			// V�rifie si aucune erreur est pr�sente dans 
			// l'identification du participant.
			if(!panneauSaisie.aviserDuneErreur()) {
				
				// Retourne le participant que nous venons d'�crire.
				nouveauParticipant = panneauSaisie.getParticipant();
				
				// V�rifie si le participant est d�j� pr�sent dans liste
				// des participants.
				if(listeParticipant.contains(nouveauParticipant)) {
					
					JOptionPane.showMessageDialog(CadreGestionParticipant.this,
							Constantes.MSG_PAT_DEJA_PRESENT);

				}
				
				// Si le participant n'est pas d�j� dans la liste.
				else {
					
					// Ajout du participant � liste.
					listeParticipant.add(nouveauParticipant);
					
					// Sauvegarde de la clinique.
					UtilitaireFichier.sauvegarderClinique(clinique, 
							Constantes.CHEMIN_FICHIER);
					
					// Mise � jour de la table dans la liste d�roulante.
					miseAJourTableDonnees();
					
					// Met les champs de saisie vide 
					panneauSaisie.reset();
					
					UtilitaireSwing.rafraichirCadre(panneauPrincipal);
					
				}
				
			}
			
		}
		
	}
    
}
