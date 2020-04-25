/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.entites.sponsor;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;

/**
 *
 * @author Rawia
 */
public class Pack {

    private int idad;
    private int idsp;
    private String positin;
    private String ad;
    private String displaydate;
    private String discarddate;
    private String price;
    private String status;
    private ImageView img;

    public Pack() {
    }
    public Pack(ImageView img){
        this.img= img;
    }
    public Pack( int idsp, String positin, String ad, String displaydate, String discarddate, String price) {
        
        this.idsp = idsp;
        this.positin = positin;
        this.ad = ad;
        this.displaydate = displaydate;
        this.discarddate = discarddate;
        this.price = price;
        
    }

    public Pack(int idad, String displaydate, String discarddate, String status) {
        this.idad=idad;
        this.displaydate = displaydate;
        this.discarddate = discarddate;
        this.status = status;
    }

    public Pack(String positin, String displaydate, String discarddate, String price, String status) {

       
        this.positin = positin;
        this.displaydate = displaydate;
        this.discarddate = discarddate;
        this.price = price;
        this.status = status;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public int getIdad() {
        return idad;
    }

    public void setIdad(int idad) {
        this.idad = idad;
    }

    public String getPositin() {
        return positin;
    }

    public void setPositin(String positin) {
        this.positin = positin;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getDisplaydate() {
        return displaydate;
    }

    public void setDisplaydate(String displaydate) {
        this.displaydate = displaydate;
    }

    public String getDiscarddate() {
        return discarddate;
    }

    public void setDiscarddate(String discarddate) {
        this.discarddate = discarddate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Pack{" + "idad=" + idad + ", positin=" + positin + ", ad=" + ad + ", displaydate=" + displaydate + ", discarddate=" + discarddate + ", price=" + price + ", status=" + status + '}';
    }

}
