/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.event;


import GOT.entites.user.User;
import GOT.entites.event.evenement;
import GOT.services.event.ServiceEvenement;
import GOT.services.user.UserService;
import GOT.utils.SessionUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maysa1998
 */
public class ChasseureventController implements Initializable {

    private String ImageEvent;
    @FXML
    public TextField titre;
    @FXML
    public TextField desc;
    public DatePicker dated;
    public DatePicker datef;
    @FXML
    public ComboBox<String> talent;
    @FXML
    public TextField cout;
    public TextField nb;

    @FXML
    private Button browse;
    @FXML
    private ImageView imageC;
    @FXML
    private FlowPane flowPaneEvent;

    ObservableList<String> list = FXCollections.observableArrayList("Dance", "BeatBox", "Musique", "Comedie");
    @FXML
    private ImageView titreCheck;
    @FXML
    private ImageView descriptionCheck;
    @FXML
    private ImageView debCheck;
    @FXML
    private ImageView finCheck;
    @FXML
    private ImageView coutCheck;
    @FXML
    private ImageView talentChecker;
    @FXML
    private ImageView nbCheck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        talent.setItems(list);
        try {
            affichageUS();
        } catch (SQLException ex) {
            Logger.getLogger(ChasseureventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void affichageUS() throws SQLException {
        
        UserService u = new UserService();
        User userTest = u.findById(SessionUser.getUser().getId());

        ServiceEvenement ser = new ServiceEvenement();

        ObservableList<evenement> listEvent = FXCollections.observableArrayList(ser.readAll());

        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();
        ArrayList<HBox> btnP = new ArrayList<>();

        for (int i = 0; i < listEvent.size(); i++) {

            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(29);
            h.setPrefHeight(24);
            h.setVisible(false);
            as.add(h);

            VBox VBoxEvent = new VBox();

            VBoxEvent.setSpacing(7);
            VBoxEvent.setStyle("-fx-background-color : #FFFFFF;");
//            VBoxEvent.setStyle("-fx-border-color : #e44d3a;");
            VBoxEvent.setAlignment(Pos.CENTER);
            VBoxEvent.setPrefHeight(277);
            VBoxEvent.setPrefWidth(265);
            HBox panne = new HBox();
            panne.setSpacing(5);
            panne.setStyle("-fx-background-color : #FFFFFF;");
            //VBoxEvent.setStyle("-fx-border-color : #2B48E8;");
            panne.setAlignment(Pos.CENTER);
            panne.setPrefHeight(30);
            panne.setPrefWidth(80);

            Rectangle c = new Rectangle(265, 125);

            try {
              
                c.setFill(new ImagePattern(new Image(new FileInputStream("C:/xampp/htdocs/pi_dev_Image/" + listEvent.get(i).getImage()))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ChasseureventController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            VBoxEvent.getChildren().add(c);

            Label titreEvent = new Label("Titre : " + listEvent.get(i).getTitre());
            Label description = new Label("Description: " + listEvent.get(i).getDescription());
  

            int idEvenement = listEvent.get(i).getId_evenement();
            evenement c1 = ser.findById(idEvenement);
            String imgMaysa=c1.getImage();
            
            Button btnSupp = new Button("Supprimer");
            btnSupp.setTextOverrun(OverrunStyle.CLIP);
            btnSupp.setStyle("-fx-background-color : #E82B34;");
            
           
            
             Button modif = new Button("Modifier");
            int idevent = listEvent.get(i).getId_evenement();
            modif.setStyle("-fx-background-color : #60A9C4;");
            
            
            if(c1.getIduser()==userTest.getId())
            {
            

            //btnSupp.setDisable(true);
            btnSupp.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        Button button2 = new Button();
                        button2.setStyle("-fx-background-color: #F54F4F");
                        alert.setTitle("Suppression ");
                        alert.setContentText("Voulez-vous vraiment supprimer cette evenement ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            ser.delete(c1);
                            flowPaneEvent.getChildren().clear();
                            affichageUS();

                        } else {
                            flowPaneEvent.getChildren().clear();
                            affichageUS();

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            });

             Button btnDetail = new Button("...");
            btnDetail.setStyle("-fx-background-color : #CFDBD1;");
            btnDetail.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                   


                        titre.setText(c1.getTitre());
                        desc.setText(c1.getDescription());
                        cout.setText(String.valueOf(c1.getCout()));
                        dated.setValue(LocalDate.parse(String.valueOf(c1.getDatedebut())));
                        datef.setValue(LocalDate.parse(String.valueOf(c1.getDatefin())));
                        talent.setValue(c1.getTypedetalent());
                        File file = new File("C:/xampp/htdocs/pi_dev_Image/" + c1.getImage());
                        imageC.setImage(new Image(file.toURI().toString()));
                        nb.setText(String.valueOf(c1.getNbparticipant()));

                   

                }
            });

           
            modif.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    LocalDate DateDe = dated.getValue();
                    Date DateD = Date.valueOf(DateDe);

                    LocalDate Datef = datef.getValue();
                    Date DateFe = Date.valueOf(Datef);

                    String Titre = titre.getText();
                    String Description = desc.getText();

                    String TypeTalent = talent.getValue();
                    int Cout = Integer.parseInt(cout.getText());
                    int Nb = Integer.parseInt(nb.getText());
               

                    try {
                       

                        if (ser.update(Titre, DateD, DateFe, Description, TypeTalent, Cout, ImageEvent, Nb, idevent) == true) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Modification avec succés !", ButtonType.OK);
                            alert.show();
                            flowPaneEvent.getChildren().clear();
                            affichageUS();

                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Echec de modification  !", ButtonType.OK);
                            alert.show();
                            flowPaneEvent.getChildren().clear();
                            affichageUS();

                        }

                        // ser.update(Titre, Description, TypeTalent, DateD, DateFe, Cout,modif );
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());                    }
                }
            });
            panne.getChildren().add(btnSupp);
            panne.getChildren().add(modif);
            panne.getChildren().add(btnDetail);
            
        }
          else
            {
                panne.getChildren().add(btnSupp);
                panne.getChildren().add(modif);
                btnSupp.setDisable(true);
                
                modif.setDisable(true);
            }
            
            
            VBoxEvent.getChildren().add(panne);


            
            
            VBoxEvent.getChildren().add(titreEvent);
            VBoxEvent.getChildren().add(description);


            vbx.add(VBoxEvent);
            btnP.add(panne);
            flowPaneEvent.getChildren().add(vbx.get(i));

            flowPaneEvent.getChildren().add(as.get(i));

            if (i == 0) {
                System.out.println("i=0 llllll");
            } else if (((i + 1) % 2) == 0) {
                Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                sepHoriz.setPrefWidth(364);
                sepHoriz.setPrefHeight(30);
                sepHoriz.setVisible(false);
                flowPaneEvent.getChildren().add(sepHoriz);

            }
        }
    }

    @FXML
    public void ChoiceImage() throws FileNotFoundException, IOException {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if (f != null) {

            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(new File(f.getAbsolutePath()));
//             
                os = new FileOutputStream(new File("C:/xampp/htdocs/pi_dev_Image/" + f.getName()));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                System.out.println("maysa");

            } finally {
                is.close();
                os.close();

            }

            File file = new File("C:/xampp/htdocs/pi_dev_Image/" + f.getName());
            imageC.setImage(new Image(file.toURI().toString()));
            ImageEvent = f.getName();
            System.out.println(ImageEvent);
        } else if (f == null) {
            System.out.println("Erreur !");
        }

    }

    @FXML
    public void Ajouterevenement() {
//       if(talent.getValue()!=null  &&  cout.getText()!=null  &&  nb.getText()!=null)
//       {
UserService u = new UserService();
        User userTest = u.findById(SessionUser.getUser().getId());
                 if ((testSaisie() == true) && (testCout()==true)) {
        String Titre = titre.getText();
        String Description = desc.getText();
        LocalDate dateD = dated.getValue();
        Date DateDebut = Date.valueOf(dateD);
        LocalDate dateF = datef.getValue();
        Date DateFin = Date.valueOf(dateF);

        String Talent = talent.getValue();
        
        int Cout = Integer.parseInt(cout.getText());
        int Nb = Integer.parseInt(nb.getText());
        
        
        evenement event = new evenement(Titre, DateDebut, DateFin, Description, Talent, Cout,userTest.getId(), ImageEvent, Nb);
        
        ServiceEvenement ser = new ServiceEvenement();
         
        try {
            ser.ajouter(event);
            flowPaneEvent.getChildren().clear();
            affichageUS();
        } catch (SQLException ex) {
         System.out.println(ex.getMessage());                    
        }
         }
//       }
//       else
//       {
//           Alert a = new Alert(Alert.AlertType.ERROR, "Verifier les champs", ButtonType.OK);
//           a.show();
//       }
            
        
        

    }
    
    @FXML
    public void logout(ActionEvent event) throws IOException
    {
         Parent home_page_parent = FXMLLoader.load(getClass().getResource("user.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
    
     private Boolean testSaisie() {

        if (!testTitre()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "verifier que vous avez saisir le Titre !", ButtonType.OK);
            alert.show();
        }
        if (!testDescription()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "verifier que vous avez saisir la Description !", ButtonType.OK);
            alert.show();
        }
        if (!testTalent()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "verifier que vous avez selectionner le type de talent !", ButtonType.OK);
            alert.show();
        }

        if (!testDateDebut()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "verifier que la date debut est superieure a la date courante !", ButtonType.OK);
            alert.show();
        }
        if (!testDateFin()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "verifier que la date fin est superieure a la date debut !", ButtonType.OK);
            alert.show();
        }

        if (!testCout()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "verifier que vous avez saisie des entier dans le Cout !", ButtonType.OK);
            alert.show();
        }
        if (!testNombre()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "verifier que vous avez saisie des entier dans le Nombre de participants !", ButtonType.OK);
            alert.show();
        }

        return testTitre() && testDescription() && testTalent() && testDateDebut() && testDateFin() && testCout() && testNombre();
    }

    private Boolean testTalent() {

        if (!((talent.getSelectionModel().selectedItemProperty().equals("Type de talent"))  )) {
            File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/tick.png");
//            System.out.println(file.toURI().toString());
            talentChecker.setImage(new Image(file.toURI().toString()));
            return true;
        } else {
            File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/close.png");
//            System.out.println(file.toURI().toString());
            talentChecker.setImage(new Image(file.toURI().toString()));

            return false;

        }
    }

    private Boolean testDescription() {
        int nbNonChar = 0;
        for (int i = 1; i < desc.getText().trim().length(); i++) {
            char ch = desc.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if ( desc.getText().trim().length() >= 3) {
            File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/tick.png");
//            System.out.println(file.toURI().toString());
            descriptionCheck.setImage(new Image(file.toURI().toString()));
            return true;
        } else {
            File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/close.png");
//            System.out.println(file.toURI().toString());
            descriptionCheck.setImage(new Image(file.toURI().toString()));
            return false;

        }

    }

    private Boolean testTitre() {
        int nbNonChar = 0;
        for (int i = 1; i < titre.getText().trim().length(); i++) {
            char ch = titre.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if ( titre.getText().trim().length() >= 3) {
            File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/tick.png");
//            System.out.println(file.toURI().toString());
            titreCheck.setImage(new Image(file.toURI().toString()));
            return true;
        } else {
            File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/close.png");
//            System.out.println(file.toURI().toString());
            titreCheck.setImage(new Image(file.toURI().toString()));
            return false;

        }

    }

    private Boolean testDateDebut() {
        //java.sql.Timestamp today_date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        ChronoLocalDate dt
                = LocalDate.now();
        if (dated.getValue() != null && dated.getValue().isAfter(dt)) {

            File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/tick.png");
//            System.out.println(file.toURI().toString());
            debCheck.setImage(new Image(file.toURI().toString()));
            System.out.println("yes");
            return true;

        } else {

            File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/close.png");
//            System.out.println(file.toURI().toString());
            debCheck.setImage(new Image(file.toURI().toString()));
            System.out.println("yes");
            System.out.println("no");

            return false;

        }

    }


    private Boolean testDateFin() {
//        java.sql.Timestamp today_date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        ChronoLocalDate dt
                = LocalDate.now();
        if (datef.getValue() != null && datef.getValue() != null && datef.getValue().isAfter(dated.getValue())) {
            File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/tick.png");
//            System.out.println(file.toURI().toString());
            finCheck.setImage(new Image(file.toURI().toString()));
            System.out.println("yes");
            return true;

        } else {

            File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/close.png");
//            System.out.println(file.toURI().toString());
            finCheck.setImage(new Image(file.toURI().toString()));
            System.out.println("no");
            return false;
        }

    }
    
   
    private boolean testCout() {

       if((cout.getText().matches("^[0-9]*$")) && (!cout.getText().equals("")))
       {
            File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/tick.png");
            System.out.println("yes");
            coutCheck.setImage(new Image(file.toURI().toString()));
            return true;
        }
       else
       {
        File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/close.png");
           System.out.println("no");
        coutCheck.setImage(new Image(file.toURI().toString()));
        return false;
    }

    }
    
    private boolean testNombre() {

       if((nb.getText().matches("^[0-9]*$")) && (!nb.getText().equals("")))
       {
            File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/tick.png");
//            System.out.println(file.toURI().toString());
            nbCheck.setImage(new Image(file.toURI().toString()));
            return true;
        }
       else
       {
        File file = new File("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/close.png");
//            System.out.println(file.toURI().toString());
        nbCheck.setImage(new Image(file.toURI().toString()));
        return false;
    }

    }

}
