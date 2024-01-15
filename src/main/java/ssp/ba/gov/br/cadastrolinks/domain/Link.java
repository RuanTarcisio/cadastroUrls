package ssp.ba.gov.br.cadastrolinks.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Link implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_url")
	private Long id;
	@Column(unique = true)
	@NotBlank
	private String endereco;
	@NotBlank
	private String titulo;
	
	private String favicon;
	
	 @CreatedDate
	    @Column(name = "data_criacao")
	    private LocalDateTime dataCriacao;
	    @LastModifiedDate
	    @Column(name = "data_modificacao")
	    private LocalDateTime dataModificacao;
	    @CreatedBy
	    @Column(name = "criado_por")
	    private String criadoPor;
	    @LastModifiedBy
	    @Column(name = "modificado_por")
	    private String modificadoPor;

	public Link(String endereco, String titulo) {
		this.endereco = endereco;
		this.titulo = titulo;
	}

	public Link(Long id, String endereco, String titulo) {
		super();
		this.id = id;
		this.endereco = endereco;
		this.titulo = titulo;
	}
	
	

}
