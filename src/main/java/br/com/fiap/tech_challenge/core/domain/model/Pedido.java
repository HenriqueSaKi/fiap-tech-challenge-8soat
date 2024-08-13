package br.com.fiap.tech_challenge.core.domain.model;

import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Pedido {

    private String id;
    private Date dataPedido;
    private BigDecimal valorTotalPedido;
    private SituacaoPedido situacaoPedido;
    private List<ItemPedido> itens = new ArrayList<>();

}
