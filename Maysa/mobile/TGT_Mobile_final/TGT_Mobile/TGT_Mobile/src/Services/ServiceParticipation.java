/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.evenement;
import Entities.participation;
import GUI.afficherevent;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author loume78
 */
public class ServiceParticipation {
    
    public ArrayList<participation> participations;
    boolean find=false;
    
    public static ServiceParticipation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceParticipation() {
        req = new ConnectionRequest();
    }

    public static ServiceParticipation getInstance() {
        if (instance == null) {
            instance = new ServiceParticipation();
        }
        return instance;
    }
    
    
    
   
    
    
    public ArrayList<participation> parseTasks(String jsonText) {
        try {
            participations = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                participation t = new participation();
                
                float id = Float.parseFloat(obj.get("idparticipation").toString());
                t.setIdparticipation((int) id);
                
                
                Map<String, Object> contentComp = (Map<String, Object>) obj.get("idevenement");

                ArrayList myList1 = new ArrayList(contentComp.values());
                myList1.get(0);
                
                float idComp = Float.parseFloat(myList1.get(4).toString());
                t.setEvenement((int) idComp);
                
                Map<String, Object> content = (Map<String, Object>) obj.get("iduser");

                ArrayList myList = new ArrayList(content.values());
                myList.get(0);

                float user = Float.parseFloat(myList.get(4).toString());
                t.setIduser((int) user);
                
                
                
               

                participations.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return participations;
    }
    
     public ArrayList<participation> getAllParticipations() {
        String url = Statics.BASE_URL + "/affPmobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                participations = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return participations;
    }
     
     public boolean FindParticipation(int id,int id1)
     {
         
         ArrayList<participation> myPar = getAllParticipations();
         
         for (participation li : myPar) {
             if(li.getIduser()==id && li.getEvenement()==id1)
                 return true;
         }
         return false;
         
     }
     
     
     
     public boolean addParticipation(participation p) {
       

        String url = Statics.BASE_URL + "/ajoutParticipation?user=" + p.getIduser() + "&competition=" + p.getEvenement();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
//                new afficherComp(current).show();
            Dialog.show("Succés", "Participation avec succès", "ok", null);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     public void supprimerParticipation(int id,int id1) {
        ConnectionRequest con = new ConnectionRequest();
        String Url =Statics.BASE_URL +"/suppPmobile/"+id+"/"+id1;
        
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);
            Dialog.show("Succés", "Participation supprimée", "ok", null);
//            new afficherComp(current).show();
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
}
