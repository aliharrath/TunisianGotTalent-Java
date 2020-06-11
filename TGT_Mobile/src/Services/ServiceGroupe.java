/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Groupe;
import Entities.fos_user;
import GUI.DetailGroupeForm;
import Utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class ServiceGroupe {

   
    public static ServiceGroupe instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceGroupe() {
         req = new ConnectionRequest();
    }

    public static ServiceGroupe getInstance() {
        if (instance == null) {
            instance = new ServiceGroupe();
        }
        return instance;
    }

   
    
       public Container getList2(Resources theme,String id) {
                           
           Container c = new Container(BoxLayout.y());
            c.getStyle().setBorder(Border.createLineBorder(2));
            c.getStyle().setMargin(1, 1, 1, 1);
            c.getStyle().setPadding(0, 0, 0, 0);
            c.getStyle().setBgTransparency(255);
            c.getStyle().setBgColor(0xFDF2E9);

        Container container1All = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        String url = Statics.BASE_URL+"/api/groupe/allGU";
        req.setUrl(url);
        req.setPost(false);
      
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                   // System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                         Groupe task = new Groupe();
                           String    recup = obj.get("user").toString().substring(0, obj.get("user").toString().length()-2);
                         System.out.println(recup);
                         task.setUser(recup);       
                         System.out.println(recup);
                         System.out.print(id);
                   //    Object o= obj.get("user");
             
            
                       if(recup.equals(id)){
                         System.out.print(task.getUser());
                         task.setNom(obj.get("nom").toString());
                         task.setPhoto(obj.get("photo").toString());
                         Label nom=new Label("Nom :"+task.getNom());
                         EncodedImage  encImg=EncodedImage.create("/load.gif");
                         URLImage imgUrl = URLImage.createToStorage(encImg,task.getPhoto(),"http://localhost//testing_fos_ali/web/images/"+task.getPhoto()
                          ,URLImage.RESIZE_FAIL);
   
                      ImageViewer image = new ImageViewer(imgUrl);
                      System.out.println(task.getPhoto());
                        
       
                      Form current = null;

                        Button detailbtn = new Button("Detail");
                        detailbtn.getUnselectedStyle().setFgColor(5542241);
                        detailbtn.addActionListener((e) -> {
                           new DetailGroupeForm(current,theme,task).show();
                            

        });
                        
                        
    
                            Container container = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                            Container container1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                            Container container2 = new Container(BoxLayout.x());

                              
container1.add(nom);
//container1.add(adresse);
container1.add(image);


container2.add(detailbtn);



     container.add(BorderLayout.CENTER, container1);

    container.add(BorderLayout.SOUTH, container2);
    
                  container1All.add(container);
                        
                        
                          }
                       else if(!task.getUser().equals("1.0")){
                         System.out.print(task.getUser());
                         task.setNom(obj.get("nom").toString());
                         task.setPhoto(obj.get("photo").toString());
                         Label nom=new Label("Nom :"+task.getNom());
                          //Image placeholder = Image.createImage(1150, 420);
                          //EncodedImage encImg = EncodedImage.createFromImage(placeholder, false);
                          // EncodedImage  encImg=EncodedImage.create("/load.gif");
                          Image placeholder = Image.createImage(1150, 420);
                        EncodedImage encImg = EncodedImage.createFromImage(placeholder, false);
           
                        URLImage imgUrl = URLImage.createToStorage(encImg,task.getPhoto(),"http://localhost//testing_fos_ali/web/images/"+task.getPhoto()
                        );
   
                      ImageViewer image = new ImageViewer(imgUrl);
                      System.out.println(task.getPhoto());
                        
       
                      Form current = null;

                        Button detailbtn = new Button("Detail");
                        detailbtn.getUnselectedStyle().setFgColor(5542241);
                        detailbtn.addActionListener((e) -> {
                           new DetailGroupeForm(current,theme,task).show();
                      //   System.out.print(ServiceGroupe.getInstance().getAllNameUser()); 

        });
                        
                        
    
                            Container container = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                            Container container1 = new Container(BoxLayout.y());
                            Container container2 = new Container(BoxLayout.x());

                              
container1.add(nom);
//container1.add(adresse);
container1.add(image);


container2.add(detailbtn);



        container.add(BorderLayout.CENTER, container1);

    container.add(BorderLayout.SOUTH, container2);
    
                  container1All.add(container);
                        
                        
                          }

                    }
                        
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return container1All;
    }
           public ArrayList<String> ids;

       public  ArrayList<String> getAllNameUser(/*Groupe g*/) {
          /*ArrayList<String> ids=new ArrayList<>();
          ArrayList<String> username=new ArrayList<>();
          String url = Statics.BASE_URL+"/api/groupe/find/3"/*+g.getId();
          req.setUrl(url);
          req.setPost(false);
          req.addResponseListener(new ActionListener<NetworkEvent>() {
        String mot,recup;
        
              @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> group_user = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) group_user.get("root");

                    for (Map<String, Object> obj : list) {
                  //      mot=obj.get("idUser").toString();
                    //    recup = mot.substring(0, mot.length()-2);
                         //ids.add(obj.get("idUser").toString()); 
                    }
                } catch (IOException ex) {
                }

            }
        });
          NetworkManager.getInstance().addToQueueAndWait(req);*/
         return ids;
       }
}
            
