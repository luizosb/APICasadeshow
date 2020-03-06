package com.gft.casadeeventos.services.exceptions;

public class SucessoException extends RuntimeException{

	
	private static final long serialVersionUID = -6914769072363653212L;

	public SucessoException(String mensagem) {
		super(mensagem);
	}
	
	public SucessoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
