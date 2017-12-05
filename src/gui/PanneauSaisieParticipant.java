package gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clinique.Identification;
import clinique.Participant;

/**
 * Panneau de saisie g�n�rique qui demande � l'utilisateur d'entrer un nom et 
 * un pr�nom. 
 * 
 * @author Micha�l Dallaire
 * @version 1.0
 *
 */

public class PanneauSaisieParticipant extends JPanel 
implements InterfacePanSaisieParticipant {
    
    /*
     * CONSTANTES
     */
    
    //message d'erreur lorsque les champs sont vides
    public static final String MSG_ERREUR = "Veuillez remplir les chamnps";
    
    //Largeur par d�faut des champs texte
    public static final int TEXT_FIELD_LARG = 200;
    
  //Longueur par d�faut des champs texte
    public static final int TEXT_FIELD_LNG = 20;
    
    /*
     * VARIABLE PRIV�ES
     */
    
    //Panneau qui contient l'�tiquette et le champ texte pour le Nom
    private JPanel panneauNom = new JPanel();
    
    //Panneau qui contient l'�tiquette et le champ texte pour le pr�nom
    private JPanel panneauPrenom = new JPanel();
    
    //Champ texte pour le nom
    private JTextField nomTextField;
    
    //Champ texte pour le pr�nom
    private JTextField prenomTextField;
    
    private static final long serialVersionUID = 1L;
    
    
    /**
     * CONSTRUCTEUR
     * 
     * Initialise tous les panneaux necessaire � la saisie d'un nom et pr�nom
     */
    public PanneauSaisieParticipant() {
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS ));
        
        JLabel nomLabel = new JLabel("Nom : ");
        nomTextField = new JTextField();
        
        //Change la dimension du champ texte Nom
        nomTextField.setPreferredSize(new Dimension(TEXT_FIELD_LARG,
                TEXT_FIELD_LNG));
        
        JLabel prenomLabel = new JLabel("Prenom : ");
        prenomTextField = new JTextField();
        
        //Change la dimension du champ texte Prenom
        prenomTextField.setPreferredSize(new Dimension(TEXT_FIELD_LARG,
                TEXT_FIELD_LNG));
        
        panneauNom.add(nomLabel);
        panneauNom.add(nomTextField);
        
        panneauPrenom.add(prenomLabel);
        panneauPrenom.add(prenomTextField);
        
        add(panneauNom);
        add(panneauPrenom);
    }
    
    /**
     * Retourne une nouvelle Identification avec le nom et le prenom venant 
     * des champs textes
     * @return Identification
     */
    public Identification getIdentification(){
        
        return new Identification(nomTextField.getText(),
                prenomTextField.getText());
        
    }
    
    /**
     * retourne un nouveau participant avec l'identification enregistrer
     * @return Participant
     */
    @Override
    public Participant getParticipant() {
        return new Participant(getIdentification());
    }
    
    /**
     * Affiche un message d'erreur si un des champs texte est vide
     */
    @Override
    public boolean aviserDuneErreur() {
        
        if(nomTextField.getText() == "" || prenomTextField.getText() == ""){
            
            JOptionPane optionPane = new JOptionPane(MSG_ERREUR,
                    JOptionPane.ERROR_MESSAGE);
            
            add(optionPane); 
            
            return true;
        }

        return false;
    }

    /**
     * Remet les champ textes vide
     */
    @Override
    public void reset() {
 
        nomTextField.setText("");
        
        prenomTextField.setText("");
    }
}
