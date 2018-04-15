/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;
import Controleur.Connexion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MArgaux
 */
public class FenetrePrincipale extends JFrame implements ActionListener
{
   public JPanel pan, pan2;
   public JButton bouton, bouton2, bouton3;
   
   protected Connexion conni;
   public String choix;
   public JComboBox c1, c2,c3;
   public  Box Box1;
   public  JLabel l1, l2, l3, l4, l5;
   public JRadioButton r1, r2, r3, r4, r5, r6, r7, r8, tout;
   public JButton execute;
    
    public FenetrePrincipale()
            {
                pan= new JPanel();
                pan2= new JPanel();
                bouton= new JButton("Recherche d’informations ");
                bouton2= new JButton("Mise à jour des données ");
                bouton3= new JButton("Reporting ");
                
                setLayout(new BorderLayout());
                setSize(1950,1050);
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
              
              pan.setPreferredSize(new Dimension(325, 1050));
              pan2.setPreferredSize(new Dimension(1625,1050));
                
              pan.setBorder(BorderFactory.createLineBorder(Color.black));
              pan2.setBorder(BorderFactory.createLineBorder(Color.black));
             
             
                add(pan, "West");
                add(pan2,"East");
                
                bouton.addActionListener(this);
                bouton2.addActionListener(this);
                bouton3.addActionListener(this);
                
            }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==bouton)
        {
            afficher_recherche();
        }
         if(e.getSource()==bouton2)
        {
            afficher_MAJ();
        }
          if(e.getSource()==bouton3)
        {
            afficher_reporting();
        }
    }
    
   public void afficher_recherche()
   {       
      
     
      Box1 = Box.createVerticalBox();
           
      execute= new JButton("Execute");
       
      l1= new JLabel("Qu'est ce que vous recherchez? ");
      l2= new JLabel("Avec quels critères? ");
      l3= new JLabel("Voulez vous ajouter une condition ? ");
      l4= new JLabel("Quelles conditions souhaitez-vous ajouter? ");
      l5= new JLabel();
       
      c1= new JComboBox ();
      c2= new JComboBox ();
      c3= new JComboBox ();
      
      c1.addItem("Docteur");
      c1.addItem("Employe");
      c1.addItem("Malade");
      c1.addItem("Soin");
      c1.addItem("Chambre");
      c1.addItem("Hospitalisation");
      c1.addItem("Service");
      c1.addItem("Infirmier");
      
      r1= new JRadioButton();
      r2= new JRadioButton();
      r3= new JRadioButton();
      r4= new JRadioButton();
      r5= new JRadioButton();
      r6= new JRadioButton();
      r7= new JRadioButton();
      r8= new JRadioButton();
      
      c1.addActionListener(new ItemAction());
      
      //on ajoute les différents Pannels dans la box
      Box1.add(l1);
      Box1.add(c1);
      Box1.add(l2);
      
      
      
    
      pan2.add(Box1);
      add(pan2);
      setVisible(true);
      
      
       
   }
   
   class ItemAction implements ActionListener
   {
       @Override
       public void actionPerformed(ActionEvent e)
       {
           choix = (String) c1.getSelectedItem();
           if("Docteur".equals(choix))
      {
          String chaine2="", chaine1="";
          tout=new JRadioButton("Tout afficher");
        
            r1=new JRadioButton("Numero du docteur");
            r2=new JRadioButton("Spécialité du docteur");
         
           Box1.add(tout);
           Box1.add(r1); 
           Box1.add(r2); 
           
           if(r1.isSelected())
           {
               chaine1 ="numero";
           }
           
           if(r2.isSelected())
           {
               chaine2 ="specialite";
           }
           if(chaine1!="" && chaine2!="")
           {
                String chaine= chaine1+ "," + chaine2;
           }
      }
           
           if("Infirmier".equals(choix))
      {
          tout=new JRadioButton("Tout afficher");
          r1=new JRadioButton("Numero de l'infirmier");
          r2=new JRadioButton("Code de son service");
          r3=new JRadioButton("Sa période de travail (jour ou nuit)");
          r4=new JRadioButton("Son salaire");
          
          Box1.add(tout);
          Box1.add(r1); 
          Box1.add(r2); 
          Box1.add(r3);
          Box1.add(r4);
          
          String chaine="";
           
           if(r1.isSelected())
           {
               chaine="numero";
           }
           
           if(r2.isSelected())
           {
               chaine="code_service";
           }
           if(r3.isSelected())
           {
               chaine="rotation";
           }
           if(r4.isSelected())
           {
               chaine="salaire";
           }
           if(tout.isSelected())
           {
               chaine="numero, code_service, rotation,salaire";
           }
      }
           
              if("Employe".equals(choix))
      {
          tout=new JRadioButton("Tout afficher");
          r1=new JRadioButton("Numero de l'infirmier");
          r2=new JRadioButton("Code de son service");
          r3=new JRadioButton("Sa période de travail (jour ou nuit)");
          r4=new JRadioButton("Son salaire");
          
          Box1.add(tout);
          Box1.add(r1); 
          Box1.add(r2); 
          Box1.add(r3);
          Box1.add(r4);
          
          String chaine="";
           
           if(r1.isSelected())
           {
               chaine="numero";
           }
           
           if(r2.isSelected())
           {
               chaine="code_service";
           }
           if(r3.isSelected())
           {
               chaine="rotation";
           }
           if(r4.isSelected())
           {
               chaine="salaire";
           }
           if(tout.isSelected())
           {
               chaine="numero, code_service, rotation,salaire";
           }
      }
           pan2.add(Box1);
           add(pan2);
           setVisible(true);
       }
   }
   
    public void afficher_MAJ()
   {
       
   }
    
     public void afficher_reporting()
   {
       
   }
    
    
    
 
}
