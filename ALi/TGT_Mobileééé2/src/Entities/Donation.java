/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Ali
 */
public class Donation {
      private int id;
    private String lib_donation;
    private Date date_cr;
    private int valeur_d;
    private String photo;
    private String description;
    private String categorie;
    private int user_id;
    private int hidden ;

    public Donation() {
    }

    public Donation(int id) {
        this.id = id;
    }

    public Donation(int id, String lib_donation, Date date_cr, int valeur_d, String photo, String description, String categorie, int user_id, int hidden) {
        this.id = id;
        this.lib_donation = lib_donation;
        this.date_cr = date_cr;
        this.valeur_d = valeur_d;
        this.photo = photo;
        this.description = description;
        this.categorie = categorie;
        this.user_id = user_id;
        this.hidden = hidden;
    }

    public Donation(String lib_donation, String description , String categorie ,int valeur_d, int user_id, String photo) {
        this.lib_donation = lib_donation;
        
        this.valeur_d = valeur_d;
        this.photo = photo;
        this.description = description;
        this.categorie = categorie;
        this.user_id = user_id;
      
    }

    public Donation(String lib_donation, Date date_cr, int valeur_d, String photo, String description, String categorie, int user_id) {
        this.lib_donation = lib_donation;
        this.date_cr = date_cr;
        this.valeur_d = valeur_d;
        this.photo = photo;
        this.description = description;
        this.categorie = categorie;
        this.user_id = user_id;
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

    public Date getDate_cr() {
        return date_cr;
    }

    public void setDate_cr(Date date_cr) {
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
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
        final Donation other = (Donation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
