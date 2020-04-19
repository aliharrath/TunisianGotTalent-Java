package com.pidev.iservice;

import com.pidev.entities.Publication;
import java.util.List;

public interface PublicationInterface {
    public void addPublication(Publication P);
    public List<Publication> getPublications();
    public void removePublication(int publication_id);
    public void updatePublication(int publication_id, Publication new_publication);
    public void makeVisible(Publication P, int user_id);
    public void makeUnvisible(Publication P, int user_id);
    public void like(int publication_id, int user_id);
    public void dislike(int publication_id, int user_id);
    public void addView(int publication_id, int user_id);
    public List<Publication> searchPublications(String search_for_title);
}
