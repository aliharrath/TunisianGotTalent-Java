/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.user;

import GOT.entites.user.User;
import GOT.utils.SessionUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashbordController implements Initializable {

    /**
     * Initializes the controller class.
     */
     
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      @FXML
    private HBox log;
      @FXML
    private HBox event;

    @FXML
    private HBox sponsors;
     @FXML
    void Sponsors(MouseEvent event) throws IOException {

          Parent home_page_parent = FXMLLoader.load(getClass().getResource("../sponsor/back.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }

    @FXML
    void event(MouseEvent event) throws IOException {
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("../event/EventAdmin.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
      @FXML
    void logout(MouseEvent event) throws IOException {
                 Parent home_page_parent = FXMLLoader.load(getClass().getResource("user.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
}
