////https://www.tutorialspoint.com/jfreechart/jfreechart_pie_chart.htm
//source pour le devéloppement de cette classe


package Modele;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import Controleur.Connexion;

    /**
     * Classe qui permet la création des différents camemberts pour fairedes statistiques
     * @author MArgaux
     */
    
    public class ReportingPie extends ApplicationFrame 
    {
        private Connexion connex; // Connexion utilisée pour executer les requetes
        private String [] retour; // Retour des requetes
        private String [] specialite ; // Permet de faire varier les requetes, contient selon les cas différentes specialités, services, mutuelle
        private String titre; // nom des différents diagrammes
        private JFreeChart chart; // Diagramme créé
        
    /**
     * Constructeur qui recupere le titre du diagramme et la connexion
     * @param title : titre du diagramme
     * @param con : connexion pour executer les requetes
     */
   public ReportingPie(String title, Connexion con ) {
      super(title); 
      titre= title;
      connex=con;

   }
   
   
   /**
    * Recupere la requete et créé le camembert en fonction de la requete 
    * @return : retourne le camembert à afficher
    */
   public  ChartPanel createDataset( ){
       
    //Appel le sous programme qui fait la requete 
       try {
               requete();
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(ReportingPie.class.getName()).log(Level.SEVERE, null, ex);
           }
           
     // Déclaration d'une variable pour rentrer les donnees dans le camembert     
     DefaultPieDataset dataset = new DefaultPieDataset( );  
        
     //Pour chaque diagramme proposé reconnu grace a son titre remplir le diagramme avec les données remplies dans le sous programme requete
      if(titre.equals("Spécilites des docteurs"))
    {
        // remplir les données avec le tableau retour qui contient le resultat de la requete
          for(int i=0; i< specialite.length; i++)
     {
         dataset.setValue( specialite[i]+" :" + retour[i] , new Double( retour[i] ) );  
     }
    
      
    } 
      if(titre.equals("rotation des infirmieres"))
    {     // remplir les données avec le tableau retour qui contient le resultat de la requete
          for(int i=0; i< specialite.length; i++)
     {
         dataset.setValue( specialite[i]+" :" + retour[i] , new Double( retour[i] ) );  
     }
 
    } 
      
      if(titre.equals("Personnes hospitalisees par service"))
        {
             // remplir les données avec le tableau retour qui contient le resultat de la requete
              for(int i=0; i< specialite.length; i++)
     {
         dataset.setValue( specialite[i]+" :" + retour[i] , new Double( retour[i] ) );  
     }

        }
       if(titre.equals("Infirmiers par service"))
        {
             // remplir les données avec le tableau retour qui contient le resultat de la requete
              for(int i=0; i< specialite.length; i++)
     {
         dataset.setValue( specialite[i]+" :" + retour[i] , new Double( retour[i] ) );  
     }

        }
      
       if(titre.equals("Malades par mutuelle"))
    {
         // remplir les données avec le tableau retour qui contient le resultat de la requete
     for(int i=0; i< specialite.length; i++)
     {
         dataset.setValue( specialite[i]+" :" + retour[i] , new Double( retour[i] ) );  
     }

     
    } 
      // Création du chart avec les données créées 
      chart = ChartFactory.createPieChart(      
         titre,   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);
      
      // Variable associée au camembert qui prend le chart créé
      ChartPanel cam = new ChartPanel(chart) ;
     
      return cam;
      
      
           
   }
   
 
   /**
    * Methode qui permet pour chaque camembert reconnu grace a son titre, de recuperer la requete grace à connexion, et remplir un tableau avec le resulatta de la requete
    * @throws ClassNotFoundException 
    */
   
   public void requete() throws ClassNotFoundException
   {
           try {
               //declaration de la requete
                String requete[];
               
                // selon le diagramme demandé, executer la bonne requete et remplir le tableau retour avec le resultat de la requete
               if(titre.equals("Spécilites des docteurs"))
               {
                   //Déclaration des variables 
                    specialite= new String []{"orthopediste","cardiologue","traumatologue","anesthesiste","pneumologue","radiologue"};
                    retour= new String [specialite.length];
                    requete= new String[specialite.length];  
                    
                    for(int i=0; i<specialite.length;i++)
                  {
                      // requete prend la valeur desirée
                      requete[i] ="select count(specialite)/32 from docteur where specialite='"+specialite[i]+"'";
                      // retour prend la valeur du retour de l'executiuon de la requete
                      retour[i]= connex.remplirChampsRequete(requete[i]).get(0);
                  }
    
               }
               
                 if(titre.equals("rotation des infirmieres"))
               {
                   //Déclaration des variables 
                    specialite= new String []{"nuit","jour"};
                    retour= new String [specialite.length];
                    requete = new String[specialite.length];
                    
                    for(int i=0; i<specialite.length;i++)
                  {
                      // requete prend la valeur desirée
                    requete[i] ="select count(rotation)/28 from infirmier where rotation='"+specialite[i]+"'";
                    // retour prend la valeur du retour de l'executiuon de la requete
                    retour[i]= connex.remplirChampsRequete(requete[i]).get(0);
                  }                  
            
               }
              if(titre.equals("Personnes hospitalisees par service"))
        {
            //Déclaration des variables 
                   specialite= new String []{"CAR","CHG","REA"};
                   retour= new String [specialite.length];
                   requete = new String[specialite.length];
                    
                    for(int i=0; i<specialite.length;i++)
                  {
                      // requete prend la valeur desirée
                      requete[i] ="SELECT count(no_malade)/39 FROM hospitalisation WHERE code_service='"+specialite[i]+"'";
                      // retour prend la valeur du retour de l'executiuon de la requete
                      retour[i]= connex.remplirChampsRequete(requete[i]).get(0);
                  }                  
           
        }   
               if(titre.equals("Infirmiers par service"))
        {
                    //Déclaration des variables 
                    specialite= new String []{"CAR","CHG","REA"};
                    retour= new String [specialite.length];
                    requete = new String[specialite.length];
                    
                    for(int i=0; i<specialite.length;i++)
                  {
                      // requete prend la valeur desirée
                      requete[i] ="SELECT count(numero)/28 FROM infirmier WHERE code_service='"+specialite[i]+"'";
                      // retour prend la valeur du retour de l'executiuon de la requete
                      retour[i]= connex.remplirChampsRequete(requete[i]).get(0);
                  }                  
           
        }  
               
               if(titre.equals("Malades par mutuelle"))
        {
                    //Déclaration des variables 
                    specialite= new String []{"MNAM","LMDE","MNH","MAAF","AG2R","CCVRP","CNAMTS","MAS","MGEN","MGSP","MMA","MNFTC"};
                    retour= new String [specialite.length];
                    requete = new String[specialite.length];
                    
                    for(int i=0; i<specialite.length;i++)
                  {
                      // requete prend la valeur desirée
                      requete[i] ="SELECT count(numero)/80 FROM malade WHERE mutuelle='"+specialite[i]+"'";
                      // retour prend la valeur du retour de l'executiuon de la requete
                      retour[i]= connex.remplirChampsRequete(requete[i]).get(0);
                  }                  
           
        }   
           } catch (SQLException ex) {
               Logger.getLogger(ReportingPie.class.getName()).log(Level.SEVERE, null, ex);
           }
   }
    
}