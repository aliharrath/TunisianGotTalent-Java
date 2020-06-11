/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author loume78
 */
public class fos_user {

    private Integer id;

    private String username;

    private String usernameCanonical;

    private String email;

    private String emailCanonical;

    private boolean enabled;

    private String password;

    private String roles;

    private String typec;

    private int nb_diamants;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getTypec() {
        return typec;
    }

    public void setTypec(String typec) {
        this.typec = typec;
    }

    public int getNb_diamants() {
        return nb_diamants;
    }

    public void setNb_diamants(int nb_diamants) {
        this.nb_diamants = nb_diamants;
    }

    @Override
    public String toString() {
        return "fos_user{" + "id=" + id + ", username=" + username + ", usernameCanonical=" + usernameCanonical + ", email=" + email + ", emailCanonical=" + emailCanonical + ", enabled=" + enabled + ", password=" + password + ", roles=" + roles + ", typec=" + typec + ", nb_diamants=" + nb_diamants + '}';
    }

    public fos_user() {
    }

    public fos_user(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String password, String roles, String typec, int nb_diamants) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
        this.typec = typec;
        this.nb_diamants = nb_diamants;
    }
    
    
    
    
    
    

}
