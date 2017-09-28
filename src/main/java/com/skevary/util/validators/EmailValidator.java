package com.skevary.util.validators;

import com.skevary.util.Message;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("util.validators.EmailValidator")
public class EmailValidator implements Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;
    private Matcher matcher;

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }


    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        emailNotNullValidation(value);
        emailSizeValidation(value.toString());
        emailPatternValidation(value.toString());

    }


    private void emailNotNullValidation(Object value){
       if(value == null)
           Message.showValidationMessage(Message.getString("mail.not_null.summary"),
                   Message.getString("mail.not_null.detail"), FacesMessage.SEVERITY_ERROR);
    }


    private void emailSizeValidation(String value){
        if(value.length() < 3 || value.length() > 254)
            Message.showValidationMessage(Message.getString("mail.size.summary"),
                    Message.getString("mail.size.detail"), FacesMessage.SEVERITY_ERROR);
    }


    private void emailPatternValidation(String value){
        matcher = pattern.matcher(value);

        if (!matcher.matches())
            Message.showValidationMessage(Message.getString("mail.pattern.summary"),
                    Message.getString("mail.pattern.detail"), FacesMessage.SEVERITY_ERROR);
    }


}
