/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.sponsor;

import GOT.entites.sponsor.Pack;
import GOT.entites.sponsor.Sponsor;
import GOT.services.sponsor.SponsorServices;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rawia
 */
public class Sponsor_indexController implements Initializable {

    /**
     * Initializes the controller class.
     */
    List<Sponsor> listSP = new ArrayList<>();

    @FXML
    private TableView<Sponsor> tableSP;
    @FXML
    private TableColumn<Sponsor, String> nom;
    @FXML
    private TableColumn<Sponsor, String> adresse;
    @FXML
    private TableColumn<Sponsor, String> invest;
    @FXML
    private AnchorPane parent;
    @FXML
    private VBox inbox;
    @FXML
    private Button addBtn;
    @FXML
    private TableColumn<Sponsor, Integer> idsp;
    @FXML
    private ImageView log;
    @FXML
    private JFXTextField spon;
    @FXML
    private Button addBtn1;
    @FXML
    private JFXTextField addr;
    @FXML
    private JFXTextField em;
    @FXML
    private JFXTextField tele;
    @FXML
    private JFXTextField desc;
    @FXML
    private Pane detail;
    private int id;
    @FXML
    private Label notif;
    @FXML
    private Label errname;
    @FXML
    private Pane pubList;
    @FXML
    private Button okb;

    @FXML
    private TableColumn<Pack, ImageView> pubAd;
    @FXML
    private TableView<Pack> tablePub;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nom.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("namesp"));
        adresse.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("address"));
        invest.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("invest"));
        idsp.setCellValueFactory(new PropertyValueFactory<Sponsor, Integer>("idsp"));
        tableSP.setItems(getSponsors());

        tableSP.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                id = tableSP.getSelectionModel().getSelectedItem().getIdsp();

            }
        });

       
    }

    public ObservableList<ImageView> getPubs() {

        SponsorServices sps = new SponsorServices();
        List<Pack> listP = new ArrayList<Pack>();
        listP = sps.sponsor_packs(id);

        ObservableList<ImageView> im = FXCollections.observableArrayList();

        for (int i = 0; i < listP.size(); i++) {
            try {
                im.add(new ImageView(new Image(new FileInputStream(listP.get(i).getAd()))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Sponsor_indexController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return im;

    }

    public ObservableList<Sponsor> getSponsors() {

        SponsorServices sps = new SponsorServices();
        listSP = sps.read();

        ObservableList<Sponsor> sp = FXCollections.observableArrayList();

        for (int i = 0; i < listSP.size(); i++) {
            sp.add(new Sponsor(listSP.get(i).getIdsp(), listSP.get(i).getNamesp(), listSP.get(i).getAddress(), sps.sponsor_invest(listSP.get(i).getIdsp())));
        }

        return sp;

    }

    @FXML
    private void sponsorBtn(ActionEvent event) {
        Parent home_page_parent;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("sponsor_index.fxml"));
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
            home_page_parent = FXMLLoader.load(getClass().getResource("pack_index.fxml"));
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
            home_page_parent = FXMLLoader.load(getClass().getResource("mail_inbox.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addBtn(ActionEvent event) {
        Parent home_page_parent;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("sponsor_add.fxml"));
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
        spon.setDisable(true);
        addr.setDisable(true);
        desc.setDisable(true);
        tele.setDisable(true);
        em.setDisable(true);
        detail.setVisible(true);
        SponsorServices sps = new SponsorServices();
        Sponsor s = sps.show(id);
        System.out.println("" + id);

        spon.setText(s.getNamesp());
        addr.setText(s.getAddress());
        em.setText(s.getEmail());
        desc.setText(s.getDescription());
        tele.setText(s.getTel());

        Image image1;
        try {
            image1 = new Image(new FileInputStream(s.getLogo()));
            log.setImage(image1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pack_indexController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ok(ActionEvent event) {
        System.out.println("" + spon.getText());
        SponsorServices sps = new SponsorServices();
        if (sps.edit( addr.getText(), em.getText(), desc.getText(), tele.getText(), id)) {
            errname.setText("");
            detail.setVisible(false);
            notif.setText("Modifications effectuées  avec succès !");
            spon.setDisable(true);
            addr.setDisable(true);
            desc.setDisable(true);
            tele.setDisable(true);
            em.setDisable(true);
        } 

    }

    @FXML
    private void editBtn(ActionEvent event) {
        detail.setVisible(true);
        SponsorServices sps = new SponsorServices();
        Sponsor s = sps.show(id);
        System.out.println("" + id);

        spon.setText(s.getNamesp());
        addr.setText(s.getAddress());
        em.setText(s.getEmail());
        desc.setText(s.getDescription());
        tele.setText(s.getTel());
        Image image1;
        try {
            image1 = new Image(new FileInputStream(s.getLogo()));
            log.setImage(image1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pack_indexController.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        addr.setDisable(false);
        desc.setDisable(false);
        tele.setDisable(false);
        em.setDisable(false);

    }

    @FXML
    private void pubBtn(ActionEvent event) {
        pubList.setVisible(true);
        System.out.println("" + id);
         pubAd.setCellValueFactory(new PropertyValueFactory<Pack, ImageView>("img"));

        SponsorServices sps = new SponsorServices();
        List<Pack> listP = new ArrayList<Pack>();
        listP = sps.sponsor_packs(id);
        ObservableList<Pack> p = FXCollections.observableArrayList();

        System.out.println("liste de:" + listP.size());
        for (int i = 0; i < listP.size(); i++) {
            try {
                ImageView img = new ImageView(new Image(new FileInputStream(listP.get(i).getAd())));
                img.setFitHeight(300);
                img.setFitWidth(250);
                
                p.add( new Pack(img));
                        
                                
                                        
                                                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Sponsor_indexController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        tablePub.setItems(p);

    }

    @FXML
    private void okb(ActionEvent event) {
        pubList.setVisible(false);

    }

    @FXML
    private void suppBtn(ActionEvent event) {
        SponsorServices sps = new SponsorServices();
        sps.delete(id);
        notif.setText("Sponsor Supprimé!");
    }

}
