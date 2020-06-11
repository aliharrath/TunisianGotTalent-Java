/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.GUI;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author maysa1998
 */
public class DetaileventController implements Initializable {

   @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label dated;
    @FXML
    private Label datef;
    @FXML
    private Label type;
    @FXML
    private Label cout;
    @FXML
    private Label nb;
    @FXML
    private ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setDetails(String Titre, String Description, String Image, Date DateD, Date DateF,String Type,int Cout,int Nb1) {

        titre.setText(Titre);
        description.setText(Description);
        File file = new File("C:/wamp64/www/PI_DEV_Image/" + Image);

        image.setImage(new Image(file.toURI().toString()));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        String maysa = dateFormat.format(DateD);
        dated.setText(maysa);
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd ");
        String maysa1 = dateFormat1.format(DateF);
        datef.setText(maysa1);
        type.setText(Type);
        cout.setText(Integer.toString(Cout));
        this.nb.setText(Integer.toString(Nb1));
       

    }

   
    
    
}
