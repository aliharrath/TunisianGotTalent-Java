/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.evenement;
import Services.Serviceevenement;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author loume78
 */
public class detailevent extends BaseForm {
    Form current;
    String url = "http://localhost/testing_fos_ali/web/uploads";
    Services.Serviceevenement ps = new Serviceevenement();

    public detailevent(Form previous, evenement com) {

        setTitle("Détail du " + com.getTitre());
        getStyle().setBgColor(0xFFFFFF);
        
        
        
        Container title = new Container(BoxLayout.x());
        Container des = new Container(BoxLayout.y());
        Container dd = new Container(BoxLayout.y());
        Container df = new Container(BoxLayout.y());
        Container tp = new Container(BoxLayout.x());
        Container ct = new Container(BoxLayout.x());
        Container nb = new Container(BoxLayout.x());
        Container all = new Container(BoxLayout.y());
        

        Image placeholder = Image.createImage(1150, 600);
        EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
        URLImage urlim = URLImage.createToStorage(enc, com.getImage(), url + "/" + com.getImage());
        ImageViewer imgV = new ImageViewer();
        imgV.setImage(urlim);

        Label titre = new Label("Titre  : ");
        Label titre1 = new Label(com.getTitre());
        titre.setMaterialIcon(FontImage.MATERIAL_TITLE);
        titre.setUIID("SignUpForm");

        Label desc = new Label("Description  : " );
        //Label desc1 = new Label( com.getDescription());
        
        desc.setMaterialIcon(FontImage.MATERIAL_COMMENT);
        desc.setUIID("SignUpForm");
        
        TextField desc1 = new TextField();
        desc1.setText(com.getDescription());
        desc1.setEditable(false);
        desc1.setSingleLineTextArea(false);
        desc1.setRows(4);
        desc1.setColumns(20);
        
        Label type = new Label("Type de talent : " );
        Label type1 = new Label( com.getTypedetalent());
        type.setMaterialIcon(FontImage.MATERIAL_GRADE);
        type.setUIID("SignUpForm");
        
        Label dated = new Label("Date Debut  : " );
        Label dated1 = new Label( com.getDatedebut().toString());
        dated.setMaterialIcon(FontImage.MATERIAL_TODAY);
        dated.setUIID("SignUpForm");
        
        Label datef = new Label("Date fin  : ");
        Label datef1 = new Label( com.getDatefin().toString());
        datef.setMaterialIcon(FontImage.MATERIAL_EVENT);
        datef.setUIID("SignUpForm");
        
        Label cout = new Label("Cout  : " );
        Label cout1 = new Label(String.valueOf(com.getCout()) + " Diamond");
        
        Label nbparticipant = new Label("nb participant  : " );
        Label nbparticipant1 = new Label(String.valueOf(com.getNbparticipant()) );
        cout.setMaterialIcon(FontImage.MATERIAL_ATTACH_MONEY);
        cout.setUIID("SignUpForm");
        nbparticipant.setUIID("SignUpForm");
        nbparticipant.setMaterialIcon(FontImage.MATERIAL_GROUP);
        
        title.add(titre);
        title.add(titre1);
        des.add(desc);
        des.add(desc1);
        dd.add(dated);
        dd.add(dated1);
        df.add(datef);
        df.add(datef1);
        tp.add(type);
        tp.add(type1);
        ct.add(cout);
        ct.add(cout1);
        nb.add(nbparticipant);
        nb.add(nbparticipant1);
        
        all.add(title);
        all.add(des);
        all.add(dd);
        all.add(df);
        all.add(tp);
        all.add(ct);
        all.add(nb);



        addAll(imgV,all);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        

    }
}
