package ssp.ba.gov.br.cadastrolinks.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ResetPasswordDto {

	@NotEmpty
	@NotNull
	@Size(min= 6, max = 50)
//	@Senha
	private String password;

	@NotEmpty
	@NotNull
	@Size(max = 255)
	private String token;

	public void setPassword(String password) {
		this.password = password;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
