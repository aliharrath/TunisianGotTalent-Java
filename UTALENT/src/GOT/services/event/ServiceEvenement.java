/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.event;


import GOT.Interface.event.IServiceEvenement;
import GOT.entites.event.evenement;
import GOT.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maysa1998
 */
public class ServiceEvenement  implements IServiceEvenement<evenement>{
    
    
     private Connection con;
    private Statement ste;

    public ServiceEvenement() {
                con = MyConnection.getInstance().getCnx();


    }

    @Override
    public void ajouter(evenement t) throws SQLException {
         ste = con.createStatement();
        String requeteInsert = "INSERT INTO `esprit`.`evenement` (`id_evenement`, `titre`, `datedebut`, `datefin`, `description`, `typedetalent`, `cout`, `image`, `nbparticipant`, `iduser`) VALUES (NULL, '" + t.getTitre() + "', '" + t.getDatedebut() + "', '" + t.getDatefin() + "', '" + t.getDescription() + "', '" + t.getTypedetalent() + "', '" + t.getCout() + "', '" + t.getImage() + "', '" + t.getNbparticipant() + "', '" + t.getIduser() + "');";
        ste.executeUpdate(requeteInsert);//yhez requete l base de donnee
        
    }
    
    public void ajouter1(evenement p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `esprit`.`evenement` ( `titre`, `datedebut`, `datefin`, `description`, `typedetalent`, `cout`, `image`, `nbparticipant`, `iduser`) VALUES ( ?,?, ?, ?,?, ?, ?,?, ?);");
    pre.setString(1, p.getTitre());
    pre.setDate(2, p.getDatedebut());
    pre.setDate(3, p.getDatefin());
    pre.setString(4, p.getDescription());
    pre.setString(5, p.getTypedetalent());
    pre.setInt(6, p.getCout());
     pre.setString(7, p.getImage());
    pre.setInt(8, p.getNbparticipant());
     pre.setInt(9, p.getIduser());
    pre.executeUpdate();
    }

    @Override
    public boolean delete(evenement t) throws SQLException {
         PreparedStatement pre = con.prepareStatement("DELETE FROM `esprit`.`evenement` where titre =? AND description =?");
        pre.setString(1, t.getTitre());
        pre.setString(2, t.getDescription());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            
        }
        System.out.println("An event was deleted successfully!");
        return true;
    }

    @Override
    public boolean update(String titre,Date dated,Date datef,String description,String type,int cout,String image,int nbp,int id) throws SQLException {
         String sql = "UPDATE evenement SET titre=?, description=?, typedetalent=?, datedebut=?, datefin=?, cout=?,image=?,nbparticipant=? WHERE id_evenement=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, titre);
        statement.setString(2, description);
        statement.setString(3, type);
        statement.setDate(4, dated);
        statement.setDate(5, datef);
        statement.setInt(6, cout);
        statement.setString(7, image);
        statement.setInt(8, nbp);
        statement.setInt(9, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing event was updated successfully!");
        }
        return true;
    }

    @Override
    public List<evenement> readAll() throws SQLException {
        List<evenement> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=
           ste.executeQuery("select * from evenement ");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String titre=rs.getString(2);
               Date datedebut=rs.getDate(3);
                Date datefin=rs.getDate(4);
                String description=rs.getString(5);
               String typedetalent=rs.getString(6);
                int cout=rs.getInt(7);
                String image=rs.getString(8);
                int nbparticipant=rs.getInt(9);
                int iduser=rs.getInt("iduser");
                evenement p = new evenement(id, titre, datedebut, datefin, description, typedetalent, cout,iduser, image, nbparticipant);
     arr.add(p);
     }
    return arr;
    }

     public evenement findById(int id_evenement) throws SQLException {
            String req = "select * from evenement where id_evenement = ?";
            evenement evenement = null;
            try {
                PreparedStatement ps = con.prepareStatement(req);
                 ps.setInt(1, id_evenement);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
               
              
                    evenement = new evenement(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt("iduser"),resultSet.getString(8),resultSet.getInt(9));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return evenement;
        }

 
        
    
    
    
    
}
