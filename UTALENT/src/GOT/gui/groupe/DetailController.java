/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.groupe;

import GOT.entites.groupe.Groupe;
import GOT.gui.chat.ConnectionUtil;
import GOT.gui.chat.TaskClientConnection;
import static GOT.gui.groupe.MyGroupeController.validDialog;
import GOT.services.groupe.GroupeService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DetailController {

    /**
     * Initializes the controller class.
     */
   

    

   
    private int id;
    @FXML
    private Label grp_name;
    @FXML
    private ImageView close;
    @FXML
    private ImageView back;
    @FXML
    private ImageView chat;
    @FXML
    private JFXButton membre;
    public int getId() {
        return id;
    }
 

     public void setNom(String name) {
        grp_name.setText(name);
    }
     
      List<TaskClientConnection> connectionList = new ArrayList<TaskClientConnection>();
   Groupe g;
    public Groupe setGrp(Groupe group) {
        this.g=new Groupe();
      this.g=group;
        return this.g;
    }

    public void setId(int id) {   
       this.id=id;
    }
        private VBox validAlertPane;
        public static JFXDialog validDialog;
        private VBox validPane;
         @FXML
         private StackPane root;
   
    
    public void initialize(URL url, ResourceBundle rb, Groupe group) {
         new Thread(() -> {
            try {
                
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(ConnectionUtil.port);
                
               
                //continous loop
                while (true) {
                       Platform.runLater(()
                        -> System.out.print("New server started at " + new Date() + '\n'));

                    // Listen for a connection request, add new connection to the list
                    Socket socket = serverSocket.accept();
                    TaskClientConnection connection = new TaskClientConnection(socket,this);
                    connectionList.add(connection);

                    //create a new thread
                    Thread thread = new Thread(connection);
                    thread.start();

                }
            } catch (IOException ex) {
                 System.out.print(ex.toString() + '\n');
            }
        }).start();
        membre.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                         try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("membres.fxml"));
                                    validPane= loader.load();
                                    MembresController controller = loader.getController();
                                    controller.setName(group.getNom());
                            } catch(IOException ioe) {
                                ioe.printStackTrace();
                            }
    
                       validDialog = new JFXDialog(root, validPane, JFXDialog.DialogTransition.CENTER);
                       validDialog.show();
 
                    }
                });
        
      
        
        
    }    
     public void broadcast(String message) {
        for (TaskClientConnection clientConnection : this.connectionList) {
            clientConnection.sendMessage(message);
        }
    }
    
    @FXML
    private void close(MouseEvent event)
    {   Stage stage = (Stage) close.getScene().getWindow();
       stage.close();
    }
    
    @FXML
    private void back(MouseEvent event) throws IOException
    {           Parent home_page_parent = FXMLLoader.load(getClass().getResource("MyGroupe.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
      @FXML
    private void Participate(MouseEvent event) throws IOException
    {           Parent home_page_parent = FXMLLoader.load(getClass().getResource("../chat/msg.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
   
}
