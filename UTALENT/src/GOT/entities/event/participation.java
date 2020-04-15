/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.entities.event;

/**
 *
 * @author maysa1998
 */
public class participation {
    
    public int idparticipation ;
    public int iduser ;
    public evenement evenement;

    public participation(int iduser, evenement evenement) {
        this.iduser = iduser;
        this.evenement = evenement;
    }

    public participation(int idparticipation, int iduser, evenement evenement) {
        this.idparticipation = idparticipation;
        this.iduser = iduser;
        this.evenement = evenement;
    }

    public int getIdparticipation() {
        return idparticipation;
    }

    public void setIdparticipation(int idparticipation) {
        this.idparticipation = idparticipation;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(evenement evenement) {
        this.evenement = evenement;
    }

    @Override
    public String toString() {
        return "participation{" + "idparticipation=" + idparticipation + ", iduser=" + iduser + ", evenement=" + evenement + '}';
    }
    
    
    
    
    
}
