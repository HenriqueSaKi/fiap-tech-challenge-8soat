package br.com.fiap.tech_challenge.application.swagger;

import br.com.fiap.tech_challenge.domain.Cliente;
import br.com.fiap.tech_challenge.domain.Pedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Tag(name = "Pedido", description = "Serviços relacionados ao cadastro dos pedidos")
public interface PedidoSwaggerInterface {

    @Operation(description = "Realiza o cadastro do pedido do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido cadastrado com sucesso!", content =
            @Content(mediaType = "application/json", examples =
            @ExampleObject(value = "Pedido cadastrado com sucesso!")))
    })
    @RequestMapping(
            value = "/",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<String> cadastrarPedido(@RequestBody Pedido pedido);

    @Operation(description = "Lista todos os pedidos em andamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista pedidos", content =
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum pedido foi encontrado", content =
            @Content(mediaType = "application/json"))
    })
    @RequestMapping(
            value = "/listar",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Object> listarPedidos();


}
