package br.com.fiap.tech_challenge.domain;

import br.com.fiap.tech_challenge.domain.enums.SituacaoPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class PedidoDTO {

    @Schema(name = "id", description = "Identificador único do pedido", example = "000001")
    private String id;

    @Schema(name = "dataPedido", description = "Data em que o pedido foi atualizado")
    private Date dataPedido;

    @Schema(name = "valorTotalPedido", description = "Valor total do pedido", example = "100.22")
    private BigDecimal valorTotalPedido;

    @Schema(name = "situacaoPedido", description = "Status do pedido")
    private SituacaoPedido situacaoPedido;

    @Schema(name = "itens", description = "Lista de itens do pedido")
    private List<ItemPedidoDTO> itens;

    public PedidoDTO(Date dataPedido, SituacaoPedido situacaoPedido, List<ItemPedidoDTO> itens) {
        this.id = UUID.randomUUID().toString();
        this.dataPedido = dataPedido;
        this.situacaoPedido = situacaoPedido;
        this.itens = itens;
        this.valorTotalPedido = getValorTotalPedido();
    }

    public BigDecimal getValorTotalPedido() {
        return itens.stream()
                .map(ItemPedidoDTO::getValorTotalItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "Pedido{"+
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", valorTotalPedido=" + valorTotalPedido +
                ", situacaoPedido=" + situacaoPedido +
                ", itens=" + itens +
                "}";
    }
}
