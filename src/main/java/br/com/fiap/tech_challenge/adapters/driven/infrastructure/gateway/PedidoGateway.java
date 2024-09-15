package br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.PedidoRepository;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.PedidoEntity;
import br.com.fiap.tech_challenge.core.application.mapper.PedidoMapper;
import br.com.fiap.tech_challenge.core.application.ports.gateway.PedidoGatewayPort;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoGateway implements PedidoGatewayPort {

  private final PedidoRepository repository;
  private final PedidoMapper pedidoMapper;

  @Autowired
  public PedidoGateway(PedidoRepository repository, PedidoMapper pedidoMapper) {
    this.repository = repository;
    this.pedidoMapper = pedidoMapper;
  }

  @Override
  public void cadastrarPedidos(Pedido pedido) {
    PedidoEntity entity = pedidoMapper.toEntity(pedido);
    repository.save(entity);
  }

  @Override
  public List<Pedido> listaPedidos() {
    List<PedidoEntity> pedidoEntityList = repository.findAllWithActiveStatus();
    return pedidoEntityList.stream().map(pedidoMapper::toDTO).toList();
  }

  @Override
  public Pedido consultaStatusPedidoPorId(Long id) {
    Optional<PedidoEntity> pedidoEntity = repository.findById(id.intValue());
    return pedidoEntity.map(pedidoMapper::toDTO).orElse(null);
  }
}
