package br.com.eliasnepo.proposta.cartao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.eliasnepo.proposta.feignclients.CartaoFeignClient;
import br.com.eliasnepo.proposta.feignclients.dto.SolicitacaoCartaoRequest;
import br.com.eliasnepo.proposta.feignclients.dto.SolicitacaoCartaoResponse;
import br.com.eliasnepo.proposta.propostas.Proposta;
import br.com.eliasnepo.proposta.propostas.PropostaRepository;

@Component
public class AutoAssociaCartao {

	@Autowired
	private PropostaRepository propostaRepository;
	@Autowired
	private CartaoFeignClient cartaoFeignClient;
	
	@Scheduled(fixedDelayString = "${scheduled-cartao.time}")
	public void associarCartaoComProposta() {
		List<Proposta> propostasDisponiveis = propostaRepository.findAllPropostasAvailable();
		
		propostasDisponiveis.forEach(proposta -> {
			SolicitacaoCartaoResponse response = cartaoFeignClient.request(new SolicitacaoCartaoRequest(proposta.getDocument(), proposta.getName(), proposta.getId().toString()));
			proposta.associaCartao(response);
			propostaRepository.save(proposta);
		});
	}
}
