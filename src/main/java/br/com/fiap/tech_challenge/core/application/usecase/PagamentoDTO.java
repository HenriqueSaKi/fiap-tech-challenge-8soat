package br.com.fiap.tech_challenge.core.application.usecase;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PagamentoDTO {
    private String idPedido;
    private BigDecimal valor;
    private Long idCliente;
    private String nomeCliente;
    private String emailCliente;

    //String idPedido, BigDecimal valor
    public PagamentoDTO() {
        this.idPedido = idPedido;
        this.valor = valor;
    }
}