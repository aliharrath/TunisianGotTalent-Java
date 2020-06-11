/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.donation;

import GOT.entities.donation.donation;
import GOT.service.donation.donationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class EditdonController implements Initializable {

    @FXML
    private JFXTextField lib;
    @FXML
    private JFXTextField valeur;
    @FXML
    private JFXRadioButton music;
    @FXML
    private JFXRadioButton dessin;
    @FXML
    private JFXRadioButton dev;
    @FXML
    private JFXTextArea desc;
    @FXML
    private JFXButton upload;
    @FXML
    private JFXButton ajouter;
    @FXML
    private ImageView img;
    private  FileChooser Fc= new FileChooser();
    private File file;
     File selectedFile ;
     private donation don;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
public void setDon(donation don) {
        this.don = don;
    }
    @FXML
    private void selectPhoto(ActionEvent event) throws Exception{
         File dest=new File("C:\\Users\\Ali\\Desktop\\TunisianGotTalent-Java-master\\TunisianGotTalent-Java-master\\UTALENT\\src\\resources");
        Fc.setTitle("Open Resource File");
        Fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
             Fc.setInitialDirectory(new File("C:\\Users\\Ali\\Desktop\\TunisianGotTalent-Java-master\\TunisianGotTalent-Java-master\\UTALENT\\src\\resources"));
             
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
     @FXML
    private void validUpdate(ActionEvent event) throws Exception {
           donationService ds = new donationService();

    
        don = ds.findById(don.getId());
         
        
        
        don.setLib_donation(lib.getText());
           don.setDescription(desc.getText());
           int v = Integer.parseInt(valeur.getText());
           don.setValeur_d(v);
            don.setPhoto(selectedFile.getName());
            if(music.isSelected())
                    don.setCategorie(music.getText());
            else if (dessin.isSelected()) 
                don.setCategorie(dessin.getText());
            else don.setCategorie(dev.getText());
            ds.update(don,don.getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("don modifié");
        alert.setHeaderText("Le don choisi a été modifié");
        alert.setContentText("Votre modification a été enregistrée");
        alert.showAndWait();
        alert.close();

        AnchorPane pp = FXMLLoader.load(getClass().getResource("Afficherdon.fxml"));
        

    }
    public void show() throws MalformedURLException {
        lib.setText(don.getLib_donation());
       
       
      valeur.setText(String.valueOf(don.getValeur_d()));
        desc.setText(don.getDescription());
        

//        
    }
    @FXML
    private void gointo (ActionEvent event) throws IOException {
       
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Afficherdon.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
              
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
    
}
