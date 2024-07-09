package br.com.fiap.tech_challenge.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class Cliente {

    @Schema(name = "cpf", description = "Número de documento", example = "12345678910")
    private String cpf;

    @Schema(name = "nome completo", description = "Nome completo", example = "12345678910")
    private String nomeCompleto;

    @Schema(name = "email", description = "E-mail", example = "teste@exemplo.com")
    private String email;

    @Schema(name = "telefone", description = "Telefone/celular", example = "+55 11 91234-1234")
    private String telefone;

    @Schema(name = "enderecos", description = "Endereços do cliente")
    private List<Endereco> enderecos;

}
