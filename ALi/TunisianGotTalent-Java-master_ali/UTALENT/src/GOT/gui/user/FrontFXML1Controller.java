/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.user;


import GOT.utils.SessionUser;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FrontFXML1Controller implements Initializable {
 
    
    
    @FXML // Father of all components
    private StackPane root;

    private VBox validPane, invalidPane;

    public static JFXDialog validDialog, invalidDialog;
    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView logout;
    @FXML
    private Label close;
    @FXML
    private Label role;
    @FXML
    private Label name;
    @FXML
    private Button groupe;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       name.setText(SessionUser.getUser().getUsername());
       //if(SessionUser.getUser().getRoles().equals("talent"))
       role.setText("Talented ");
       
    }    
     @FXML
    private void close(MouseEvent event)
    {   Stage stage = (Stage) close.getScene().getWindow();
       stage.close();
    }
    
     @FXML
    private void logout(MouseEvent event) throws IOException
    {           Parent home_page_parent = FXMLLoader.load(getClass().getResource("user.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
    @FXML
    private void Groupe(MouseEvent event) throws IOException
    {           Parent home_page_parent = FXMLLoader.load(getClass().getResource("../donation/Afficherdon.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
                    
    }
    
}
