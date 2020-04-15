/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.entites.groupe;

import GOT.entites.user.User;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author HP
 */
public class Groupe {
  
    private Integer id;
   
    private Integer user_id;
 
    private String nom;

    private String description;

    private String photo;
    
    private Timestamp date_creation;

    private String categorie;

    private ArrayList<User> Users;

    public ArrayList<User> getUsers() {
        return Users;
    }

    public void setUsers(ArrayList<User> Users) {
        this.Users = Users;
    }
    public Groupe(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Groupe(Integer user_id, String nom, String description, String photo, String categorie) {
        this.user_id = user_id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.categorie = categorie;
    }

    public Groupe() {
    }

  

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
        this.date_creation = date_creation;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Groupe other = (Groupe) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Groupe(Integer useradmin, String nom, String description, String photo, Timestamp date_creation, String categorie) {
        this.user_id = useradmin;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.date_creation = date_creation;
        this.categorie = categorie;
    }

    public Groupe(Integer id, Integer useradmin, String nom, String description, String photo, Timestamp date_creation, String categorie) {
        this.id = id;
        this.user_id = useradmin;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.date_creation = date_creation;
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Groupe{" + "id=" + id + ", user_id=" + user_id + ", nom=" + nom + ", description=" + description + ", photo=" + photo + ", date_creation=" + date_creation + ", categorie=" + categorie + ", Users=" + Users + '}';
    }
       
    
}
