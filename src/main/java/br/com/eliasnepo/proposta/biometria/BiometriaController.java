package br.com.eliasnepo.proposta.biometria;

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
import br.com.eliasnepo.proposta.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

	
	private final BiometriaRepository biometriaRepository;
	private final CartaoRepository cartaoRepository;
	
	public BiometriaController(BiometriaRepository biometriaRepository, CartaoRepository cartaoRepository) {
		this.biometriaRepository = biometriaRepository;
		this.cartaoRepository = cartaoRepository;
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<BiometriaResponse> insertNewBiometry(@PathVariable Long id, @RequestBody @Valid BiometriaRequest request) {
		Cartao cartao = cartaoRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Não existe um cartão com esse id."));
		
		Biometria biometria = request.toModel(cartao);
		biometria = biometriaRepository.save(biometria);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/biometrias/{id}")
				.buildAndExpand(biometria.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(new BiometriaResponse(biometria));
	}
	
	
}
