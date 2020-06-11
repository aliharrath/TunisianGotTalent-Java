/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static Entities.Session.getCurrentSession;
import Entities.evenement;
import Entities.participation;
import Entities.ticket;
import Services.ServiceParticipation;
import Services.ServiceTicket;
import Services.ServiceUser;
import Services.Serviceevenement;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author maysa1998
 */
public class participer extends BaseForm {

    Form current;

    Form f;
    String url = "http://localhost/testing_fos_ali/web/uploads";
    EncodedImage enc;

    Services.Serviceevenement ps = new Serviceevenement();
    ServiceTicket tic = new ServiceTicket();
    ServiceParticipation par = new ServiceParticipation();
    ServiceUser us = new ServiceUser();
    com.codename1.ui.util.Resources resourceObjectInstance;

    public participer(Form previous) {
        current = this;
        ArrayList<evenement> lis = ps.getAllCompetitions();
        getStyle().setBgColor(0xFFFFFF);
        setTitle("Bienvenue à nos evenements");

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
            evenement com = new evenement(li.getId_evenement(), li.getTitre(),li.getDatedebut(), li.getDatefin(), li.getDescription(), li.getTypedetalent(),  li.getCout(),li.getIduser(), li.getImage(),li.getNbparticipant());

        
            Container c = new Container(BoxLayout.y());
            c.getStyle().setBorder(Border.createLineBorder(2));
            c.getStyle().setMargin(1, 1, 1, 1);
            c.getStyle().setPadding(0, 0, 0, 0);
            c.getStyle().setBgColor(0xC40C0C);

            Image placeholder = Image.createImage(1150, 420);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, li.getImage(), url + "/" + li.getImage());
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);

                    

            TextField a = new TextField(li.getId_evenement());

            Label aa = new Label("Titre  : " + li.getTitre().toString());
            aa.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new detailevent(current,com).show();
                }
            });
    
            
            
            Label desc = new Label("Cout : " + String.valueOf(li.getCout()) + " Diamond");
            Button details = new Button(FontImage.MATERIAL_LOCAL_OFFER, "Details");
                        details.setText("Ticket");

//            remove.getStyle().setBgTransparency(255);
//            remove.getStyle().setBgColor(0xde4223);

            details.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                      
                     Dialog d=new Dialog("Ticket pour : "+li.getTitre()+"         "); 
                     
                     d.setHeight(400);
                     d.setWidth(300);
                     Button close = new Button("Close");
                     close.addActionListener((ee) -> d.dispose());

                     
                    
                     Image img=null;  
                     try 
                     { 
                         img=Image.createImage("/logo.png");
                         img.scaled(350, 250);
                     }
                     catch (IOException ex) 
                     { 
                         Dialog.show("Erreur", ex.getMessage(), "OK", null); 
                     }
                     Label l=new Label(img); 
                     Label l1=new Label(); 
                     Label l2=new Label(); 
                     Label l3=new Label(); 
                     l.setText("Bienvenue Mr/Mme "+getCurrentSession().getUsername()
                     );
                     l1.setText(" dans notre Evenement intitulé :"+li.getTitre());
                     l2.setText(" qui aura lieu le :");
                     l3.setText(li.getDatedebut().toString());
                      
                     l.setTextPosition(Label.BOTTOM);
                     l1.setTextPosition(Label.BOTTOM);
                     l2.setTextPosition(Label.BOTTOM);
                     l3.setTextPosition(Label.BOTTOM);
                      Container box = new Container(BoxLayout.y());
                     
                     
                      box.add(l);
                      box.add(l1);
                      box.add(l2);
                      box.add(l3);
                      box.add(close);
                     d.addComponent(box);
                     
                     d.show();


                }
            });
            Container btn = new Container(BoxLayout.y());
            
            boolean find=par.FindParticipation(37, 162);
            System.out.println(find);
            
            if((!par.FindParticipation(getCurrentSession().getId(), li.getId_evenement())) && (getCurrentSession().getNb_diamants()>li.getCout()) && li.getNbparticipant()!=0)
            {
                Button remove = new Button(FontImage.MATERIAL_ADD_CIRCLE, "Participer");
//            remove.getStyle().setBgTransparency(255);
//            remove.getStyle().setBgColor(0xde4223);

                remove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        participation p=new participation(getCurrentSession().getId(),li.getId_evenement() );
                        ticket t = new ticket(li.getId_evenement(), getCurrentSession().getId());
                        
//                        ps.supprimerComp(li.getIdCompetition());
                          par.addParticipation(p);
                       tic.addTicket(t);
                       us.DecrementDiamant(getCurrentSession().getId(), li.getId_evenement());
                       
                          //fos.DecrementDiamant(getCurrentSession().getId(), li.getIdCompetition());
////                          TwilioSms twilio = new TwilioSms();
////                    twilio.sendSms("Participation avec succés Mr--"+getCurrentSession().getUsername());
                        new participer(current).show();
                    }
                });

                remove.setText("Participer");
                

                btn.add(remove);
            }
            else if(getCurrentSession().getNb_diamants()<li.getCout())
                    {
                        
                        
                                       Button remove = new Button(FontImage.MATERIAL_ADD_CIRCLE, "Participer");
//            remove.getStyle().setBgTransparency(255);
//            remove.getStyle().setBgColor(0xde4223);

                remove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        Dialog.show("Error", "Vous n'avez pas le nombre exacte du diamands", "ok",null);
                    }
                });

                remove.setText("Participer");
                

                btn.add(remove);
                        
                        
                        
                    }
            
            else if(li.getNbparticipant()==0)
                    {
                        
                        
                                       Button remove = new Button(FontImage.MATERIAL_ADD_CIRCLE, "Participer");
//            remove.getStyle().setBgTransparency(255);
//            remove.getStyle().setBgColor(0xde4223);

                remove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        Dialog.show("Error", "Evenement plein !! ", "ok",null);
                    }
                });

                remove.setText("Participer");
                

                btn.add(remove);
                        
                        
                        
                    }
            else
            {
                Button remove = new Button(FontImage.MATERIAL_REMOVE, "Remove");
//            remove.getStyle().setBgTransparency(255);
//            remove.getStyle().setBgColor(0xde4223);

                remove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        participation p=new participation(getCurrentSession().getId(),li.getId_evenement() );
                        ticket t = new ticket(li.getId_evenement(), getCurrentSession().getId());
                        
//                        ps.supprimerComp(li.getIdCompetition());
                          par.supprimerParticipation(getCurrentSession().getId(), li.getId_evenement());
                          tic.supprimerTicket(getCurrentSession().getId(), li.getId_evenement());
                          
////                          TwilioSms twilio = new TwilioSms();
////                    twilio.sendSms("Participation avec succés Mr--"+getCurrentSession().getUsername());
                        new participer(current).show();
                    }
                });

                remove.setText("Remove");
                

                btn.add(remove);
                btn.add(details);
            }
            
           

//            edit.addActionListener(e -> new modifCompetition(current, com).show());

           



            
            

            c.add(imgV);
            Container det = new Container(BoxLayout.x());

            Container det1 = new Container(BoxLayout.y());

            det1.add(aa);
            det1.add(desc);

            det.add(det1);
            det.add(btn);
            c.add(det);

            add(c);

        }
        installSidemenu(resourceObjectInstance);

      
    }
     @Override
    protected boolean isCurrentCalendar() {
        return true;
    }

}
