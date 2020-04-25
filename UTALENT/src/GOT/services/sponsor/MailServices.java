/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.services.sponsor;

import GOT.entites.sponsor.Mail;
import GOT.utils.MyConnection;
import GOT.utils.SessionUser;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Properties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.mail.*;
import javax.mail.internet.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javafx.beans.property.SimpleStringProperty;
import javax.activation.FileDataSource;

//#e44d3a
/**
 *
 * @author Rawia
 */
public class MailServices {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;

    public MailServices() {
        cnx = MyConnection.getInstance().getCnx();
    }

    // for example, smtp.mailgun.org
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USERNAME = "rawiakhart@gmail.com";
    private static final String PASSWORD = "23271959";

    private static final String EMAIL_FROM = "rawiakhart@gmail.com";
    private static String EMAIL_TO = "rawiakhart@gmail.com";
    private static final String EMAIL_TO_CC = "";

    private static String EMAIL_SUBJECT = "Test Send Email via SMTP";
    private static String EMAIL_TEXT = "Hello Java Mail \n ABC123";

    public boolean mail(String mailto, String objet, String text, String file) {
        boolean resp = false;
        EMAIL_TO = mailto;
        EMAIL_SUBJECT = objet;
        EMAIL_TEXT = text;
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587"); // default port 25
        prop.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {

            // from
            msg.setFrom(new InternetAddress(EMAIL_FROM));

            // to 
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

            // cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

            // subject
            msg.setSubject(EMAIL_SUBJECT);

            // content 
            // 
            String req="";
            if (file.isEmpty()) {
                msg.setText(EMAIL_TEXT);
                msg.setSentDate(new Date());

                // Get SMTPTransport
                SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

                // connect
                t.connect(SMTP_SERVER, USERNAME, PASSWORD);

                // send
                t.sendMessage(msg, msg.getAllRecipients());

                System.out.println("Response: " + t.getLastServerResponse());

                t.close();

                String pattern = "yyyy-MM-dd HH:mm";

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());
                //     file = file.replaceAll("\\", "/");
                //`id`, `mailto`, `subject`, `object`, `mailfrom`, `time`, `idsp`, `idu`

                req = "INSERT INTO mail VALUES(null, null, " + SessionUser.getUser().getId() + " , '" + EMAIL_TO + "' , '" + EMAIL_SUBJECT + "' ,  '" + EMAIL_TEXT + "', '" + EMAIL_FROM + "', '" + date + "',null)";

            } else {
                MimeBodyPart p1 = new MimeBodyPart();
                p1.setText(EMAIL_TEXT);

                MimeBodyPart p2 = new MimeBodyPart();

                FileDataSource fds = new FileDataSource(file);
                p2.setDataHandler(new DataHandler(fds));
                p2.setFileName(fds.getName());

                Multipart mp = new MimeMultipart();
                mp.addBodyPart(p1);
                mp.addBodyPart(p2);
                msg.setContent(mp);

                msg.setSentDate(new Date());

                // Get SMTPTransport
                SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

                // connect
                t.connect(SMTP_SERVER, USERNAME, PASSWORD);

                // send
                t.sendMessage(msg, msg.getAllRecipients());

                System.out.println("Response: " + t.getLastServerResponse());

                t.close();

                String pattern = "yyyy-MM-dd HH:mm";

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());
                //     file = file.replaceAll("\\", "/");
                //`id`, `mailto`, `subject`, `object`, `mailfrom`, `time`, `idsp`, `idu`

                req = "INSERT INTO mail VALUES(null, null, " + SessionUser.getUser().getId() + " , '" + EMAIL_TO + "' , '" + EMAIL_SUBJECT + "' ,  '" + EMAIL_TEXT + "', '" + EMAIL_FROM + "', '" + date + "','" + file + "')";
            }
            System.out.println("    " + req);
            st = cnx.createStatement();
            st.executeUpdate(req);
            resp = true;

        } catch (Exception e) {
            e.printStackTrace();
            resp = false;
        }
        return resp;
    }

    public String dateConv(Date d) {
        String pattern = "yyyy-MM-dd s";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(d);
        return date;

    }

    public Mail show(int id) {
//        String pattern = "yyyy-MM-dd HH:mm";
//        String d1;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        String date = simpleDateFormat.format(new Date());
//        System.out.println(""+date);

        Mail m = new Mail();
        try {

            String req = "SELECT * FROM mail order by time desc";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {

                //  m.setIdsp(res.getInt(2));
                //   m.setIdu(res.getInt(3));
                m.setMailto(res.getString(4));
                m.setSubject(res.getString(5));
                m.setObject(res.getString(6));
                m.setMailfrom(res.getString(7));
                m.setTime(res.getDate(8));
                m.setFile(res.getString(9));
            }
        } catch (SQLException ex) {
            System.out.println("this" + ex);
        }
        return m;
    }

    public List<Mail> inbox() {

        List<Mail> listM = new ArrayList<>();
        try {

            String req = "SELECT * FROM mail order by time desc";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
           
            while (res.next()) {

                Mail m = new Mail();
                m.setId(res.getInt(1));
                //  m.setIdsp(res.getInt(2));
                //   m.setIdu(res.getInt(3));
                m.setMailto(res.getString(4));
                m.setSubject(res.getString(5));
                m.setObject(res.getString(6));
                m.setMailfrom(res.getString(7));
                m.setTime(res.getDate(8));
                m.setFile(res.getString(9));

                listM.add(m);
            }

        } catch (SQLException ex) {
            System.out.println("here" + ex);
        }
        return listM;
    }

}
