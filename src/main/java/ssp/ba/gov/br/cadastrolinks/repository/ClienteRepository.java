package ssp.ba.gov.br.cadastrolinks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ssp.ba.gov.br.cadastrolinks.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
