package ssp.ba.gov.br.cadastrolinks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ssp.ba.gov.br.cadastrolinks.security.AccessToken;
import ssp.ba.gov.br.cadastrolinks.security.JwtService;

@Service
public class AutenticacaoService {
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private PasswordEncoder passEncoder;
	@Autowired
	private JwtService jwtService;

	public AccessToken authenticate(String email, String password) {

		var cliente = clienteService.buscarPorEmail(email);
	
		if(passEncoder.matches(password, cliente.getUsuario().getPassword())) {
			return jwtService.generateToken(cliente);
		}
		
		return null;
	}

}
