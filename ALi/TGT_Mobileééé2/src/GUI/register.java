/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.fos_user;
import Services.ServiceUser;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;


/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */
public class register extends com.codename1.ui.Form {

    public register() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public register(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignupTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SignupTitle", 3.5f);
        //getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("SignUpForm");
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField("email",TextField.EMAILADDR);
    private com.codename1.ui.TextField gui_Text_Field_3 = new com.codename1.ui.TextField("password",TextField.PASSWORD);
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.CheckBox chasseur =new com.codename1.ui.CheckBox();
    private com.codename1.ui.CheckBox talent =new com.codename1.ui.CheckBox();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
        setTitle("Sign Up");
        setName("SignUpForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Component_Group_1.addComponent(gui_Text_Field_3);
        gui_Component_Group_1.addComponent(talent);
        gui_Component_Group_1.addComponent(chasseur);
        gui_Text_Field_2.setHint("username");
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_1.setHint("email");
        gui_Text_Field_1.setName("Text_Field_1");
        gui_Text_Field_3.setConstraint(TextField.PASSWORD);
        gui_Text_Field_3.setHint("Password");
        gui_Text_Field_3.setName("Text_Field_3");
        talent.setName("talent");
        talent.setText("Je suis un Talent");
        chasseur.setName("chasseur");
        chasseur.setText("Je suis un chasseur");
        gui_Container_1.addComponent(gui_Button_2);
        gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Sign Up");
        gui_Button_2.setName("Button_2");
        /*gui_Button_3.setText("Forgot Your Password");
        gui_Button_3.setUIID("CenterLabelSmall");
        gui_Button_3.setName("Button_3");*/
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Button_1.setText("Already have an Account");
        gui_Button_1.setUIID("CenterLabel");
        gui_Button_1.setName("Button_1");
        gui_Button_1.addActionListener(e->new SignInForm_1().show());
    }// </editor-fold>

    
//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        
        //new InboxForm().show();
        
        if(gui_Text_Field_1.getText().length()==0 || gui_Text_Field_2.getText().length()==0 ||gui_Text_Field_3.getText().length()==0){
            Dialog.show("Alert","please fill all the fields", new Command("OK"));
        }
        else{
            try{
                fos_user u=new fos_user();
                u.setUsername(gui_Text_Field_2.getText());
                u.setEmail(gui_Text_Field_1.getText());
                u.setPassword(gui_Text_Field_3.getText());
                if(chasseur.isSelected()){
                    u.setTypec("chasseur");
                }
                else{
                    u.setTypec("talent");
                }
                if(new ServiceUser().addUser(u)){
                   Dialog.show("Success","success", new Command("OK"));
                   new SignInForm_1().show();
                }else{
                    Dialog.show("Erreur","erreur", new Command("OK"));
                }
                
            }catch(Exception e){
                //Dialog.show("Erreur","please fill all the fields", new Command("OK"));
            }
            
        }
    }
    
    
    

}