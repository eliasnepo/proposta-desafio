package br.com.eliasnepo.proposta.validation;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.eliasnepo.proposta.exceptions.FieldMessage;

public class ValidBase64Validator implements ConstraintValidator<ValidBase64, String> {

	private String errorMessage;

	@Override
	public void initialize(ValidBase64 ann) {
		errorMessage = ann.message();
	}

	@Override
	@Transactional
	public boolean isValid(String valueOfBiometry, ConstraintValidatorContext context) {
		Decoder decoder = Base64.getDecoder();
		List<FieldMessage> list = new ArrayList<>();
		boolean isValid = false;

		try {
			decoder.decode(valueOfBiometry);
			isValid = true;
		} catch (IllegalArgumentException e) {
			list.add(new FieldMessage("biometry", errorMessage));
		}

		return isValid;
	}
}
