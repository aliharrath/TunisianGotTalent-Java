/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.sponsor;

import GOT.entites.sponsor.Pack;
import GOT.services.sponsor.PackServices;
import GOT.services.sponsor.SponsorServices;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Rawia
 */
public class Pack_addController implements Initializable {

    ObservableList<String> adpos = FXCollections.observableArrayList("Center", "Right", "Left");
    ObservableList<String> obsp = FXCollections.observableArrayList();
    @FXML
    private Label notif;
    @FXML
    private AnchorPane parent;
    @FXML
    private VBox inbox;
    @FXML
    private TextField ad;
    @FXML
    private ImageView imageAd;
    @FXML
    private ChoiceBox<String> posChoice;
    @FXML
    private ChoiceBox<String> spChoice;
    @FXML
    private DatePicker disp;
    @FXML
    private DatePicker disc;
    @FXML
    private JFXTextField price;
    @FXML
    private Label errpos;
    @FXML
    private Label errprix;
    @FXML
    private Label errdisp;
    @FXML
    private Label errdisc;
    @FXML
    private Label errspon;
    @FXML
    private Label errad;
    Boolean verif = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SponsorServices sp = new SponsorServices();
        PackServices ps = new PackServices();

        String[] np = new String[sp.read().size()];

        posChoice.setItems(adpos);

        for (int i = 0; i < sp.read().size(); i++) {
            np[i] = (sp.read().get(i).getNamesp());

        }
        obsp.setAll(np);
        spChoice.setItems(obsp);

        disp.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                disp.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        disc.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                disc.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        disp.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (ps.checkDate(disp.getValue())) {
                    errdisp.setTextFill(Paint.valueOf("GREEN"));
                    errdisp.setText("OK.");
                } else {
                    errdisp.setTextFill(Paint.valueOf("Red"));
                    errdisp.setText("Date non valide!");
                }
            }
        });
        disc.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (ps.checkAddDate(disp.getValue(), disc.getValue())) {
                    errdisc.setTextFill(Paint.valueOf("GREEN"));
                    errdisc.setText("OK.");
                } else {
                    errdisc.setTextFill(Paint.valueOf("Red"));
                    errdisc.setText("Date non valide!");
                }
            }
        });

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
    private void saveBtn(ActionEvent event) {

        String path = ad.getText();
       
        path = path.replace("\\", "/");
        PackServices ps = new PackServices();
        Pack p = new Pack(ps.owner_id(spChoice.getValue()), posChoice.getValue(), path, disp.getValue().toString(), disc.getValue().toString(), price.getText());
        // int idsp, String positin, String ad, String displaydate, String discarddate, String price
        ps.pack_add(p);
        notif.setText("Publicité ajouté avec succès!");

        System.out.println(""+verif);
    }

    @FXML
    private void uploadBtn(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        Image image1;
        if (selectedFile != null) {
            ad.setText(selectedFile.getAbsolutePath());
            try {
                image1 = new Image(new FileInputStream(selectedFile.getAbsolutePath()));
                imageAd.setImage(image1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Pack_addController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("file is not valid!");
        }

    }
   
    public void controleSaisi(InputMethodEvent event) {

    }

    @FXML
    private void controleSaisi(KeyEvent event) {

        if (!price.getText().isEmpty()) {
            errprix.setTextFill(Paint.valueOf("GREEN"));
            errprix.setText("OK.");
            verif = true;
        } else {
            errprix.setTextFill(Paint.valueOf("Red"));
            errprix.setText("Champ obligatoire!");
            verif = false;
        }/*
        if (!posChoice.getValue().isEmpty()) {
            errpos.setTextFill(Paint.valueOf("GREEN"));
            errpos.setText("OK.");
            verif = true;
        } else {
            errpos.setTextFill(Paint.valueOf("Red"));
            errpos.setText("Champ obligatoire!");
            verif = false;
        }
        if (!spChoice.getValue().isEmpty()) {
            errspon.setTextFill(Paint.valueOf("GREEN"));
            errspon.setText("OK.");
            verif = true;
        } else {
            errspon.setTextFill(Paint.valueOf("Red"));
            errspon.setText("Champ obligatoire!");
            verif = false;
            
        }
*/
    }

}
