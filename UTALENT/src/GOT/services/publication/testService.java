package GOT.services.publication;

import GOT.Interface.publication.testInterface;
import GOT.entites.publication.test;
import GOT.utils.MyConnection;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class testService implements testInterface{
    Connection con ;
    public testService()
    {
        con = MyConnection.getInstance().getCnx();
    }

    @Override
    public void addTest(test t) {
        try
        {
            String req1 = "insert into test(nom, age) values('" + t.getNom() + "', " + t.getAge() + ")" ;
            Statement ste = con.createStatement();
            ste.executeUpdate(req1);
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    @Override
    public List<test> getTests() {
        List<test> tests = new ArrayList<>();
        try
        {
            String req2 = "select * from test ;";
            Statement ste = con.createStatement();
            ResultSet res = ste.executeQuery(req2);
            while(res.next())
            {
                test testsample = new test(res.getString("nom"), parseInt(res.getString("age")));
                tests.add(testsample);
                //System.out.println("Nom: " + res.getString("nom") + "\tAge: " + res.getInt("age"));
            }
        }catch(SQLException e)
        {System.out.println(e);}
        return tests ;
    }

    @Override
    public void removeTest(test t) {
        try
        {
            String req4 = "DELETE FROM test WHERE id=?" ;
            PreparedStatement ste = con.prepareStatement(req4);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        }catch(SQLException e)
        {
            System.out.println(e);
        }    
    }
    
    @Override
    public void updateTest(test t, test new_t){
        try
        {
            String req3 = "UPDATE test SET nom=?,age=? where id=?;" ;
            PreparedStatement ste = con.prepareStatement(req3);
            ste.setString(1, new_t.getNom());
            ste.setInt(2, new_t.getAge());
            ste.setInt(3, t.getId());
            ste.executeUpdate();
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
}