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
import org.jfree.ui.RefineryUtilities;


/**
 *Classe qui gère l'affichage et la mise en place des trois modules: recherche, mise à jour, reporting
 * @author MArgaux 
 */
public class FenetrePrincipale extends JFrame
{
   public JPanel pan, pan2, pan3, pan4;
   public JButton bouton, bouton2, bouton3,bouton4, execute, execute2;
   public JTextField t1, t2;
   public JComboBox c1, c2,c3;
   public  JLabel l1, l2, l3, l4, l5, l6, l7;
   public JRadioButton r1, r2, r3, r4;
   public String select, table, condition, math, valeur; //   dans l'ordre chaine: select, chaine2: <, > ou = du where, chaie3: l'attribut du where
   public Connexion con;
   public JTable tableau, tableau_model;
   public JScrollPane tableau2;
   public String[][] tabRecup ;
   public boolean choix;
   public  DefaultTableModel tableau3;
   public  String [] tab3;
   
   /**
    *Constructeur
    * Initialise la page principale avec les 4 boutons à gauche et le pan vide à droite
    */
    public FenetrePrincipale()
            {
                
                
                // Initialisation de la fenetre
                
                setLayout(new BorderLayout());
                setSize(1000,800);
                setVisible(true);
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setResizable(false);
                setTitle("Mon Hopital");
                
              
              // Initialisation des 4 varaibles qui permettent de créer la requete
              
                math="=";
                select="";
                table="";
                condition="";
                valeur="";
                
                // Initialisation du tableau qui permet de recuperer  le resultat des requetes 
                tabRecup= new String[][]{{""}};
                
                //initialisation des deux panel 
                pan= new JPanel();
                pan2= new JPanel();
                pan.setLayout(null);
                pan2.setLayout(null);
                pan.setBackground(new Color(0x00604A));
                pan2.setBackground(new Color(0x79F8F8));  
                       
                
                //Initialisation des 4 boutons sur la gauche de la page pour choisir ce que l'utilisateutr veut faire
                bouton= new JButton("Recherche ");
                bouton2= new JButton("Mise à jour");
                bouton3= new JButton("Reporting ");
                bouton4= new JButton("Complique ");
                
              
                //Positionner les boutons
                bouton.setBounds(30,30,120,50);
                bouton2.setBounds(30,100,120,50);
                bouton3.setBounds(30,170, 120,50);
                bouton4.setBounds(30,240, 120,50);
                
                //choisir la couleur des boutons
                bouton.setBackground(new Color(0x0EAD89 ));
                bouton2.setBackground(new Color(0x0EAD89 ));  
                bouton3.setBackground(new Color(0x0EAD89));
                bouton4.setBackground(new Color(0x0EAD89));
                
               // Taille des pannels               
               pan.setPreferredSize(new Dimension(190,800));
               pan2.setPreferredSize(new Dimension(790,800));
              
               //Affiche les bordures des pannels en noir
               pan.setBorder(BorderFactory.createLineBorder(Color.black));
               pan2.setBorder(BorderFactory.createLineBorder(Color.black));
                
               // Ajoute les boutons sur le premier pannel
                pan.add(bouton);
                pan.add(bouton2);
                pan.add(bouton3);
                pan.add(bouton4);
               
               // Ajoute les deux apnnels sur la fenetre        
                add(pan, "West");
                add(pan2,"East");
                setVisible(true);
                
                //Ajoute des actionLstener sur chzque bouton
                bouton.addActionListener(new ItemAction());
                bouton2.addActionListener(new ItemAction());
                bouton3.addActionListener(new ItemAction());
                 bouton4.addActionListener(new ItemAction());
                
                
            }

 /**
  * Cette méthode permet d'afficher ce qui se passe qaund on veut rechercher dans la base de donnée: c'est-a-dire quand on appuie sur le bouton 1 
  * Elle affiche tous les elelments graphique nécéssaires à la bonne position et mis en forment
  */
   public void afficher_recherche()
   {   
       
       // Booleen qui permet de savoir si l'on est dans le module recherche ou dans le module recherche compliué ou le client rentre la requete
        choix=true;    
        
        //Création d'un tableau model qui permet de le mettre à jour quand on fait une nouvelle requete
        String [] vide = {""};         
        tableau = new JTable(tabRecup,vide);
        tableau3 = new DefaultTableModel();
        tableau.setModel(tableau3);
        tableau.setBounds(200,400,500,100);
                
       // Insertion du tableau dans un tableau avec scroll
        tableau2= new JScrollPane(tableau);                
        tableau2.setBounds(100,350,600,300);
                
                // Création du bouton qui permet d'executer la requete
                execute= new JButton("Execute");
                execute.addActionListener(new ItemAction());
                
                // Création des trois comboBoxe pour reccueillir la volonté d el'utilisateur
                c1= new JComboBox ();
                c2= new JComboBox (new String[]{""});
                c3= new JComboBox (new String[]{""});
                
                // Création des radiobutton pour choisir si l'on veut cherccher qq chose <, > ou = a la valeur rentrée
                r1= new JRadioButton("Inferieur ou egal");
                r2= new JRadioButton("Supérieur ou egal");
                r3= new JRadioButton("Egal");
                r4= new JRadioButton("Different");
                
                // Création du JText Field pour rentrer la valeur du where par l'utilisateur
                t1= new JTextField (20);
                
                // Création des labels pour expliuer à l'utilisateur ce qu'il doit faire
                l1= new JLabel("Table: ");
                l2= new JLabel("Champ: ");
                l3= new JLabel("Condition: ");
                l4= new JLabel("Valeur: ");
                l5= new JLabel("La valeur cherchee doit être: ");
                l6 = new JLabel();
                l6.setBounds(100,680,700,10);
                
                // Rajouter les champs de la premiere comboboxe les chammps des deux autres dépendront du choix d el'utilisteur sur celle-ci
                c1.addItem("Selectionner");
                c1.addItem("Docteur");
                c1.addItem("Employe");
                c1.addItem("Malade");
                c1.addItem("Soigne");
                c1.addItem("Chambre");
                c1.addItem("Hospitalisation");
                c1.addItem("Service");
                c1.addItem("Infirmier");
                
                //Positionnement des comboboxes
                c1.setBounds(150,30,210,20);
                c2.setBounds(150,100,210,20);
                c3.setBounds(150,170,210,20);
                
                //positionnement des radiobuttons
                r1.setBounds(550,70,130,20);
                r2.setBounds(550,110,130,20);
                r3.setBounds(550,150,130,20);
                r4.setBounds(550,190,130,20);
                
                //Creation d'un bouton group qui permet qu'on ne puisse chosiir qu'un seul radiobutton
                ButtonGroup b = new ButtonGroup();
                b.add(r1);
                b.add(r2);
                b.add(r3);
                b.add(r4);
                
                //Coouleur des radiobutton
                r1.setBackground(new Color(0x79F8F8));
                r2.setBackground(new Color(0x79F8F8));
                r3.setBackground(new Color(0x79F8F8));
                r4.setBackground(new Color(0x79F8F8));
                
                //Position du textfield et du bouton execute
                t1.setBounds(150,240,210,20);              
                execute.setBounds(550,240,130,20);
                
                // Position des labels
                l1.setBounds(50,30,60,20);
                l2.setBounds(50,100,60,20);
                l3.setBounds(50,170,60,20);
                l4.setBounds(50,240,60,20);
                l5.setBounds(550,30,180,20);
                
                // MAJ du pan2 en ajoutant tous les éléments graphiques précedents
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
                pan2.add(l6);
                pan2.add(t1);
                pan2.add(r1);
                pan2.add(r2);
                pan2.add(r3);
                pan2.add(r4);
                pan2.add(tableau2);
                pan2.add(execute);
             
                
                // Ajout d'actionListener pour chaque ojjet graphique qui doit etre ecouté
                // Création de différentes class de Listener pour séparer les cas
                c1.addActionListener(new Combo());
                c2.addActionListener(new Combo2());
                c3.addActionListener(new Combo3());
                r1.addActionListener(new ItemAction());
                r2.addActionListener(new ItemAction());
                r3.addActionListener(new ItemAction());
                r4.addActionListener(new ItemAction());
                
                // MAJ de la fenetre
                add(pan,"West");
                add(pan2,"East");
                pan2.setVisible(true);
                setVisible(true);
      
                           
   }
   /**
    * Méthode qui gère l'interface quand on appuie sur l bouton qui permet à l'utilisateur de rentrer ces propres requetes
    * Elle met en place le pan2 et le maj pour ue l'utilisateur puisse rentrer une requete
    */
   
   public void requetes_compliquees()
   {
     // Booleen qui permet de dire que l'utilisateur choisit sa requete et ne passe pas par le formulaire
     choix=false;
     
     //Création de dux nouveau pannels pour la mise en page + Layout + position+ couleur
     pan3= new JPanel();
     pan3.setLayout(null);
     pan3.setBounds(20,30,700,150);
     pan3.setBackground(new Color(0x79F8F8));   
     pan4= new JPanel();
     pan4.setLayout(null);
     pan4.setBounds(20,200,700,500);
     pan4.setBackground(new Color(0x79F8F8));
     
     // Création du bouton executer
     execute2 = new JButton ("execute");
     execute2.setBounds(290,100,80,20);
     execute2.addActionListener(new ItemAction());
     
    // Instanciation du tableu qui affiche le resultat de la requete et son modele qui permet de mettre a jour le contenu du tableau selon les requetes
     String [] vide = {""};
     tableau = new JTable(tabRecup,vide);
     tableau3 = new DefaultTableModel();
     tableau.setModel(tableau3);    
     tableau.setBounds(200,400,500,100);
     tableau2= new JScrollPane(tableau);          
     tableau2.setBounds(30,50,650,300);
     
     //création d'un label
     l6 = new JLabel();
     l6.setBounds(100,680,700,10);
     
     //declaration du JTextField qui recupere la requete
     t2= new JTextField(25);
     t2.setBounds(170,60,300,20);
     
     //declaration du JLabel qui explique à l'utiliateur ce qu'il doit faire
     l7 = new JLabel("Veuillez rentrer votre requete: ");
     l7.setBounds(250,30,300,20);
     
     // Ajoutter les elements graphique sur pan2 puis sur la fenetre
     pan3.add(t2);
     pan3.add(execute2);
     pan3.add(l7);
     pan3.add(l6);
     pan4.add(tableau2);
     pan2.add(pan4);
     pan2.add(pan3);
     setVisible(true);
       
      
   }
   
   
   /**
    * Classe ItemAction qui implemente actionListener qui permet de gérer tous les éléments qui on un actionListener Itemaction
    * Il permet, pour ces elements graphiques, de gérer ce qu'il se passe dans le cas ou l'utilisateur les selectionneent
    * Cette classe est pour les JButton il y aura d'autrespour les comboboxes
    */
   
   class ItemAction implements ActionListener
   {
       @Override
       public void actionPerformed(ActionEvent e)
       {
           //Quand le bouton de recherche est actionné remettre le pan2 à 0 et appelerla fonction qui execute la recherche dans la base de donnée
           if(e.getSource()==bouton)
           {
               select="";
               table="";
               condition="";
               valeur="";
               pan2.removeAll();
               afficher_recherche();
           }
           
           // Quand le bouton recherchecompliquée est activé remettre le pan2 à 0 et appeler la fontion qui affiche et execute la recherche lorsque l'utilisateur rentre une requete
           if(e.getSource()==bouton4)
           {
               pan2.removeAll();
               pan2.setEnabled(false);
               pan2.setEnabled(true);
               requetes_compliquees();
           }
           
         // quand le bouton reporting est actionné appel le sous programme  qui affiche le reporting 
           
            if(e.getSource()==bouton3)
           {
               pan2.removeAll();
               pan2.setEnabled(false);
               pan2.setEnabled(true);
               afficher_reporting();
           }
         // Pour les 4 bouton radios associer  à la variable utilisée pour créer une requete, la valeur qui lui correspond  
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
              
           // Bouton qui affiche le resultat de la requete lorsque l'on appui sur executer   
           if(e.getSource()==execute)
              {
             
                  
                  afficher_resultat();
              }
            // Bouton qui affiche le resultat de la requete lorsque l'on appui sur executer dans le module de recherche compliqué
            if(e.getSource()==execute2)
             {        
                 
                  afficher_resultat();
              }
            
            // Bouton qui gère les maj quand on appui dessus
           if(e.getSource()==bouton2)
           {
               pan2.updateUI();
           }
           
       }
       
      
   }
   /**
    * Methode qui affiche qui créé une requete selon les decisions de l'utilisateur et remplit le tableau affichant le resultat
    */
   public void afficher_resultat()
   {
       try {
           //remettre à zero le JTextField à chaque execution
           l6.setText("");
           
           
           String requete;           
           con= new Connexion("hopital", "root", "");          
        
         
    // dans le cas ou on est dans le module ou l'utilisateur passe par le formulaire               
     if(choix==true)    
     {
         //recuperer ce qu'il  ais dans le JTextField
            valeur="'"+t1.getText()+"'";
            
          // Si il selectionne Selectionner dans une des 3 ComboBoxe initialiser les cariables de la requete à vide
           if("Selectionner".equals(select))
       {   
            select="";
                     
       }
           if("Selectionner".equals(table))
       {   
            table="";
                     
       }
           if("Selectionner".equals(condition))
       {   
            condition="";
                     
       }
           // Permet de gerer si il y a un where ou non dans la requete si oui ce qu'il y a apres le where prend la valeeur condition + math+valeur sinon 1 ce qui veut dire pas de where
         
           if(condition.equals("aucune"))
           {
               requete="SELECT "+ select +  " FROM " + table + " WHERE " + "1";
           }
           else{
               requete="SELECT "+ select +  " FROM " + table + " WHERE " + condition + math+valeur;
           }
     }
     else{ // si choix == false cad que l'utilisateur rentre directement la requete on recupere juste ce qu'il y a dans le jTextField
         requete=t2.getText();
       
     }
   
           //Blindage pour que les requetes demandées à l'execution existent 
           if((select!=""&&table!=""&&condition!="")|| choix==false)
           {
            //tableau qui va recupererle retour de la requete
           tabRecup = new String[con.remplirChampsRequete(requete).size()][1];
           
           //initialise les titres dans le tableau qui affiche le resulatta de la requete
           if(choix==true)
           {
               tabRecup [0][0]="";
               
                for(int i=0; i< tab3.length; i++)
           {
                tabRecup[0][0]+=tab3[i]+"  ";
           }
           }
           
          
        //// // System.out.println(requete);
        
        // remplir le tableau du resultat des requetes
           for(int i=1; i<con.remplirChampsRequete(requete).size();i++)
           {
              tabRecup[i][0]= con.remplirChampsRequete(requete).get(i);
              
           }
           
           // remplir le tableau graphique avec le tableau de requetes
           String[] videe = new String[]{""};
           DefaultTableModel mod = new DefaultTableModel(tabRecup,videe);
           tableau.setModel(mod);
           }
           else
           {
                // Affichage que la requete est erronee dans le cas ou le blindage a empeché la requete de s'executer          
               l6.setText("Votre saisie est erronee veuillez recommencer");
             
           }
           
           // reininitialiser les vraiables des requetes à vide pour ne pas garder les valurs prises precedemment
           select="";
           table="";
           condition="";
           valeur="";
          
           
       } catch (SQLException ex) {
           Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   
   
   
   
   
   /**
    * déclaration d'une classe qui implémente actionListener et qui décrit ce qui sera fait selon le choix de l'utilisateur sur la premiere combo boxe
    */
   class Combo implements ActionListener
   {
      // Implementation de la méthode actionperformed 
       @Override
       public void actionPerformed(ActionEvent e)
       {
           // A chaque fois que l'utilisteur fait un choix dans la combo boxe on appelle la focntion afficher_combo
          table = (String)c1.getSelectedItem();                                 
          afficher_combo();
    
       }
       /**
        * Cette méthode permet de créer le contenu de la deuxieme comboboxs selon le choix d el'utilisateur dans la premiere
        */
        public void afficher_combo()
       {
           
          String tab[]={""};
          String tab2[]={""};
          
          // Si l'utilisateur a choisi docteur la comboboxe "deux" presentra les attributs de la classe docteur
          if("Docteur".equals(table))
      {   
        tab= new String [] {"Selectionner","Tout_afficher", "numero", "specialite"};
        tab2= new String [] {"Selectionner","aucune", "numero", "specialite"};
        tab3= new String [] {"numero", "specialite"};
                     
      }
          
          
         // Si l'utilisateur a choisi infirmier la comboboxe "deux" presentra les attributs de la classe infirmier 
           if("Infirmier".equals(table))
      {
         tab= new String []{"Selectionner","Tout_afficher", "numero", "code_service","rotation", "salaire"};
         tab2= new String []{"Selectionner","aucune", "numero", "code_service","rotation", "salaire"};
         tab3= new String []{ "numero", "code_service","rotation", "salaire"};
            
            
      }
        // Si l'utilisateur a choisi employe la comboboxe "deux" presentra les attributs de la classe employe  
              if("Employe".equals(table))
      {
          tab=new String []{"Selectionner","Tout_afficher", "numero", "nom", "prenom","tel", "adresse"};
          tab2=new String []{"Selectionner","aucune", "numero", "nom", "prenom","tel", "adresse"};
          tab3=new String []{ "numero", "nom", "prenom","tel", "adresse"};
                  
        }
              
         // Si l'utilisateur a choisi hospitalisation la comboboxe "deux" presentra les attributs de la classe hospitalisation     
              if("Hospitalisation".equals(table))
      {
           tab= new String []{"Selectionner","Tout_afficher", "no_malade", "code_service","no_chambre","lit"};
           tab2= new String []{"Selectionner","aucune", "no_malade", "code_service","no_chambre","lit"};
           tab3= new String []{ "no_malade", "code_service","no_chambre","lit"};
          
      }
        // Si l'utilisateur a choisi malade la comboboxe "deux" presentra les attributs de la classe mlade      
                if("Malade".equals(table))
      {
          tab=new String []{"Selectionner","Tout_afficher", "numero", "nom","prenom","tel","adresse","mutuelle"};
          tab2=new String []{"Selectionner","aucune", "numero", "nom","prenom","tel","adresse","mutuelle"};
          tab3= new String []{"numero", "nom","prenom","tel","adresse","mutuelle"};
          
    
      }
                // Si l'utilisateur a choisi soigne la comboboxe "deux" presentra les attributs de la classe soigne
                  if("Soigne".equals(table))
      {
          tab=new String []{"Selectionner","Tout_afficher", "no_docteur", "no_malade"};
          tab2=new String []{"Selectionner","aucune", "no_docteur", "no_malade"};
          tab3=new String []{ "no_docteur", "no_malade"};
          
    
      }
                  // Si l'utilisateur a choisi chambre la comboboxe "deux" presentra les attributs de la classe chambre
                
                if("Chambre".equals(table))
      {
          tab=new String []{"Selectionner","Tout_afficher", "no_chambre", "code_service","surveillant","nb_lits"};
          tab2=new String []{"Selectionner","aucune", "no_chambre", "code_service","surveillant","nb_lits"};
          tab3=new String []{ "no_chambre", "code_service","surveillant","nb_lits"};
                   
      }
             // Si l'utilisateur a choisi service la comboboxe "deux" presentra les attributs de la classe service   
                if("Service".equals(table))
      {
           tab=new String []{"Selectionner","Tout_afficher", "code", "nom","batiment","directeur"};
           tab2=new String []{"Selectionner","aucune", "code", "nom","batiment","directeur"};
           tab3=new String []{ "code", "nom","batiment","directeur"};
    
      }
                
                
         // Création des comboboxes avec un model pour que les maj des comboboxes soient prisent en compte à chaque nouvelle requete       
                
        DefaultComboBoxModel model = new DefaultComboBoxModel(tab) ; 
        DefaultComboBoxModel model2 = new DefaultComboBoxModel(tab2) ; 
        
        c2.setModel(model) ;
        c3.setModel(model2);
        

           
       }
        
        
       
   }
   
    /**
    * déclaration d'une classe qui implémente actionListener et qui décrit ce qui sera fait selon le choix de l'utilisateur sur la deuxieme combo boxe
    */
   
    class Combo2 implements ActionListener
   {
       @Override
       public void actionPerformed(ActionEvent e)
       {
           // chzque fois que l'utilisateur fait un choix sur la 2eme comboboxe chaine_prend_bonne_valeur est appelé
           select = (String) c2.getSelectedItem();
           chaine_prend_bonne_valeur();
               
       }
       /**
        * Cette sous fonction permet de faire prendre à select la bonne valeur lorsque il fait le chois de tout afficher 
        */
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
    
    /**
    * déclaration d'une classe qui implémente actionListener et qui décrit ce qui sera fait selon le choix de l'utilisateur sur la troisieme combo boxe
    */
    class Combo3 implements ActionListener
   {
       @Override
       public void actionPerformed(ActionEvent e)
       {
           // condition prend la valeur du choix fait ar l'utilisateur
           condition = (String) c3.getSelectedItem();
       }
    }
   
   
   
   /**
    * Méthode qui est appelé qaund l'utilisateur decide de mettre à jour la base de donnée
    */
   
    public void afficher_MAJ()
   {
      
   }
    /**
    * Méthode qui est appelé qaund l'utilisateur decide d'afficher des diagrammes faisant certaines statistiques
    */
     public void afficher_reporting()
   {
       System.out.println("reporting");
      Pie demo = new Pie( "doc" );  
      demo.setSize( 800 , 600 );    
      RefineryUtilities.centerFrameOnScreen( demo );    
      demo.setVisible( true ); 
      
   }
    
}
