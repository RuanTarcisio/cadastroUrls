package ssp.ba.gov.br.cadastrolinks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ssp.ba.gov.br.cadastrolinks.domain.Link;
import ssp.ba.gov.br.cadastrolinks.repository.LinkRepository;
import ssp.ba.gov.br.cadastrolinks.service.exception.EntityNotFoundException;

@Service
public class LinkService {

	@Autowired
	private LinkRepository linkRepository;

	@Transactional
	public Link buscarPorId(Long id) {
		return linkRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Link id=%s não encontrado no sistema", id)));
	}

	public List<Link> findAll() {

		return linkRepository.findAll();
	}

	public Link salvarUrl(Link newLink) {
		
		return linkRepository.save(newLink);
	}
	
	public Link buscarPorEndereco(String endereco) {
		Optional<Link> obj = linkRepository.findByEndereco(endereco);
		return obj.orElse(null);
	}

}
