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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MArgaux
 */
public class FenetrePrincipale extends JFrame 
{
    JPanel pan, pan2;
    JButton bouton, bouton2, bouton3;
    protected Connexion conni;
   
   private final java.awt.List listeDeTables, listeDeRequetes;
   private final JTextArea fenetreLignes, fenetreRes;
    
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
                
                // creation des listes pour les tables et les requetes
                listeDeTables = new java.awt.List(10, false);
                listeDeRequetes = new java.awt.List(10, false);
                fenetreLignes = new JTextArea();
                fenetreRes = new JTextArea();
                
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
    
     ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////Source : Code JDBC//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
       /**
     * Méthode privée qui initialise la liste des tables
     */
    private void remplirTables()
    
    {
        conni.ajouterTable("chambre");
        conni.ajouterTable("docteur");
        conni.ajouterTable("employe");
        conni.ajouterTable("hospitalisation");
        conni.ajouterTable("infirmier");
        conni.ajouterTable("malade");
        conni.ajouterTable("service");
        conni.ajouterTable("soine");
    }

    /**
     * Méthode privée qui initialise la liste des requetes de selection
     */
    private void remplirRequetes() 
    
    {
        conni.ajouterRequete("SELECT prenom, nom FROM malade WHERE mutuelle ='MAAF’;");
        conni.ajouterRequete("SELECT prenom, nom FROM infirmier JOIN employe ON infirmier.numero = employe.numero WHERE rotation = 'NUIT’;");
        conni.ajouterRequete("SELECT service.nom, batiment, prenom, employe.nom, specialite FROM service JOIN docteur ON directeur = docteur.numero JOIN employe ON directeur = employe.numero;");
        
        conni.ajouterRequete("SELECT chambre.no_chambre, lit , service.nom, prenom, malade.nom, mutuelle \n" +
        "FROM chambre \n" +
        "JOIN service ON chambre.code_service = code\n" +
        "JOIN hospitalisation ON chambre.code_service = hospitalisation.code_service and chambre.no_chambre = hospitalisation.no_Chambre\n" +
        "JOIN malade ON numero = no_Malade\n" +
        "WHERE batiment = 'B' AND mutuelle LIKE 'MN%';");
        
        conni.ajouterRequete("select code_Service, avg(Salaire) From infirmier Group By code_service ;");
        conni.ajouterRequete("select code_service, AVG(nb_lits) From chambre Join service on code_service = Code Where batiment = 'A' Group By code_service;");
        
        conni.ajouterRequete("select nom, prenom, count(no_docteur), count(distinct specialite)\n" +
        "From soigne\n" +
        "Join malade on malade.numero = no_malade\n" +
        "Join docteur on docteur.numero = soigne.no_docteur\n" +
        "Group By no_malade\n" +
        "Having count(no_docteur) >3;");
        
        conni.ajouterRequete("select nom, count(infirmier.numero)/ count(no_malade)\n" +
        "From service\n" +
        "Join hospitalisation on service.code= hospitalisation.code_service \n" +
        "Join infirmier on service.code= infirmier.code_service \n" +
        "Group By nom \n" +
        "Order by nom ;");
        
        conni.ajouterRequete("select prenom, nom, count(no_malade) \n" +
        "From docteur\n" +
        "join employe on docteur.numero = employe.numero \n" +
        "Join soigne on docteur.numero=no_docteur \n" +
        "Group by prenom, nom \n" +
        "Having count(*)>0 \n" +
        "order by nom ");
        
        conni.ajouterRequete("select prenom, nom \n" +
        "From Docteur join Employe on Docteur.Numero = Employé.Numéro\n" +
        "Left Join soigne on Docteur.Numero=No_Docteur\n" +
        "Where No_Malade = Null;");
        
        conni.ajouterRequete("select code_service, count(infirmier.numero) From infirmier Group By code_service ;");
        
    }

    /**
     * Méthode privée qui initialise la liste des requetes de MAJ
     */
    private void remplirRequetesMaj() {
        // Requêtes d'insertion
        conni.ajouterRequeteMaj("INSERT INTO Dept (deptno,dname,loc) VALUES (50,'ECE','Paris');");

        // Requêtes de modification
        conni.ajouterRequeteMaj("UPDATE Dept SET loc='Eiffel' WHERE loc='Paris';");

        // Requêtes de suppression
        conni.ajouterRequeteMaj("DELETE FROM Dept WHERE loc='Eiffel';");

    }

    /**
     *
     * Afficher les tables
     */
    public void afficherTables() {
        for (String table : conni.tables) {
            listeDeTables.add(table);
        }
    }

    /**
     *
     * Afficher les lignes de la table sélectionnée
     */
    public void afficherLignes(String nomTable) {
        try {
            ArrayList<String> liste;

            // effacer les résultats
            fenetreLignes.removeAll();

            // recupérér les résultats de la table selectionnee
            liste = conni.remplirChampsTable(nomTable);

            // afficher les champs de la table selectionnee 
            fenetreLignes.setText("");
            for (String liste1 : liste) {
                fenetreLignes.append(liste1);
            }

            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select * from " + nomTable + ";";
            liste = conni.remplirChampsRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste
            for (String liste1 : liste) {
                fenetreLignes.append(liste1);
            }

        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
            fenetreRes.setText("");
            fenetreRes.append("Echec table SQL");
            e.printStackTrace();

        }
    }

    /**
     *
     * Afficher les requetes de selection et de MAJ dans la fenetre
     */
    public void afficherRequetes() {
        for (String requete : conni.requetes) {
            listeDeRequetes.add(requete);
        }
    }
    
    
    
        /**
     *
     * Afficher et retourner les résultats de la requete sélectionnée
     *
     * @param requeteSelectionnee
     */
    public ArrayList<String> afficherRes(String requeteSelectionnee) throws SQLException {
        ArrayList<String> liste = null;
        try {

            // effacer les résultats
            fenetreRes.removeAll();

            // recupérér les résultats de la requete selectionnee
            liste = conni.remplirChampsRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste
            fenetreRes.setText("");
            for (String liste1 : liste) {
                fenetreRes.append(liste1);
            }
        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
            fenetreRes.setText("");
            fenetreRes.append("Echec requete SQL");
        }
        return liste;
    }
    
    
    
    
 
}
