package ssp.ba.gov.br.cadastrolinks.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ssp.ba.gov.br.cadastrolinks.domain.Cliente;
import ssp.ba.gov.br.cadastrolinks.dto.UsuarioNewDto;
import ssp.ba.gov.br.cadastrolinks.repository.ClienteRepository;
import ssp.ba.gov.br.cadastrolinks.service.ClienteService;
import ssp.ba.gov.br.cadastrolinks.service.validation.templ.Problem;
import ssp.ba.gov.br.cadastrolinks.util.BR;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDto> {

	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioNewDto objDto, ConstraintValidatorContext context) {
		List<Problem.Field> list = new ArrayList<>();

		if (!BR.isValidCPF(objDto.getCpf())) {
			list.add(new Problem.Field("cpf", "CPF invalido"));
		}

	
		if (!clienteRepository.findByUsuarioEmail(objDto.getEmail()).isEmpty()) {
			list.add(new Problem.Field("email", "E-mail ja cadastrado."));
		}

		if (!clienteRepository.findByCpf(objDto.getCpf()).isEmpty()) {
			list.add(new Problem.Field("cpf", "CPF ja cadastrado."));
		}
		
		if (!clienteRepository.findByMatricula(objDto.getMatricula()).isEmpty()) {
			list.add(new Problem.Field("matricula", "matricula ja cadastrada."));
		}

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