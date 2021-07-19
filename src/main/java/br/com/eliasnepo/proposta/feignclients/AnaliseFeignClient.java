package br.com.eliasnepo.proposta.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eliasnepo.proposta.feignclients.dto.ApiAnaliseRequest;
import br.com.eliasnepo.proposta.feignclients.dto.ApiAnaliseResponse;

@Component
@FeignClient(name = "requester", url = "${api-requester.url}", path = "${api-requester.path}")
public interface AnaliseFeignClient {

	@PostMapping
	ApiAnaliseResponse sendData(@RequestBody ApiAnaliseRequest request);
}
