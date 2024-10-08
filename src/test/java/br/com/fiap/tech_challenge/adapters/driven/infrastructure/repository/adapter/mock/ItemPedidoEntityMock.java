package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter.mock;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ItemPedidoEntity;

import java.math.BigDecimal;

public class ItemPedidoEntityMock {

    public static ItemPedidoEntity getItemPedidoEntity() {
        ItemPedidoEntity entity = new ItemPedidoEntity();
        entity.setDescricao("Item Entity Teste");
        entity.setQuantidade(2);
        entity.setValorUnitario(new BigDecimal("22.44"));
        entity.setValorTotalPedido(new BigDecimal("44.88"));
        return entity;

    }

}
