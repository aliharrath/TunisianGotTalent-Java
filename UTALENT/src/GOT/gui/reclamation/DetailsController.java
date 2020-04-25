/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.reclamation;

import GOT.entites.reclamation.Reclamation;
import GOT.services.reclamation.ReclamationService;
import GOT.utils.MyConnection;
import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class DetailsController implements Initializable {

    @FXML
    private Label probleme;
    @FXML
    private Label id;
    @FXML
    private Label status;
    @FXML
    private Label desc;
    @FXML
    private Label reponse;
    @FXML
    private TextField d;
    @FXML
    private ComboBox<String> t;
    @FXML
    private Button update;
    @FXML
    private Label modif;
    @FXML
    private Label rp;

    
    private Connection con;
    private Statement ste;
    @FXML
    private StackPane trans;
    @FXML
    private Group groups;
    @FXML
    private AnchorPane loadPane;
    private AnchorPane blr;
    @FXML
    private AnchorPane blrr;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        reponse.setVisible(false);
        rp.setVisible(false);
        modif.setVisible(false);
        t.setItems(FXCollections.observableArrayList(getData()));
        
    } 
    
    
    public void setData(Reclamation r,int modif) throws IOException{
        this.id.setText(String.valueOf(r.getId()));
        reponse.setText(r.getReponse());
        this.modif.setText(String.valueOf(modif));
        probleme.setText(r.getType());
        status.setText("traité");
        desc.setText(r.getDescription());
        id.setVisible(false);
        this.modif.setVisible(false);
        if(modif==1)
        {
            probleme.setVisible(false);
            status.setText("En Cours");
            desc.setVisible(false);
            reponse.setVisible(false);
            t.setVisible(true);
            d.setVisible(true);
            update.setVisible(true);
            t.setValue(r.getType());
            d.setText(r.getDescription());
        }
        else
        {
            probleme.setText(r.getType());
            status.setText("En Cours");
            
            desc.setText(r.getDescription());
            if(r.isStatus())
            {
                reponse.setVisible(true);
                status.setText("traité");
                reponse.setText(r.getReponse());
                rp.setVisible(true);
                //todo
            }
            
            t.setVisible(false);
            d.setVisible(false);
            update.setVisible(false);
        }
        if(r.isStatus() && !r.isAvis())
        {
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("rating.fxml"));
            
            new FadeInRightTransition(trans).play();
            AnchorPane pane = Loader.load();
            RatingController c = Loader.getController();
            c.setData(r.getId());
            loadPane.getChildren().setAll(pane);
        }

    }
    
    @FXML
    private void update(ActionEvent event) throws SQLException {
        Reclamation r = new Reclamation();
        ReclamationService rs = new ReclamationService();
        rs.updateReclamation(new Reclamation(Integer.parseInt(id.getText()),d.getText(),t.getValue()));
        JOptionPane.showMessageDialog(null, "Réclamation modifié!");
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

    @FXML
    private void tombolClose(ActionEvent event) {
        
        new FadeOutLeftTransition(trans).play();
    }
    
}
