/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.event;


import GOT.Interface.event.IServiceParticipation;
import GOT.entites.user.User;
import GOT.entities.event.evenement;
import GOT.entities.event.participation;
import GOT.entities.event.ticket;
import GOT.services.user.UserService;
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
public class Serviceparticipation implements IServiceParticipation<participation>{
    
    
         private Connection con;
    private Statement ste;

    public Serviceparticipation() {
        con = MyConnection.getInstance().getCnx();

    }

    

    @Override
    public void ajouter(participation t) throws SQLException {
           ste = con.createStatement();
        String requeteInsert = "INSERT INTO `esprit`.`participation` (`idparticipation`, `iduser`, `idevenement`) VALUES (NULL, '" + t.getIduser() + "', '" + t.getEvenement().getId_evenement() + "');";
        ste.executeUpdate(requeteInsert);
        
    }

    @Override
    public boolean delete(participation t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `esprit`.`participation` where idevenement =?");
        pre.setInt(1, t.getEvenement().getId_evenement());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A participation was deleted successfully!");
        }
        return true;
    }

    public List<evenement> findByRechercher(int iduser) throws SQLException {
        List<evenement> arr = new ArrayList<>();
        String req = "select * from participation where iduser = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, iduser);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int ideve = resultSet.getInt("idevenement");//75
                ServiceEvenement ser2 = new ServiceEvenement();
                evenement evt = ser2.findById(ideve);
                arr.add(evt);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return arr;
    
    
    
    
    
}
    
    public Boolean findParticipation(evenement evt, int user) throws SQLException {
        String req = "select * from participation where iduser = ? And idevenement=?";

        PreparedStatement ps = con.prepareStatement(req);
        ps.setInt(1, user);
        ps.setInt(2, evt.getId_evenement());
        ResultSet rs = ps.executeQuery();
        if (rs.next() == true) {
            System.out.println("Participation found !");
            return true;

        }
        System.out.println("Participation doesnt exist !");
        return false;
    }
    
     public void ParticiperEvenement(evenement evt, User user) throws SQLException {
        Serviceparticipation s = new Serviceparticipation();
        ServiceEvenement s1 = new ServiceEvenement();
        ServiceTicket s2 = new ServiceTicket();
        UserService s3 = new UserService();
        User u = new User();

        if (s.findParticipation(evt, user.getId()) == true) {
            System.out.println("vous etes deja participé ! ");

        } else //            Competition c = s1.findById(comp.getIdCompetition());
        //            u = s3.findById(user.getIdUser());
        ////            System.out.println(u);
        {
            if (user.getNb_diamants() > evt.getCout()) {
                s3.update( user.getNb_diamants() - evt.getCout(), user.getId());
                
                s1.update(evt.getTitre(), evt.getDatedebut(), evt.getDatefin(), evt.getDescription(), evt.getTypedetalent(), evt.getCout(), evt.getImage(), evt.getNbparticipant()-1, evt.getId_evenement());
                
                
                
                participation p;
                ajouter(p = new participation(user.getId(),evt));
               
                    ticket t;
                    

                    s2.ajouter(t = new ticket(evt, user.getId()));

                }

             else {
                System.out.println("vous n avez pas le nombre de diaments exacte !! ");
            } //            System.out.println(20);        //            String louay = u.getSexe().substring(2, 3);
        }        //            System.out.println(louay);

    }

    
}
