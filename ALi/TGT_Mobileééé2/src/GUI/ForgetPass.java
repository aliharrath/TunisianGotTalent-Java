/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.SignInForm_1;
import Services.ServiceUser;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;

/**
 *
 * @author skand
 */
public class ForgetPass extends com.codename1.ui.Form{
    
    public ForgetPass(com.codename1.ui.Form previous) {
        this(com.codename1.ui.util.Resources.getGlobalResources()); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }
   
    
    public ForgetPass(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        //getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("ForgetPass");
        
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_back = new com.codename1.ui.Button();

    private com.codename1.ui.Label gui_Label_email = new com.codename1.ui.Label();
                           
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_2.addActionListener(callback);
    }
    
    

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_Button_2) {
                onButton_2ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Forget your Password!");
        setName("ForgetPassForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(gui_Label_email);
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Text_Field_2.setText("");
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_1.setText("");
        gui_Text_Field_1.setName("Text_Field_1");
        gui_Label_email.setText("Email");
        gui_Container_1.addComponent(gui_Button_2);
        gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Send");
        gui_Button_2.setName("Button_2");
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        ServiceUser us=new ServiceUser();
        String email=gui_Text_Field_2.getText();
        if( email.length()==0 ){
            Dialog.show("Erreur", "Coordonnees incorrectes", "Ok", null);
        }
        else{
            if(us.Verify(email)){
                Dialog.show("Check your mail", "Nous avons vous envoyé un email pour changer votre mot de passe", "Ok", null);
                new SignInForm_1().show();
            }
            else{
                Dialog.show("Erreur", "Nous n'avons pas trouvé cet utilisateur, Veuillez verifier votre email!", "Ok", null);
                gui_Text_Field_2.setText("");
            }
        }
    }

}
