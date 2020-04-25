/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.sponsor;

import GOT.entites.sponsor.Pack;
import GOT.services.sponsor.PackServices;
import GOT.services.sponsor.SponsorServices;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Rawia
 */
public class Pack_indexController implements Initializable {

    /**
     * Initializes the controller class.
     */
    List<Pack> listP = new ArrayList<>();
    @FXML
    private TableView<Pack> tableP;
    @FXML
    private TableColumn<Pack, String> discarddate;
    @FXML
    private TableColumn<Pack, String> displaydate;

    @FXML
    private ChoiceBox<String> owner;
    @FXML
    private TableColumn<Pack, String> status;
    @FXML
    private AnchorPane parent;
    @FXML
    private VBox inbox;
    @FXML
    private Pane detail;
    @FXML
    private ChoiceBox<String> pos;
    @FXML
    private JFXTextField prix;
    @FXML
    private JFXTextField disp;
    @FXML
    private ImageView ad;
    @FXML
    private JFXTextField disc;
    @FXML
    private TableColumn<Pack, Integer> idad;

    private int id;
    @FXML
    private Label notif;

    ObservableList<String> adpos = FXCollections.observableArrayList("Center", "Right", "Left");
    ObservableList<String> obsp=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //   tableP.getSelectionModel().setCellSelectionEnabled(true); 

        displaydate.setCellValueFactory(new PropertyValueFactory<Pack, String>("displaydate"));
        discarddate.setCellValueFactory(new PropertyValueFactory<Pack, String>("discarddate"));
        status.setCellValueFactory(new PropertyValueFactory<Pack, String>("status"));
        idad.setCellValueFactory(new PropertyValueFactory<Pack, Integer>("idad"));

        PackServices ps = new PackServices();
        listP = ps.read();
        ps.updatestatus();
        ObservableList<Pack> p = FXCollections.observableArrayList();

        for (int i = 0; i < listP.size(); i++) {
            p.add(new Pack(listP.get(i).getIdad(), listP.get(i).getDisplaydate(), listP.get(i).getDiscarddate(), listP.get(i).getStatus()));

        }
        tableP.setItems(p);
        tableP.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                id = tableP.getSelectionModel().getSelectedItem().getIdad();

            }
        });

        SponsorServices sp = new SponsorServices();
        String[] np=new String[sp.read().size()]; 

        pos.setItems(adpos);

        for ( int i=0; i<sp.read().size();i++) {
            np[i]=(sp.read().get(i).getNamesp());
           
        }
        obsp.setAll(np);
        owner.setItems(obsp);
        
    }

    @FXML
    private void sponsorBtn(ActionEvent event) {
        Parent home_page_parent;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("/got/gui/sponsor/sponsor_index.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void packBtn(ActionEvent event) {
        Parent home_page_parent;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("/got/gui/pack/pack_index.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void inboxBtn(ActionEvent event) {
        Parent home_page_parent;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("/got/gui/mail/mail_inbox.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showBtn(ActionEvent event) {
        detail.setVisible(true);
       
        prix.setDisable(true);
        disc.setDisable(true);
        disp.setDisable(true);
        
        PackServices ps = new PackServices();
        Pack p = ps.show(id);

        pos.setValue(p.getPositin());
        prix.setText(p.getPrice());
        disc.setText(p.getDiscarddate());
        disp.setText(p.getDisplaydate());
        owner.setValue(ps.owner(p.getIdsp()));

        Image image1;
        try {
            image1 = new Image(new FileInputStream(p.getAd()));
            ad.setImage(image1);
            ad.setStyle("border-radius:15px;");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pack_indexController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ok(ActionEvent event) {
       PackServices ps = new PackServices();
        detail.setVisible(false);
        ps.edit(pos.getValue(),disp.getText(),disc.getText(), prix.getText(),ps.owner_id(owner.getValue()), id );
      notif.setText("Modifications effectuées avec succès!");
        prix.setDisable(true);
        disc.setDisable(true);
        disp.setDisable(true);
       
    }

    @FXML
    private void addBtn(ActionEvent event) {
        Parent home_page_parent;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("pack_add.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editBtn(ActionEvent event) {
        detail.setVisible(true);
        PackServices ps = new PackServices();
        Pack p = ps.show(id);

        pos.setValue(p.getPositin());
        prix.setText(p.getPrice());
        disc.setText(p.getDiscarddate());
        disp.setText(p.getDisplaydate());
        owner.setValue(ps.owner(p.getIdsp()));
        Image image1;
        try {
            image1 = new Image(new FileInputStream(p.getAd()));
            ad.setImage(image1);
            ad.setStyle("border-radius:15px;");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pack_indexController.class.getName()).log(Level.SEVERE, null, ex);
        }

        pos.setDisable(false);
        prix.setDisable(false);
        disc.setDisable(false);
        disp.setDisable(false);
        owner.setDisable(false);

    }

    @FXML
    private void suppBtn(ActionEvent event) {
        PackServices ps = new PackServices();
        ps.delete(id);
        notif.setText("Publicité supprimée.");
    }
}
