package com.skevary.security;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {

    private static final long serialVersionUID = -3472799378008130650L;

    public String redirectToLogin() {
        return "/pages/login.xhtml?faces-redirect=true";
    }

    public String toLogin() {
        return "/pages/login.xhtml";
    }

    public String redirectToIndex1() {
        return "/pages/secured/index1.xhtml?faces-redirect=true";
    }

    public String toIndex1() {
        return "/pages/secured/index1.xhtml";
    }

    public String redirectToIndex2() {
        return "/pages/secured/index2.xhtml?faces-redirect=true";
    }

    public String toIndex2() {
        return "/pages/secured/index2.xhtml";
    }
}
