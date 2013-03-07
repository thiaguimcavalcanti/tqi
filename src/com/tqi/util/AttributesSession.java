package com.tqi.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class AttributesSession {

	public static final String MESSAGES_FILE = "com.tqi.locale.messages";
	public static ResourceBundle resourceBundleMsgs;
	
	static {
        try {
            resourceBundleMsgs = ResourceBundle.getBundle(MESSAGES_FILE, Locale.getDefault());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
