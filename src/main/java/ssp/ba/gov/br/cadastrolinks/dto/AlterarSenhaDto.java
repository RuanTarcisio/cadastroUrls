package ssp.ba.gov.br.cadastrolinks.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AlterarSenhaDto {
	

	@NotEmpty
	@NotNull
	private String senhaAntiga;
	
	@NotEmpty
	@NotNull
	@Size(min= 6, max = 50)
//	@Senha
	private String novaSenha;
	
	private String confirmaNovaSenha;
	
}
