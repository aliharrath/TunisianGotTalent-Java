/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.gui.chat;

import GOT.utils.SessionUser;
import com.jfoenix.controls.JFXDrawer;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MsgController implements Initializable {
       private double xOffset;
    private double yOffset;
  @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane titleBar;

    @FXML
    private AnchorPane detailPane;

    @FXML
    private AnchorPane chatPane;

    @FXML
    private TextArea txtMsg;

    @FXML
    private VBox chatBox;

    @FXML
    private Button btnSend;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextFlow emojiList;

  //  @FXML
  //  private Button btnEmoji;
    @FXML
    private JFXDrawer drawerPane;

    @FXML
    private ScrollPane clientListScroll;

    @FXML
    private VBox clientListBox;
    @FXML
    private Button btnClose;

   @FXML
   TextField txtInput;
   @FXML
   TextArea txtAreaDisplay;
   @FXML
   Button sendMsg;
   @FXML
   Button use;
   @FXML
   AnchorPane parent;
   private double x=0,y=0;
   private Stage stage;
   @FXML
   private ImageView btnEmoji;
    @FXML
   private ImageView close;
    // IO streams
    DataOutputStream output = null;
    List<TaskClientConnection> connectionList = new ArrayList<TaskClientConnection>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Node text : emojiList.getChildren()){
            text.setOnMouseClicked(event -> {
                txtInput.setText(txtInput.getText()+" "+((Text)text).getText());
                emojiList.setVisible(false);
            });
        }
        try {
            // Create a socket to connect to the server
            Socket socket = new Socket(ConnectionUtil.host, ConnectionUtil.port);

            //Connection successful
            txtAreaDisplay.appendText("Connected. \n");
          
            // Create an output stream to send data to the server
            output = new DataOutputStream(socket.getOutputStream());

            //create a thread in order to read message from server continuously
            TaskReadThread task = new TaskReadThread(socket,this);
            Thread thread = new Thread(task);
            thread.start();
          
        } catch (IOException ex) {
            
            txtAreaDisplay.appendText(ex.toString() + '\n');
        }
  
    }
 
    @FXML
    void emojiAction (MouseEvent event) throws IOException
    { if(emojiList.isVisible()){

            emojiList.setVisible(false);
        }else {
            emojiList.setVisible(true);
        }
    }
     @FXML
    void close (MouseEvent event) throws IOException
    {  Stage stage = (Stage) close.getScene().getWindow();
       stage.close();
    }
    
   
     @FXML
    private void send(MouseEvent event) throws IOException
    {           try {
                //get username and message
                
                String username = SessionUser.getUser().getUsername();
                String message = txtInput.getText().trim();

                //if username is empty set it to 'Unknown' 
                if (username.length() == 0) {
                    username = "Unknown";
                }
                //if message is empty, just return : don't send the message
                if (message.length() == 0) {
                    return;
                }

                //send message to server
                output.writeUTF("[" + username + "]: " + message + "");
                output.flush();

                //clear the textfield
                txtInput.clear();
            } catch (IOException ex) {
                System.err.println(ex);
            }
    }
    
    
    
    
    
}
