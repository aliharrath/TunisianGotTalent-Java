package com.pidev.entities;

import java.time.LocalDate;
import java.sql.Timestamp ;

public class Publication {
    private int id ;
    private String titrePublication ;
    private String typePublication ;
    private String srcPublication ;
    private String catPublication ;
    private int viewsPublication ;
    private Timestamp datePublication ;
    private int isVisiblePublication ;
    private int userIdPublication ;
    private int likesPublication ;

    public Publication() {
    }

    public Publication(String titrePublication, String typePublication, String srcPublication, String catPublication, int userIdPublication) {
        this.titrePublication = titrePublication;
        this.typePublication = typePublication;
        this.srcPublication = srcPublication;
        this.catPublication = catPublication;
        this.userIdPublication = userIdPublication;
    }
    
    public Publication(int id, String titrePublication, String typePublication, String srcPublication, String catPublication, int viewsPublication, Timestamp datePublication, int isVisiblePublication, int userIdPublication, int likesPublication) {
        this.id = id ;
        this.titrePublication = titrePublication;
        this.typePublication = typePublication;
        this.srcPublication = srcPublication;
        this.catPublication = catPublication;
        this.viewsPublication = viewsPublication;
        this.datePublication = datePublication;
        this.isVisiblePublication = isVisiblePublication;
        this.userIdPublication = userIdPublication;
        this.likesPublication = likesPublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitrePublication() {
        return titrePublication;
    }

    public void setTitrePublication(String titrePublication) {
        this.titrePublication = titrePublication;
    }

    public String getTypePublication() {
        return typePublication;
    }

    public void setTypePublication(String typePublication) {
        this.typePublication = typePublication;
    }

    public String getSrcPublication() {
        return srcPublication;
    }

    public void setSrcPublication(String srcPublication) {
        this.srcPublication = srcPublication;
    }

    public String getCatPublication() {
        return catPublication;
    }

    public void setCatPublication(String catPublication) {
        this.catPublication = catPublication;
    }

    public int getViewsPublication() {
        return viewsPublication;
    }

    public void setViewsPublication(int viewsPublication) {
        this.viewsPublication = viewsPublication;
    }

    public Timestamp getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Timestamp datePublication) {
        this.datePublication = datePublication;
    }

    public int getIsVisiblePublication() {
        return isVisiblePublication;
    }

    public void setIsVisiblePublication(int isVisiblePublication) {
        this.isVisiblePublication = isVisiblePublication;
    }

    public int getUserIdPublication() {
        return userIdPublication;
    }

    public void setUserIdPublication(int userIdPublication) {
        this.userIdPublication = userIdPublication;
    }

    public int getLikesPublication() {
        return likesPublication;
    }

    public void setLikesPublication(int likesPublication) {
        this.likesPublication = likesPublication;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", titrePublication=" + titrePublication + ", typePublication=" + typePublication + ", srcPublication=" + srcPublication + ", catPublication=" + catPublication + ", viewsPublication=" + viewsPublication + ", datePublication=" + datePublication + ", isVisiblePublication=" + isVisiblePublication + ", userIdPublication=" + userIdPublication + ", likesPublication=" + likesPublication + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Publication other = (Publication) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
