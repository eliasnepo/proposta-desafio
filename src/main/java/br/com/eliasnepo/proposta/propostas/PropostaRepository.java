package br.com.eliasnepo.proposta.propostas;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	Optional<Proposta> findByDocument(String document);
}
