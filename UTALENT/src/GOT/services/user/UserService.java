/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.user;

import GOT.entites.user.User;
import GOT.utils.BCrypt;
import GOT.utils.EmailAttachmentSender;
import GOT.utils.MyConnection;
import GOT.utils.SessionUser;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 *
 * @author Freddy
 */
public class UserService {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet res;
    private int exc;

    public UserService() {
        con = MyConnection.getInstance().getCnx();

    }
    
    //Cryptage du Mot de passe
    public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
               
      
    }
    
    public User existUser(String username) {
        User ut =null;
        try {
            String sql = "SELECT * FROM fos_user WHERE username = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            res = ps.executeQuery();
            while (res.next()) {
                ut = new User();
                ut.setId(res.getInt(1));
                ut.setUsername(res.getString(2));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ut;
    }
  
    
     public void Registration(User u) throws MessagingException {
     try {
         {
            Timestamp lastlogin= new Timestamp(System.currentTimeMillis());
            String req = "INSERT INTO fos_user (username,username_canonical,email,email_canonical,enabled,salt,password,last_login,confirmation_token,password_requested_at,roles,facebook_id,typec,nb_diamants) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(req);
            String token = UUID.randomUUID().toString();
            String Role = u.getRoles();
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getUsernameCanonical());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getEmailCanonical());
            ps.setBoolean(5, false);
            ps.setString(6, "null");
            ps.setString(7, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
            ps.setObject(8, lastlogin);
            ps.setString(9, token);
            ps.setObject(10, null);
            ps.setString(11, "a:0:{}");
            ps.setString(12,"null" );
            ps.setString(13, u.getTypec());
            ps.setInt(14, 0);
            EmailAttachmentSender.sendEmailWithAttachments(u.getEmail(), "Confirmation du Compte UTalent", "<h1> Cher utilisateur,</h1></br> <p>Nous avons l'honneur de vous accueiller parmi notre famille. </p>");
            ps.executeUpdate();
                System.out.println("Insertion  Reussie!");
         }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
  

    public User displayUser(int idUser) {
        User u = null;
        String req = "select * from users where idUser=?";

        try {
            ps = con.prepareStatement(req);
            ps.setInt(1, idUser);
            res = ps.executeQuery();
            while (res.next()) {
                int id = res.getInt("idUser");

            }
        } catch (SQLException ex) {
                System.out.print(ex);
        }
        return u;
    }

    public boolean Authentification(User u) {
        boolean status = false;
        try {
            String req = "select * from fos_user where username=? ";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, u.getUsername());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (BCrypt.checkpw(u.getPassword(), rs.getString("password")) == true) {
                                 
                    status = true;
                    u = this.findById(rs.getInt("id"));
                    SessionUser.setUser(u);
                    System.out.println(u.getId());

                } else {
                    status = false;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    } 
    
    public boolean checkRole(String username) {
        boolean exist = false;
        try {
            String req = "select roles from fos_user where username=? ";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                if (rs.getString(1).equals("a:0:{}")) {
                    exist = true;
                } else {
                    exist = false;
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exist;
    }
    
    public User findById(Integer id) {
        User u = null;
        try {
            String req = "select * from fos_user where id=? ";
            PreparedStatement st = con.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                        rs.getBoolean(6),
                        rs.getString(7),
                        rs.getString(8),
                        (Timestamp) rs.getObject(9),
                        rs.getString(10),
                        (Timestamp) rs.getObject(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getInt(15));
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return u;
    }
     public Boolean CheckIfUserExist(String email) {
        boolean check = false;
        try {
            String req = "select * from fos_user where email=? ";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;
    }

     public Boolean CheckIfUsernameExist(String username) {
        boolean check = false;
        try {
            String req = "select * from fos_user where username=? ";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;
    }
   public boolean update( int nb_diamants, int idUser) throws SQLException {
        String sql = "UPDATE fos_user SET nb_diamants=?  WHERE id='" + idUser + "'";
        
        PreparedStatement statement = con.prepareStatement(sql);
      
        statement.setInt(1, nb_diamants);
        

             

     
     
        
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            
            
            System.out.println("An existing user was updated successfully!");
        }
        return true;
    }
  

}
