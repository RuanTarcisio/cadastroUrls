package ssp.ba.gov.br.cadastrolinks.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "clientes")
@EntityListeners(AuditingEntityListener.class)
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    @CPF
    private String cpf;
    
    @Column(unique = true)
	private String matricula;
    
    @NotNull
	private String celular;


}
