/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import Controleur.Connexion;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
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
public class Recherche extends JFrame
{
   private JPanel pan, pan2, pan3, pan4, pan5; // 2 premiers pan = pan principaux, les deux autres= sous pan dans le module recherchec compliquée
   private JButton bouton, bouton2, bouton3,bouton4, execute, execute2, exemartin,quitter; // 4 boutons pour choisir le modul et differents boutons quitter
   private JTextField t1, t2;
   private JComboBox c1, c2,c3,c4;
   private  JLabel l1, l2, l3, l4, l5, l6, l7, l8;
   private JRadioButton r1, r2, r3, r4;
   private String select, table, condition, math, valeur, condition2; // Retours des comboBoxes et JTextField pourconstruire une requete  
   private Connexion con; // connexion récupérée en parametre du constructeur
   private JTable tableau, tableau_model; // Tableau affichant le resultat d'une requete
   private JScrollPane tableau2; // Tableau affichant le resultat d'une requete
   private String[][] tabRecup ; // Tableau recuperant le resultat d'une requete
   private boolean choix; // savoir si l'utilisateur a chosii le module de recherche avec le formulaire ou en rentrant la requete
   private  DefaultTableModel tableau3; // model du tableau affiché
   private  String [] tab3; // Titres dans l'affichage des requetes
   private ReportingPie demo; // declaration d'un diagramme
   private double []y;
   private int x;
   
   private JRadioButton saisie_num, saisie_nom;
   private String choix_maj, choix_sous_menu, choix_combo_ss, requeteMaj;
   private JComboBox sel_action, choix_supprimer, choix_mis, combo_ss_menu,combo_spec, code_service, rotation, mutuelle, champs_surveillant,nbr_lit, choix_modifier;
   private JTextField champs_nom, champs_prenom, champs_numero,champs_adresse,champs_tel, salaire, lit, n_chambre, n_doctor, champs_num;
   private JTextField search_maj_numero, search_maj_nom, search_maj_prenom;
   private JLabel chp_nom_tl, chp_prenom_tl, chp_numero_tl,chp_adresse_tl, chp_tel_tl, type_emploi, chp_special,cs_tl,rot_tl,sal_tl, mut_tl, champs_lit;
   private JLabel n_chambre_tl, search_maj_numtl, search_maj_ptl, n_doctor_tl, chp_num_tl, chp_chambre_num, nb_lit_tl, n_surveillant_tl, num_medecin, chp_numero_patient, titre, label_maj_p1;
   private JButton valider_maj;
   private JLabel label_maj_p, saisie;
   
   
   /**
    *Constructeur
    * Initialise la page principale avec les 4 boutons à gauche et le pan vide à droite
     * @param conni
    */
    public Recherche(Connexion conni)
            {
                
                
                // Initialisation de la fenetre
                
                setLayout(new BorderLayout());
                setSize(1000,800);
                setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setResizable(false);
                setTitle("Mon Hopital");
                
               // recupee la connexion et le place en attribut
               con=conni;
              // Initialisation des 4 varaibles qui permettent de créer la requete             
                math="=";
                select="";
                table="";
                condition="";
                valeur="";
                condition2="% de docteur par specialite";
                
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
                quitter= new JButton("Quitter ");
                
              
                //Positionner les boutons
                bouton.setBounds(30,30,120,50);
                bouton2.setBounds(30,100,120,50);
                bouton3.setBounds(30,170, 120,50);
                bouton4.setBounds(30,240, 120,50);
                quitter.setBounds(30,310, 120,50);
                
                //choisir la couleur des boutons
                bouton.setBackground(new Color(0x0EAD89 ));
                bouton2.setBackground(new Color(0x0EAD89 ));  
                bouton3.setBackground(new Color(0x0EAD89));
                bouton4.setBackground(new Color(0x0EAD89));
                quitter.setBackground(new Color(0x0EAD89));
                
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
                pan.add(quitter);
               
               // Ajoute les deux apnnels sur la fenetre        
                add(pan, "West");
                add(pan2,"East");
                setVisible(true);
                
                //Ajoute des actionLstener sur chzque bouton
                bouton.addActionListener(new ItemAction());
                bouton2.addActionListener(new ItemAction());
                bouton3.addActionListener(new ItemAction());
                bouton4.addActionListener(new ItemAction());
                quitter.addActionListener(new ItemAction());
                
                
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
     l8 = new JLabel();
     l6.setBounds(250,400,300,20);
     l8.setBounds(250,400,300,20);
     
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
     pan4.add(l6);
     pan4.add(l8);
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
               afficher_MAJ();
           }
           
           //fermer le programme
           if(e.getSource()==quitter)
           {
               dispose();
           }
           
           
           //Bouton qui lance le diagramme choisit par l'utilisateur dans la comboboxe
          if(e.getSource()==exemartin)
           {
               //Premier diagramme
               if(condition2.equals("% de docteur par specialite"))
               {
                pan5.removeAll();
                demo = new ReportingPie( "Spécilites des docteurs", con );
                demo.setSize( 500 , 500 );    
                RefineryUtilities.centerFrameOnScreen(demo);
                pan5.add(demo.createDataset());
                setVisible( true ); 
   
               }
               //deuxieme diagramme
               if(condition2.equals("% d'infirmier par rotation"))
               {
                pan5.removeAll();
                demo = new ReportingPie( "rotation des infirmieres", con );  
                demo.setSize( 500 , 500 );    
                RefineryUtilities.centerFrameOnScreen(demo);
                pan5.add(demo.createDataset());
                setVisible( true ); 
               }
               //troisieme diagramme
               if(condition2.equals("% de personnes hospitalisees par service"))
               {
                pan5.removeAll();
                demo = new ReportingPie( "Personnes hospitalisees par service", con );  
                demo.setSize( 500 , 500 );    
                RefineryUtilities.centerFrameOnScreen(demo);
                pan5.add(demo.createDataset());
                setVisible( true ); 
               }
               //Quatrieme
                 if(condition2.equals("% d'infirmiers par service"))
               {
                pan5.removeAll();
                demo = new ReportingPie( "Infirmiers par service", con );  
                demo.setSize( 500 , 500 );    
                RefineryUtilities.centerFrameOnScreen(demo);
                pan5.add(demo.createDataset());
                setVisible( true ); 
               }
                 
                 if(condition2.equals("% de malades par mutuelle"))
               {
                pan5.removeAll();
                demo = new ReportingPie( "Malades par mutuelle", con );  
                demo.setSize( 500 , 500 );    
                RefineryUtilities.centerFrameOnScreen(demo);
                pan5.add(demo.createDataset());
                setVisible( true ); 
               }
              
               
//               if(condition2.equals("test"))
//               {
//                   pan5.removeAll();
//                   ReportingHistogramme r = new ReportingHistogramme("test","x","y",con);
//                   pan5.add(r.creation_chart());
//                   setVisible(true);
//               }
              
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
                    
          
        System.out.println(requete);
        
        // remplir le tableau du resultat des requetes
        try{
          tabRecup = new String[con.remplirChampsRequete(requete).size()][1];
          //tableau qui va recupererle retour de la requete
//           tabRecup [0][0]="";

           //initialise les titres dans le tableau qui affiche le resulatta de la requete
//           if(choix==true)
//           {
//               
//                for(int i=0; i< tab3.length; i++)
//           {
//                tabRecup[0][0]+=tab3[i]+"  ";
//           }
//           }

           for(int i=1; i<con.remplirChampsRequete(requete).size();i++)
           {
              tabRecup[i][0]= con.remplirChampsRequete(requete).get(i);
              
           }
        }catch(com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e )
        {
            l8.setText("SQL excetion, votre requete est erronee veuillez en saisir une nouvelle");
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
           Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
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
    
     class Combo4 implements ActionListener
   {
       @Override
       public void actionPerformed(ActionEvent e)
       {
           condition2 = (String)c4.getSelectedItem();
   
       }
       
       
       
       
   }
   
   /**
    * Méthode qui est appelée qaund l'utilisateur decide de mettre à jour la base de donnée
    */
   
    public void afficher_MAJ()
   {
      pan2.removeAll();
      pan2.setLayout(null);
       
        
       ////Need pour la fenetre de mise à jour
       label_maj_p = new JLabel();
       search_maj_numero = new JTextField();
       search_maj_nom = new JTextField();
       search_maj_prenom = new JTextField();
       num_medecin = new JLabel("Numéro du médecin: ");
       chp_numero_patient = new JLabel("Numéro du patient");
       saisie = new JLabel();
       nb_lit_tl = new JLabel("Nombre de lits: ");
       n_surveillant_tl = new JLabel("Surveillant: ");
       valider_maj = new JButton("Valider");
       saisie_nom = new JRadioButton("Saisie par nom");
       saisie_num = new JRadioButton("Saisie par numéro d'identification");
       lit = new JTextField();
       n_doctor = new JTextField();
       n_chambre = new JTextField();
       n_doctor_tl = new JLabel("Numero du médecin");
       n_chambre_tl = new JLabel("Numero de chambre");
       champs_lit = new JLabel("Lit:");
       saisie_nom.addActionListener(new ItemActionMaj());
       saisie_num.addActionListener(new ItemActionMaj());
       
       nbr_lit =  new JComboBox ();
            nbr_lit.addItem("");
            nbr_lit.addItem("1");
            nbr_lit.addItem("2");
            nbr_lit.addItem("3");
            nbr_lit.addItem("4");
       nbr_lit.addActionListener(new ItemActionMaj());
       
       choix_modifier = new JComboBox();
            choix_modifier.addItem("");
            choix_modifier.addItem("Un employé");
            choix_modifier.addItem("Un malade");
            choix_modifier.addItem("Une hospitalisation");
            choix_modifier.addItem("Changement de médecin");
       choix_modifier.addActionListener(new ItemActionMaj()); 
       
       choix_supprimer = new JComboBox();
            choix_supprimer.addItem("");
            choix_supprimer.addItem("Un employé");
            choix_supprimer.addItem("Un malade");
            choix_supprimer.addItem("Une hospitalisation");
            choix_supprimer.addItem("Fin de traitement");
       choix_supprimer.addActionListener(new ItemActionMaj());  
       
       champs_surveillant = new JComboBox();
            champs_surveillant.addItem("");
            champs_surveillant.addItem("95");
            champs_surveillant.addItem("29");
            champs_surveillant.addItem("57");
            champs_surveillant.addItem("130");
            champs_surveillant.addItem("151");
            champs_surveillant.addItem("12");
            champs_surveillant.addItem("23");
            champs_surveillant.addItem("49");
            champs_surveillant.addItem("116");
            champs_surveillant.addItem("169");
       champs_surveillant.addActionListener(new ItemActionMaj());
            
       choix_mis = new JComboBox();
            choix_mis.addItem("");
            choix_mis.addItem("Un employé");
            choix_mis.addItem("Un malade");
            choix_mis.addItem("Une chambre");
            choix_mis.addItem("Une hospitalisation");
            choix_mis.addItem("Attribuer un médecin");
       choix_mis.addActionListener(new ItemActionMaj());
       
       sel_action = new JComboBox();
                 sel_action.addItem("");
                 sel_action.addItem("Insérer une donnée");
                 sel_action.addItem("Modifier une donnée");
                 sel_action.addItem("Supprimer une donnée");
                 
       combo_ss_menu = new JComboBox();
                 combo_ss_menu.addItem("");
                 combo_ss_menu.addItem("Docteur");
                 combo_ss_menu.addItem("Infirmier");
       combo_ss_menu.addActionListener(new ItemActionMaj());
       
       combo_spec = new JComboBox();
                 combo_spec.addItem("");
                 combo_spec.addItem("Orthopediste");
                 combo_spec.addItem("Cardiologue");
                 combo_spec.addItem("Traumatologue");
                 combo_spec.addItem("Anesthesiste");
                 combo_spec.addItem("Pneumologue");
                 combo_spec.addItem("Radiologue");
                 combo_spec.addItem("Pneumologue");
        combo_spec.addActionListener(new ItemActionMaj());  
        
        code_service = new JComboBox();
                 code_service.addItem("");
                 code_service.addItem("REA");
                 code_service.addItem("CHG");
                 code_service.addItem("CAR");
        code_service.addActionListener(new ItemActionMaj());
        
        rotation = new JComboBox();
                 rotation.addItem("");
                 rotation.addItem("JOUR");
                 rotation.addItem("NUIT");
        rotation.addActionListener(new ItemActionMaj());
        
        mutuelle = new JComboBox();
                 mutuelle.addItem("");
                 mutuelle.addItem("MNAM");
                 mutuelle.addItem("LMDE");
                 mutuelle.addItem("MNH");
                 mutuelle.addItem("MAAF");
                 mutuelle.addItem("MGEN");
                 mutuelle.addItem("MMA");
                 mutuelle.addItem("CNAMTS");
                 mutuelle.addItem("CCVRP");
                 mutuelle.addItem("MNFTC");
                 mutuelle.addItem("MAS");
                 mutuelle.addItem("AG2R");
                 mutuelle.addItem("MGSP");
        mutuelle.addActionListener(new ItemActionMaj());
        
        ButtonGroup g = new ButtonGroup();
                 g.add(saisie_nom);
                 g.add(saisie_num);
        
       champs_nom = new JTextField();
       champs_prenom = new JTextField();
       champs_tel = new JTextField();
       champs_numero = new JTextField();
       champs_adresse = new JTextField();
       champs_num = new JTextField();
       salaire = new JTextField();
       chp_nom_tl = new JLabel("Nom: ");
       chp_prenom_tl = new JLabel("Prenom: ");
       chp_tel_tl = new JLabel ("Téléphone: ");
       chp_numero_tl = new JLabel ("Numéro: ");
       chp_adresse_tl = new JLabel ("Adresse: ");
       type_emploi = new JLabel("Type d'employé: ");
       chp_special = new JLabel ("Spécialité:");
       cs_tl = new JLabel ("Code service:");
       rot_tl = new JLabel ("Rotation: ");
       sal_tl = new JLabel ("Salaire: ");
       mut_tl = new JLabel ("Mutuelle");
       chp_num_tl = new JLabel ("Numéro du patient: ");
       chp_chambre_num = new JLabel ("Numéro de chambre:");
       saisie_nom.setSelected(false);
       saisie_num.setSelected(false);
       
       Font font = new Font("Arial",Font.BOLD,18);
       titre = new JLabel("Menu de mise à jour des données");
       label_maj_p1 = new JLabel("Quelle action souhaitez-vous effectuer?");
       titre.setFont(font);
       titre.setBounds(250,50,300,20);
       label_maj_p1.setBounds(250,100,300,20);
       sel_action.setBounds(250,125,300,20);
       //On met la combobox sous écoute afin de récupérer le choix de l'utilisateur
       sel_action.addActionListener(new ItemActionMaj());
       
       pan2.setEnabled(false);
       pan2.setEnabled(true);
       pan2.add(titre);
       pan2.add(label_maj_p1);
       pan2.add(sel_action);
       
       pan2.updateUI();
       add(pan2,"East");
       setVisible(true);
   }
    /**
    * Méthode qui est appelé qaund l'utilisateur decide d'afficher des diagrammes faisant certaines statistiques
    * Il affiche seulement la mise en page du pannel
    */
     public void afficher_reporting()
   {
     // bouton executer  
     exemartin = new JButton("GO");
     exemartin.setBounds(340,60,110,20);
     exemartin.addActionListener(new ItemAction());
     
     //nouveau pan pourr afficher le diagramme 
     pan5= new JPanel();
     pan5.setBounds(20,220,750,430);
     pan5.setBackground(new Color(0x79F8F8));
     
   
     
     //declaration de la comboboxe pour choisir le diagramme a afficher
     c4= new JComboBox ();
     c4.addItem("% de docteur par specialite");
     c4.addItem("% d'infirmier par rotation");
     c4.addItem("% de personnes hospitalisees par service");
     c4.addItem("% de malades par mutuelle");
     c4.addItem("% d'infirmiers par service");
     c4.setBounds(250,30,280,20);
     c4.addActionListener(new Combo4());
     
     //MAJ du pannel
     pan2.add(c4);
     pan2.add(pan5);
     pan2.add(exemartin);
     
    // MAJ de la fenetre   
     add(pan,"West");
     add(pan2,"East");
     pan2.setVisible(true);
     setVisible(true);
      
   }
    
     class ItemActionMaj implements ActionListener{
       
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            //on récupère le choix de la combobox du menu de maj
            choix_maj = (String) sel_action.getSelectedItem();
            choix_mis.addActionListener(new ItemActionMaj());
            choix_sous_menu = "";
            valider_maj.addActionListener(this);
        ///////////////////////////////////////////
        // Actions pour le menu des mises à jour //
        ///////////////////////////////////////////
       
        if ("Insérer une donnée".equals(choix_maj)){
            //System.out.println("insérer");
            pan2.removeAll();
            pan2.setEnabled(false);
            pan2.setEnabled(true);
            pan2.add(titre);
            pan2.add(label_maj_p1);
            pan2.add(sel_action);
            sel_action.setSelectedItem("Insérer une donnée");
            add(pan2);
            pan2.updateUI();
            add(pan2,"East");
            setVisible(true);
            
            label_maj_p.setText("Sélectionnez ce que vous souhaitez ajouter");
            label_maj_p.setBounds(250,175,300,20);
            choix_mis.setBounds(250,200,300,20);
            pan2.add(label_maj_p);
            pan2.add(choix_mis);
            
            choix_sous_menu = (String) choix_mis.getSelectedItem();
               
                ///////////////////////
                //Sous menu d'insérer//
                ///////////////////////
                
                if("Un employé".equals(choix_sous_menu)){
                    saisie.setText("Veuillez remplir les champs ci-dessous:");
                    pan2.add(saisie);
                    saisie.setBounds(280,250,300,20);
                    
                    pan2.add(chp_nom_tl);
                    chp_nom_tl.setBounds(250,340,75,20);
                    pan2.add(champs_nom);
                    champs_nom.setBounds(250,365,100,20);
                    
                    pan2.add(chp_prenom_tl);
                    chp_prenom_tl.setBounds(250, 400, 100, 20);
                    pan2.add(champs_prenom);
                    champs_prenom.setBounds(250, 425, 100, 20);
                    
                    pan2.add(chp_numero_tl);
                    chp_numero_tl.setBounds(450, 400, 100, 20);
                    pan2.add(champs_numero);
                    champs_numero.setBounds(450, 425, 100, 20);
                    
                    pan2.add(chp_adresse_tl);
                    chp_adresse_tl.setBounds(450, 280, 100, 20);
                    pan2.add(champs_adresse);
                    champs_adresse.setBounds(450, 305, 100, 20);
                    
                    pan2.add(chp_tel_tl);
                    chp_tel_tl.setBounds(450, 340, 100, 20);
                    pan2.add(champs_tel);
                    champs_tel.setBounds(450, 365, 100, 20);
                    
                    pan2.add(type_emploi);
                    type_emploi.setBounds(250,280,100,20);
                    pan2.add(combo_ss_menu);
                    
                    pan2.add(valider_maj);
                    valider_maj.setBounds(350,520,100,30);
                    combo_ss_menu.setBounds(250,305,100,20);
                    
                    
                    choix_combo_ss = (String) combo_ss_menu.getSelectedItem();
                    
                        if("Docteur".equals(choix_combo_ss))
                        {
                            pan2.remove(cs_tl);
                            pan2.remove(code_service);
                            pan2.remove(rot_tl);
                            pan2.remove(rotation);
                            pan2.remove(sal_tl);
                            pan2.remove(salaire);
                            
                            pan2.setEnabled(false);
                            pan2.setEnabled(true);
                            
                            pan2.add(chp_special);
                            chp_special.setBounds(350,460,100,20);
                            pan2.add(combo_spec);
                            combo_spec.setBounds(350,485,100,20);
                            pan2.updateUI();
                            add(pan2,"East");
                            setVisible(true);
                            
                        if(ae.getSource()==valider_maj){  
                        try { 
                            Connexion con = new Connexion("hopital", "root", "");
                            requeteMaj = "INSERT INTO employe (numero,nom,prenom,adresse,tel) VALUES('" + champs_numero.getText() + "','" + champs_nom.getText() + "','" + champs_prenom.getText() + "','" + champs_adresse.getText() + "','" + champs_tel.getText()+"')";
                            con.executeUpdate(requeteMaj);
                            requeteMaj = "INSERT INTO docteur (numero,specialite) VALUES ('" + champs_numero.getText() + "','" + combo_spec.getSelectedItem() + "');";
                            con.executeUpdate(requeteMaj);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("ca marche pas");
                            
                        }}
                            
                            
                        }
                        
                        if("Infirmier".equals(choix_combo_ss)){
                            
                            pan2.remove(chp_special);
                            pan2.remove(combo_spec);
                            
                            pan2.setEnabled(false);
                            pan2.setEnabled(true);
                            
                            pan2.setEnabled(false);
                            pan2.setEnabled(true);
                            pan2.add(cs_tl);
                            cs_tl.setBounds(200,460,100,20);
                            pan2.add(code_service);
                            code_service.setBounds(200,485,100,20);
                            
                            pan2.add(rot_tl);
                            rot_tl.setBounds(350,460,100,20);
                            pan2.add(rotation);
                            rotation.setBounds(350,485,100,20);
                            
                            pan2.add(sal_tl);
                            sal_tl.setBounds(500,460,100,20);
                            pan2.add(salaire);
                            salaire.setBounds(500,485,100,20);
                            pan2.updateUI();
                            add(pan2,"East");
                            setVisible(true);
                            
                            if(ae.getSource()==valider_maj){  
                        try { 
                            Connexion con = new Connexion("hopital", "root", "");
                            requeteMaj = "INSERT INTO employe (numero,nom,prenom,adresse,tel) VALUES('" + champs_numero.getText() + "','" + champs_nom.getText() + "','" + champs_prenom.getText() + "','" + champs_adresse.getText() + "','" + champs_tel.getText()+"')";
                            con.executeUpdate(requeteMaj);
                            requeteMaj = "INSERT INTO infirmier (numero,code_service,rotation,salaire) VALUES ('" + champs_numero.getText() + "','" + code_service.getSelectedItem() + "','" + rotation.getSelectedItem() + "','" + salaire.getText() + "');";
                            con.executeUpdate(requeteMaj);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("ca marche pas");
                            
                        }}
                            
                        }
                    pan2.updateUI();
                    add(pan2,"East");
                    setVisible(true);
                }
                
                if("Un malade".equals(choix_sous_menu)){
                    saisie.setText("Veuillez remplir les champs ci-dessous:");
                    pan2.add(saisie);
                    saisie.setBounds(280,250,300,20);
                    
                    pan2.add(chp_nom_tl);
                    chp_nom_tl.setBounds(250,280,75,20);
                    pan2.add(champs_nom);
                    champs_nom.setBounds(250,305,100,20);
                    
                    pan2.add(chp_prenom_tl);
                    chp_prenom_tl.setBounds(250, 340, 100, 20);
                    pan2.add(champs_prenom);
                    champs_prenom.setBounds(250, 365, 100, 20);
                    
                    pan2.add(mut_tl);
                    mut_tl.setBounds(250,400,100,20);
                    pan2.add(mutuelle);
                    mutuelle.setBounds(250,425,100,20);
                    
                    pan2.add(chp_numero_tl);
                    chp_numero_tl.setBounds(450, 400, 100, 20);
                    pan2.add(champs_numero);
                    champs_numero.setBounds(450, 425, 100, 20);
                    
                    pan2.add(chp_adresse_tl);
                    chp_adresse_tl.setBounds(450, 280, 100, 20);
                    pan2.add(champs_adresse);
                    champs_adresse.setBounds(450, 305, 100, 20);
                    
                    pan2.add(chp_tel_tl);
                    chp_tel_tl.setBounds(450, 340, 100, 20);
                    pan2.add(champs_tel);
                    champs_tel.setBounds(450, 365, 100, 20);
                    
                    pan2.add(valider_maj);
                    valider_maj.setBounds(350,520,100,30);
                    pan2.updateUI();
                    add(pan2,"East");
                    setVisible(true);
                    
                    if(ae.getSource()==valider_maj){
                    try {Connexion con = new Connexion("hopital", "root", "");
                    requeteMaj = "INSERT INTO malade (numero,nom,prenom,adresse,tel,mutuelle) VALUES('" + champs_numero.getText() + "','" + champs_nom.getText() + "','" + champs_prenom.getText() + "','" + champs_adresse.getText() + "','" + champs_tel.getText()+ "','" + mutuelle.getSelectedItem() + "');";
                    con.executeUpdate(requeteMaj);
                    } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }   
                }
                
                if("Une hospitalisation".equals(choix_sous_menu)){
                    saisie.setText("Veuillez remplir les champs ci-dessous:");
                    pan2.add(saisie);
                    saisie.setBounds(280,250,300,20);
                    
                    pan2.add(saisie_num);
                    saisie_num.setBackground(new Color(0x79F8F8));
                    saisie_num.setBounds(150,300,275,20);
                    pan2.add(saisie_nom);
                    saisie_nom.setBackground(new Color(0x79F8F8));
                    saisie_nom.setBounds(450,300,200,20);
                    
                    if(saisie_nom.isSelected()){
                        
                        pan2.add(chp_nom_tl);
                        chp_nom_tl.setBounds(250,340,75,20);
                        pan2.add(champs_nom);
                        champs_nom.setBounds(250,365,100,20);
                        
                        pan2.add(champs_lit);
                        champs_lit.setBounds(450,340,75,20);
                        pan2.add(lit);
                        lit.setBounds(450,365,75,20);
                    
                        pan2.add(chp_prenom_tl);
                        chp_prenom_tl.setBounds(250, 400, 100, 20);
                        pan2.add(champs_prenom);
                        champs_prenom.setBounds(250, 425, 100, 20);
                        
                        pan2.add(cs_tl);
                        cs_tl.setBounds(450, 400, 100, 20);
                        pan2.add(code_service);
                        code_service.setBounds(450, 425, 100, 20);
                        
                        pan2.add(n_chambre_tl);
                        n_chambre_tl.setBounds(250, 460,150,20);
                        pan2.add(n_chambre);
                        n_chambre.setBounds(250,485,100,20);
                        
                        if(ae.getSource()==valider_maj){
                         try {Connexion con = new Connexion("hopital", "root", "");
                         String tampon;
                         tampon = "SELECT numero FROM malade WHERE nom = '" + champs_nom.getText() + "'";
                         String num = (String) (con.remplirChampsRequete(tampon).get(0));
                         requeteMaj = "INSERT INTO hopital.hospitalisation (no_malade,code_service,no_chambre,lit) VALUES('" + num + "','" + code_service.getSelectedItem() + "','" + n_chambre.getText() + "','" + lit.getText() + "');";
                         con.executeUpdate(requeteMaj);
                         } catch (SQLException | ClassNotFoundException ex) {
                         Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                         }
                    
                         }
                        
                        
                    }
                    
                    if(saisie_num.isSelected()){
                        pan2.add(chp_num_tl);
                        chp_num_tl.setBounds(250,340,150,20);
                        pan2.add(champs_num);
                        champs_num.setBounds(250,365,150,20);
                        
                        pan2.add(champs_lit);
                        champs_lit.setBounds(450,340,75,20);
                        pan2.add(lit);
                        lit.setBounds(450,365,75,20);
                    
                        pan2.add(n_chambre_tl);
                        n_chambre_tl.setBounds(250, 400, 150, 20);
                        pan2.add(champs_prenom);
                        champs_prenom.setBounds(250, 425, 100, 20);
                        
                        pan2.add(cs_tl);
                        cs_tl.setBounds(450, 400, 100, 20);
                        pan2.add(code_service);
                        code_service.setBounds(450, 425, 100, 20);
                        
                        if(ae.getSource()==valider_maj){
                         try {Connexion con = new Connexion("hopital", "root", "");
                         requeteMaj = "INSERT INTO hopital.hospitalisation (no_malade,code_service,no_chambre,lit) VALUES('" + champs_num.getText() + "','" + code_service.getSelectedItem() + "','" + champs_prenom.getText() + "','" + lit.getText() + "');";
                         con.executeUpdate(requeteMaj);
                         } catch (SQLException | ClassNotFoundException ex) {
                         Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                         }
                    
                         }
                        
                    }
                   
                    
                    pan2.add(valider_maj);
                    valider_maj.setBounds(350,520,100,30);
                    pan2.updateUI();
                    add(pan2,"East");
                    setVisible(true);
                }
            
                if("Une chambre".equals(choix_sous_menu)){
                     saisie.setText("Veuillez remplir les champs ci-dessous:");
                     pan2.add(saisie);
                     saisie.setBounds(280,250,300,20);
                    
                     pan2.add(cs_tl);
                     cs_tl.setBounds(250,300,150,20);
                     pan2.add(code_service);
                     code_service.setBounds(250,325,150,20);
                    
                     pan2.add(n_chambre_tl);
                     n_chambre_tl.setBounds(250, 360, 150, 20);
                     pan2.add(champs_prenom);
                     champs_prenom.setBounds(250, 385, 100, 20);
                     
                     pan2.add(n_surveillant_tl);
                     n_surveillant_tl.setBounds(450, 300, 150, 20);
                     pan2.add(champs_surveillant);
                     champs_surveillant.setBounds(450, 325, 100, 20);
                     
                     pan2.add(nb_lit_tl);
                     nb_lit_tl.setBounds(450, 360, 150, 20);
                     pan2.add(nbr_lit);
                     nbr_lit.setBounds(450, 385, 100, 20);
                     
                     pan2.add(valider_maj);
                     valider_maj.setBounds(350,520,100,30);
                     pan2.updateUI();
                     add(pan2,"East");
                     setVisible(true);
                     
                     if(ae.getSource()==valider_maj){
                    try {Connexion con = new Connexion("hopital", "root", "");
                    requeteMaj = "INSERT INTO chambre (code_service,no_chambre,surveillant,nb_lits) VALUES('" + code_service.getSelectedItem() + "','" + champs_prenom.getText() + "','" + champs_surveillant.getSelectedItem() + "','" + nbr_lit.getSelectedItem() + "');";
                    con.executeUpdate(requeteMaj);
                    } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }
                     
            }       
                
                if("Attribuer un médecin".equals(choix_sous_menu)){
                    saisie.setText("Veuillez remplir les champs ci-dessous:");
                    pan2.add(saisie);
                    saisie.setBounds(280,250,300,20);
                    
                    pan2.add(num_medecin);
                    num_medecin.setBounds(250,300,150,20);
                    pan2.add(champs_num);
                    champs_num.setBounds(250,325,150,20);
                        
                    pan2.add(chp_numero_patient);
                    chp_numero_patient.setBounds(450, 300, 150, 20);
                    pan2.add(champs_numero);
                    champs_numero.setBounds(450, 325, 100, 20);
                    
                    pan2.add(valider_maj);
                    valider_maj.setBounds(350,400,100,30);
                    pan2.updateUI();
                    add(pan2,"East");
                    setVisible(true);
                    
                    if(ae.getSource()==valider_maj){
                    try {Connexion con = new Connexion("hopital", "root", "");
                    requeteMaj = "INSERT INTO hopital.soigne (no_docteur, no_malade) VALUES ('" + champs_num.getText() + "','" + champs_numero.getText() +"');";
                    con.executeUpdate(requeteMaj);
                    } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                }
                    
                }
                
        }
        
        if ("Modifier une donnée".equals(choix_maj)){
            
            pan2.removeAll();
            
            pan2.setEnabled(false);
            pan2.setEnabled(true);
            
            pan2.add(titre);
            pan2.add(label_maj_p1);
            pan2.add(sel_action);
            sel_action.setSelectedItem("Modifier une donnée");
            add(pan2,"East");
            pan2.updateUI();
            setVisible(true);
            
            label_maj_p.setText("Que souhaitez-vous modifier?");
            label_maj_p.setBounds(310,175,300,20);
            choix_modifier.setBounds(250,200,300,20);
            pan2.add(label_maj_p);
            pan2.add(choix_modifier);
            String choixdemodif = (String) choix_modifier.getSelectedItem();
            
            if("Un employé".equals(choixdemodif)){
                
                saisie.setText("Veuillez remplir les champs ci-dessous:");
                    pan2.add(saisie);
                    saisie.setBounds(280,250,300,20);
                    
                    pan2.add(chp_nom_tl);
                    chp_nom_tl.setBounds(250,340,75,20);
                    pan2.add(champs_nom);
                    champs_nom.setBounds(250,365,100,20);
                    
                    pan2.add(chp_prenom_tl);
                    chp_prenom_tl.setBounds(250, 400, 100, 20);
                    pan2.add(champs_prenom);
                    champs_prenom.setBounds(250, 425, 100, 20);
                    
                    pan2.add(chp_numero_tl);
                    chp_numero_tl.setBounds(450, 400, 100, 20);
                    pan2.add(champs_numero);
                    champs_numero.setBounds(450, 425, 100, 20);
                    
                    pan2.add(chp_adresse_tl);
                    chp_adresse_tl.setBounds(450, 280, 100, 20);
                    pan2.add(champs_adresse);
                    champs_adresse.setBounds(450, 305, 100, 20);
                    
                    pan2.add(chp_tel_tl);
                    chp_tel_tl.setBounds(450, 340, 100, 20);
                    pan2.add(champs_tel);
                    champs_tel.setBounds(450, 365, 100, 20);
                    
                    pan2.add(type_emploi);
                    type_emploi.setBounds(250,280,100,20);
                    pan2.add(combo_ss_menu);
                    
                    pan2.add(valider_maj);
                    valider_maj.setBounds(350,520,100,30);
                    combo_ss_menu.setBounds(250,305,100,20);
                    
                    
                    choix_combo_ss = (String) combo_ss_menu.getSelectedItem();
                    
                        if("Docteur".equals(choix_combo_ss))
                        {
                            pan2.remove(cs_tl);
                            pan2.remove(code_service);
                            pan2.remove(rot_tl);
                            pan2.remove(rotation);
                            pan2.remove(sal_tl);
                            pan2.remove(salaire);
                            
                            pan2.setEnabled(false);
                            pan2.setEnabled(true);
                            
                            pan2.add(chp_special);
                            chp_special.setBounds(350,460,100,20);
                            pan2.add(combo_spec);
                            combo_spec.setBounds(350,485,100,20);
                            pan2.updateUI();
                            add(pan2,"East");
                            setVisible(true);
                            
                        if(ae.getSource()==valider_maj){  
                        try { 
                            Connexion con = new Connexion("hopital", "root", "");
                            requeteMaj = "UPDATE employe SET numero = '" + champs_numero.getText() + "', adresse = '" + champs_adresse.getText() + "', tel = '" + champs_tel.getText() + "' WHERE nom = '" + champs_nom.getText() + "'";
                            con.executeUpdate(requeteMaj);
                            requeteMaj = "UPDATE docteur SET specialite = '" + combo_spec.getSelectedItem() + "' WHERE numero = '" + champs_numero.getText() + "'";
                            con.executeUpdate(requeteMaj);
                            
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }}
                            
                            
                        }
                        
                        if("Infirmier".equals(choix_combo_ss)){
                            
                            pan2.remove(chp_special);
                            pan2.remove(combo_spec);
                            
                            pan2.setEnabled(false);
                            pan2.setEnabled(true);
                            
                            pan2.add(cs_tl);
                            cs_tl.setBounds(200,460,100,20);
                            pan2.add(code_service);
                            code_service.setBounds(200,485,100,20);
                            
                            pan2.add(rot_tl);
                            rot_tl.setBounds(350,460,100,20);
                            pan2.add(rotation);
                            rotation.setBounds(350,485,100,20);
                            
                            pan2.add(sal_tl);
                            sal_tl.setBounds(500,460,100,20);
                            pan2.add(salaire);
                            salaire.setBounds(500,485,100,20);
                            pan2.updateUI();
                            add(pan2,"East");
                            setVisible(true);
                            
                            if(ae.getSource()==valider_maj){  
                        try { 
                            Connexion con = new Connexion("hopital", "root", "");
                            requeteMaj = "UPDATE employe SET numero = '" + champs_numero.getText() + "', adresse = '" + champs_adresse.getText() + "', tel = '" + champs_tel.getText() + "' WHERE nom = '" + champs_nom.getText() + "'";
                            con.executeUpdate(requeteMaj);
                            requeteMaj = "UPDATE infirmier SET code_service = '" + code_service.getSelectedItem() + "', rotation = '" + rotation.getSelectedItem() + "', salaire = '" + salaire.getText() + "' WHERE numero = '" + champs_numero.getText() + "'";
                            con.executeUpdate(requeteMaj);
                            
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }}
                            
                        }
                    pan2.updateUI();
                    add(pan2,"East");
                    setVisible(true);
  
            }
            
            if("Un malade".equals(choixdemodif)){
                 
                 saisie.setText("Veuillez remplir les champs ci-dessous:");
                 pan2.add(saisie);
                 saisie.setBounds(280,250,300,20);
                
                    pan2.add(chp_nom_tl);
                    chp_nom_tl.setBounds(250,280,75,20);
                    pan2.add(champs_nom);
                    champs_nom.setBounds(250,305,100,20);
                    
                    pan2.add(chp_prenom_tl);
                    chp_prenom_tl.setBounds(250, 340, 100, 20);
                    pan2.add(champs_prenom);
                    champs_prenom.setBounds(250, 365, 100, 20);
                    
                    pan2.add(mut_tl);
                    mut_tl.setBounds(250,400,100,20);
                    pan2.add(mutuelle);
                    mutuelle.setBounds(250,425,100,20);
                    
                    pan2.add(chp_numero_tl);
                    chp_numero_tl.setBounds(450, 400, 100, 20);
                    pan2.add(champs_numero);
                    champs_numero.setBounds(450, 425, 100, 20);
                    
                    pan2.add(chp_adresse_tl);
                    chp_adresse_tl.setBounds(450, 280, 100, 20);
                    pan2.add(champs_adresse);
                    champs_adresse.setBounds(450, 305, 100, 20);
                    
                    pan2.add(chp_tel_tl);
                    chp_tel_tl.setBounds(450, 340, 100, 20);
                    pan2.add(champs_tel);
                    champs_tel.setBounds(450, 365, 100, 20);
                    
                    pan2.add(valider_maj);
                    valider_maj.setBounds(350,520,100,30);
                    pan2.updateUI();
                    add(pan2,"East");
                    setVisible(true);
                    
                    if(ae.getSource()==valider_maj){  
                        try { 
                            Connexion con = new Connexion("hopital", "root", "");
                            requeteMaj = "UPDATE malade SET numero = '" + champs_numero.getText() + "', adresse = '" + champs_adresse.getText() + "', tel = '" + champs_tel.getText() + "', mutuelle = '" + mutuelle.getSelectedItem() + "' WHERE nom = '" + champs_nom.getText() + "'";
                            con.executeUpdate(requeteMaj);
                            
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }}
                 
            }
            
            if("Une hospitalisation".equals(choixdemodif)){
                 
                 saisie.setText("Veuillez remplir les champs ci-dessous:");
                 pan2.add(saisie);
                 saisie.setBounds(280,250,300,20);
                
                        pan2.add(chp_num_tl);
                        chp_num_tl.setBounds(250,340,150,20);
                        pan2.add(champs_num);
                        champs_num.setBounds(250,365,150,20);
                        
                        pan2.add(champs_lit);
                        champs_lit.setBounds(450,340,75,20);
                        pan2.add(lit);
                        lit.setBounds(450,365,75,20);
                    
                        pan2.add(n_chambre_tl);
                        n_chambre_tl.setBounds(250, 400, 150, 20);
                        pan2.add(champs_prenom);
                        champs_prenom.setBounds(250, 425, 100, 20);
                        
                        pan2.add(cs_tl);
                        cs_tl.setBounds(450, 400, 100, 20);
                        pan2.add(code_service);
                        code_service.setBounds(450, 425, 100, 20);
                        
                        pan2.add(valider_maj);
                        valider_maj.setBounds(350,520,100,30);
                        pan2.updateUI();
                        add(pan2,"East");
                        setVisible(true);
                 
                 if(ae.getSource()==valider_maj){  
                        try { 
                            Connexion con = new Connexion("hopital", "root", "");
                            requeteMaj = "UPDATE hospitalisation SET code_service = '" + code_service.getSelectedItem() + "', no_chambre = '" + champs_prenom.getText() + "', lit = '" + lit.getText() + "' WHERE no_malade = '" + champs_num.getText() + "'";
                            con.executeUpdate(requeteMaj);
                            
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }}
            }
            
            if("Changement de médecin".equals(choixdemodif)){
                ///A laisser ou pas? pas vraiment utile à vrai dire
                saisie.setText("Veuillez remplir les champs ci-dessous:");
                pan2.add(saisie);
                saisie.setBounds(280,250,300,20);
                
                pan2.add(num_medecin);
                    num_medecin.setBounds(250,300,150,20);
                    pan2.add(champs_num);
                    champs_num.setBounds(250,325,150,20);
                        
                    pan2.add(chp_numero_patient);
                    chp_numero_patient.setBounds(450, 300, 150, 20);
                    pan2.add(champs_numero);
                    champs_numero.setBounds(450, 325, 100, 20);
                
                pan2.add(valider_maj);
                valider_maj.setBounds(360,360,100,30);
                
                if(ae.getSource()==valider_maj){  
                        try { 
                            Connexion con = new Connexion("hopital", "root", "");
                            requeteMaj = "UPDATE soigne SET no_docteur = '" + champs_num.getText() + "' WHERE no_malade = '" + champs_numero.getText() + "'";
                            con.executeUpdate(requeteMaj);
                            
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }}
                
            }
            
            add(pan2,"East");
            setVisible(true);
       
        }
        
        if("Supprimer une donnée".equals(choix_maj)){
            //system.out.println("supprimer");
            pan2.removeAll();
            
            pan2.setEnabled(false);
            pan2.setEnabled(true);
            
            pan2.add(titre);
            pan2.add(label_maj_p1);
            pan2.add(sel_action);
            sel_action.setSelectedItem("Supprimer une donnée");
            add(pan2,"East");
            pan2.updateUI();
            setVisible(true);
            
            label_maj_p.setText("Que souhaitez-vous supprimer?");
            label_maj_p.setBounds(310,175,300,20);
            choix_supprimer.setBounds(250,200,300,20);
            pan2.add(label_maj_p);
            pan2.add(choix_supprimer);
            String choixdemodif2 = (String) choix_supprimer.getSelectedItem();
            
            if("Un employé".equals(choixdemodif2)){
                
                saisie.setText("Veuillez remplir les champs ci-dessous:");
                pan2.add(saisie);
                saisie.setBounds(280,250,300,20);
                
                 pan2.add(chp_numero_tl);
                 chp_numero_tl.setBounds(350, 345, 150,20);
                 pan2.add(search_maj_numero);
                 search_maj_numero.setBounds(350,370,150,20);
                 
                 pan2.add(chp_nom_tl);
                 chp_nom_tl.setBounds(250,285,150,20);
                 pan2.add(search_maj_nom);
                 search_maj_nom.setBounds(250,310,150,20);
                 
                 pan2.add(chp_prenom_tl);
                 chp_prenom_tl.setBounds(450,285,150,20);
                 pan2.add(search_maj_prenom);
                 search_maj_prenom.setBounds(450,310,150,20);
                 
                 pan2.add(valider_maj);
                 valider_maj.setBounds(375,405,100,30);
                 
                 if(ae.getSource()==valider_maj){  
                        try { 
                            Connexion con = new Connexion("hopital", "root", "");
                            requeteMaj = "DELETE FROM employe WHERE numero = '" + search_maj_numero.getText() + "'";
                            requeteMaj = "DELETE FROM employe WHERE nom = '" + search_maj_nom.getText() + "'";
                            requeteMaj = "DELETE FROM employe WHERE prenom = '" + search_maj_prenom.getText() + "'";
                            con.executeUpdate(requeteMaj);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }}
  
            }
            
            if("Un malade".equals(choixdemodif2)){
                 
                 saisie.setText("Veuillez remplir les champs ci-dessous:");
                 pan2.add(saisie);
                 saisie.setBounds(280,250,300,20);
                
                 pan2.add(chp_numero_tl);
                 chp_numero_tl.setBounds(350, 345, 150,20);
                 pan2.add(search_maj_numero);
                 search_maj_numero.setBounds(350,370,150,20);
                 
                 pan2.add(chp_nom_tl);
                 chp_nom_tl.setBounds(250,285,150,20);
                 pan2.add(search_maj_nom);
                 search_maj_nom.setBounds(250,310,150,20);
                 
                 pan2.add(chp_prenom_tl);
                 chp_prenom_tl.setBounds(450,285,150,20);
                 pan2.add(search_maj_prenom);
                 search_maj_prenom.setBounds(450,310,150,20);
                 
                 pan2.add(valider_maj);
                 valider_maj.setBounds(375,405,100,30);
                 
                  if(ae.getSource()==valider_maj){  
                        try { 
                            Connexion con = new Connexion("hopital", "root", "");
                            requeteMaj = "DELETE FROM malade WHERE numero = '" + search_maj_numero.getText() + "'";
                            requeteMaj = "DELETE FROM malade WHERE nom = '" + search_maj_nom.getText() + "'";
                            requeteMaj = "DELETE FROM malade WHERE prenom = '" + search_maj_prenom.getText() + "'";
                            con.executeUpdate(requeteMaj);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }}
                 
                 
            }
            
            if("Une hospitalisation".equals(choixdemodif2)){
                 
                 saisie.setText("Veuillez remplir les champs ci-dessous:");
                 pan2.add(saisie);
                 saisie.setBounds(280,250,300,20);
                
                        pan2.add(chp_num_tl);
                        chp_num_tl.setBounds(250,285,150,20);
                        pan2.add(champs_num);
                        champs_num.setBounds(250,320,150,20);
                        
                        pan2.add(cs_tl);
                        cs_tl.setBounds(450, 285, 100, 20);
                        pan2.add(code_service);
                        code_service.setBounds(450, 320, 100, 20);
                 
                 pan2.add(valider_maj);
                 valider_maj.setBounds(350,355,100,30);
                 
                  if(ae.getSource()==valider_maj){  
                        try { 
                            Connexion con = new Connexion("hopital", "root", "");
                            requeteMaj = "DELETE FROM hospitalisation WHERE no_malade = '" + champs_num.getText() + "'";
                            con.executeUpdate(requeteMaj);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }}
                 
            }
            
            if("Fin de traitement".equals(choixdemodif2)){
                
                saisie.setText("Veuillez remplir les champs ci-dessous:");
                pan2.add(saisie);
                saisie.setBounds(280,250,300,20);
                
                pan2.add(chp_numero_patient);
                chp_numero_patient.setBounds(360, 285, 150, 20);
                pan2.add(champs_numero);
                champs_numero.setBounds(360, 310, 100, 20);
                
                pan2.add(valider_maj);
                valider_maj.setBounds(360,345,100,30);
                
                if(ae.getSource()==valider_maj){  
                        try { 
                            Connexion con = new Connexion("hopital", "root", "");
                            requeteMaj = "DELETE FROM soigne WHERE no_malade = '" + champs_numero.getText() + "'";
                            con.executeUpdate(requeteMaj);
                        } catch (SQLException | ClassNotFoundException ex) {
                            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }}
            }
            
            add(pan2,"East");
            setVisible(true);
            
        }
          
        }
       
   }
     
}
