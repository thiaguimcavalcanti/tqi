package com.tqi.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public enum EnumMessages {

	// ERROS
	ERRO_INESPERADO (FacesMessage.SEVERITY_FATAL, "error.1"),
	ERRO_SAVE (FacesMessage.SEVERITY_FATAL, "error.2"),
	ERRO_UPDATE (FacesMessage.SEVERITY_FATAL, "error.3"),
	ERRO_FIND_ALL (FacesMessage.SEVERITY_FATAL, "error.4"),
	ERRO_FIND_ONE (FacesMessage.SEVERITY_FATAL, "error.5"),
	ERRO_DELETE (FacesMessage.SEVERITY_FATAL, "error.6"),
	ERRO_DELETE_BY_ID (FacesMessage.SEVERITY_FATAL, "error.7"),
	ERRO_FIND_BY_NAME (FacesMessage.SEVERITY_FATAL, "error.8"),
	ERRO_GENERIC (FacesMessage.SEVERITY_FATAL, "error.9"),
	ERRO_DUPLICATE_SAVE (FacesMessage.SEVERITY_FATAL, "error.10"),
	ERRO_GET_VOTES_RESULT (FacesMessage.SEVERITY_FATAL, "error.11"),
	
	// INFOS
	INFO_NENHUMA_INFORMACAO_ENCONTRADA_DO_LINK (FacesMessage.SEVERITY_WARN, "info.1"),
	
	// SUCESSOS
	SUCESSO_VOTE (FacesMessage.SEVERITY_INFO, "success.1"),
	
	// ALERTAS
	ALERTA_NENHUM_INFORMACAO_ENCONTRADA (FacesMessage.SEVERITY_ERROR, "warn.1"),;
	
	private Severity severity;
	private String key;
	
	private EnumMessages(Severity severity, String key) {
		this.severity = severity;
		this.key = key;
	}
	
	public Severity getSeverity() {
		return severity;
	}
	
	public String getKey() {
		return key;
	}
}
