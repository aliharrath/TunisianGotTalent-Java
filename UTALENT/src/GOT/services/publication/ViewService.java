package GOT.services.publication;


import GOT.Interface.publication.ViewInterface;
import GOT.entites.publication.View;
import GOT.utils.MyConnection;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ViewService implements ViewInterface {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet res;    

    public ViewService() {
        con = MyConnection.getInstance().getCnx();
    }

    @Override
    public void addView(View V) {
        try
        {
            String req1 = "insert into views(userId, Publication_id) values(?, ?)" ;
            ps = con.prepareStatement(req1) ;
            ps.setInt(1, V.getUserId()) ;
            ps.setInt(2, V.getPublication_id()) ;
            ps.executeUpdate() ;
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public List<View> getViews(int publication_id) {
        List<View> Views = new ArrayList<>();
        try
        {
            String req2 = "select * from views inner join publication on Publication_id = publication.id where Publication_id = ?";
            ps = con.prepareStatement(req2);
            ps.setInt(1, publication_id);
            res = ps.executeQuery();
            while(res.next())
            {
                Views.add(new View(res.getInt("id"), res.getInt("userId"), res.getInt("Publication_id")));
            }
        }catch(SQLException e)
        {System.out.println(e);}
        return Views ;
    }

    @Override
    public View viewExists(int publication_id, int user_id) {
        View V = null ;
        try
        {
            String req2 = "select * from views inner join publication on Publication_id = publication.id where Publication_id = ? and userId = ? ;";
            ps = con.prepareStatement(req2);
            ps.setInt(1, publication_id);
            ps.setInt(2, user_id);
            res = ps.executeQuery();
            while(res.next())
            {
                V = new View(res.getInt("id"), res.getInt("userId"), res.getInt("Publication_id"));
            }
        }catch(SQLException e)
        {System.out.println(e);}
        return V ;
    }
    
}
