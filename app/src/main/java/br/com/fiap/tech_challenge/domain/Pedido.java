package br.com.fiap.tech_challenge.domain;

import br.com.fiap.tech_challenge.domain.enums.SituacaoPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Pedido {

    @Schema(name = "dataPedido", description = "Data em que o pedido foi atualizado")
    private Date dataPedido;

    @Schema(name = "valorTotalPedido", description = "Valor total do pedido", example = "100.22")
    private BigDecimal valorTotalPedido;

    @Schema(name = "situacaoPedido", description = "Status do pedido")
    private SituacaoPedido situacaoPedido;

    @Schema(name = "itens", description = "Lista de itens do pedido")
    private List<ItemPedido> itens;

    public BigDecimal getValorTotalPedido() {
        return itens.stream()
                .map(ItemPedido::getValorTotalItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
