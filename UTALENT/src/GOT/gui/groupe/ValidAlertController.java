/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.groupe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author HP
 */
import javafx.fxml.FXML;

public class ValidAlertController {

   
    @FXML
    private void onDone() {
       MyGroupeController.validDialog.close();
    }

}

