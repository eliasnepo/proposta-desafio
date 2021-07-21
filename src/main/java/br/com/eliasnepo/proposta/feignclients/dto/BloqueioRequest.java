package br.com.eliasnepo.proposta.feignclients.dto;

public class BloqueioRequest {

	private String sistemaResponsavel;

	public BloqueioRequest() {
	}
	
	public BloqueioRequest(String sistemaResponsavel) {
		super();
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
}
