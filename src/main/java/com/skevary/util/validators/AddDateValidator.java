package com.skevary.util.validators;

import com.skevary.model.DataBean;
import com.skevary.util.Message;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("util.validators.AddDateValidator")
public class AddDateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        DataBean bean = (DataBean) value;
        notNullValidation(bean);
        containsDateValidation(bean);


    }

    private void notNullValidation(DataBean data) {
        if (data.getDate() == null)
            Message.showMessage("message.add_data.not_null.summary", "message.add_data.not_null.detail");
    }

    private void containsDateValidation(DataBean data) {
        if (data.getDate() == null)
            Message.showMessage("message.add_data.not_null.summary", "message.add_data.not_null.detail");
    }
}
