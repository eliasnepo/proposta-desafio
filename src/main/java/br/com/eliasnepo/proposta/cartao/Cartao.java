package br.com.eliasnepo.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.eliasnepo.proposta.biometria.Biometria;
import br.com.eliasnepo.proposta.bloqueio.Bloqueio;
import br.com.eliasnepo.proposta.bloqueio.BloqueioStatus;
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
	
	@OneToMany(mappedBy = "card")
	private Set<Biometria> biometrias = new HashSet<>();
	
	@Enumerated(EnumType.STRING)
	private BloqueioStatus status;
	
	@Deprecated
	public Cartao() {
	}

	public Cartao(String number, LocalDateTime createdAt, String owner, BigDecimal creditLimit, Proposta proposta) {
		this.number = number;
		this.createdAt = createdAt;
		this.owner = owner;
		this.creditLimit = creditLimit;
		this.proposta = proposta;
		this.status = BloqueioStatus.NAO_BLOQUEADO;
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

	public BloqueioStatus getStatus() {
		return status;
	}

	public Bloqueio block(String ip, String userAgent) {
		this.status = BloqueioStatus.BLOQUEADO;
		return new Bloqueio(ip, userAgent, this);
	}
}
