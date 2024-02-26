package ssp.ba.gov.br.cadastrolinks.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Validated
@Data
@Component
@ConfigurationProperties("cadastro-urls.email")
public class EmailProperties {

	@NotNull
	private String remetente;
}
