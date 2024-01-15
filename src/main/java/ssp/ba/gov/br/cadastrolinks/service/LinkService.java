package ssp.ba.gov.br.cadastrolinks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ssp.ba.gov.br.cadastrolinks.domain.Link;
import ssp.ba.gov.br.cadastrolinks.service.exception.EntityNotFoundException;

@Service
public class LinkService {

	@Autowired
	private LinkRepository repository;

	@Transactional
	public Link buscarPorId(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Link id=%s não encontrado no sistema", id)));
	}

	public List<Link> findAll() {

		return repository.findAll();
	}

	public Link salvarUrl(Link newLink) {
		
		return repository.save(newLink);
	}

}
