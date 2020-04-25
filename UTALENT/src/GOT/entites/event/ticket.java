/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.entites.event;
import java.util.Date;
import java.util.Objects;
/**
 *
 * @author maysa1998
 */
public class ticket {
    int id_ticket;
    evenement evenement_id;
   
    Date dateemission ;
    int iduser;

    public ticket(int id_ticket, evenement evenement_id, Date dateemission, int iduser) {
        this.id_ticket = id_ticket;
        this.evenement_id = evenement_id;
        this.dateemission = dateemission;
        this.iduser = iduser;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    
    public ticket(){}

    public ticket( int id_ticket,evenement evenement_id, Date Dateemission) {
        this.id_ticket=id_ticket;
        this.evenement_id =evenement_id;
        this.dateemission= dateemission;
        
    }

    public ticket(int id_ticket, evenement evenement_id) {
        this.id_ticket = id_ticket;
        this.evenement_id = evenement_id;
    }

    public ticket(evenement evenement_id, int iduser) {
        this.evenement_id = evenement_id;
        this.iduser = iduser;
    }
    

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public evenement getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(evenement evenement_id) {
        this.evenement_id = evenement_id;
    }

    public Date getDateemission() {
        return dateemission;
    }

    public void setDateemission(Date dateemission) {
        this.dateemission = dateemission;
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
        final ticket other = (ticket) obj;
        if (!Objects.equals(this.dateemission, other.dateemission)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ticket{" + "id_ticket=" + id_ticket + ", evenement_id=" + evenement_id + ", dateemission=" + dateemission + ", iduser=" + iduser + '}';
    }

  
    
    
    
    
   
    }


    
    
    

