package ssp.ba.gov.br.cadastrolinks.emailTemplates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import ssp.ba.gov.br.cadastrolinks.domain.Cliente;

@Component
public class MailToUsuario {
	
	@Value("${baseUrl}")
	private String baseUrl;

	
	@Autowired
	private TemplateEngine templateEngine;

	public String emailParaAtivarUsuarioDto(Cliente cliente) {
		
		Context context = new Context();
		context.setVariable("url", baseUrl + "new-password?q=" + cliente.getUsuario().getToken());
		context.setVariable("nome", cliente.getNome().split(" ")[0]);
		return templateEngine.process("ativar-usuario", context);
	}
	
	public String emailParaEsqueceuSenhaDto(Cliente cliente) {

		Context context = new Context();
		context.setVariable("url", baseUrl + "recover-password?q=" + cliente.getUsuario().getToken());
		context.setVariable("nome", cliente.getNome().split(" ")[0]);
		return templateEngine.process("recuperar-senha", context);
	}

}
