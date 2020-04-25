/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.reclamation;

import GOT.entites.reclamation.Reclamation;
import GOT.services.reclamation.ReclamationService;
import GOT.utils.MyConnection;
import GOT.utils.SessionUser;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class AjoutReclamationController implements Initializable {

    @FXML
    private Button upload;
    @FXML
    private ImageView listev;
    @FXML
    private TextField desc;
    @FXML
    private Button envoyer;
    @FXML
    private Label file;
    @FXML
    private ComboBox<String> typer;
    
    
    /**
     * Initializes the controller class.
     */
    private Connection con;
    private Statement ste;
    @FXML
    private Label id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO*
        //id.setText(String.valueOf(SessionUser.getUser().getId()));
        id.setText(String.valueOf(SessionUser.getUser().getId()));
        id.setVisible(false);
        typer.setItems(FXCollections.observableArrayList(getData()));
        //System.out.println(SessionUser.getUser().getUsername());
    }    
    private List<String> getData() {
        con = MyConnection.getInstance().getCnx();
        List<String> strings = new ArrayList<>();
        try {
            ste=con.createStatement();
            String query="select * from type";
            ResultSet rs=ste.executeQuery(query);
            while(rs.next())
            {
                strings.add(rs.getString("probleme"));
            }
           return strings;
        } catch (SQLException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    private void alerte(boolean test){
        if(test)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Votre Reclamation a été bien ajouté");
            alert.setContentText("!");
            alert.showAndWait();
        }
        else{
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setHeaderText("Reclamation exist");
            alert1.setContentText("erreur !");
            alert1.showAndWait();
        }
    }
    @FXML
    private void envoyer(ActionEvent event) throws SQLException, DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException {
        String description=desc.getText();
        String typ=(String)typer.getValue();
        
        ReclamationService rs=new ReclamationService();
        Reclamation r =new Reclamation(Integer.parseInt(id.getText()),  description,"",typ);
        r.setFichier(file.getText());
        if(rs.ajouterReclamation(r) == true)
        {
            /*pdf pd=new pdf();
            pd.GeneratePdf("Reclmation",r);
            file.setText("");
            desc.setText("");*/
            alerte(true);
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("../reclamation/affiche_reclamation.fxml"));
                    Scene home_page_scene = new Scene(home_page_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.hide(); 
                    app_stage.setScene(home_page_scene);
                    app_stage.show();
            
        }
        else
        {
            alerte(false);
        }
        
    }

    
    @FXML
    private void uploadbouton(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        String path = fc.showOpenDialog(upload.getScene().getWindow()).getPath();
        file.setText(path);
    }
    
}
