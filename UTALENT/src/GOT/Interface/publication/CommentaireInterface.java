package GOT.Interface.publication;


import GOT.entites.publication.Commentaire;
import java.util.List;

public interface CommentaireInterface {
    public void addCommentaire(Commentaire C);
    public List<Commentaire> getCommentaires(int publication_id);
    public void removeCommentaire(int commentaire_id);
    public void updateCommentaire(int commentaire_id , Commentaire new_commentaire);
    
}
