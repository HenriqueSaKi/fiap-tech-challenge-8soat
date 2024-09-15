package br.com.fiap.tech_challenge.core.application.ports.gateway;

import br.com.fiap.tech_challenge.core.domain.model.Pedido;

import java.util.List;

public interface PedidoGatewayPort {
  void cadastrarPedidos(Pedido pedido);
  List<Pedido> listaPedidos();
  Pedido consultaStatusPedidoPorId(Long id);

}
