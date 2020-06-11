/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Donation;
import Entities.Facture;
import Entities.ticket;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Ali
 */
public class ServiceFacture {
    public ArrayList<Facture> Factures;
    
    public static ServiceFacture instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceFacture() {
        req = new ConnectionRequest();
    }

    public static ServiceFacture getInstance() {
        if (instance == null) {
            instance = new ServiceFacture();
        }
        
    return instance;
}
     public ArrayList<Facture> parseTasks(String jsonText) {
        try {
            Factures = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Facture f = new Facture();
                
                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int) id);
                
                
               
                
                Map<String, Object> time = (Map<String, Object>)obj.get("dateCr");
                Date datecr = new Date((long)Double.parseDouble(time.get("timestamp").toString())*1000L);
                f.setDate_cr(datecr);
                
                
                Map<String, Object> useridM = (Map<String, Object>) obj.get("userid");

                ArrayList myList1 = new ArrayList(useridM.values());
                myList1.get(0);
                System.out.println(myList1.get(4).toString());
                float iduser = Float.parseFloat(myList1.get(4).toString());
                f.setUser_id((int) iduser);
                
                Map<String, Object> content = (Map<String, Object>) obj.get("donationid");

                ArrayList myList = new ArrayList(content.values());
                myList.get(6);

                float donationid = Float.parseFloat(myList.get(5).toString());
                f.setDonation_id((int) donationid);

                Factures.add(f);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Factures;
    }
     
      public boolean addFacture(Donation d) {
       
        String url = Statics.BASE_URL + "/facture/"+ d.getId() + "/ajoutFM";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
//                new afficherComp(current).show();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
