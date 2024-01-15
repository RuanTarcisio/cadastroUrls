package ssp.ba.gov.br.cadastrolinks.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ssp.ba.gov.br.cadastrolinks.domain.Cliente;
import ssp.ba.gov.br.cadastrolinks.domain.Usuario;
import ssp.ba.gov.br.cadastrolinks.domain.Usuario.StatusUsuarioEnum;
import ssp.ba.gov.br.cadastrolinks.repository.ClienteRepository;
import ssp.ba.gov.br.cadastrolinks.repository.UsuarioRepository;
import ssp.ba.gov.br.cadastrolinks.service.exception.EntityNotFoundException;

@SuppressWarnings("unused")
@Service
public class ClienteService {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente cadastrar(Cliente newCliente) {
		newCliente.getUsuario().setToken(generateToken());
		Usuario user = userRepository.save(newCliente.getUsuario());
		newCliente.setUsuario(user);

		return clienteRepository.save(newCliente);
	}

	public List<Cliente> findAll() {

		return clienteRepository.findAll();
	}

	@Transactional
	public Cliente buscarPorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Cliente id=%s não encontrado no sistema", id)));
	}

	public void deleteUsuario(Long id) {

		Cliente aDeletar = buscarPorId(id);
		aDeletar.getUsuario().setStatusUsuario(StatusUsuarioEnum.DESATIVADO);

	}

	@Transactional
	public Cliente update(Cliente cliente) {

		Cliente aAtualizar = buscarPorId(cliente.getId());

		updateData(aAtualizar, cliente);

		/*
		 * if (UsuarioUtils.getUsuarioLogado().getId() != newUsuario.getId()) {
		 * saveLogControl(newUsuario, modificationEnum.UPDATE,
		 * Constantes.ATUALIZA_USUARIO); }
		 */

		return aAtualizar;
	}

	private void updateData(Cliente aAtualizar, Cliente cliente) {

		aAtualizar.getUsuario().setEmail(cliente.getUsuario().getEmail());
		aAtualizar.setNome(cliente.getNome());
		aAtualizar.setCelular(cliente.getCelular());
		aAtualizar.getUsuario().setCpf(cliente.getUsuario().getEmail());
	}

	private String generateToken() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	private void atualizarUsuario(Cliente cliente) {
		Usuario user = userRepository.findById(cliente.getId()).get();
		user = cliente.getUsuario();

	}

}
