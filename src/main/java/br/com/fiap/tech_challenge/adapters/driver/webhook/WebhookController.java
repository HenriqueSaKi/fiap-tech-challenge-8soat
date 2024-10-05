package br.com.fiap.tech_challenge.adapters.driver.webhook;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway.PedidoGateway;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.PedidoRepository;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.webhook.swagger.WebhookSwaggerInterface;
import br.com.fiap.tech_challenge.core.application.usecase.impl.PedidoUseCaseImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/webhook")
public class WebhookController implements WebhookSwaggerInterface {

  private final PedidoRepository pedidoRepository;

  public WebhookController(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  @Override
  public ResponseEntity<String> notificationReceiver(Body body) {
    if(body.getStatus().equals("200")) {
      var pedidoGateway = new PedidoGateway(this.pedidoRepository);
      var pedidoUseCase = new PedidoUseCaseImpl(pedidoGateway);
      pedidoUseCase.atualizarStatusPedido(1L, SituacaoPedidoDTO.PAGAMENTO_RECEBIDO);

      return "Pagamento Recebido";
    }
    else {
      return "Pagamento Negado";
    }
  }

}
