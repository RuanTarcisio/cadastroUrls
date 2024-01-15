package ssp.ba.gov.br.cadastrolinks.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import ssp.ba.gov.br.cadastrolinks.domain.Link;
import ssp.ba.gov.br.cadastrolinks.domain.converter.LinkConverter;
import ssp.ba.gov.br.cadastrolinks.dto.LinkNewDto;
import ssp.ba.gov.br.cadastrolinks.service.LinkService;

@RestController
@RequestMapping(value = "urls")
public class LinkResource {

	@Autowired
	private LinkService service;

	@Operation(summary = "Listar url por Id", method = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Url não encontrada") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<Link> find(@PathVariable Long id) {

		Link obj = service.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@Operation(summary = "Listar todas urls", method = "GET")
	@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
	@GetMapping(value = "/listar")
	public ResponseEntity<List<Link>> findAll() {

		List<Link> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Cadastro de nova url", method = "POST")
	@ApiResponses (value = {
			 @ApiResponse(responseCode = "400", description = "Dado informado invalido."),
			 @ApiResponse(responseCode = "201", description = "Url cadastrada com sucesso.") })       
	@PostMapping(value = "/inserir")
	public ResponseEntity<Void> insert(@Valid @RequestBody LinkNewDto objDto) {

		Link newLink = LinkConverter.toLink(objDto);
		newLink = service.salvarUrl(newLink);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newLink.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
