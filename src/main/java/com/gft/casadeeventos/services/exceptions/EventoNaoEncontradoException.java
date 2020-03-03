package com.gft.casadeeventos.services.exceptions;

public class EventoNaoEncontradoException extends RuntimeException{

	
	private static final long serialVersionUID = -6914769072363653212L;

	public EventoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EventoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
