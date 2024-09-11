package br.com.fiap.tech_challenge.core.application.ports.repository;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.PedidoEntity;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.StatusPedidoReponseDTO;

import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryPort {
  void cadastrarPedidos(PedidoEntity pedido);
  List<PedidoEntity> listaPedidos();
  Optional<PedidoEntity> consultaStatusPedidoPorId(Long id);

}
