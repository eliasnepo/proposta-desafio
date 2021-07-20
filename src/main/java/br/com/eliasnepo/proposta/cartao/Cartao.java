package br.com.eliasnepo.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.eliasnepo.proposta.propostas.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String number;
	@Column(nullable = false)
	private LocalDateTime createdAt;
	@Column(nullable = false)
	private String owner;
	@Column(nullable = false)
	private BigDecimal creditLimit;
	
	@OneToOne(mappedBy = "cartao")
	private Proposta proposta;
	
	@Deprecated
	public Cartao() {
	}

	public Cartao(String number, LocalDateTime createdAt, String owner, BigDecimal creditLimit, Proposta proposta) {
		super();
		this.number = number;
		this.createdAt = createdAt;
		this.owner = owner;
		this.creditLimit = creditLimit;
		this.proposta = proposta;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getOwner() {
		return owner;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public Proposta getProposta() {
		return proposta;
	}
}