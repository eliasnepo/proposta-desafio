package br.com.eliasnepo.proposta.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidationError error = new ValidationError(
				status.value(), 
				"Erro de validação.", 
				"Tente inserir um formato válido de autor de acordo com base nos erros abaixo.", 
				request.getRequestURI(), 
				LocalDateTime.now());
		
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			error.addError(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(error);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(DocumentException.class)
	public ResponseEntity<StandardError> documentValidation(DocumentException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError error = new StandardError(
				LocalDateTime.now(),
				status.value(), 
				"Erro no campo documento.", 
				e.getMessage(), 
				request.getRequestURI() 
				);
		
		return ResponseEntity.status(status).body(error);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(
				LocalDateTime.now(),
				status.value(), 
				"Erro ao encontrar entidade.", 
				e.getMessage(), 
				request.getRequestURI() 
				);
		
		return ResponseEntity.status(status).body(error);
	}
}
