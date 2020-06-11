/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.entities.donation;

import java.sql.Timestamp;

/**
 *
 * @author Ali
 */
public class donation {
    private int id;
    private String lib_donation;
    private Timestamp date_cr;
    private int valeur_d;
    private String photo;
    private String description;
    private String categorie;
    private int user_id1;
    private int hidden ;

    public donation() {
    }

    public donation(String lib_donation, int valeur_d, String photo, String description, String categorie, int user_id1) {
       
        this.lib_donation = lib_donation;
       
        this.valeur_d = valeur_d;
        this.photo = photo;
        this.description = description;
        this.categorie = categorie;
        this.user_id1 = user_id1;
       
    }

    public donation(int id, String lib_donation, int valeur_d, String photo, String description, String categorie, int user_id1, int hidden) {
        this.id = id;
        this.lib_donation = lib_donation;
        this.valeur_d = valeur_d;
        this.photo = photo;
        this.description = description;
        this.categorie = categorie;
        this.user_id1 = user_id1;
        this.hidden = hidden;
    }

      public donation(int id, String lib_donation,int valeur_d, String photo, String description, int user_id1) {
        this.id = id;
        this.lib_donation = lib_donation;
        this.valeur_d = valeur_d;
       this.description = description;
        this.photo = photo;
       this.user_id1 = user_id1;
       
    }
      
      public donation(int id, String lib_donation,int valeur_d, String photo, String description) {
        this.id = id;
        this.lib_donation = lib_donation;
        this.valeur_d = valeur_d;
       this.description = description;
        this.photo = photo;
       
       
    }



    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLib_donation() {
        return lib_donation;
    }

    public void setLib_donation(String lib_donation) {
        this.lib_donation = lib_donation;
    }

    public Timestamp getDate_cr() {
        return date_cr;
    }

    public void setDate_cr(Timestamp date_cr) {
        this.date_cr = date_cr;
    }

    public int getValeur_d() {
        return valeur_d;
    }

    public void setValeur_d(int valeur_d) {
        this.valeur_d = valeur_d;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getUser_id1() {
        return user_id1;
    }

    public void setUser_id1(int user_id1) {
        this.user_id1 = user_id1;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "donation{" + "id=" + id + ", lib_donation=" + lib_donation + ", date_cr=" + date_cr + ", valeur_d=" + valeur_d + ", photo=" + photo + ", description=" + description + ", categorie=" + categorie + ", user_id1=" + user_id1 + ", hidden=" + hidden + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
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
        final donation other = (donation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
