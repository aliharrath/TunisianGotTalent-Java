/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.donation;

import GOT.Interface.donation.Idonation;
import GOT.entites.donation.donation;
import GOT.utils.MyConnection;
import GOT.utils.SessionUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ali
 */
public class donationService implements Idonation<donation>{

     private Connection con;
    private PreparedStatement ps;
    private Statement ste;
    private ResultSet res;
    private int exc;

    public donationService() {
        con = MyConnection.getInstance().getCnx();

    }
    @Override
    public void ajouter(donation d) throws SQLException {
        String req = "INSERT INTO donation (lib_donation,date_cr,valeur_d,photo,description,categorie,user_id,hidden) values(?,?,?,?,?,?,?,?)";
        ps = con.prepareStatement(req);
        ps.setString(1, d.getLib_donation());
        Timestamp date_cr= new Timestamp(System.currentTimeMillis());
        ps.setTimestamp(2, date_cr);
        ps.setInt(3, d.getValeur_d());
        ps.setString(4, d.getPhoto());
        ps.setString(5, d.getDescription());
        ps.setString(6, d.getCategorie());
        ps.setInt(7, SessionUser.getUser().getId());
       
        ps.setInt(8, 1);
        ps.execute();
    }

    @Override
    public ObservableList<donation> affciher() throws SQLException {
 ObservableList <donation> Dn = FXCollections.observableArrayList();
 
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from donation");
        while (rs.next()) {
            int id = rs.getInt("id");
            String lib = rs.getString("lib_donation");
             int valeur_d= rs.getInt("valeur_d");
            String photo = rs.getString("photo");
            
            String description = rs.getString("description");
        //    String categorie = rs.getString("categorie");
               int user_id1= rs.getInt("user_id");
           
           //int hidden= rs.getInt("hidden");
            
           
            donation d = new donation(id,lib,valeur_d,photo,description,user_id1);
            Dn.add(d);
                           d.setId(id);

        }
           
 
 return Dn; 
    }

    @Override
    public void delete(int id) throws SQLException {
        try {

            String req = "DELETE FROM `donation` WHERE `donation`.`id` = " + id + "";
            ps = con.prepareStatement(req);

            ps.execute();
        }
        catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void update(donation d, int id) throws SQLException {
        try {
         String req = "UPDATE donation SET `lib_donation`=?,`date_cr`=? ,`valeur_d`=?,`photo`=?,`description`=?,`categorie`=? WHERE `id`=?";
            ps = con.prepareStatement(req);
        ps.setString(1, d.getLib_donation());
        Timestamp date_cr= new Timestamp(System.currentTimeMillis());
        ps.setTimestamp(2, date_cr);
        ps.setInt(3, d.getValeur_d());
        ps.setString(4, d.getPhoto());
        ps.setString(5, d.getDescription());
        ps.setString(6, d.getCategorie());
         ps.setInt(7, id);
        
      
        ps.executeUpdate();
           } catch (Exception e) {
                Logger.getLogger(donationService.class.getName()).log(Level.SEVERE,null,e);
            }
    }

    @Override
    public donation findById(int id) {
          donation don = null;

        try {
            String select = "SELECT * FROM donation WHERE  id = '" + id + "' ";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(select);

            if (result.next()) {
                don = new donation();
                don.setId(id);
                don.setLib_donation(result.getString("lib_donation"));
                  
      
                don.setPhoto(result.getNString("photo"));

                donationService ds = new donationService();

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return don;
    }

    @Override
    public List<donation> afficherdon() throws SQLException {
        List<donation> ld = new ArrayList<>();
        try {
            String select = "SELECT * from donation ";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(select);

            while (result.next()) {
                //donation d = new donation(result.getInt("id"));
               // d.setLib_donation(result.getString("lib_donation"));
               /* g.setUser_id((User)result.getObject("user_id"));
                g.setDate_creation(result.getTimestamp("date_creation"));
                g.setDescription(result.getString("description"));
                g.setCategorie(result.getString("categorie"));*/
           //    d.setPhoto(result.getString("photo"));
                //ld.add(d);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return ld;

    }

    @Override
    public ArrayList<donation> getdonationbyCat(String dbc) {
        ArrayList<donation> lsttype = new ArrayList<>();

        try {
             
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("Select * from donation where categorie LIKE '" + dbc + "' ;");

            while (rs.next()) {
                donation don = new donation();
                 int id = rs.getInt("id");
            String lib = rs.getString("lib_donation");
             int valeur_d= rs.getInt("valeur_d");
            String photo = rs.getString("photo");
            
            String description = rs.getString("description");
        

                 donation d = new donation(id,lib,valeur_d,photo,description);
            lsttype.add(d);
            }
            ste.close();
        } catch (SQLException ex) {
            Logger.getLogger(donationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsttype; 
    }
    
}
