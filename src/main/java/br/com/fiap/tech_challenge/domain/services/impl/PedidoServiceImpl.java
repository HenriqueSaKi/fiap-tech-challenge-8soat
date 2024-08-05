package br.com.fiap.tech_challenge.domain.services.impl;

import br.com.fiap.tech_challenge.domain.ItemPedidoDTO;
import br.com.fiap.tech_challenge.domain.PedidoDTO;
import br.com.fiap.tech_challenge.domain.enums.SituacaoPedido;
import br.com.fiap.tech_challenge.domain.repository.PedidoRepositoryPort;
import br.com.fiap.tech_challenge.domain.services.PedidoService;
import br.com.fiap.tech_challenge.domain.repository.entity.ItemPedidoEntity;
import br.com.fiap.tech_challenge.domain.repository.entity.PedidoEntity;
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
    public void cadastrarPedido(PedidoDTO pedidoDTO) {
        PedidoEntity entity = new PedidoEntity();
        entity.setDataPedido(new Date());
        entity.setValorTotalPedido(pedidoDTO.getValorTotalPedido());

        // Valor mockado por conta do FAKE CHECKOUT
        entity.setSituacao(SituacaoPedido.PAGO);

        List<ItemPedidoEntity> itensEntity = new ArrayList<>();
        pedidoDTO.getItens().forEach(item -> {
            ItemPedidoEntity itemEntity = new ItemPedidoEntity();
            BeanUtils.copyProperties(item, itemEntity);
            itemEntity.setValorTotalItem(item.getValorTotalItem());
            itensEntity.add(itemEntity);

        });

        entity.setItensPedido(itensEntity);
        repositoryPort.cadastrarPedidos(entity);
    }

    @Override
    public List<PedidoDTO> listarPedidos() {
        List<PedidoEntity> pedidoEntity = repositoryPort.listaPedidos();

        List<PedidoDTO> pedidoDTOS = new ArrayList<>();
        pedidoEntity.forEach(entity -> {
            PedidoDTO pedidoDTO = new PedidoDTO();
            BeanUtils.copyProperties(entity, pedidoDTO);
            pedidoDTO.setSituacaoPedido(entity.getSituacao());

            List<ItemPedidoDTO> itens = new ArrayList<>();
            entity.getItensPedido().forEach(item -> {
                ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
                BeanUtils.copyProperties(item, itemPedidoDTO);
                itens.add(itemPedidoDTO);
            });

            pedidoDTO.setItens(itens);
            pedidoDTOS.add(pedidoDTO);
        });

        return pedidoDTOS;
    }

}
