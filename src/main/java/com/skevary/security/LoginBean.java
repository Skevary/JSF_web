package com.skevary.security;

import com.skevary.util.Message;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@ManagedBean
@SessionScoped
public class LoginBean implements Serializable, Message {
    private static final long serialVersionUID = 9007171658473182460L;

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;
    //TODO: fix authorization;
    private static final Map<String, String> users;
    //TODO: fix validation;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /* USERS - initialization */
    static {
        users = new HashMap<>();
        users.put("foo@mail.com", "12345");
        users.put("bar@mail.com", "12345");
    }

    /* EMAIL - validation */
    private String email;

    /* PASSWORD - validation */
    private String password;

    /* LOGGED IN - the authorization flag */
    private boolean loggedIn;

    public String doLogin() {
        if ((users.get(email) != null) && (users.get(email).equals(password))) {
            loggedIn = true;

            return navigationBean.redirectToIndex1();
        }

        Message.showMessage("message.login.error.summary", "message.login.error.detail");
        return navigationBean.toLogin();
    }

    public String doLogout() {
        loggedIn = false;
        Message.showMessage("message.logout.success.summary", "message.logout.success.detail");

        return navigationBean.redirectToLogin();
    }

    public String signUp() {
        if (users.get(email) != null) {
            Message.showMessage("message.sign_up.error.summary", "message.sign_up.error.detail");

            return navigationBean.toLogin();
        } else {
            users.put(email, password);
            loggedIn = true;
            Message.showMessage("message.sign_up.success.summary", "message.sign_up.success.detail");

            return navigationBean.redirectToIndex1();
        }
    }

    @NotNull(message = "{message.validate.mail.not_null}")
    @Size(min = 3, max = 254, message = "{message.validate.mail.    size}")
    @Pattern(regexp = EMAIL_PATTERN, message = "{message.validate.mail.pattern}")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "{message.validate.password.not_null}")
    @Size(min = 3, max = 254, message = "{message.validate.password.size}")
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