/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.entites.reclamation;
import java.sql.Date;
/**
 *
 * @author skand
 */
public class Reclamation {
    private int id;
    private int user_id;
    private Date Date_creation;
    private String description;
    private boolean status;
    private String fichier;
    private String reponse;
    private String type;
    private boolean avis;

    public Reclamation() {
    }

    public Reclamation(int id) {
        this.id = id;
    }

    public Reclamation(int id, String description, String type) {
        this.id = id;
        this.description = description;
        this.type = type;
    }
    
    public Reclamation(int user_id, String description, String fichier, String type) {
        this.user_id = user_id;
        this.description = description;
        this.fichier = fichier;
        this.type = type;
    }

    public Reclamation(int id, int user_id, Date Date_creation, boolean status, String type) {
        this.id = id;
        this.user_id = user_id;
        this.Date_creation = Date_creation;
        this.status = status;
        this.type = type;
    }

    public Reclamation(int id, int user_id, Date Date_creation, String description, boolean status, String fichier, String reponse, String type, boolean avis) {
        this.id = id;
        this.user_id = user_id;
        this.Date_creation = Date_creation;
        this.description = description;
        this.status = status;
        this.fichier = fichier;
        this.reponse = reponse;
        this.type = type;
        this.avis = avis;
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

    public Date getDate_creation() {
        return Date_creation;
    }

    public void setDate_creation(Date Date_creation) {
        this.Date_creation = Date_creation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvis() {
        return avis;
    }

    public void setAvis(boolean avis) {
        this.avis = avis;
    }
    
    
    
}
