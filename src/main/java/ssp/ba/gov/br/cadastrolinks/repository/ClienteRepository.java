package ssp.ba.gov.br.cadastrolinks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ssp.ba.gov.br.cadastrolinks.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	 @Query("from Cliente c inner join Usuario u on u.email = %:email% and c.id = u.id ")
    Optional<Cliente> findByEmail(String email);
	 
	 Optional<Cliente> findByMatricula(String matricula	);
	 
	 Optional<Cliente> findByCpf(String cpf);
	
	

}
