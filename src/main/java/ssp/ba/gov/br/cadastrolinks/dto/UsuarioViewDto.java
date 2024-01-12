package ssp.ba.gov.br.cadastrolinks.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioViewDto {

	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String celular;
	private String matricula;
	private String statusUsuario;

}
