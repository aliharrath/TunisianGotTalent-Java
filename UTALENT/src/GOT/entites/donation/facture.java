/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.entites.donation;

import java.sql.Timestamp;

/**
 *
 * @author Ali
 */
public class facture {
     private int id;
    private int user_id;
    private Timestamp date_cr;
    donation donation;

    public facture() {
    }

    public facture(int id, int user_id, Timestamp date_cr, donation donation) {
        this.id = id;
        this.user_id = user_id;
        this.date_cr = date_cr;
        this.donation = donation;
    }

    public facture(int user_id, Timestamp date_cr, donation donation) {
        this.user_id = user_id;
        this.date_cr = date_cr;
        this.donation = donation;
    }
      public facture( int user_id, donation donation) {
        this.user_id = user_id;
     
        this.donation = donation;
    }

    public facture(int id, donation don, Timestamp date_cr, int user_id) {
       this.id = id;
       this.donation = don;
        this.date_cr = date_cr;
        this.user_id = user_id;
        
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

    public Timestamp getDate_cr() {
        return date_cr;
    }

    public void setDate_cr(Timestamp date_cr) {
        this.date_cr = date_cr;
    }

    public donation getDonation() {
        return donation;
    }

    public void setDonation(donation donation) {
        this.donation = donation;
    }

    @Override
    public String toString() {
        return "facture{" + "id=" + id + ", user_id=" + user_id + ", date_cr=" + date_cr + ", donation=" + donation + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
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
        final facture other = (facture) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
}
