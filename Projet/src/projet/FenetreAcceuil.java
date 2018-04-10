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

/**
 *
 * @author martin
 */
public class FenetreAcceuil extends JFrame
{
    
   
    public FenetreAcceuil(){
        
    //titre de la fenetre
    this.setTitle("Acceuil Hopital");
    //taille de la fenetre
    this.setSize(800, 600);
    

    //on part sur un nouveau pannel
    JPanel P1 = new JPanel();
    P1.setLayout(new BoxLayout(P1, BoxLayout.LINE_AXIS));
    
    
    //on créé les différents composant du pannel
    JLabel l2 = new JLabel("A quelle BDD voulez vous vous connecter ?");
    JTextField t2 = new JTextField("Nom de la BDD");
    JButton b2 = new JButton("Enregistrer");    
    
    //On les ajoute au pannel
    JPanel P2 = new JPanel();
    P2.add(l2);
    P2.add(t2);
    P2.add(b2);
    
    
    //on créé les différents composant du pannel
    JLabel l3 = new JLabel("Quel est votre login ?");
    JTextField t3 = new JTextField("Login : ");
    JButton b3 = new JButton("Enregistrer"); 
    
    //On les ajoute au pannel//On les ajoute au pannel
    JPanel P3 = new JPanel();
    P3.add(l3);
    P3.add(t3);
    P3.add(b3);
    
   
    //on créé les différents composant du pannel
    JLabel l4 = new JLabel("Quel est votre mot de passe ?");
    JTextField t4 = new JTextField("Mot de passe : ");
    JButton b4 = new JButton("Enregistrer"); 
    
    //On les ajoute au pannel
    JPanel P4 = new JPanel();
    P4.add(l4);
    P4.add(t4);
    P4.add(b4);
    
    
    //on créé la box
    Box Box1 = Box.createVerticalBox();
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
    
}
