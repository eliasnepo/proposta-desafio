package br.com.eliasnepo.proposta.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	public ValidationError(Integer status, String error, String message, String path, LocalDateTime timestamp) {
		super(timestamp, status, error, message, path);
	}

	private List<FieldMessage> errors = new ArrayList<>();

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
}