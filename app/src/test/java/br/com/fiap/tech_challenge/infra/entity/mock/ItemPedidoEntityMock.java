package br.com.fiap.tech_challenge.infra.entity.mock;

import br.com.fiap.tech_challenge.infra.entity.ItemPedidoEntity;

import java.math.BigDecimal;

public class ItemPedidoEntityMock {

    public static ItemPedidoEntity getItemPedidoEntity() {
        ItemPedidoEntity entity = new ItemPedidoEntity();
        entity.setDescricao("Item Entity Teste");
        entity.setQuantidade(2);
        entity.setValorUnitario(new BigDecimal("22.44"));
        entity.setValorTotalItem(new BigDecimal("44.88"));
        return entity;

    }

}
