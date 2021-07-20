package br.com.eliasnepo.proposta.propostas;

import java.math.BigDecimal;

public class PropostaResponse {

	private Long id;
	private String document;
	private String email;
	private String name;
	private String address;
	private BigDecimal salary;
	private PropostaStatus status;
	
	public PropostaResponse(Proposta entity) {
		this.id = entity.getId();
		this.document = entity.getDocument();
		this.email = entity.getEmail();
		this.name = entity.getName();
		this.address = entity.getAddress();
		this.salary = entity.getSalary();
		this.status = entity.getStatus();
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
}
