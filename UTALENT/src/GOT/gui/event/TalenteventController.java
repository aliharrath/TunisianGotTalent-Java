/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.event;

import GOT.entites.user.User;
import GOT.entites.event.evenement;
import GOT.entites.event.participation;
import GOT.services.event.ServiceEvenement;
import GOT.services.event.ServiceTicket;
import GOT.services.event.Serviceparticipation;
import GOT.services.user.UserService;
import GOT.utils.SessionUser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maysa1998
 */
public class TalenteventController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private FlowPane flowPaneEvent;
    @FXML
    private FlowPane flowPanePart;
    @FXML
    private ImageView qrviewer;
    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            affichageEvenement();
            AfficherParticipation();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void QrCode(String Body) {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = Body;
        int width = 200;
        int height = 200;
        String fileType = "png";

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");

        } catch (WriterException ex) {
            ex.getMessage();
        }
        qrviewer.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
    }

    public void setFILES(String Body, String Body1, String Body2, String Body3) {
        try {

            OutputStream file = new FileOutputStream(new File("text.pdf"));

            Document document = new Document();

            PdfWriter.getInstance(document, file);

            document.open();
            document.addTitle("Ticket");

            com.itextpdf.text.Image img;
            img = com.itextpdf.text.Image.getInstance("C:/Users/HP/Downloads/UTALENT/src/GOT/event/Images/louay.jpg");
            com.itextpdf.text.Image.getInstance(img);
            document.add(img);
            document.add(new Paragraph("                    "));
            document.add(new Paragraph("                    "));

            document.add(new Paragraph(Body));
            document.add(new Paragraph(Body1));
            document.add(new Paragraph(Body2));
            document.add(new Paragraph(Body3));
            document.close();
            file.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void btnPDF(String Body, String Body1, String Body2, String Body3) throws IOException, SQLException {
        //User userTest = new User(102, "Louay", "Yahyaoui", "louay@esprit.tn", "louay", "oussema", "male", "28-08-1992", 234223878, "SimpleUtilisateur", "Comedie", "hahaha", 5000);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Button button2 = new Button();
        button2.setStyle("-fx-background-color: #00ff00");
        alert.setTitle("PDF ");
        alert.setContentText("Participation avec succés !  vous voulez exporter votre ticket en PDF ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            setFILES(Body, Body1, Body2, Body3);

        } else {

        }
    }

    private void affichageEvenement() throws SQLException {

        ServiceEvenement serC = new ServiceEvenement();

        ObservableList<evenement> listeve = FXCollections.observableArrayList(serC.readAll());

        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();
        ArrayList<HBox> btnP = new ArrayList<>();

        for (int i = 0; i < listeve.size(); i++) {

            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(17);
            h.setPrefHeight(24);
            h.setVisible(false);
            as.add(h);

            VBox VBoxEvent = new VBox();

            VBoxEvent.setSpacing(5);
            VBoxEvent.setStyle("-fx-background-color : #FFFFFF;");
            //VBoxEvent.setStyle("-fx-border-color : #e44d3a;");
            VBoxEvent.setAlignment(Pos.CENTER);
            VBoxEvent.setPrefHeight(175);
            VBoxEvent.setPrefWidth(205);
            HBox panne = new HBox();
            panne.setSpacing(5);
            panne.setStyle("-fx-background-color : #FFFFFF;");
            //VBoxEvent.setStyle("-fx-border-color : #2B48E8;");
            panne.setAlignment(Pos.CENTER);
            panne.setPrefHeight(30);
            panne.setPrefWidth(70);

            Rectangle c = new Rectangle(205, 90);

            try {
                c.setFill(new ImagePattern(new Image(new FileInputStream("C:/xampp/htdocs/pi_dev_Image/" + listeve.get(i).getImage()))));

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

            VBoxEvent.getChildren().add(c);

            Label titreEvent = new Label("Titre : " + listeve.get(i).getTitre());
            Label description = new Label("Cout: " + listeve.get(i).getCout());

            int idEvenement = listeve.get(i).getId_evenement();
            evenement e1 = serC.findById(idEvenement);

            String Titre1 = e1.getTitre();
            String Description1 = e1.getDescription();
            String Image1 = e1.getImage();
            Date DateD1 = e1.getDatedebut();
            Date DateF1 = e1.getDatefin();
            String Type1 = e1.getTypedetalent();
            int Cout1 = e1.getCout();
            int Nb1 = e1.getNbparticipant();
            UserService u = new UserService();
            User userTest = u.findById(SessionUser.getUser().getId());

            Button btnPart = new Button("Participer");
            btnPart.setTextOverrun(OverrunStyle.CLIP);
            btnPart.setStyle("-fx-background-color : #3D7AB7;");
            String titre = listeve.get(i).getTitre();
            Date date = listeve.get(i).getDatedebut();

            Serviceparticipation serviceP = new Serviceparticipation();
            if (serviceP.findParticipation(e1, SessionUser.getUser().getId()) == true) {
                btnPart.setDisable(true);
            } else {

                btnPart.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                        try {

                            ServiceTicket service = new ServiceTicket();
//                            ticket tik = service.findBy(c1, Userconnected);
//                            String motDePasse = tik.getMotDePasse();
//
                            String nom = userTest.getUsername();
                            Serviceparticipation serP = new Serviceparticipation();

                            if (serP.findParticipation(e1, SessionUser.getUser().getId()) == false) {
                                UserService u = new UserService();
                                User userTest = u.findById(SessionUser.getUser().getId());

                                if ((userTest.getNb_diamants() > e1.getCout()) && (e1.getNbparticipant() > 0)) {

                                    serP.ParticiperEvenement(e1, userTest);
                                    
                                    
                                    
                                    
                  //  TwilioSms twilio = new TwilioSms();
                  //  twilio.sendSms("Participation avec succés Mr--"+nom);

                                    String Body = "Bonjour Mr/Mme  --" + nom + "-- je vous souhaite la bienvenue ";
                                    String Body1 = "Au sein de notre Evenement intutilé --" + titre + "--";
                                    String Body2 = "Qui aura lieu le  --" + date + "--";
                                    String Body3 = "";
                                    try {
                                        btnPDF(Body, Body1, Body2, Body3);
                                    } catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                                    }

                                    QrCode("Bonjour Mr/Mme  --" + nom + "-- je vous souhaite la bienvenue au sein de notre Evenement sous le Titre --" + titre + "-- qui aura lieu le --" + date);

                                    flowPanePart.getChildren().clear();
                                    AfficherParticipation();
                                    flowPaneEvent.getChildren().clear();
                                    affichageEvenement();

                                } else if ((e1.getNbparticipant() == 0)) {
                                    Alert alert1 = new Alert(Alert.AlertType.ERROR, "Evenement plein !!!", ButtonType.OK);
                                    alert1.show();
                                    flowPanePart.getChildren().clear();
                                    AfficherParticipation();
                                    flowPaneEvent.getChildren().clear();
                                    affichageEvenement();

                                } else {

                                    Alert alert1 = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas le nombre exacte du diamant", ButtonType.OK);
                                    alert1.show();
                                    flowPanePart.getChildren().clear();
                                    AfficherParticipation();
                                    flowPaneEvent.getChildren().clear();
                                    affichageEvenement();
                                }
                            } else {

                                QrCode("Bonjour Mr  --" + nom + "-- je vous souhaite la bienvenue au sein de notre Evenement sous le Titre --" + titre + "-- qui aura lieu le --" + date);

                                flowPanePart.getChildren().clear();
                                AfficherParticipation();
                                flowPaneEvent.getChildren().clear();
                                affichageEvenement();

                            }
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }

                    }

                });
            }

            Button btnDetail = new Button("Details");
            btnDetail.setTextOverrun(OverrunStyle.CLIP);
            btnDetail.setStyle("-fx-background-color : #E4E0E0;");
            btnDetail.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    try {

                        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("detailevent.fxml"));
                        Parent root = LOADER.load();
                        DetaileventController maysa = LOADER.getController();
                        maysa.setDetails(Titre1, Description1, Image1, DateD1, DateF1, Type1, Cout1, Nb1);

                        Scene scene = new Scene(root, 600, 400);

                        Stage primaryStage = new Stage();
                        primaryStage.setScene(scene);
                        primaryStage.show();

                    } catch (IOException ex) {

                    }

                }
            });

            panne.getChildren().add(btnPart);
            panne.getChildren().add(btnDetail);
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

    private void AfficherParticipation() throws SQLException {

        UserService u = new UserService();
        User userTest = u.findById(SessionUser.getUser().getId());
        Serviceparticipation service1 = new Serviceparticipation();

        ObservableList<evenement> MyList = FXCollections.observableArrayList(service1.findByRechercher(SessionUser.getUser().getId()));

        System.out.println("We're right here for now ");
        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();
        ArrayList<HBox> btnP = new ArrayList<>();

        for (int i = 0; i < MyList.size(); i++) {

            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(17);
            h.setPrefHeight(24);
            h.setVisible(false);
            as.add(h);

            VBox VBoxEvent = new VBox();

            VBoxEvent.setSpacing(5);
            VBoxEvent.setStyle("-fx-background-color : #FFFFFF;");
            //VBoxEvent.setStyle("-fx-border-color : #e44d3a;");
            VBoxEvent.setAlignment(Pos.CENTER);
            VBoxEvent.setPrefHeight(175);
            VBoxEvent.setPrefWidth(205);
            HBox panne = new HBox();
            panne.setSpacing(5);
            panne.setStyle("-fx-background-color : #FFFFFF;");
            //VBoxEvent.setStyle("-fx-border-color : #2B48E8;");
            panne.setAlignment(Pos.CENTER);
            panne.setPrefHeight(30);
            panne.setPrefWidth(70);

            Rectangle c = new Rectangle(205, 90);

            try {
                c.setFill(new ImagePattern(new Image(new FileInputStream("C:/xampp/htdocs/pi_dev_Image/" + MyList.get(i).getImage()))));
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

            VBoxEvent.getChildren().add(c);

            Label titreEvent = new Label("Titre : " + MyList.get(i).getTitre());
            Label dateF = new Label("Cout: " + MyList.get(i).getCout());
            int idevenement = MyList.get(i).getId_evenement();
            ServiceEvenement serC = new ServiceEvenement();
            evenement c1 = serC.findById(idevenement);

            Button btnSupp = new Button("Supprimer");
            btnSupp.setTextOverrun(OverrunStyle.CLIP);
            btnSupp.setStyle("-fx-background-color : #E82B34;");
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
                            participation p = new participation(SessionUser.getUser().getId(), c1);
                            Serviceparticipation ser = new Serviceparticipation();
                            System.err.println("jareb1");
                            ser.delete(p);
                            ServiceEvenement maysa = new ServiceEvenement();
                            maysa.update(c1.getTitre(), c1.getDatedebut(), c1.getDatefin(), c1.getDescription(), c1.getTypedetalent(), c1.getCout(), c1.getImage(), c1.getNbparticipant() + 1, c1.getId_evenement());
                            System.err.println("jareb2");
                            qrviewer.setImage(null);
                            flowPanePart.getChildren().clear();
                            AfficherParticipation();
                            flowPaneEvent.getChildren().clear();
                            affichageEvenement();

                        } else {
                            flowPanePart.getChildren().clear();
                            AfficherParticipation();
                            flowPaneEvent.getChildren().clear();
                            affichageEvenement();

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            });

            Button btnAff = new Button("Ticket");
            btnAff.setTextOverrun(OverrunStyle.CLIP);
            btnAff.setStyle("-fx-background-color : #B9C0C7;");
            btnAff.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    try {

                        String titre = c1.getTitre();
                        String nom = userTest.getUsername();
                        Date date = c1.getDatedebut();

                        QrCode("Bonjour Mr  --" + nom + "-- je vous souhaite la bienvenue au sein de notre Evenement sous le Titre --" + titre + "-- qui aura lieu le --" + date);

                        flowPanePart.getChildren().clear();
                        AfficherParticipation();
                        flowPaneEvent.getChildren().clear();
                        affichageEvenement();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }

                }
            });

            panne.getChildren().add(btnSupp);
            panne.getChildren().add(btnAff);

            VBoxEvent.getChildren().add(panne);
            VBoxEvent.getChildren().add(titreEvent);
            VBoxEvent.getChildren().add(dateF);

            vbx.add(VBoxEvent);
            btnP.add(panne);
            flowPanePart.getChildren().add(vbx.get(i));

            flowPanePart.getChildren().add(as.get(i));

            if (i == 0) {
                System.out.println("i=0 llllll");
            } else if (((i + 1) % 2) == 0) {
                Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                sepHoriz.setPrefWidth(364);
                sepHoriz.setPrefHeight(30);
                sepHoriz.setVisible(false);
                flowPanePart.getChildren().add(sepHoriz);

            }
        }

    }
    @FXML
    public void menu(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("../user/FrontFXML.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    

}
