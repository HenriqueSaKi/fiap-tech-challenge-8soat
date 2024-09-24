package br.com.fiap.tech_challenge.core.application.usecase;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.StatusPedidoReponseDTO;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;

import java.util.List;

public interface PedidoUseCase {
  Long cadastrarPedido(Pedido pedido);
  List<Pedido> listarPedidos();
  StatusPedidoReponseDTO consultaStatusPedido(Long id);
  StatusPedidoReponseDTO atualizarStatusPedido(Long id, SituacaoPedidoDTO situacaoPedido);

}
