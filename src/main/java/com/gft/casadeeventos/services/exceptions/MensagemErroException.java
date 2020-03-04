package com.gft.casadeeventos.services.exceptions;

public class MensagemErroException extends RuntimeException{

	
	private static final long serialVersionUID = -6914769072363653212L;

	public MensagemErroException(String mensagem) {
		super(mensagem);
	}
	
	public MensagemErroException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
