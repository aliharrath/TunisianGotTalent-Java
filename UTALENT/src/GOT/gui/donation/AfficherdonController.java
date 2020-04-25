/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.donation;

import GOT.entites.user.User;
import GOT.entites.donation.donation;
import GOT.entites.donation.facture;
import GOT.gui.donation.EditdonController;
import GOT.services.donation.donationService;
import GOT.services.donation.factureService;
import GOT.services.user.UserService;
import GOT.utils.Notification;
import GOT.utils.SessionUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class AfficherdonController implements Initializable {

    @FXML
    private ScrollPane s1;
    @FXML
    private VBox vbox1;
      List<String> dons= new ArrayList<>();
    donationService ds = new donationService();
    donation donations = new donation();
    facture facture = new facture();
    factureService fs = new factureService();
    public List<donation>lst=null;
      
    UserService us=new UserService();
    User u = new User();
    @FXML
    private JFXButton ajout;
    @FXML
    private JFXComboBox<String> cat;
    ObservableList<String> cat1 = FXCollections.observableArrayList("musique", "dessin", "developpement");
    @FXML
    private JFXTextField valeurd;
    @FXML
    private ImageView usr_d;

  

    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    
    
        // TODO
          List<donation> ld = new ArrayList<>();
      valeurd.setText(String.valueOf(SessionUser.getUser().getNb_diamants()));
      valeurd.setFont(Font.font(15));
      
       File d = new File("C:\\Users\\HP\\Downloads\\UTALENT\\src\\resources\\dia.png");
            Image dm = new Image(d.toURI().toString());
            usr_d.setImage(dm);
            usr_d.setFitHeight(80);
            usr_d.setFitWidth(40);
            
        try {
            ld = ds.affciher();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherdonController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         for (donation donations : ld) {
             if (donations.getHidden()==0)
             {
         
               Label libellé = new Label();
               Label desc = new Label();
              
               Label valeur = new Label();
               Text Des = new Text("Description: ");
               Des.setFill(Color.DARKCYAN);
               Des.setFont(Font.font(15));
                  //User userTest = us.findById(SessionUser.getUser().getId());
                       //System.out.println(SessionUser.getUser().getId());
                valeur.setText(String.valueOf(donations.getValeur_d()));
               
               valeur.setFont(Font.font(20));
              
            File file = new File("C:\\Users\\HP\\Downloads\\UTALENT\\src\\resources\\"+donations.getPhoto());
            Image image = new Image(file.toURI().toString());
          File diamant = new File("C:\\Users\\HP\\Downloads\\UTALENT\\src\\resources\\dia.png");
            Image dia = new Image(diamant.toURI().toString());
             ImageView diapng = new ImageView();
            diapng.setImage(dia);
            diapng.setFitHeight(40);
            diapng.setFitWidth(20);
            ImageView photo = new ImageView();
            photo.setImage(image);
            photo.setFitHeight(300);
            photo.setFitWidth(800);
            
           
           libellé.setText(donations.getLib_donation());
              //libellé.setText(SessionUser.getUser().getUsername());
           //   System.out.println(SessionUser.getUser().getUsername());
            libellé.setFont(Font.font(30));
            libellé.setWrapText(true);
           desc.setText(donations.getDescription());
           desc.setFont(Font.font(15));
           desc.setWrapText(true);
           if(SessionUser.getUser().getTypec().equals("talent"))
           {
               ajout.setDisable(true);
           }
                // System.out.println(donations.getUser_id1());
         //  valeur.set(donations.getValeur_d());
           HBox h0 = new HBox();
            HBox h1 = new HBox();
            HBox h2 = new HBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
            VBox rv = new VBox();
            HBox hv1 = new HBox();
            JFXButton bt = new JFXButton("Récuperer ! ");
            Button bt2 = new Button("modifier"); 
            Button bt3 = new Button("Supprimer");
            bt3.setTextOverrun(OverrunStyle.CLIP);
            bt3.setStyle("-fx-background-color : #FA7676;");
            bt2.setTextOverrun(OverrunStyle.CLIP);
            bt2.setStyle("-fx-background-color : #FA7676;");
            bt.setTextOverrun(OverrunStyle.CLIP);
            bt.setStyle("-fx-background-color : #FA7676;");
            final Separator sep = new Separator();
            vbox1.setSpacing(20);
            btn.setSpacing(2);
             vbox1.setStyle("-fx-background-color : #FFFFFF;");
          // vbox1.setStyle("-fx-background-color: green; -fx-text-fill: red;");
            
            sep.setMaxWidth(Double.MAX_EXPONENT);
         //   sep.setStyle("-fx-background-color: green; -fx-text-fill: red;");
            
              hv1.getChildren().add(libellé);
              h0.getChildren().add(diapng);
               h0.getChildren().add(valeur);
              
                h1.getChildren().add(photo);
                h2.getChildren().add(Des);
                h2.getChildren().add(desc);
              
                 if(SessionUser.getUser().getTypec().equals("talent")){
                     if (SessionUser.getUser().getNb_diamants()>=donations.getValeur_d())
                     {
                        
                btn.getChildren().add(bt);
                
                     }
              }
              else {
                     if (SessionUser.getUser().getId()==donations.getUser_id1())
                     {
                    btn.getChildren().add(bt2);
                btn.getChildren().add(bt3);}
              }
            
                vbox1.getChildren().add(hv1);
                vbox1.getChildren().add(v1);
                vbox1.getChildren().add(v2);
                vbox1.getChildren().add(h0);
                vbox1.getChildren().add(h1);
                vbox1.getChildren().add(h2);
                vbox1.getChildren().add(btn);
                vbox1.getChildren().add(sep);
                btn.setAlignment(Pos.CENTER);
                hv1.setAlignment(Pos.CENTER);
            h1.setSpacing(10);
            bt3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                       
                        Notification.showNotif("Suppression d'un don", "etes vous sure de  vouloir supprimer ce don☺  ");
                        Optional<ButtonType> res = alert.showAndWait();

                        if (res.get() == ButtonType.OK) {

                            try {
                                ds.delete(donations.getId());
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherdonController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            vbox1.getChildren().remove(hv1);
                            vbox1.getChildren().remove(v1);
                            vbox1.getChildren().remove(h1);
                            vbox1.getChildren().remove(btn);
                            vbox1.getChildren().remove(v2);
                            vbox1.getChildren().remove(sep);
                            
                            
                            Notification.showNotif("Suppression d'un don", "donation suprrimée avec succées  ");
                           

                        }

                    }

                });
            bt2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            FXMLLoader root = new FXMLLoader(getClass().getResource("Editdon.fxml"));
                            AnchorPane x = root.load();
                            EditdonController Edgroup = root.getController();
                            vbox1.getChildren().setAll(x);
                            Edgroup.setDon(donations);
                            Edgroup.show();

                        } catch (IOException ex) {
                            Logger.getLogger(EditdonController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            facture = new facture (SessionUser.getUser().getId(),donations);
                            fs.ajouter(facture);
                            FXMLLoader root = new FXMLLoader(getClass().getResource("Facture.fxml"));
                            AnchorPane x = root.load();
                            FactureController fc_c = root.getController();
                            vbox1.getChildren().setAll(x);
                            fc_c.setfacture(facture);
                            fc_c.setDon(donations.getId());
                            fc_c.setName(donations.getLib_donation());
                            donations.setHidden(0);
                        } catch (SQLException ex) {
                            Logger.getLogger(AfficherdonController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(AfficherdonController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        
                });
            
         }}
         //////////////////filtre heyyyy ////////////////////////
         ////////////////////////////////////////////////////
         //////////////////////////////////////////////
         //////////////////////////////////////////////
         /////////////////////////////////////////////
         
          cat.setPromptText("Choisir une catégorie");
        cat.getItems().clear();
        cat.setItems(cat1);
        cat.setOnAction((ActionEvent e) -> {
            vbox1.getChildren().clear();

            List<donation> ls = new ArrayList<>();
            ls = ds.getdonationbyCat(this.cat.getValue());
            for (donation donations : ls) {
                if (this.donations.getHidden()==0)
                {
                
                 System.out.println("   aaaaaaaaaaaaaff");
                 Label libellé = new Label();
               Label desc = new Label();
              
               Label valeur = new Label();
               Text Des = new Text("Description: ");
               Des.setFill(Color.DARKCYAN);
               Des.setFont(Font.font(15));
                  //User userTest = us.findById(SessionUser.getUser().getId());
                       //System.out.println(SessionUser.getUser().getId());
                valeur.setText(String.valueOf(donations.getValeur_d()));
               
               valeur.setFont(Font.font(20));
              
            File file = new File("C:\\Users\\HP\\Downloads\\UTALENT\\src\\resources\\"+donations.getPhoto());
            Image image = new Image(file.toURI().toString());
          File diamant = new File("C:\\Users\\HP\\Downloads\\UTALENT\\src\\resources\\dia.png");
            Image dia = new Image(diamant.toURI().toString());
             ImageView diapng = new ImageView();
            diapng.setImage(dia);
            diapng.setFitHeight(40);
            diapng.setFitWidth(20);
            ImageView photo = new ImageView();
            photo.setImage(image);
            photo.setFitHeight(300);
            photo.setFitWidth(800);
            
           
           libellé.setText(donations.getLib_donation());
              //libellé.setText(SessionUser.getUser().getUsername());
           //   System.out.println(SessionUser.getUser().getUsername());
            libellé.setFont(Font.font(30));
            libellé.setWrapText(true);
           desc.setText(donations.getDescription());
           desc.setFont(Font.font(15));
           desc.setWrapText(true);
         //  valeur.set(donations.getValeur_d());
           HBox h0 = new HBox();
            HBox h1 = new HBox();
            HBox h2 = new HBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
            VBox rv = new VBox();
            HBox hv1 = new HBox();
            
            JFXButton bt = new JFXButton("Récuperer ! ");
            Button bt2 = new Button("modifier"); 
            Button bt3 = new Button("Supprimer");
            bt3.setTextOverrun(OverrunStyle.CLIP);
            bt3.setStyle("-fx-background-color : #FA7676;");
            bt2.setTextOverrun(OverrunStyle.CLIP);
            bt2.setStyle("-fx-background-color : #FA7676;");
            bt.setTextOverrun(OverrunStyle.CLIP);
            bt.setStyle("-fx-background-color : #FA7676;");
            final Separator sep = new Separator();
            vbox1.setSpacing(20);
            btn.setSpacing(2);
             vbox1.setStyle("-fx-background-color : #FFFFFF;");
          // vbox1.setStyle("-fx-background-color: green; -fx-text-fill: red;");
            
            sep.setMaxWidth(Double.MAX_EXPONENT);
         //   sep.setStyle("-fx-background-color: green; -fx-text-fill: red;");
            
              hv1.getChildren().add(libellé);
              h0.getChildren().add(diapng);
               h0.getChildren().add(valeur);
              
                h1.getChildren().add(photo);
                h2.getChildren().add(Des);
                h2.getChildren().add(desc);
              if(SessionUser.getUser().getTypec().equals("talent")){
                btn.getChildren().add(bt);
               
              }
              else if (SessionUser.getUser().getId()==donations.getUser_id1()) {
                  
                    btn.getChildren().add(bt2);
                btn.getChildren().add(bt3);
              }
            
                vbox1.getChildren().add(hv1);
                vbox1.getChildren().add(v1);
                vbox1.getChildren().add(v2);
                vbox1.getChildren().add(h0);
                vbox1.getChildren().add(h1);
                vbox1.getChildren().add(h2);
                
                   
                vbox1.getChildren().add(btn);
                vbox1.getChildren().add(sep);
                btn.setAlignment(Pos.CENTER);
                hv1.setAlignment(Pos.CENTER);
            h1.setSpacing(10);
            bt3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                        alert.setTitle("Suppression d'un don");
                        alert.setHeaderText("etes vous sure de  vouloir supprimer ce don☺ ");
                        Optional<ButtonType> res = alert.showAndWait();

                        if (res.get() == ButtonType.OK) {

                            try {
                                ds.delete(donations.getId());
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherdonController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            vbox1.getChildren().remove(hv1);
                            vbox1.getChildren().remove(v1);
                            vbox1.getChildren().remove(h1);
                            vbox1.getChildren().remove(btn);
                            vbox1.getChildren().remove(v2);
                            vbox1.getChildren().remove(sep);
                            Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                            alertSec.setHeaderText("donation suprrimée avec succées");
                            alertSec.showAndWait();

                        }

                    }

                });
            bt2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            FXMLLoader root = new FXMLLoader(getClass().getResource("Editdon.fxml"));
                            AnchorPane x = root.load();
                            EditdonController Edgroup = root.getController();
                            vbox1.getChildren().setAll(x);
                            Edgroup.setDon(donations);
                            Edgroup.show();

                        } catch (IOException ex) {
                            Logger.getLogger(EditdonController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            fs.ajouter(facture);
                        } catch (SQLException ex) {
                            Logger.getLogger(AfficherdonController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        
                });
            
         
        }}
         
         
            });
   
   
   /* @FXML
    private void affichercat (ActionEvent event)  {
        cat.setPromptText("Choisir un type");
        cat.getItems().clear();
        cat.setItems(cat1);
        cat.setOnAction((ActionEvent e) -> {
            vbox1.getChildren().clear();

            List<donation> ls = new ArrayList<>();
            ls = ds.getdonationbyCat(this.cat.getValue());
            for (donation donations : ls) {
                 System.out.println("   aaaaaaaaaaaaaff");
                 Label libellé = new Label();
               Label desc = new Label();
              
               Label valeur = new Label();
               Text Des = new Text("Description: ");
               Des.setFill(Color.DARKCYAN);
               Des.setFont(Font.font(15));
                  //User userTest = us.findById(SessionUser.getUser().getId());
                       //System.out.println(SessionUser.getUser().getId());
                valeur.setText(String.valueOf(donations.getValeur_d()));
               
               valeur.setFont(Font.font(20));
              
            File file = new File("C:\\Users\\Ali\\Desktop\\TunisianGotTalent-Java-master\\TunisianGotTalent-Java-master\\UTALENT\\src\\resources\\"+donations.getPhoto());
            Image image = new Image(file.toURI().toString());
          File diamant = new File("C:\\Users\\Ali\\Desktop\\TunisianGotTalent-Java-master\\TunisianGotTalent-Java-master\\UTALENT\\src\\resources\\dia.png");
            Image dia = new Image(diamant.toURI().toString());
             ImageView diapng = new ImageView();
            diapng.setImage(dia);
            diapng.setFitHeight(40);
            diapng.setFitWidth(20);
            ImageView photo = new ImageView();
            photo.setImage(image);
            photo.setFitHeight(300);
            photo.setFitWidth(800);
            
           
           libellé.setText(donations.getLib_donation());
              //libellé.setText(SessionUser.getUser().getUsername());
           //   System.out.println(SessionUser.getUser().getUsername());
            libellé.setFont(Font.font(30));
            libellé.setWrapText(true);
           desc.setText(donations.getDescription());
           desc.setFont(Font.font(15));
           desc.setWrapText(true);
         //  valeur.set(donations.getValeur_d());
           HBox h0 = new HBox();
            HBox h1 = new HBox();
            HBox h2 = new HBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
            VBox rv = new VBox();
            HBox hv1 = new HBox();
            JFXButton bt = new JFXButton("Récuperer ! ");
            Button bt2 = new Button("modifier"); 
            Button bt3 = new Button("Supprimer");
            bt3.setTextOverrun(OverrunStyle.CLIP);
            bt3.setStyle("-fx-background-color : #FA7676;");
            bt2.setTextOverrun(OverrunStyle.CLIP);
            bt2.setStyle("-fx-background-color : #FA7676;");
            bt.setTextOverrun(OverrunStyle.CLIP);
            bt.setStyle("-fx-background-color : #FA7676;");
            final Separator sep = new Separator();
            vbox1.setSpacing(20);
            btn.setSpacing(2);
             vbox1.setStyle("-fx-background-color : #FFFFFF;");
          // vbox1.setStyle("-fx-background-color: green; -fx-text-fill: red;");
            
            sep.setMaxWidth(Double.MAX_EXPONENT);
         //   sep.setStyle("-fx-background-color: green; -fx-text-fill: red;");
            
              hv1.getChildren().add(libellé);
              h0.getChildren().add(diapng);
               h0.getChildren().add(valeur);
              
                h1.getChildren().add(photo);
                h2.getChildren().add(Des);
                h2.getChildren().add(desc);
              
                btn.getChildren().add(bt);
                btn.getChildren().add(bt2);
                btn.getChildren().add(bt3);
            
                vbox1.getChildren().add(hv1);
                vbox1.getChildren().add(v1);
                vbox1.getChildren().add(v2);
                vbox1.getChildren().add(h0);
                vbox1.getChildren().add(h1);
                vbox1.getChildren().add(h2);
                vbox1.getChildren().add(btn);
                vbox1.getChildren().add(sep);
                btn.setAlignment(Pos.CENTER);
                hv1.setAlignment(Pos.CENTER);
            h1.setSpacing(10);
            bt3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                        alert.setTitle("Suppression d'un don");
                        alert.setHeaderText("etes vous sure de  vouloir supprimer ce don☺ ");
                        Optional<ButtonType> res = alert.showAndWait();

                        if (res.get() == ButtonType.OK) {

                            try {
                                ds.delete(donations.getId());
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherdonController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            vbox1.getChildren().remove(hv1);
                            vbox1.getChildren().remove(v1);
                            vbox1.getChildren().remove(h1);
                            vbox1.getChildren().remove(btn);
                            vbox1.getChildren().remove(v2);
                            vbox1.getChildren().remove(sep);
                            Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                            alertSec.setHeaderText("donation suprrimée avec succées");
                            alertSec.showAndWait();

                        }

                    }

                });
            bt2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            FXMLLoader root = new FXMLLoader(getClass().getResource("Editdon.fxml"));
                            AnchorPane x = root.load();
                            EditdonController Edgroup = root.getController();
                            vbox1.getChildren().setAll(x);
                            Edgroup.setDon(donations);
                            Edgroup.show();

                        } catch (IOException ex) {
                            Logger.getLogger(EditdonController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            fs.ajouter(factures);
                        } catch (SQLException ex) {
                            Logger.getLogger(AfficherdonController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        
                });
            
         
        }
        });
        */
    
    }
   

@FXML
 private void gointo (ActionEvent event) throws IOException {
       
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Ajoutdon.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
              
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
}



    


    

