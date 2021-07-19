package br.com.eliasnepo.proposta.feignclients.dto;

import br.com.eliasnepo.proposta.propostas.PropostaStatus;

public enum ApiAnaliseResponseStatus {
	COM_RESTRICAO {
		@Override
		public PropostaStatus toModel() {
			return PropostaStatus.NAO_ELEGIVEL;
		}
	},
	SEM_RESTRICAO {
		@Override
		public PropostaStatus toModel() {
			return PropostaStatus.ELEGIVEL;
		}
	};
	
	public abstract PropostaStatus toModel();
}
