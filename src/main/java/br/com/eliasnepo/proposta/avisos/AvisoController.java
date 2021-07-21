package br.com.eliasnepo.proposta.avisos;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eliasnepo.proposta.cartao.Cartao;
import br.com.eliasnepo.proposta.cartao.CartaoRepository;
import br.com.eliasnepo.proposta.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/avisos")
public class AvisoController {

	private final AvisoRepository avisoRepository;
	private final CartaoRepository cartaoRepository;
	
	static final Logger log = LoggerFactory.getLogger(AvisoController.class);
	
	public AvisoController(AvisoRepository avisoRepository, CartaoRepository cartaoRepository) {
		this.avisoRepository = avisoRepository;
		this.cartaoRepository = cartaoRepository;
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> fazerAviso(@PathVariable Long id, @RequestBody @Valid AvisoRequest bodyRequest, HttpServletRequest request) {
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
		String ip = request.getRemoteAddr();
		
		Cartao cartao = cartaoRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe um cartão com esse id."));
		
		Aviso aviso = bodyRequest.toModel(ip, userAgent, cartao);
		aviso = avisoRepository.save(aviso);
		log.info(aviso.getId().toString());
		
		return ResponseEntity.ok().build();
	}
}
