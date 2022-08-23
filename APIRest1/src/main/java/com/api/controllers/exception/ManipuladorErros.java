package com.api.controllers.exception;

import java.time.Instant;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.services.exceptions.EmailValidoException;
import com.api.services.exceptions.OperacaoNaoPermitidaException;
import com.api.services.exceptions.TelefoneException;

@ControllerAdvice   //para transformar em uma classe que 	uando acontecer erro no service, ele vai captar erro e mudar a natureza do retorno do erro
public class ManipuladorErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErroPadrao> entidadeNaoEncontrada(EntityNotFoundException e, HttpServletRequest req) {
		ErroPadrao erro = new ErroPadrao();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		//erro.setError("Recurso nao encontrado");
		erro.setMessage(e.getMessage());
		//erro.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(OperacaoNaoPermitidaException.class)
	public ResponseEntity<ErroPadrao> minhaExcecao(OperacaoNaoPermitidaException e, HttpServletRequest req) {
		ErroPadrao erro = new ErroPadrao();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		//erro.setError("Sem conteudo");
		erro.setMessage(e.getMessage());
		//erro.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(erro);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ErroPadrao> minhaExcecao(EmptyResultDataAccessException e, HttpServletRequest req) {
		ErroPadrao erro = new ErroPadrao();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		//erro.setError("Recurso nao existe");
		erro.setMessage(e.getMessage());
		//erro.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(erro);
	}
	
	@ExceptionHandler(EmailValidoException.class)
	public ResponseEntity<ErroPadrao> emailInvalido(EmailValidoException e, HttpServletRequest req) {
		ErroPadrao erro = new ErroPadrao(Instant.now(),HttpStatus.BAD_REQUEST.value(),"Formato invalido");
//		erro.setTimestamp(Instant.now());
//		erro.setStatus(HttpStatus.BAD_REQUEST.value());
//		erro.setError("Formato invalido");
//		erro.setMessage(e.getMessage());
//		erro.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroPadrao> minhaExcecao(MethodArgumentNotValidException e, HttpServletRequest req) {
		ErroPadrao erro = new ErroPadrao(Instant.now(),HttpStatus.BAD_REQUEST.value(),e.getLocalizedMessage());
//		erro.setTimestamp(Instant.now());
//		erro.setStatus(HttpStatus.BAD_REQUEST.value());
//		erro.setError("Formato invalido");
//		erro.setMessage(e.getMessage());
//		erro.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	
}
