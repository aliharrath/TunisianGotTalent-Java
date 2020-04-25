/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.reclamation;

import GOT.Interface.reclamation.AvisInterface;
import GOT.entites.reclamation.Avis;
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


/**
 *
 * @author skand
 */
public class AvisService implements AvisInterface{

    private Connection con;
    private Statement ste;
    
    public AvisService(){
        con = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public Boolean ExistAvis(int id) throws SQLException {
        ste=con.createStatement();
        int p=0;
        String query="select * from avis where id="+id;
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
    public Boolean ajouterAvis(Avis a) throws SQLException {
      ste = con.createStatement();
        String requeteInsert;
        requeteInsert = "INSERT INTO `esprit`.`avis` (`user_id` , `reclamation_id`, `note`, `description`) VALUES ('" + a.getUser_id()+ "' , '" + a.getReclamation_id()+ "', '" +a.getNote()+ "', '" +a.getDescription()+ "');";
        if(ExistAvis(a.getId()))
            {
            System.out.println("avis existant");
            return false;
            }
        else {
        ste.executeUpdate(requeteInsert);
        System.out.println("utilisateur ajouté") ;
         try {
            //Mail mail =new Mail("talentaholic2020@gmail.com","yahia212","skander.baccouche@esprit.tn","","Bonjour,\n\n" + "Merci pour votre inscription sur Talentahloic. Votre compte est créé");
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
        } catch (Exception ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
        return true ;
    }

    @Override
    public List<Avis> AfficherAvis() throws SQLException {
        List<Avis> list= new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from avis");
        while (rs.next()) {     
           Avis p=new Avis(rs.getInt("id"),rs.getInt("user_id"),rs.getInt("reclamation_id"),rs.getInt("note"),rs.getString("description"));
        list.add(p);
        }
        return list;
    }

    @Override
    public Avis getAvisById(int id) throws SQLException {
        ste=con.createStatement();
        String query="select * from avis where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            Avis p=new Avis(rs.getInt("id"),rs.getInt("user_id"),rs.getInt("reclamation_id"),rs.getInt("note"),rs.getString("description"));
            return p;
        }
        return null;
    }

    @Override
    public Boolean deleteAvis(int id) throws SQLException {
        ste=con.createStatement();
        String query="delete from avis where id="+id;
        if(ExistAvis(id))
        {
            ste.execute(query);
            return true;
        }
        System.out.println("avis n'existe pas");
        return false;
    }
    
}
