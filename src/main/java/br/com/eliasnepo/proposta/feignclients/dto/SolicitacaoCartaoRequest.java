package br.com.eliasnepo.proposta.feignclients.dto;

import javax.validation.constraints.NotBlank;

public class SolicitacaoCartaoRequest {

	@NotBlank
	private String documento;
	@NotBlank
	private String nome;
	@NotBlank
	private String idProposta;
	
	public SolicitacaoCartaoRequest(String documento, String nome, String idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}
}
