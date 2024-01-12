package ssp.ba.gov.br.cadastrolinks.domain.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ssp.ba.gov.br.cadastrolinks.domain.Cliente;
import ssp.ba.gov.br.cadastrolinks.dto.UsuarioNewDto;
import ssp.ba.gov.br.cadastrolinks.dto.UsuarioViewDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteConverter {

	public static Cliente toCliente(UsuarioNewDto dto) {

		return Cliente.builder().nome(dto.getNome()).cpf(dto.getCpf()).matricula(dto.getMatricula())
				.celular(dto.getCelular()).build();

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
