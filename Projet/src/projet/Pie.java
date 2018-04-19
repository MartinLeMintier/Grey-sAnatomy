package projet;

import Controleur.Connexion;
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

    
    
    public class Pie extends ApplicationFrame 
    {
        double nb_cardio = 20;
      double nb_ortho = 20;
       double nb_trauma = 30;
        double nb_anes = 10;
         double nb_pneumo = 10;
          double nb_radio = 10;
         public Connexion connex;
         public String [] retour;
         public String [] specialite ;
    
   public Pie( String title ) {
      super( title ); 
      specialite= new String []{"orthopediste","cardiologue","traumatologue","anesthesiste","pneumologue","radiologue"};
      setContentPane(createDemoPanel( ));
   }
   
   public  PieDataset createDataset( ){
       
           try {
               requete();
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(Pie.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           
           
           
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "Orthopediste :" + retour[0] +"%" , new Double( retour[0] ) );  
      dataset.setValue( "Cardiologue : " + retour[1]  +"%" , new Double( retour[1] ) );   
      dataset.setValue( "Traumatologue :" + retour[2]  +"%" , new Double( retour[2] ) );    
      dataset.setValue( "Anesthesiste : " + retour[3]  +"%" , new Double( retour[3] ) );  
      dataset.setValue( "Pneumologue" + retour[4] +"%" , new Double( retour[4] ) );    
      dataset.setValue( "Radiologue " + retour[5]  +"%" , new Double( retour[5] ) ); 
      
      return dataset;         
   }
   
   public  JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Nombre de Docteur par Spécialité",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   
   
   public void requete() throws ClassNotFoundException
   {
           try {
               connex= new Connexion("hopital", "root", "");
               retour= new String [specialite.length];
               String requete[] = new String[specialite.length];
               
               for(int i=0; i<specialite.length;i++)
               {
                   requete[i] ="select count(specialite) from docteur where specialite='"+specialite[i]+"'";
                   retour[i]= connex.remplirChampsRequete(requete[i]).get(0);
                   System.out.println(retour[i]) ;
               }
            
                   
              
                   
               
           } catch (SQLException ex) {
               Logger.getLogger(Pie.class.getName()).log(Level.SEVERE, null, ex);
           }
   }
   public  JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(createDataset( ) );  
      return new ChartPanel( chart ); 
   }

   
}