package ssp.ba.gov.br.cadastrolinks.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
//@LinkUpdate
public class LinkUpdateDto {

	@Length(min = 5, max = 80, message = "Campo Obrigatório, mínimo 5 letras.")
	private String titulo;
	@NotEmpty
	private String endereco;
	private String favicon;
	private String baseUrl;
}
