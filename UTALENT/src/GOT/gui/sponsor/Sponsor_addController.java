/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.sponsor;

import GOT.entites.sponsor.Sponsor;
import GOT.services.sponsor.SponsorServices;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rawia
 */
public class Sponsor_addController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private VBox inbox;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField namesp;
    @FXML
    private JFXTextField desc;
    @FXML
    private TextField logo;
    @FXML
    private JFXTextField tel;
    @FXML
    private Label notif;
    @FXML
    private Label errname;
    @FXML
    private Label erremail;
    @FXML
    private Label erraddress;
    @FXML
    private Label errtel;
    @FXML
    private Label errdesc;
    @FXML
    private Label errlogo;
    boolean verif = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sponsorBtn(ActionEvent event) {
        Parent home_page_parent;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("sponsor_index.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void packBtn(ActionEvent event) {
        Parent home_page_parent;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("pack_index.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void inboxBtn(ActionEvent event) {
        Parent home_page_parent;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("mail_inbox.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void saveBtn(ActionEvent event) {
        
         notif.setText("");   
        SponsorServices sps = new SponsorServices();

        System.out.println("here:" + verif);

        String path = logo.getText();
        path = path.replace("\\", "/");

        if (verif) {
           if (sps.sponsor_add(new Sponsor(namesp.getText(), address.getText(), email.getText(), tel.getText(), desc.getText(), path))) {
                errname.setText("");
                notif.setTextFill(Paint.valueOf("GREEN"));
                notif.setText("Sponsor enregistré!");
            } else {
                errname.setTextFill(Paint.valueOf("RED"));
                errname.setText("Sponsor existe déjà!");
            }  
        }
        else{
             
             notif.setTextFill(Paint.valueOf("Red"));
            notif.setText("Verifier vos donnees.");   
            
        }
            
    }

    @FXML
    private void uploadBtn(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        Image image1;
        if (selectedFile != null) {
            logo.setText(selectedFile.getAbsolutePath());
            try {
                image1 = new Image(new FileInputStream(selectedFile.getAbsolutePath()));
                // logo.setImage(image1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Pack_addController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("file is not valid!");
        }
    }

    @FXML
    private boolean controleSaisi(KeyEvent event) {
        SponsorServices sps = new SponsorServices();

        if (namesp.getText().isEmpty()) {
            errname.setTextFill(Paint.valueOf("RED"));
            errname.setText("Champ obligatoire! ");
            verif = false;
           
        } else {
            errname.setTextFill(Paint.valueOf("GREEN"));
            errname.setText("OK. ");
            verif = true;
        }
        if (address.getText().isEmpty()) {
            erraddress.setTextFill(Paint.valueOf("RED"));
            erraddress.setText("Champ obligatoire! ");
           verif = false;
        } else {
            erraddress.setTextFill(Paint.valueOf("GREEN"));
            erraddress.setText("OK. ");
            verif = true;
        }
        if (desc.getText().isEmpty()) {
            errdesc.setTextFill(Paint.valueOf("RED"));
            errdesc.setText("Champ obligatoire! ");
            verif = false;
        } else {
            errdesc.setTextFill(Paint.valueOf("GREEN"));
            errdesc.setText("OK. ");
            verif = true;
        }

        if (!sps.checkMail(email.getText())) {
            erremail.setTextFill(Paint.valueOf("RED"));
            erremail.setText("Email non valide détecté! ");
           verif = false; 
        } else if (email.getText() == "") {
            erremail.setTextFill(Paint.valueOf("RED"));
            erremail.setText("Champ obligatoire! ");
           verif = false;
        } else {
            erremail.setTextFill(Paint.valueOf("GREEN"));
            erremail.setText("OK. ");
            verif = true;
        }
        if (!sps.checkTel(tel.getText())) {
            errtel.setTextFill(Paint.valueOf("RED"));
            errtel.setText("Numéro non valide détecté! ");
            verif = false;
        } else if (tel.getText() == "") {
            errtel.setTextFill(Paint.valueOf("RED"));
            errtel.setText("Champ obligatoire! ");
           verif = false;
        } else {
            errtel.setTextFill(Paint.valueOf("GREEN"));
            errtel.setText("OK. ");
            verif = true;
        }
        if(!(namesp.getText().isEmpty()||email.getText().isEmpty()||address.getText().isEmpty()||tel.getText().isEmpty()||desc.getText().isEmpty()||logo.getText().isEmpty()))
            verif=true;
       
        System.out.println("verif"+verif);
        return verif;
    }

}
