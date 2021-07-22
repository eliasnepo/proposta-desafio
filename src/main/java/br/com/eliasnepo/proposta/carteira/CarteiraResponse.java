package br.com.eliasnepo.proposta.carteira;

public class CarteiraResponse {

	private String carteira;
	private Long cartaoId;
	
	public CarteiraResponse(Carteira carteira) {
		this.carteira = carteira.getWallet();
		this.cartaoId = carteira.getCard().getId();
	}

	public String getCarteira() {
		return carteira;
	}

	public Long getCartaoId() {
		return cartaoId;
	}
}
