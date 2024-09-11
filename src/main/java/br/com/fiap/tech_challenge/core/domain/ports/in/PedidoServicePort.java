package br.com.fiap.tech_challenge.core.domain.ports.in;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.StatusPedidoReponseDTO;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;

import java.util.List;

public interface PedidoServicePort {
  void cadastrarPedido(Pedido pedido);
  List<Pedido> listarPedidos();
  StatusPedidoReponseDTO consultaStatusPedido(Long id);

}
