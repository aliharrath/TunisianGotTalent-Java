/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static Entities.Session.getCurrentSession;
import Entities.evenement;
import Entities.participation;
import Entities.fos_user;
import Services.Serviceevenement;
import Services.ServiceUser;
import static Utils.Statics.BASE_URL;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author loume78
 */
public class afficherevent extends BaseForm {

    Form current;

    Form f;
    String url = "http://localhost/testing_fos_ali/web/uploads";
    //String url = "http://localhost:8080/PI/web/uploads";
    EncodedImage enc;
    ServiceUser fos = new ServiceUser();

    Services.Serviceevenement ps = new Serviceevenement();
    com.codename1.ui.util.Resources resourceObjectInstance;

    public afficherevent(Form previous) {
        
        
        
        current = this;

        ArrayList<evenement> lis = ps.getAllCompetitions();
        getStyle().setBgColor(0xFFFFFF);
        setTitle("Bienvenue à nos evenement");
        System.out.println(lis);

//
//        for (int i = 0; i < lis.size(); i++) {
//            
//            
//            Image placeholder1 = Image.createImage(250, 200);
//                EncodedImage en = EncodedImage.createFromImage(placeholder1, false);
//                URLImage urli = URLImage.createToStorage(en, lis.get(i).getImageC(), url + "/" +lis.get(i).getImageC());
//                ImageViewer img = new ImageViewer();
//                img.setImage(urli);
//            Label titre = new Label(lis.get(i).getTitre());
//            Label cout = new Label(String.valueOf(lis.get(i).getCout()));
//            add(img);
//            add(titre);
//            add(cout);
//            
//            
//        }
        for (evenement li : lis) {
           
            evenement com =new evenement(li.getId_evenement(), li.getTitre(), li.getDatedebut(), li.getDatefin(), li.getDescription(), li.getTypedetalent(), li.getCout(), li.getIduser(), li.getImage(), li.getNbparticipant());
            System.out.println(li.getIduser());
            Container c = new Container(BoxLayout.y());
            c.getStyle().setBorder(Border.createLineBorder(2));
            c.getStyle().setMargin(1, 1, 1, 1);
            c.getStyle().setPadding(0, 0, 0, 0);
            c.getStyle().setBgTransparency(255);
            c.getStyle().setBgColor(0xFDF2E9);

            Image placeholder = Image.createImage(1150, 420);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, li.getImage(), url + "/" + li.getImage());
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);

            

            Container title = new Container(BoxLayout.x());


            
            Label titre1 = new Label("              Titre : "+com.getTitre());
            titre1.setAlignment(CENTER);

            titre1.setUIID("SignUpForm");

            
            title.add(titre1);

            titre1.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new detailevent(current, com).show();
                }
            });
            
                        Container cout = new Container(BoxLayout.x());

            //Label desc = new Label("Cout : " + String.valueOf(li.getCout()) + " Diamond");
            Label desc = new Label("Cout : " );
            Label desc1 = new Label( String.valueOf(li.getCout()) + " Diamond");
            desc.setMaterialIcon(FontImage.MATERIAL_ATTACH_MONEY);
            desc.setUIID("SignUpForm");
            cout.add(desc);
            cout.add(desc1);
            
                        Container type = new Container(BoxLayout.x());


            Label aa = new Label("Type de talent : ");
            Label aa1 = new Label(com.getTypedetalent());
            aa.setMaterialIcon(FontImage.MATERIAL_GRADE);
            aa.setUIID("SignUpForm");
            
            type.add(aa);
            type.add(aa1);

            Container btn = new Container(BoxLayout.x());

            if (getCurrentSession().getId() == li.getIduser()) {

                Button remove = new Button(FontImage.MATERIAL_DELETE_FOREVER, "Remove");
//            remove.getStyle().setBgTransparency(255);
//            remove.getStyle().setBgColor(0xde4223);

                remove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ps.supprimerComp(li.getId_evenement());
                        new afficherevent(current).show();
                    }
                });

                remove.setText("");
                Button edit = new Button(FontImage.MATERIAL_CREATE, "Edit");
                edit.setText("");

                edit.addActionListener(e -> new modifCompetition(current, com).show());

                btn.add(remove);
                btn.add(edit);

            }
            
            
            


                

            
            c.add(title);
            c.add(imgV);
            Container det = new Container(BoxLayout.x());

            Container det1 = new Container(BoxLayout.y());

            
            det1.add(cout);
            det1.add(type);
            det.add(det1);
            det.add(btn);
            c.add(det);

            add(c);

        }

        getToolbar().addMaterialCommandToOverflowMenu("Ajouter evenement", FontImage.MATERIAL_ADD_CIRCLE, (e) -> {
            new ajouterCompetition(current).show();

        });
        
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_CANCEL, e
//                -> {
//            new SignInForm(resourceObjectInstance).show();
//        });

         installSidemenu(resourceObjectInstance);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_CANCEL, e
//                -> {
//            new SignInForm(resourceObjectInstance).show();
//        });
    }
    
    @Override
    protected boolean isCurrentCalendar() {
        return true;
    }
    
    
    }
    


