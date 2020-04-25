package GOT.gui.user;

import GOT.services.user.UserService;
import GOT.utils.SessionUser;
import com.jfoenix.controls.JFXTextField;
import com.pidev.entities.Commentaire;
import com.pidev.entities.Publication;
import com.pidev.services.CommentaireService;
import com.pidev.services.PublicationService;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class PublicationController implements Initializable {
    
    @FXML
    private VBox vbox_publication;
    @FXML
    private ScrollPane affichage_publication ;
    @FXML
    private TextField post_title;
    @FXML
    private ComboBox post_type;
    @FXML
    private TextArea post_src_text;
    @FXML
    private Button post_src_other;
    @FXML
    private Button post_add_button;
    @FXML
    private JFXTextField post_search_text;
    @FXML
    private ListView comments_view ;
    @FXML
    private TextField comments_src ;
    @FXML
    private Button comments_post_button ;
    File selectedFile;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        post_type.getItems().addAll("audio", "img", "video", "text");
        post_type.getSelectionModel().select(1);
        FileChooser fileChooser = new FileChooser();

        post_src_other.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(null);
        });
        PublicationService ps = new PublicationService() ;
        List<Publication> Publications = ps.getPublications() ;
        loadAllpublications(Publications);
    }    
    
    @FXML
    private void handleb1Action(ActionEvent event) {
        post_title.clear();
        post_src_text.clear();
        affichage_publication.setVisible(false);
        comments_view.setVisible(false);
        comments_src.setVisible(false);
        comments_post_button.setVisible(false);
        post_add_button.setOnAction(e -> {
            try {
                AddPostAction();
                Notifications notficationsbuilder = Notifications.create()
                        .title("New Post Created !")
                        .text("Press the search button to see all the posts !")
                        .hideAfter(Duration.millis(2000))
                        .position(Pos.BOTTOM_RIGHT);
                notficationsbuilder.show();
            } catch (IOException ex) {
                Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    @FXML
    private void searchPostAction(ActionEvent event) {
        PublicationService ps = new PublicationService() ;
        List<Publication> Publications = ps.searchPublications(post_search_text.getText()) ;
        vbox_publication.getChildren().clear();
        loadAllpublications(Publications);
        affichage_publication.setVisible(true);
    }
    
    @FXML
    private void AddPostAction() throws IOException {
        Publication p = new Publication() ;
        p.setTitrePublication(post_title.getText());
        p.setTypePublication(post_type.getSelectionModel().getSelectedItem().toString());
        if(post_type.getSelectionModel().getSelectedItem().toString().compareTo("text") == 0)
            p.setSrcPublication(post_src_text.getText());
        else
        {
            p.setSrcPublication(selectedFile.getName());            
            File dest = new File(Paths.get("C:/wamp64/www/testing_fos_ali/web/uploads/" + p.getTypePublication() + "/" + selectedFile.getName()).toString());
            Files.copy(selectedFile.toPath(), dest.toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        }
        p.setCatPublication("");
        p.setUserIdPublication(SessionUser.getUser().getId());
        
        PublicationService ps = new PublicationService() ;
        ps.addPublication(p);
        affichage_publication.setVisible(true);
        List<Publication> new_publication = ps.getPublications();
        vbox_publication.getChildren().clear();
        loadAllpublications(new_publication);
    }
    
    private void loadAllpublications(List<Publication> Publications)
    {
        for(Publication P: Publications)
        {
            HBox hbox_publication = new HBox();
            Label lbl = new Label("Title: " + P.getTitrePublication());
            vbox_publication.getChildren().add(lbl);
            lbl.setTranslateX( (vbox_publication.getPrefWidth() / 2) - (lbl.getWidth()) );
            
            if(P.getTypePublication().compareTo("img")==0)
            {
                ImageView imgv = new ImageView() ;
                Image img = new Image("file:/C:/wamp64/www/testing_fos_ali/web/uploads/img/" + P.getSrcPublication(), 700, 400, false, false);
                
                imgv.setTranslateX( (vbox_publication.getPrefWidth() / 2) - (img.getWidth() / 2) );
                imgv.setImage(img);
        
                vbox_publication.getChildren().add(imgv);
            }
            else if(P.getTypePublication().compareTo("text")==0)
            {
                Label lbl4 = new Label("Post: " + P.getSrcPublication());
                vbox_publication.getChildren().add(lbl4);
                lbl4.setTranslateX( (vbox_publication.getPrefWidth() / 2) - (lbl4.getWidth() / 2) );
            }
            else if(P.getTypePublication().compareTo("video")==0)
            {
                String urlmedia = "file:/C:/wamp64/www/testing_fos_ali/web/uploads/video/" + P.getSrcPublication() ;
                Media m = new Media(urlmedia);
                MediaPlayer mp = new MediaPlayer(m);
                MediaView media = new MediaView();
                media.setMediaPlayer(mp);
                vbox_publication.getChildren().add(media);
                media.setTranslateX( 50 );
                Button playbtnvid = new Button("", new ImageView(new Image("resources/icons8_play_50px.png")));
                Button pausebtnvid = new Button("", new ImageView(new Image("resources/icons8_pause_50px.png")));
                playbtnvid.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        mp.play();
                    }
                });
                
                pausebtnvid.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        mp.pause();
                    }
                });
                HBox hbox_publicationvid = new HBox();
                playbtnvid.setTranslateX( 320 );
                pausebtnvid.setTranslateX( 325 );
                
                hbox_publicationvid.getChildren().addAll(playbtnvid, pausebtnvid);
                vbox_publication.getChildren().add(hbox_publicationvid);
            }
            else if(P.getTypePublication().compareTo("audio")==0)
            {
                String urlmedia = "file:/C:/wamp64/www/testing_fos_ali/web/uploads/audio/" + P.getSrcPublication() ;
                Media m = new Media(urlmedia);
                MediaPlayer mp = new MediaPlayer(m);
                MediaView media = new MediaView();
                media.setMediaPlayer(mp);
                vbox_publication.getChildren().add(media);
                media.setTranslateX( 50 );
                Button playbtnvid = new Button("", new ImageView(new Image("resources/icons8_play_50px.png")));
                playbtnvid.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        mp.play();
                    }
                });
                Button pausebtnvid = new Button("", new ImageView(new Image("resources/icons8_pause_50px.png")));
                pausebtnvid.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        mp.pause();
                    }
                });
                HBox hbox_publicationvid = new HBox();
                playbtnvid.setTranslateX( 320 );
                pausebtnvid.setTranslateX( 325 );
                
                hbox_publicationvid.getChildren().addAll(playbtnvid, pausebtnvid);
                vbox_publication.getChildren().add(hbox_publicationvid);
            }
            
            Label lbl2 = new Label(": " + P.getViewsPublication());
            ImageView imglbl2 = new ImageView(new Image("resources/icons8_eye_50px_1.png")) ;
            lbl2.setGraphic(imglbl2);
            lbl2.setTranslateX( (vbox_publication.getPrefWidth() / 3) - (lbl2.getWidth() / 2) );
            
            Label lbl3 = new Label(": " + P.getLikesPublication());
            ImageView imglbl3 = new ImageView(new Image("resources/icons8_heart_50px.png")) ;
            lbl3.setGraphic(imglbl3);
            lbl3.setTranslateX( (vbox_publication.getPrefWidth() / 2) );
            
            hbox_publication.getChildren().addAll(lbl2, lbl3);
            HBox utils_publication = new HBox();
            PublicationService ps = new PublicationService();
            Button add_like = new Button("Like") ;
            add_like.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        ps.like(P.getId(), SessionUser.getUser().getId());
                        P.setLikesPublication(P.getLikesPublication() + 1);
                        lbl3.setText(": " + P.getLikesPublication());
                    }
                });
            Button delete_post = new Button("Delete") ;
            delete_post.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        ps.removePublication(P.getId());
                        if(P.getTypePublication().compareTo("video")==0)
                            vbox_publication.getChildren().remove(Publications.indexOf(P), Publications.indexOf(P)+6) ;
                        else
                            vbox_publication.getChildren().remove(Publications.indexOf(P), Publications.indexOf(P)+5) ;
                    }
                });
            Button update_post = new Button("Update") ;
            update_post.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        comments_view.setVisible(false);
                        comments_src.setVisible(false);
                        comments_post_button.setVisible(false);
                        affichage_publication.setVisible(false);
                        post_title.setText(P.getTitrePublication());
                        post_type.getSelectionModel().select(post_type.getItems().indexOf(P.getTypePublication())) ;
                        post_src_text.setText(P.getSrcPublication());
                        post_add_button.setOnAction(e2 -> {
                            P.setTitrePublication(post_title.getText());
                            P.setTypePublication(post_type.getSelectionModel().getSelectedItem().toString());
                            if(post_type.getSelectionModel().getSelectedItem().toString().compareTo("text") == 0)
                                P.setSrcPublication(post_src_text.getText());
                            else
                            {
                                P.setSrcPublication(selectedFile.getName());            
                                File dest = new File(Paths.get("C:/wamp64/www/testing_fos_ali/web/uploads/" + P.getTypePublication() + "/" + selectedFile.getName()).toString());
                                try {
                                    Files.copy(selectedFile.toPath(), dest.toPath(),
                                            StandardCopyOption.REPLACE_EXISTING);
                                } catch (IOException ex) {
                                    Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            P.setCatPublication("");
                            P.setUserIdPublication(SessionUser.getUser().getId());
                            ps.updatePublication(P.getId(), P);
        
                            List<Publication> new_publication = ps.getPublications();
                            vbox_publication.getChildren().clear();
                            loadAllpublications(new_publication);
                            affichage_publication.setVisible(true);
                        });
                    }
                });
            Button new_comment = new Button("Comment") ;
            new_comment.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        comments_view.setVisible(true);
                        comments_src.setVisible(true);
                        comments_post_button.setVisible(true);
                        affichage_publication.setVisible(false);
                        CommentaireService cs = new CommentaireService();
                        List<Commentaire> lcs = cs.getCommentaires(P.getId()) ;
                        comments_view.getItems().clear();
                        for(Commentaire c : lcs)
                            comments_view.getItems().add(c.getCommentaire()) ;
        
                        comments_post_button.setOnAction(e2 -> {
                            Commentaire C = new Commentaire(comments_src.getText(), P.getId(), SessionUser.getUser().getId()) ;
                            cs.addCommentaire(C);
                            comments_view.getItems().add(C.getCommentaire()) ;
                            comments_src.clear();
                        });
                    }
            });
            add_like.setTranslateX( 220 );
            delete_post.setTranslateX( 250 );
            update_post.setTranslateX( 280 );
            new_comment.setTranslateX( 310 );
            if(P.getUserIdPublication()==SessionUser.getUser().getId())
                utils_publication.getChildren().addAll(add_like, delete_post, update_post, new_comment);
            else    
                utils_publication.getChildren().addAll(add_like, new_comment);
            vbox_publication.getChildren().addAll(hbox_publication, utils_publication);
            vbox_publication.getChildren().add(new SplitPane());
        }
    }
}