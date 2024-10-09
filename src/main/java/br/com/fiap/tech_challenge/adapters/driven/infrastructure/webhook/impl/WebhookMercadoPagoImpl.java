package br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.impl;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.WebhookPagamento;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.entity.PagamentoDTO;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WebhookMercadoPagoImpl implements WebhookPagamento {

  private final static Logger LOGGER = LoggerFactory.getLogger(WebhookMercadoPagoImpl.class);

  @Override
  public Long processarPagamento(PagamentoDTO pagamentoDTO) {
    MercadoPagoConfig.setAccessToken(System.getenv("ACCESS_TOKEN_MP"));

    PaymentClient client = new PaymentClient();

    PaymentCreateRequest createRequest =
        PaymentCreateRequest.builder()
            .transactionAmount(pagamentoDTO.getValor())
            .description("Loja Tech Challenge")
            .installments(1)
            .paymentMethodId("pix")
            .payer(
                PaymentPayerRequest.builder()
                    .email(pagamentoDTO.getEmailCliente())
                    .firstName(pagamentoDTO.getPrimeiroNome())
                    .lastName(pagamentoDTO.getSobrenome())
                    .identification(
                        IdentificationRequest.builder()
                            .type("CPF")
                            .number(pagamentoDTO.getCpf())
                            .build()
                    )
                    .build())
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
