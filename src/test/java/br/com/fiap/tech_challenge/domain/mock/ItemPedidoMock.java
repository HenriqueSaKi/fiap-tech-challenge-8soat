package br.com.fiap.tech_challenge.domain.mock;

import br.com.fiap.tech_challenge.domain.ItemPedidoDTO;

import java.math.BigDecimal;

public class ItemPedidoMock {

    public static ItemPedidoDTO getItemPedido() {
        ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
        itemPedidoDTO.setDescricao("Item Teste");
        itemPedidoDTO.setQuantidade(2);
        itemPedidoDTO.setValorUnitario(new BigDecimal("12.34"));

        return itemPedidoDTO;

    }

}
