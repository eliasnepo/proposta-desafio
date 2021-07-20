package br.com.eliasnepo.proposta.biometria;

import javax.validation.constraints.NotBlank;

import br.com.eliasnepo.proposta.cartao.Cartao;
import br.com.eliasnepo.proposta.validation.ValidBase64;

public class BiometriaRequest {

	@NotBlank
	@ValidBase64
	private String biometry;

	public BiometriaRequest() {
	}

	public BiometriaRequest(@NotBlank String biometry) {
		this.biometry = biometry;
	}

	public String getBiometry() {
		return biometry;
	}

	public Biometria toModel(Cartao cartao) {
		return new Biometria(this.biometry, cartao);
	}
}
