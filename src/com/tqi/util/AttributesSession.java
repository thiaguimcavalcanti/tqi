package com.tqi.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import com.tqi.model.AppConfigurationBean;
import com.tqi.util.exceptions.AppException;

public class AttributesSession {

	public static final String MESSAGES_FILE = "com.tqi.locale.messages";
	public static final String APP_CONFIGURATION = "com.tqi.locale.messages";
	public static ResourceBundle resourceBundleMsgs;
	
	static {
        try {
            resourceBundleMsgs = ResourceBundle.getBundle(MESSAGES_FILE, Locale.getDefault());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static AppConfigurationBean getAppConfigurationSession() throws AppException {
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (context != null) {
			return (AppConfigurationBean) context.getExternalContext().getSessionMap().get(APP_CONFIGURATION);
		} else
			return null;
	}
}
