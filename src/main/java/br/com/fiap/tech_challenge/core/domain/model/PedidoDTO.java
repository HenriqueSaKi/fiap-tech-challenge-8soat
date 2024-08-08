package br.com.fiap.tech_challenge.core.domain.model;

import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    public BigDecimal getValorTotalPedido() {
        return itens.stream()
                .map(ItemPedidoDTO::getValorTotalItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
