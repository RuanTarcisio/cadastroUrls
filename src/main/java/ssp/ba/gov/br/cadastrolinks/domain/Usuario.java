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
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "email", nullable = false, unique = true, length = 50)
	@Email
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 25)
	private Role role = Role.ROLE_CLIENTE;
	
	@Column(name = "status_usuario")
	@Enumerated(EnumType.STRING)
	private StatusUsuarioEnum statusUsuario;

	private String token;
	
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


	public enum Role {
		ROLE_ADMIN, ROLE_CLIENTE
	}

	
	public enum StatusUsuarioEnum {

		ATIVO("ATIVO"), PENDENTE("PENDENTE"), BLOQUEADO("BLOQUEADO"), DESATIVADO("DESATIVADO");

		private String descricao;

		private StatusUsuarioEnum(String descricao) {
			this.descricao = descricao;

		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

	}

}