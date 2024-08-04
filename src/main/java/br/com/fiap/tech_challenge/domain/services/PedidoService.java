package br.com.fiap.tech_challenge.domain.services;

import br.com.fiap.tech_challenge.domain.Pedido;

import java.util.List;

public interface PedidoService {
    void cadastrarPedido(Pedido pedido);
    List<Pedido> listarPedidos();

}
