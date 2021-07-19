package br.com.eliasnepo.proposta.propostas;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.eliasnepo.proposta.feignclients.dto.ApiAnaliseResponseStatus;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String document;
	
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private BigDecimal salary;

	@Enumerated(EnumType.STRING)
	private PropostaStatus status;
	
	@Deprecated
	public Proposta() { }

	public Proposta(String document, String email, String name, String address, BigDecimal salary) {
		this.document = document;
		this.email = email;
		this.name = name;
		this.address = address;
		this.salary = salary;
		this.status = PropostaStatus.EM_ANALISE;
	}

	public Long getId() {
		return id;
	}

	public String getDocument() {
		return document;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public PropostaStatus getStatus() {
		return status;
	}

	public void setStatus(ApiAnaliseResponseStatus status) {
		this.status = status.toModel();
	}
}
