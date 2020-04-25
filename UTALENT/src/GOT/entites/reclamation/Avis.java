/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.entites.reclamation;

/**
 *
 * @author skand
 */
public class Avis {
    private int id;
    private int user_id;
    private int reclamation_id;
    private int note;
    private String description;

    public Avis() {
    }

    public Avis(int id, int user_id, int reclamation_id, int note, String description) {
        this.id = id;
        this.user_id = user_id;
        this.reclamation_id = reclamation_id;
        this.note = note;
        this.description = description;
    }

    public Avis(int user_id, int reclamation_id, int note, String description) {
        this.user_id = user_id;
        this.reclamation_id = reclamation_id;
        this.note = note;
        this.description = description;
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

    public int getReclamation_id() {
        return reclamation_id;
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
