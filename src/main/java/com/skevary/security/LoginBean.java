package com.skevary.security;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 9007171658473182460L;

    private static final String[] users = {"skev@mail.com:123456", "mary@mail.org:123456"};

    @NotNull(message = "Email may not be null.")
    @Size(min = 3, max = 254, message = "Email length must be between 3 and 254.")
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", message = "Email format is invalid.")
    private String email;

    @NotNull(message = "Password may not be null.")
    @Size(min = 3, max = 254, message = "Password length must be between 3 and 254.")
    private String password;

    private boolean loggedIn;

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

    public String doLogin() {
        for (String user : users) {
            String dbEmail = user.split(":")[0];
            String dbPassword = user.split(":")[1];

            if (dbEmail.equalsIgnoreCase(email) && dbPassword.equals(password)) {
                loggedIn = true;
                return navigationBean.redirectToIndex1();
            }
        }

        // Set login ERROR
        FacesMessage msg = new FacesMessage("Login error!", "There is a problem with your email address or password. Please try again.");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        // To do login page
        return navigationBean.toLogin();
    }

    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;

        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "You have successfully logged out from your account.");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return navigationBean.redirectToLogin();
    }

    public void signUp() {
        FacesMessage msg = new FacesMessage("Sign up - Error!", "This functionality is not yet supported.");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

}
