package br.com.eliasnepo.proposta.feignclients.dto;

public class CarteiraApiResponse {

	private String resultado;
	private String id;
	
	public CarteiraApiResponse() {
	}

	public CarteiraApiResponse(String resultado, String id) {
		this.resultado = resultado;
		this.id = id;
	}

	public String getResultado() {
		return resultado;
	}

	public String getId() {
		return id;
	}
}
