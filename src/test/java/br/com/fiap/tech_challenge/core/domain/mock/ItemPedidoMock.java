package br.com.fiap.tech_challenge.core.domain.mock;

import br.com.fiap.tech_challenge.core.domain.model.ItemPedido;

import java.math.BigDecimal;

public class ItemPedidoMock {

    public static ItemPedido getItemPedido() {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setIdProduto(1L);
        itemPedido.setQuantidade(2);;

        return itemPedido;

    }

}
