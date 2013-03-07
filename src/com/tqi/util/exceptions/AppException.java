package com.tqi.util.exceptions;

import com.tqi.util.EnumMessages;

public class AppException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private EnumMessages enumMessage = EnumMessages.ERRO_INESPERADO;

	public AppException() {
		
	}
	
	public AppException(EnumMessages message) {
		this.enumMessage = message;
	}
	
	@Override
	public String getMessage() {
		return this.enumMessage.getKey();
	}
	
	public EnumMessages getEnumMessage() {
		return enumMessage;
	}
	
	public void setEnumMessage(EnumMessages enumMessage) {
		this.enumMessage = enumMessage;
	}
}
