/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author User
 */
public class SelectionServeur extends JFrame implements ActionListener
{
    
    private JPanel servt;
    private JPanel servc;
    private JPanel servb;
    private JPanel servG;
    private JLabel titre;
    private Box boite;
    private JComboBox selection;
    private JButton bouton;
    private String choix;
    
    public SelectionServeur(){
        
        this.setTitle("server");
        this.setSize(400,150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        choix="";
        
        
        servt = new JPanel ();
        servc = new JPanel ();
        servb = new JPanel ();
        servG = new JPanel ();
        titre = new JLabel("Sélectionnez le serveur auquel vous souhaitez accéder");
        boite = Box.createVerticalBox();
        bouton = new JButton("Valider");
        bouton.addActionListener(this);
        selection = new JComboBox();
        servt.setBackground(new Color(0xEDF7F7));
        servc.setBackground(new Color(0xEDF7F7));
        servG.setBackground(new Color(0xEDF7F7));
        servb.setBackground(new Color(0xEDF7F7));
            
            ///choix pour la comboBox
            selection.addItem("localhost");
            selection.addItem("gandalf");
            
        servt.add(titre);
        servc.add(selection);
        servb.add(bouton);
        
        boite.add(servt);
        boite.add(servc);
        boite.add(servb);
   
        selection.addActionListener(new ItemAction());
        choix=(String)selection.getSelectedItem();
        servG.add(boite);
        getContentPane().add(servG);
        this.setVisible(true);
        
    }
    
    //Classe permettant de prendre en compte le changement de valeur de la combobox
    class ItemAction implements ActionListener{
        
    @Override
    public void actionPerformed(ActionEvent e) {
        
        choix=(String)selection.getSelectedItem();
    }               
  }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==bouton)
        {
            this.dispose();
            FenetreAcceuil F = new FenetreAcceuil(choix);
        }
        
        
    }
    
    
}
