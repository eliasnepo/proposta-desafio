package br.com.eliasnepo.proposta.propostas;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.eliasnepo.proposta.validation.CPForCNPJ;
import br.com.eliasnepo.proposta.validation.UniqueValue;

public class PropostaRequest {

	@CPForCNPJ
	@NotBlank
	private String document;
	@NotBlank @Email @UniqueValue(domainClass = Proposta.class, fieldName = "email")
	private String email;
	@NotBlank
	private String name;
	@NotBlank
	private String address;
	@NotNull @Positive
	private BigDecimal salary;
	
	public PropostaRequest(String document, String email, String name, String address, BigDecimal salary) {
		this.document = document;
		this.email = email;
		this.name = name;
		this.address = address;
		this.salary = salary;
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

	public Proposta toModel() {
		return new Proposta(this.document, this.email, this.name, this.address, this.salary);
	}
}
