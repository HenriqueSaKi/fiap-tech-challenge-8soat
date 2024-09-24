package br.com.fiap.tech_challenge.adapters.driver.controller;

import br.com.fiap.tech_challenge.adapters.driver.controller.mapper.PedidoDTOMapper;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.StatusPedidoRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.PedidosResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.StatusPedidoReponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.swagger.PedidoSwaggerInterface;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import br.com.fiap.tech_challenge.core.application.usecase.PedidoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/pedido")
public class PedidoController implements PedidoSwaggerInterface {

    private final PedidoUseCase pedidoUseCase;
    private final PedidoDTOMapper pedidoDTOMapper;

    @Autowired
    public PedidoController(PedidoUseCase pedidoUseCase, PedidoDTOMapper pedidoDTOMapper) {
        this.pedidoUseCase = pedidoUseCase;
        this.pedidoDTOMapper = pedidoDTOMapper;
    }

    @Override
    public ResponseEntity<String> cadastrarPedido(CadastrarPedidoDTO cadastrar) {
        Pedido pedido = pedidoDTOMapper.cadastrarToPedido(cadastrar);
        Long id = pedidoUseCase.cadastrarPedido(pedido);
        return new ResponseEntity<>("Pedido cadastrado com sucesso. Código: " + id,
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> listarPedidos() {
        PedidosResponseDTO pedidoDTOS = new PedidosResponseDTO();
        List<Pedido> pedidos = pedidoUseCase.listarPedidos();
        pedidoDTOS.setPedidos(pedidoDTOMapper.pedidosToPedidosResponseDTO(pedidos));
        return new ResponseEntity<>(pedidoDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> consultaStatusPedido(Long id) {
        StatusPedidoReponseDTO statusPedido = pedidoUseCase.consultaStatusPedido(id);
        return new ResponseEntity<>(statusPedido, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> atualizaStatusPedido(Long id, StatusPedidoRequestDTO request) {
        StatusPedidoReponseDTO statusPedido = pedidoUseCase.atualizarStatusPedido(id, request.getSituacaoPedido());
        return new ResponseEntity<>(statusPedido, HttpStatus.ACCEPTED);
    }

}
