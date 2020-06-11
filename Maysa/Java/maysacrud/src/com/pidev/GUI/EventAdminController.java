/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.GUI;

import com.pidev.entities.User;
import com.pidev.entities.evenement;
import com.pidev.services.ServiceEvenement;
import com.pidev.services.UserService;
import com.pidev.utils.SessionUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maysa1998
 */
public class EventAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private FlowPane paneAdmin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            affichageUS();
        } catch (SQLException ex) {
            Logger.getLogger(EventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    
      private void affichageUS() throws SQLException {

        ServiceEvenement ser = new ServiceEvenement();

        ObservableList<evenement> listEvent = FXCollections.observableArrayList(ser.readAll());

        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();
        ArrayList<HBox> btnP = new ArrayList<>();

        for (int i = 0; i < listEvent.size(); i++) {

            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(29);
            h.setPrefHeight(24);
            h.setVisible(false);
            as.add(h);

            VBox VBoxEvent = new VBox();

            VBoxEvent.setSpacing(7);
            VBoxEvent.setStyle("-fx-background-color : #FFFFFF;");
//            VBoxEvent.setStyle("-fx-border-color : #e44d3a;");
            VBoxEvent.setAlignment(Pos.CENTER);
            VBoxEvent.setPrefHeight(277);
            VBoxEvent.setPrefWidth(300);
            HBox panne = new HBox();
            panne.setSpacing(5);
            panne.setStyle("-fx-background-color : #FFFFFF;");
            //VBoxEvent.setStyle("-fx-border-color : #2B48E8;");
            panne.setAlignment(Pos.CENTER);
            panne.setPrefHeight(30);
            panne.setPrefWidth(80);

            Rectangle c = new Rectangle(300, 125);

            try {
              
                c.setFill(new ImagePattern(new Image(new FileInputStream("C:/wamp64/www/PI_DEV_Image/" + listEvent.get(i).getImage()))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ChasseureventController.class.getName()).log(Level.SEVERE, null, ex);
            }
            VBoxEvent.getChildren().add(c);

            Label titreEvent = new Label("Titre : " + listEvent.get(i).getTitre());
            Label description = new Label("Description: " + listEvent.get(i).getDescription());
  

            int idEvenement = listEvent.get(i).getId_evenement();
            evenement e1 = ser.findById(idEvenement);
            String imgMaysa=e1.getImage();
            

            String Titre1 = e1.getTitre();
            String Description1 = e1.getDescription();
            String Image1 = e1.getImage();
            Date DateD1 = e1.getDatedebut();
            Date DateF1 = e1.getDatefin();
            String Type1 = e1.getTypedetalent();
            int Cout1 = e1.getCout();
            int Nb1 = e1.getNbparticipant();
            UserService u = new UserService();
            User userTest = u.findById(SessionUser.getUser().getId());

            Button btnSupp = new Button("Supprimer");
            btnSupp.setTextOverrun(OverrunStyle.CLIP);
            btnSupp.setStyle("-fx-background-color : #E82B34;");

            //btnSupp.setDisable(true);
            btnSupp.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        Button button2 = new Button();
                        button2.setStyle("-fx-background-color: #F54F4F");
                        alert.setTitle("Suppression ");
                        alert.setContentText("Voulez-vous vraiment supprimer cette evenement ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            ser.delete(e1);
                            paneAdmin.getChildren().clear();
                            affichageUS();

                        } else {
                            paneAdmin.getChildren().clear();
                            affichageUS();

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            });

            Button btnDetail = new Button("Details");


            btnDetail.setStyle("-fx-background-color : #CFDBD1;");
            btnDetail.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                   


                       

                        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("detailevent.fxml"));
                        Parent root;
                    try {
                        root = LOADER.load();
                        DetaileventController maysa = LOADER.getController();
                        maysa.setDetails(Titre1, Description1, Image1, DateD1, DateF1, Type1, Cout1, Nb1);

                        Scene scene = new Scene(root, 600, 400);

                        Stage primaryStage = new Stage();
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(EventAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        

                   

                   

                }
            });
                     
            panne.getChildren().add(btnSupp);
            panne.getChildren().add(btnDetail);
            VBoxEvent.getChildren().add(panne);


            
            
            VBoxEvent.getChildren().add(titreEvent);
            VBoxEvent.getChildren().add(description);


            vbx.add(VBoxEvent);
            btnP.add(panne);
            paneAdmin.getChildren().add(vbx.get(i));

            paneAdmin.getChildren().add(as.get(i));

            if (i == 0) {
                System.out.println("i=0 llllll");
            } else if (((i + 1) % 12) == 0) {
                Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                sepHoriz.setPrefWidth(364);
                sepHoriz.setPrefHeight(30);
                sepHoriz.setVisible(false);
                paneAdmin.getChildren().add(sepHoriz);

            }
        }
    }
    
}
