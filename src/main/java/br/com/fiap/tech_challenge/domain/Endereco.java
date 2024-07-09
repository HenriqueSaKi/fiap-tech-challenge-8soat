package br.com.fiap.tech_challenge.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Endereco {

    @Schema(name = "cep", description = "CEP", example = "12345-12")
    String cep;

    @Schema(name = "logradouro", description = "Logradouro", example = "Rua exemplo, 123")
    String logradouro;

    @Schema(name = "complemento", description = "Complemento", example = "Bloco A, Apto 123")
    String complemento;

    @Schema(name = "bairro", description = "Bairro", example = "Vila Exemplo")
    String bairro;

    @Schema(name = "cidade", description = "Cidade", example = "São Paulo")
    String cidade;

    @Schema(name = "estado", description = "Estado", examples = "SP")
    String estado;

}
