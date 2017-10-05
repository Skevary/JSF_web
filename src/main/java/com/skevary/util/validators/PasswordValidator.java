package com.skevary.util.validators;

import com.skevary.util.Message;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("util.validators.PasswordValidator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        passNotNullValidation(value);
        passSizeValidation(value.toString());
    }


    private void passNotNullValidation(Object value) {
        if (value == null)
            Message.showValidationMessage("password.not_null.summary", "password.not_null.detail", FacesMessage.SEVERITY_ERROR);
    }


    private void passSizeValidation(String value) {
        if (value.length() < 3 || value.length() > 254)
            Message.showValidationMessage("password.size.summary", "password.size.detail", FacesMessage.SEVERITY_ERROR);
    }

}
