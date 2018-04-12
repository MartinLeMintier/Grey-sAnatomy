/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.*;
import Controleur.Connexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class FenetreAcceuil extends JFrame implements ActionListener 
{
    public JPanel P1;
    public JLabel l2 ;
    public JTextField t2;
    public JPanel P2 ;
    public  JLabel l3;
    public JTextField t3;
    public JPanel P3 ;
   public JLabel l4;
   public JTextField t4;
   public  JButton b4;
   public  JPanel P4;
   public Box Box1;
   public Connexion conni;
   
   
    public FenetreAcceuil(){
        
    //titre de la fenetre
    this.setTitle("Acceuil Hopital");
    //taille de la fenetre
    this.setSize(800, 600);
    
   

    //on part sur un nouveau pannel
    P1 = new JPanel();
    P1.setLayout(new BoxLayout(P1, BoxLayout.LINE_AXIS));
    
    
    //on créé les différents composant du pannel
    l2 = new JLabel("A quelle BDD voulez vous vous connecter ?");
    t2 = new JTextField(20);  
    
    //On les ajoute au pannel
    P2 = new JPanel();
    P2.add(l2);
    P2.add(t2);
    
    
    //on créé les différents composant du pannel
    l3 = new JLabel("Quel est votre login ?");
    t3 = new JTextField(20);
    
    //On les ajoute au pannel//On les ajoute au pannel
    P3 = new JPanel();
    P3.add(l3);
    P3.add(t3);
    
   
    //on créé les différents composant du pannel
    l4 = new JLabel("Quel est votre mot de passe ?");
    t4 = new JTextField(20);
    b4 = new JButton("Enregistrer"); 
    
    //On les ajoute au pannel
    P4 = new JPanel();
    P4.add(l4);
    P4.add(t4);
    P4.add(b4);
    
    
    //on créé la box
    Box1 = Box.createVerticalBox();
    //on ajoute les différents Pannels dans la box
    Box1.add(P2);
    Box1.add(P3);
    Box1.add(P4);
    
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
         
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         try {
             conni = new Connexion (nom_data,login, passw);
         } catch (SQLException ex) {
             Logger.getLogger(FenetreAcceuil.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(FenetreAcceuil.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
 }
}
