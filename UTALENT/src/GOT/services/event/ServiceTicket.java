/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.event;

import GOT.Interface.event.IServiceTicket;
import GOT.entites.user.User;
import GOT.entities.event.evenement;
import GOT.entities.event.ticket;
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
 * @author maysa1998
 */
public class ServiceTicket implements IServiceTicket<ticket>

{
    
    
       private Connection con;
    private Statement ste;

    public ServiceTicket() {
        con = MyConnection.getInstance().getCnx();

    }
    
    

    @Override
    public void ajouter(ticket t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `esprit`.`ticket` (`id_ticket` ,`evenement_id`, `dateemission` , `iduser` ) VALUES (NULL, '" + t.getEvenement_id().getId_evenement()+ "' , NOW(), '" + t.getIduser()+ "' );";
        ste.executeUpdate(requeteInsert);    }

    @Override
    public boolean delete(ticket t) throws SQLException {
  PreparedStatement pre = con.prepareStatement("DELETE FROM `esprit`.`ticket` where id_ticket =?");
       
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A Ticket was deleted successfully!");
        }
        return true;    }

    @Override
public List<ticket> readAll() throws SQLException {
List<ticket> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from ticket");
        while (rs.next()) {
            int id_ticket = rs.getInt(1);
            int id_evenement = rs.getInt(2);//3
            int id_user = rs.getInt(4);
          ServiceEvenement ser2= new ServiceEvenement();
            evenement event2 =ser2.findById(id_evenement);
  
            Timestamp DateEmission = rs.getTimestamp("DateEmission");
            ticket c = new ticket(id_ticket,event2, DateEmission,id_user);
            arr.add(c);
        }
        return arr;

    }

    @Override
    public ticket findById(int idTicket) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ticket findBy(evenement evt , User user) {
        String req = "select * from ticket where evenement_id = ? AND iduser = ?";
        ticket ticket=new ticket();
        try{
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, evt.getId_evenement());
            ps.setInt(2, user.getId());
            
            ResultSet resultSet = ps.executeQuery();
            
           
            if (resultSet.next()) {
                
            int idTicket = resultSet.getInt(1);
            int idevenement = resultSet.getInt(2);
            ServiceEvenement ser2= new ServiceEvenement();
            evenement evt2 =ser2.findById(idevenement);
            int idUser = resultSet.getInt(4);
          
            
            ticket= new ticket(idTicket, evt2, resultSet.getDate(3), idUser);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;

}

   
    
    
    
    
}
