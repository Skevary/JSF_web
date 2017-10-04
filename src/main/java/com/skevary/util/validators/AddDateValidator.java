package com.skevary.util.validators;

import com.skevary.model.DataBean;
import com.skevary.service.DataService;
import com.skevary.util.Message;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

@FacesValidator("util.validators.AddDateValidator")
public class AddDateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Date date = (Date) value;
        notNullValidation(date);
        containsDateValidation(date);
    }

    private void notNullValidation(Date date) {
        if (date == null)
            Message.showValidationMessage("message.add_data.not_null.summary", "message.add_data.not_null.detail", FacesMessage.SEVERITY_ERROR);
    }

    private void containsDateValidation(Date date) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        DataService service = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{dataService}", DataService.class);
        for (DataBean bean : service.getDataBeans())
            if (bean.getDate().equals(date)) {
                Message.showValidationMessage("message.add_data.already_exists.summary",
                        "message.add_data.already_exists.detail", FacesMessage.SEVERITY_ERROR);
            }
    }
}
