/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Groupe;
import Services.ServiceGroupe;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;

import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;



/**
 *
 * @author HP
 */
public class DetailGroupeForm extends Form{
ArrayList<String> L=new ArrayList<>();
     public DetailGroupeForm(Form previous,Resources theme,Groupe g) {
       
         setTitle("Welcome "+g.getNom());
            L=ServiceGroupe.getInstance().getAllNameUser();

         Button membre = new Button("Membres du groupe");
                        membre.getUnselectedStyle().setFgColor(5542241);
                        membre.addActionListener((e) -> {
                                  String names="";
                                    Dialog d = new Dialog("Liste des membres de groupe");
                                    TextArea popupBody = new TextArea(/*L.toString()*/);
                            //    System.out.print(ServiceGroupe.getInstance().getAllNameUser().toString());
                            // username=ServiceGroupe.getInstance().getAllNameUser();
                            //  System.out.print(username);
                            //     System.out.print(names);
                            System.out.print(L);
                                    Button OK = new Button("Okey!");
                                    OK.getUnselectedStyle().setBgColor(5542241);
                                    OK.addActionListener((ee) -> d.dispose());
                                        try {
                                            EncodedImage  encImg=EncodedImage.create("/team.png");
                                            ImageViewer image = new ImageViewer(encImg);                                   
                                            popupBody.setUIID("PopupBody");
                                            popupBody.setEditable(false);
                                            d.setLayout(new BorderLayout());
                                            d.add(BorderLayout.CENTER,image);
                                            d.add(BorderLayout.CENTER, popupBody);
                                            d.addComponent(BorderLayout.SOUTH, OK);
                                            d.show();
                                        } catch (IOException ex) {
                                                                      }
                 });
                        Button Ajout = new Button("Ajouter une publication");
                        Ajout.getUnselectedStyle().setFgColor(5542241);
                        Ajout.addActionListener((e)->{}); 
                         
         this.add(membre);
         this.add(Ajout);
       //  this.add()
                        
                       
                          
                          
    }
    
}
