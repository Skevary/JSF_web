package com.skevary.security;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 9007171658473182460L;

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

    private static final Map<String,String> users;
    static
    {
        users = new HashMap<String, String>();
        users.put("foo@mail.com", "12345");
        users.put("bar@mail.com", "12345");
    }

    @NotNull(message = "Email may not be null.")
    @Size(min = 3, max = 254, message = "Email length must be between 3 and 254.")
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", message = "Email format is invalid.")
    private String email;

    @NotNull(message = "Password may not be null.")
    @Size(min = 3, max = 254, message = "Password length must be between 3 and 254.")
    private String password;

    private boolean loggedIn;

    public String doLogin() {
        if(users.get(email)!=null)
            if(users.get(email).equals(password)){
                loggedIn = true;
                return navigationBean.redirectToIndex1();
            }


        // Set login ERROR
        FacesMessage msg = new FacesMessage("Login error!", "There is a problem with your email address or password. Please try again.");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        getCurrentInstance().addMessage(null, msg);

        // To do login page
        return navigationBean.toLogin();
    }

    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;

        // Set logout message
        FacesContext facesContext = getCurrentInstance();
        Flash flash = getCurrentInstance().getExternalContext().getFlash();
        flash.setKeepMessages(true);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Logout success!", "You have successfully logged out from your account."));

        return navigationBean.redirectToLogin();
    }

    public String signUp() {
        FacesContext facesContext = getCurrentInstance();
        Flash flash = getCurrentInstance().getExternalContext().getFlash();

        flash.setKeepMessages(true);
        if(users.get(email)!=null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Entered email - is busy!", "Please enter valid email."));

            return navigationBean.toLogin();
        } else {
            users.put(email, password);
            loggedIn = true;
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sign up success!", "Welcome, you have successfully registered."));
            return navigationBean.redirectToIndex1();
        }
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