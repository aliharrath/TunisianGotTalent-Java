package com.pidev.services;

import com.pidev.entities.Like;
import com.pidev.entities.View;
import com.pidev.iservice.LikeInterface;
import GOT.utils.MyConnection;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeService implements LikeInterface {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet res;    

    public LikeService() {
        con = MyConnection.getInstance().getCnx();
    }

    @Override
    public void addLike(Like L) {
        try
        {
            String req1 = "insert into likes(userId, Publication_id) values(?, ?)" ;
            ps = con.prepareStatement(req1) ;
            ps.setInt(1, L.getUserId()) ;
            ps.setInt(2, L.getPublication_id()) ;
            ps.executeUpdate() ;
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public List<Like> getLikes(int publication_id) {
        List<Like> Likes = new ArrayList<>();
        try
        {
            String req2 = "select * from likes inner join publication on Publication_id = publication.id where Publication_id = ? ;";
            ps = con.prepareStatement(req2);
            ps.setInt(1, publication_id);
            res = ps.executeQuery();
            while(res.next())
            {
                Likes.add(new Like(res.getInt("id"), res.getInt("userId"), res.getInt("Publication_id")));
            }
        }catch(SQLException e)
        {System.out.println(e);}
        return Likes ;
    }

    @Override
    public void removeLike(int like_id) {
        try
        {
            String req3 = "DELETE FROM likes WHERE id=?" ;
            ps = con.prepareStatement(req3);
            ps.setInt(1, like_id);
            ps.executeUpdate();
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public Like likeExists(int publication_id, int user_id) {
        Like L = null ;
        try
        {
            String req2 = "select * from likes inner join publication on Publication_id = publication.id where Publication_id = ? and userId = ? ;";
            ps = con.prepareStatement(req2);
            ps.setInt(1, publication_id);
            ps.setInt(2, user_id);
            res = ps.executeQuery();
            while(res.next())
            {
                L = new Like(res.getInt("id"), res.getInt("userId"), res.getInt("Publication_id"));
            }
        }catch(SQLException e)
        {System.out.println(e);}
        return L ;
    }
    
}
