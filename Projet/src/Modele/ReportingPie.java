////https://www.tutorialspoint.com/jfreechart/jfreechart_pie_chart.htm
//source pour le devéloppement de cette classe


package Modele;

import projet.FenetrePrincipale;
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
        
    
   public ReportingPie(String title, Connexion con ) {
      super(title); 
      titre= title;
      connex=con;
      setContentPane(createDemoPanel());
   }
   
   
   
   public  PieDataset createDataset( ){
       
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
      
      
      
      return dataset;         
   }
   
   public  JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         titre,   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);
      
      return chart;
   }
   
    public  JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(createDataset( ) );   
      return new ChartPanel( chart ); 
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