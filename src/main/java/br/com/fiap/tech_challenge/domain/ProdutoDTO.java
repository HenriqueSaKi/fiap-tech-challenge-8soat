package br.com.fiap.tech_challenge.domain;

import br.com.fiap.tech_challenge.domain.enums.CategoriaProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {
    @NotBlank
    private String nomeProduto;
    @NotBlank
    private String descricaoProduto;
    @NotNull
    private BigDecimal precoProduto;
    @NotNull
    private CategoriaProduto categoriaProduto;
}
