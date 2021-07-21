package br.com.eliasnepo.proposta.avisos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.eliasnepo.proposta.cartao.Cartao;

@Entity
public class Aviso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String destiny;
	private LocalDate endOfTravelDate;
	private LocalDateTime instant;
	private String ip;
	private String userAgent;
	
	@ManyToOne
	private Cartao card;

	@Deprecated
	public Aviso() {
	}
	
	public Aviso(String destiny, LocalDate endOfTravelDate, String ip, String userAgent,
			Cartao card) {
		this.destiny = destiny;
		this.endOfTravelDate = endOfTravelDate;
		this.ip = ip;
		this.userAgent = userAgent;
		this.card = card;
		this.instant = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public String getDestiny() {
		return destiny;
	}

	public LocalDate getEndOfTravelDate() {
		return endOfTravelDate;
	}

	public LocalDateTime getInstant() {
		return instant;
	}

	public String getIp() {
		return ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public Cartao getCard() {
		return card;
	}
}
