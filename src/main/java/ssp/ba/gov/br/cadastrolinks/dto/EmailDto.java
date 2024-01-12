package ssp.ba.gov.br.cadastrolinks.dto;

public class EmailDto {

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.replaceAll("(?<=.{3}).(?=.*@)", "*");
	}

}
