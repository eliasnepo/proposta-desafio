package br.com.eliasnepo.proposta.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = IsValidEnumValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidEnum {
	String message() default "Não é um tipo de pagamento reconhecido.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
