package ssp.ba.gov.br.cadastrolinks.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EsqueceuSenhaDto {

	@NotNull
	private String email;
}
