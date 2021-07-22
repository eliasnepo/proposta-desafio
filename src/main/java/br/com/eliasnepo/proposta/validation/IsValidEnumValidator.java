package br.com.eliasnepo.proposta.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.eliasnepo.proposta.cartao.CarteiraNome;
import br.com.eliasnepo.proposta.exceptions.FieldMessage;

public class IsValidEnumValidator implements ConstraintValidator<IsValidEnum, String> {

	private String errorMessage;

	@Override
	public void initialize(IsValidEnum ann) {
		errorMessage = ann.message();
	}

	@Override
	@Transactional
	public boolean isValid(String valueOfString, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (!Arrays.stream(CarteiraNome.class.getEnumConstants()).anyMatch(e -> e.name().equals(valueOfString))) {
			list.add(new FieldMessage("carteira", errorMessage));
		}
		
		return Arrays.stream(CarteiraNome.class.getEnumConstants()).anyMatch(e -> e.name().equals(valueOfString));
	}
}
