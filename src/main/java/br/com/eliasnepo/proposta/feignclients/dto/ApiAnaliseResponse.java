package br.com.eliasnepo.proposta.feignclients.dto;

public class ApiAnaliseResponse {

	private ApiAnaliseResponseStatus resultadoSolicitacao;
	
	public ApiAnaliseResponse() {
	}

	public ApiAnaliseResponse(ApiAnaliseResponseStatus resultadoSolicitacao) {
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public ApiAnaliseResponseStatus getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
}
