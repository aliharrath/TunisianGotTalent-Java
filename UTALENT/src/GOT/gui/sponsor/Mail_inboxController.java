/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.sponsor;

import GOT.entites.sponsor.Mail;
import GOT.services.sponsor.MailServices;
import com.jfoenix.controls.JFXScrollPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.stream.FileImageInputStream;

/**
 * FXML Controller class
 *
 * @author Rawia
 */
public class Mail_inboxController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private VBox inbox;
    @FXML
    private Pane MailContainer;
    
    private ScrollPane sp;
    @FXML
    private FlowPane Container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MailServices ms = new MailServices();
        List<Mail> listM = new ArrayList<Mail>();
        listM = ms.inbox();
        VBox vb = new VBox();
        sp = new ScrollPane();
        for (int i = 0; i < listM.size(); i++) {
            HBox hb = new HBox();
            
            Label  mailToLabel = new Label();
            Label  obj = new Label(); Label text = new Label();
            Label  time = new Label();
            ImageView imV = new ImageView();
            imV.setFitHeight(50);
            imV.setFitWidth(50);
            
            try {
                Image im= new Image(new FileInputStream("C:\\Users\\HP\\Downloads\\UTALENT\\src\\resources\\user.png"));
                imV.setImage(im);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Mail_inboxController.class.getName()).log(Level.SEVERE, null, ex);
            }
            mailToLabel.setStyle("-fx-font-weight: bold;");
            obj.setStyle("-fx-font-weight: bold;");
            mailToLabel.setMaxWidth(300);
            obj.setMaxWidth(300);
           time.setAlignment(Pos.CENTER_RIGHT);
          //  time.setMaxWidth(50);
//            mailToLabel.onMousePressedProperty(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event){} });

            hb.setMargin(mailToLabel, new Insets(5, 5, 5, 5));
            hb.setMargin(obj, new Insets(5, 5, 5, 5));
            hb.setMargin(text, new Insets(5, 5, 5, 5));
            hb.setMargin(time, new Insets(5, 5, 5, 5));
            
            mailToLabel.setText(listM.get(i).getMailto());
           
            if(listM.get(i).getObject().length()+listM.get(i).getSubject().length()>35){
                String s =listM.get(i).getObject().substring(0, 15);
                System.out.println();
                obj.setText(listM.get(i).getSubject()+" - "+s+" ...");
                
            }
             else   {
           obj.setText(listM.get(i).getSubject()+" - "+listM.get(i).getObject());
            }
            
            
            time.setText(ms.dateConv(listM.get(i).getTime()));
 
            hb.getChildren().add(imV);
            hb.getChildren().add(mailToLabel);
            hb.getChildren().add(obj);
            hb.getChildren().add(text);
            hb.getChildren().add(time);
            
            
            vb.getChildren().add(hb);
          
        }
        Container.getChildren().add(vb);
       
       // sp.setContent(Container);
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
    private void composeBtn(ActionEvent event) {
        Parent home_page_parent;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("mail_send.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void compose(ActionEvent event) {
         Parent home_page_parent;
        try {
            home_page_parent = FXMLLoader.load(getClass().getResource("mail_send.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void compose(MouseEvent event) {
    }

}
