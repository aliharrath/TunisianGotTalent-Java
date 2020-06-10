/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.fos_user;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author loume78
 */
public class ServiceUser {
    
    public static ServiceUser instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<fos_user> users;

    public fos_user User = new fos_user();

    public ServiceUser() {
        req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {

        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public fos_user parseUser(String jsonText) {
   
        fos_user UserL = new fos_user();
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            if (UserListJson.get("type").equals("Login succeed")) {

                float id = Float.parseFloat(UserListJson.get("id").toString());
                UserL.setId((int) (id));
                float nbD = Float.parseFloat(UserListJson.get("nbDiamants").toString());
                UserL.setNb_diamants((int) (nbD));
                
                
                UserL.setEmail(UserListJson.get("email").toString());
                UserL.setUsername(UserListJson.get("username").toString());
                
                UserL.setTypec(UserListJson.get("typec").toString());
                
                
                
                

            } else {
                return null;
            }

        } catch (IOException ex) {
                ex.getMessage();
        }

        return UserL;
    }

    public fos_user Login(String username,String password) {
        //String url =Statics.BASE_URL+"/loginMobile/"+username+"/"+password;
        String url =Statics.BASE_URL+"/loginn/"+username+"/"+password;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                User = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return User;
    }
    
    public void DecrementDiamant(int id,int id1) {
        ConnectionRequest con = new ConnectionRequest();
        String url =Statics.BASE_URL+"/mobile/"+id+"/"+id1;
        
        con.setUrl(url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);
            
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
    public boolean addUser(fos_user u){
        String url =Statics.BASE_URL+"/adduser/"+u.getUsername()+"/"+u.getPassword()+"/"+u.getEmail()+"/"+u.getTypec();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK=req.getResponseCode()==200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean Verify(String email) {
        String url =Statics.BASE_URL+"/verify/"+email;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsermail(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        if(users.size()>0){
            this.sendtoken(email);
            return true;
        }else{
            return false;
        }
        
        
    }
    public ArrayList<fos_user> parseUsermail(String jsonText){
        try{
            users=new ArrayList<>();
            JSONParser j=new JSONParser();
            Map<String,Object> userListJson;
            userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list =(List<Map<String,Object>>)userListJson.get("root");
            for(Map<String,Object> obj:list){
                fos_user u=new fos_user();
                u.setEmail(obj.get("email").toString());
                users.add(u);
            }
        }catch(IOException ex){
            
        }
        return users;
    }
    
     public boolean sendtoken(String email){
        String url =Statics.BASE_URL+"/sendtoken/"+email;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK=req.getResponseCode()==200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
    
}
