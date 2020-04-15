/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.groupe;

import GOT.entites.demande.Demande;
import GOT.entites.groupe.Groupe;
import GOT.entites.user.User;
import GOT.services.groupe.DemandeService;
import GOT.services.groupe.GroupeService;
import GOT.services.user.UserService;
import GOT.utils.MyConnection;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MembresController implements Initializable {
  @FXML
    private VBox vbox1;
    @FXML
    private ScrollPane s1;
    @FXML 
    private JFXButton show;  
    @FXML 
    private ImageView back;    
    @FXML 
    private Label name;  
    private Connection con;
    List<String> li= new ArrayList<>();
    GroupeService gs = new GroupeService();
    DemandeService ds=new DemandeService();
    UserService us=new UserService();
    Demande demande = new Demande();
    Groupe grp=new Groupe();
     
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
            
      
}    

  

    
  public Label getName() {
        return name;
    }

    /**
     * Initializes the controller class.
     */
    public void setName(Label name) {
        this.name = name;
    }

    public void setName(String nom) {
        name.setText(name.getText()+nom);
    }
    @FXML
    private void back(MouseEvent event) throws IOException
    {        DetailController.validDialog.close();}
     @FXML
    private void show(MouseEvent event) throws IOException
    {  
        System.out.print(name.getText());
        int id_groupe= gs.FindGroupeByNom(name.getText());
        
        System.out.print(id_groupe);
             List<User> list = new ArrayList<User>();

      list = gs.afficherMem(id_groupe);

        list.forEach((u) -> {
            File file = new File("C:\\Users\\HP\\Downloads\\UTALENT\\src\\resources\\user.png");
            Image image = new Image(file.toURI().toString());

            ImageView photo = new ImageView();
            photo.setImage(image);
            photo.setFitHeight(40);
            photo.setFitWidth(30);
            Label nom = new Label();
            nom.setText("                      "+u.getUsername());
            nom.setPrefWidth(352);
            
            nom.setFont(javafx.scene.text.Font.font("System", FontWeight.BOLD, 13));
            HBox h1 = new HBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
            VBox rv = new VBox();
            HBox hv1 = new HBox();
           
            JFXButton bt1 = new JFXButton("Retirer");
            final Separator sep = new Separator();
            //   vbox1.setSpacing(5);
            //sep.setMaxWidth(Double.MAX_EXPONENT);
            bt1.setStyle("-fx-background-color:  #F15C5C; -fx-text-fill: White;");
            hv1.getChildren().add(photo);
            hv1.getChildren().add(nom);
            hv1.setMaxWidth(Double.MAX_EXPONENT);
            btn.getChildren().add(bt1);
            hv1.getChildren().add(btn);
            
            vbox1.getChildren().add(hv1);
            vbox1.getChildren().add(v1);
            vbox1.getChildren().add(v2);
            vbox1.getChildren().add(h1);
            
            btn.setAlignment(Pos.CENTER);
            h1.setSpacing(10);
            bt1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            
            gs.retirer(u.getId(),id_groupe);
            vbox1.getChildren().remove(hv1);
            vbox1.getChildren().remove(v1);
            vbox1.getChildren().remove(h1);
            vbox1.getChildren().remove(btn);
            vbox1.getChildren().remove(v2);
            Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
            alertSec.setHeaderText("Utilisateur retiré");
            alertSec.showAndWait();
            
            }
            
            });
            li.add(u.getUsername());
            System.out.print(u.getUsername());
      });

    }
    
   
}
