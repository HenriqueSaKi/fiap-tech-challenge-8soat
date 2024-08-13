package br.com.fiap.tech_challenge.core.domain.mock;

import br.com.fiap.tech_challenge.core.domain.model.ItemPedido;

import java.math.BigDecimal;

public class ItemPedidoMock {

    public static ItemPedido getItemPedido() {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setDescricao("Item Teste");
        itemPedido.setQuantidade(2);
        itemPedido.setValorUnitario(new BigDecimal("12.34"));

        return itemPedido;

    }

}
