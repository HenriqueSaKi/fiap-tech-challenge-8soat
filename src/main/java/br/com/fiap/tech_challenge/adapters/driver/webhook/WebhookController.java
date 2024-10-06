package br.com.fiap.tech_challenge.adapters.driver.webhook;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway.PedidoGateway;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.PedidoRepository;
import br.com.fiap.tech_challenge.adapters.driver.webhook.model.WebhookRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.webhook.swagger.WebhookSwaggerInterface;
import br.com.fiap.tech_challenge.core.application.usecase.impl.WebhookUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/webhook")
/*
{
  "data": {
     "id": "999999999"
 }
}
*/
public class WebhookController implements WebhookSwaggerInterface {

  private final PedidoRepository pedidoRepository;

  public WebhookController(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  @Override
  public ResponseEntity<String> notificationReceiver(WebhookRequestDTO requestDTO) {
    if(requestDTO.getStatus().equals("200")) {
      var pedidoGateway = new PedidoGateway(this.pedidoRepository);
      var webhookUseCase = new WebhookUseCaseImpl(pedidoGateway);
      webhookUseCase.atualizarStatusPedido(requestDTO.getId());

      return new ResponseEntity<>("Pagamento Recebido", HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>("Pagamento Negado", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
