/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.user;


import GOT.entites.user.User;
import GOT.services.user.UserService;
import GOT.utils.SessionUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class UserController implements Initializable {

    @FXML
    private AnchorPane layersignup;
    @FXML
    private AnchorPane layer2;
    @FXML
    private JFXButton signin;
    @FXML
    private Label pwverif;
    @FXML
    private Label l1;
    @FXML
    private Label msguser;  
    @FXML
    private Label msgemail;  
    @FXML
    private Label msgchoix;
    @FXML
    private Label Exist; 
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label s1;
    @FXML
    private Label s2;
    @FXML
    private Label s3;
    @FXML
    private JFXButton signup;
    @FXML
    private Label a2;
    @FXML
    private Label b2;
    @FXML
    private JFXButton btnsignup;
    @FXML
    private JFXButton btnsignin;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField n1;
    @FXML
    private TextField n2;
    @FXML
    private Label n3;
    @FXML
    private AnchorPane layer1;
    @FXML
    private JFXRadioButton talent;
    @FXML 
    private JFXRadioButton donneur;
    @FXML
    private HBox hb;
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        b2.setVisible(false);
        btnsignin.setVisible(false);
        n1.setVisible(false);
        n2.setVisible(false);
        n3.setVisible(false);
        username.setVisible(true);
        email.setVisible(true);
        password.setVisible(true);
    }    

    @FXML
    private void btn(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);
        
        slide.setToX(491);
        slide.play();
        
        layer1.setTranslateX(-309);
        btnsignin.setVisible(true);
        b2.setVisible(true);
        hb.setVisible(false);
        s1.setVisible(true);
        s2.setVisible(true);
        s3.setVisible(true);
        signup.setVisible(true);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        signin.setVisible(false);
        a2.setVisible(false);
        btnsignup.setVisible(false);
        n1.setVisible(true);
        n2.setVisible(true);
        n3.setVisible(true);
        username.setVisible(false);
        email.setVisible(false);
        password.setVisible(false);
        
        slide.setOnFinished((e->{
        
        
        }));
    }

    @FXML
    private void btn2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);
        
        slide.setToX(0);
        slide.play();
        
        layer1.setTranslateX(0);
        btnsignin.setVisible(false);
        b2.setVisible(false);
        hb.setVisible(true);
        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        signin.setVisible(true);
        a2.setVisible(true);
        btnsignup.setVisible(true);
        n1.setVisible(false);
        n2.setVisible(false);
        n3.setVisible(false);
        username.setVisible(true);
        email.setVisible(true);
        password.setVisible(true);
        
        slide.setOnFinished((e->{
        
        
        }));
    }
    
  @FXML
    private void btnsignup(MouseEvent event)  {
            UserService fs = new UserService();
            User u = new User();
            
         /*   if (((!msgemail.getText().equals("Email valide") || !pwverif.getText().equals("Mot de passe valide")) || !msguser.getText().equals(" username valide")) ||!msgchoix.getText().equals("choix effectué"))
            { Exist.setTextFill(Paint.valueOf("RED"));
            Exist.setText("Veuillez vérifier vos données !! ");}
            else */
            {
                u.setUsername(username.getText());
                u.setUsernameCanonical(username.getText());
                u.setEmail(email.getText());
                u.setEmailCanonical(email.getText());
                u.setPassword(password.getText());
                if(talent.isSelected())
                    u.setTypec(talent.getText());
                else if (donneur.isSelected()) u.setTypec(donneur.getText());
                fs.Registration(u);
                
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.7));
                slide.setNode(layer2);
                slide.setToX(491);
                slide.play();
                layer1.setTranslateX(-309);
                btnsignin.setVisible(true);
                b2.setVisible(true);
                hb.setVisible(false);
                s1.setVisible(true);
                s2.setVisible(true);
                s3.setVisible(true);
                signup.setVisible(true);
                l1.setVisible(false);
                l2.setVisible(false);
                l3.setVisible(false);
                signin.setVisible(false);
                a2.setVisible(false);
                btnsignup.setVisible(false);
                n1.setVisible(true);
                n2.setVisible(true);
                n3.setVisible(true);
                username.setVisible(false);
                email.setVisible(false);
                pwverif.setVisible(false);
                password.setVisible(false);
                msgemail.setVisible(false);
                msguser.setVisible(false);
                msgchoix.setVisible(false);
                System.out.println("Ajout effectué avec succès");
            }
    }

    @FXML
    private void sign(MouseEvent event){ }

    

    @FXML
    private void click(ActionEvent event)throws IOException {

       UserService us = new UserService();
        User u = new User();

        u.setUsername(n1.getText());
        u.setPassword(n2.getText());

        if (us.Authentification(u)) {

            if (n1.getText().equals("admin")) {

                Parent home_page_parent = FXMLLoader.load(getClass().getResource("Dashbord.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            }
            else if (us.checkRole(n1.getText())) {
                    //SessionUser.getFirstInstance(SessionUser.getUser());
                    Parent home_page_parent = FXMLLoader.load(getClass().getResource("FrontFXML1.fxml"));
                    Scene home_page_scene = new Scene(home_page_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.hide(); 
                    app_stage.setScene(home_page_scene);
                    app_stage.show();
            }
             else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez vérifier vos champs!");
            alert.showAndWait();
            System.out.println("no");
        }

    }
        
    }
    
     public static boolean validPassword(String password) {
        if (password.length() > 7) {
            return true;

        }
        return false;
    }

    public static boolean checkPassword(String password) {
        boolean hasNum = false;
        boolean hasCap = false;
        boolean hasLow = false;
        char c;
        for (int i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasNum = true;
            } else if (Character.isUpperCase(c)) {
                hasCap = true;
            } else if (Character.isLowerCase(c)) {
                hasLow = true;
            }
            if (hasCap && hasLow && hasNum) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void pw(MouseEvent event) {

        if (!validPassword(password.getText())) {
            pwverif.setTextFill(Paint.valueOf("RED"));
            pwverif.setText("Le mot de passe doit contenir plus que 7 caractères.");
        } else if (!checkPassword(password.getText())) {
            pwverif.setTextFill(Paint.valueOf("RED"));
            pwverif.setText("Le mot de passe doit contenir des lettres  en majuscule,\n en miniscule ainsi que des chiffres.");
        } else {
            pwverif.setTextFill(Paint.valueOf("#0000FF"));
            pwverif.setText("Mot de passe valide");
        }
    }

     public boolean checkMail(String a) {

        Boolean valide = false;
        int i, j, k;
        for (j = 1; j < a.length(); j++) {
            if (a.charAt(j) == '@') {
                if (j < a.length() - 4) {
                    for (k = j; k < a.length() - 2; k++) {
                        if (a.charAt(k) == '.') {
                            valide = true;
                        }
                    }
                }
            }
        }

        return valide;
    }

    @FXML
    private void verifMail(MouseEvent event) {
        UserService fs = new UserService();
        if (checkMail(email.getText())) {
            if (fs.CheckIfUserExist(email.getText())) {
                msgemail.setTextFill(Paint.valueOf("RED"));
                msgemail.setText("Email utilisé déjà ");
            } else {
                msgemail.setTextFill(Paint.valueOf("#0000FF"));
                msgemail.setText("Email valide");
            }
        } else {
            msgemail.setTextFill(Paint.valueOf("RED"));
            msgemail.setText("Vérifiez le format de votre adresse mail ");
        }

    }
    
     public static boolean checkUsername(String username) {

        if (username.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,}\\b")) {
            return true;
        }

        return false;
    }
    @FXML
    private void Choix(MouseEvent event) {
        
        if ((talent.isSelected())||(donneur.isSelected())) {
                msgchoix.setTextFill(Paint.valueOf("#0000FF"));
                msgchoix.setText("choix effectué");
            }

         else {
            msgchoix.setTextFill(Paint.valueOf("RED"));
           msgchoix.setText("Vous devez choisir un type d'utilisateur ");
        }

    }

    @FXML
    private void verifUsername(MouseEvent event) {
        UserService fs = new UserService();
        if (checkUsername(username.getText())) {
            if (fs.CheckIfUsernameExist(username.getText())) {
                msguser.setTextFill(Paint.valueOf("RED"));
                msguser.setText("Pseudo existe déjà ");
            } else {
                msguser.setTextFill(Paint.valueOf("#0000FF"));
                msguser.setText("Pseudo valide");

            }

        } else {
            msguser.setTextFill(Paint.valueOf("RED"));
            msguser.setText("Vérifiez le format de votre pseudo ");
        }

    }

  
}
