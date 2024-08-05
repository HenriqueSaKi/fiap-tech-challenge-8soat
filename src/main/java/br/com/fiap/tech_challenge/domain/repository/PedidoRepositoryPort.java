package br.com.fiap.tech_challenge.domain.repository;

import br.com.fiap.tech_challenge.domain.repository.entity.PedidoEntity;

import java.util.List;

public interface PedidoRepositoryPort {
    void cadastrarPedidos(PedidoEntity pedido);
    List<PedidoEntity> listaPedidos();

}
