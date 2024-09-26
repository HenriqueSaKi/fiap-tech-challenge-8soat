package br.com.fiap.tech_challenge.webhook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class PaymentWebhookController {

  @PostMapping("/payment-status")
  public ResponseEntity<String> receivePaymentStatus(@RequestBody PaymentStatusRequest paymentStatusRequest) {
    // processa confirmação de pagamento
    if ("APPROVED".equals(paymentStatusRequest.getStatus())) {
      System.out.println("Pagamento aprovado: " + paymentStatusRequest.getTransactionId());
    } else if ("REFUSED".equals(paymentStatusRequest.getStatus())) {
      System.out.println("Pagamento recusado: " + paymentStatusRequest.getTransactionId());
    }

    return ResponseEntity.ok("Pagamento recebido com sucesso");
  }

  private boolean isValidToken(String token) {
    // ainda estou um pouco perdido em como fazer a validação do token

    return "o_token_aqui".equals(token);
  }

}
