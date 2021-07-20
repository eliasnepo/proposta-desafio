package br.com.eliasnepo.proposta.biometria;

import java.time.LocalDateTime;

public class BiometriaResponse {

	private Long id;
	private String biometry;
	private LocalDateTime createdAt;
	private Long cardId;
	
	public BiometriaResponse() {
	}
	
	public BiometriaResponse(Biometria entity) {
		this.id = entity.getId();
		this.biometry = entity.getBiometry();
		this.createdAt = entity.getCreatedAt();
		this.cardId = entity.getCard().getId();
	}

	public Long getId() {
		return id;
	}

	public String getBiometry() {
		return biometry;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Long getCardId() {
		return cardId;
	}
}
