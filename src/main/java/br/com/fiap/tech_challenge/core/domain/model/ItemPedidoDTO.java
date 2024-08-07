package br.com.fiap.tech_challenge.core.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedidoDTO {

    @JsonProperty(required = true)
    @Schema(name = "descricao", description = "Valor total do pedido", example = "Item Teste")
    private String descricao;

    @JsonProperty(required = true)
    @Schema(name = "valorUnitario", description = "Valor unitário", example = "50.11")
    private BigDecimal valorUnitario;

    @JsonProperty(required = true)
    @Schema(name = "quantidade", description = "Quantidade", example = "2")
    private int quantidade;

    @Schema(name = "valorTotalItem", description = "Valor total", example = "100.22")
    private BigDecimal valorTotalItem;

    public BigDecimal getValorTotalItem() {
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade, 0));
    }

}
