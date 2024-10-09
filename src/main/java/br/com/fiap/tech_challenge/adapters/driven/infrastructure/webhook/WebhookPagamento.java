package br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook;

import br.com.fiap.tech_challenge.core.application.usecase.PagamentoDTO;

public interface WebhookPagamento {

  Long processarPagamento(PagamentoDTO pagamentoDTO);

}
