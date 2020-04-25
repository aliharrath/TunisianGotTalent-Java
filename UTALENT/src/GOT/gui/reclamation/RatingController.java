/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.reclamation;

import GOT.entites.reclamation.Avis;
import GOT.services.reclamation.AvisService;
import GOT.services.reclamation.ReclamationService;
import GOT.utils.SessionUser;
import static java.lang.Math.round;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class RatingController implements Initializable {

    @FXML
    private Rating rating;
    @FXML
    private TextField desc;
    @FXML
    private Label id;
    @FXML
    private Label id_rec;
    @FXML
    private Button envoyer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
              //desc.setText("Rating : "+ t1.toString());
            }
        });
    } 
    public void setData(int id_rec)
    {
        int userid=1;
        this.id.setText(String.valueOf(SessionUser.getUser().getId()));
        this.id_rec.setText(String.valueOf(id_rec));
        id.setVisible(false);
        this.id_rec.setVisible(false);
    }

    @FXML
    private void envoyer(ActionEvent event) throws SQLException {
        int userid=SessionUser.getUser().getId();
        int id_reclamation=Integer.parseInt(id_rec.getText());
        int note;
        note=(int) round(rating.ratingProperty().getValue());
        
        String description=desc.getText();
        AvisService us=new AvisService();
        
        Avis u =new Avis(userid,id_reclamation,note,description);  
        if(us.ajouterAvis(u))
        {
            ReclamationService rs = new ReclamationService();
            rs.updateAvis(id_reclamation);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Avis");
            alert.setHeaderText("Votre avis a été envoyé");
            alert.setContentText("Merci beaucoup!");
            alert.showAndWait();
            desc.setText("");
        }
        
        

    }
    
}
