package br.com.eliasnepo.proposta.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.eliasnepo.proposta.feignclients.dto.SolicitacaoCartaoRequest;
import br.com.eliasnepo.proposta.feignclients.dto.SolicitacaoCartaoResponse;

@FeignClient(name = "cartao-solicitacao", url = "${cartao-solicitacao.url}", path = "${cartao-solicitacao.path}")
public interface CartaoFeignClient {

	@PostMapping
	SolicitacaoCartaoResponse request(SolicitacaoCartaoRequest request);
}
