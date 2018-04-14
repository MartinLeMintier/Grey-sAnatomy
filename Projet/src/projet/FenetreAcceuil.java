/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import javax.swing.*;
import java.awt.event.*;
import Controleur.Connexion;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class FenetreAcceuil extends JFrame implements ActionListener 
{
   protected JPanel P1, P2, P3, P4, P5, Ptitre;
   protected JLabel l2, l3, l4, titre;
    
   protected JTextField t2, t3;
    
   protected JPasswordField t4;
    
   protected JButton b4; 
   protected Box Box1;
   protected Connexion conni;
   
   
    public FenetreAcceuil(){
        
    //titre de la fenetre
    this.setTitle("Acceuil Hopital");
    //taille de la fenetre
    this.setSize(800, 600);
    
   

    //on part sur un nouveau pannel
    P1 = new JPanel();
    P1.setBackground(new Color(0x97DFC6));
    P1.setLayout(new BoxLayout(P1, BoxLayout.LINE_AXIS));
    
    
    //on créé les différents composant du pannel
    l2 = new JLabel("A quelle BDD voulez vous vous connecter ?");
    t2 = new JTextField(15);  
    
    //On les ajoute au pannel
    P2 = new JPanel();
    P2.setBackground(new Color(0x97DFC6));
    P2.add(l2);
    P2.add(t2);
    
    
    //on créé les différents composant du pannel
    l3 = new JLabel("Quel est votre login ?");
    t3 = new JTextField(15);
    
    //On les ajoute au pannel//On les ajoute au pannel
    P3 = new JPanel();
    P3.setBackground(new Color(0x97DFC6));
    P3.add(l3);
    P3.add(t3);
    
   
    //on créé les différents composant du pannel
    l4 = new JLabel("Quel est votre mot de passe ?");
    t4 = new JPasswordField(15);
    b4 = new JButton("Connexion"); 
    
    //On les ajoute au pannel
    P4 = new JPanel();
    P4.setBackground(new Color(0x97DFC6));
    P5 = new JPanel();
    P5.setBackground(new Color(0x97DFC6));
    Ptitre = new JPanel();
    Ptitre.setBackground(new Color(0x97DFC6));
    b4.addActionListener(this);
    P4.add(l4);
    P4.add(t4);
    P5.add(b4);
    titre = new JLabel("ACCUEIL");
    
    Ptitre.add(titre);
    
    
    //on créé la box
    Box1 = Box.createVerticalBox();
    //on ajoute les différents Pannels dans la box
    Box1.add(Ptitre);
    Box1.add(P2);
    Box1.add(P3);
    Box1.add(P4);
    Box1.add(P5);
    
    //on ajoute la box dans le premier pannel
    P1.add(Box1);
    
    //visibilité du pannel1
    getContentPane().add(P1);
    this.setVisible(true);
    
    
    }
    
 public void actionPerformed (ActionEvent e)
 {
     
     if(e.getSource()==b4)
     {
        String nom_data;
        String login;
        String passw;
               
         nom_data= t2.getText();
         login= t3.getText();
         passw= t4.getText();
         
         try {
             conni = new Connexion (nom_data,login, passw);
             this.dispose();
             
         } catch (SQLException ex) {
             Logger.getLogger(FenetreAcceuil.class.getName()).log(Level.SEVERE, null, ex);
             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(FenetreAcceuil.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
 }
}
