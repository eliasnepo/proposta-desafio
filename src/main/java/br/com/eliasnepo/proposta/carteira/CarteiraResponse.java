package br.com.eliasnepo.proposta.carteira;

import br.com.eliasnepo.proposta.cartao.CarteiraNome;

public class CarteiraResponse {

	private CarteiraNome carteira;
	private Long cartaoId;
	
	public CarteiraResponse(Carteira carteira) {
		this.carteira = carteira.getWallet();
		this.cartaoId = carteira.getCard().getId();
	}

	public CarteiraNome getCarteira() {
		return carteira;
	}

	public Long getCartaoId() {
		return cartaoId;
	}
}
