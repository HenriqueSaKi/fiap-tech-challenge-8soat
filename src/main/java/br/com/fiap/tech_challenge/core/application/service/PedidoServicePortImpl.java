package br.com.fiap.tech_challenge.core.application.service;

import br.com.fiap.tech_challenge.core.application.exception.pedido.ErroAoCadastrarPedidoException;
import br.com.fiap.tech_challenge.core.application.exception.pedido.NenhumPedidoEncontradoException;
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

import static br.com.fiap.tech_challenge.core.application.constant.PedidoExceptionConstante.ERRO_AO_CADASTRAR_PEDIDO_EXCEPTION;
import static br.com.fiap.tech_challenge.core.application.constant.PedidoExceptionConstante.NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION;

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
        try {
            PedidoEntity entity = pedidoMapper.toEntity(pedidoDTO);
            entity.setDataPedido(new Date());
            entity.setValorTotalPedido(pedidoDTO.getValorTotalPedido());

            // Valor mockado por conta do FAKE CHECKOUT
            entity.setSituacao(SituacaoPedido.PAGO);

            repositoryPort.cadastrarPedidos(entity);
        }
        catch (Exception e) {
            throw new ErroAoCadastrarPedidoException(ERRO_AO_CADASTRAR_PEDIDO_EXCEPTION);
        }
    }

    @Override
    public List<PedidoDTO> listarPedidos() {
        List<PedidoEntity> pedidoEntity = repositoryPort.listaPedidos();
        if(pedidoEntity.isEmpty()) {
            throw new NenhumPedidoEncontradoException(NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION);
        }

        List<PedidoDTO> pedidoDTOS = new ArrayList<>();
        pedidoEntity.forEach(entity -> {
            PedidoDTO pedidoDTO = pedidoMapper.toDTO(entity);
            pedidoDTOS.add(pedidoDTO);
        });

        return pedidoDTOS;
    }

}
