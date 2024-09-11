package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter.mock;

import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.PedidoEntity;

import java.util.Date;
import java.util.List;

public class PedidoEntityMock {

    public static PedidoEntity getPedidoEntity() {
        PedidoEntity entity = new PedidoEntity();
        entity.setDataPedido(new Date());
        entity.setItensPedido(List.of(
                ItemPedidoEntityMock.getItemPedidoEntity()
        ));
        entity.setSituacao(SituacaoPedido.PAGAMENTO_RECEBIDO);

        return entity;
    }

}
