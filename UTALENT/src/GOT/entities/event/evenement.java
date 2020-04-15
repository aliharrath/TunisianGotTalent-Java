/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.entities.event;

import java.sql.Date;

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

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
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
    

    public evenement(int id_evenement, String titre, Date datedebut, Date datefin, String description, String typedetalent, int cout, String image, int nbparticipant) {
        this.id_evenement = id_evenement;
        this.titre = titre;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.typedetalent = typedetalent;
        this.cout = cout;
        this.image = image;
        this.nbparticipant = nbparticipant;
    }

    public evenement(String titre, Date datedebut, Date datefin, String description, String typedetalent, int cout,int iduser, String image, int nbparticipant) {
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
    

    public evenement(int id_evenement, String titre, String description, String typedetalent, int cout, String image, int nbparticipant) {
        this.id_evenement = id_evenement;
        this.titre = titre;
        this.description = description;
        this.typedetalent = typedetalent;
        this.cout = cout;
        this.image = image;
        this.nbparticipant = nbparticipant;
    }
    
    

    public int getId_evenement() {
        return id_evenement;
    }

    public String getTitre() {
        return titre;
    }


    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypedetalent(String typedetalent) {
        this.typedetalent = typedetalent;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNbparticipant(int nbparticipant) {
        this.nbparticipant = nbparticipant;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public String getDescription() {
        return description;
    }

    public String getTypedetalent() {
        return typedetalent;
    }

    public int getCout() {
        return cout;
    }

    public String getImage() {
        return image;
    }

    public int getNbparticipant() {
        return nbparticipant;
    }
    
    
      @Override
    public String toString() {
        return "evenement{" + " \n id_evenement=" + id_evenement + ",\n titre=" + titre + ",\n datedebut=" + datedebut + ", \n datefin=" + datefin + ",\n description=" + description + ", \n typedetalent=" + typedetalent + ",\n cout=" + cout + ", \n image=" + image + ", \n nbparticipant=" + nbparticipant + ", \n"+ '}';
    }
    
    
}
