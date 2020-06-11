/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
    public HomeForm(Resources theme) {
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
        btnListMG.addActionListener(e-> new ListGroupsForm(current,theme).show());
        btnListOG.setIcon(FontImage.createMaterial(FontImage.MATERIAL_PAGEVIEW, btnListMG.getUnselectedStyle()));
        btnListGI.addActionListener(e-> new GroupeImIN(current,theme).show());
        btnListOG.addActionListener(e-> new ListGroupsForm(current,theme).show());
        addAll(btnListMG);
        addAll(btnListGI);
        addAll(btnListOG);
    }
    
    
}
