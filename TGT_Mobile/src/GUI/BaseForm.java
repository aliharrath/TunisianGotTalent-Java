/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author loume78
 */
import static Entities.Session.getCurrentSession;
import Entities.fos_user;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;


/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
    Resources theme;
     Form current;
     static  fos_user u;

    public fos_user getU() {
        return u;
    }

    public void setU(fos_user u) {
        BaseForm.u = u;
    }
     String url = "http://localhost/testing_fos_ali/web/uploads";
    public void installSidemenu(Resources res) {
         try {
             Image selection = Image.createImage("/selection-in-sidemenu.png");
             
             Image inboxImage = null;
             if(isCurrentInbox()) inboxImage = selection;
             
             Image trendingImage = null;
             if(isCurrentTrending()) trendingImage = selection;
             
             Image calendarImage = null;
             if(isCurrentCalendar()) calendarImage = selection;
             
             Image statsImage = null;
             if(isCurrentStats()) statsImage = selection;
             
             Button inboxButton = new Button("Inbox", inboxImage);
             inboxButton.setUIID("SideCommand");
             inboxButton.getAllStyles().setPaddingBottom(0);
             Container inbox = FlowLayout.encloseMiddle(inboxButton,
                     new Label("18", "SideCommandNumber"));
             inbox.setLeadComponent(inboxButton);
             inbox.setUIID("SideCommand");
             //inboxButton.addActionListener(e -> new InboxForm().show());
             getToolbar().addComponentToSideMenu(inbox);
             
             getToolbar().addCommandToSideMenu("publications", statsImage, e -> new afficherevent(current).show());
             getToolbar().addCommandToSideMenu("evenements", calendarImage, 
                     
                     e -> {
                         if(getCurrentSession().getTypec().equals("chasseur"))
                             new afficherevent(current).show();
                         else
                             new participer(current).show();
                     }
                         
             
             );
             getToolbar().addCommandToSideMenu("donations", null, e -> {});
             getToolbar().addCommandToSideMenu("Groups", trendingImage, e -> new HomeForm(theme,getCurrentSession()).show());
             getToolbar().addCommandToSideMenu("Talk with us", statsImage, e -> new DrSbaitso().start());
             getToolbar().addCommandToSideMenu("Logout", null, e -> new SignInForm_1().show());
             
             
             
             // spacer
             getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
           
             getToolbar().addComponentToSideMenu(new Label(getCurrentSession().getUsername(), "SideCommandNoPad"));
             getToolbar().addComponentToSideMenu(new Label(getCurrentSession().getTypec(), "SideCommandSmall"));             
              getToolbar().addComponentToSideMenu(new Label(String.valueOf(getCurrentSession().getNb_diamants())+"  Diamond", "SideCommandSmall"));

             
         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }
    }

        
    protected boolean isCurrentInbox() {
        return false;
    }
    
    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
}
