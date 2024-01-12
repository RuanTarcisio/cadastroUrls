package ssp.ba.gov.br.cadastrolinks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ssp.ba.gov.br.cadastrolinks.domain.Cliente;
import ssp.ba.gov.br.cadastrolinks.domain.Usuario;
import ssp.ba.gov.br.cadastrolinks.domain.Usuario.StatusUsuarioEnum;
import ssp.ba.gov.br.cadastrolinks.repository.ClienteRepository;
import ssp.ba.gov.br.cadastrolinks.repository.UsuarioRepository;
import ssp.ba.gov.br.cadastrolinks.service.exception.EntityNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente cadastrar(Cliente newUser, String email) {
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setPassword("ATUALIZAR SENHA");
		usuario.setStatusUsuario(StatusUsuarioEnum.BLOQUEADO);
		userRepository.save(usuario);

		newUser.setUsuario(usuario);
		

		return clienteRepository.save(newUser);
	}

	public List<Cliente> findAll() {
		
		return clienteRepository.findAll();
	}

	@Transactional
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cliente id=%s não encontrado no sistema", id))
        );
    }

}
