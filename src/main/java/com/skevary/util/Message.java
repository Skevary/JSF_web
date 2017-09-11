package com.skevary.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.util.Locale;
import java.util.ResourceBundle;

import static javax.faces.context.FacesContext.getCurrentInstance;

public interface Message {
    /**
     * Display notifications.
     * Method adds message to the {@link FacesContext} & flash memory.
     *
     * @param summary - Localized summary message text
     * @param detail - Localized detail message text
     *
     * @see Flash
     * @see FacesMessage
     */
    static void showMessage(String summary, String detail) {
        ResourceBundle message = ResourceBundle.getBundle("messages", Locale.getDefault());
        FacesContext facesContext = getCurrentInstance();
        Flash flash = getCurrentInstance().getExternalContext().getFlash();
        flash.setKeepMessages(true);

        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message.getString(summary), message.getString(detail)));
    }

    /**
     * Gets a string for the given key from this resource bundle or one of its parents.
     *
     * @param key  - the key for the desired string
     * @return the string for the given key
     *
     * @see ResourceBundle
     */
    static String getString(String key) {
        ResourceBundle message = ResourceBundle.getBundle("messages", Locale.getDefault());

        return message.getString(key);
    }
}
