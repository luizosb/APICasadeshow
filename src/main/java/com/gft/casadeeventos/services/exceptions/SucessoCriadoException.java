package com.gft.casadeeventos.services.exceptions;

public class SucessoCriadoException extends RuntimeException{

	
	private static final long serialVersionUID = -6914769072363653212L;

	public SucessoCriadoException(String mensagem) {
		super(mensagem);
	}
	
	public SucessoCriadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
