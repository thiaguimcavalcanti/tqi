package com.tqi.locale;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tqi.util.AttributesSession;

@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Locale locale = Locale.getDefault();
    
    public LocaleBean() {
    	setLanguage();
    }
    
    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage() {
        AttributesSession.resourceBundleMsgs = ResourceBundle.getBundle(AttributesSession.MESSAGES_FILE, locale);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
