package br.com.fiap.tech_challenge.core.application.usecase;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.StatusPedidoReponseDTO;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;

import java.util.List;

public interface PedidoUseCase {
  void cadastrarPedido(Pedido pedido);
  List<Pedido> listarPedidos();
  StatusPedidoReponseDTO consultaStatusPedido(Long id);

}
