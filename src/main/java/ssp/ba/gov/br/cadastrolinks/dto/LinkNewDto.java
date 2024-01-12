package ssp.ba.gov.br.cadastrolinks.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@LinkInsert
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkNewDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Length(min = 7, message = "url-invalida.")
	private String endereco;
	@NotBlank
	@Length(min = 5, max = 80, message = "Campo Obrigatório, mínimo 5 letras.")
	private String titulo;
	private String favicon;
	private String baseUrl;

	
}
