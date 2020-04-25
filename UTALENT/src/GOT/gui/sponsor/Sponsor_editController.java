/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.sponsor;

import GOT.entites.sponsor.Sponsor;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rawia
 */
public class Sponsor_editController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
        Sponsor s = new Sponsor();
     

        
        //  s = sps.show(spController.passingID());
        //    System.out.println(":"+spController.passingID());
    }

    @FXML
    private void sponsorBtn(ActionEvent event) {
        

    }

    public void passingID(String id) {
        
        namesp.setText(id);
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
        Sponsor s = new Sponsor();

    }

}
