package br.com.fiap.tech_challenge.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Endereco {

    @Schema(name = "cep", description = "CEP", example = "12345-12")
    private String cep;

    @Schema(name = "logradouro", description = "Logradouro", example = "Rua exemplo, 123")
    private String logradouro;

    @Schema(name = "complemento", description = "Complemento", example = "Bloco A, Apto 123")
    private String complemento;

    @Schema(name = "bairro", description = "Bairro", example = "Vila Exemplo")
    private String bairro;

    @Schema(name = "cidade", description = "Cidade", example = "São Paulo")
    private String cidade;

    @Schema(name = "estado", description = "Estado", examples = "SP")
    private String estado;

}
