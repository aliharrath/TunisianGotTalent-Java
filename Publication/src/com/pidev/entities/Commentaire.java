package com.pidev.entities;

import java.sql.Timestamp;
import java.util.Date ;

public class Commentaire {
    private int id ;
    private String Commentaire ;
    private Timestamp dateCommentaire ;
    private int Publication_Id ;
    private int userIdPublication ;

    public Commentaire() {
    }
    
    public Commentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    public Commentaire(String Commentaire, int Publication_Id, int userIdPublication) {
        this.Commentaire = Commentaire;
        this.Publication_Id = Publication_Id;
        this.userIdPublication = userIdPublication;
    }
    
    public Commentaire(int id, String Commentaire, Timestamp dateCommentaire, int Publication_Id, int userIdPublication) {
        this.id = id ;
        this.Commentaire = Commentaire;
        this.dateCommentaire = dateCommentaire;
        this.Publication_Id = Publication_Id;
        this.userIdPublication = userIdPublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    public Timestamp getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Timestamp dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public int getPublication_Id() {
        return Publication_Id;
    }

    public void setPublication_Id(int Publication_Id) {
        this.Publication_Id = Publication_Id;
    }

    public int getUserIdPublication() {
        return userIdPublication;
    }

    public void setUserIdPublication(int userIdPublication) {
        this.userIdPublication = userIdPublication;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commentaire other = (Commentaire) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", Commentaire=" + Commentaire + ", dateCommentaire=" + dateCommentaire + ", Publication_Id=" + Publication_Id + ", userIdPublication=" + userIdPublication + '}';
    }
}
