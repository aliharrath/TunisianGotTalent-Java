/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.test;

import com.pidev.entities.evenement;
import com.pidev.entities.ticket;
import com.pidev.services.ServiceEvenement;
import com.pidev.services.ServiceTicket;
import java.awt.Image;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maysa1998
 */
public class Test {
    public static void main(String[] args) {
        LocalDate dateD = LocalDate.now(); 
        Date DateDebut = Date.valueOf(dateD);
         LocalDate dateF = LocalDate.of(2021, 1, 13);  
         Date DateFin = Date.valueOf(dateF);
         
        
        evenement p =new evenement(79, "mmmmmmm",DateDebut, DateFin,"hello" , "dance", 200, "path/to/your/image.jpg", 15);
        ServiceEvenement ser = new ServiceEvenement();
        
      
                 ticket t =new ticket(10, p);
                ServiceTicket ser1 = new ServiceTicket();
     
                
                 List<ticket> list = null;
        try {
            list = ser1.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
 System.out.println(list);
   

        
        
        
    }
    
}
