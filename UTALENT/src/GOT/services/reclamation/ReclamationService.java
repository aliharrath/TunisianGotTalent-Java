/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.reclamation;
import GOT.Interface.reclamation.ReclamationInterface;
import GOT.entites.reclamation.Avis;
import GOT.entites.reclamation.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import GOT.utils.MyConnection;
//import reclamationUtils.Mail;

/**
 *
 * @author skand
 */
public class ReclamationService implements ReclamationInterface{
    private Connection con;
    private Statement ste;
    
    public ReclamationService(){
        con = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public Boolean ExistReclamation(int id) throws SQLException{
        ste=con.createStatement();
        int p=0;
        String query="select * from reclamation where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            p++;
        }
        if(p==0){
            return false;
        }
        else{
            return true;
        }
    }
    
    @Override
    public Boolean ajouterReclamation(Reclamation r) throws SQLException{
        ste = con.createStatement();
        String requeteInsert;
        requeteInsert = "INSERT INTO `esprit`.`reclamation` (`user_id` , `description`, `fichier`, `type`) VALUES ('" + r.getUser_id()+ "' , '" + r.getDescription()+ "', '" +r.getFichier()+ "', '" +r.getType()+ "');";
        if(ExistReclamation(r.getId()))
        {
            System.out.println("Reclamation existante");
            return false;
        }
        else 
        {
            ste.executeUpdate(requeteInsert);
            System.out.println("Reclaamtion ajoutée") ;
            try 
            {
                //Mail mail =new Mail("talentaholic2020@gmail.com","yahia212","skander.baccouche@esprit.tn","","Bonjour,\n\n" + "Votre Reclamation a été ajouté  avec succée.");            
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        return true ;
    }

    @Override
    public List<Reclamation> AfficherReclamation() throws SQLException {
        List<Reclamation> list= new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from reclamation");
        while (rs.next()) {     
           Reclamation p=new Reclamation(rs.getInt("id"),rs.getInt("user_id"),rs.getDate("Date_creation"),rs.getString("description"),rs.getBoolean("status"),rs.getString("fichier"),rs.getString("reponse"),rs.getString("type"),rs.getBoolean("avis"));
        list.add(p);
        }
        return list;
    }
    @Override
    public List<Reclamation> AfficherReclamation(int id_user) throws SQLException {
        List<Reclamation> list= new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from reclamation where user_id="+id_user);
        while (rs.next()) {     
           Reclamation p=new Reclamation(rs.getInt("id"),rs.getInt("user_id"),rs.getDate("Date_creation"),rs.getString("description"),rs.getBoolean("status"),rs.getString("fichier"),rs.getString("reponse"),rs.getString("type"),rs.getBoolean("avis"));
        list.add(p);
        }
        return list;
    }

    @Override
    public Reclamation getReclamationById(int id) throws SQLException {
        ste=con.createStatement();
        String query="select * from reclamation where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            Reclamation p=new Reclamation(rs.getInt("id"),rs.getInt("user_id"),rs.getDate("Date_creation"),rs.getString("description"),rs.getBoolean("status"),rs.getString("fichier"),rs.getString("reponse"),rs.getString("type"),rs.getBoolean("avis"));
            return p;
        }
        return null;
    }

    @Override
    public Boolean deleteReclamation(int id) throws SQLException {
        Avis a=new Avis();
        a=chercheAvis(id);
        if(a!=null)
        {
           AvisService as=new AvisService();
            as.deleteAvis(a.getId()); 
        }
        
        ste=con.createStatement();
        String query="delete from reclamation where id="+id;
        if(ExistReclamation(id))
        {
            ste.execute(query);
            return true;
        }
        System.out.println("reclamation n'existe pas");
        return false;
    }

    @Override
    public Boolean updateReclamation(Reclamation r) throws SQLException {
        if(ExistReclamation(r.getId()))
        {
            ste=con.createStatement();
            String query="UPDATE reclamation set type='"+r.getType()+"',description='"+r.getDescription()+"'where id='"+r.getId()+"'";
            ste.execute(query);
            return true;
        }
        return false;
    }
    
    @Override
    public Boolean updateAvis(int id) throws SQLException {
        if(ExistReclamation(id))
        {
            ste=con.createStatement();
            String query="UPDATE reclamation set avis='"+1+"'where id='"+id+"'";
            ste.execute(query);
            return true;
        }
        return false;
    }
    
    @Override
    public Avis chercheAvis(int id){
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query="select * from avis where reclamation_id="+id;
        ResultSet rs;
        try {
            rs = ste.executeQuery(query);
            while(rs.next())
            {
                Avis p=new Avis(rs.getInt("id"),rs.getInt("user_id"),rs.getInt("reclamation_id"),rs.getInt("note"),rs.getString("description"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    
    
}
