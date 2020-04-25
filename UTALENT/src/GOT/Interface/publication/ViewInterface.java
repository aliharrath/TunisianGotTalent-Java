package GOT.Interface.publication;


import GOT.entites.publication.View;
import java.util.List;

public interface ViewInterface {
    public void addView(View V);
    public List<View> getViews(int publication_id);
    public View viewExists(int publication_id, int user_id);
}
