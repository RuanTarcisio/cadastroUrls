package ssp.ba.gov.br.cadastrolinks.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhaValidator implements ConstraintValidator<Senha, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		List<String> messages = new ArrayList<>();

		if (value != null) {
			if (!Pattern.compile("[0-9]").matcher(value).find()) {
				messages.add("{senha.numero.conter-pelo-menos-um}");
			}

			if (!Pattern.compile("[a-zA-Z]").matcher(value).find()) {
				messages.add("{senha.alfabetico.conter-pelo-menos-um}");
			}

		}

		for (String message : messages) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		}

		return messages.isEmpty();
	}
}
