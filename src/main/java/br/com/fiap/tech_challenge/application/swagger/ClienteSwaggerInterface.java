package br.com.fiap.tech_challenge.application.swagger;

import br.com.fiap.tech_challenge.domain.ClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Tag(name = "Cliente", description = "Serviços relacionados às transações cadastrais do cliente")
public interface ClienteSwaggerInterface {

    @Operation(description = "Verifica se o serviço está disponível.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content =
                @Content(mediaType = "application/json", examples =
                @ExampleObject(value = "OK")))
    })
    @RequestMapping(
            value = "/health",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<String> heathCheck();

    @Operation(description = "Realiza o cadastro do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso!", content =
                @Content(mediaType = "application/json", examples =
                @ExampleObject(value = "Cliente cadastrado com sucesso!")))
    })
    @RequestMapping(
            value = "/",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<String> cadastrarCliente(@RequestBody ClienteDTO clienteDTO);

    @Operation(description = "Consulta cliente pelo número do documento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado", content =
                @Content(mediaType = "application/json", schema =
                @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content =
                @Content(mediaType = "application/json"))
    })
    @RequestMapping(
            value = "/{cpf}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Object> buscarClientePorCPF(@Parameter(in = ParameterIn.PATH, description = "Número do documento do cliente", required = true, schema = @Schema()) @PathVariable String cpf);

    @Operation(description = "Atualiza informações do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Cliente atualizado com sucesso", content =
                @Content(mediaType = "application/json", schema =
                @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar as informações do cliente.", content =
                @Content(mediaType = "application/json"))
    })
    @RequestMapping(
            value = "/",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Object> atualizarCliente(@RequestBody ClienteDTO clienteDTO);

    @Operation(description = "Exclui as informações do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente excluído com sucesso!", content =
                @Content(mediaType = "application/json", examples =
                @ExampleObject(value = "Cliente excluído com sucesso!"))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.", content =
                @Content(mediaType = "application/json", examples =
                @ExampleObject(value = "Cliente não encontrado.")))
    })
    @RequestMapping(
            value = "/id/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<String> excluirCliente(@Parameter(in = ParameterIn.PATH, description = "Id do cliente", required = true, schema = @Schema()) @PathVariable Long id);

}
