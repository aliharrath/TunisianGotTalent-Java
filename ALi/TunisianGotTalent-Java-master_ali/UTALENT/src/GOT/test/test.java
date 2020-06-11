/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.test;

import GOT.entities.donation.donation;
import GOT.service.donation.donationService;
import java.sql.SQLException;

/**
 *
 * @author Ali
 */
public class test {
    public static void main(String[] args)throws SQLException {
    donation d = new donation();
    donation d1 = new donation("plz",5,"WIN_20190616_12_30_42_Pro.jpg","azrrrrrrrrrzaeazeaeza","Musique",5);
       donationService ds =new donationService();
      // ds.ajouter(d);
          //System.out.println(ds.affciher());
          d=ds.findById(29);
          
          ds.update(d1, 29);
         // ds.delete(12);
       
}
}
