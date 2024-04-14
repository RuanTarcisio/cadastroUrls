package ssp.ba.gov.br.cadastrolinks.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import ssp.ba.gov.br.cadastrolinks.domain.Cliente;
import ssp.ba.gov.br.cadastrolinks.domain.Usuario;
import ssp.ba.gov.br.cadastrolinks.domain.Usuario.StatusUsuarioEnum;
import ssp.ba.gov.br.cadastrolinks.repository.ClienteRepository;
import ssp.ba.gov.br.cadastrolinks.service.EnvioEmailService.Mensagem;
import ssp.ba.gov.br.cadastrolinks.service.exception.EntityNotFoundException;
import ssp.ba.gov.br.cadastrolinks.service.exception.UsuarioNaoEncontradoException;
import ssp.ba.gov.br.cadastrolinks.util.Constantes;

@SuppressWarnings("unused")
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PasswordEncoder passEncoder;
	@Autowired
	private EnvioEmailService emailService;
	@Value("${baseUrl}")
	private String baseUrl;

	public Cliente buscarPorCpf(String cpf) {
		Optional<Cliente> obj = clienteRepository.findByCpf(cpf);
		return obj.orElseThrow(() -> new UsuarioNaoEncontradoException(Constantes.USUARIO_NAO_ENCONTRADO));
	}

	public Cliente buscarPorEmail(String email) {
		Optional<Cliente> obj = clienteRepository.findByUsuarioEmail(email);
		return obj.orElseThrow(() -> new UsuarioNaoEncontradoException(Constantes.USUARIO_NAO_ENCONTRADO));
	}

	public Cliente buscarPorMatricula(String matricula) {
		Optional<Cliente> obj = clienteRepository.findByMatricula(matricula);
		return obj.orElseThrow(() -> new UsuarioNaoEncontradoException(Constantes.USUARIO_NAO_ENCONTRADO));
	}

	@Transactional
	public Cliente cadastrar(@Valid Cliente newCliente) {
		newCliente.getUsuario().setToken(generateToken());
//		encodePass(newCliente.getUsuario());

		newCliente = clienteRepository.save(newCliente);

		var mensagem = Mensagem.builder().assunto("testando envio de email").corpo("ativar-usuario.html")
				.variavel("cliente", newCliente).variavel("url", baseUrl)
				.destinatario(newCliente.getUsuario().getEmail()).build();

		emailService.enviar(mensagem);

		return newCliente;
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
		aAtualizar.setCpf(cliente.getCpf());
	}

	private String generateToken() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

//	private void atualizarUsuario(Cliente cliente) {
//		Usuario user = userRepository.findById(cliente.getId()).get();
//		user = cliente.getUsuario();
//
//	}

	private void encodePass(Usuario user) {
		var passNotEncod = user.getPassword();
		var encodPass = passEncoder.encode(passNotEncod);
		user.setPassword(encodPass);

	}

}
