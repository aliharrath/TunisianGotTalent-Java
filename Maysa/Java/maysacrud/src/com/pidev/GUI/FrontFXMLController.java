/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.GUI;

import com.pidev.utils.SessionUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FrontFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label username;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     username.setText(SessionUser.getUser().getUsername());
    }    
    
}
