package br.com.fiap.tech_challenge.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedido {

    @Schema(name = "descricao", description = "Valor total do pedido", example = "Item Teste")
    private String descricao;

    @Schema(name = "valorUnitario", description = "Valor unitário", example = "50.11")
    private BigDecimal valorUnitario;

    @Schema(name = "quantidade", description = "Quantidade", example = "2")
    private int quantidade;

    @Schema(name = "valorTotalItem", description = "Valor total", example = "100.22")
    private BigDecimal valorTotalItem;

    public BigDecimal getValorTotalItem() {
        return valorUnitario.multiply(
                BigDecimal.valueOf(quantidade, 0));
    }

}
