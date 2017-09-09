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
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 9007171658473182460L;
    private static final ResourceBundle message = ResourceBundle.getBundle("messages", Locale.getDefault());

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

    private static final Map<String, String> users;

    static {
        users = new HashMap<>();
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
        if ((users.get(email) != null) && (users.get(email).equals(password))) {
            loggedIn = true;

            return navigationBean.redirectToIndex1();
        }

        showMessage("message.login.error.summary", "message.login.error.detail");
        return navigationBean.toLogin();
    }

    public String doLogout() {
        loggedIn = false;
        showMessage("message.logout.success.summary", "message.logout.success.detail");

        return navigationBean.redirectToLogin();
    }

    public String signUp() {
        if (users.get(email) != null) {
            showMessage("message.sign_up.error.summary", "message.sign_up.error.detail");

            return navigationBean.toLogin();
        } else {
            users.put(email, password);
            loggedIn = true;
            showMessage("message.sign_up.success.summary", "message.sign_up.success.detail");

            return navigationBean.redirectToIndex1();
        }
    }

    private void showMessage(String summary, String detail) {
        FacesContext facesContext = getCurrentInstance();
        Flash flash = getCurrentInstance().getExternalContext().getFlash();
        flash.setKeepMessages(true);

        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message.getString(summary), message.getString(detail)));
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