package ssp.ba.gov.br.cadastrolinks.domain;

import java.io.Serializable;
import java.util.Objects;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
	@Column(name = "username", nullable = false, unique = true, length = 100)
	private String email;
	@Column(name = "password", nullable = false, length = 200)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 25)
	private Role role = Role.ROLE_CLIENTE;
	@Column(name = "status_usuario")
	@Enumerated(EnumType.STRING)
	private StatusUsuarioEnum statusUsuario;

//	@CreatedDate
//	@Column(name = "data_criacao")
//	private LocalDateTime dataCriacao;
//	@LastModifiedDate
//	@Column(name = "data_modificacao")
//	private LocalDateTime dataModificacao;
//	@CreatedBy
//	@Column(name = "criado_por")
//	private String criadoPor;
//	@LastModifiedBy
//	@Column(name = "modificado_por")
//	private String modificadoPor;

	public enum Role {
		ROLE_ADMIN, ROLE_CLIENTE
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(id, usuario.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Usuario{" + "id=" + id + '}';
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