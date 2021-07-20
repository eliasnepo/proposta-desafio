package br.com.eliasnepo.proposta.propostas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	Optional<Proposta> findByDocument(String document);
	
	@Query("SELECT obj FROM Proposta obj WHERE obj.cartao = null AND obj.status = 'ELEGIVEL'")
	List<Proposta> findAllPropostasAvailable();
}
