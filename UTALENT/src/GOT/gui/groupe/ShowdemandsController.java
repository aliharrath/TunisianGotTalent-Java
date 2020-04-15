/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.groupe;

import GOT.entites.demande.Demande;
import GOT.entites.groupe.Groupe;
import GOT.services.groupe.DemandeService;
import GOT.services.groupe.GroupeService;
import GOT.services.user.UserService;
import GOT.utils.MyConnection;
import GOT.utils.SessionUser;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ShowdemandsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vbox1;
    @FXML
    private ScrollPane s1;
    @FXML 
    private JFXButton okey;      
     private Connection con;
    List<String> li= new ArrayList<>();
    GroupeService gs = new GroupeService();
    DemandeService ds=new DemandeService();
    UserService us=new UserService();
    Demande demande = new Demande();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Demande> lg = new ArrayList<>();
        lg = ds.afficherdm(SessionUser.getUser().getId());

        for (Demande d : lg) {
       
            
            Label nom = new Label();
            nom.setText(d.getNom());
            Label notif=new Label();
            notif.setText(" L'utilisateur "+us.findById(d.getUser()).getUsername()+" souhaite rejoindre ton groupe nommée "+d.getNom()+"       ");
            notif.setFont(javafx.scene.text.Font.font("System", FontWeight.NORMAL, 13));
            HBox h1 = new HBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
            VBox rv = new VBox();
            HBox hv1 = new HBox();
            JFXButton bt = new JFXButton("Accepter");
            JFXButton bt1 = new JFXButton("Refuser");
            final Separator sep = new Separator();
         //   vbox1.setSpacing(5);
            //sep.setMaxWidth(Double.MAX_EXPONENT);
             bt.setStyle("-fx-background-color:  #70D293; -fx-text-fill: White;");
             bt1.setStyle("-fx-background-color:  #F15C5C; -fx-text-fill: White;");
                hv1.getChildren().add(notif);
                hv1.setMaxWidth(Double.MAX_EXPONENT);
                btn.getChildren().add(bt);
                btn.getChildren().add(bt1);
                hv1.getChildren().add(btn);
              
                
            
                vbox1.getChildren().add(hv1);
                vbox1.getChildren().add(v1);
                vbox1.getChildren().add(v2);
                vbox1.getChildren().add(h1);
               
                btn.setAlignment(Pos.CENTER);
                h1.setSpacing(10);
                bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                           
                     
                            try {
                                    String req = "INSERT INTO grp_users (id_user,id_group) VALUES (?,?)";
                                    con = MyConnection.getInstance().getCnx();
                                    PreparedStatement st = con.prepareStatement(req);
                                    st.setInt(1,d.getUser());
                                    st.setInt(2, gs.FindGroupeByNom(d.getNom()));
                                    st.executeUpdate();
                                } catch (SQLException ex) {
                                    Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            ds.Accepter(d.getId());
                            vbox1.getChildren().remove(hv1);
                            vbox1.getChildren().remove(v1);
                            vbox1.getChildren().remove(h1);
                            vbox1.getChildren().remove(btn);
                            vbox1.getChildren().remove(v2);
                            Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                            alertSec.setHeaderText("Demandé Accepté");
                            alertSec.showAndWait();

                    }

                });
                  bt1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                         
                            ds.Accepter(d.getId());
                            vbox1.getChildren().remove(hv1);
                            vbox1.getChildren().remove(v1);
                            vbox1.getChildren().remove(h1);
                            vbox1.getChildren().remove(btn);
                            vbox1.getChildren().remove(v2);
                            Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                            alertSec.setHeaderText("Demandé Refusé");
                            alertSec.showAndWait();

                    }

                });

            
           
            }}    
    
     @FXML
    private void close(MouseEvent event) throws IOException
    {  
        MyGroupeController.validDialog.close();
    }
    
}
