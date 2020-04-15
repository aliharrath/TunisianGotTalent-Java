/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.groupe;

import GOT.Interface.groupe.IGroupeServices;
import GOT.entites.groupe.Groupe;
import GOT.entites.user.User;
import GOT.services.user.UserService;
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
public class GroupeService implements IGroupeServices{

    private Connection con;
    private PreparedStatement ps;
    private ResultSet res;
    private int exc;

    public GroupeService() {
        con = MyConnection.getInstance().getCnx();

    }
    
    @Override
    public void create(Groupe g) {
     try {
            String req = "INSERT INTO groupe (user_id,nom,description,photo,date_creation,categorie) VALUES (?,?,?,?,?,?)";
        
            PreparedStatement st = con.prepareStatement(req);
            st.setInt(1,g.getUser_id());
            st.setString(2, g.getNom());
            st.setString(3, g.getDescription());
            st.setString(4, g.getPhoto());         
            st.setObject(5, g.getDate_creation());
            st.setString(6, g.getCategorie());
             
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public void create1(Groupe g) {
     try {
            String req = "INSERT INTO groupe (user_id) VALUES (?)";
            Timestamp date_crea= new Timestamp(System.currentTimeMillis());

            PreparedStatement st = con.prepareStatement(req);
            st.setInt(1,g.getUser_id());
           
             
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    /*

    @Override
    public void create(Groupe g) {
     try {
            String req = "INSERT INTO groupe (photo) VALUES (?)";
          //  Timestamp date_crea= new Timestamp(System.currentTimeMillis());

            PreparedStatement st = con.prepareStatement(req);
           /* st.setObject(1,(User) g.getUser_id());
            st.setString(2, g.getNom());
            st.setString(3, g.getDescription());
            st.setString(1, g.getPhoto());         
           // ps.setObject(5, date_crea);
            //st.setString(6, g.getCategorie());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/    

    @Override
    public List<Groupe> affichergp() {
      List<Groupe> le = new ArrayList<>();
        try {
            String select = "SELECT * from groupe ";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(select);

            while (result.next()) {
                Groupe g = new Groupe(result.getInt("id"));
                g.setNom(result.getString("nom"));
                g.setUser_id(result.getInt("user_id"));
                g.setDate_creation(result.getTimestamp("date_creation"));
                g.setDescription(result.getString("description"));
                g.setCategorie(result.getString("categorie"));
                g.setPhoto(result.getString("photo"));
                le.add(g);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return le;

    }
    
    public List<Groupe> affichergpIN(int id) {
      List<Groupe> le = new ArrayList<>();
        try {
            String select = "SELECT * from groupe g join grp_users grp where grp.id_user  = '" + id + "' ";
            

            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(select);

            while (result.next()) {
                Groupe g = new Groupe(result.getInt("id"));
                g.setNom(result.getString("nom"));
                g.setUser_id(result.getInt("user_id"));
                g.setDate_creation(result.getTimestamp("date_creation"));
                g.setDescription(result.getString("description"));
                g.setCategorie(result.getString("categorie"));
                g.setPhoto(result.getString("photo"));
                le.add(g);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return le;

    }
    public List<Groupe> other(int id) {
      List<Groupe> le = new ArrayList<>();
        try {
          //  String select = "SELECT * from groupe g join grp_users grp where grp.id_user  != '" + id + "' ";
              String select = "select * from groupe where id NOT IN(SELECT id_groupe FROM `grp_users` WHERE id_user=" + id + " ) and user_id != "+id+"";
            

            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(select);

            while (result.next()) {
                Groupe g = new Groupe(result.getInt("id"));
                g.setNom(result.getString("nom"));
                g.setUser_id(result.getInt("user_id"));
                g.setDate_creation(result.getTimestamp("date_creation"));
                g.setDescription(result.getString("description"));
                g.setCategorie(result.getString("categorie"));
                g.setPhoto(result.getString("photo"));
                le.add(g);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return le;

    }

    @Override
    public void update(Groupe g) {
  try {
            String req = "UPDATE groupe SET nom = ?, description=?, categorie=?, `photo`=? WHERE `id` = ?";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, g.getNom());
            st.setString(2, g.getDescription());
            st.setString(3, g.getCategorie());
            st.setString(4, g.getPhoto());
            System.out.println(g.getId());
            st.setInt(5, g.getId());

            st.executeUpdate();
            System.out.println("groupe updated ");
        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void delete(int id) {
      try {
            String req = "DELETE FROM `groupe` WHERE `groupe`.`id` = ? ";
            PreparedStatement st = con.prepareStatement(req);
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException ex) {

            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public Groupe FindGroupe(int id) {
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
        public int FindGroupeByNom(String nom) {
             int id_grp = 0;
        try {
            String select = "SELECT id FROM groupe WHERE  nom = '" + nom + "' ";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(select);

            if (result.next()) {
                id_grp=result.getInt("id");

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return id_grp;
    }
        public  List<User> afficherMem(int id) {
            UserService us=new UserService();
      List<User> lu = new ArrayList<>();
      int id_user=0;
        try {
            String select = "SELECT id_user from grp_users WHERE  id_groupe =  '" + id + "' ";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(select);

            while (result.next()) {
                id_user=result.getInt("id_user");
                User u= us.findById(id_user);
                lu.add(u);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLSTATE: " + ex.getSQLState());
            System.err.println("VnedorError: " + ex.getErrorCode());
        }
        return lu;

    }

    public void retirer(int id_user, int id_groupe) {
     try {
            String req = "DELETE FROM `grp_users` WHERE `id_groupe` = ? AND `id_user` = ?";
            PreparedStatement st = con.prepareStatement(req);
            st.setInt(1, id_groupe);
            st.setInt(2, id_user);
            st.executeUpdate();
        } catch (SQLException ex) {

            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
