package br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.PedidoRepository;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.ClienteEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.PedidoEntity;
import br.com.fiap.tech_challenge.core.application.mapper.ClienteMapper;
import br.com.fiap.tech_challenge.core.application.mapper.PedidoMapper;
import br.com.fiap.tech_challenge.core.application.ports.gateway.PedidoGatewayPort;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoGateway implements PedidoGatewayPort {
  private final ClienteMapper clienteMapper;
  private final PedidoRepository pedidoRepository;
  private final PedidoMapper pedidoMapper;

  @Autowired
  public PedidoGateway(ClienteMapper clienteMapper,
                       PedidoRepository pedidoRepository,
                       PedidoMapper pedidoMapper) {
    this.clienteMapper = clienteMapper;
    this.pedidoRepository = pedidoRepository;
    this.pedidoMapper = pedidoMapper;
  }

  @Override
  public Long cadastrarPedidos(Pedido pedido, Cliente cliente) {
    PedidoEntity entity = pedidoMapper.toEntity(pedido);
    ClienteEntity clienteEntity = clienteMapper.toClienteEntity(cliente);
    entity.setCliente(clienteEntity);
    return pedidoRepository.save(entity).getId();
  }

  @Override
  public List<Pedido> listaPedidos() {
    List<PedidoEntity> pedidoEntityList = pedidoRepository.findAllWithActiveStatus();
    return pedidoEntityList.stream().map(pedidoMapper::toDTO).toList();
  }

  @Override
  public Pedido consultaStatusPedidoPorId(Long id) {
    Optional<PedidoEntity> pedidoEntity = pedidoRepository.findById(id.intValue());
    return pedidoEntity.map(pedidoMapper::toDTO).orElse(null);
  }
}
