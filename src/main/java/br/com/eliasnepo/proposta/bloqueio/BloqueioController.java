package br.com.eliasnepo.proposta.bloqueio;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eliasnepo.proposta.cartao.Cartao;
import br.com.eliasnepo.proposta.cartao.CartaoRepository;
import br.com.eliasnepo.proposta.exceptions.ResourceNotFoundException;
import br.com.eliasnepo.proposta.feignclients.CartaoFeignClient;
import br.com.eliasnepo.proposta.feignclients.dto.BloqueioRequest;
import br.com.eliasnepo.proposta.feignclients.dto.BloqueioResponse;
import feign.FeignException.FeignClientException;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {

	private final BloqueioRepository bloqueioRepository;
	private final CartaoRepository cartaoRepository;
	private final CartaoFeignClient feignClient;
	
	static Logger log = LoggerFactory.getLogger(BloqueioController.class);

	public BloqueioController(BloqueioRepository bloqueioRepository, CartaoRepository cartaoRepository, CartaoFeignClient feignClient) {
		this.bloqueioRepository = bloqueioRepository;
		this.cartaoRepository = cartaoRepository;
		this.feignClient = feignClient;
	}

	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<?> fazerBloqueio(@PathVariable Long id, HttpServletRequest request) {
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
		String ip = request.getRemoteAddr();
		
		Cartao cartao = cartaoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nãoe existe um cartão com esse id."));
		
		try {
			BloqueioResponse response = feignClient.sistemaLegadoBloqueiaCartao(cartao.getNumber(), new BloqueioRequest("API de Propostas"));
			log.info(response.getResultado());
		} catch (FeignClientException e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}

		Bloqueio bloqueio = cartao.block(ip, userAgent);
		bloqueio = bloqueioRepository.save(bloqueio);

		return ResponseEntity.ok().build();
	}
}
