/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.entites.sponsor;

import javafx.beans.property.SimpleStringProperty;
import javax.annotation.Generated;

/**
 *
 * @author Rawia
 */
public class Sponsor {

   
    private int idsp;
    private SimpleStringProperty  namesp;
    private SimpleStringProperty address;
    private String email;
    private String tel;
    private String description;
    private String logo;
    private int invest;

    public Sponsor(int idsp,String namesp, String address, int invest) {
        this.idsp=idsp;
        this.namesp = new SimpleStringProperty(namesp);
        this.address = new SimpleStringProperty(address) ;
        this.invest=invest;
    }

    
    public Sponsor(String namesp, String address, String email, String tel, String description, String logo) {
        this.namesp = new SimpleStringProperty(namesp);
        this.address = new SimpleStringProperty(address);
        this.email = email;
        this.tel = tel;
        this.description = description;
        this.logo = logo;
    }

    public Sponsor() {
    }

    
    
    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getNamesp() {
        return namesp.get();
    }

    public void setNamesp(SimpleStringProperty namesp) {
        this.namesp = namesp;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(SimpleStringProperty address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    public int getInvest() {
        return invest;
    }

    public void setInvest(int invest) {
        this.invest = invest;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "idsp=" + idsp + ", namesp=" + namesp + ", address=" + address + ", email=" + email + ", tel=" + tel + ", description=" + description + ", logo=" + logo + ", invest=" + invest + '}';
    }
    
            
}

