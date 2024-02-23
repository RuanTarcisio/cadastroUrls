package ssp.ba.gov.br.cadastrolinks.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ssp.ba.gov.br.cadastrolinks.domain.Usuario;
import ssp.ba.gov.br.cadastrolinks.repository.UsuarioRepository;

public class TokenSenhaValidator implements ConstraintValidator<TokenSenha, String> {
	
	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		List<String> messages = new ArrayList<>();

//		Optional<Usuario> user = repository.findByToken(value);
//		if (user.isPresent()) {
//			messages.add("token-ja-cadastrado");
//
//		}

		for (String message : messages) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		}

		return messages.isEmpty();
	}
}
