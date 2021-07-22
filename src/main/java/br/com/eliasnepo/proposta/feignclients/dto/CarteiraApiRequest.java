package br.com.eliasnepo.proposta.feignclients.dto;

public class CarteiraApiRequest {

	private String email;
	private String carteira;

	@Deprecated
	public CarteiraApiRequest() {
	}
	
	public CarteiraApiRequest(String email, String carteira) {
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
