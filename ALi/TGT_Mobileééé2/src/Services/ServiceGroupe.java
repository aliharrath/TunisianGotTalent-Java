/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Groupe;
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
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author bhk
 */
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
                           
        
        Container container1All = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<Groupe> listTasks = new ArrayList<>();
        String url = Statics.BASE_URL+"/api/groupe/all";
        req.setUrl(url);
        req.setPost(false);
      
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                ArrayList<Groupe> listTasks = new ArrayList<>();
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                   // System.out.println("roooooot:" +tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                         Groupe task = new Groupe();
                         task.setUser(obj.get("user").toString());            
                         System.out.print(task.getUser());
                   //    Object o= obj.get("user");
                     
                     
                       if(task.getUser().equals("1.0")){
                         System.out.print(task.getUser());
                         task.setNom(obj.get("nom").toString());
                         task.setPhoto(obj.get("photo").toString());
                         Label nom=new Label("Nom :"+task.getNom());
                         EncodedImage  encImg=EncodedImage.create("/load.gif");
                         URLImage imgUrl = URLImage.createToStorage(encImg,task.getPhoto(),"http://localhost//testing_fos_ali/web/images/"+task.getPhoto()
                          ,URLImage.RESIZE_SCALE);
   
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
                            Container container2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                              
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
                         EncodedImage  encImg=EncodedImage.create("/load.gif");
                         URLImage imgUrl = URLImage.createToStorage(encImg,task.getPhoto(),"http://localhost//testing_fos_ali/web/images/"+task.getPhoto()
                          ,URLImage.RESIZE_SCALE);
   
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
                            Container container2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                              
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
}
            
