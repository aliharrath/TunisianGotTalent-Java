/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.sponsor;

import GOT.entites.sponsor.Pack;
import GOT.entites.sponsor.Sponsor;
import GOT.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Rawia
 */
public class SponsorServices {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public SponsorServices() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public List<Sponsor> read() {

        List<Sponsor> listSP = new ArrayList<>();
        try {

            String req = "SELECT * FROM sponsor";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            /*
            while (res.next()) {

                System.out.println(res.getString(2));
                System.out.println(res.getString(3));
                System.out.println(res.getString(4));
                System.out.println(res.getString(5));
                System.out.println(res.getString(6));
                System.out.println(res.getString(7));

            }*/

            while (res.next()) {

                Sponsor s = new Sponsor();
                s.setIdsp(res.getInt(1));
                s.setNamesp(new SimpleStringProperty(res.getString(2)));
                s.setAddress(new SimpleStringProperty(res.getString(3)));
                s.setEmail(res.getString(4));
                s.setTel(res.getString(5));
                s.setDescription(res.getString(6));
                s.setLogo(res.getString(7));

                listSP.add(s);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listSP;
    }

    public void delete(int idsp) {
        try {

            String req = "DELETE FROM sponsor WHERE idsp=" + idsp;

            st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Sponsor show(int idsp) {
        Sponsor s = new Sponsor();
        try {

            String req = "SELECT * FROM sponsor WHERE idsp=" + idsp;

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                s.setIdsp(res.getInt(1));
                s.setNamesp(new SimpleStringProperty(res.getString(2)));
                s.setAddress(new SimpleStringProperty(res.getString(3)));
                s.setEmail(res.getString(4));
                s.setTel(res.getString(5));
                s.setDescription(res.getString(6));
                s.setLogo(res.getString(7));

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return s;
    }

    public boolean edit(String address, String email, String desc, String tel, int idsp) {
        boolean verif = false;

        try {

            String req = "UPDATE sponsor SET  address='" + address + "', email='" + email + "',tel='" + tel + "', description='" + desc + "' WHERE idsp= " + idsp;

            st = cnx.createStatement();
            st.executeUpdate(req);
            verif = true;

        } catch (SQLException ex) {
            System.out.println(ex);
            verif =false;
        }
        return verif;
    }

    public List<Pack> sponsor_packs(int idsp) {
        List listP = new ArrayList<Pack>();
        try {

            String req = "SELECT * FROM pack WHERE idsp=" + idsp;

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {

                Pack p = new Pack();

                p.setIdad(res.getInt(1));
                p.setIdsp(res.getInt(2));
                p.setPositin(res.getString(3));
                p.setAd(res.getString(4));
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

    public int sponsor_invest(int idsp) {
        int inv = 0;
        try {

            String req = "SELECT SUM(price) FROM sponsor s, pack p WHERE s.idsp=p.idsp AND s.idsp=" + idsp;

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                inv = res.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return inv;
    }

    public boolean sponsor_add(Sponsor s) {
        boolean verif = false;
        int existant = -1;
        try {

            String req0 = "SELECT count(*) FROM sponsor WHERE namesp='" + s.getNamesp() + "'";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req0);

            while (res.next()) {
                existant = res.getInt(1);
            }
            System.out.println("" + existant);
            if (existant == 0) {
                String req = "INSERT INTO sponsor VALUES(null, '" + s.getNamesp() + "' , '" + s.getAddress() + "' ,  '" + s.getEmail() + "', '"
                        + s.getTel() + "', '" + s.getDescription() + "', '" + s.getLogo() + "')";
                System.out.println("    " + req);
                st = cnx.createStatement();
                st.executeUpdate(req);
                verif = true;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return verif;
    }

    public boolean checkMail(String a) {

        Boolean valide = false;
        int i, j, k;
        for (j = 1; j < a.length(); j++) {
            if (a.charAt(j) == '@') {
                if (j < a.length() - 4) {
                    for (k = j; k < a.length() - 2; k++) {
                        if (a.charAt(k) == '.') {
                            valide = true;
                        }
                    }
                }
            }
        }

        return valide;
    }

    public static boolean checkTel(String tel) {
        boolean verif = false;
        if (tel.length() == 8) {

            char c;
            for (int i = 0; i < tel.length(); i++) {
                c = tel.charAt(i);
                if (Character.isDigit(c)) {

                    verif = true;

                } else {
                    return false;
                }
            }

        }

        return verif;
    }
}
/*
    
 */
