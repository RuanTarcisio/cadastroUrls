package ssp.ba.gov.br.cadastrolinks.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ssp.ba.gov.br.cadastrolinks.dto.CredentialsDTO;
import ssp.ba.gov.br.cadastrolinks.security.AccessToken;
import ssp.ba.gov.br.cadastrolinks.service.AutenticacaoService;

@RestController
@RequestMapping("/login")
public class AutenticacaoResource {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	 @PostMapping
	    public ResponseEntity<AccessToken> autheticate(@RequestBody CredentialsDTO credentials){
	        var token = autenticacaoService.authenticate(credentials.email(), credentials.password());

	        if(token == null){
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	        }

	        return ResponseEntity.ok(token);
	    }
}
