package br.com.eliasnepo.proposta.bloqueio;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eliasnepo.proposta.cartao.Cartao;
import br.com.eliasnepo.proposta.cartao.CartaoRepository;
import br.com.eliasnepo.proposta.exceptions.IllegalOperationException;
import br.com.eliasnepo.proposta.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {

	private final BloqueioRepository bloqueioRepository;
	private final CartaoRepository cartaoRepository;

	public BloqueioController(BloqueioRepository bloqueioRepository, CartaoRepository cartaoRepository) {
		this.bloqueioRepository = bloqueioRepository;
		this.cartaoRepository = cartaoRepository;
	}

	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<?> fazerBloqueio(@PathVariable Long id, HttpServletRequest request) {
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
		String ip = request.getRemoteAddr();

		Cartao cartao = cartaoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nãoe existe um cartão com esse id."));
		
		if (cartao.getStatus() == BloqueioStatus.BLOQUEADO) {
			throw new IllegalOperationException("Esse cartão já está bloqueado.");
		}
		
		Bloqueio bloqueio = cartao.block(ip, userAgent);
		bloqueio = bloqueioRepository.save(bloqueio);

		return ResponseEntity.ok().build();
	}
}
