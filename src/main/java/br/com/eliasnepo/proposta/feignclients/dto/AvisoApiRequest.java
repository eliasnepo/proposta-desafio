package br.com.eliasnepo.proposta.feignclients.dto;

import java.time.LocalDate;

public class AvisoApiRequest {

	private String destino;
	private LocalDate validoAte;

	@Deprecated
	public AvisoApiRequest() {
	}
	
	public AvisoApiRequest(String destino, LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public LocalDate getValidoAte() {
		return validoAte;
	}
}
