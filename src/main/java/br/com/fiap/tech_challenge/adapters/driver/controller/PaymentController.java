@RestController
@RequestMapping("/api/payments")

public class PaymentController {
    public ResponseEntity<String> processPayment(@RequestBody PaymentNotification paymentNotification) {
        // atualiza o database com os dados recebidos
        System.out.println("Notificação de pagamento recebido no backend: " + paymentNotification);

        return ResponseEntity.ok("Pagamento processado com sucesso");
    }
}