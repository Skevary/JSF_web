package com.skevary.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@ManagedBean(eager = true)
@SessionScoped
public class LocalizationBean implements Serializable {
    private static final long serialVersionUID = -3811608161111348357L;

    private static Map<String, Locale> languages;
    private String locale;

    static {
        languages = new LinkedHashMap<>();
        languages.put("English", Locale.ENGLISH);
        languages.put("Russian", new Locale("ru"));
    }

    public Map<String, Locale> getLanguages() {
        return languages;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void localeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();
        for (Map.Entry<String, Locale> entry : languages.entrySet()) {
            if (entry.getValue().toString().equals(newLocaleValue)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale(entry.getValue());
                Locale.setDefault(entry.getValue());
            }
        }
    }
}