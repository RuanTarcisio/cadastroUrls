package ssp.ba.gov.br.cadastrolinks.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RedefinirSenhaDto {
	
	private String token;

	@NotEmpty
	@NotNull
	@Size(min= 6, max = 50)
//	@Senha
	private String senha;
	
	@NotEmpty
	@NotNull
	@Size(min= 6, max = 50)
//	@Senha
	private String confirmaSenha;
	
}
