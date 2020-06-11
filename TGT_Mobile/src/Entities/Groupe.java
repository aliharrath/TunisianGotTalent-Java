/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;




/**
 *
 * @author HP
 */
public class Groupe {
  
    private Integer id;
   
    private String user;
 
    private String nom;

    private String description;

    private String photo;
    
  //  private Timestamp date_creation;

    private String categorie;

    public Groupe(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

  /*  public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
        this.date_creation = date_creation;
    }
*/
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }


    @Override
    public String toString() {
        return "Groupe{" + "id=" + id + ", user_id=" + user+ ", nom=" + nom + ", description=" + description + ", photo=" + photo +  ", categorie=" + categorie + ""+'}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
 
    
    
}
