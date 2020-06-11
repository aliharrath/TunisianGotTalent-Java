/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Donation;
import Entities.evenement;
import GUI.AfficherDonation;
import GUI.afficherevent;
import Utils.Statics;
import com.codename1.io.CharArrayReader;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Ali
 */
public class ServiceDonation {
      public ArrayList<Donation> donations;
    Form current;

    public static ServiceDonation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceDonation() {
        req = new ConnectionRequest();
    }

     public static ServiceDonation getInstance() {
        if (instance == null) {
            instance = new ServiceDonation();
        }
        return instance;
    }
     public ArrayList<Donation> parseTasks(String jsonText) {
        try {
            donations = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Donation d = new Donation();
                float id = Float.parseFloat(obj.get("iddon").toString());
                d.setId((int) id);
                int valeur_d = (int) Float.parseFloat(obj.get("valeurD").toString());
                d.setValeur_d((int) valeur_d);
                
          //      float hidden = Float.parseFloat(obj.get("hidden").toString());
                //d.setHidden((int) hidden);
                
              // Map<String, Object> content = (Map<String, Object>) obj.get("userid");

               
               
             // ArrayList myList = new ArrayList(content.values());
                // myList.get(0);
                
                float user =  Float.parseFloat(obj.get("id").toString());
                System.out.println(user);
                 d.setUser_id((int) user);
                
             Map<String, Object> time = (Map<String, Object>)obj.get("dateCr");
              Date datecr = new Date((long)Double.parseDouble(time.get("timestamp").toString())*1000L);
                
            d.setDate_cr(datecr);
                
               d.setCategorie(obj.get("categorie").toString());
                d.setLib_donation(obj.get("libDonation").toString());
                d.setDescription(obj.get("description").toString());
               d.setPhoto(obj.get("photo").toString());
               
              

                donations.add(d);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return donations;
    }
       public boolean addDonation(Donation d) {
       // String d = new SimpleDateFormat("yyyy-MM-dd").format(d.getDatedebut());
       
        

        String url = Statics.BASE_URL + "/donation/ajoutmobile?categorie=" + d.getCategorie()+ "&description=" + d.getDescription()+"&libDonation="+d.getLib_donation()+ "&photo=" + d.getPhoto()+ "&valeurD=" + d.getValeur_d();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                 System.out.println("azeeeeeeeeeeeeeeeee");
              //new afficherevent(current).show();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         public ArrayList<Donation> getalldonations() {
        String url = Statics.BASE_URL + "/donation/afficherMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("afffichhhhhhhhage");
                donations = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return donations;
    }
         public void supprimerdon(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url =Statics.BASE_URL +"/donation/supprimerMobile/"+id;
        
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);
            Dialog.show("Succés", "Donation supprimée", "ok", null);
            new AfficherDonation(current).show();
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
          public boolean modifierdon(Donation d,int id) {
        ConnectionRequest con = new ConnectionRequest();
        
        
        
        String Url = Statics.BASE_URL +"/donation/modifierMobile/?categorie=" + d.getCategorie()
                + "&description=" + d.getDescription()
                + "&libDonation=" + d.getLib_donation()
                + "&photo=" + d.getPhoto()
                + "&valeurD=" + d.getValeur_d();
               
        con.setUrl(Url);
        

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            
            Dialog.show("Succés", "Donation modifié", "ok", null);
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            new AfficherDonation(current).show();
          

            

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
    }


   
    
}
