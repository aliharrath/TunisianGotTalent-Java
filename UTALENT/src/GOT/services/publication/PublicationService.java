package GOT.services.publication;


import GOT.Interface.publication.PublicationInterface;
import GOT.entites.publication.Like;
import GOT.entites.publication.Publication;
import GOT.entites.publication.View;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import GOT.utils.MyConnection ;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublicationService implements PublicationInterface {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet res;    

    public PublicationService() {
        con = MyConnection.getInstance().getCnx();
    }

    @Override
    public void addPublication(Publication P) {
        try
        {
            String req1 = "insert into publication(titrePublication, typePublication, srcPublication, catPublication, viewsPublication, datePublication,"
                    + " isVisiblePublication, userIdPublication, likesPublication) values(?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
            ps = con.prepareStatement(req1) ;
            ps.setString(1, P.getTitrePublication()) ;
            ps.setString(2, P.getTypePublication()) ;
            ps.setString(3, P.getSrcPublication()) ;
            ps.setString(4, P.getCatPublication()) ;
            ps.setInt(5, 0) ;
            ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.setInt(7, 1) ;
            ps.setInt(8, P.getUserIdPublication()) ;
            ps.setInt(9, 0) ;
            ps.executeUpdate() ;
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public List<Publication> getPublications() {
        List<Publication> Publications = new ArrayList<>();
        try
        {
            String req2 = "select * from publication order by datePublication DESC;";
            Statement ste = con.createStatement();
            res = ste.executeQuery(req2);
            while(res.next())
            {
                Publications.add(new Publication(res.getInt("id"), res.getString("titrePublication"), res.getString("typePublication"), res.getString("srcPublication"), res.getString("catPublication"), res.getInt("viewsPublication"), res.getTimestamp("datePublication"), res.getInt("isVisiblePublication"), res.getInt("userIdPublication"), res.getInt("likesPublication")));
            }
        }catch(SQLException e)
        {System.out.println(e);}
        return Publications ;
    }
    
    @Override
    public List<Publication> searchPublications(String search_for_title) {
        List<Publication> Publications = new ArrayList<>();
        try
        {
            String req2 = "select * from publication where titrePublication LIKE ? order by datePublication DESC;";
            //Statement ste = con.createStatement();
            ps = con.prepareStatement(req2) ;
            ps.setString(1, "%" + search_for_title + "%") ;
            res = ps.executeQuery();
            while(res.next())
            {
                Publications.add(new Publication(res.getInt("id"), res.getString("titrePublication"), res.getString("typePublication"), res.getString("srcPublication"), res.getString("catPublication"), res.getInt("viewsPublication"), res.getTimestamp("datePublication"), res.getInt("isVisiblePublication"), res.getInt("userIdPublication"), res.getInt("likesPublication")));
            }
        }catch(SQLException e)
        {System.out.println(e);}
        return Publications ;
    }

    @Override
    public void removePublication(int publication_id) {
        try
        {
            String req3 = "DELETE FROM likes WHERE Publication_id=?" ;
            ps = con.prepareStatement(req3);
            ps.setInt(1, publication_id);
            ps.executeUpdate();
            
            req3 = "DELETE FROM views WHERE Publication_id=?" ;
            ps = con.prepareStatement(req3);
            ps.setInt(1, publication_id);
            ps.executeUpdate();
            
            req3 = "DELETE FROM commentaire WHERE Publication_id=?" ;
            ps = con.prepareStatement(req3);
            ps.setInt(1, publication_id);
            ps.executeUpdate();
            
            req3 = "DELETE FROM publication WHERE id=?" ;
            ps = con.prepareStatement(req3);
            ps.setInt(1, publication_id);
            ps.executeUpdate();
        }catch(SQLException e)
        {
            System.out.println(e);
        } 
    }

    @Override
    public void updatePublication(int publication_id, Publication new_publication) {
        try
        {
            String req4 = "UPDATE publication SET titrePublication=?, typePublication=?, srcPublication=?, catPublication=? where id=?;" ;
            ps = con.prepareStatement(req4);
            ps.setString(1, new_publication.getTitrePublication()) ;
            ps.setString(2, new_publication.getTypePublication()) ;
            ps.setString(3, new_publication.getSrcPublication()) ;
            ps.setString(4, new_publication.getCatPublication()) ;
            ps.setInt(5, publication_id) ;
            ps.executeUpdate();
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void makeVisible(Publication P, int user_id) {
        if(P.getUserIdPublication() == user_id){
            try
            {
                String req5 = "UPDATE publication SET isVisiblePublication=? where id=?;" ;
                ps = con.prepareStatement(req5);
                ps.setInt(1, 1) ;
                ps.setInt(2, P.getId()) ;
                ps.executeUpdate();
            }catch(SQLException e)
            {
                System.out.println(e);
            }
        }
    }

    @Override
    public void makeUnvisible(Publication P, int user_id) {
        if(P.getUserIdPublication() == user_id){
            try
            {
                String req5 = "UPDATE publication SET isVisiblePublication=? where id=?;" ;
                ps = con.prepareStatement(req5);
                ps.setInt(1, 0) ;
                ps.setInt(2, P.getId()) ;
                ps.executeUpdate();
            }catch(SQLException e)
            {
                System.out.println(e);
            }
        }
    }

    @Override
    public void like(int publication_id, int user_id) {
        LikeService ls = new LikeService();
        if(ls.likeExists(publication_id, user_id) == null ){
        ls.addLike(new Like(user_id, publication_id));
        try
            {
                String req5 = "UPDATE publication SET likesPublication = likesPublication + 1 where id=?;" ;
                ps = con.prepareStatement(req5);
                ps.setInt(1, publication_id) ;
                ps.executeUpdate();
            }catch(SQLException e)
            {
                System.out.println(e);
            }
        }
    }

    @Override
    public void dislike(int publication_id, int user_id) {
        LikeService ls = new LikeService();
        Like L = ls.likeExists(publication_id, user_id) ;
        if(L != null ){
        ls.removeLike(L.getId());
        try
            {
                String req5 = "UPDATE publication SET likesPublication = likesPublication - 1 where id=?;" ;
                ps = con.prepareStatement(req5);
                ps.setInt(1, publication_id) ;
                ps.executeUpdate();
            }catch(SQLException e)
            {
                System.out.println(e);
            }
        }
    }

    @Override
    public void addView(int publication_id, int user_id) {
        ViewService vs = new ViewService();
        if(vs.viewExists(publication_id, user_id) == null ){
        vs.addView(new View(user_id, publication_id));
        try
            {
                String req5 = "UPDATE publication SET viewsPublication = viewsPublication + 1 where id=?;" ;
                ps = con.prepareStatement(req5);
                ps.setInt(1, publication_id) ;
                ps.executeUpdate();
            }catch(SQLException e)
            {
                System.out.println(e);
            }
        }
    }
}
