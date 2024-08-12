package br.com.fiap.tech_challenge.adapters.driver.controller.swagger;

import br.com.fiap.tech_challenge.core.domain.model.ProdutoDTO;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import io.swagger.v3.oas.annotations.Operation;
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

@Tag(name = "Produto", description = "Serviços relacionados ao cadastro dos produtos")
public interface ProdutoSwaggerInterface {

    @Operation(description = "Realiza o cadastro do produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso!", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Produto cadastrado com sucesso!"))),
            @ApiResponse(responseCode = "400", description = "Produto já cadastrado.", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Produto já cadastrado."))),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar produto.", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Ocorreu um erro inesperado ao cadastrar o produto.")))
    })
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST)
    ResponseEntity<String> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO);

    @Operation(description = "Lista todos os produtos de determinada categoria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso!", content =
                @Content(mediaType = "application/text", schema =
                @Schema(implementation = ProdutoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Não foram encontrados produtos para essa categoria."))),
            @ApiResponse(responseCode = "500", description = "Erro ao consultar produto", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Ocorreu um erro inesperado ao consultar os produtos dessa categoria.")))
    })
    @RequestMapping(
            value = "/categoria/{categoria}",
            method = RequestMethod.GET)
    ResponseEntity<Object> consultaPorCategoria(@PathVariable CategoriaProduto categoria);

    @Operation(description = "Atualiza as informações de determinado produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Produto atualizado com sucesso!", content =
                @Content(mediaType = "application/json", examples =
                @ExampleObject(value = "Produto atualizado com sucesso!"))),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Não foram encontrados produtos para essa categoria."))),
            @ApiResponse(responseCode = "500", description = "Erro ao consultar produto", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Ocorreu um erro inesperado ao atualizar esse produto.")))
    })
    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT)
    ResponseEntity<String> atualizaInformacoesProduto(@RequestBody ProdutoDTO produtoDTO);

    @Operation(description = "Exclui determinado produto pelo número id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto excluido com sucesso!", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Produto excluido com sucesso!"))),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Não foram encontrados produtos com esse id."))),
            @ApiResponse(responseCode = "500", description = "Erro ao consultar produto", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Ocorreu um erro inesperado ao consultar os produtos dessa categoria.")))
    })
    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.DELETE)
    ResponseEntity<String> excluirProduto(@PathVariable Long id);


}