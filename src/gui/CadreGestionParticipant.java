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
 * Une interface graphique qui hérite par extension de JDialog.
 * L'interface va servir à gérer les Participants de la clinique.
 * 
 * @author Niko Girardelli
 * @version Automne 2017
 *
 */
public class CadreGestionParticipant extends JDialog {

	/** ATTRIBUTS **/
    private static final long serialVersionUID = 1L;

    // La liste déroulante qui montre les participants de la clinique.
    private JScrollPane listeDeroulante;
    
    // La table de données contenant les participants.
    private JTable tableDonnees;
    
    // Le panneau qui s'occupe d'interchanger les boutons selon 
    // l'interface de saisie ou celle de gestion.
    private JPanel panneauBas;
    
    // Le panneau qui s'occupe des boutons au bas de la fenêtre.
    private JPanel panneauGestion;
    
    // Le panneau qui contient tous les panneaux de l'interface.
    // Il sera ajouté au panneau de ce cadre.
    private JPanel panneauPrincipal;
    
    // Les boutons "Ajouter" et "Supprimer"
    private JButton boutonAjout;
    private JButton boutonSupprimer;
    
    // Les boutons "Ok" et "Annule".
	private JButton boutonOk;
	private JButton boutonAnnule;
    
    // Référence de la classe Clinique.
    private Clinique clinique;
    
    // Liste de participants permettant de modifier l'interface affichée.
    private List<Participant> listeParticipant;
    
    // Référence classe PanneauSaisie.
    private PanneauSaisieParticipant panneauSaisie;
    
    // Référence de la sous-classe PresenterBouton.
    private PanneauBoutonsSaisie panneauBoutonsSaisie;
    
    

    /**
     * Copie les références pour ses attributs de la clinique, du panneau
     * de saisie et de ka liste des participants. Par la suite, on rends
     * le cadre modal et modifiant le comportement du clique sur le 'X'.
     * Et on initialise les composantes du cadre en appelant le sous-programme
     * InitialiserComposantes() avec la position et la dimension du cadre
     * voulu.
     * 
     * Interdit de modifier l'entête formel.
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
     * On utilise UtilitaireSwing pour convertir la liste à afficher
     * en une table de données. Par la suite, on ajoute la table de 
     * données à notre liste déroulante. On ajoute un écouteur d'évènement
     * à nos boutons, ce qui permettra de changer l'interface.
     * 
     * @param Point position
     * 		  Pour positioner le cadre.
     * 
     * @param Dimension dimCadre,
     * 		  Pour affecter les dimensions par défaut du cadre.
     * 
     * @return void
     */
    public void initialiserComposantes(Point position, Dimension dimCadre) {
    	
    	// Création de la table de données avec notre liste de participants.
    	tableDonnees = UtilitaireSwing.obtenirListe_A_Afficher(
    			listeParticipant.toArray());
    	
    	// Création de la liste déroulante avec notre table de données.
    	listeDeroulante = new JScrollPane(tableDonnees);

    	// S'occupe de créer nos panneaux.
    	creationPanneaux();
    	
    	// S'occupee de créer nos boutons.
    	creationBoutons();
    	
    	// S'occupe de vérifier si on affiche le panneau de saisie ou 
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
     * Cette méthode s'occupe de générer les boutons "Ajout" 
     * et "Supprimer"
     *
     */
    private void creationBoutons() {

    	// On crée nos boutons.
    	boutonAjout = new JButton("Ajouter");
    	boutonSupprimer = new JButton("Supprimer");
    	
    	// Ajout de l'écouteur d'évènement sur les boutons.
    	boutonAjout.addActionListener(new EcouteBouton());
    	boutonSupprimer.addActionListener(new EcouteBouton());
    	
    	// Ajout des boutons au panneau de gestion.
    	panneauGestion.add(boutonAjout);
    	panneauGestion.add(boutonSupprimer);
		
	}

	/**
     * Cette méthode s'occupe de générer les panneaux d'affichage 
     * qui serviront à notre interface de getion d'un participant.
     * On verra une liste déroulante contenant notre participant selon 
     * leur rôle dans la clinique (docteur, infirmier et patient) avec 
     * deux boutons pour ajouter ou supprimer quelqu'un de la liste.
     * Sinon on peut afficher l'interface pour ajouter un participant. 
     *
     */
    private void creationPanneaux() {
		
    	// Création des panneaux.
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
    	
    	// Ajout de la liste déroulante et le panneauBas dans le 
    	// panneauPrincipal et modification du BorderLayout du panneauBas.
    	panneauPrincipal.add(listeDeroulante);
    	panneauPrincipal.add(panneauBas, BorderLayout.PAGE_END);
    	
    	// Ajout du panneu de saisie avec BorderLayout.PAGE_START.
    	add(panneauSaisie, BorderLayout.PAGE_START);
	}

	/**
     * Cette méthode s'occupe d'afficher le panneau de saisie si on
     * n'a pas d'élément dans notre liste de participant, sinon 
     * on affiche la liste déroulante si nous avions des 
     * participants dans notre liste voulu.
     *
     */
    private void  afficherPanneauSaisieOuListeDeroulante() {
		
    	if(listeParticipant.isEmpty()) afficherPanneauSaisie();
    	
    	else afficherListeDeroulante();

	}
    
    /**
     * Cette méthode s'occupe d'afficher le panneau de saisie
     * en le mettant visible et la liste déroulante invisible.
     * De plus, on affiche les boutons "Ok" et "Annule".
     *
     */
    private void afficherPanneauSaisie() {
		
		listeDeroulante.setVisible(false);
		
		panneauSaisie.setVisible(true);
		
		((CardLayout) panneauBas.getLayout()).last(panneauBas);
		
	}
    
    /**
     * Cette méthode s'occupe d'afficher la liste déroulante
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
     * Cette méthode s'occupe d'ajuster la JTable après l'ajout
     * d'un participant dans la liste. On supprime et recrée une JTable.
     * Et on rafraîchit l'interface par la suite.
     */
    private void miseAJourTableDonnees() {
		
    	listeDeroulante.remove(tableDonnees);
    	
    	tableDonnees = UtilitaireSwing.obtenirListe_A_Afficher(
    			listeParticipant.toArray());
    	
    	listeDeroulante.setViewportView(tableDonnees);
    	
		UtilitaireSwing.rafraichirCadre(panneauPrincipal);
    	
	}
    
    /**
     * Cette sous-classe privée sert à afficher les bons boutons 
     * pour l'interface de saisie. On voit les boutons "Ok" et "Annule"
     * et le panneau sera implémenté dans le panneau du bas.
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
    		
    		// On crée nos panneaux et nos boutons.
        	boutonOk = new JButton("Ok");
        	boutonAnnule = new JButton("Annule");
        	
        	// Ajout des écouteurs sur le bouton d'ajout
        	// et celui de suppression.
        	boutonOk.addActionListener(new EcouteBouton());
        	boutonAnnule.addActionListener(new EcouteBouton());
        	
        	// Ajout des boutons au panneau de gestion.
        	add(boutonOk);
        	add(boutonAnnule);
    		
    	}
    	
	}

	/**
     * Une sous-classe qui s'occupe d'écouter l'évènement du 
     * clique sur les boutons sur les boutons "Ajout", 
     * "Supprime", "Ok" et "Annule". Selon le bouton choisi,
     * on effectue différente action décrite dans les commentaires 
     * de ces sous-programmes. 
     *
     */
	private class EcouteBouton implements ActionListener {
		
		// On vérifie quel bouton qui vient de déclencher l'évènement 
		// du clique.
		public void actionPerformed(ActionEvent e) {
		
			// Si le bouton est "Ajouter", on affiche l'interface de saisie.
			if(e.getSource() == boutonAjout) passerAuModeAjout();
			
			// Si le bouton est "Annule", on affiche l'interface de gestion.
			else if(e.getSource() == boutonAnnule) annuler();
			
			// Si le bouton est "Ok", on ajoute le participant que 
			// nous venons de créer s'il est valide.
			else  if(e.getSource() == boutonOk) ajouterSiValide();
			
			// Si le bouton est "Supprimer", on supprimer 
			// les participants sélectionnés de la clinique.
			else supprimerSelections();
			
		}

		/**
	     * Récupère les indices des lignes sélectionnées à partir 
	     * de la table participant (multi-séletion) et 
	     * retire le participant de la liste à afficher. 
	     * Le participant n'est disponible dans la table.
	     *
	     */
		private void supprimerSelections() {
			
			// Si aucune sélection est faite
			if(tableDonnees.getSelectedRowCount() == 0) {
				
				JOptionPane.showMessageDialog(CadreGestionParticipant.this,
						Constantes.MSG_SELECT_AUCUNE);
				
			}
			
			// Récupère les indices des lignes sélectionnées grâce à la méthode
			// "getSelectedRowCount()" qui retourne le nombre de rangée de 
			// sélectionnées.
			for(int i = 0; i < tableDonnees.getSelectedRowCount(); i++) {
				
				// Grâce à la méthode "getSelectedRow()", on supprime le 
				// premier élément des rangées voulu.
				((DefaultTableModel)tableDonnees.getModel()).getDataVector().
				remove(tableDonnees.getSelectedRow());
				
				// Supprime les participants sélectionnées de la 
				// liste des participants.
				listeParticipant.remove(tableDonnees.getSelectedRow());

			}
			
			// S'occupe de sauvergarder les modifications.
			UtilitaireFichier.sauvegarderClinique(clinique, Constantes.CHEMIN_FICHIER);
			
			// Si la liste est maintenant vide, on passe au mode ajout.
			if(tableDonnees.getRowCount() == 0) afficherPanneauSaisie();
			
			// On rafraîchit l'affichage si notre liste n'est pas vide.
			else {
				
				// Permet de rafraîchir le panneau cadre.
				UtilitaireSwing.rafraichirCadre(panneauPrincipal);
				
				// Enlève les sélections de la liste déroulante.
				tableDonnees.clearSelection();
				
			}
			
		}
		
		/**
	     * Rend la liste déroulante invisible et rend 
	     * le panneau de saisie visible en affichant 
	     * les boutons pour l'interface de saisie ("Ok" et "Annule")
	     * et en rafraîchissant le panneau principal.
	     *
	     */
		private void passerAuModeAjout() {
			
			afficherPanneauSaisie();
			
			UtilitaireSwing.rafraichirCadre(panneauPrincipal);
			
		}
		
		/**
		 * Lorsqu'on clique sur le bouton "Annule":
	     * Rend le panneau de saisie invisible et rend 
	     * la liste déroulante visible en affichant 
	     * les boutons pour l'interface de gestion ("Ajouter" et "Supprimer")
	     * 
	     *
	     */
		private void annuler() {
			
			afficherListeDeroulante();
			
		}
		
		/**
	     * Vérifie si les informations du participant réponds aux 
	     * exigences, donc qu'il n'y a pas d'erreur. Si aucune erreur est 
	     * trouvé, on ajoute la participant à la liste à afficher, la clinique 
	     * est sauvegardée et on mets à jour l'interface. C'est-à-dire qu'on 
	     * remet les champs de saisie vides et qu'on rafraîchit l'interface.
	     *
	     */
		private void ajouterSiValide() {
			
			/** ATTRIBUT **/
			// Référence sur un objet de type Participant.
			Participant nouveauParticipant;
			
			// Vérifie si aucune erreur est présente dans 
			// l'identification du participant.
			if(!panneauSaisie.aviserDuneErreur()) {
				
				// Retourne le participant que nous venons d'écrire.
				nouveauParticipant = panneauSaisie.getParticipant();
				
				// Vérifie si le participant est déjà présent dans liste
				// des participants.
				if(listeParticipant.contains(nouveauParticipant)) {
					
					JOptionPane.showMessageDialog(CadreGestionParticipant.this,
							Constantes.MSG_PAT_DEJA_PRESENT);

				}
				
				// Si le participant n'est pas déjà dans la liste.
				else {
					
					// Ajout du participant à liste.
					listeParticipant.add(nouveauParticipant);
					
					// Sauvegarde de la clinique.
					UtilitaireFichier.sauvegarderClinique(clinique, 
							Constantes.CHEMIN_FICHIER);
					
					// Mise à jour de la table dans la liste déroulante.
					miseAJourTableDonnees();
					
					// Met les champs de saisie vide 
					panneauSaisie.reset();
					
					UtilitaireSwing.rafraichirCadre(panneauPrincipal);
					
				}
				
			}
			
		}
		
	}
    
}
