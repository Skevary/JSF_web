package com.skevary.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.util.Locale;
import java.util.ResourceBundle;

import static javax.faces.context.FacesContext.getCurrentInstance;

public interface Message {

    static void showMessage(String summary, String detail) {
        ResourceBundle message = ResourceBundle.getBundle("messages", Locale.getDefault());
        FacesContext facesContext = getCurrentInstance();
        Flash flash = getCurrentInstance().getExternalContext().getFlash();
        flash.setKeepMessages(true);

        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message.getString(summary), message.getString(detail)));
    }

    static String getString(String key) {
        ResourceBundle message = ResourceBundle.getBundle("messages", Locale.getDefault());

        return message.getString(key);
    }
}
