/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.groupe;

import GOT.entites.groupe.Groupe;
import GOT.services.groupe.DemandeService;
import GOT.services.groupe.GroupeService;
import GOT.utils.SessionUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class InGroupController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML
    private StackPane root;

    @FXML
    private AnchorPane a;
    @FXML
    private VBox vbox1;
    @FXML
    private ScrollPane s1;
   
 
    
  
   
   
    private VBox validPane;
    List<String> li= new ArrayList<>();
    GroupeService gs = new GroupeService();
    DemandeService ds=new DemandeService();
    Groupe groups = new Groupe();
    @FXML
    private ImageView close;
    @FXML
    private ImageView back;
    @FXML 
    private ImageView notif;
    private VBox validAlertPane;
        public static JFXDialog validDialog;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        String name="test";
        name=SessionUser.getUser().getUsername();
        System.out.println(name);
       

       List<Groupe> lg = new ArrayList<>();
        lg = gs.affichergpIN(SessionUser.getUser().getId());

        for (Groupe groups : lg) {
       
             if(groups.getUser_id()!=SessionUser.getUser().getId()){
            Label nom = new Label();
            Text description=new Text();
            Label Espace=new Label();
            Label Espace2=new Label();
            Label Espace3=new Label();
            Label cat=new Label();
            File file = new File("C:\\Users\\HP\\Downloads\\UTALENT\\src\\resources\\"+groups.getPhoto());
            Image image = new Image(file.toURI().toString());

            ImageView photo = new ImageView();
            photo.setImage(image);
            photo.setFitHeight(100);
            photo.setFitWidth(100);
            Espace.setText("    ");
            Espace2.setText("    ");
            Espace3.setText("         ");
            nom.setText("                             "+groups.getNom()+"                                     ");
            nom.setMinWidth(100);
            nom.setFont(javafx.scene.text.Font.font("System", FontWeight.BOLD, 13));
            description.setText(groups.getDescription());
            description.setFont(javafx.scene.text.Font.font("System", FontWeight.NORMAL, 13));
            description.setWrappingWidth(323.251953125);
            cat.setText(groups.getCategorie()+"                             ");
            cat.setFont(javafx.scene.text.Font.font("System", FontWeight.BOLD, 13));
            HBox h1 = new HBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
            VBox rv = new VBox();
            HBox hv1 = new HBox();
            JFXButton bt = new JFXButton("Afficher Détails");
         
            final Separator sep = new Separator();
            vbox1.setSpacing(5);
                bt.setStyle("-fx-background-color:  #aac9f4; -fx-border-width: 10px;");
             

            
            sep.setMaxWidth(Double.MAX_EXPONENT);
                hv1.getChildren().add(Espace3);
                hv1.getChildren().add(photo);
                hv1.getChildren().add(nom);
                hv1.getChildren().add(cat);
                hv1.getChildren().add(description);
                btn.getChildren().add(bt);
              
            
                vbox1.getChildren().add(hv1);
                vbox1.getChildren().add(v1);
                vbox1.getChildren().add(v2);
                vbox1.getChildren().add(h1);
                vbox1.getChildren().add(btn);
                vbox1.getChildren().add(sep);
                btn.setAlignment(Pos.CENTER);
            h1.setSpacing(10);
            
             bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                     try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("detail.fxml"));
                                    Parent root = (Parent)loader.load();
                                    DetailController controller = loader.getController();
                                    controller.initialize(url, rb, groups);
                                    controller.setNom(groups.getNom());
                                    Stage stage=new Stage();
                                    stage.setScene(new Scene(root));
                                    stage.show();
                            } catch (IOException ex) {
                            Logger.getLogger(EditGroupeController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });

            li.add(groups.getNom());
            }
       }}
    
    
    @FXML
    private void close(MouseEvent event)
    {   Stage stage = (Stage) close.getScene().getWindow();
       stage.close();
    }
    
    @FXML
    private void back(MouseEvent event) throws IOException
    {           Parent home_page_parent = FXMLLoader.load(getClass().getResource("../user/FrontFXML.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
     @FXML
    private void add(MouseEvent event) throws IOException
    {           Parent home_page_parent = FXMLLoader.load(getClass().getResource("../groupe/Menu.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              //  app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
  
}      
    
