package br.com.fiap.tech_challenge.core.application.service;

import br.com.fiap.tech_challenge.core.application.mapper.PedidoMapper;
import br.com.fiap.tech_challenge.core.domain.model.ItemPedidoDTO;
import br.com.fiap.tech_challenge.core.domain.model.PedidoDTO;
import br.com.fiap.tech_challenge.core.domain.ports.in.PedidoServicePort;
import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import br.com.fiap.tech_challenge.core.application.ports.repository.PedidoRepositoryPort;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ItemPedidoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.PedidoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PedidoServicePortImpl implements PedidoServicePort {

    private PedidoRepositoryPort repositoryPort;
    private PedidoMapper pedidoMapper;

    @Autowired
    public PedidoServicePortImpl(PedidoRepositoryPort repositoryPort, PedidoMapper pedidoMapper) {
        this.repositoryPort = repositoryPort;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public void cadastrarPedido(PedidoDTO pedidoDTO) {
        PedidoEntity entity = pedidoMapper.toEntity(pedidoDTO);
        entity.setDataPedido(new Date());
        entity.setValorTotalPedido(pedidoDTO.getValorTotalPedido());

        // Valor mockado por conta do FAKE CHECKOUT
        entity.setSituacao(SituacaoPedido.PAGO);

        repositoryPort.cadastrarPedidos(entity);
    }

    @Override
    public List<PedidoDTO> listarPedidos() {
        List<PedidoEntity> pedidoEntity = repositoryPort.listaPedidos();

        List<PedidoDTO> pedidoDTOS = new ArrayList<>();
        pedidoEntity.forEach(entity -> {
            PedidoDTO pedidoDTO = pedidoMapper.toDTO(entity);
            pedidoDTOS.add(pedidoDTO);
        });

        return pedidoDTOS;
    }

}
