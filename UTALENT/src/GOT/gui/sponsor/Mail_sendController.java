/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.sponsor;

import GOT.entites.sponsor.Mail;
import GOT.services.sponsor.MailServices;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Rawia
 */
public class Mail_sendController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private JFXTextField mailto;
    @FXML
    private JFXTextField subject;
    @FXML
    private JFXTextField object;
    @FXML
    private Label notif;
    @FXML
    private VBox inbox;
    @FXML
    private Pane Container;
    @FXML
    private Label file;
    @FXML
    private ImageView imgV;
    @FXML
    private Pane compose;
    @FXML
    private Pane show;
    @FXML
    private Label s_sub;
    @FXML
    private Label s_time;
    @FXML
    private Label s_mailto;
    @FXML
    private Label s_obj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MailServices ms = new MailServices();
        List<Mail> listM = new ArrayList<Mail>();
        listM = ms.inbox();

        VBox item = new VBox();
        for (int i = 0; i < listM.size(); i++) {

            VBox vb = new VBox();
            HBox hb0 = new HBox();
            HBox hb = new HBox();
            Label mailToLabel = new Label();
            Label obj = new Label();
            Label text = new Label();
            Label time = new Label();

            mailToLabel.setStyle("-fx-font-weight: bold;");
            obj.setStyle("-fx-font-weight: bold;");
            time.setTextAlignment(TextAlignment.RIGHT);
            ImageView imV = new ImageView();
            imV.setFitHeight(50);
            imV.setFitWidth(50);

            try {
                Image im = new Image(new FileInputStream("C:\\Users\\Rawia\\Documents\\avatar.png"));
                imV.setImage(im);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Mail_inboxController.class.getName()).log(Level.SEVERE, null, ex);
            }

            hb.setMargin(mailToLabel, new Insets(5, 5, 5, 5));
            hb.setMargin(obj, new Insets(5, 5, 5, 5));
            hb.setMargin(text, new Insets(5, 5, 5, 5));
            hb.setMargin(time, new Insets(5, 5, 5, 5));

            mailToLabel.setText(listM.get(i).getMailto());

            if (listM.get(i).getObject().length() > 15) {
                System.out.println(listM.get(i).getObject().substring(0, 10));
                text.setText(listM.get(i).getSubject() + ":" + listM.get(i).getObject().substring(0, 10) + "...");
            } else {
                text.setText(listM.get(i).getSubject() + ":" + listM.get(i).getObject());
            }

            time.setText(listM.get(i).getTime() + "");

            hb0.getChildren().add(mailToLabel);
            hb0.getChildren().add(time);

            hb0.getChildren().add(obj);
            hb0.getChildren().add(text);

            vb.getChildren().addAll(hb0, text);

            vb.setId(listM.get(i).getId() + "");
            hb.getChildren().addAll(imV, vb);
            item.getChildren().add(hb);
            item.setMargin(hb,new Insets(5, 0, 5, 0) );
            hb.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    hb.setStyle("-fx-background-color:#666666");
                    //hb.setStyle("-fx-text-inner-color: white;");

                }
            });
            hb.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    hb.setStyle("-fx-background-color:transparent");
                }
            });
            hb.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    
                    
                    Mail mail = new Mail();
                    mail = ms.show(Integer.parseInt(vb.getId()));
                    System.out.println("id:"+vb.getId()+mail.getSubject());
                    s_mailto.setText(mail.getMailto());
                    s_sub.setText("EMAIL: " + mail.getSubject());
                    s_time.setText(ms.dateConv(mail.getTime()));
                    s_obj.setText(mail.getObject());
                    compose.setVisible(false);
                    show.setVisible(true);

                    show.translateXProperty().set(compose.getWidth());

                    Timeline timeline = new Timeline();
                    KeyValue kv = new KeyValue(show.translateXProperty(), 0, Interpolator.EASE_IN);
                    KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
                    timeline.getKeyFrames().add(kf);
                    timeline.play();

                }
            });

            // vb.setStyle("-fx-border-color: black;");
        }
        Container.getChildren().add(item);

    }

    @FXML
    private void sendBtn(ActionEvent event) {
        MailServices ms = new MailServices();
        System.out.println("here " + file.getText());
        boolean ok;
        ok = ms.mail(mailto.getText(), subject.getText(), object.getText(), file.getText());
        if (ok) {
            notif.setText("Message Envoye!");
        } else {
            notif.setTextFill(Paint.valueOf("RED"));
            notif.setText("Erreur de Connexion!");
        }
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
    private void fileBtn(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        Image image1;
        if (selectedFile != null) {
            file.setText(selectedFile.getAbsolutePath());
            try {
                image1 = new Image(new FileInputStream(selectedFile.getAbsolutePath()));
                imgV.setImage(image1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Pack_addController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("file is not valid!");
        }
    }

    @FXML
    private void test(MouseEvent event) {

    }

    @FXML
    private void ok(ActionEvent event) {
        compose.setVisible(true);
        show.setVisible(false);
    }

}
