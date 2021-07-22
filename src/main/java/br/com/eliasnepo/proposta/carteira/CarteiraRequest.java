package br.com.eliasnepo.proposta.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraRequest {

	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String carteira;

	@Deprecated
	public CarteiraRequest() {
	}
	
	public CarteiraRequest(String email, String carteira) {
		this.email = email;
		this.carteira = carteira;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getCarteira() {
		return carteira;
	}
}
