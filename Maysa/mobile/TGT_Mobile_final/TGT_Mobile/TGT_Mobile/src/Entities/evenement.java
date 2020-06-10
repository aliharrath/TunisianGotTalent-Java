/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;


/**
 *
 * @author maysa1998
 */
public class evenement {
    
    int id_evenement ;
    
    String titre ;
    Date datedebut ;
    Date datefin ;
    String description	;
    String typedetalent;
    int cout ;
    int iduser;
    String image ;
    int nbparticipant ;

    public evenement() {
    }

    public evenement(String titre, Date datedebut, Date datefin, String description, String typedetalent, int cout, int iduser, String image, int nbparticipant) {
        this.titre = titre;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.typedetalent = typedetalent;
        this.cout = cout;
        this.iduser = iduser;
        this.image = image;
        this.nbparticipant = nbparticipant;
    }

    public evenement(int id_evenement, String titre, Date datedebut, Date datefin, String description, String typedetalent, int cout, int iduser, String image, int nbparticipant) {
        this.id_evenement = id_evenement;
        this.titre = titre;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.typedetalent = typedetalent;
        this.cout = cout;
        this.iduser = iduser;
        this.image = image;
        this.nbparticipant = nbparticipant;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypedetalent() {
        return typedetalent;
    }

    public void setTypedetalent(String typedetalent) {
        this.typedetalent = typedetalent;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbparticipant() {
        return nbparticipant;
    }

    public void setNbparticipant(int nbparticipant) {
        this.nbparticipant = nbparticipant;
    }

    @Override
    public String toString() {
        return "evenement{" + "id_evenement=" + id_evenement + ", titre=" + titre + ", datedebut=" + datedebut + ", datefin=" + datefin + ", description=" + description + ", typedetalent=" + typedetalent + ", cout=" + cout + ", iduser=" + iduser + ", image=" + image + ", nbparticipant=" + nbparticipant + '}';
    }

    
    
}
