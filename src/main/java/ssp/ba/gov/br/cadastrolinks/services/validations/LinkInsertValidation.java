package ssp.ba.gov.br.cadastrolinks.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ssp.ba.gov.br.cadastrolinks.domain.Link;
import ssp.ba.gov.br.cadastrolinks.dto.LinkNewDto;
import ssp.ba.gov.br.cadastrolinks.service.LinkService;
import ssp.ba.gov.br.cadastrolinks.service.validation.templ.Problem;

public class LinkInsertValidation implements ConstraintValidator<LinkInsert, LinkNewDto> {

	@Autowired
	private LinkService service;

	@Override
	public void initialize(LinkInsert ann) {
	}

	@Override
	public boolean isValid(LinkNewDto objDto, ConstraintValidatorContext context) {

		List<Problem.Field> list = new ArrayList<Problem.Field>();
		Link aux = service.buscarPorEndereco(objDto.getEndereco());
		final int tamanho = objDto.getEndereco().length();

		if (aux != null) {
			list.add(new Problem.Field("endereco", "Url já cadastrada."));
		}

		if (tamanho > 7) {
			if (!objDto.getEndereco().substring(0, 8).equalsIgnoreCase("https://")
					&& !objDto.getEndereco().substring(0, 7).equalsIgnoreCase("http://")) {
				list.add(new Problem.Field("endereco", "Dominio invalido, diferente de http:// ou https://"));
			}
		}

	/*	if (objDto.getEndereco().substring(0, 8).equalsIgnoreCase("https://")) {
			try {
				if (!isValidURL(objDto.getEndereco())) {
					list.add(new Problem.Field("endereco", "Endereco invalido"));
				}
			} catch (MalformedURLException e) {
				throw new MyMalformedURLException("endereco-invalido", e);
			}
		}*/
		if (objDto.getEndereco().substring(0, 7).equalsIgnoreCase("http://")) {
			if (!dominioUrlValido(objDto.getEndereco())) {
				list.add(new Problem.Field("endereco", "Endereco Url invalido"));
			}
		}

		for (Problem.Field e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getUserMessage()).addPropertyNode(e.getName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

	private boolean dominioUrlValido(String url) {
		return Pattern.compile("^https?://(w{3}\\.)?([a-z-0-9]{2,26}\\.)"
				+ "+[a-z-0-9]{2,26}(:(0|[1-9]|[1-5]?[0-9]{2,4}|6[1-4][0-9]{3}|65[1-4][0-9]{2}|655[1-2][0-9]|6553[1-5]))"
				+ "?(//?([a-zA-Z0-9_.:=&\\\"'?%+-@_#$!]{2,}))*/?$", Pattern.CASE_INSENSITIVE).matcher(url).matches();
	}

	/*public boolean isValidURL(String url) throws MalformedURLException {
		UrlValidator validator = new UrlValidator();
		return validator.isValid(url);
	}*/

}
