/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.entites.sponsor;

import java.util.Date;


/**
 *
 * @author Rawia
 */
public class Mail {
    private int id;
    private String mailto;
    private String subject;
    private String object;
    private String mailfrom;
    private Date time;
    private String file;
    private int idsp;
    private int idu;

    public Mail() {
    }

    public Mail(int id, String mailto, String subject, String object, String mailfrom, Date time,String file, int idsp, int idu) {
        this.id = id;
        this.mailto = mailto;
        this.subject = subject;
        this.object = object;
        this.mailfrom = mailfrom;
        this.time = time;
        this.file=file;
        this.idsp = idsp;
        this.idu = idu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMailto() {
        return mailto;
    }

    public void setMailto(String mailto) {
        this.mailto = mailto;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getMailfrom() {
        return mailfrom;
    }

    public void setMailfrom(String mailfrom) {
        this.mailfrom = mailfrom;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    @Override
    public String toString() {
        return "Mail{" + "id=" + id + ", mailto=" + mailto + ", subject=" + subject + ", object=" + object + ", mailfrom=" + mailfrom + ", time=" + time + ", idsp=" + idsp + ", idu=" + idu + '}';
    }
    
    
}
