package br.com.eliasnepo.proposta.feignclients.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.eliasnepo.proposta.cartao.Cartao;
import br.com.eliasnepo.proposta.propostas.Proposta;

public class SolicitacaoCartaoResponse {

	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;
	private String idProposta;
	
	public SolicitacaoCartaoResponse() { }
	
	public SolicitacaoCartaoResponse(String id, LocalDateTime emitidoEm, String titular, BigDecimal limite,
			String idProposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.idProposta = idProposta;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public String getIdProposta() {
		return idProposta;
	}
	
	public Cartao toModel(Proposta proposta) {
//		Proposta proposta = repository.findById(Long.valueOf(idProposta)).orElseThrow(() -> new ResourceNotFoundException("Não existe proposta com esse id."));
		return new Cartao(this.id, this.emitidoEm, this.titular, this.limite, proposta);
	}
}
