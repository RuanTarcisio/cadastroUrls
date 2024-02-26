package ssp.ba.gov.br.cadastrolinks.domain.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ssp.ba.gov.br.cadastrolinks.domain.Cliente;
import ssp.ba.gov.br.cadastrolinks.domain.Usuario;
import ssp.ba.gov.br.cadastrolinks.dto.UsuarioNewDto;
import ssp.ba.gov.br.cadastrolinks.dto.UsuarioUpdateDto;
import ssp.ba.gov.br.cadastrolinks.dto.UsuarioViewDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteConverter {
	
	private final static String senha_inicial = "#ALTERAR_SENHA_INICIAL#";

	public static Cliente toCliente(UsuarioNewDto dto) {

		Usuario user = new Usuario();
		user.setEmail(dto.getEmail());
		user.setPassword(senha_inicial);
				
		return Cliente.builder()
				.nome(dto.getNome())
				.matricula(dto.getMatricula())
				.cpf(dto.getCpf())
				.celular(dto.getCelular())
				.usuario(user)
				.build();

	}
	
	public static Cliente toCliente(UsuarioUpdateDto dto, Long id) {

		Usuario user = new Usuario();
		user.setEmail(dto.getEmail());
				
		return Cliente.builder()
				.nome(dto.getNome())
				.cpf(dto.getCpf())
				.celular(dto.getCelular())
				.usuario(user)
				.build();

	}

	public static UsuarioViewDto toDto(Cliente cliente) {

		return UsuarioViewDto.builder()
				.email(cliente.getUsuario().getEmail())
				.cpf(cliente.getCpf())
				.nome(cliente.getNome())
				.celular(cliente.getCelular())
				.id(cliente.getId())
				.matricula(cliente.getMatricula())
				.statusUsuario(cliente.getUsuario().getStatusUsuario().getDescricao())
				.build();

	}
}
