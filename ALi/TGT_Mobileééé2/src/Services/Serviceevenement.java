/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.evenement;
import Entities.fos_user;
import GUI.afficherevent;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


import java.util.Map;


/**
 *
 * @author loume78
 */
public class Serviceevenement {

    public ArrayList<evenement> competitions;
    Form current;

    public static Serviceevenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public Serviceevenement() {
        req = new ConnectionRequest();
    }

    public static Serviceevenement getInstance() {
        if (instance == null) {
            instance = new Serviceevenement();
        }
        return instance;
    }

    public ArrayList<evenement> parseTasks(String jsonText) {
        try {
            competitions = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                evenement t = new evenement();
                float id = Float.parseFloat(obj.get("idEvenement").toString());
                t.setId_evenement((int) id);
                float cout = Float.parseFloat(obj.get("cout").toString());
                t.setCout((int) cout);
                
                float nbp = Float.parseFloat(obj.get("nbparticipant").toString());
                t.setNbparticipant((int) nbp);
                
                Map<String, Object> content = (Map<String, Object>) obj.get("iduser");

                ArrayList myList = new ArrayList(content.values());
                myList.get(0);
                System.out.println(myList.get(4).toString());
                float user = Float.parseFloat(myList.get(4).toString());
                t.setIduser((int) user);
                
                Map<String, Object> time = (Map<String, Object>)obj.get("datedebut");
                Date dateD = new Date((long)Double.parseDouble(time.get("timestamp").toString())*1000L);
                
                Map<String, Object> time1 = (Map<String, Object>)obj.get("datefin");
                Date dateF = new Date((long)Double.parseDouble(time1.get("timestamp").toString())*1000L);
                
                
                t.setDatedebut(dateD);
                t.setDatefin(dateF);
                t.setTitre(obj.get("titre").toString());
                t.setImage(obj.get("image").toString());
                t.setDescription(obj.get("description").toString());
                t.setTypedetalent(obj.get("typedetalent").toString());

                competitions.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return competitions;
    }

    public ArrayList<evenement> getAllCompetitions() {
        String url = Statics.BASE_URL + "/afficherMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(competitions);
                competitions = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return competitions;
    }

    public boolean addCompetition(evenement c) {
        String dd = new SimpleDateFormat("yyyy-MM-dd").format(c.getDatedebut());
        String df = new SimpleDateFormat("yyyy-MM-dd").format(c.getDatefin());
        

        String url = Statics.BASE_URL + "/AjoutMobile?titre=" + c.getTitre() + "&description=" + c.getDescription()+"&user="+c.getIduser()+ "&cout=" + c.getCout()+ "&nbparticipant=" + c.getNbparticipant()+ "&datedebut=" + dd + "&datefin=" + df + "&typedetalent=" + c.getTypedetalent()+ "&image=" + c.getImage();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                new afficherevent(current).show();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public void supprimerComp(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url =Statics.BASE_URL +"/supprimerMobile/"+id;
        
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);
            Dialog.show("Succés", "Competition supprimée", "ok", null);
            new afficherevent(current).show();
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
    public boolean modifierEvent(evenement c,int id) {
        ConnectionRequest con = new ConnectionRequest();
        String dd = new SimpleDateFormat("yyyy-MM-dd").format(c.getDatedebut());
        String df = new SimpleDateFormat("yyyy-MM-dd").format(c.getDatefin());
        
        String Url = Statics.BASE_URL +"/modifierMobile/"+id + "?titre=" + c.getTitre()
                + "&description=" + c.getDescription()
                + "&cout=" + c.getCout()
                + "&nbparticipant=" + c.getNbparticipant()
                + "&datedebut=" + dd
                + "&datefin=" + df
                 + "&user=" + c.getIduser()
                + "&typedetalent=" + c.getTypedetalent()
                + "&image=" + c.getImage();
        con.setUrl(Url);
        

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            
            Dialog.show("Succés", "Event modifié", "ok", null);
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            new afficherevent(current).show();
          

            

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
    }

}
