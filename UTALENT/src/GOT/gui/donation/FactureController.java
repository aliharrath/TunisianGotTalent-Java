/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.donation;

import GOT.entites.donation.donation;
import GOT.entites.donation.facture;
import GOT.services.donation.donationService;
import GOT.services.donation.factureService;
import GOT.utils.PDF2;
import GOT.utils.SessionUser;
import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class FactureController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label uc;
    @FXML
    private Label date;
    private facture fc;
    private int donation_id;
    @FXML
    private Label don_f;
    @FXML
    private JFXButton pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factureService fs = new factureService();
       // donationService ds = new donationService();
        // = ds.findById(fc.getDonation().getId());
        System.out.println(donation_id);
      //String dt_cr = fc.getDate_cr().toString();
        Timestamp date_c = new Timestamp(System.currentTimeMillis());
       String user= SessionUser.getUser().getUsername();
        //int donn = Integer.parseInt(donation_id);
        //String donn1 = fc.
        //don_f.setText(donn);
        uc.setText(user);
        date.setText(date_c.toString().substring(0, 10));
         
         
         
    }    

    public void setfacture(facture fc) {
        this.fc = fc;
    }
    public void setDon(int donation_id) {
        this.donation_id = donation_id;
    }
   public void setName(String nom) {
        don_f.setText(don_f.getText()+nom);
    }
    
   
      @FXML
        public void generationpdf (ActionEvent event) throws IOException, DocumentException, SQLException {
        PDF2.main();
    }
    
}
