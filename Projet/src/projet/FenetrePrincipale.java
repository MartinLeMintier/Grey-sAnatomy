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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MArgaux 
 */
public class FenetrePrincipale extends JFrame
{
   public JPanel pan, pan2;
   public JButton bouton, bouton2, bouton3, execute;
   public JTextField t1;
   public JTextArea a;
   public JComboBox c1, c2,c3;
   public  Box Box1;
   public  JLabel l1, l2, l3, l4, l5;
   public JRadioButton r1, r2, r3, r4;
   public String select, table, condition, math, valeur; //   dans l'ordre chaine: select, chaine2: <, > ou = du where, chaie3: l'attribut du where
   public Connexion con;
   public JTable tableau;
   public JScrollPane tableau2;
   public String[][] tabRecup ;
   
   
    public FenetrePrincipale()
            {
                setLayout(new BorderLayout());
                setSize(1000,800);
                setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setResizable(false);
                setTitle("Mon Hopital");
                
                tabRecup= new String[][]{{""}};
                
                pan= new JPanel();
                pan2= new JPanel();
                pan.setLayout(null);
                pan2.setLayout(null);
                pan.setBackground(new Color(0x00604A));
                pan2.setBackground(new Color(0x79F8F8));  
                       
                             
                bouton= new JButton("Recherche d’informations ");
                bouton2= new JButton("Mise à jour des données ");
                bouton3= new JButton("Reporting ");
                
                
                
                bouton.setBounds(30,30,120,50);
                bouton2.setBounds(30,100,120,50);
                bouton3.setBounds(30,170, 120,50);
                
                
                bouton.setBackground(new Color(0x0EAD89 ));
                bouton2.setBackground(new Color(0x0EAD89 ));  
                bouton3.setBackground(new Color(0x0EAD89));  
                
                               
              pan.setPreferredSize(new Dimension(190,800));
              pan2.setPreferredSize(new Dimension(790,800));
              
              pan.setBorder(BorderFactory.createLineBorder(Color.black));
              pan2.setBorder(BorderFactory.createLineBorder(Color.black));
                
                pan.add(bouton);
                pan.add(bouton2);
                pan.add(bouton3);
               
                       
                add(pan, "West");
                add(pan2,"East");
                setVisible(true);
                
                bouton.addActionListener(new ItemAction());
                bouton2.addActionListener(new ItemAction());
                bouton3.addActionListener(new ItemAction());
                
                
            }

 
   public void afficher_recherche()
   {   
                
        String [] vide = {""};
                
        tableau = new JTable(tabRecup,vide);
                
        tableau.setBounds(200,400,500,100);
                tableau2= new JScrollPane(tableau);
                
                tableau2.setBounds(100,350,600,300);
                
                execute= new JButton("Execute");
                execute.addActionListener(new ItemAction());
                
                
                c1= new JComboBox ();
                c2= new JComboBox (new String[]{""});
                c3= new JComboBox (new String[]{""});
                
                r1= new JRadioButton("Inferieur ou egal");
                r2= new JRadioButton("Supérieur ou egal");
                r3= new JRadioButton("Egal");
                r4= new JRadioButton("Different");
                
                t1= new JTextField (20);
                                
                l1= new JLabel("Table: ");
                l2= new JLabel("Champ: ");
                l3= new JLabel("Condition: ");
                l4= new JLabel("Valeur: ");
                l5= new JLabel("La valeur cherchee doit être: ");
      
                c1.addItem("Selectionner");
                c1.addItem("Docteur");
                c1.addItem("Employe");
                c1.addItem("Malade");
                c1.addItem("Soigne");
                c1.addItem("Chambre");
                c1.addItem("Hospitalisation");
                c1.addItem("Service");
                c1.addItem("Infirmier");
                
                c1.setBounds(150,30,210,20);
                c2.setBounds(150,100,210,20);
                c3.setBounds(150,170,210,20);
                
                r1.setBounds(550,70,130,20);
                r2.setBounds(550,110,130,20);
                r3.setBounds(550,150,130,20);
                r4.setBounds(550,190,130,20);
                
                ButtonGroup b = new ButtonGroup();
                b.add(r1);
                b.add(r2);
                b.add(r3);
                b.add(r4);
                
                r1.setBackground(new Color(0x79F8F8));
                r2.setBackground(new Color(0x79F8F8));
                r3.setBackground(new Color(0x79F8F8));
                r4.setBackground(new Color(0x79F8F8));
                
                t1.setBounds(150,240,210,20);
                
                execute.setBounds(550,240,130,20);
                
                l1.setBounds(50,30,60,20);
                l2.setBounds(50,100,60,20);
                l3.setBounds(50,170,60,20);
                l4.setBounds(50,240,60,20);
                l5.setBounds(550,30,180,20);
                
                pan2.setEnabled(false);
                pan2.setEnabled(true);
                pan2.add(c1);
                pan2.add(c2);
                pan2.add(c3);
                pan2.add(l1);
                pan2.add(l2);
                pan2.add(l3);
                pan2.add(l4);
                pan2.add(l5);
                pan2.add(t1);
                pan2.add(r1);
                pan2.add(r2);
                pan2.add(r3);
                pan2.add(r4);
                pan2.add(tableau2);
                pan2.add(execute);
             
                
                c1.addActionListener(new Combo());
                c2.addActionListener(new Combo2());
                c3.addActionListener(new Combo3());
                r1.addActionListener(new ItemAction());
                r2.addActionListener(new ItemAction());
                r3.addActionListener(new ItemAction());
                r4.addActionListener(new ItemAction());
                
                add(pan,"West");
                add(pan2,"East");
                pan2.setVisible(true);
                setVisible(true);
      
                           
   }
   
   class ItemAction implements ActionListener
   {
       @Override
       public void actionPerformed(ActionEvent e)
       {
           if(e.getSource()==bouton)
           {
               
               afficher_recherche();
           }
              
         if(e.getSource()==r1)
           {
               math="<=";
           }        
            if(e.getSource()==r2)
           {
               math=">=";
           }   
            
             if(e.getSource()==r3)
           {
               math="=";
           }   
             
              if(e.getSource()==r4)
           {
               math="!=";
           }   
              
              
           if(e.getSource()==execute)
              {
                  afficher_resultat();
              }
      
           
       }
       
      
   }
   public void afficher_resultat()
   {
       try {
           System.out.println("patate");
           String requete;
           valeur=t1.getText();
           con= new Connexion("hopital", "root", "");
           
           String chaine5;
             
//           if(condition=="aucune")
//           {
//               condition="1";
//           }
//           else
//           {
//               
//               condition=condition+math+valeur;
//           }
         
           requete="SELECT "+ select +  " FROM " + table + " WHERE " + condition + math+valeur;
           
           System.out.println(requete);
           tabRecup = new String[con.remplirChampsRequete(requete).size()][1];
           for(int i=0; i<con.remplirChampsRequete(requete).size();i++)
           {
              tabRecup[i][0]= con.remplirChampsRequete(requete).get(i);
              
           }
           String[] videe = new String[]{""};
           DefaultTableModel mod = new DefaultTableModel(tabRecup,videe);
           tableau.setModel(mod);
          
           
       } catch (SQLException ex) {
           Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   
   
   
   
   
   
   class Combo implements ActionListener
   {
       @Override
       public void actionPerformed(ActionEvent e)
       {
          table = (String)c1.getSelectedItem();
         
                 
          
          afficher_combo();
    
       }
       
        public void afficher_combo()
       {
           
          String tab[]={""};
          String tab2[]={""};
          if("Docteur".equals(table))
      {   
        tab= new String [] {"Selectionner","Tout_afficher", "numero", "specialite"};
        tab2= new String [] {"Selectionner","aucune", "numero", "specialite"};
                     
      }
          
           
           if("Infirmier".equals(table))
      {
         tab= new String []{"Selectionner","Tout_afficher", "numero", "code_service","rotation", "salaire"};
         tab2= new String []{"Selectionner","aucune", "numero", "code_service","rotation", "salaire"};
            
            
      }
           
              if("Employe".equals(table))
      {
          tab=new String []{"Selectionner","Tout_afficher", "numero", "nom", "prenom","tel", "adresse"};
          tab2=new String []{"Selectionner","aucune", "numero", "nom", "prenom","tel", "adresse"};
                  
        }
              
              
              if("Hospitalisation".equals(table))
      {
           tab= new String []{"Selectionner","Tout_afficher", "no_malade", "code_service","no_chambre","lit"};
           tab2= new String []{"Selectionner","aucune", "no_malade", "code_service","no_chambre","lit"};
          
      }
               
                if("Malade".equals(table))
      {
          tab=new String []{"Selectionner","Tout_afficher", "numero", "nom","prenom","tel","adresse","mutuelle"};
          tab2=new String []{"Selectionner","aucune", "numero", "nom","prenom","tel","adresse","mutuelle"};
          
    
      }
                
                if("Chambre".equals(table))
      {
          tab=new String []{"Selectionner","Tout_afficher", "no_chambre", "code_service","surveillant","nb_lits"};
          tab2=new String []{"Selectionner","aucune", "no_chambre", "code_service","surveillant","nb_lits"};
                   
      }
                
                if("Service".equals(table))
      {
           tab=new String []{"Selectionner","Tout_afficher", "code", "nom","batiment","directeur"};
           tab2=new String []{"Selectionner","aucune", "code", "nom","batiment","directeur"};
    
      }
                
                
                
                
        DefaultComboBoxModel model = new DefaultComboBoxModel(tab) ; 
        DefaultComboBoxModel model2 = new DefaultComboBoxModel(tab2) ; 
        
        c2.setModel(model) ;
        c3.setModel(model2);
        

           
       }
        
        
       
   }
   
   
   
    class Combo2 implements ActionListener
   {
       @Override
       public void actionPerformed(ActionEvent e)
       {
           select = (String) c2.getSelectedItem();
           chaine_prend_bonne_valeur();
               
       }
       
        public void chaine_prend_bonne_valeur()
        {
            if("Docteur".equals(table)&& "Tout_afficher".equals(select))
                {
                    select="numero,specialite";
                }
              if("Employe".equals(table)&& "Tout_afficher".equals(select))
                {
                     select="numero, nom, prenom, tel, adresse";
                }
              if("Infirmier".equals(table)&& "Tout_afficher".equals(select))
                {
                     select="numero, code_service, rotation,salaire";
                }
              
              if("Soigne".equals(table)&& "Tout_afficher".equals(select))
                {
                     select="no_docteur, no_malade";
                }
              if("Chambre".equals(table)&& "Tout_afficher".equals(select))
                {
                     select="no_chambre, code_service, surveillant, nb_lits";
                }
                if("Hospitalisation".equals(table)&& "Tout_afficher".equals(select))
                {
                     select="no_malade, code_service, no_chambre, lit";
                }
                if("Malade".equals(table)&& "Tout_afficher".equals(select))
                {
                     select="numero, nom, prenom, tel, adresse, mutuelle";
                }
                if("Service".equals(table)&& "Tout_afficher".equals(select))
                {
                     select="code, nom, batiment, directeur";
                }
              
        }
    }
    class Combo3 implements ActionListener
   {
       @Override
       public void actionPerformed(ActionEvent e)
       {
           condition = (String) c3.getSelectedItem();
       }
    }
   
   
   
   
   
    public void afficher_MAJ()
   {
       Box1.removeAll();
   }
    
     public void afficher_reporting()
   {
       
   }
    
   public void afficher_requete()
   {
      
 
}
}
