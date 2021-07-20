package br.com.eliasnepo.proposta.propostas;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.eliasnepo.proposta.exceptions.DocumentException;
import br.com.eliasnepo.proposta.exceptions.ResourceNotFoundException;
import br.com.eliasnepo.proposta.feignclients.AnaliseFeignClient;
import br.com.eliasnepo.proposta.feignclients.dto.ApiAnaliseRequest;
import br.com.eliasnepo.proposta.feignclients.dto.ApiAnaliseResponseStatus;
import feign.FeignException;
import feign.FeignException.FeignClientException;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	private final PropostaRepository propostaRepository;
	private AnaliseFeignClient requesterDataClient;
	
	public PropostaController(PropostaRepository propostaRepository, AnaliseFeignClient requesterDataClient) {
		this.propostaRepository = propostaRepository;
		this.requesterDataClient = requesterDataClient;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PropostaResponse> insertProposta(@RequestBody @Valid PropostaRequest request) {
		Optional<Proposta> propostaExists = propostaRepository.findByDocument(request.getDocument());
		if (propostaExists.isPresent()) {
			throw new DocumentException("Esse documento já existe.");
		}
		
		Proposta proposta = request.toModel();
		proposta = propostaRepository.save(proposta);
		
		proposta = requestApi(proposta);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(proposta.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PropostaResponse> findById(@PathVariable Long id) {
		Proposta proposta = propostaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não exista proposta com esse id."));
		return ResponseEntity.ok(new PropostaResponse(proposta));
	}
	
	private Proposta requestApi(Proposta proposta) {
		ApiAnaliseResponseStatus response = ApiAnaliseResponseStatus.COM_RESTRICAO;
		
		try {
			response = requesterDataClient
					.sendData(new ApiAnaliseRequest(proposta.getDocument(), proposta.getName(), proposta.getId().toString()))
					.getResultadoSolicitacao();
		} catch (FeignClientException ex) {
			if (ex.getClass() == FeignException.UnprocessableEntity.class) {
				proposta.setStatus(ApiAnaliseResponseStatus.COM_RESTRICAO);
				return proposta;
			}
		}
		
		proposta.setStatus(response);
		return proposta;
	}
}
