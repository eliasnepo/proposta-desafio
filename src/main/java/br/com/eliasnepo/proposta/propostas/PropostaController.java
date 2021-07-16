package br.com.eliasnepo.proposta.propostas;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.eliasnepo.proposta.exceptions.DocumentException;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	private final PropostaRepository propostaRepository;

	public PropostaController(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}
	
	@PostMapping
	public ResponseEntity<?> insertProposta(@RequestBody @Valid PropostaRequest request) {
		Optional<Proposta> propostaExists = propostaRepository.findByDocument(request.getDocument());
		if (propostaExists.isPresent()) {
			throw new DocumentException("Esse documento já existe.");
		}
		
		Proposta proposta = request.toModel();
		proposta = propostaRepository.save(proposta);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(proposta.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
	}
}
