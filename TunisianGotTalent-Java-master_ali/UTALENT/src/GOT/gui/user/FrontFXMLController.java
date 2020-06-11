/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.user;

import GOT.utils.SessionUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FrontFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Label username;
    @FXML
    private ImageView don;
    @FXML
    private Button donation;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     username.setText(SessionUser.getUser().getUsername());
    }    

      
    @FXML
    private void go(MouseEvent event) throws IOException
    {           Parent home_page_parent = FXMLLoader.load(getClass().getResource("../donation/Afficherdon.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();

    }
}
