package com.tqi.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.tqi.model.AppConfigurationBean;
import com.tqi.service.AppConfigurationService;
import com.tqi.util.AttributesSession;
import com.tqi.util.EnumMessages;
import com.tqi.util.exceptions.AppException;

public abstract class CommonController {

	protected final Logger logger = Logger.getLogger( getClass() );
	
	@ManagedProperty(value="#{appConfigurationService}")
	private AppConfigurationService appConfigurationService;
	
	/**
	 * Cria uma mensagem para apresentar ao usuario
	 * 
	 * @param enumMessage
	 */
	public static void createViewMessage(EnumMessages enumMessage) {
		FacesMessage message = new FacesMessage(enumMessage.getSeverity(), AttributesSession.resourceBundleMsgs.getString(enumMessage.getKey()), null);  
        FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	/**
	 * Busca as configuracoes da aplicacao
	 * 
	 * @return
	 * @throws AppException
	 */
	public AppConfigurationBean getAppConfig() throws AppException {
		
		AppConfigurationBean appConfig = AttributesSession.getAppConfigurationSession();
		
		if (appConfig != null) {
			return appConfig;
		} else {
			appConfig = appConfigurationService.findByName(AppConfigurationBean.APP_NAME);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AttributesSession.APP_CONFIGURATION, appConfig);
			return appConfig;
		}
		
	}
	
	public AppConfigurationService getAppConfigurationService() {
		return appConfigurationService;
	}
	
	public void setAppConfigurationService(AppConfigurationService appConfigurationService) {
		this.appConfigurationService = appConfigurationService;
	}
}
