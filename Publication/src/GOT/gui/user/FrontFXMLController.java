package GOT.gui.user;

import GOT.utils.SessionUser;
import com.jfoenix.controls.JFXTextField;
import com.pidev.entities.Publication;
import com.pidev.services.PublicationService;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FrontFXMLController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void redirectToPublicationAction(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Publication.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); 
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}
