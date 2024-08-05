package br.com.fiap.tech_challenge.domain.services;

import br.com.fiap.tech_challenge.domain.PedidoDTO;

import java.util.List;

public interface PedidoService {
    void cadastrarPedido(PedidoDTO pedidoDTO);
    List<PedidoDTO> listarPedidos();

}
