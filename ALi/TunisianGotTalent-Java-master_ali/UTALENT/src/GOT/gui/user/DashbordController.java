/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.user;

import GOT.entites.user.User;
import GOT.utils.SessionUser;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashbordController implements Initializable {

    @FXML
    private AnchorPane parent;
   
    @FXML
    private JFXComboBox<String> cat;
     ObservableList<String> cat1 = FXCollections.observableArrayList("musique", "dessin", "developpement");

    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 cat.setPromptText("Choisir un type");
        cat.getItems().clear();

        cat.setItems(cat1);
    }    
  
}
