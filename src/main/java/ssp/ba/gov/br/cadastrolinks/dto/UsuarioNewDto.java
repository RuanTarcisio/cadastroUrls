package ssp.ba.gov.br.cadastrolinks.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ssp.ba.gov.br.cadastrolinks.services.validations.UsuarioInsert;

//@UsuarioInsert
@Data
@AllArgsConstructor
@NoArgsConstructor
@UsuarioInsert
public class UsuarioNewDto {

	@NotEmpty
	private String nome;
	@NotEmpty
	@Email(message = "email invalido")
	private String email;
	@NotEmpty
	private String cpf;
	@NotEmpty
	@Size(min = 8, max = 8, message = "tamanho invalido da matricula.")
	private String matricula;
	@NotNull
	@NotEmpty
	private String celular;


}
