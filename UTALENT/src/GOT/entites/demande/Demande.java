/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.entites.demande;

import GOT.entites.groupe.Groupe;
import GOT.entites.user.User;

/**
 *
 * @author HP
 */
public class Demande {
    
    private Integer id;
    private Integer user;
    private Integer groupe;
    private String nom;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }
     public Demande(Integer id) {
        this.id = id;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getGroupe() {
        return groupe;
    }

    public void setGroupe(Integer groupe) {
        this.groupe = groupe;
    }

    public Demande() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", user=" + user + ", nom=" + nom + '}';
    }
    

    public Demande(Integer id, Integer user, String nom) {
        this.id = id;
        this.user = user;
        this.nom = nom;
    }
    
}
