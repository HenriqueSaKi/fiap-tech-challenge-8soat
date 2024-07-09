package br.com.fiap.tech_challenge.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Cliente {

    @Schema(name = "cpf", description = "Número de documento", example = "12345678910")
    String cpf;

    @Schema(name = "nome completo", description = "Nome completo", example = "12345678910")
    String nomeCompleto;

    @Schema(name = "email", description = "E-mail", example = "teste@exemplo.com")
    String email;

    @Schema(name = "telefone", description = "Telefone/celular", example = "+55 11 91234-1234")
    String telefone;

}
