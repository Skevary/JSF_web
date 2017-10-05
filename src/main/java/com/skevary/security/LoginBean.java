package com.skevary.security;

import com.skevary.util.Message;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.xml.bind.ValidationException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 9007171658473182460L;
    private static final Map<String, String> users;
    static {
        users = new HashMap<>();
        users.put("foo@mail.com", "12345");
        users.put("bar@mail.com", "12345");
    }

    private String email;
    private String password;
    private boolean loggedIn;

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

    public String doLogin() throws ValidationException {
        if ((users.get(email) != null) && (users.get(email).equals(password))) {
            loggedIn = true;

            return navigationBean.redirectToIndex1();
        }

        Message.showMessage("message.login.error.summary", "message.login.error.detail", FacesMessage.SEVERITY_ERROR);
        return navigationBean.toLogin();
    }


    public String doLogout() {
        loggedIn = false;
        Message.showFlashMessage("message.logout.success.summary", "message.logout.success.detail", FacesMessage.SEVERITY_INFO);
        return navigationBean.redirectToLogin();
    }


    public String signUp() {
        if (users.get(email) != null) {
            Message.showMessage("message.sign_up.error.summary", "message.sign_up.error.detail", FacesMessage.SEVERITY_ERROR);

            return navigationBean.toLogin();
        } else {
            users.put(email, password);
            loggedIn = true;
            Message.showFlashMessage("message.sign_up.success.summary", "message.sign_up.success.detail", FacesMessage.SEVERITY_INFO);

            return navigationBean.redirectToIndex1();
        }
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
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