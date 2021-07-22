package br.com.eliasnepo.proposta.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraRequest {

	@Email
	@NotBlank
	private String email;

	@Deprecated
	public CarteiraRequest() {
	}
	
	public CarteiraRequest(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
}
