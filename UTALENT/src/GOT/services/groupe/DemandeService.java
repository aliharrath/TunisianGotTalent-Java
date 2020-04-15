/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.groupe;

import GOT.entites.demande.Demande;
import GOT.entites.groupe.Groupe;
import GOT.entites.user.User;
import GOT.utils.MyConnection;
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

/**
 *
 * @author HP
 */
public class DemandeService {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet res;
    private int exc;

    public DemandeService() {
        con = MyConnection.getInstance().getCnx();

    }
    
    
    public void create(Demande d) {
     try {
            String req = "INSERT INTO demande (user_id,groupe_id) VALUES (?,?)";
           
            PreparedStatement st = con.prepareStatement(req);
            st.setInt(1, d.getUser());
            st.setInt(2, d.getGroupe());
           
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
   
    
    public List<Demande> afficherdm(int id) {
      List<Demande> ld = new ArrayList<>();
        try {
        String select = "SELECT d.id,d.user_id,g.nom FROM demande d join groupe g on d.groupe_id=g.id WHERE g.user_id = '" + id + "' ";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(select);

            while (result.next()) {
                Demande d = new Demande(result.getInt("d.id"));
                d.setUser( result.getInt("d.user_id"));
                d.setNom(result.getString("g.nom"));
                ld.add(d);
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return ld;

    }

    public void Accepter(int id) {
      try {
            String req = "DELETE FROM `demande` WHERE `demande`.`id` = ? ";
            PreparedStatement st = con.prepareStatement(req);
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException ex) {

            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
/*    public Groupe FindGroupe(int id) {
        Groupe grp = null;

        try {
            String select = "SELECT * FROM groupe WHERE  id = '" + id + "' ";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(select);

            if (result.next()) {
                grp = new Groupe();
                grp.setId(id);
                grp.setNom(result.getString("nom"));
            
      
                grp.setPhoto(result.getNString("photo"));

                GroupeService gs = new GroupeService();

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return grp;
    }
*/
}
