package br.com.fiap.tech_challenge.infra.entity.mock;

import br.com.fiap.tech_challenge.domain.enums.SituacaoPedido;
import br.com.fiap.tech_challenge.domain.repository.entity.PedidoEntity;

import java.util.Date;
import java.util.List;

public class PedidoEntityMock {

    public static PedidoEntity getPedidoEntity() {
        PedidoEntity entity = new PedidoEntity();
        entity.setDataPedido(new Date());
        entity.setItensPedido(List.of(
                ItemPedidoEntityMock.getItemPedidoEntity()
        ));
        entity.setSituacao(SituacaoPedido.PAGO);

        return entity;
    }

}
