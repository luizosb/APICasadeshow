package com.gft.casadeeventos.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gft.casadeeventos.model.DetalhesErro;
import com.gft.casadeeventos.services.exceptions.CasaNaoEncontradaException;
import com.gft.casadeeventos.services.exceptions.EventoExistenteException;
import com.gft.casadeeventos.services.exceptions.EventoNaoEncontradoException;
import com.gft.casadeeventos.services.exceptions.MensagemErroException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(CasaNaoEncontradaException.class)
	public ResponseEntity<DetalhesErro> handleCasaNaoEncontradaException(CasaNaoEncontradaException e, 
																	HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Casa não pode ser encontrada!");
		erro.setMensagemDesenvolvedor("http://erros.casa.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(EventoExistenteException.class)
	public ResponseEntity<DetalhesErro> handleEventoExistenteException(EventoExistenteException e, 
																	HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O evento já existe!");
		erro.setMensagemDesenvolvedor("http://erros.evento.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(EventoNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleEventoNaoEncontradoException(EventoNaoEncontradoException e, 
																	HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O evento não existe!");
		erro.setMensagemDesenvolvedor("http://erros.casa.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e, 
																	HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Requisição inválida.");
		erro.setMensagemDesenvolvedor("http://erros.casa.com/400");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	 @ExceptionHandler(MensagemErroException.class)
	    public ResponseEntity<DetalhesErro> handleMensagemErroException(MensagemErroException e, 
	                HttpServletRequest request) {
		 
	        DetalhesErro erro = new DetalhesErro();
	        erro.setStatus(500l);
	        erro.setTitulo("Faltou algum dado para preencher.");
	        erro.setMensagemDesenvolvedor("http://erros.casa.com/500");
	        erro.setTimestamp(System.currentTimeMillis());
	        
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	    }
}

