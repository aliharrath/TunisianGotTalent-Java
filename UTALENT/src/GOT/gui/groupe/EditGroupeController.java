/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.groupe;

import GOT.entites.groupe.Groupe;
import GOT.services.groupe.GroupeService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class EditGroupeController implements Initializable {
    private  FileChooser Fc= new FileChooser();
    private File file;
     File selectedFile ;
    @FXML
    private JFXButton filechooser;
    @FXML
    private ImageView img;

   
    
    protected StringProperty from = new SimpleStringProperty();
    
    private String newPhoto = null;

    private File selected_photo = null;
    private String oldPhoto;
    @FXML
    private JFXTextField nonevent;
   
    @FXML
    private JFXButton backtolist;

    @FXML
    private AnchorPane ass;

    /**
     * Initializes the controller class.
     */
    private Groupe grp;

    public Groupe getGrp() {
        return grp;
    }

    public void setGrp(Groupe grp) {
        this.grp = grp;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void show() {
        nonevent.setText(grp.getNom());
        File file1 = new File("C:\\Users\\HP\\Downloads\\UTALENT\\src\\resources\\"+grp.getPhoto());
        Image image = new Image(file1.toURI().toString());
        img.setImage(image);
    }
    
    @FXML
    private void validUpdate(ActionEvent event) throws Exception {
        GroupeService gs = new GroupeService();
        this.grp = gs.FindGroupe(this.grp.getId());
        System.out.println(this.grp.getId());
        //Evenement ev = new Annonce();
        this.grp.setNom(nonevent.getText());
       
        if (this.selected_photo != null) {
            this.grp.setPhoto(this.newPhoto);
        }
        System.out.println(this.grp.getPhoto());
        gs.update(this.grp);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Groupe modifié");
        alert.setHeaderText("Le groupe choisi a été modifié");
        alert.setContentText("Votre modification a été enregistrée");
        alert.showAndWait();
        alert.close();

      //  AnchorPane pp = FXMLLoader.load(getClass().getResource("ShowGroupeFXML.fxml"));
       // boolean setAll = this.ass.getChildren().setAll(pp);

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
