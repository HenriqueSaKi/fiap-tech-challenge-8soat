package br.com.fiap.tech_challenge.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class Cliente {

    @Schema(name = "id do cliente", description = "Número de identificação do cliente", example = "1L")
    private Long id;

    @JsonProperty(required = true)
    @Schema(name = "cpf", description = "Número de documento", example = "12345678910")
    private String cpf;

    @JsonProperty(required = true)
    @Schema(name = "nomeCompleto", description = "Nome completo", example = "Nome Teste")
    private String nomeCompleto;

    @JsonProperty(required = true)
    @Schema(name = "email", description = "E-mail", example = "teste@exemplo.com")
    private String email;

    @Schema(name = "telefones", description = "Números de telefone")
    private List<Telefone> telefones;

    @Schema(name = "enderecos", description = "Endereços do cliente")
    private List<Endereco> enderecos;

    public String getCpf() {
        return cpf.replaceAll("[./-]", "");
    }
}
