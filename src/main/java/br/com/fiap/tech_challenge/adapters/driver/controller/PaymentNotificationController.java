package br.com.fiap.tech_challenge.adapters.driver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/payments")

public class PaymentNotificationController {
    public ResponseEntity<String> processPayment(@RequestBody PaymentNotification paymentNotification) {
        // atualiza o database com os dados recebidos
        System.out.println("Notificação de pagamento recebido no backend: " + paymentNotification);

        return ResponseEntity.ok("Pagamento processado com sucesso");
    }
}