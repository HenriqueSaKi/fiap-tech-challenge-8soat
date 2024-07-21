package br.com.fiap.tech_challenge.domain.services.impl;

import br.com.fiap.tech_challenge.domain.ItemPedido;
import br.com.fiap.tech_challenge.domain.Pedido;
import br.com.fiap.tech_challenge.domain.enums.SituacaoPedido;
import br.com.fiap.tech_challenge.domain.repository.PedidoRepositoryPort;
import br.com.fiap.tech_challenge.domain.services.PedidoService;
import br.com.fiap.tech_challenge.infra.entity.ItemPedidoEntity;
import br.com.fiap.tech_challenge.infra.entity.PedidoEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoServiceImpl implements PedidoService {

    PedidoRepositoryPort repositoryPort;

    public PedidoServiceImpl(PedidoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public void cadastrarPedido(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        entity.setDataPedido(new Date());
        entity.setSituacao(SituacaoPedido.RECEBIDO);
        entity.setValorTotalPedido(pedido.getValorTotalPedido());

        List<ItemPedidoEntity> itensEntity = new ArrayList<>();
        pedido.getItens().forEach(item -> {
            ItemPedidoEntity itemEntity = new ItemPedidoEntity();
            BeanUtils.copyProperties(item, itemEntity);
            itemEntity.setValorTotalItem(item.getValorTotalItem());
            itensEntity.add(itemEntity);

        });

        entity.setItensPedido(itensEntity);
        repositoryPort.cadastrarPedidos(entity);
    }

    @Override
    public List<Pedido> listarPedidos() {
        List<PedidoEntity> pedidoEntity = repositoryPort.listaPedidos();

        List<Pedido> pedidos = new ArrayList<>();
        if(!pedidoEntity.isEmpty()) {
            List<PedidoEntity> pedidosEntity = pedidoEntity.stream().toList();
            pedidosEntity.forEach(entity -> {
                Pedido pedido = new Pedido();
                BeanUtils.copyProperties(entity, pedido);
                pedido.setSituacaoPedido(entity.getSituacao());

                List<ItemPedido> itens = new ArrayList<>();
                entity.getItensPedido().forEach(item -> {
                    ItemPedido itemPedido = new ItemPedido();
                    BeanUtils.copyProperties(item, itemPedido);
                    itens.add(itemPedido);
                });

                pedido.setItens(itens);
                pedidos.add(pedido);
            });

        }

        return pedidos;
    }
}
