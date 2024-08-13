package br.com.fiap.tech_challenge.core.domain.model;

import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<ItemPedidoDTO> itens = new ArrayList<>();

}
