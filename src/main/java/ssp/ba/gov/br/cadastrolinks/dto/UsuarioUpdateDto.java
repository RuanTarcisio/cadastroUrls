package ssp.ba.gov.br.cadastrolinks.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateDto {

	@NotNull
	@Email(message = "e-mail invalido")
	private String email;

	@NotNull
	private String nome;

	private String celular;
	private String cpf;
}
