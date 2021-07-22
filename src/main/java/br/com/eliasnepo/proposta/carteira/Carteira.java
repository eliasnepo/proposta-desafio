package br.com.eliasnepo.proposta.carteira;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.eliasnepo.proposta.cartao.Cartao;

@Entity
public class Carteira {

	@Id
	private String id;
	private String wallet;
	
	@ManyToOne
	private Cartao card;

	@Deprecated
	public Carteira() {
	}
	
	public Carteira(String id, String wallet, Cartao card) {
		this.id = id;
		this.wallet = wallet;
		this.card = card;
	}

	public String getId() {
		return id;
	}

	public String getWallet() {
		return wallet;
	}

	public Cartao getCard() {
		return card;
	}
}
