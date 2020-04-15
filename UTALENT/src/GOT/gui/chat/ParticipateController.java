/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ParticipateController implements Initializable {
    @FXML
    private Button particapte;
     @FXML
    private TextArea rep;
    /**
     * Initializes the controller class.
     */
    List<TaskClientConnection> connectionList = new ArrayList<TaskClientConnection>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         new Thread(() -> {
            try {
                
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(ConnectionUtil.port);
                
               
                //continous loop
                while (true) {
                       Platform.runLater(()
                        -> rep.appendText("New server started at " + new Date() + '\n'));

                    // Listen for a connection request, add new connection to the list
                    Socket socket = serverSocket.accept();
                 //   TaskClientConnection connection = new TaskClientConnection(socket,this);
                   // connectionList.add(connection);

                    //create a new thread
                    Thread thread = new Thread();
                    thread.start();

                }
            } catch (IOException ex) {
                  rep.appendText(ex.toString() + '\n');
            }
        }).start();
    }

    public void broadcast(String message) {
        for (TaskClientConnection clientConnection : this.connectionList) {
            clientConnection.sendMessage(message);
        }
    }
    
     @FXML
    private void Participate(MouseEvent event) throws IOException
    {           Parent home_page_parent = FXMLLoader.load(getClass().getResource("msg.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
    }    
    

