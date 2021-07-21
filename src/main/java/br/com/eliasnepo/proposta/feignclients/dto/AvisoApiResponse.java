package br.com.eliasnepo.proposta.feignclients.dto;

public class AvisoApiResponse {

	private String resultado;

	@Deprecated
	public AvisoApiResponse() {
	}

	public AvisoApiResponse(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}
}
