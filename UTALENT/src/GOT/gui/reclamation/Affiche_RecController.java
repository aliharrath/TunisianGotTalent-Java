/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.reclamation;

import GOT.entites.reclamation.Reclamation;
import GOT.services.reclamation.ReclamationService;
import GOT.utils.SessionUser;
import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class Affiche_RecController implements Initializable {

    ReclamationService rs = new ReclamationService();  
    private Statement ste;
    private Connection con;
    
    @FXML
    private Button update;
    @FXML
    private Button supprimer;
    @FXML
    private TableColumn<Reclamation,String> colType;
    @FXML
    private TableColumn<Reclamation, Date> colDate;
    @FXML
    private TableColumn<String,String> colStatus;
    ObservableList<Reclamation> cls = FXCollections.observableArrayList();
    @FXML
    private TableView<Reclamation> tabrec;
    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> coluser;
    @FXML
    private Label nbrreclamation;
    @FXML
    private Label id;
    @FXML
    private AnchorPane blr;
    @FXML
    private StackPane trans;
    @FXML
    private Group groups;
    @FXML
    private AnchorPane loadPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setText(SessionUser.getUser().getId().toString());
        id.setVisible(false);
        try {
            Affichage();
        } catch (SQLException ex) {
            Logger.getLogger(Affiche_RecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    @FXML
    private void update(ActionEvent event) throws IOException, SQLException {
        tabrec.setItems(cls);
            ObservableList<Reclamation> tsReclamation,rec ;
            tsReclamation=tabrec.getItems();
            rec=tabrec.getSelectionModel().getSelectedItems();
            Reclamation r = rec.get(0);
            if(r.isStatus())
            {
                consultRec(r.getId(),false);
            }
            else
            {
                consultRec(r.getId(),true);
            }
            
    }


    
    public void Affichage() throws SQLException{
        try {
        ReclamationService rs = new ReclamationService();
        List<Reclamation> liste = rs.AfficherReclamation(Integer.parseInt(id.getText()));
        for (Reclamation aux : liste)
        {
          cls.add(new Reclamation(aux.getId(), aux.getUser_id(), aux.getDate_creation(),aux.isStatus(), aux.getType()));  
          tabrec.setItems(cls);

        }
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date_creation"));
        //if(status)
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coluser.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        
        tabrec.setItems(cls);
        }
        catch (SQLException ex) {
                    System.out.println(ex);
                 }
    }

    @FXML
    private void supprimer(MouseEvent event) throws SQLException {
        tabrec.setItems(cls);
        ObservableList<Reclamation> tsReclamation,rec ;
        tsReclamation=tabrec.getItems();
        rec=tabrec.getSelectionModel().getSelectedItems();
        Reclamation r = rec.get(0);
        ReclamationService rs = new ReclamationService(); // STD = Service TAB DEMANDE
        if(rs.deleteReclamation(r.getId()))
        {
            cls.clear();
            Affichage();
        }

    }

    @FXML
    private void consult(MouseEvent event) throws IOException, SQLException {
        if (event.getClickCount() == 2)
        {
            tabrec.setItems(cls);
            ObservableList<Reclamation> tsReclamation,rec ;
            tsReclamation=tabrec.getItems();
            rec=tabrec.getSelectionModel().getSelectedItems();
            Reclamation r = rec.get(0);
            consultRec(r.getId(),false);
        }
    }
    
    public void consultRec(int id,boolean modif) throws IOException, SQLException
    {   
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("Details.fxml"));
        blr.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = Loader.load();
        DetailsController c = Loader.getController();
        
        ReclamationService rs = new ReclamationService();
        Reclamation r = new Reclamation();
        
        r=rs.getReclamationById(id);
        
        if(modif)
        {
            c.setData(r,1);
        }
        else
        {
            c.setData(r,0);
        }
        
        loadPane.getChildren().setAll(pane);

    }

    @FXML
    private void tombolClose(ActionEvent event) throws SQLException {
        blr.setEffect(null);
        new FadeOutLeftTransition(trans).play();
        cls.clear();
        Affichage();
    }
    
}
