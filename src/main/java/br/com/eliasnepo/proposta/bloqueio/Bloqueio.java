package br.com.eliasnepo.proposta.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.eliasnepo.proposta.cartao.Cartao;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime instant;
	private String ip;
	private String userAgent;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;
	
	public Bloqueio(String ip, String userAgent, Cartao cartao) {
		this.ip = ip;
		this.userAgent = userAgent;
		this.cartao = cartao;
		this.instant = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getInstant() {
		return instant;
	}

	public String getIp() {
		return ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public Cartao getCartao() {
		return cartao;
	}
}
