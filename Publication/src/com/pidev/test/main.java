package com.pidev.test;
import com.pidev.entities.Commentaire;
import com.pidev.entities.Like;
import com.pidev.entities.Publication;
import com.pidev.entities.View;
import com.pidev.services.CommentaireService;
import com.pidev.services.LikeService;
import com.pidev.services.PublicationService;
import com.pidev.services.ViewService;
import GOT.utils.MyConnection;
import java.sql.SQLException;
import java.sql.Timestamp;
public class main {
    public static void main(String[] args) throws SQLException
    {
        PublicationService ps = new PublicationService() ;
        ViewService vs = new ViewService() ;
        LikeService ls = new LikeService() ;
        CommentaireService cs = new CommentaireService() ;
        //cs.addCommentaire(new Commentaire("hiiii", 61, 3));
        //ls.addLike(new Like(3, 54));
        //View v = new View(3, 55) ;
        Publication p1 = new Publication("testing java.....56", "img", "20190626_195322-5e444d0b78074.jpeg", "music", 3) ;
        ps.addPublication(p1);
        System.out.println(ps.getPublications());
        /*ps.removePublication(82);
        p1.setTitrePublication("modified");
        ps.updatePublication(83, p1);*/
        //vs.addView(v);
        //System.out.println(vs.getViews(61));
        //System.out.println(ps.getPublications());
        //cs.removeCommentaire(83);
        //cs.updateCommentaire(70, new Commentaire("hihihiihhii"));
        //System.out.println(vs.getViews(7));
        Publication p = new Publication();
        p.setId(51);
        p.setUserIdPublication(3);
        
        //ps.addView(84, 3);
        ps.removePublication(84);
        
        MyConnection.getInstance().getCnx().close();
    }
}