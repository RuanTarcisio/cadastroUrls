package ssp.ba.gov.br.cadastrolinks.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ssp.ba.gov.br.cadastrolinks.domain.Cliente;
import ssp.ba.gov.br.cadastrolinks.dto.UsuarioNewDto;
import ssp.ba.gov.br.cadastrolinks.service.ClienteService;
import ssp.ba.gov.br.cadastrolinks.service.validation.templ.Problem;
import ssp.ba.gov.br.cadastrolinks.util.BR;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDto> {

	@Autowired
	private ClienteService clienteService;

	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioNewDto objDto, ConstraintValidatorContext context) {
		List<Problem.Field> list = new ArrayList<>();

		if (!BR.isValidCPF(objDto.getCpf())) {
			list.add(new Problem.Field("cpf", "CPF invalido"));
		}

		Cliente aux = clienteService.buscarPorEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new Problem.Field("email", "E-mail ja cadastrado."));
		}

//		aux = clienteService.buscarPorCpf(objDto.getCpf());
//		if (aux != null) {
//			list.add(new Problem.Field("cpf", "CPF ja cadastrado."));
//		}
//		aux = clienteService.buscarPorMatricula(objDto.getMatricula());
//		if (aux != null) {
//			list.add(new Problem.Field("matricula", "matricula ja cadastrada."));
//		}

		if (objDto.getCelular() != null && !Pattern.compile("^[0-9]{11}$").matcher(objDto.getCelular()).matches()) {
			list.add(new Problem.Field("celular", "{usuario.padrao-celular.incorreto}"));
		}
		if (objDto.getMatricula() != null && !Pattern.compile("^[0-9]{8}$").matcher(objDto.getMatricula()).matches()) {
			list.add(new Problem.Field("matricula", "{usuario.padrao-matricula.incorreta}"));
		}
		

		for (Problem.Field e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getUserMessage()).addPropertyNode(e.getName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}