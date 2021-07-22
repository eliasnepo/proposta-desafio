package br.com.eliasnepo.proposta.carteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.eliasnepo.proposta.cartao.Cartao;
import br.com.eliasnepo.proposta.cartao.CarteiraNome;

@Entity
public class Carteira {

	@Id
	private String id;
	
	@Enumerated(EnumType.STRING)
	private CarteiraNome wallet;
	
	@ManyToOne
	private Cartao card;

	@Deprecated
	public Carteira() {
	}
	
	public Carteira(String id, CarteiraNome wallet, Cartao card) {
		this.id = id;
		this.wallet = wallet;
		this.card = card;
	}

	public String getId() {
		return id;
	}

	public CarteiraNome getWallet() {
		return wallet;
	}

	public Cartao getCard() {
		return card;
	}
}
