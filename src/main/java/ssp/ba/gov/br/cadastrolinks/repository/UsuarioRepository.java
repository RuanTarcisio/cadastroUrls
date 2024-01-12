package ssp.ba.gov.br.cadastrolinks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ssp.ba.gov.br.cadastrolinks.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
