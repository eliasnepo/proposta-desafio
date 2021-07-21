package br.com.eliasnepo.proposta.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.eliasnepo.proposta.feignclients.dto.AvisoApiRequest;
import br.com.eliasnepo.proposta.feignclients.dto.AvisoApiResponse;
import br.com.eliasnepo.proposta.feignclients.dto.BloqueioRequest;
import br.com.eliasnepo.proposta.feignclients.dto.BloqueioResponse;
import br.com.eliasnepo.proposta.feignclients.dto.SolicitacaoCartaoRequest;
import br.com.eliasnepo.proposta.feignclients.dto.SolicitacaoCartaoResponse;

@FeignClient(name = "cartao-solicitacao", url = "${cartao-solicitacao.url}", path = "${cartao-solicitacao.path}")
public interface CartaoFeignClient {

	@PostMapping
	SolicitacaoCartaoResponse request(SolicitacaoCartaoRequest request);
	
	@PostMapping(value = "/{id}/bloqueios")
    BloqueioResponse sistemaLegadoBloqueiaCartao(@PathVariable String id, BloqueioRequest request);
	
	@PostMapping(value = "/{id}/avisos")
	AvisoApiResponse sistemaLegadoAvisoCartao(@PathVariable String id, AvisoApiRequest request);
}
