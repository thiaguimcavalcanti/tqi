package com.tqi.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.tqi.util.EnumMessages;

public abstract class CommonController {

	protected final Logger logger = Logger.getLogger( getClass() );
	
	public static void createViewMessage(EnumMessages enumMessage) {
		FacesMessage message = new FacesMessage(enumMessage.getSeverity(), null, enumMessage.getKey());  
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
