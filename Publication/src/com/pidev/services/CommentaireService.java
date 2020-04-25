package com.pidev.services;

import com.pidev.entities.Commentaire;
import com.pidev.entities.Like;
import com.pidev.iservice.CommentaireInterface;
import GOT.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentaireService implements CommentaireInterface {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet res;    

    public CommentaireService() {
        con = MyConnection.getInstance().getCnx();
    }

    @Override
    public void addCommentaire(Commentaire C) {
        try
        {
            String req1 = "insert into commentaire(Commentaire, Publication_id, userIdPublication) values(?, ?, ?)" ;
            ps = con.prepareStatement(req1) ;
            ps.setString(1, C.getCommentaire()) ;
            ps.setInt(2, C.getPublication_Id()) ;
            ps.setInt(3, C.getUserIdPublication()) ;
            ps.executeUpdate() ;
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public List<Commentaire> getCommentaires(int publication_id) {
        List<Commentaire> Commentaires = new ArrayList<>();
        try
        {
            String req2 = "SELECT * FROM commentaire INNER JOIN publication ON commentaire.Publication_id = publication.id WHERE Publication_id = ? ;";
            ps = con.prepareStatement(req2);
            ps.setInt(1, publication_id);
            res = ps.executeQuery();
            while(res.next())
            {
                Commentaires.add(new Commentaire(res.getInt("id"), res.getString("Commentaire"), res.getTimestamp("dateCommentaire"), res.getInt("Publication_id"), res.getInt("userIdPublication")));
            }
        }catch(SQLException e)
        {System.out.println(e);}
        return Commentaires ;
    }

    @Override
    public void removeCommentaire(int commentaire_id) {
        try
        {
            String req3 = "DELETE FROM commentaire WHERE id=?" ;
            ps = con.prepareStatement(req3);
            ps.setInt(1, commentaire_id);
            ps.executeUpdate();
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void updateCommentaire(int commentaire_id, Commentaire new_commentaire) {
        try
        {
            String req4 = "UPDATE commentaire SET commentaire=? where id=?;" ;
            ps = con.prepareStatement(req4);
            ps.setString(1, new_commentaire.getCommentaire()) ;
            ps.setInt(2, commentaire_id) ;
            ps.executeUpdate();
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
}
