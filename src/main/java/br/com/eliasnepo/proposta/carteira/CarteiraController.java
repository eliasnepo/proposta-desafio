package br.com.eliasnepo.proposta.carteira;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.eliasnepo.proposta.cartao.Cartao;
import br.com.eliasnepo.proposta.cartao.CartaoRepository;
import br.com.eliasnepo.proposta.cartao.CarteiraNome;
import br.com.eliasnepo.proposta.exceptions.IllegalOperationException;
import br.com.eliasnepo.proposta.exceptions.ResourceNotFoundException;
import br.com.eliasnepo.proposta.feignclients.CartaoFeignClient;
import br.com.eliasnepo.proposta.feignclients.dto.CarteiraApiRequest;
import br.com.eliasnepo.proposta.feignclients.dto.CarteiraApiResponse;
import feign.FeignException.UnprocessableEntity;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

	private final CarteiraRepository carteiraRepository;
	private final CartaoRepository cartaoRepository;
	private final CartaoFeignClient feignClient;
	
	public CarteiraController(CarteiraRepository carteiraRepository, CartaoRepository cartaoRepository,
			CartaoFeignClient feignClient) {
		this.carteiraRepository = carteiraRepository;
		this.cartaoRepository = cartaoRepository;
		this.feignClient = feignClient;
	}
	
	@PostMapping("/{id}/associa-paypal")
	public ResponseEntity<?> associarCarteiraPaypal(@RequestBody @Valid CarteiraRequest request, @PathVariable Long id) {
		CarteiraNome walletName = CarteiraNome.PAYPAL;
		return associaCarteira(walletName, id, request);
	}
	
	@PostMapping("/{id}/associa-samsung-pay")
	public ResponseEntity<?> associarCarteiraSamsungPay(@RequestBody @Valid CarteiraRequest request, @PathVariable Long id) {
		CarteiraNome walletName = CarteiraNome.SAMSUNG_PAY;
		return associaCarteira(walletName, id, request);
	}
	
	private ResponseEntity<?> associaCarteira(CarteiraNome walletName, Long id, CarteiraRequest request) {
		Cartao cartao = cartaoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existe cartão com esse id."));
		
		CarteiraApiResponse response;
		
		try {
			response = feignClient
					.associarCarteira(cartao.getNumber(), new CarteiraApiRequest(request.getEmail(), walletName.toString()));
		} catch (UnprocessableEntity e) {
			throw new IllegalOperationException("Cartão já associado a essa carteira.");
		}
		
		Carteira carteira = new Carteira(response.getId(), walletName, cartao);
		carteiraRepository.save(carteira);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/carteiras/{id}")
				.buildAndExpand(carteira.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(new CarteiraResponse(carteira));
	}
}
