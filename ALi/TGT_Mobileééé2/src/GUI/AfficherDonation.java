/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Donation;
import static Entities.Session.getCurrentSession;
import Services.ServiceDonation;
import Services.ServiceFacture;
import Services.ServiceUser;
import Utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import java.util.ArrayList;

/**
 *
 * @author Ali
 */
public class AfficherDonation  extends BaseForm  {
    Form current;

    Form f;
    String url = "http://localhost/testing_fos_ali/web/images";
    //String url = "http://localhost:8080/PI/web/uploads";
    EncodedImage enc;
    ServiceUser fos = new ServiceUser();

    Services.ServiceDonation ds = new ServiceDonation();
    Services.ServiceFacture fs = new ServiceFacture();
    com.codename1.ui.util.Resources resourceObjectInstance;

    public AfficherDonation(Form previous) {
        
        
        
        current = this;

        ArrayList<Donation> lis = ds.getalldonations();
        getStyle().setBgColor(0xFFFFFF);
        setTitle("Bienvenue à nos dons");
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
        for (Donation li : lis) {
           
            Donation don =new Donation(li.getLib_donation(),li.getDate_cr(), li.getValeur_d(), li.getPhoto(), li.getDescription(), li.getCategorie(), li.getUser_id());
            System.out.println(li.getUser_id());
            
            Container c = new Container(BoxLayout.y());
            c.getStyle().setBorder(Border.createLineBorder(2));
            c.getStyle().setMargin(1, 1, 1, 1);
            c.getStyle().setPadding(0, 0, 0, 0);
            c.getStyle().setBgTransparency(255);
            c.getStyle().setBgColor(0xFDF2E9);

            Image placeholder = Image.createImage(1150, 420);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, li.getPhoto(), url + "/" + li.getPhoto());
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);

            

            Container title = new Container(BoxLayout.x());


            
            Label titre1 = new Label("              Titre : "+don.getLib_donation());
            titre1.setAlignment(CENTER);

            titre1.setUIID("SignUpForm");

            
            title.add(titre1);

            titre1.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    //new detailevent(current, com).show();
                }
            });
            
                        Container cout = new Container(BoxLayout.x());

            //Label desc = new Label("Cout : " + String.valueOf(li.getCout()) + " Diamond");
            Label desc = new Label("Valeur : " );
            Label desc1 = new Label( String.valueOf(li.getValeur_d()) + " Diamond");
            desc.setMaterialIcon(FontImage.MATERIAL_ATTACH_MONEY);
            desc.setUIID("SignUpForm");
            cout.add(desc);
            cout.add(desc1);
            
                        Container type = new Container(BoxLayout.x());


            Label aa = new Label("Catégorie : ");
            Label aa1 = new Label(don.getCategorie());
            aa.setMaterialIcon(FontImage.MATERIAL_GRADE);
            aa.setUIID("SignUpForm");
            
            type.add(aa);
            type.add(aa1);

            Container btn = new Container(BoxLayout.x());

            if (getCurrentSession().getId() == li.getUser_id()) {
                if(getCurrentSession().getTypec().equals("chasseur")){

                Button remove = new Button(FontImage.MATERIAL_DELETE_OUTLINE, "Remove");
//          remove.getStyle().setBgTransparency(255);
//            remove.getStyle().setBgColor(0xde4223);

                remove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       ds.supprimerdon(li.getId());
                      new AfficherDonation(current).show();
                    }
                });

                remove.setText("");
                
                
                Button edit = new Button(FontImage.MATERIAL_CREATE, "Edit");
                edit.setText("");

                edit.addActionListener(e -> new ModifierDonation(current, don).show());

                btn.add(remove);
                
                btn.add(edit);
                }
            }
            if(getCurrentSession().getTypec().equals("talent")){
                Form hi = new Form("PDF Viewer", BoxLayout.y());
            Button recup = new Button(FontImage.MATERIAL_TOUCH_APP, "Récuperer");
                 recup.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       fs.addFacture(li);
                       FileSystemStorage as = FileSystemStorage.getInstance();
    String fileName = as.getAppHomePath() + "pdf-sample.pdf";
    if(!as.exists(fileName)) {
        Util.downloadUrlToFile(Statics.BASE_URL +"/facture/"+li.getId() +"/pdfM", fileName, true);
    }
    Display.getInstance().execute(fileName);
                      //new AfficherDonation(current).show();
                    }
                });
                 
                 btn.add(recup);
            
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

        getToolbar().addMaterialCommandToOverflowMenu("Ajouter Donation", FontImage.MATERIAL_ADD_CIRCLE, (e) -> {
            new AjoutDonation(current).show();

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
