package com.gft.casadeeventos.services.exceptions;

public class CasaNaoEncontradaException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6914769072363619062L;

	public CasaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CasaNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
