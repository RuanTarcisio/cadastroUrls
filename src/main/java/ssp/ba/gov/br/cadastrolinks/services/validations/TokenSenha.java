package ssp.ba.gov.br.cadastrolinks.services.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = TokenSenhaValidator.class)
@Target({ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface TokenSenha {
	
	String message() default "{validacao.erro}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
