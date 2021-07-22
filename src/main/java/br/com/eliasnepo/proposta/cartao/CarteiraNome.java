package br.com.eliasnepo.proposta.cartao;

public enum CarteiraNome {

	PAYPAL, SAMSUNG_PAY;
	
	public static CarteiraNome converter(String nome) {
		return CarteiraNome.valueOf(nome);
	}
}
