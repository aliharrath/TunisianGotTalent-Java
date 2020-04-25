/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.sponsor;


import GOT.entites.sponsor.Pack;
import GOT.utils.MyConnection;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Rawia
 */
public class PackServices {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public PackServices() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public List<Pack> read() {
        List<Pack> listP = new ArrayList<>();
        try {

            String req = "SELECT * FROM pack ORDER BY idsp ASC";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {

                Pack p = new Pack();
                p.setIdad(res.getInt(1));
                p.setIdsp(res.getInt(2));
                p.setPositin(res.getString(3));
                p.setDisplaydate(res.getString(5));
                p.setDiscarddate(res.getString(6));
                p.setPrice(res.getString(7));
                p.setStatus(res.getString(8));

                listP.add(p);

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listP;
    }

    public void pack_add(Pack p) {
        try {

            String req = "INSERT INTO pack VALUES(null, '" + p.getIdsp() + "' , '" + p.getPositin() + "' ,  '" + p.getAd() + "', '"
                    + p.getDisplaydate() + "', '" + p.getDiscarddate() + "', '" + p.getPrice() + "', status= null)";
            System.out.println("    " + req);
            st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void delete(int idad) {
        try {

            String req = "DELETE FROM pack WHERE idad=" + idad;

            st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void updatestatus() {

        String pattern = "yyyy-MM-dd";
        String d1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        try {
            String req1 = "UPDATE pack set status='Historique' where  displaydate < current_date";
            String req2 = "UPDATE pack set status='En display' where   current_date between displaydate and discarddate";
            String req3 = "UPDATE pack set status='Programmé' where  displaydate > current_date";

            st = cnx.createStatement();
            st.executeUpdate(req1);
            st.executeUpdate(req2);
            st.executeUpdate(req3);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public String owner(int idsp) {

        String sp = "null";
        try {
            String req = "SELECT namesp FROM pack p, sponsor s where p.idsp= s.idsp and p.idsp=" + idsp;
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                sp = res.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PackServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sp;

    }

    public int owner_id(String namesp) {

        int idsp = -1;
        try {
            String req = "SELECT idsp FROM sponsor WHERE namesp='" + namesp + "'";
            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                idsp = res.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PackServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idsp;

    }

    public Pack show(int idad) {
        Pack p = new Pack();
        try {

            String req = "SELECT * FROM pack WHERE idad=" + idad;

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {

                p.setIdad(res.getInt(1));
                p.setIdsp(res.getInt(2));
                p.setAd(res.getString(4));
                p.setPositin(res.getString(3));
                p.setDisplaydate(res.getString(5));
                p.setDiscarddate(res.getString(6));
                p.setPrice(res.getString(7));
                p.setStatus(res.getString(8));

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;
    }

    public void edit(String position, String disp, String disc, String price, int idsp, int idad) {
        try {

            String req = "UPDATE pack SET position='" + position + "', price='" + price + "', displaydate='" + disp + "',discarddate='" + disc + "', idsp=" + idsp + " WHERE idad= " + idad;
            System.out.println("    " + req);
            st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

     public boolean checkAddDate(LocalDate d1,LocalDate d2 ) {
       
        Date date1 = Date.from(d1.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(d2.atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (date2.after(date1)) {
            return true;
        } else {
            return false;
        }

    }
    
    public boolean checkDate(LocalDate d) {
        Date today = new Date();
        Date date = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (today.after(date)) {
            return false;
        } else {
            return true;
        }

    }
}
