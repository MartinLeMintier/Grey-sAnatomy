/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import javax.swing.*;
import java.awt.event.*;
import Controleur.Connexion;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Classe de l'écran d'accueil avec les champs permettant de rentrer les identifiants
 * de l'utilisateur en fonction du serveur choisi sur la fenêtre précédente
 * elle contient ainsi tous les éléments graphiques de la fenêtre
 * elle dispose également d'un constructeur permettant d'initialiser la fenêtre, ainsi 
 * que d'une fonction actionPerformed qui détermine les actions effectuées lorsqu'on
 * interagit avec un élément graphique
 * 
 */
public class FenetreAcceuil extends JFrame implements ActionListener 
{
    ///Initialisation des éléments graphiques
   private JPanel P1, P2, P3, P4, P5, P6, Ptitre;
   private JLabel l2, l3, l4, l5, titre;
    
   private JTextField t2, t3;
    
   private JPasswordField t4, t5;
    
   private JButton b4; 
   private Box Box1;
   private Connexion conni;
   private String choix_serveur;
   
   private JFrame error;
   private JLabel mess_error;
   private JLabel mess_error2;
   private JButton berror;
   private JPanel pan_error;
   
   /**
    * constructeur qui crée la fênetre d'accueil et instancie tous les objets graphiques s'y trouvant 
    * @param choix 
    */
    public FenetreAcceuil(String choix){
        
    //titre de la fenetre
    this.setTitle("Acceuil Hopital");
    //taille de la fenetre
    this.setSize(500, 300);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               

    //fenetre d'erreur
    error = new JFrame("erreur");
    error.setResizable(false);
    error.setSize(300,150);
    error.setLocationRelativeTo(null);
    
    pan_error = new JPanel();
    pan_error.setLayout(null);
    pan_error.setBackground(new Color(0xEF3C15));
    mess_error = new JLabel("Echec de la connexion");
    mess_error2 = new JLabel("Vérifiez vos identifiants");
    berror = new JButton();
    berror.setText("OK");
    berror.addActionListener(this);
    
    mess_error.setBounds(85,5,200,20);
    mess_error2.setBounds(85,40, 200, 20);
    berror.setBounds(110,70,70,30);
    
    pan_error.add(mess_error);
    pan_error.add(mess_error2);
    pan_error.add(berror);
    
    error.add(pan_error);
    
    
    //Panel général qui regroupe tous les autres 
    P1 = new JPanel();
    P1.setBackground(new Color(0xEDF7F7));
    P1.setLayout(new BoxLayout(P1, BoxLayout.LINE_AXIS));
    P6 = new JPanel();
    P6.setBackground(new Color(0xEDF7F7));
    l5 = new JLabel ("Quel est votre mot de passe database?");
    t5 = new JPasswordField(15);
    t5.setText("");
    choix_serveur=choix;
    
    titre = new JLabel("ACCUEIL");
    //L'écran d'accueil ne sera pas le même suivant le serveur utilisé
    if ("localhost".equals(choix_serveur)){
        
        l2 = new JLabel("A quelle BDD voulez vous vous connecter ?");
        l3 = new JLabel("Quel est votre login ?");
        l4 = new JLabel("Quel est votre mot de passe ?");
    }
    
    if("gandalf".equals(choix_serveur)){
        l2 = new JLabel("Quel est votre login ECE?");
        l3 = new JLabel("Quel est votre mot de passe ECE?");
        l4 = new JLabel("Quel est votre login database?");             
    }
    
    //Initialisation de nouveaux éléments graphiques
    t2 = new JTextField(15); 
    t2.setText("");
    t3 = new JTextField(15);
    t3.setText("");
    t4 = new JPasswordField(15);
    t4.setText("");
        
    //On les ajoute au pannel
    P2 = new JPanel();
    P3 = new JPanel();
    P4 = new JPanel();
    P5 = new JPanel();
    Ptitre = new JPanel();
    
    //Couleur du background
    P2.setBackground(new Color(0xEDF7F7));
    P3.setBackground(new Color(0xEDF7F7));
    P4.setBackground(new Color(0xEDF7F7));
    P5.setBackground(new Color(0xEDF7F7));
    Ptitre.setBackground(new Color(0xEDF7F7));
    
    //On ajoute tout aux panneaux correspondant 
    
    P6.add(l5);
    P6.add(t5);
    P2.add(l2);
    P2.add(t2);
    
    if ("localhost".equals(choix_serveur)){
    
    P3.add(l3);
    P3.add(t3);
    P4.add(l4);
    P4.add(t4);
    }
    
    if ("gandalf".equals(choix_serveur)){
    P3.add(l3);
    P3.add(t4);
    P4.add(l4);
    P4.add(t3);   
    }
    ///On ajoute le titre au panneau
    Ptitre.add(titre);
    
    b4 = new JButton("Connexion");
    b4.addActionListener(this);
    P5.add(b4);
    
    //on créé la box
    Box1 = Box.createVerticalBox();
    
    //on ajoute les différents Pannels dans la box
    Box1.add(Ptitre);
    Box1.add(P2);
    Box1.add(P3);
    Box1.add(P4);
    
    if("gandalf".equals(choix_serveur)){
       Box1.add(P6);
    }
    
    Box1.add(P5);
    
    //on ajoute la box dans le premier pannel
    P1.add(Box1);
    
    //visibilité du pannel1
    getContentPane().add(P1);
    this.setVisible(true);
    
    
    }
    
    /**
     * méthode actionPerformed qui instancie des objets de la classe connexion
     * En fonction du choix du serveur, l'objet instancié ne reçoit pas les mêmes
     * paramètres
     * Permet aussi d'afficher une fenetre d'erreur en cas d'échec de la connexion
     * @param e 
     */
   @Override
 public void actionPerformed (ActionEvent e)
 { 
     
     if(e.getSource()==berror){
         error.dispose();
         setEnabled(true);
         setVisible(true);
     }
     
     if(e.getSource()==b4)
     {
        ///localhost 
        String nom_data;
        String login;
        String passw;
        
        ///gandalf
        String usernameECE;
        String passwordECE;
        String loginDataBase;
        String passwordDataBase;
               
        if ((t2.getText().length() == 0)||(t3.getText().length()==0)){
            mess_error2.setText("Champs BDD ou login vide");
            error.setVisible(true);
            setEnabled(false);
        }
        
        ///localhost
        if(("localhost".equals(choix_serveur))&&(!"".equals(t2.getText()))&&(!"".equals(t3.getText()))){
         
             nom_data= t2.getText();
             login= t3.getText();
             passw= t4.getText();
        try {    
             conni = new Connexion (nom_data,login, passw);
             this.dispose();
             Recherche P = new Recherche(conni);
        
         } catch (SQLException | ClassNotFoundException ex) {
             mess_error2.setText("Vérifiez vos identifiants");
             error.setVisible(true);
             setEnabled(false);
         }
        }
   
        ///gandalf
        if(("gandalf".equals(choix_serveur))&&((!"".equals(t2.getText()))&&(!"".equals(t3.getText()))&&(!"".equals(t4.getText()))&&(!"".equals(t5.getText())))){
         
               usernameECE= t2.getText();
               passwordECE= t4.getText();
               loginDataBase = t3.getText();
               passwordDataBase = t5.getText();
         try {      
               conni = new Connexion (usernameECE,passwordECE, loginDataBase, passwordDataBase);
               this.dispose();
               Recherche P = new Recherche(conni);
             
         } catch (SQLException | ClassNotFoundException ex) {
             mess_error2.setText("Vérifiez vos identifiants");
             error.setVisible(true);
             setEnabled(false);
         }
         
        }        
      }
   }
}
