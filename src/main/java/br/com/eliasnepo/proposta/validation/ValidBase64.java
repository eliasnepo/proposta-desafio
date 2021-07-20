package br.com.eliasnepo.proposta.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ValidBase64Validator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBase64 {
	String message() default "Não está no formato Base64.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
