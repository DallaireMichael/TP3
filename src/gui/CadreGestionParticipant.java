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
 * Cadre qui apparait lorsqu'un s�lectionne un type de participant
 * dans le menu de gestion. 
 * 
 * Une fen�tre de gestion apparait en affichant respectivement
 * les composantes pour chaque participants
 * 
 * Permet d'ajouter ou de supprimer des participants
 * 
 * @author Micha�l Dallaire
 * @version 1.0
 *
 */
public class CadreGestionParticipant extends JDialog implements ActionListener {
    
    /**
     * CONSTANTES
     */
    
    public static final String TEXT_BOUTON_AJOUTER = "Ajouter";
    
    public static final String  TEXT_BOUTON_SUPPRIMER = "Supprimer";
    
    /**
     * ATTRIBUTS
     */
    private static final long             serialVersionUID = 1L;
    
    //Panneau global contenant toute l'information
    private JPanel panneauPrincipal;
    
    //Deroule pour voir une liste qui d�passe la panneau
    private JScrollPane listeDeroulante;
    
    //Tableau contenant tous les participants
    private JTable tableDonnees;
    
    //Panneau contenant les boutons du bas
    private JPanel panneauBas;
    
    //Panneau contenant les boutons qui g�re l'information
    private JPanel panneauGestion;
    
    //R�f�rence a une clinique qu'on veut afficher, ajouter et supprimer
    //des participants
    private Clinique clinique;
    
    //La liste actuelle qu'on veut afficher
    private List<Participant> listeParticipant;
    
    //
    private InterfacePanSaisieParticipant panneauSaisie;
    
    //Bouton qui permettra d'ajouter des participants
    private JButton btnAjouterParticipant = new JButton(TEXT_BOUTON_AJOUTER);
    
    //Bouton qui permettra de supprimer des participants
    private JButton btnSupprimerParticipant = new JButton(TEXT_BOUTON_SUPPRIMER);
    
    
    /**
     * � vous de commenter
     * 
     * Initialiser les param�tre de dialogue et appeler une m�thode
     * qui initialise les composantes contenu dans le cadre
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
        
        this.panneauSaisie = panneauSaisie;
        
        this.listeParticipant = listeParticipant;
        
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        initialiseLesComposantesCadreGestion(position, dimCadre);
        
    }
    
    /**
     * Ajoute les composantes n�cessaire au cadre de gestion pour mettre
     * les fonctionnalit� par d�faut au d�mmarage du programme
     */
    public void initialiseLesComposantesCadreGestion(Point position,
            Dimension dimCadre){
    	
    	/*
    	 * STRAT�GIE : Cr�er d'abord les instances necessaire de Swing
    	 * les param�trer par la suite.
    	 * 
    	 * Int�grer les panneau int�rieur � celui principal
    	 */
        
        tableDonnees = UtilitaireSwing.obtenirListe_A_Afficher(
                listeParticipant.toArray());
        
        listeDeroulante = new JScrollPane(tableDonnees);
        
        panneauPrincipal = new JPanel();
        
        panneauBas = new JPanel();
        
        panneauGestion = new JPanel();
        
        btnAjouterParticipant.addActionListener(this);
        
        btnSupprimerParticipant.addActionListener(this);
        
        //Ajout des boutons au panneau de gestion
        panneauGestion.add(btnAjouterParticipant);
        panneauGestion.add(btnSupprimerParticipant);
        
        panneauBas.add(panneauGestion);
        
        //La panneau du bas devient en affichage par carte
        panneauBas.setLayout(new CardLayout());
        
        //panneau principal ajout� au cadre de gestion
        add(panneauPrincipal);
        
        //Ajout de la liste deroulante et du panneau du bas au panneau
        //principal s'il y a au moins une ligne
        if(tableDonnees.getRowCount() > 0) {
        	
        	panneauPrincipal.add(listeDeroulante);
        	
        }else {
        	
        	panneauPrincipal.add((JPanel)panneauSaisie, BorderLayout.PAGE_START);
        	
        }
        
        panneauPrincipal.add(panneauBas, BorderLayout.PAGE_END);
        
        //param�tres du cadre de gestion JDialog
        setSize(dimCadre);
       
        setLocation(position);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == btnAjouterParticipant ){
            
        }else{
            
        }
        
    }
    
}
