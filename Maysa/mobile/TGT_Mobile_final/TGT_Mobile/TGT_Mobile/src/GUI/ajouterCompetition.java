/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.evenement;
import Entities.Session;
import static Entities.Session.getCurrentSession;
import Entities.fos_user;

import Services.Serviceevenement;
import com.codename1.capture.Capture;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;



import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author loume78
 */
public class ajouterCompetition extends BaseForm {

    private static String i;
    String path;
    //String f;
    TextField image;
    String filePath;
    String Imagecode;
    private ImageViewer iV;
    
    
    private FileUploader file;
    
    String fileNameInServer;
    
    private String imgPath;
    
    
    ImageViewer iv;

    public ajouterCompetition(Form previous) {
    
        Label pp = new Label();

        Container input = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        input.addComponent(new Label("Description"));
        TextField multi = new TextField();
        multi.setHint("Description here ... ");
        multi.setSingleLineTextArea(false);
        multi.setRows(4);
        multi.setColumns(20);
        input.addComponent(multi);

        setTitle("Add a new Competition");
        setLayout(BoxLayout.y());

        TextComponent name = new TextComponent().labelAndHint("Titre");
        FontImage.setMaterialIcon(name.getField().getHintLabel(), FontImage.MATERIAL_TITLE);
        
        

//        TextField tfName = new TextField("", "Titre");
//        TextField tfStatus = new TextField("", "Description");

//        TextComponent bio = new TextComponent().labelAndHint("Description");
//        FontImage.setMaterialIcon(bio.getField().getHintLabel(), FontImage.MATERIAL_COMMENT);

        TextComponent tfcout = new TextComponent().labelAndHint("Cout").multiline(true);
        FontImage.setMaterialIcon(tfcout.getField().getHintLabel(), FontImage.MATERIAL_COMMENT);
        
        TextComponent nbp = new TextComponent().labelAndHint("nbparticipant").multiline(true);
        FontImage.setMaterialIcon(nbp.getField().getHintLabel(), FontImage.MATERIAL_COMMENT);

        ComboBox<String> box = new ComboBox();
        box.addItem("Dance");
        box.addItem("Musique");
        box.addItem("Beatbox");
        box.addItem("Comédie");
;

        Button picture = new Button("parcourir");
        picture.setMaterialIcon(FontImage.MATERIAL_CLOUD_UPLOAD);
        
        
        
        TextField path = new TextField("", "aucun fichier choisi");
        path.setMaxSize(BASELINE);

        picture.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    imgPath = Capture.capturePhoto();

                    System.out.println(imgPath);
                    String link = imgPath.toString();
                    int pod = link.indexOf("/", 2);
                    String news = link.substring(pod + 2, link.length());
                    System.out.println("" + news);

                    FileUploader fu = new FileUploader("http://localhost/testing_fos_ali/web");
                    
                    fileNameInServer = fu.upload(news);
                    path.setText(fileNameInServer);

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                }
            }
        });
        
        
        Container cc = new Container(BoxLayout.x());
        cc.setName("Cover Image");

        cc.add(picture);
        cc.add(path);
        
        

//        Picker dateEvent = new Picker();
//        dateEvent.setDate(new Date());
//        dateEvent.setType(Display.PICKER_TYPE_DATE);
        PickerComponent dd = PickerComponent.createDate(null).label("Date Debut");
        dd.getPicker().setMaterialIcon(FontImage.MATERIAL_TODAY);

        PickerComponent df = PickerComponent.createDate(null).label("Date Fin");
        df.getPicker().setMaterialIcon(FontImage.MATERIAL_TODAY);

//        Picker dateEvent1 = new Picker();
//        dateEvent1.setDate(new Date());
//        dateEvent1.setType(Display.PICKER_TYPE_DATE);
        Validator val = new Validator();
        val.setValidationFailureHighlightMode(Validator.HighlightMode.EMBLEM);
        val.addConstraint(name, new LengthConstraint(2, "Titre must have at least 2 characters")).
                addConstraint(multi, new LengthConstraint(5, "Description must have at least 2 characters")).
                addConstraint(box, new LengthConstraint(1, "invalid select")).
                addConstraint(tfcout, new NumericConstraint(false, 0, 100000, "Cout must be an Integer")).
                //addConstraint(path, new LengthConstraint(1, "invalid path")).
                addConstraint(dd, new LengthConstraint(1, "invalid date")).
                addConstraint(df, new LengthConstraint(1, "invalid date"));

        TextModeLayout tl = new TextModeLayout(BASELINE, CENTER);
        Container comps = new Container(tl);

        comps.add(tl.createConstraint(), name);
        comps.add(tl.createConstraint(), input);
        comps.add(tl.createConstraint(), box);
        comps.add(tl.createConstraint(), dd);
        comps.add(tl.createConstraint(), df);
        comps.add(tl.createConstraint(), cc);
        comps.add(tl.createConstraint(), tfcout);
        comps.add(tl.createConstraint(), nbp);

        comps.setScrollableY(true);
        comps.setUIID("PaddedContainer");

        Container content = BorderLayout.center(comps);

        Button btnValider = new Button("Valider");
        btnValider.setMaterialIcon(FontImage.MATERIAL_CHECK_CIRCLE);
        
        System.out.println("this is just tet ***********"+ getCurrentSession());
        

        Container actualContent = LayeredLayout.encloseIn(content,
                FlowLayout.encloseCenter());
        fos_user user = new fos_user();
        user=getCurrentSession();
        btnValider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                if ((name.getText().length() == 0) || (multi.getText().length() == 0) || (tfcout.getText().length() == 0) || (path.getText().length() == 0) || (dd.getPicker().getDate().toString().length()==0) || (df.getPicker().getDate().toString().length()==0) ) {
                   
                    ToastBar.showMessage("Remplir tous les champs SVP ... ! ", FontImage.MATERIAL_ERROR);

                } else {
                    
                    try {

                        
                           evenement t = new evenement( name.getText(), dd.getPicker().getDate(), df.getPicker().getDate(), multi.getText(), box.getSelectedItem(), Integer.parseInt(tfcout.getText()), getCurrentSession().getId(), fileNameInServer, Integer.parseInt(nbp.getText()));
                        if (Serviceevenement.getInstance().addCompetition(t)) {
                            //Dialog.show("Success", "Connection accepted", "OK", null);
                            ToastBar.showMessage("Ajout avec succée ! ", FontImage.MATERIAL_CHECK_CIRCLE);
                           

                        } else {
                            //Dialog.show("ERROR", "Server error", "OK", null);
                            ToastBar.showMessage("Server error ! ", FontImage.MATERIAL_WARNING);
                        }

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", "OK", null);
                    }
                  
                    
                }

            }
        });

        addAll(actualContent, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
