package br.com.fiap.tech_challenge.core.domain.ports.in;

import br.com.fiap.tech_challenge.core.domain.model.PedidoDTO;

import java.util.List;

public interface PedidoServicePort {
    void cadastrarPedido(PedidoDTO pedidoDTO);
    List<PedidoDTO> listarPedidos();

}
