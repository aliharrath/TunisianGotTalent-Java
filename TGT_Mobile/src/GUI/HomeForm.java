/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Groupe;
import Entities.fos_user;
import Services.ServiceGroupe;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
ArrayList<String> L=new ArrayList<>();
    public HomeForm(Resources theme,fos_user u) {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
        Button btnListMG = new Button("My Groups");
        btnListMG.setIcon(FontImage.createMaterial(FontImage.MATERIAL_GROUP, btnListMG.getUnselectedStyle()));
        Button btnListGI= new Button("Groupe I'm in");
        btnListGI.setIcon(FontImage.createMaterial(FontImage.MATERIAL_FACE, btnListMG.getUnselectedStyle()));
        Button btnListOG = new Button("Autre Groupe");
       // btnListMG.addActionListener(e-> new ListGroupsForm(current,theme).show());
        btnListMG.addActionListener(e-> new ListGroupsForm(current,theme,u).show());
        btnListOG.setIcon(FontImage.createMaterial(FontImage.MATERIAL_PAGEVIEW, btnListMG.getUnselectedStyle()));
        L=ServiceGroupe.getInstance().getAllNameUser();
        btnListGI.addActionListener(e-> System.out.print(L));
             
    //  btnListGI.addActionListener(e-> new GroupeImIN(current,theme,u).show());
     //   btnListOG.addActionListener(e-> new ListGroupsForm(current,theme,u).show());
       btnListOG.addActionListener((e) -> {
                                  String names="";
                                    Dialog d = new Dialog("Liste des membres de groupe");
                                    TextArea popupBody = new TextArea(L.toString());
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
        addAll(btnListMG);
        addAll(btnListGI);
        addAll(btnListOG);
    }
    
    
}
