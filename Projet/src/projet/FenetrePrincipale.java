/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author MArgaux
 */
public class FenetrePrincipale extends JFrame {
    JPanel pan, pan2;
    JButton bouton, bouton2, bouton3;
    
    public FenetrePrincipale()
            {
                pan= new JPanel();
                pan2= new JPanel();
                bouton= new JButton("Recherche d’informations ");
                bouton2= new JButton("Mise à jour des données ");
                bouton3= new JButton("Reporting ");
                
                setLayout(new BorderLayout());
                setSize(1200,800);
                setVisible(true);
                setLocationRelativeTo(null);
                setResizable(false);
                setTitle("Mon Hopital");
                
                pan.setLayout(null);
                bouton.setBounds(65,  125, 175, 80);
                bouton2.setBounds(65,  325, 175, 80);
                bouton3.setBounds(65,  525, 175, 80);
                
                bouton.setBackground(new Color(0x0EAD89 ));
                bouton2.setBackground(new Color(0x0EAD89 ));  
                bouton3.setBackground(new Color(0x0EAD89));  
                pan.setBackground(new Color(0x00604A));
                pan2.setBackground(new Color(0x79F8F8));
                pan.add(bouton);
                pan.add(bouton2);
                pan.add(bouton3);
              
              pan.setPreferredSize(new Dimension(300, 800));
              pan2.setPreferredSize(new Dimension(900, 800));
                
              pan.setBorder(BorderFactory.createLineBorder(Color.black));
              pan2.setBorder(BorderFactory.createLineBorder(Color.black));
             
             
                add(pan, "West");
                add(pan2,"East");
                
            }
    
    
}
