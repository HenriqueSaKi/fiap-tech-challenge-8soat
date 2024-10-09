package br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.impl;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.WebhookPagamento;
import br.com.fiap.tech_challenge.core.application.usecase.PagamentoDTO;
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
  public Long processarPagamento(PagamentoDTO pagamentoDTO) {
    MercadoPagoConfig.setAccessToken(System.getenv("ACCESS_TOKEN_MP"));

    PaymentClient client = new PaymentClient();

    PaymentCreateRequest createRequest =
        PaymentCreateRequest.builder()
            .transactionAmount(pagamentoDTO.getValor()) //TODO: Colocar o valor do pedido, vai precisar incluir parametros no metodo
            .token("your_cardtoken") //TODO: Não sei o que é
            .description("Mock da descrição do pedido") //TODO: Pode remover ou colocar algo como o nome da loja/projeto
            .installments(1) //TODO: Remover
            .paymentMethodId("pix")
            .binaryMode(true)
            .payer(PaymentPayerRequest.builder().email(pagamentoDTO.getEmailCliente()).build()) //TODO: passar email do cliente
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
