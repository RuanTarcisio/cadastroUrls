package ssp.ba.gov.br.cadastrolinks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ssp.ba.gov.br.cadastrolinks.domain.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {

	@Transactional(readOnly = true)
	Optional<Link> findByEndereco(String endereco);

}
