package GOT.Interface.publication;

import GOT.entites.publication.Like;
import java.util.List;

public interface LikeInterface {
    public void addLike(Like L);
    public List<Like> getLikes(int publication_id);
    public void removeLike(int like_id);
    public Like likeExists(int publication_id, int user_id);
}
