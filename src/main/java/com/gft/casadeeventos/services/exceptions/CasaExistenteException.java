package com.gft.casadeeventos.services.exceptions;

public class CasaExistenteException extends RuntimeException{

	
	private static final long serialVersionUID = -6914769072363653212L;

	public CasaExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public CasaExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
