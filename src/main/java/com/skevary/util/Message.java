package com.skevary.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.validator.ValidatorException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javax.faces.context.FacesContext.getCurrentInstance;

public interface Message {

    /**
     * Display notifications.
     * Method adds message to the {@link FacesContext}.
     *
     * @param summary - Localized summary message text
     * @param detail - Localized detail message text
     *
     * @see FacesMessage
     */
    static void showMessage(String summary, String detail, FacesMessage.Severity severity) throws ValidatorException {
        String sum = getString(summary);
        String det = getString(detail);

        FacesContext facesContext = getCurrentInstance();

        FacesMessage msg = new FacesMessage(severity,sum, det);

        facesContext.addMessage(null, msg);
    }


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
    static void showFlashMessage(String summary, String detail, FacesMessage.Severity severity) throws ValidatorException {
        String sum = getString(summary);
        String det = getString(detail);

        FacesContext facesContext = getCurrentInstance();

        Flash flash = getCurrentInstance().getExternalContext().getFlash();
        flash.setKeepMessages(true);

        FacesMessage msg = new FacesMessage(severity,sum, det);

        facesContext.addMessage(null, msg);
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


    /**
     * Construct a new exception with the specified message and no root cause.
     *
     * @param summary - Localized summary message text
     * @param detail - Localized detail message text
     *
     * @see ResourceBundle
     */
    static void showValidationMessage(String summary, String detail){
        String sum = getString(summary);
        String det = getString(detail);

        FacesMessage msg = new FacesMessage(sum, det);
        throw new ValidatorException(msg);
    }


    /**
     * Construct a new exception with the specified message and no root cause.
     *
     * @param summary - Localized summary message text
     * @param detail - Localized detail message text
     * @param severity -Class used to represent message severity levels in a typesafe enumeration.
     *
     * @see FacesMessage.Severity
     * @see ResourceBundle
     */
    static void showValidationMessage(String summary, String detail, FacesMessage.Severity severity) throws ValidatorException {
        String sum = getString(summary);
        String det = getString(detail);

        FacesMessage msg = new FacesMessage(severity,sum, det);

        throw new ValidatorException(msg);
    }
}
