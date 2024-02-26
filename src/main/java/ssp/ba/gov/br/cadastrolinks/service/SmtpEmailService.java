package ssp.ba.gov.br.cadastrolinks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import ssp.ba.gov.br.cadastrolinks.config.EmailProperties;

@Service
public class SmtpEmailService implements EnvioEmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Autowired
	private Configuration freeMarkerConfig;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String corpo = processarTemplate(mensagem);
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom("ruan.silva@ssp.ba.gov.br");
			helper.setTo("ruan.tarciisio@gmail.com");
			helper.setSubject(mensagem.getAssunto());
			helper.setText(corpo, true);
			
			
			mailSender.send(mimeMessage);
			System.out.println(emailProperties.getRemetente());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	private String processarTemplate(Mensagem mensagem) {
		try {
			Template template = freeMarkerConfig.getTemplate(mensagem.getCorpo());
			
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
