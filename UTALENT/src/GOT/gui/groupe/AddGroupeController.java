/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.groupe;

import GOT.entites.groupe.Groupe;
import GOT.entites.user.User;
import GOT.services.groupe.GroupeService;
import GOT.utils.SessionUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddGroupeController implements Initializable{

 
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private JFXRadioButton musique;
    @FXML
    private JFXRadioButton autre;
    private String newPhoto;

    @FXML
    private JFXButton add;
  
     int width = 420, height = 524;
    int ulx = 100, uly = 100, w = 80, h = 80;
    int offset;
    Rectangle rectInside;
    PixelWriter pixelWriter;
    byte[] buffer;
     private  FileChooser Fc= new FileChooser();
    private File file;
     File selectedFile ;
    @FXML
    private JFXButton filechooser;
    @FXML
    private ImageView img;
    @FXML
    private ImageView back;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        GroupeService gs = new GroupeService();
    

    }

    @FXML
    public void GroupAdd(ActionEvent e) throws IOException {
        GroupeService gs = new GroupeService();

        Groupe group=new Groupe();
 Timestamp date_crea= new Timestamp(System.currentTimeMillis());
            // Groupe group = new Groupe(u,, String description, String photo,categorie);
            //  group.setPhoto(selectedFile.getName());
            System.out.println(SessionUser.getUser().getId());
            group.setUser_id(SessionUser.getUser().getId());
            if(musique.isSelected()){
            group.setCategorie(musique.getText());}
            else group.setCategorie(autre.getText());
            group.setNom(nom.getText());
            group.setDescription(description.getText());
            group.setDate_creation(date_crea);
            gs.create(group);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText("Groupe Added");
            alert.setContentText("Groupe successfully added");
            alert.showAndWait();
                
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("MyGroupe.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage =new Stage();
                app_stage.setScene(home_page_scene);
                app_stage.show();

      
    }

    @FXML
    private void back(MouseEvent event) throws IOException
    {           Parent home_page_parent = FXMLLoader.load(getClass().getResource("MyGroupe.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
    @FXML
    void selectPhoto(ActionEvent event) throws Exception {
    
        File dest=new File("C:\\Users\\HP\\Downloads\\UTALENT\\src\\resources");
        Fc.setTitle("Open Resource File");
        Fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
             Fc.setInitialDirectory(new File("C:\\Users\\HP\\Downloads\\UTALENT\\src\\resources"));
             selectedFile = Fc.showOpenDialog(null);
          
          selectedFile = Fc.showOpenDialog(null);
          FileUtils.copyFileToDirectory(selectedFile, dest);
          
           try {
               Image imge = new Image(selectedFile.toURI().toURL().toString());
               System.out.println(selectedFile.toURI().toURL().toString());
                this.img.setImage(imge);
           } catch (MalformedURLException ex) {
           }

        
    }

    


    
    
}
