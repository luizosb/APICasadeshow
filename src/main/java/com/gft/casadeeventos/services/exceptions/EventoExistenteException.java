package com.gft.casadeeventos.services.exceptions;

public class EventoExistenteException extends RuntimeException{

	
	private static final long serialVersionUID = -6914769072363653212L;

	public EventoExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public EventoExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
