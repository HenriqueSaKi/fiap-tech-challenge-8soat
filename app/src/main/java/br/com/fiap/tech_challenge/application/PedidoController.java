package br.com.fiap.tech_challenge.application;

import br.com.fiap.tech_challenge.application.swagger.PedidoSwaggerInterface;
import br.com.fiap.tech_challenge.domain.Pedido;
import br.com.fiap.tech_challenge.domain.services.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/pedido")
public class PedidoController implements PedidoSwaggerInterface {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<String> cadastrarPedido(Pedido pedido) {
        try {
            service.cadastrarPedido(pedido);
            return new ResponseEntity<>("Pedido cadastrado com sucesso",
                    HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Erro ao cadastrar pedido. Motivo: " + e.getMessage() ,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> listarPedidos() {
        List<Pedido> pedidos = service.listarPedidos();

        if(pedidos.isEmpty()) {
            return new ResponseEntity<>("Nenhum pedido foi encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

}
