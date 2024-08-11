package br.com.fiap.tech_challenge.core.domain.model;

import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {

    @Schema(name = "id", description = "Número de identificação do pedido", example = "1L")
    private Integer id;

    @JsonProperty(required = true)
    @Schema(name = "nomeProduto", description = "Nome do produto", example = "Nome do Produto")
    private String nome;

    @JsonProperty(required = true)
    @Schema(name = "nomeDoProduto", description = "Descrição do produto", example = "Descrição do Produto")
    private String descricao;

    @JsonProperty(required = true)
    @Schema(name = "precoProduto", description = "Preço do produto", example = "Preço do Produto")
    private BigDecimal preco;

    @JsonProperty(required = true)
    @Schema(name = "categoriaDoProduto", description = "Categoria do produto", example = "Categoria do Produto")
    private CategoriaProduto categoria;
}
