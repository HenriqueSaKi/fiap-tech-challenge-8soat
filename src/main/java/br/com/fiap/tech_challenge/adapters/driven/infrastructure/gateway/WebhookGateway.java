package br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.WebhookPagamento;
import br.com.fiap.tech_challenge.core.application.ports.gateway.WebhookGatewayPort;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;

public class WebhookGateway implements WebhookGatewayPort {

  private final WebhookPagamento webhookPagamento;

  public WebhookGateway(WebhookPagamento webhookPagamento) {
    this.webhookPagamento = webhookPagamento;
  }

  @Override
  public Long processarPagamentoWebhookMP(Pedido pedido) {
    // TODO: Vai precisar passar as informações do pedido de alguma forma nesse método
    //  "webhookPagamento.processarPagamento()", porém.. Acho que será melhor tratar as informações antes de mandar.
    //  Talvez criar um entity assim como é feito para repository... Acho melhor validar..
    return webhookPagamento.processarPagamento();
  }

}
