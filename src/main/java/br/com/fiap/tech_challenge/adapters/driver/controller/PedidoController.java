package br.com.fiap.tech_challenge.adapters.driver.controller;

import br.com.fiap.tech_challenge.adapters.driver.controller.swagger.PedidoSwaggerInterface;
import br.com.fiap.tech_challenge.core.domain.model.PedidoDTO;
import br.com.fiap.tech_challenge.core.domain.ports.in.PedidoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/pedido")
public class PedidoController implements PedidoSwaggerInterface {

    private PedidoServicePort service;

    @Autowired
    public PedidoController(PedidoServicePort service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<String> cadastrarPedido(PedidoDTO pedidoDTO) {
        service.cadastrarPedido(pedidoDTO);
        return new ResponseEntity<>("Pedido cadastrado com sucesso",
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> listarPedidos() {
        List<PedidoDTO> pedidoDTOS = service.listarPedidos();
        return new ResponseEntity<>(pedidoDTOS, HttpStatus.OK);
    }

}
