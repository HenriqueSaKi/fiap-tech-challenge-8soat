package br.com.fiap.tech_challenge.core.application.ports.gateway;

import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;

public interface WebhookGatewayPort {

  Long processarPagamentoWebhookMP(Pedido pedido, Cliente cliente);

}
