package br.com.eliasnepo.proposta.avisos;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.eliasnepo.proposta.cartao.Cartao;

public class AvisoRequest {

	@NotBlank
	private String destiny;
	@NotNull @Future
	private LocalDate endDate;
	
	public AvisoRequest(String destiny, LocalDate endDate) {
		this.destiny = destiny;
		this.endDate = endDate;
	}
	
	public String getDestiny() {
		return destiny;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}

	public Aviso toModel(String ip, String userAgent, Cartao cartao) {
		return new Aviso(this.destiny, this.endDate, ip, userAgent, cartao);
	}
}
