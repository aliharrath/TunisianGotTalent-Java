/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.test;

import GOT.entites.user.User;
import GOT.services.user.UserService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import GOT.utils.MyConnection;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.mail.MessagingException;


public class MainClass {
    
    public static void main(String[] args) throws NoSuchAlgorithmException, MessagingException 
    { 
       Timestamp lastlogin= new Timestamp(System.currentTimeMillis());

       User u=new User("mama","mama","fathallahmaha29@gmail.com","fathallahmaha29@gmail.com",false,"null","ali",lastlogin,"null",null,"a:0:{}","null","null",0);
   
       UserService us=new UserService(); 
      
      System.out.print(us.checkRole("mama"));
       
}
     
}