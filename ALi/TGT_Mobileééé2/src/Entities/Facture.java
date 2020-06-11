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
public class Facture {
     private int id;
    private int user_id;
    private Date date_cr;
    int donation_id;

    public Facture(int id, int user_id, Date date_cr, int donation_id) {
        this.id = id;
        this.user_id = user_id;
        this.date_cr = date_cr;
        this.donation_id = donation_id;
    }

    public Facture(int user_id, Date date_cr, int donation_id) {
        this.user_id = user_id;
        this.date_cr = date_cr;
        this.donation_id = donation_id;
    }

    public Facture() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate_cr() {
        return date_cr;
    }

    public void setDate_cr(Date date_cr) {
        this.date_cr = date_cr;
    }

    public int getDonation_id() {
        return donation_id;
    }

    public void setDonation_id(int donation_id) {
        this.donation_id = donation_id;
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", user_id=" + user_id + ", date_cr=" + date_cr + ", donation_id=" + donation_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
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
        final Facture other = (Facture) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
