package br.com.eliasnepo.proposta.biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.eliasnepo.proposta.cartao.Cartao;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String biometry;
	private LocalDateTime createdAt;
	
	@ManyToOne
	private Cartao card;

	@Deprecated
	public Biometria() {
	}

	public Biometria(String biometry, Cartao card) {
		this.biometry = biometry;
		this.createdAt = LocalDateTime.now();
		this.card = card;
	}

	public Long getId() {
		return id;
	}

	public String getBiometry() {
		return biometry;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Cartao getCard() {
		return card;
	}
}
