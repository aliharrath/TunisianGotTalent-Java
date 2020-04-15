/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.user;

import static GOT.gui.groupe.MyGroupeController.validDialog;
import GOT.utils.SessionUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FrontFXMLController implements Initializable {
 
    
    @FXML 
    private Pane PU;
    @FXML // Father of all components
    private StackPane root;

    private VBox validPane, invalidPane;

    public static JFXDialog validDialog, invalidDialog;
    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView Profile;
    @FXML
    private ImageView logout;
    @FXML
    private ImageView close;
    @FXML
    private Label role;
    @FXML
    private Label name;
    @FXML
    private Button groupe;
    @FXML
    private Button evenement;
    @FXML
    private Button invisible;
    @FXML
    private JFXButton hi;
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private Label diamant;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       name.setText(SessionUser.getUser().getUsername());
       //if(SessionUser.getUser().getRoles().equals("talent"))
       role.setText(SessionUser.getUser().getTypec());
       username.setText(username.getText()+"  "+SessionUser.getUser().getUsername());
       email.setText(email.getText()+"  "+SessionUser.getUser().getEmail());
       diamant.setText(diamant.getText()+"  "+SessionUser.getUser().getNb_diamants());

     //  PU.setVisible(false);
                      hi.setVisible(false);

       
    }    
     @FXML
    private void close(MouseEvent event)
    {   Stage stage = (Stage) close.getScene().getWindow();
       stage.close();
    }
    
     @FXML
    private void logout(MouseEvent event) throws IOException
    {           Parent home_page_parent = FXMLLoader.load(getClass().getResource("user.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
    @FXML
    private void Groupe(MouseEvent event) throws IOException
    {           Parent home_page_parent = FXMLLoader.load(getClass().getResource("../groupe/Menu.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
                    
    }
    @FXML
    private void Event(MouseEvent event) throws IOException
    {      
        if (SessionUser.getUser().getTypec().equals("talent"))
           {
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("../event/talentevent.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
           }
        else if (SessionUser.getUser().getTypec().equals("donneur"))
                   {
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("../event/chasseurevent.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();}
                   
    }
      @FXML
    private void profile(MouseEvent event) throws IOException
    {        
        // PU.setVisible(true);
                 hi.setVisible(true);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(PU);
        
        slide.setToX(491);
        slide.play();
        
        PU.setTranslateX(-309);
        slide.setDuration(Duration.seconds(0.7));
        hi.setVisible(true);

         
    }
      @FXML
    private void hide(MouseEvent event) throws IOException
    {      System.out.print("test");
         hi.setVisible(false);
         TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(PU);
        
        slide.setToX(0);
        slide.play();
        
        PU.setTranslateX(0);
      
    }
    
}
