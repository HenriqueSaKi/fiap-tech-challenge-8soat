package br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.impl;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.WebhookPagamento;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class WebhookMercadoPagoImpl implements WebhookPagamento {

  private final static Logger LOGGER = LoggerFactory.getLogger(WebhookMercadoPagoImpl.class);

  @Override
  public Long processarPagamento() {
    MercadoPagoConfig.setAccessToken(System.getenv("ACCESS_TOKEN_MP"));

    PaymentClient client = new PaymentClient();

    PaymentCreateRequest createRequest =
        PaymentCreateRequest.builder()
            .transactionAmount(new BigDecimal(1000)) //TODO: Colocar o valor do pedido, vai precisar incluir parametros no metodo
            .token("your_cardtoken") //TODO: Não sei o que é
            .description("description") //TODO: Pode remover ou colocar algo como o nome da loja/projeto
            .installments(1) //TODO: Remover
            .paymentMethodId("visa") //TODO: Ajustar
            .payer(PaymentPayerRequest.builder().email("dummy_email").build()) //TODO: passar email do cliente
            .build();

    Payment payment = new Payment();
    try {
      payment = client.create(createRequest);
    } catch (MPApiException ex) {
      LOGGER.error(String.format("MercadoPago Error. Status: %s, Content: %s%n",
          ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent()));
    } catch (MPException ex) {
      LOGGER.error(ex.getMessage());
    }
    return payment.getId();
  }

}
