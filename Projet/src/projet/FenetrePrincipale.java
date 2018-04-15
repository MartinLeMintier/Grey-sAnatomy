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
   public JRadioButton r1, r2, r3, r4, r5, r6, r7, r8,r9, r10, r11, r12, r13, r14,r15, r16, r17,r18, r19, r20, r21, r22, r23, r24, r25, r26,r27, r28, r29, r30, r31, r32,r33,r34, tout, tout1, tout2, tout3, tout4, tout5, tout6, tout7, tout8;
   public JButton execute;
   public   boolean nombre, bool;
    
    public FenetrePrincipale()
            {
                nombre=true;
                bool=true;
                
                pan= new JPanel();
                pan2= new JPanel();
                bouton= new JButton("Recherche d’informations ");
                bouton2= new JButton("Mise à jour des données ");
                bouton3= new JButton("Reporting ");
                
                setLayout(new BorderLayout());
                setSize(1950,1050);
                setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
          String chaine="";
          
       
         if(e.getSource()==r1)
           {   
               chaine ="numero";
               bool=false;
                             
           }
           
           if(e.getSource()==r2)
           {   
               chaine ="specialite";
               bool=false;
           }
         
           if(e.getSource()==tout)
           {
                chaine="numero,specialite";
                bool=false;
            }
           if(e.getSource()==r3)
           {
               chaine="numero";
               bool=false;
           }
           
           if(e.getSource()==r4)
           {
               chaine="code_service";
               bool=false;
           }
           if(e.getSource()==r5)
           {
               chaine="rotation";
               bool=false;
           }
           if(e.getSource()==r6)
           {
               chaine="salaire";
               bool=false;
           }
           if(e.getSource()==tout2)
           {
               chaine="numero, code_service, rotation,salaire";
               bool=false;
           }
             if(e.getSource()==r7)
           {
               chaine="numero";
               bool=false;
               
           }
           
           if(e.getSource()==r8)
           {
               chaine="code_service";
               bool=false;
           }
           if(e.getSource()==r9)
           {
               chaine="nom";
               bool=false;
           }
           if(e.getSource()==r10)
           {
               chaine="prenom";
               bool=false;
           }
           if(e.getSource()==r11)
           {
               chaine="tel";
               bool=false;
           }
           if(e.getSource()==r12)
           {
               chaine="adresse";
               bool=false;
           }
           if(e.getSource()==tout3)
           {
               chaine="numero, code_service, nom, prenom, tel, adresse";
               bool=false;
           }
           
           if(e.getSource()==r13)
           {
               chaine="no_malade";
               bool=false;
           }
           
           if(e.getSource()==r14)
           {
               chaine="code_service";
               bool=false;
           }
           if(e.getSource()==r15)
           {
               chaine="no_chambre";
               bool=false;
           }
           if(e.getSource()==r16)
           {
               chaine="lit";
               bool=false;
           }
          
           if(e.getSource()==tout4)
           {
               chaine="no_malade, code_service, no_chambre, lit";
               bool=false;
           }
            if(e.getSource()==r17)
           {
               chaine="no_docteur";
               bool=false;
           }
           
           if(e.getSource()==r18)
           {
               chaine="no_malade";
               bool=false;
           }
                     
           if(e.getSource()==tout5)
           {
               chaine="no_docteur, no_malade";
               bool=false;
           }
             if(e.getSource()==r19)
           {
               chaine="numero";
               bool=false;
           }
           
           if(e.getSource()==r20)
           {
               chaine="nom";
               bool=false;
           }
           if(e.getSource()==r21)
           {
               chaine="prenom";
               bool=false;
           }
           if(e.getSource()==r22)
           {
               chaine="tel";
               bool=false;
           }
           if(e.getSource()==r23)
           {
               chaine="adresse";
               bool=false;
           }
           if(e.getSource()==r24)
           {
               chaine="mutuelle";
               bool=false;
           }
           if(e.getSource()==tout6)
           {
               chaine="numero, nom, prenom, tel, adresse, mutuelle";
               bool=false;
           }
           
            if(e.getSource()==r25)
           {
               chaine="no_chambre";
               bool=false;
           }
           
           if(e.getSource()==r26)
           {
               chaine="code_service";
               bool=false;
           }
           if(e.getSource()==r27)
           {
               chaine="surveillant";
               bool=false;
           }
           if(e.getSource()==r28)
           {
               chaine="nb_lits";
               bool=false;
           }
          
           if(e.getSource()==tout7)
           {
               chaine="no_chambre, code_service, surveillant, nb_lits";
               bool=false;
           }
           
           if(e.getSource()==r29)
           {
               chaine="code";
               bool=false;
           }
           
           if(e.getSource()==r30)
           {
               chaine="nom";
               bool=false;
           }
           if(e.getSource()==r31)
           {
               chaine="batiment";
               bool=false;
           }
           if(e.getSource()==r32)
           {
               chaine="directeur";
               bool=false;
           }
          
           if(e.getSource()==tout8)
           {
               chaine="code, nom, batiment, directeur";
               bool=false;
           }
           
        if(bool==false)
            {
        // System.out.println(bool);
         l3.setText("Voulez vous ajouter une condition ? ");
         r33.setText("Oui");
         r34.setText("Non");
                         
         Box1.add(l3);  
         Box1.add(r33); 
         Box1.add(r34); 
         pan2.add(Box1);
         add(pan2);
         setVisible(true);
        bool=true;       
  
            }
        
         if(e.getSource()==r33)
           {
               System.out.println("maman!!!!");
                l4= new JLabel("Sur quel critère voulez-vous faire porter la condition? ");
                Box1.add(l4);
                
                if("Docteur".equals(choix))
                {
                    c2.addItem("Numero");
                    c2.addItem("Specialite");
                }
                  if("Employe".equals(choix))
                {
                    c2.addItem("Numero");
                    c2.addItem("Code du sevice");
                    c2.addItem("Nom");
                    c2.addItem("Prenom");
                    c2.addItem("Tel");
                    c2.addItem("Adresse");
                }
                     if("Infirmier".equals(choix))
                {
                    c2.addItem("Numero");
                    c2.addItem("Code du sevice");
                    c2.addItem("Rotation");
                    c2.addItem("Salaire");
                 }
                        if("Soigne".equals(choix))
                {
                    c2.addItem("Numero du docteur");
                    c2.addItem("Code du malade");
                 
                }
                        
                           if("Hospitalisation".equals(choix))
                {
                    c2.addItem("Numero du malade");
                    c2.addItem("Code du sevice");
                    c2.addItem("Surveillant");
                    c2.addItem("Nomvre de lits");
         
                }
                           
                        if("Malade".equals(choix))
                {
                    c2.addItem("Numero");
                    c2.addItem("Nom");
                    c2.addItem("Prenom");
                    c2.addItem("Tel");
                    c2.addItem("Adresse");
                    c2.addItem("Mutuelle");
                }
                       if("Service".equals(choix))
                {
                    c2.addItem("Code du service");
                    c2.addItem("nom du service");
                    c2.addItem("Batiment");
                    c2.addItem("Directeur");
                  }
                Box1.add(c2);
                add(pan2);
         setVisible(true);
        bool=true; 
           }
           
           
           if(e.getSource()==r34)
           {     
               
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
      r9= new JRadioButton();
      r10= new JRadioButton();
      r11= new JRadioButton();
      r12= new JRadioButton();
      r13= new JRadioButton();
      r14= new JRadioButton();
      r15= new JRadioButton();
      r16= new JRadioButton();
      r17= new JRadioButton();
      r18= new JRadioButton();
      r19= new JRadioButton();
      r20= new JRadioButton();
      r21= new JRadioButton();
      r22= new JRadioButton();
      r23= new JRadioButton();
      r24= new JRadioButton();
      r25= new JRadioButton();
      r26= new JRadioButton();
      r27= new JRadioButton();
      r28= new JRadioButton();
      r29= new JRadioButton();
      r30= new JRadioButton();
      r31= new JRadioButton();
      r32= new JRadioButton();
      r33= new JRadioButton();
      r34= new JRadioButton();
      tout= new JRadioButton();
      tout1= new JRadioButton();
      tout2= new JRadioButton();
      tout3= new JRadioButton();
      tout4= new JRadioButton();
      tout5= new JRadioButton();
      tout6= new JRadioButton();
      tout7= new JRadioButton();
      tout8= new JRadioButton();
      
      c1.addActionListener(new ItemAction());
      r1.addActionListener(this);
      r2.addActionListener(this);
      r3.addActionListener(this);
      r4.addActionListener(this);
      r5.addActionListener(this);
      r6.addActionListener(this);
      r7.addActionListener(this);
      r8.addActionListener(this);
      r9.addActionListener(this);
      r10.addActionListener(this);
      r11.addActionListener(this);
      r12.addActionListener(this);
      r13.addActionListener(this);
      r14.addActionListener(this);
      r15.addActionListener(this);
      r16.addActionListener(this);
      r17.addActionListener(this);
      r18.addActionListener(this);
      r19.addActionListener(this);
      r20.addActionListener(this);
      r21.addActionListener(this);
      r22.addActionListener(this);
      r23.addActionListener(this);
      r24.addActionListener(this);
      r25.addActionListener(this);
      r26.addActionListener(this);
      r27.addActionListener(this);
      r28.addActionListener(this);
      r29.addActionListener(this);
      r30.addActionListener(this);
      r31.addActionListener(this);
      r32.addActionListener(this);
      r33.addActionListener(this);
      r34.addActionListener(this);
      tout.addActionListener(this);
      tout2.addActionListener(this);
      tout3.addActionListener(this);
      tout4.addActionListener(this);
      tout5.addActionListener(this);
      tout6.addActionListener(this);
      tout7.addActionListener(this);
      tout8.addActionListener(this);
      
      //on ajoute les différents éléments dans la box
      Box1.add(l1);
      Box1.add(c1);
      
      
         
    
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
           
           if("Docteur".equals(choix)&& nombre==true)
      {   
          nombre=false;
          l2.setText("Avec quels critères? ");
          tout.setText("Tout afficher");
          r1.setText("Numero du docteur");
          r2.setText("Spécialité du docteur");  
          System.out.println("paatet");
          Box1.add(l2);
          Box1.add(tout);
          Box1.add(r1); 
          Box1.add(r2); 
          
      }
           
           if("Infirmier".equals(choix)&& nombre==true)
      {
          nombre=false;
          l2.setText("Avec quels critères? ");
          tout2.setText("Tout afficher");
          r3.setText("Numero de l'infirmier");
          r4.setText("Code de son service");
          r5.setText("Sa période de travail (jour ou nuit)");
          r6.setText("Son salaire");
          
          Box1.add(l2);
          Box1.add(tout2);
          Box1.add(r3); 
          Box1.add(r4); 
          Box1.add(r5);
          Box1.add(r6);
          
         
           
      }
           
              if("Employe".equals(choix)&& nombre==true)
      {
          nombre=false;
          l2.setText("Avec quels critères? ");
          tout3.setText("Tout afficher");
          r7.setText("Numero de l'employe");
          r8.setText("Code de son service");
          r9.setText("Nom de l'employe");
          r10.setText("Prenom de l'employe");
          r11.setText("téléphone de l'employe");
          r12.setText("Adresse de l'employe");
          
          Box1.add(l2);
          Box1.add(tout3);
          Box1.add(r7); 
          Box1.add(r8); 
          Box1.add(r9);
          Box1.add(r10);
          Box1.add(r11);
          Box1.add(r12);
          
         
        }
              
              
              if("Hospitalisation".equals(choix)&& nombre==true)
      {
          nombre=false;
          l2.setText("Avec quels critères? ");
          tout4.setText("Tout afficher");
          r13.setText("Numero du malade");
          r14.setText("Code du service de l'hospitalisation");
          r15.setText("Numero chambre");
          r16.setText("Nombre de lits");
          
          Box1.add(l2);
          Box1.add(tout4);
          Box1.add(r13); 
          Box1.add(r14); 
          Box1.add(r15);
          Box1.add(r16);
          
        }
              
               if("Soigne".equals(choix)&& nombre==true)
      {
          nombre=false;
          l2.setText("Avec quels critères? ");
          tout5.setText("Tout afficher");
          r17.setText("Numero du docteur");
          r18.setText("Numero du malade");
       
          Box1.add(l2);
          Box1.add(tout5);
          Box1.add(r17); 
          Box1.add(r18); 
          
         
          }
               
                if("Malade".equals(choix)&& nombre==true)
      {
          nombre=false;
          l2.setText("Avec quels critères? ");
          tout6.setText("Tout afficher");
          r19.setText("Numero du malade");
          r20.setText("Nom du malade");
          r21.setText("Prenom du malade");
          r22.setText("tel du malade");
          r23.setText("adresse du malade");
          r24.setText("mutuelle");
          
          Box1.add(l2);
          Box1.add(tout6);
          Box1.add(r19); 
          Box1.add(r20); 
          Box1.add(r21);
          Box1.add(r22);
          Box1.add(r23);
          Box1.add(r24);
          

    
      }
                
                if("Chambre".equals(choix)&& nombre==true)
      {
          nombre= false;
          l2.setText("Avec quels critères? ");
          tout7.setText("Tout afficher");
          r25.setText("Numero de la chambre");
          r26.setText("Code du service de de la chambre");
          r27.setText("surveillant de la chambre");
          r28.setText("Nombre de lits dans la chambre");
          
          Box1.add(l2);
          Box1.add(tout7);
          Box1.add(r25); 
          Box1.add(r26); 
          Box1.add(r27);
          Box1.add(r28);
          
            
      }
                
                if("Service".equals(choix)&& nombre==true)
      {
          nombre=false;
          l2.setText("Avec quels critères? ");
          tout8.setText("Tout afficher");
          r29.setText("Code du service");
          r30.setText("Nom du service");
          r31.setText("Batiment du service");
          r32.setText("Directeur du service");
          
          Box1.add(l2);
          Box1.add(tout8);
          Box1.add(r29); 
          Box1.add(r30); 
          Box1.add(r31);
          Box1.add(r32);           
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
