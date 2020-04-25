/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.donation;


import GOT.Interface.donation.Ifacture;
import GOT.entites.donation.donation;
import GOT.entites.donation.facture;
import GOT.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ali
 */
public class factureService implements Ifacture<facture>{
         private Connection con;
    private PreparedStatement ps;
    private Statement ste;
    private ResultSet res;
    private int exc;

    public factureService() {
        con = MyConnection.getInstance().getCnx();

    }

    @Override
    public void ajouter(facture f) throws SQLException {
       ste = con.createStatement();
        String requeteInsert = "INSERT INTO `esprit`.`facture` (`id` ,`user_id`, `donation_id` , `date_cr` ) VALUES (NULL, '" + f.getUser_id()+ "' , '" + f.getDonation().getId()+ "', NOW() );";
        ste.executeUpdate(requeteInsert); 
    }

    @Override
    public List<facture> affciher() throws SQLException {
       List<facture> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from facture");
        while (rs.next()) {
            int id = rs.getInt(1);
            int donation_id = rs.getInt(2);//3
            int user_id = rs.getInt(4);
          donationService ser2= new donationService();
            donation don =ser2.findById(donation_id);
  
            Timestamp date_cr = rs.getTimestamp("date_cr");
            facture f = new facture(id,don,date_cr, user_id );
            arr.add(f);
        }
        return arr;
    }

    @Override
    public void delete(int id) throws SQLException {
         ps = con.prepareStatement("DELETE FROM `esprit`.`facture` where id =?");
       
        ps.executeUpdate();
        int rowsDeleted = ps.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("la facture a etait supprimer avec succée!");
        }
    }
    @Override
    public void update(facture t, int id) throws SQLException {
   
    }
}
