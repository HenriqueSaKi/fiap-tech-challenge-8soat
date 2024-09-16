package br.com.fiap.tech_challenge.adapters.driver.controller.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedidoDTO {

    @JsonProperty(required = true)
    @Schema(name = "idProduto", description = "Id do Produto", example = "1L")
    @NotNull
    private Long idProduto;

    @JsonProperty(required = true)
    @Schema(name = "quantidade", description = "Quantidade", example = "2")
    @NotNull
    private int quantidade;

}
