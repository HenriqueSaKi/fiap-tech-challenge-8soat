package br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.PedidoRepository;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.ClienteEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.PedidoEntity;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.StatusPedidoReponseDTO;
import br.com.fiap.tech_challenge.core.application.mapper.ClienteMapperImpl;
import br.com.fiap.tech_challenge.core.application.mapper.PedidoMapperImpl;
import br.com.fiap.tech_challenge.core.application.ports.gateway.PedidoGatewayPort;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PedidoGateway implements PedidoGatewayPort {
  private final PedidoRepository pedidoRepository;

  public PedidoGateway(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  @Override
  public Long cadastrarPedidos(Pedido pedido, Cliente cliente) {
    PedidoEntity entity = new PedidoMapperImpl().toEntity(pedido);
    ClienteEntity clienteEntity = new ClienteMapperImpl().toClienteEntity(cliente);
    entity.setCliente(clienteEntity);
    return pedidoRepository.save(entity).getId();
  }

  @Override
  public List<Pedido> listaPedidos() {
    List<PedidoEntity> pedidoEntityList = pedidoRepository.findAllWithActiveStatus();
    List<Pedido> pedidos = new ArrayList<>();
    pedidoEntityList.forEach(
        entity -> pedidos.add(new PedidoMapperImpl().toDTO(entity))
    );
    return pedidos;
  }

  @Override
  public Pedido consultaStatusPedidoPorId(Long id) {
    Optional<PedidoEntity> pedidoEntity = pedidoRepository.findById(id.intValue());
    return pedidoEntity.map(new PedidoMapperImpl()::toDTO).orElse(null);
  }

  @Override
  public StatusPedidoReponseDTO atualizaStatusPedido(Pedido pedido, SituacaoPedidoDTO situacaoPedido) {
      PedidoEntity pedidoEntity = new PedidoMapperImpl().toEntity(pedido);
      pedidoEntity.setSituacao(SituacaoPedido.valueOf(situacaoPedido.name()));
      pedidoEntity = pedidoRepository.save(pedidoEntity);

      StatusPedidoReponseDTO response = new StatusPedidoReponseDTO();
      response.setIdPedido(pedidoEntity.getId());
      response.setSituacaoPedidoDTO(
          SituacaoPedidoDTO.valueOf(pedidoEntity.getSituacao().name()));
      return response;
  }

  @Override
  public Pedido consultaStatusPedidoPorMercadoPagoId(Long mercadoPagoId) {
    Optional<PedidoEntity> pedido = pedidoRepository.findByMercadoPagoId(mercadoPagoId);
    return pedido.map(new PedidoMapperImpl()::toDTO).orElse(null);
  }

}
