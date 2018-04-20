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

    
    
    public class ReportingPie extends ApplicationFrame 
    {
        private Connexion connex;
        private String [] retour;
        private String [] specialite ;
        private String total ;
        private String titre;
        private JFreeChart chart;
        
    
   public ReportingPie(String title, Connexion con ) {
      super(title); 
      titre= title;
      connex=con;

   }
   
   
   
   public  ChartPanel createDataset( ){
       
           try {
               requete();
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(ReportingPie.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           
     DefaultPieDataset dataset = new DefaultPieDataset( );  
           
      if(titre.equals("Spécilites des docteurs"))
    {
     
      dataset.setValue( "Orthopediste :" + retour[0] , new Double( retour[0] ) );  
      dataset.setValue( "Cardiologue : " + retour[1], new Double( retour[1] ) );   
      dataset.setValue( "Traumatologue :" + retour[2] , new Double( retour[2] ) );    
      dataset.setValue( "Anesthesiste : " + retour[3] , new Double( retour[3] ) );  
      dataset.setValue( "Pneumologue" + retour[4], new Double( retour[4] ) );    
      dataset.setValue( "Radiologue " + retour[5], new Double( retour[5] ) ); 
      
    } 
      if(titre.equals("rotation des infirmieres"))
    {    
      dataset.setValue( "Jour :" + retour[0] , new Double( retour[0] ) );  
      dataset.setValue( "Nuit : " + retour[1], new Double( retour[1] ) );   
    } 
      
      if(titre.equals("Personnes hospitalisees par service"))
        {
           dataset.setValue( "CAR :" + retour[0] , new Double( retour[0] ) );  
           dataset.setValue( "CHG : " + retour[1], new Double( retour[1] ) );   
           dataset.setValue( "REA :" + retour[2] , new Double( retour[2] ) );
        }
       if(titre.equals("Infirmiers par service"))
        {
           dataset.setValue( "CAR :" + retour[0] , new Double( retour[0] ) );  
           dataset.setValue( "CHG : " + retour[1], new Double( retour[1] ) );   
           dataset.setValue( "REA :" + retour[2] , new Double( retour[2] ) );
        }
      
       if(titre.equals("Malades par mutuelle"))
    {
     
      dataset.setValue( "MNAM :" + retour[0] , new Double( retour[0] ) );  
      dataset.setValue( "LMDE : " + retour[1], new Double( retour[1] ) );   
      dataset.setValue( "MNH:" + retour[2] , new Double( retour[2] ) );    
      dataset.setValue( "MAAF : " + retour[3] , new Double( retour[3] ) );  
      dataset.setValue( "AG2R" + retour[4], new Double( retour[4] ) );    
      dataset.setValue( "CCVRP " + retour[5], new Double( retour[5] ) ); 
       dataset.setValue( "CNAMTS :" + retour[6] , new Double( retour[6] ) );  
      dataset.setValue( "MAS : " + retour[7], new Double( retour[7] ) );   
      dataset.setValue( "MGEN :" + retour[8] , new Double( retour[8] ) );    
      dataset.setValue( "MGSP : " + retour[9] , new Double( retour[9] ) );  
      dataset.setValue( "MMA" + retour[10], new Double( retour[10] ) );    
      dataset.setValue( "MNFTC " + retour[11], new Double( retour[11] ) ); 
      
    } 
       
      chart = ChartFactory.createPieChart(      
         titre,   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);
      
      
      ChartPanel cam = new ChartPanel(chart) ;
     
      return cam;
      
      
           
   }
   
 
   
   
   public void requete() throws ClassNotFoundException
   {
           try {
                String requete[];
               
               if(titre.equals("Spécilites des docteurs"))
               {
                    specialite= new String []{"orthopediste","cardiologue","traumatologue","anesthesiste","pneumologue","radiologue"};

                    retour= new String [specialite.length];
                    requete= new String[specialite.length];  
                    
                    for(int i=0; i<specialite.length;i++)
                  {
                      requete[i] ="select count(specialite)/32 from docteur where specialite='"+specialite[i]+"'";
                      retour[i]= connex.remplirChampsRequete(requete[i]).get(0);
                      System.out.println(retour[i]) ;
                  }
    
               }
               
                 if(titre.equals("rotation des infirmieres"))
               {
                    specialite= new String []{"nuit","jour"};

                    retour= new String [specialite.length];
                    requete = new String[specialite.length];
                    
                    for(int i=0; i<specialite.length;i++)
                  {
                      requete[i] ="select count(rotation)/28 from infirmier where rotation='"+specialite[i]+"'";
                      retour[i]= connex.remplirChampsRequete(requete[i]).get(0);
                      System.out.println(retour[i]) ;
                  }                  
            
               }
              if(titre.equals("Personnes hospitalisees par service"))
        {
            specialite= new String []{"CAR","CHG","REA"};

                    retour= new String [specialite.length];
                    requete = new String[specialite.length];
                    
                    for(int i=0; i<specialite.length;i++)
                  {
                      requete[i] ="SELECT count(no_malade) FROM hospitalisation WHERE code_service='"+specialite[i]+"'";
                      retour[i]= connex.remplirChampsRequete(requete[i]).get(0);
                      System.out.println(retour[i]) ;
                  }                  
           
        }   
               if(titre.equals("Infirmiers par service"))
        {
            specialite= new String []{"CAR","CHG","REA"};

                    retour= new String [specialite.length];
                    requete = new String[specialite.length];
                    
                    for(int i=0; i<specialite.length;i++)
                  {
                      requete[i] ="SELECT count(numero) FROM infirmier WHERE code_service='"+specialite[i]+"'";
                      retour[i]= connex.remplirChampsRequete(requete[i]).get(0);
                      System.out.println(retour[i]) ;
                  }                  
           
        }  
               
               if(titre.equals("Malades par mutuelle"))
        {
            specialite= new String []{"MNAM","LMDE","MNH","MAAF","AG2R","CCVRP","CNAMTS","MAS","MGEN","MGSP","MMA","MNFTC"};

                    retour= new String [specialite.length];
                    requete = new String[specialite.length];
                    
                    for(int i=0; i<specialite.length;i++)
                  {
                      requete[i] ="SELECT count(numero) FROM malade WHERE mutuelle='"+specialite[i]+"'";
                      retour[i]= connex.remplirChampsRequete(requete[i]).get(0);
                      System.out.println(retour[i]) ;
                  }                  
           
        }   
              
              
                   
              
                   
               
           } catch (SQLException ex) {
               Logger.getLogger(ReportingPie.class.getName()).log(Level.SEVERE, null, ex);
           }
   }
   
   
   
//      public  PieDataset createDataset2( ){
//       
//           try {
//               requete2();
//           } catch (ClassNotFoundException ex) {
//               Logger.getLogger(Pie.class.getName()).log(Level.SEVERE, null, ex);
//           }
//             
//           
//      DefaultPieDataset dataset = new DefaultPieDataset( );
//      dataset.setValue( "Infirmiers de Jour :" + retour[0] , new Double( retour[0] ) );  
//      dataset.setValue( "Infirmier de Nuit : " + retour[1], new Double( retour[1] ) );   
//     
//      
//      return dataset;         
//   }
   
//   public  JFreeChart createChart2( PieDataset dataset ) {
//      JFreeChart chart = ChartFactory.createPieChart(      
//         "Nombre d'infirmier par garde",   // chart title 
//         dataset,          // data    
//         true,             // include legend   
//         true, 
//         false);
//      
//      return chart;
//   }
//   
//    public  JPanel createDemoPanel2( ) {
//      JFreeChart chart = createChart2(createDataset2( ) );   
//      return new ChartPanel( chart ); 
//   }
//    
    
//       public void requete2() throws ClassNotFoundException
//   {
//           try {
//               connex= new Connexion("hopital", "root", "");
//               retour= new String [specialite.length];
//               String requete2[] = new String[specialite.length];
//               
//               for(int i=0; i<specialite.length;i++)
//               {
//                   requete2[i] ="select count(rotation) from infirmier where rotation='"+specialite[i]+"'";
//                   retour[i]= connex.remplirChampsRequete(requete2[i]).get(0);
//                   System.out.println(retour[i]) ;
//               }
//           
//               
//           } catch (SQLException ex) {
//               Logger.getLogger(Pie.class.getName()).log(Level.SEVERE, null, ex);
//           }
//   }
//  

   
}