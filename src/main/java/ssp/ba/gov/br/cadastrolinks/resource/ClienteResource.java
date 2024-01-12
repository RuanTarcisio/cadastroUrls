package ssp.ba.gov.br.cadastrolinks.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import ssp.ba.gov.br.cadastrolinks.domain.Cliente;
import ssp.ba.gov.br.cadastrolinks.domain.converter.ClienteConverter;
import ssp.ba.gov.br.cadastrolinks.dto.UsuarioNewDto;
import ssp.ba.gov.br.cadastrolinks.dto.UsuarioViewDto;
import ssp.ba.gov.br.cadastrolinks.service.ClienteService;



@RestController
@RequestMapping("/usuarios")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@PostMapping(value = "/cadastrar")
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioNewDto objDto) {

		Cliente newUser = ClienteConverter.toCliente(objDto);
		newUser = service.cadastrar(newUser, objDto.getEmail());

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}
	
	@Operation(summary = "Busca Usuario por ID")
	@ApiResponses(value = { @ApiResponse() })
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioViewDto> find(@PathVariable Long id) {
		Cliente cliente = service.buscarPorId(id);
		UsuarioViewDto objDto = ClienteConverter.toDto(cliente);
		return ResponseEntity.ok().body(objDto);
	}
	
	@Operation(summary = "Lista todos Usuarios")
	@ApiResponses(value = { @ApiResponse() })
	
	@GetMapping(value = "/listar")
	public ResponseEntity<List<UsuarioViewDto>> findAll() {
		List<Cliente> list = service.findAll();
		List<UsuarioViewDto> listDto = list.stream()
				.map(cliente -> ClienteConverter.toDto(cliente))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}
	


}
