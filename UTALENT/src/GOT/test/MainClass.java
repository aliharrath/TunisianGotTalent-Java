/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.test;

import GOT.entites.demande.Demande;
import GOT.entites.groupe.Groupe;
import GOT.entites.user.User;
import GOT.services.groupe.DemandeService;
import GOT.services.groupe.GroupeService;
import GOT.services.user.UserService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import GOT.utils.MyConnection;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.mail.MessagingException;


public class MainClass {
    private static Connection con;
          static UserService us=new UserService();

    public static void main(String[] args) throws NoSuchAlgorithmException, MessagingException 
    { 
       Timestamp lastlogin= new Timestamp(System.currentTimeMillis());

       /*User u=new User("mama","mama","fathallahmaha29@gmail.com","fathallahmaha29@gmail.com",false,"null","ali",lastlogin,"null",null,"a:0:{}","null","null",0);
   
       UserService us=new UserService(); 
      
      System.out.print(us.checkRole("mama"));*/
     /* GroupeService gs=new GroupeService();
      List<Groupe> lg = new ArrayList<Groupe>();
      lg = gs.affichergpIN(21);
    for (Groupe d: lg) { 
      System.out.println(d.toString());}
    System.out.print(lastlogin);
    */
      File file = new File("C:\\Users\\HP\\Downloads\\UTALENT\\src\\resources\\img\\MgRLnC.jpg");
      System.out.print(file.toURI().toString());
     //       Image image = new Image(file.toURI().toString());
       
}
      
    
     
}